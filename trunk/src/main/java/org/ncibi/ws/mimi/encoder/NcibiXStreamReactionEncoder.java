package org.ncibi.ws.mimi.encoder;

import org.ncibi.mimiweb.data.MetabReaction;
import org.ncibi.ws.encoder.xstream.AbstractNcibiXStreamEncoder;

import com.google.inject.Inject;
import com.thoughtworks.xstream.XStream;

public class NcibiXStreamReactionEncoder extends AbstractNcibiXStreamEncoder<MetabReaction>
{
    @Inject
    public NcibiXStreamReactionEncoder(XStream xstream)
    {
        super(xstream);
    }
    
    @Override
    protected void setupResultsEncoder()
    {
        this.xstream.registerConverter(new MetabReactionConverter(true));
    }
}
