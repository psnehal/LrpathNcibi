package org.ncibi.ws.resource;

import java.util.Iterator;

import org.ncibi.mimiweb.data.MetabReaction;
import org.ncibi.mimiweb.data.StringItem;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class MetabReactionWithGenesConverter implements Converter {

        @SuppressWarnings("unchecked")
		public boolean canConvert(Class aClass) {
                return aClass.equals(MetabReaction.class);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        	MetabReaction reaction = (MetabReaction) value;

        	writer.startNode("Reaction");
	    		writer.startNode("Description");
	    			writer.setValue(reaction.getDescription());
	    		writer.endNode();
	    		writer.startNode("KEGGID");
	    			writer.setValue(reaction.getKeggid());
	    		writer.endNode();
	    		writer.startNode("Equation");
	    			writer.setValue(reaction.getEquation());
	    		writer.endNode();
	    		writer.startNode("ReactionText");
	    			writer.setValue(reaction.getRxntxt());
	    		writer.endNode();
	        	writer.startNode("CompoundIDs");
	        	Iterator<StringItem> iterator = reaction.getGenes().iterator();
	        	while(iterator.hasNext()) {
	        		writer.startNode("ID");
	        			writer.setValue((String) iterator.next().getId().toString());
	        		writer.endNode();
	        	}
	        	Iterator<String> iterator2 = reaction.getCompoundIds().iterator();
	        	while(iterator2.hasNext()) {
	        		writer.startNode("ID");
	        			writer.setValue((String) iterator2.next());
	        		writer.endNode();
	        	}
	        writer.endNode();

        }

        public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                return null;
        }

}
