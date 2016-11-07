package org.ncibi.ws.resource;

import java.util.Iterator;
import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class NCIBIWebServiceConverter implements Converter
{

    @SuppressWarnings("unchecked")
    public boolean canConvert(Class aClass)
    {
        return aClass.equals(NCIBIWebService.class);
    }

    @SuppressWarnings("unchecked")
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context)
    {
        NCIBIWebService nws = (NCIBIWebService) value;
        Object results = nws.getResults();
        writer.startNode(nws.getApplication());
        writer.startNode("Request");
        writer.addAttribute("type", "fetch");
        Iterator it = nws.getParameterSet().entrySet().iterator();
        writer.startNode("ParameterSet");
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            writer.startNode(pair.getKey().toString());
            writer.setValue(pair.getValue().toString());
            writer.endNode();
        }
        writer.endNode();
        writer.endNode(); // Request
        writer.startNode("Response");
        if (nws.getCurrentErrorCode() != null)
        {
            writer.startNode("Error");
            writer.startNode("Code");
            writer.setValue(nws.getCurrentErrorCode().toString());
            writer.endNode();
            writer.startNode("Message");
            writer.setValue(nws.getErrorMessage());
            writer.endNode();
            writer.endNode();
        } // if error
        else if (results != null)
        {
            writer.startNode("Copyright");
            writer.startNode("Statement");
            writer.setValue(nws.getCopyrightStatement());
            writer.endNode();
            writer.startNode("Year");
            writer.setValue(nws.getCopyrightYear());
            writer.endNode();
            writer.startNode("Details");
            writer.setValue(nws.getCopyrightDetails());
            writer.endNode();
            writer.endNode(); // Copyright
            writer.startNode("Support");
            writer.startNode("Statement");
            writer.setValue(nws.getSupportStatement());
            writer.endNode();
            writer.startNode("GrantNumber");
            writer.setValue(nws.getGrantNumber());
            writer.endNode();
            writer.startNode("Details");
            writer.setValue(nws.getSupportDetails());
            writer.endNode();
            writer.endNode(); // Support
            writer.startNode("ResultSet");
            if (nws.getResultTag())
            {
                writer.startNode("Result");
                context.convertAnother(results);
                writer.endNode();
            }
            else
                context.convertAnother(results);
            writer.endNode();// Results
        }
        else
        {
            writer.setValue("There were no records that matched your query.");
        }
        writer.endNode(); // Response
        writer.endNode(); // Application
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
    {
        return null;
    }

}
