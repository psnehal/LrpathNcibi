package org.ncibi.ws.resource.g2m;

import java.util.Iterator;
import java.util.List;

import org.ncibi.ws.encoder.AbstractResponseEncoder;
import org.ncibi.ws.model.g2m.Gene2MeSHDBResult;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class Gene2MeshEncoder extends AbstractResponseEncoder<Gene2MeSHDBResult, List<Gene2MeSHDBResult>>
{

    public Gene2MeshEncoder()
    {
        super(Gene2MeSHDBResult.class);
    }

    @Override
    protected void marshaller(Gene2MeSHDBResult obj, HierarchicalStreamWriter writer,
            MarshallingContext context)
    {
        Gene2MeSHDBResult g2m = (Gene2MeSHDBResult) obj;
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
    protected String[] persistenceFields()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void setupAliases(XStream xstream)
    {
        xstream.alias("Result", org.ncibi.ws.model.g2m.Gene2MeSHDBResult.class);
    }
}
