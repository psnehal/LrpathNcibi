package org.ncibi.ws.resource.wsapi;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.ncibi.mimiweb.hibernate.HibernateInterface;

import com.thoughtworks.xstream.XStream;

public class ApiMap
{
    private static Map<String, ApiWrapper> apiMap = new HashMap<String, ApiWrapper>();
    private static XStream xstream = new XStream();
    
    static
    {
        apiMap.put("getSingleGene", new ApiWrapper()
        {
            @Override
            public String makeApiCall(WsApiArgs args)
            {
                int geneid = NumberUtils.toInt(args.getArg0(), -1);
                if (geneid == -1)
                {
                    return "ERROR: " + geneid;
                }
                else
                {
                    try
                    {
                        return xstream.toXML(HibernateInterface.getInterface().getSingleGene(geneid));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        return "ERROR exception";
                    }
                }
            }         
        });
        
        apiMap.put("getPathwayInfo", new ApiWrapper()
        {
            @Override
            public String makeApiCall(WsApiArgs args)
            {
                int geneid = NumberUtils.toInt(args.getArg0(), -1);
                if (geneid == -1)
                {
                    return "ERROR";
                }
                else
                {
                    try
                    {
                        return xstream.toXML(HibernateInterface.getInterface().getParticipatingPathways(geneid));
                    }
                    catch (Exception e)
                    {
                        return "ERROR";
                    }
                }
            }
            
        });
    }
    
    private ApiMap() {}
    
    public static ApiWrapper getWrapperForCall(String apiCall)
    {
        return apiMap.get(apiCall);
    }
}
