package org.ncibi.ws.mimi.encoder;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLEncoder;

import org.ncibi.task.CTask;
import org.ncibi.ws.AbstractBeanXMLResponseEncoder;
import org.ncibi.ws.Response;

public class CTaskBeanXMLResponseEncoder extends AbstractBeanXMLResponseEncoder<CTask>
{
    public CTaskBeanXMLResponseEncoder(Response<CTask> response)
    {
        super(response);
    }

    @Override
    protected void setupPersistenceDelegatesForResponseValue(XMLEncoder encoder)
    {
        String[] persistenceFields = new String[] {
                "uuid", "status"
        };
        encoder.setPersistenceDelegate(CTask.class, new DefaultPersistenceDelegate(persistenceFields));
    }
}
