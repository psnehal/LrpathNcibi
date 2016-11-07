package org.ncibi.ws.resource.nlp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.ncibi.db.ws.TaskType;
import org.ncibi.db.ws.SplitterArgs;
import org.ncibi.db.ws.Task;
import org.ncibi.mqueue.task.TaskQueuer;
import org.ncibi.mqueue.task.TaskUtil;
import org.ncibi.task.logger.TaskLogger;
import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;

import com.google.inject.Inject;

public abstract class AbstractNlpResource extends
        AbstractSimpleResponseServerResource<Task, SplitterArguments>
{
    protected abstract TaskType nlpCommandType();

    protected final TaskQueuer<SplitterArgs> taskQueuer;
    private final TaskLogger taskLogger;
    private final XmlEncoder<Response<Task>> encoder;

    @Inject
    public AbstractNlpResource(TaskQueuer<SplitterArgs> taskQueuer, TaskLogger taskLogger,
            final XmlEncoder<Response<Task>> encoder)
    {
        this.taskQueuer = taskQueuer;
        this.taskLogger = taskLogger;
        this.encoder = encoder;
    }

    @Override
    protected Task performResourceAction(SplitterArguments args)
    {
        String address = getRequest().getClientInfo().getAddress();

        Task task = TaskUtil.newQueuedTask(nlpCommandType());
        SplitterArgs st = new SplitterArgs();
        st.setUuid(task.getUuid());
        st.setSentences(args.getSentences());

        taskQueuer.queue(task, st);

        if (task != null)
        {
            taskLogger.logMessage(task, "Received request " + task.getTaskType() + " from "
                    + address);
        }

        return task;
    }

    @Override
    protected SplitterArguments getArgumentsToResource()
    {
        SplitterArguments args = new SplitterArguments(decode(getAttribute("sentences")));
        return args;
    }

    private String decode(String what)
    {
        try
        {
            return URLDecoder.decode(what, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            return "BAD FORMAT";
        }
    }

    @Override
    protected String response2Xml(Response<Task> response)
    {
        encoder.setResult(response);
        return encoder.toXml();
    }
}
