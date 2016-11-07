package org.ncibi.ws.server;

import org.ncibi.db.PersistenceSession;
import org.ncibi.lrpath.LRPathArguments;
import org.ncibi.mqueue.MessageQueue;
import org.ncibi.mqueue.task.PersistentTaskQueuer2;
import org.ncibi.mqueue.task.TaskQueuer;
import org.ncibi.task.logger.TaskLogger;
import org.ncibi.ws.resource.lrpath.LRPathResource;
import org.restlet.resource.ServerResource;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class LRPathResourceModule extends AbstractModule
{
    private final TaskLogger taskLogger;
    private final TaskQueuer<LRPathArguments> taskQueuer; 
    
    public LRPathResourceModule(MessageQueue queue, PersistenceSession persistence, TaskLogger taskLogger)
    {
        this.taskLogger = taskLogger;
        this.taskQueuer = new PersistentTaskQueuer2<LRPathArguments>(queue, persistence);
    }
    
    @Override
    protected void configure()
    {
        bind(ServerResource.class).to(LRPathResource.class);
        bind(TaskLogger.class).toInstance(taskLogger);
        bind(new TypeLiteral<TaskQueuer<LRPathArguments>>(){}).toInstance(taskQueuer);
    }
}
