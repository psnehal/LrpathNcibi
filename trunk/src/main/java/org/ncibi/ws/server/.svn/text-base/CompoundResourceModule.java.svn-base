package org.ncibi.ws.server;

import org.ncibi.mimiweb.data.Compound;
import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.mimi.encoder.NcibiXStreamCompoundEncoder;
import org.ncibi.ws.mimi.resource.compound.CompoundResource;
import org.restlet.resource.ServerResource;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.thoughtworks.xstream.XStream;

public class CompoundResourceModule extends AbstractModule
{
    private final XStream xstream;

    public CompoundResourceModule(XStream xstream)
    {
        super();
        this.xstream = xstream;
    }
    
    @Override
    protected void configure()
    {
        bind(ServerResource.class).to(CompoundResource.class);
        bind(new TypeLiteral<XmlEncoder<Response<Compound>>>()
        {
        }).to(NcibiXStreamCompoundEncoder.class);
        bind(XStream.class).toInstance(xstream);
    }
}
