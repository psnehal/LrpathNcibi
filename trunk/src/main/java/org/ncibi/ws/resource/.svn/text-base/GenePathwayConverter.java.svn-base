package org.ncibi.ws.resource;

import org.ncibi.mimiweb.data.hibernate.GenePathways;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class GenePathwayConverter implements Converter {

        @SuppressWarnings("unchecked")
		public boolean canConvert(Class aClass) {
                return aClass.equals(GenePathways.class);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        	GenePathways gp = (GenePathways) value;
	        writer.startNode("PathwayID");
	        	writer.setValue(gp.getId().toString());
	        writer.endNode();
        	writer.startNode("PathwayName");
	        	writer.setValue(gp.getPathname());
	        writer.endNode();
        	writer.startNode("PathwayDescription");
        		writer.setValue(gp.getDescription());
        	writer.endNode();
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                return null;
        }

}
