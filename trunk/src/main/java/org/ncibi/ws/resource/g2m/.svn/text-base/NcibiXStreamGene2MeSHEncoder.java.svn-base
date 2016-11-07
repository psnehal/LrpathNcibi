package org.ncibi.ws.resource.g2m;

import java.util.List;

import org.ncibi.ws.encoder.xstream.AbstractNcibiXStreamEncoder;
import org.ncibi.ws.model.g2m.Gene2MeSHDBResult;

import com.google.inject.Inject;
import com.thoughtworks.xstream.XStream;

public class NcibiXStreamGene2MeSHEncoder extends AbstractNcibiXStreamEncoder<List<Gene2MeSHDBResult>>
{
    @Inject
    public NcibiXStreamGene2MeSHEncoder(XStream xstream)
    {
        super(xstream);
    }
    
    @Override
    protected void setupResultsEncoder()
    {
    	xstream.alias("Result", org.ncibi.ws.model.g2m.Gene2MeSHDBResult.class);
        this.xstream.registerConverter(new Gene2MeSHConverter());
    }
}
