package org.ncibi.ws.mimi.resource.gene;

import org.apache.commons.lang.math.NumberUtils;
import org.ncibi.mimiweb.data.ResultGeneMolecule;
import org.ncibi.mimiweb.hibernate.HibernateInterface;
import org.ncibi.mimiweb.lucene.LuceneInterface;
import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.xstream.AbstractNcibiXStreamEncoder;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;

import com.thoughtworks.xstream.XStream;

@SuppressWarnings("unchecked")
public class GeneResource extends AbstractSimpleResponseServerResource<GeneResult, Integer>
{
    private XStream xstream = new XStream();
    private GeneQuery query;
    private Integer geneid;

    @Override
    protected GeneResult performResourceAction(Integer geneid)
    {
        return query.queryOnGeneid(geneid);
    }

    @Override
    protected String response2Xml(Response<GeneResult> response)
    {
        AbstractNcibiXStreamEncoder<GeneResult> encoder = query.newEncoder(xstream);
        addGeneDataToResponse(response);
        encoder.setResult(response);
        return encoder.toXml();
    }
    
    private void addGeneDataToResponse(Response<GeneResult> response)
    {
        LuceneInterface l = LuceneInterface.getInterface();
        HibernateInterface h = HibernateInterface.getInterface();
        
        try
        {
            ResultGeneMolecule gene = l.getGeneData(geneid);
            gene = h.extendSingleGene(gene);
            response.getResponseValue().setGene(gene);
        }
        catch (Exception e)
        {
            // Do nothing. Stupid required exception handling...
        }
    }

    @Override
    protected Integer getArgumentsToResource()
    {
        setupQueryType();
        geneid = NumberUtils.toInt(getAttribute("geneid"), -1);
        return geneid;
    }
    
    private void setupQueryType()
    {
        String queryType = getArgument("type");
        if (queryType.equals("interactions"))
        {
            query = GeneQuery.GENE_INTERACTIONS;
        }
        else if (queryType.equals("compounds"))
        {
           query = GeneQuery.GENE_COMPOUNDS;
        }
        else if (queryType.equals("nlp"))
        {
            query = GeneQuery.GENE_LITERATURE;
        }
        else if (queryType.equals("reactions"))
        {
            query = GeneQuery.GENE_REACTIONS;
        }
        else if (queryType.equals("pathways"))
        {
            query = GeneQuery.GENE_PATHWAYS;
        }
        else if (queryType.equals("docs"))
        {
            query = GeneQuery.GENE_DOCS;
        }
        else
        {
           query = GeneQuery.GENE_INTERACTIONS;
        }
        
    }

}
