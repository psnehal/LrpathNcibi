package org.ncibi.ws.resource.interaction;

import java.util.ArrayList;

import org.ncibi.mimiweb.data.hibernate.DenormInteraction;
import org.ncibi.mimiweb.hibernate.HibernateInterface;
import org.ncibi.ws.Response;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;

import com.thoughtworks.xstream.XStream;

public class InteractionResource extends AbstractSimpleResponseServerResource<ArrayList<DenormInteraction>, GeneGeneInteractionResourceArgument>
{
	private XStream xstream = new XStream();
    private GeneGeneInteractionEncoder encoder = new GeneGeneInteractionEncoder(xstream);

    @Override
    protected ArrayList<DenormInteraction> performResourceAction(GeneGeneInteractionResourceArgument args)
    {
        HibernateInterface h = HibernateInterface.getInterface();
        ArrayList<DenormInteraction> interactions = h.getInteractionsByGeneId(args.getGeneid1(), args.getGeneid2(), null, -1);
        return interactions;
    }

    @Override
    protected String response2Xml(Response<ArrayList<DenormInteraction>> response)
    {
        encoder.setResult(response);
        return encoder.toXml();
    }

    @Override
    protected GeneGeneInteractionResourceArgument getArgumentsToResource()
    {
        GeneGeneInteractionResourceArgument args = new GeneGeneInteractionResourceArgument(getArgument("geneid1"), getArgument("geneid2"));
        return args;
    }
    
}
