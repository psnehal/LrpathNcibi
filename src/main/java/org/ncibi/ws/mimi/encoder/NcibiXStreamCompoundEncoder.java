package org.ncibi.ws.mimi.encoder;

import org.ncibi.mimiweb.data.Compound;
import org.ncibi.ws.encoder.xstream.AbstractNcibiXStreamEncoder;

import com.google.inject.Inject;
import com.thoughtworks.xstream.XStream;

public class NcibiXStreamCompoundEncoder extends AbstractNcibiXStreamEncoder<Compound>
{
    @Inject
    public NcibiXStreamCompoundEncoder(XStream xstream)
    {
        super(xstream);
    }
    
    @Override
    protected void setupResultsEncoder()
    {
        this.xstream.registerConverter(new CompoundConverter());
    }
}
