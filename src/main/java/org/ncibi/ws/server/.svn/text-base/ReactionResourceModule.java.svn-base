package org.ncibi.ws.server;

import org.ncibi.mimiweb.data.MetabReaction;
import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.mimi.encoder.NcibiXStreamReactionEncoder;
import org.ncibi.ws.mimi.resource.reaction.ReactionResource;
import org.restlet.resource.ServerResource;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.thoughtworks.xstream.XStream;

public class ReactionResourceModule extends AbstractModule
{
    private final XStream xstream;

    public ReactionResourceModule(XStream xstream)
    {
        super();
        this.xstream = xstream;
    }
    
    @Override
    protected void configure()
    {
        bind(ServerResource.class).to(ReactionResource.class);
        bind(new TypeLiteral<XmlEncoder<Response<MetabReaction>>>()
        {
        }).to(NcibiXStreamReactionEncoder.class);
        bind(XStream.class).toInstance(xstream);
    }
}
