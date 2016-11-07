package org.ncibi.ws.resource.s2m;

import java.util.List;

import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.model.s2m.Substance2MeSHDB;
import org.ncibi.ws.model.s2m.Substance2MeSHDBResult;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;

import com.google.inject.Inject;

public class Substance2MeSHResource extends
        AbstractSimpleResponseServerResource<List<Substance2MeSHDBResult>, Substance2MeSHResourceArgument>
{
    private final XmlEncoder<Response<List<Substance2MeSHDBResult>>> encoder;
    private final Substance2MeSHDB s2m = new Substance2MeSHDB();

    @Inject
    public Substance2MeSHResource(XmlEncoder<Response<List<Substance2MeSHDBResult>>> encoder)
    {
        this.encoder = encoder;
    }

    @Override
    protected List<Substance2MeSHDBResult> performResourceAction(Substance2MeSHResourceArgument args)
    {
        List<Substance2MeSHDBResult> result = null;

        if (args.getSearchTerm() != "")
        {
            result = s2m.getResultsBySubstanceOrDescriptor(args.getSearchTerm());
        }
        else if (args.getCid() != "")
        {
            result = s2m.getMeSHBySubstanceID(args.getCid());
        }
        else if (args.getPreferred() != "")
        {
            result = s2m.getMeSHBySubstance(args.getPreferred());
        }
        else if (args.getDescriptorid() != "")
        {
            result = s2m.getSubstancesByDescriptorID(args.getDescriptorid());
        }
        else if (args.getDescriptorSearchTerm() != "")
        {
            result = s2m.getSubstancesByMeSH(args.getDescriptorSearchTerm());
        }
        
        result = s2m.collapseResults(result);
        result = s2m.addPublications(result, Integer.parseInt(args.getPubLimit()));
        
        return result;
    }

    @Override
    protected String response2Xml(Response<List<Substance2MeSHDBResult>> response)
    {
        encoder.setResult(response);
        return encoder.toXml();
    }

    @Override
    protected Substance2MeSHResourceArgument getArgumentsToResource()
    {
        Substance2MeSHResourceArgument args = new Substance2MeSHResourceArgument();

        if (getArgument("publimit") != "")
        {
            args.setPubLimit(getArgument("publimit"));
        }

        if (getAttribute("preferred") != null)
        {
            args.setPreferred(getAttribute("preferred"));
        }
        else if (getAttribute("compoundid") != null)
        {
            args.setCid(getAttribute("compoundid"));
        }
        else if (getAttribute("searchTerm") != null)
        {
            args.setSearchTerm(getAttribute("searchTerm"));
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
