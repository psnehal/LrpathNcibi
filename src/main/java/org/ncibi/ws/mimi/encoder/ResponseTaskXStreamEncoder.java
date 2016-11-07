package org.ncibi.ws.mimi.encoder;

import org.ncibi.db.ws.Task;
import org.ncibi.ws.encoder.xstream.AbstractNcibiXStreamEncoder;

import com.google.inject.Inject;
import com.thoughtworks.xstream.XStream;

public class ResponseTaskXStreamEncoder extends AbstractNcibiXStreamEncoder<Task>
{
    @Inject
    public ResponseTaskXStreamEncoder(XStream xstream)
    {
        super(xstream);
    }
    
    @Override
    protected void setupResultsEncoder()
    {
        this.xstream.registerConverter(new TaskConverter());
    }
}
