package org.ncibi.ws.resource.lrpath;

import java.beans.XMLEncoder;

import org.ncibi.ws.AbstractBeanXMLResponseEncoder;
import org.ncibi.ws.Response;

public class UuidResponseEncoder extends AbstractBeanXMLResponseEncoder<String>
{
    public UuidResponseEncoder(Response<String> response)
    {
        super(response);
    }
    
    @Override
    protected void setupPersistenceDelegatesForResponseValue(XMLEncoder encoder)
    { 
    }
}
