package org.ncibi.ws.mimi.encoder;

import java.util.Iterator;

import org.ncibi.mimiweb.data.MetabReaction;
import org.ncibi.mimiweb.data.StringItem;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class MetabReactionConverter implements Converter {
	
		Boolean withGenes;
		Boolean withTag = false;
		
        public Boolean getWithGenes() {
			return withGenes;
		}

		public void setWithGenes(Boolean withGenes) {
			this.withGenes = withGenes;
		}

		public MetabReactionConverter(Boolean withGenes) {
			super();
			this.withGenes = withGenes;
		}

		@SuppressWarnings("unchecked")
		public boolean canConvert(Class aClass) {
                return aClass.equals(MetabReaction.class);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        	MetabReaction reaction = (MetabReaction) value;
        	if (withTag)
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
        	if (this.getWithGenes()) {
            	writer.startNode("Genes");
	        		Iterator<StringItem> iterator = reaction.getGenes().iterator();
		        	while(iterator.hasNext()) {
		        		writer.startNode("ID");
		        			writer.setValue((String) iterator.next().getId().toString());
		        		writer.endNode();
		        		writer.startNode("Symbol");
	        				writer.setValue((String) iterator.next().getStr());
	        			writer.endNode(); 		
		        	}
	        	writer.endNode();
        	}
        	writer.startNode("CompoundIDs");
		        Iterator<String> iterator = reaction.getCompoundIds().iterator();
		        while(iterator.hasNext()) {
		        	writer.startNode("ID");
		        		writer.setValue((String) iterator.next());
		        	writer.endNode();
		        }
		    writer.endNode();
        	if (withTag)
        		writer.endNode();
        }
        public Boolean getWithTag() {
			return withTag;
		}

		public void setWithTag(Boolean withTag) {
			this.withTag = withTag;
		}

		public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                return null;
        }

}
