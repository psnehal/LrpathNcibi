package org.ncibi.ws.resource.g2m;

import java.util.List;

import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.model.g2m.Gene2MeSHDBResult;
import org.restlet.resource.ServerResource;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.thoughtworks.xstream.XStream;

public class Gene2MeSHResourceModule extends AbstractModule
{
    private final XStream xstream;

    public Gene2MeSHResourceModule(XStream xstream)
    {
        super();
        this.xstream = xstream;
    }
    
    @Override
    protected void configure()
    {
        bind(ServerResource.class).to(Gene2MeSHResource.class);
        bind(new TypeLiteral<XmlEncoder<Response<List<Gene2MeSHDBResult>>>>()
        {
        }).to(NcibiXStreamGene2MeSHEncoder.class);
        bind(XStream.class).toInstance(xstream);
    }
}
