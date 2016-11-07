package org.ncibi.ws.resource;

import org.ncibi.mimiweb.data.ResultGeneMolecule;
import org.ncibi.ws.mimi.resource.gene.GeneResult;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class GeneResultConverter implements Converter {

        @SuppressWarnings("unchecked")
        @Override
		public boolean canConvert(Class aClass) {
                return aClass.equals(GeneResult.class);
        }

        @Override
        public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
            @SuppressWarnings("unchecked")
        	GeneResult gr = (GeneResult) value;
        	ResultGeneMolecule gene = gr.getGene();
        	writer.startNode("GeneDetails");
        		writer.startNode("Symbol");
        			writer.setValue(gene.getSymbol());
        		writer.endNode();
        		writer.startNode("Description");
        			writer.setValue(gene.getDescription());
        		writer.endNode();		
        		writer.startNode("OtherNames");
        		for(String name: gene.getMoleculeNames())
        		{
        			writer.startNode("Name");
        				writer.setValue(name);
        			writer.endNode(); 			
        		}
        		writer.endNode();
	        	writer.startNode("BiologicalProcesses");
	        	for (String proc: gene.getBiologicalProcesses())
	        	{
	        		writer.startNode("Process");
	        			writer.setValue(proc);
	        		writer.endNode();
	        	}
	        	writer.endNode();
	        	writer.startNode("CellularComponents");
	        	for (String comp: gene.getCellularComponents())
	        	{
	        		writer.startNode("Component");
	        			writer.setValue(comp);
	        		writer.endNode();
	        	}
        		writer.endNode();	 
        		writer.startNode("MolecularFunctions");
        		for (String func: gene.getMolecularFunctions())
	        	{
	        		writer.startNode("Function");
	        			writer.setValue(func);
	        		writer.endNode();
	        	}
        		writer.endNode();
	        writer.endNode();
        	context.convertAnother(gr.getResult());
        }

        @Override
        public Object unmarshal(HierarchicalStreamReader reader,
                        UnmarshallingContext context) {
                return null;
        }

}

