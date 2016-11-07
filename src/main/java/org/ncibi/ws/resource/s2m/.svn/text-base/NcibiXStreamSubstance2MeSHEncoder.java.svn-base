package org.ncibi.ws.resource.s2m;

import java.util.List;

import org.ncibi.ws.encoder.xstream.AbstractNcibiXStreamEncoder;
import org.ncibi.ws.model.s2m.Substance2MeSHDBResult;

import com.google.inject.Inject;
import com.thoughtworks.xstream.XStream;

public class NcibiXStreamSubstance2MeSHEncoder extends AbstractNcibiXStreamEncoder<List<Substance2MeSHDBResult>>
{
    @Inject
    public NcibiXStreamSubstance2MeSHEncoder(XStream xstream)
    {
        super(xstream);
    }
    
    @Override
    protected void setupResultsEncoder()
    {
    	xstream.alias("Result", org.ncibi.ws.model.s2m.Substance2MeSHDBResult.class);
        this.xstream.registerConverter(new Substance2MeSHConverter());
    }
}
