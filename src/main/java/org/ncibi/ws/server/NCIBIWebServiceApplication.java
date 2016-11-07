package org.ncibi.ws.server;

import org.ncibi.db.EntityManagers;
import org.ncibi.db.PersistenceSession;
import org.ncibi.db.PersistenceUnit;
import org.ncibi.mqueue.MessageQueue;
import org.ncibi.mqueue.MessageQueueConfiguration;
import org.ncibi.mqueue.persistent.AnyMessagePersistentMessageQueue;
import org.ncibi.task.logger.PersistentTaskLogger;
import org.ncibi.task.logger.TaskLogger;
import org.ncibi.ws.mimi.resource.compound.CompoundResource;
import org.ncibi.ws.mimi.resource.gene.GeneResource;
import org.ncibi.ws.mimi.resource.reaction.ReactionResource;
import org.ncibi.ws.resource.g2m.Gene2MeSHResource;
import org.ncibi.ws.resource.g2m.Gene2MeSHResourceModule;
import org.ncibi.ws.resource.interaction.InteractionResource;
import org.ncibi.ws.resource.lrpath.LRPathResource;
import org.ncibi.ws.resource.nlp.parser.stanford.StanfordParserResource;
import org.ncibi.ws.resource.nlp.splitter.mxterminator.MxTerminatorSplitterResource;
import org.ncibi.ws.resource.restlet.AbstractRestletApplication;
import org.ncibi.ws.resource.s2m.Substance2MeSHResource;
import org.ncibi.ws.resource.s2m.Substance2MeSHResourceModule;
import org.ncibi.ws.resource.status.StatusResource;
import org.ncibi.ws.resource.think.gsea.GseaThinkResource;
import org.ncibi.ws.resource.think.lrpath.LRThinkResource;
import org.ncibi.ws.resource.wsapi.WsApiResource;

import com.thoughtworks.xstream.XStream;

public class NCIBIWebServiceApplication extends AbstractRestletApplication
{
    private static final XStream xstream = new XStream();
    private static final PersistenceSession wsPersistence = new PersistenceUnit(
            EntityManagers.newEntityManagerFromProject("task"));
    private static final MessageQueue queue = new AnyMessagePersistentMessageQueue(wsPersistence,
            MessageQueueConfiguration.queueName());
    private static final TaskLogger taskLogger = new PersistentTaskLogger(wsPersistence);

    @Override
    public void configureRoutes()
    {
        attachRoute("/gene/{geneid}", GeneResource.class);
        attachRoute("/interaction", InteractionResource.class);
        attachRoute("/wsapi/{apicall}", WsApiResource.class);
        // attachRoute("/upload", ThinkbackResource.class);
        attachDIConfiguredRoute("/g2m/{searchTerm}", Gene2MeSHResource.class, new Gene2MeSHResourceModule(
                xstream));
        attachDIConfiguredRoute("/g2m/gene/id/{geneid}", Gene2MeSHResource.class,
                new Gene2MeSHResourceModule(xstream));
        attachDIConfiguredRoute("/g2m/gene/symbol/{symbol}", Gene2MeSHResource.class,
                new Gene2MeSHResourceModule(xstream));
        attachDIConfiguredRoute("/g2m/mesh/id/{descriptorid}", Gene2MeSHResource.class,
                new Gene2MeSHResourceModule(xstream));
        attachDIConfiguredRoute("/g2m/mesh/descriptor/{descriptorSearchTerm}", Gene2MeSHResource.class,
                new Gene2MeSHResourceModule(xstream));
        attachDIConfiguredRoute("/s2m/{searchTerm}", Substance2MeSHResource.class,
                new Substance2MeSHResourceModule(xstream));
        attachDIConfiguredRoute("/s2m/substance/id/{compoundid}", Substance2MeSHResource.class,
                new Substance2MeSHResourceModule(xstream));
        attachDIConfiguredRoute("/s2m/substance/name/{preferred}", Substance2MeSHResource.class,
                new Substance2MeSHResourceModule(xstream));
        attachDIConfiguredRoute("/s2m/mesh/id/{descriptorid}", Substance2MeSHResource.class,
                new Substance2MeSHResourceModule(xstream));
        attachDIConfiguredRoute("/s2m/mesh/descriptor/{descriptorSearchTerm}", Substance2MeSHResource.class,
                new Substance2MeSHResourceModule(xstream));
        attachDIConfiguredRoute("/compound/{compoundid}", CompoundResource.class, new CompoundResourceModule(
                xstream));
        attachDIConfiguredRoute("/reaction/{reactionid}", ReactionResource.class, new ReactionResourceModule(
                xstream));
        attachDIConfiguredRoute("/lrpath", LRPathResource.class, new LRPathResourceModule(queue,
                wsPersistence, taskLogger));
        attachDIConfiguredRoute("/lrpath-think", LRThinkResource.class, new LRThinkResourceModule(queue,
                wsPersistence, taskLogger));
        attachDIConfiguredRoute("/gsea-think", GseaThinkResource.class, new GseaThinkResourceModule(queue,
                wsPersistence, taskLogger));
        attachDIConfiguredRoute("/splitter/mxterminator/{sentences}", MxTerminatorSplitterResource.class,
                new MxTerminatorResourceModule(queue, wsPersistence, taskLogger, xstream));
        attachDIConfiguredRoute("/parser/stanford/{sentences}", StanfordParserResource.class,
                new StanfordParserResourceModule(queue, wsPersistence, taskLogger, xstream));
        attachDIConfiguredRoute("/status/{uuid}/{service}", StatusResource.class, new StatusResourceModule(
                wsPersistence, xstream));
    }
}
