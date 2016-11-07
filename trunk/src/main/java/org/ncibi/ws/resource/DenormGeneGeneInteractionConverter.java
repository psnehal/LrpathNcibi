package org.ncibi.ws.resource;

import org.ncibi.mimiweb.data.hibernate.DenormInteraction;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class DenormGeneGeneInteractionConverter implements Converter {

        @SuppressWarnings("unchecked")
		public boolean canConvert(Class aClass) {
                return aClass.equals(DenormInteraction.class);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        	DenormInteraction interaction = (DenormInteraction) value;
        	//writer.startNode("Interaction");
	        	writer.startNode("InteractionID");
	        		writer.setValue(String.valueOf(interaction.getId()));
	        	writer.endNode();
	        	writer.startNode("GeneID1");
        		writer.addAttribute("type", "entrez");
        		writer.setValue(String.valueOf(interaction.getGeneid1()));
        	writer.endNode();
        	writer.startNode("GeneSymbol1");
    		writer.setValue(interaction.getSymbol1());
    	writer.endNode();
	        	writer.startNode("GeneID2");
	        		writer.addAttribute("type", "entrez");
	        		writer.setValue(String.valueOf(interaction.getGeneid2()));
	        	writer.endNode();
	        	writer.startNode("GeneSymbol2");
	        		writer.setValue(interaction.getSymbol2());
	        	writer.endNode();
	        	writer.startNode("TaxonomyID");
	        		writer.setValue(String.valueOf(interaction.getTaxid2()));
	        	writer.endNode();
	        	context.convertAnother(interaction.getAttributes());
	        //writer.endNode();
        	
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                return null;
        }

}
