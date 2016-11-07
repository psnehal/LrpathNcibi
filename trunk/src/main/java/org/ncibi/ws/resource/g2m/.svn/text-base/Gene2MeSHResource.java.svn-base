package org.ncibi.ws.resource.g2m;

import java.util.List;

import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.model.g2m.Gene2MeSHDB;
import org.ncibi.ws.model.g2m.Gene2MeSHDBResult;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;

import com.google.inject.Inject;

public class Gene2MeSHResource extends
        AbstractSimpleResponseServerResource<List<Gene2MeSHDBResult>, Gene2MeSHResourceArgument>
{
    private final XmlEncoder<Response<List<Gene2MeSHDBResult>>> encoder;
    private final Gene2MeSHDB g2m = new Gene2MeSHDB();

    @Inject
    public Gene2MeSHResource(XmlEncoder<Response<List<Gene2MeSHDBResult>>> encoder)
    {
        this.encoder = encoder;
    }

    @Override
    protected List<Gene2MeSHDBResult> performResourceAction(Gene2MeSHResourceArgument args)
    {
        List<Gene2MeSHDBResult> result = null;

        if (args.getSearchTerm() != "")
        {
            result = g2m.getGenesByDescOrSymbol(args.getSearchTerm(), args.getTaxid());
        }
        else if (args.getGeneid() != "")
        {
            result = g2m.getGenesByGeneID(args.getGeneid());
        }
        else if (args.getDescriptorid() != "")
        {
            result = g2m.getGenesByDescriptorID(args.getDescriptorid(), args.getTaxid());
        }
        else if (args.getSymbol() != "")
        {
            result = g2m.getGenesBySymbol(args.getSymbol(), args.getTaxid());
        }
        else if (args.getDescriptorSearchTerm() != "")
        {
            result = g2m.getGenesByMeSH(args.getDescriptorSearchTerm(), args.getTaxid());
        }

        if (result != null)
        {
            result = g2m.addPublications(result, Integer.parseInt(args.getPubLimit()));
        }

        return result;
    }

    @Override
    protected String response2Xml(Response<List<Gene2MeSHDBResult>> response)
    {
        encoder.setResult(response);
        return encoder.toXml();
    }

    @Override
    protected Gene2MeSHResourceArgument getArgumentsToResource()
    {
        Gene2MeSHResourceArgument args = new Gene2MeSHResourceArgument();

        if (getArgument("taxid") != "")
        {
            args.setTaxid(getArgument("taxid"));
        }

        if (getArgument("publimit") != "")
        {
            args.setPubLimit(getArgument("publimit"));
        }

        if (getAttribute("geneid") != null)
        {
            args.setGeneid(getAttribute("geneid"));
        }

        if (getAttribute("symbol") != null)
        {
            args.setSymbol(getAttribute("symbol"));
        }
        else if (getAttribute("descriptorid") != null)
        {
            args.setDescriptorid(getAttribute("descriptorid"));
        }
        else if (getAttribute("descriptorSearchTerm") != null)
        {
            args.setDescriptorSearchTerm(getAttribute("descriptorSearchTerm"));
        }

        return args;
    }

}
