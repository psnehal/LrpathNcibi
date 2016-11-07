package org.ncibi.ws.resource.cellularcomponent;

import java.util.List;

import org.ncibi.commons.collections.ListUtilities;
import org.ncibi.db.PersistenceSession;
import org.ncibi.hibernate.ColumnAnnotatedResultTransformer;
import org.ncibi.ws.Response;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;

import com.thoughtworks.xstream.XStream;

public class GeneCellularComponentResource extends
        AbstractSimpleResponseServerResource<List<GeneAttribute>, GenesArg>
{
    private final PersistenceSession persistence;
    private final XStream xstream = new XStream();

    public GeneCellularComponentResource(PersistenceSession persistence)
    {
        this.persistence = persistence;
    }

    @Override
    protected GenesArg getArgumentsToResource()
    {
        GenesArg arg = new GenesArg();

        String geneids = getArgument("geneids");

        for (int geneid : ListUtilities.csv2IntegersList(geneids))
        {
            arg.addGeneid(geneid);
        }

        return arg;
    }

    @Override
    protected List<GeneAttribute> performResourceAction(GenesArg args)
    {
        /*
         * select distinct geneid, attrType, attrValue from denorm.GeneAttribute
         * where geneid = 1537 and attrType in ('Function', 'Process',
         * 'Component')
         */

        String query = constructQueryFromArgs(args);
        List<GeneAttribute> attributes = persistence.sqlQuery(query)
                .to(new ColumnAnnotatedResultTransformer(GeneAttribute.class)).list();
        return attributes;
    }

    private String constructQueryFromArgs(GenesArg args)
    {
        String geneids = ListUtilities.createCommaJoinedStringFromIntegers(args.getGeneids());
        String query = "select distinct geneid, attrType, attrValue from denorm.GeneAttribute "
                + "where geneid in (" + geneids + ") and attrType in ('Function', 'Process', 'Component')";
        return query;
    }

    @Override
    protected String response2Xml(Response<List<GeneAttribute>> response)
    {
        return xstream.toXML(response.getResponseValue());
    }

}
