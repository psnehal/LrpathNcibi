package org.ncibi.ws.resource;

import org.ncibi.mimiweb.data.Enzyme;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class EnzymeConverter implements Converter {

        @SuppressWarnings("unchecked")
		public boolean canConvert(Class aClass) {
                return aClass.equals(Enzyme.class);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        	Enzyme enzyme = (Enzyme) value;
        	writer.startNode("Result");
	        	writer.startNode("Enzyme");
		        	writer.startNode("Name");
		        		writer.setValue(enzyme.getName());
		        	writer.endNode(); //name
		        	writer.startNode("ECNumber");
		        		writer.setValue(enzyme.getEc());
		        	writer.endNode();
		        	writer.startNode("Reactions");
		        		context.convertAnother(enzyme.getReactions());
		        	writer.endNode();
		        writer.endNode();
	        writer.endNode();
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                return null;
        }

}
