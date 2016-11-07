package org.ncibi.ws.server;

import org.ncibi.db.PersistenceSession;
import org.ncibi.mqueue.MessageQueue;
import org.ncibi.mqueue.task.PersistentTaskQueuer2;
import org.ncibi.mqueue.task.TaskQueuer;
import org.ncibi.task.logger.TaskLogger;
import org.ncibi.ws.resource.think.lrpath.LRThinkResource;
import org.ncibi.ws.thinkback.LRThinkArgs;
import org.restlet.resource.ServerResource;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class LRThinkResourceModule extends AbstractModule
{
    private final TaskLogger taskLogger;
    private final TaskQueuer<LRThinkArgs> taskQueuer; 
    
    public LRThinkResourceModule(MessageQueue queue, PersistenceSession persistence, TaskLogger taskLogger)
    {
        this.taskLogger = taskLogger;
        this.taskQueuer = new PersistentTaskQueuer2<LRThinkArgs>(queue, persistence);
    }
    
    @Override
    protected void configure()
    {
        bind(ServerResource.class).to(LRThinkResource.class);
        bind(TaskLogger.class).toInstance(taskLogger);
        bind(new TypeLiteral<TaskQueuer<LRThinkArgs>>(){}).toInstance(taskQueuer);
    }
}
