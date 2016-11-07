package org.ncibi.ws.resource.lrpath;

import org.apache.commons.lang.BooleanUtils;
import org.ncibi.db.ws.Task;
import org.ncibi.db.ws.TaskType;
import org.ncibi.lrpath.LRPathArguments;
import org.ncibi.mqueue.task.TaskQueuer;
import org.ncibi.mqueue.task.TaskUtil;
import org.ncibi.task.logger.TaskLogger;
import org.ncibi.ws.Response;
import org.ncibi.ws.resource.AbstractSimpleLRPathResponseServerResource;

import com.google.inject.Inject;

public class LRPathResource extends AbstractSimpleLRPathResponseServerResource<Task>
{
    protected final TaskQueuer<LRPathArguments> taskQueuer;
    protected final TaskLogger taskLogger;
    protected boolean asJavaXml = false;

    @Inject
    public LRPathResource(TaskQueuer<LRPathArguments> taskQueuer, TaskLogger taskLogger)
    {
        this.taskQueuer = taskQueuer;
        this.taskLogger = taskLogger;
    }

    @Override
    protected Task performResourceAction(LRPathArguments args)
    {
        Task task = TaskUtil.newQueuedTask(this.asJavaXml ? TaskType.LRPATH_JAVAX : TaskType.LRPATH);
        // args.setUuid(task.getUuid());
        taskQueuer.queue(task, args);
        if (task != null)
        {
            String address = getRequest().getClientInfo().getAddress();
            taskLogger.logMessage(task, "Received request " + task.getTaskType() + " from " + address);
        }
        return task;
    }

    @Override
    protected String response2Xml(Response<Task> response)
    {
        if (asJavaXml)
        {
            return response2JavaXml(response);
        }
        else
        {
            return response2NcibiXml(response);
        }
    }

    private String response2JavaXml(Response<Task> response)
    {
        Task t = response.getResponseValue();
        Response<String> r = new Response<String>(response.getResponseStatus(), t == null ? null
                : t.getUuid());
        UuidResponseEncoder encoder = new UuidResponseEncoder(r);
        String xml = encoder.toXmlString();
        return xml;
    }

    private String response2NcibiXml(Response<Task> response)
    {
        Task t = response.getResponseValue();
        StringBuilder sb = new StringBuilder();
        sb.append("<response>\n");
        if (!response.isSuccess())
        {
            sb.append("<status>false</status>\n");
        }
        else
        {
            sb.append("<status>true</status>\n");
            sb.append("<taskId>" + t == null ? null : t.getUuid() + "</taskId>");
        }
        sb.append("</response>\n");
        return sb.toString();

    }

    @Override
    protected LRPathArguments getArgumentsToResource()
    {
        LRPathArguments args = super.getArgumentsToResource();
        String dojavax = getArgumentWithDefault("javax", "false");
        this.asJavaXml = BooleanUtils.toBoolean(dojavax);
        return args;
    }
}
