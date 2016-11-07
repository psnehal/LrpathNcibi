package org.ncibi.ws.resource.think.gsea;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.ncibi.commons.lang.ArrayUtil;
import org.ncibi.db.ws.Task;
import org.ncibi.db.ws.TaskType;
import org.ncibi.mqueue.task.TaskQueuer;
import org.ncibi.mqueue.task.TaskUtil;
import org.ncibi.task.logger.TaskLogger;
import org.ncibi.uuid.UuidUtil;
import org.ncibi.ws.Response;
import org.ncibi.ws.ResponseStatus;
import org.ncibi.ws.resource.lrpath.UuidResponseEncoder;
import org.ncibi.ws.thinkback.GseaThinkArgs;
import org.ncibi.ws.thinkback.ThinkbackAdjustmentMethod;
import org.ncibi.ws.thinkback.ThinkbackEnrichmentMethod;
import org.restlet.data.MediaType;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

public class GseaThinkResource extends ServerResource
{
    private final TaskQueuer<GseaThinkArgs> taskQueuer;
    private final TaskLogger taskLogger;

    @Inject
    public GseaThinkResource(TaskQueuer<GseaThinkArgs> taskQueuer, TaskLogger taskLogger)
    {
        this.taskQueuer = taskQueuer;
        this.taskLogger = taskLogger;
    }

    @Post
    synchronized public String upload(Representation entity) throws Exception
    {
        GseaThinkArguments args = getArgumentsFromEntity(entity);
        boolean filesUploaded = tryToUploadFiles(entity, args);
        Task task = filesUploaded ? createAndSubmitTaskFromArgs(args) : null;

        return response2Xml(task);
    }

    private GseaThinkArguments getArgumentsFromEntity(Representation entity)
    {
        if (!MediaType.MULTIPART_FORM_DATA.equals(entity.getMediaType(), true))
        {
            return null;
        }

        GseaThinkArguments args = getDatafilesInEntity(entity);
        return args;
    }

    private GseaThinkArguments getDatafilesInEntity(Representation entity)
    {
        GseaThinkArguments args = new GseaThinkArguments();
        try
        {
            List<FileItem> fileItems = fileItemsForEntity(entity);
            for (FileItem fileItem : fileItems)
            {
                String fieldName = fileItem.getFieldName();
                if (fieldName.equals("file:template"))
                {
                    args.setTemplate(UuidUtil.tempUuidFile(fieldName));
                    args.setTemplateFileItem(fileItem);
                }
                else if (fieldName.equals("file:dataset"))
                {
                    args.setDataset(UuidUtil.tempUuidFile(fieldName));
                    args.setDatasetFileItem(fileItem);
                }
                else if (fieldName.equals("file:chipset"))
                {
                    args.setChipset(UuidUtil.tempUuidFile(fieldName));
                    args.setChipsetFileItem(fileItem);
                }
                else if (fieldName.equals("file:cls"))
                {
                    args.setClassfile(UuidUtil.tempUuidFile(fieldName));
                    args.setClassFileItem(fileItem);
                }
                else if (fieldName.equals("adjustmentMethod"))
                {
                    String value = getFileItemValueAsString(fileItem);
                    args.setAdjustMethod(ThinkbackAdjustmentMethod.valueOf(value));
                }
                else if (fieldName.equals("pathways"))
                {
                    String value = getFileItemValueAsString(fileItem);
                    List<String> pathways = Arrays.asList((ArrayUtil.toStringArray(value)));
                    args.setPathways(pathways);
                }
                else if (fieldName.equals("enrichmentMethod"))
                {
                    String value = getFileItemValueAsString(fileItem);
                    args.setEnrichmentMethod(ThinkbackEnrichmentMethod.valueOf(value));
                }
            }
        }
        catch (FileUploadException e)
        {
            // do something
        }

        return args;
    }

