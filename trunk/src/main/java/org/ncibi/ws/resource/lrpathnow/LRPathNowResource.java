package org.ncibi.ws.resource.lrpathnow;

import java.beans.XMLEncoder;
import java.util.List;

import org.ncibi.commons.lang.ArrayUtil;
import org.ncibi.lrpath.LRPath;
import org.ncibi.lrpath.LRPathArguments;
import org.ncibi.lrpath.LRPathRServer;
import org.ncibi.lrpath.LRPathResult;
import org.ncibi.ws.AbstractBeanXMLResponseEncoder;
import org.ncibi.ws.Response;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;

import com.google.inject.Inject;

public class LRPathNowResource extends AbstractSimpleResponseServerResource<List<LRPathResult>, LRPathArguments>
{
    private final LRPath lrpath;

    @Inject
    public LRPathNowResource(final LRPathRServer rserver)
    {
        this.lrpath = new LRPath(rserver);
    }

    @Override
    protected List<LRPathResult> performResourceAction(LRPathArguments args)
    {
        return lrpath.runAnalysis(args);
    }

    @Override
    protected String response2Xml(Response<List<LRPathResult>> response)
    {
        return new AbstractBeanXMLResponseEncoder<List<LRPathResult>>(response)
        {
            @Override
            protected void setupPersistenceDelegatesForResponseValue(XMLEncoder encoder)
            {
                //
            }
        }.toXmlString();
    }

	@Override
	protected LRPathArguments getArgumentsToResource() {
		LRPathArguments data = new LRPathArguments();
        data.setDatabase(getArgumentWithDefault("database", "KEGG"));
        data.setDirection(ArrayUtil.toDoubleArray(getArgumentWithDefault("direction", "")));
        data.setGeneids(ArrayUtil.toIntArray(getArgument("geneids")));
        data.setMaxg(getArgumentWithDefault("maxg", 99999));
        data.setMing(getArgumentWithDefault("ming", 1));
        data.setOddsmax(getArgumentWithDefault("oddsmax", 0.5));
        data.setOddsmin(getArgumentWithDefault("oddsmin", 0.001));
        data.setSigcutoff(getArgumentWithDefault("sigcutoff", 0.05));
        data.setSigvals(ArrayUtil.toDoubleArray(getArgument("sigvals")));
        data.setReadcount(ArrayUtil.toDoubleArray(getArgumentWithDefault("readcount", "")));
        data.setSpecies(getArgumentWithDefault("species", "hsa"));
        data.setApplication(getArgumentWithDefault("standalone", "application"));
    	data.setRnaseq(getArgumentWithDefault("rnaseq", "yes"));
        data.setAvgread(getArgumentWithDefault("avgread", "yes"));
        data.setCustomfile(getArgumentWithDefault("uploadMappafile", ""));
        data.setOutname(getArgumentWithDefault("outname", "lrpath"));
        
        return data;
	}
	
}
