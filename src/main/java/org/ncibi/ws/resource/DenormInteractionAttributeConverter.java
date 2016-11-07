package org.ncibi.ws.resource;

import org.ncibi.mimiweb.data.hibernate.DenormInteractionAttribute;
import org.ncibi.mimiweb.data.hibernate.DenormInteractionAttributePk;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class DenormInteractionAttributeConverter implements Converter {

        @SuppressWarnings("unchecked")
        public boolean canConvert(Class aClass) {
        	return aClass.equals(DenormInteractionAttribute.class);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        	DenormInteractionAttribute attribute = (DenormInteractionAttribute) value;
        	DenormInteractionAttributePk id = attribute.getId();
        	writer.addAttribute("type", id.getAttrType());
        	writer.setValue(id.getAttrValue());
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                return null;
        }

}
