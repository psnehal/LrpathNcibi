package org.ncibi.ws.server;

import org.ncibi.db.PersistenceSession;
import org.ncibi.db.ws.SplitterArgs;
import org.ncibi.db.ws.Task;
import org.ncibi.mqueue.MessageQueue;
import org.ncibi.mqueue.task.PersistentTaskQueuer;
import org.ncibi.mqueue.task.TaskQueuer;
import org.ncibi.task.logger.TaskLogger;
import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.mimi.encoder.ResponseTaskXStreamEncoder;
import org.ncibi.ws.resource.nlp.splitter.mxterminator.MxTerminatorSplitterResource;
import org.restlet.resource.ServerResource;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.thoughtworks.xstream.XStream;

public class MxTerminatorResourceModule extends AbstractModule
{
    private final TaskLogger taskLogger;
    private final TaskQueuer<SplitterArgs> taskQueuer;
    private final XStream xstream;
    
    public MxTerminatorResourceModule(MessageQueue queue, PersistenceSession persistence, TaskLogger taskLogger, XStream xstream)
    {
        this.taskLogger = taskLogger;
        this.taskQueuer = new PersistentTaskQueuer<SplitterArgs>(queue, persistence);
        this.xstream = xstream;
    }
    
    @Override
    protected void configure()
    {
        bind(ServerResource.class).to(MxTerminatorSplitterResource.class);
        bind(TaskLogger.class).toInstance(taskLogger);
        bind(new TypeLiteral<TaskQueuer<SplitterArgs>>(){}).toInstance(taskQueuer);
        bind(new TypeLiteral<XmlEncoder<Response<Task>>>() {}).to(ResponseTaskXStreamEncoder.class);
        bind(XStream.class).toInstance(xstream);
    }
}
