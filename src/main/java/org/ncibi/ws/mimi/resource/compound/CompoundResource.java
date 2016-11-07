package org.ncibi.ws.mimi.resource.compound;

import org.ncibi.mimiweb.data.Compound;
import org.ncibi.mimiweb.hibernate.HumDBQueryInterface;
import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;

import com.google.inject.Inject;

public class CompoundResource extends AbstractSimpleResponseServerResource<Compound, String>
{
    private final XmlEncoder<Response<Compound>> encoder;
    private final HumDBQueryInterface humdb = HumDBQueryInterface.getInterface();

    @Inject
    public CompoundResource(XmlEncoder<Response<Compound>> encoder)
    {
        this.encoder = encoder;
    }

    @Override
    protected Compound performResourceAction(String cid)
    {
        return humdb.getCompoundDetailsForCid(cid);
    }

    @Override
    protected String response2Xml(Response<Compound> response)
    {
        encoder.setResult(response);
        return encoder.toXml();
    }

    @Override
    protected String getArgumentsToResource()
    {
        return getAttribute("compoundid");
    }
}
