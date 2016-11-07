package org.ncibi.ws.resource;

import org.ncibi.mimiweb.data.GeneInteractionList;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class GeneInteractionListConverter implements Converter {

        @SuppressWarnings("unchecked")
		public boolean canConvert(Class aClass) {
                return aClass.equals(GeneInteractionList.class);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        	GeneInteractionList geneList = (GeneInteractionList) value;
	        context.convertAnother(geneList.toArray());
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                return null;
        }

}

