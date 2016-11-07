package org.ncibi.ws.mimi.guice;

import junit.framework.Assert;

import org.junit.Test;
import org.ncibi.db.EntityManagers;
import org.ncibi.db.PersistenceSession;
import org.ncibi.db.PersistenceUnit;
import org.ncibi.mqueue.MessageQueue;
import org.ncibi.mqueue.MessageQueueConfiguration;
import org.ncibi.mqueue.persistent.AnyMessagePersistentMessageQueue;
import org.ncibi.task.logger.PersistentTaskLogger;
import org.ncibi.task.logger.TaskLogger;
import org.ncibi.ws.guice.GuiceFinder;
import org.ncibi.ws.mimi.resource.compound.CompoundResource;
import org.ncibi.ws.mimi.resource.reaction.ReactionResource;
import org.ncibi.ws.resource.lrpath.LRPathResource;
import org.ncibi.ws.resource.status.StatusResource;
import org.ncibi.ws.resource.think.gsea.GseaThinkResource;
import org.ncibi.ws.resource.think.lrpath.LRThinkResource;
import org.ncibi.ws.server.CompoundResourceModule;
import org.ncibi.ws.server.GseaThinkResourceModule;
import org.ncibi.ws.server.LRPathResourceModule;
import org.ncibi.ws.server.LRThinkResourceModule;
import org.ncibi.ws.server.ReactionResourceModule;
import org.ncibi.ws.server.StatusResourceModule;
import org.restlet.resource.ServerResource;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thoughtworks.xstream.XStream;

public class GuiceConfigurationTest
{
    private static final XStream xstream = new XStream();
    private static final PersistenceSession wsPersistence = new PersistenceUnit(
            EntityManagers.newEntityManagerFromProject("task"));
    private static final MessageQueue queue = new AnyMessagePersistentMessageQueue(wsPersistence,
            MessageQueueConfiguration.queueName());
    private static final TaskLogger taskLogger = new PersistentTaskLogger(wsPersistence);

    @Test
    public void testGuiceConfiguration()
    {
        System.out.println("CompoundResource");
        testConfigurationFor(new CompoundResourceModule(xstream), CompoundResource.class);

        System.out.println("ReactionResource");
        testConfigurationFor(new ReactionResourceModule(xstream), ReactionResource.class);

        System.out.println("LRPathResource");
        testConfigurationFor(new LRPathResourceModule(queue, wsPersistence, taskLogger), LRPathResource.class);

        System.out.println("LRThinkResource");
        testConfigurationFor(new LRThinkResourceModule(queue, wsPersistence, taskLogger),
                LRThinkResource.class);

        System.out.println("GseaThinkResource");
        testConfigurationFor(new GseaThinkResourceModule(queue, wsPersistence, taskLogger),
                GseaThinkResource.class);

        System.out.println("StatusResource");
        testConfigurationFor(new StatusResourceModule(wsPersistence, xstream), StatusResource.class);
    }

    private void testConfigurationFor(AbstractModule module, Class<? extends ServerResource> resource)
    {
        final Injector injector = Guice.createInjector(module);
        GuiceFinder finder = new GuiceFinder(injector, null, null);
        ServerResource r = finder.create(resource, null, null);
        Assert.assertNotNull(r);
        Assert.assertTrue(r.getClass().equals(resource));
    }
}
