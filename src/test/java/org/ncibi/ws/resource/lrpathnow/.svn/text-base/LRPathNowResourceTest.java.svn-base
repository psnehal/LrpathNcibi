package org.ncibi.ws.resource.lrpathnow;

import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.ncibi.lrpath.LRPath;
import org.ncibi.lrpath.LRPathArguments;
import org.ncibi.lrpath.LRPathRServer;
import org.ncibi.lrpath.LRPathResult;

@Ignore
public class LRPathNowResourceTest {

	@Test
	public void testPerformResourceAction() {
		String rServeAddress = "192.168.2.70";
		int rServePort = 6311;
		LRPathRServer rserver = new LRPathRServer(rServeAddress, rServePort);
		LRPath lrpath = new LRPath(rserver);
		LRPathArguments data = createLRPathArguments();
		List<LRPathResult> results = lrpath.runAnalysis(data);
		Assert.assertNotNull(results);
		Assert.assertTrue(results.isEmpty());
		// System.out.println(results);
	}

	private LRPathArguments createLRPathArguments() {
		LRPathArguments data = new LRPathArguments();
		int[] geneids = { 780, 5982, 3310 };
		double[] sigvals = { 0.004859222, 0.275428947, 0.940720196 };
		double[] direction = { 0.414580082, -0.176934427, 0.01006101 };
		data.setGeneids(geneids);
		data.setSigvals(sigvals);
		data.setDirection(direction);
		data.setMing(1);
		return data;
	}

}
