package org.ncibi.ws.resource.g2m;

import java.util.Iterator;

import org.ncibi.ws.model.g2m.Gene2MeSHDBResult;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class Gene2MeSHConverter implements Converter
{

    @Override
    @SuppressWarnings("unchecked")
    public boolean canConvert(Class arg0)
    {
        return arg0.equals(Gene2MeSHDBResult.class);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context)
    {
    	Gene2MeSHDBResult g2m = (Gene2MeSHDBResult) value;
    	writer.startNode("Gene");
    	writer.addAttribute("type", "Entrez");
    		writer.startNode("Identifier");
    			writer.setValue(Integer.toString(g2m.getGeneid()));
    		writer.endNode();
    		writer.startNode("Symbol");
				writer.setValue(g2m.getSymbol());
			writer.endNode();
    		writer.startNode("Description");
				writer.setValue(g2m.getDescription());
			writer.endNode();
			writer.startNode("Taxonomy");
				writer.startNode("Identifier");
					writer.setValue(Integer.toString(g2m.getTaxid()));
				writer.endNode();
			writer.endNode();
    	writer.endNode();
    	writer.startNode("MeSH");
    		writer.startNode("Descriptor");
    			writer.startNode("Name");
    				writer.setValue(g2m.getDname());
    			writer.endNode();
	    		writer.startNode("Identifier");
	    			writer.setValue(g2m.getUniqueID());
	    		writer.endNode();
    			writer.startNode("TreeNumber");
    			writer.endNode();
    		writer.endNode();
    		writer.startNode("Qualifier");
    			writer.startNode("Name");
    			if (g2m.getQname() != null)
    			{
    				writer.setValue(g2m.getQname());
    			}
    			writer.endNode();
    		writer.endNode();
    	writer.endNode();
    	writer.startNode("Fover");
    		writer.setValue(Double.toString(g2m.getFover()));
    	writer.endNode();
    	writer.startNode("ChiSquare");
    		writer.setValue(Double.toString(g2m.getChiSq()));
    	writer.endNode();
    	writer.startNode("FisherExact");
			writer.setValue(Double.toString(g2m.getFisherExact()));
		writer.endNode();
		writer.startNode("DocumentSet");
			writer.addAttribute("type", "Pubmed");
			Iterator<Integer> i = g2m.getPubs().getPMIDS().iterator();
			while (i.hasNext())
			{
				writer.startNode("PMID");
					writer.setValue(i.next().toString());
				writer.endNode();
			}
		writer.endNode();
    }
    @Override
    public Object unmarshal(HierarchicalStreamReader arg0, UnmarshallingContext arg1)
    {
        throw new UnsupportedOperationException("unmarshal call is not supported.");
    }
}
