package org.ncibi.ws.resource;

import org.ncibi.mimiweb.data.Compound;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CompoundConverter implements Converter {

        @SuppressWarnings("unchecked")
		public boolean canConvert(Class aClass) {
                return aClass.equals(Compound.class);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        	Compound compound = (Compound) value;
        	String casnum = null;
        	casnum = compound.getCasnum();
        	writer.startNode("Compound");
	    		writer.startNode("CompoundID");
	    			writer.setValue(compound.getCid());
	    		writer.endNode();
	    		writer.startNode("CompoundName");
	    			writer.setValue(compound.getName());
	    		writer.endNode();
	    		writer.startNode("MolecularFormula");
	    			writer.setValue(compound.getMf());
	    		writer.endNode();
	    		writer.startNode("MolecularWeight");
	    			writer.setValue(compound.getMolecularWeight().toString());
	    		writer.endNode();
	    		if (casnum != null) {
		    		writer.startNode("CASNumber");
		    			writer.setValue(compound.getCasnum());
		    		writer.endNode();
	    		}
    		writer.endNode();
        }

        public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                return null;
        }

}
