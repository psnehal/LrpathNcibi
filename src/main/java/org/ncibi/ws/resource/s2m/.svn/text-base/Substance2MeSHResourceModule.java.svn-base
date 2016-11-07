package org.ncibi.ws.resource.s2m;

import java.util.List;

import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.model.s2m.Substance2MeSHDBResult;
import org.restlet.resource.ServerResource;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.thoughtworks.xstream.XStream;

public class Substance2MeSHResourceModule extends AbstractModule
{
    private final XStream xstream;

    public Substance2MeSHResourceModule(XStream xstream)
    {
        super();
        this.xstream = xstream;
    }
    
    @Override
    protected void configure()
    {
        bind(ServerResource.class).to(Substance2MeSHResource.class);
        bind(new TypeLiteral<XmlEncoder<Response<List<Substance2MeSHDBResult>>>>()
        {
        }).to(NcibiXStreamSubstance2MeSHEncoder.class);
        bind(XStream.class).toInstance(xstream);
    }
}
