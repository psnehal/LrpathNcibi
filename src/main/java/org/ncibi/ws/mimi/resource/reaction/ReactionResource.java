package org.ncibi.ws.mimi.resource.reaction;

import org.ncibi.mimiweb.data.MetabReaction;
import org.ncibi.mimiweb.hibernate.HumDBQueryInterface;
import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;

import com.google.inject.Inject;

public class ReactionResource extends AbstractSimpleResponseServerResource<MetabReaction, String>
{
    private final XmlEncoder<Response<MetabReaction>> encoder;

    @Inject
    public ReactionResource(XmlEncoder<Response<MetabReaction>> encoder)
    {
        this.encoder = encoder;
    }

    @Override
    public MetabReaction makeEmptyResponseValue()
    {
        return new MetabReaction();
    }

    @Override
    protected MetabReaction performResourceAction(String rid)
    {
        HumDBQueryInterface humdb = HumDBQueryInterface.getInterface();
        MetabReaction reaction = humdb.getReactionDetailsForRid(rid);
        return reaction;
    }

    @Override
    protected String response2Xml(Response<MetabReaction> response)
    {
        encoder.setResult(response);
        return encoder.toXml();
    }

    @Override
    protected String getArgumentsToResource()
    {
       return getAttribute("reactionid");
    }

}
