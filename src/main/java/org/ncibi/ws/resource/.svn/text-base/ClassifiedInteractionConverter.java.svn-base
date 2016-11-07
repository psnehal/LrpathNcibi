package org.ncibi.ws.resource;

import org.ncibi.db.pubmed.gin.ClassifiedInteraction;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ClassifiedInteractionConverter implements Converter {

        @SuppressWarnings("unchecked")
		public boolean canConvert(Class aClass) {
                return aClass.equals(ClassifiedInteraction.class);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        	ClassifiedInteraction interaction = (ClassifiedInteraction) value;
        	writer.startNode("NlpInteractingGene");
        		writer.startNode("GeneID");
        			writer.addAttribute("type", "entrez");
        			writer.setValue(interaction.getGeneID2().toString());
        		writer.endNode();
        		writer.startNode("Tag2");
        			writer.setValue(interaction.getTag2());
        		writer.endNode();
        		writer.startNode("TaxonomyID");
        			writer.setValue(interaction.getTaxid().toString());
        		writer.endNode();
        		writer.startNode("Sentence");
        			writer.setValue(interaction.getSentence());
        		writer.endNode();
        		writer.startNode("PubMed");
        			writer.setValue(interaction.getPmid().toString());
        		writer.endNode();
        	writer.endNode();
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                return null;
        }

}
