package org.ncibi.ws.mimi.resource.compound;

import junit.framework.Assert;

import org.junit.Test;
import org.ncibi.mimiweb.data.Compound;
import org.ncibi.mimiweb.hibernate.HumDBQueryInterface;
import org.ncibi.ws.Response;
import org.ncibi.ws.ResponseStatus;
import org.ncibi.ws.mimi.encoder.NcibiXStreamCompoundEncoder;

import com.thoughtworks.xstream.XStream;

public class CompoundResourceTest
{
    private final HumDBQueryInterface humdb = HumDBQueryInterface.getInterface();
    private final XStream xstream = new XStream();
    private final NcibiXStreamCompoundEncoder encoder = new NcibiXStreamCompoundEncoder(xstream);
    private final ResponseStatus rs = new ResponseStatus(null, true, "Success!");
   
    @Test
    public void testInvalidCompoundLookup()
    {
        Assert.assertNotNull(compoundLookupToXml("C0001"));
    }
    
    @Test
    public void testValidCompoundLookup()
    {
        Assert.assertNotNull(compoundLookupToXml("C00001"));
    }
    
    private String compoundLookupToXml(String cid)
    {
        Compound c = humdb.getCompoundDetailsForCid(cid);
        Response<Compound> r = new Response<Compound>(rs,c);
        encoder.setResult(r);
        return encoder.toXml();
    }
}
