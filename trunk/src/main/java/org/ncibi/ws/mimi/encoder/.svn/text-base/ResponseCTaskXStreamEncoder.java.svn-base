package org.ncibi.ws.mimi.encoder;

import org.ncibi.task.CTask;
import org.ncibi.ws.encoder.xstream.AbstractNcibiXStreamEncoder;

import com.google.inject.Inject;
import com.thoughtworks.xstream.XStream;

public class ResponseCTaskXStreamEncoder extends AbstractNcibiXStreamEncoder<CTask>
{
    @Inject
    public ResponseCTaskXStreamEncoder(XStream xstream)
    {
        super(xstream);
    }
    
    @Override
    protected void setupResultsEncoder()
    {
        this.xstream.registerConverter(new TaskConverter());
    }
}
