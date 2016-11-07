package org.ncibi.ws.resource;

import org.ncibi.commons.lang.ArrayUtil;
import org.ncibi.lrpath.LRPathArguments;

public abstract class AbstractSimpleLRPathResponseServerResource<V> extends AbstractSimpleResponseServerResource<V, LRPathArguments>
{
	@Override
	protected LRPathArguments getArgumentsToResource()
	{
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
		data.setIdentifiers(ArrayUtil.toStringArray(getArgumentWithDefault("identifiers", "")));
		data.setRnaseq(getArgumentWithDefault("rnaseq", "yes"));
        data.setAvgread(getArgumentWithDefault("avgread", "yes"));
        data.setCustomfile(getArgumentWithDefault("uploadMappafile", ""));
        data.setOutname(getArgumentWithDefault("outname", "lrpath"));
		return data;
	}
}
