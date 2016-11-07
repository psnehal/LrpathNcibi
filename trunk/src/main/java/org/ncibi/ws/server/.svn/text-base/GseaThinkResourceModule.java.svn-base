package org.ncibi.ws.server;

import org.ncibi.db.PersistenceSession;
import org.ncibi.mqueue.MessageQueue;
import org.ncibi.mqueue.task.PersistentTaskQueuer2;
import org.ncibi.mqueue.task.TaskQueuer;
import org.ncibi.task.logger.TaskLogger;
import org.ncibi.ws.resource.think.gsea.GseaThinkResource;
import org.ncibi.ws.thinkback.GseaThinkArgs;
import org.restlet.resource.ServerResource;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class GseaThinkResourceModule extends AbstractModule
{
    private final TaskLogger taskLogger;
    private final TaskQueuer<GseaThinkArgs> taskQueuer; 
    
    public GseaThinkResourceModule(MessageQueue queue, PersistenceSession persistence, TaskLogger taskLogger)
    {
        this.taskLogger = taskLogger;
        this.taskQueuer = new PersistentTaskQueuer2<GseaThinkArgs>(queue, persistence);
    }
    
    @Override
    protected void configure()
    {
        bind(ServerResource.class).to(GseaThinkResource.class);
        bind(TaskLogger.class).toInstance(taskLogger);
        bind(new TypeLiteral<TaskQueuer<GseaThinkArgs>>(){}).toInstance(taskQueuer);
    }
}
