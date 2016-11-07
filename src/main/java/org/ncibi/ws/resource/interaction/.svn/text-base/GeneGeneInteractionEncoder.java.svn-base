package org.ncibi.ws.resource.interaction;

import java.util.ArrayList;

import org.ncibi.mimiweb.data.hibernate.DenormInteraction;
import org.ncibi.mimiweb.data.hibernate.DenormInteractionAttribute;
import org.ncibi.ws.encoder.xstream.AbstractNcibiXStreamEncoder;
import org.ncibi.ws.resource.DenormGeneGeneInteractionConverter;
import org.ncibi.ws.resource.DenormInteractionAttributeConverter;

import com.thoughtworks.xstream.XStream;

public class GeneGeneInteractionEncoder extends AbstractNcibiXStreamEncoder<ArrayList<DenormInteraction>> {

	public GeneGeneInteractionEncoder(XStream xstream) {
		super(xstream);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setupResultsEncoder() {
		//xstream.alias("Response", GeneResult.class);
		//xstream.alias("ResultSet", Response.class);
    	xstream.alias("Result", DenormInteraction.class);
    	//xstream.aliasField("ResultSet", GeneResult.class, "result");
		xstream.alias("InteractionAttribute", DenormInteractionAttribute.class);
    	//xstream.registerConverter(new GeneInteractionListConverter());
    	xstream.registerConverter(new DenormGeneGeneInteractionConverter());
    	xstream.registerConverter(new DenormInteractionAttributeConverter());
		
	}

}
