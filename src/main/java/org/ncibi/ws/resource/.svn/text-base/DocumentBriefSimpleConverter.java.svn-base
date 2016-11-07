package org.ncibi.ws.resource;

import org.ncibi.mimiweb.data.hibernate.DocumentBriefSimple;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class DocumentBriefSimpleConverter implements Converter {

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		DocumentBriefSimple doc = (DocumentBriefSimple) source;
		writer.startNode("Document");
			writer.startNode("PMID");
				writer.setValue(doc.getId().toString());
			writer.endNode();
			writer.startNode("Date");
				writer.setValue(doc.getDate());
			writer.endNode();
			writer.startNode("Citation");
				writer.setValue(doc.getCitation());
			writer.endNode();
			writer.startNode("Title");
				writer.setValue(doc.getTitle());
			writer.endNode();
			writer.startNode("AuthorList");
				writer.setValue(doc.getAuthors());
			writer.endNode();
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean canConvert(Class type) {
		return type.equals(DocumentBriefSimple.class);
	}

}
