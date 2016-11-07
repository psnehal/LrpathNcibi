package org.ncibi.ws.resource.s2m;


import java.util.ArrayList;
import java.util.Iterator;

import org.ncibi.ws.model.s2m.Substance2MeSHDBResult;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class Substance2MeSHConverter implements Converter {

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
		Substance2MeSHDBResult s2m = (Substance2MeSHDBResult) value;
		writer.startNode("Substance");
			writer.startNode("CompoundName");
				writer.setValue(s2m.getPreferred());
			writer.endNode();
			writer.startNode("CompoundIDs");
				writer.addAttribute("type", "Pubchem");
				ArrayList<Integer> cids = (ArrayList<Integer>) s2m.getCIDs();
				Iterator<Integer> iterator = cids.iterator();
				while (iterator.hasNext())
				{
					writer.startNode("ID");
					writer.setValue(iterator.next().toString());
					writer.endNode();
				}
			writer.endNode();
		writer.endNode();
		writer.startNode("MeSH");
			writer.startNode("Heading");
				writer.setValue(s2m.getTopLevelHeading());
			writer.endNode();
			writer.startNode("Descriptor");
				writer.startNode("Name");
					writer.setValue(s2m.getDname());
				writer.endNode();
				writer.startNode("Identifier");
					writer.setValue(s2m.getUniqueID());
				writer.endNode();	
			writer.endNode();
		writer.endNode();
		writer.startNode("Fover");
			writer.setValue(Double.toString(s2m.getFover()));
		writer.endNode();
		writer.startNode("ChiSquare");
			writer.setValue(Double.toString(s2m.getChiSq()));
		writer.endNode();
		writer.startNode("DocumentSet");
			writer.addAttribute("type", "Pubmed");
		Iterator<Integer> i = s2m.getPubs().getPMIDS().iterator();
		while (i.hasNext())
		{
			writer.startNode("PMID");
				writer.setValue(i.next().toString());
			writer.endNode();
		}
	writer.endNode();
		

	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		throw new UnsupportedOperationException("unmarshal call is not supported.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean canConvert(Class type) {
		// TODO Auto-generated method stub
		   return type.equals(Substance2MeSHDBResult.class);
	}

}