    private String getFileItemValueAsString(FileItem fileItem)
    {
        String value = null;
        try
        {
            value = new String(fileItem.get(), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();  
        }
        
        return value;
    }

    private List<FileItem> fileItemsForEntity(Representation entity) throws FileUploadException
    {
        DiskFileItemFactory dfiFactory = new DiskFileItemFactory();
        dfiFactory.setSizeThreshold(1000240);
        RestletFileUpload upload = new RestletFileUpload(dfiFactory);
        List<FileItem> fileItems = upload.parseRequest(getRequest());
        return fileItems;
    }

    private boolean tryToUploadFiles(Representation entity, GseaThinkArguments args)
    {
        boolean filesUploaded = true;

        if (entity != null)
        {
            try
            {
                uploadFiles(args);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                filesUploaded = false;
            }
        }

        return filesUploaded;
    }

    private void uploadFiles(GseaThinkArguments args) throws Exception
    {
        writeDataFilesInArgs(args);
    }

    private void writeDataFilesInArgs(GseaThinkArguments args) throws Exception
    {
        if (args.getTemplateFileItem() != null)
        {
            writeValidDataFileForFileItem(args.getTemplate(), args.getTemplateFileItem());
        }

        if (args.getDatasetFileItem() != null)
        {
            writeValidDataFileForFileItem(args.getDataset(), args.getDatasetFileItem());
        }

        if (args.getChipsetFileItem() != null)
        {
            writeValidDataFileForFileItem(args.getChipset(), args.getChipsetFileItem());
        }

        if (args.getClassFileItem() != null)
        {
            writeValidDataFileForFileItem(args.getClassfile(), args.getClassFileItem());
        }
    }

    private void writeValidDataFileForFileItem(String filePath, FileItem fileItem) throws Exception
    {
        File f = new File(filePath);
        if (f != null)
        {
            fileItem.write(f);
        }
    }

    private Task createAndSubmitTaskFromArgs(GseaThinkArguments args)
    {
        Task task = TaskUtil.newQueuedTask(TaskType.GSEA_THINK);
        GseaThinkArgs a = createDatabaseThinkbackArgs(task, args);
        taskQueuer.queue(task, a);
        if (task != null)
        {
            String address = getRequest().getClientInfo().getAddress();
            taskLogger.logMessage(task, "Received request " + task.getTaskType() + " from " + address);
        }

        return task;
    }

    private GseaThinkArgs createDatabaseThinkbackArgs(Task task, GseaThinkArguments args)
    {
        GseaThinkArgs a = new GseaThinkArgs();
        a.setUuid(task.getUuid());
        a.setAdjustmentMethod(args.getAdjustMethod());
        a.setDataset(args.getDataset());
        a.setTemplate(args.getTemplate());
        a.setClassfile(args.getClassfile());
        a.setChipfile(args.getChipset());
        
        /*
         * This code is here to work around a serialization error. The XML Serializer for Java
         * is not able to create an ArrayList when the values from Arrays.asList().
         */
        List<String> pathways = new ArrayList<String>();
        for (String p : args.getPathways())
        {
            pathways.add(p);
        }
        a.setPathways(pathways);

        return a;
    }

    private String response2Xml(Task task)
    {
        ResponseStatus status = task == null ? errorResponseStatus() : successResponseStatus();
        Response<String> r = new Response<String>(status, task == null ? null : task.getUuid());
        UuidResponseEncoder encoder = new UuidResponseEncoder(r);
        String xml = encoder.toXmlString();
        return xml;
    }

    private ResponseStatus errorResponseStatus()
    {
        // Map<String, String> arguments = BeanUtils.beanToMapOfFields(args);
        // arguments.remove("serialVersionUID"); //Null object contains this
        // field.
        ResponseStatus status = new ResponseStatus(null, false, "Failed!");
        return status;
    }

    private ResponseStatus successResponseStatus()
    {
        ResponseStatus status = new ResponseStatus(null, true, "Success!");
        return status;
    }
}