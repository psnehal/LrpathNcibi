package org.ncibi.ws.resource.status;

import java.io.IOException;
import java.util.List;

import org.ncibi.commons.io.FileInputLineProcessor;
import org.ncibi.db.PersistenceSession;
import org.ncibi.db.ws.Task;
import org.ncibi.lrpath.LRPathResult;
import org.ncibi.task.CTask;
import org.ncibi.task.TaskFileUtil;
import org.ncibi.task.TaskStatus;
import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.encoder.status.LRPathRequestStatusResponseEncoder;
import org.ncibi.ws.request.RequestStatus;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;

import com.google.inject.Inject;

public class StatusResource extends AbstractSimpleResponseServerResource<CTask, StatusArguments>
{
    private final PersistenceSession persistence;
    private final XmlEncoder<Response<CTask>> encoder;
    private String service = null;
    private String uuid;

    @Inject
    public StatusResource(PersistenceSession persistence, XmlEncoder<Response<CTask>> encoder)
    {
        this.persistence = persistence;
        this.encoder = encoder;
    }
    
    @Override
    protected CTask performResourceAction(StatusArguments args)
    {
        Task task = persistence.hqlQuery("from ws.Task where uuid = '" + args.getUuid() + "'").single();
        CTask ctask = new CTask(task.getUuid(), task.getStatus());
        return ctask;
    }

    @Override
    protected StatusArguments getArgumentsToResource()
    {
        uuid = getAttribute("uuid");
        StatusArguments args = new StatusArguments(uuid);
        service = getAttribute("service");
        return args;
    }

    @Override
    protected String response2Xml(Response<CTask> response)
    {
        if (response.getResponseValue().getStatus() == TaskStatus.DONE)
        {
            return readDoneFileXml(uuid);
        }
        else
        {
            return encodeResponseAsXml(response);
        }   
    }
    
    private String readDoneFileXml(String uuid)
    {
        final StringBuilder sb = new StringBuilder(10000);
        FileInputLineProcessor processor = new FileInputLineProcessor()
        {
            @Override
            public void processLine(String line) throws IOException
            {
                sb.append(line + "\n");
            }
        };

        String filepath = TaskFileUtil.taskFile(uuid);
        processor.process(filepath);
        return sb.toString();
    }
    
    private String encodeResponseAsXml(Response<CTask> response)
    {
        if ("lrpath".equals(service))
        {
            RequestStatus<List<LRPathResult>> request = new RequestStatus<List<LRPathResult>>(
                    response.getResponseValue(), null);
            Response<RequestStatus<List<LRPathResult>>> r = new Response<RequestStatus<List<LRPathResult>>>(
                    response.getResponseStatus(), request);
            LRPathRequestStatusResponseEncoder lrpathencoder = new LRPathRequestStatusResponseEncoder(r);
            return lrpathencoder.toXmlString();
        }
        else
        {
            encoder.setResult(response);
            return encoder.toXml();
        }
    }
}
