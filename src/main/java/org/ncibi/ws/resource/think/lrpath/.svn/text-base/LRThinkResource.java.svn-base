package org.ncibi.ws.resource.think.lrpath;

import java.util.ArrayList;
import java.util.List;

import org.ncibi.commons.lang.ArrayUtil;
import org.ncibi.db.ws.Task;
import org.ncibi.db.ws.TaskType;
import org.ncibi.lrpath.LRPathArguments;
import org.ncibi.mqueue.task.TaskQueuer;
import org.ncibi.mqueue.task.TaskUtil;
import org.ncibi.task.logger.TaskLogger;
import org.ncibi.ws.Response;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;
import org.ncibi.ws.resource.lrpath.UuidResponseEncoder;
import org.ncibi.ws.thinkback.LRThinkArgs;
import org.ncibi.ws.thinkback.ThinkbackAdjustmentMethod;

import com.google.inject.Inject;

public class LRThinkResource extends AbstractSimpleResponseServerResource<Task, LRThinkArgs>
{

    private final TaskQueuer<LRThinkArgs> taskQueuer;
    private final TaskLogger taskLogger;
    
    @Inject
    public LRThinkResource(TaskQueuer<LRThinkArgs> taskQueuer, TaskLogger taskLogger)
    {
        this.taskQueuer = taskQueuer;
        this.taskLogger = taskLogger;
    }

    @Override
    protected Task performResourceAction(LRThinkArgs args)
    {
        Task task = TaskUtil.newQueuedTask(TaskType.LRPATH_THINK);
        // args.setUuid(task.getUuid());
        taskQueuer.queue(task, args);
        if (task != null)
        {
            String address = getRequest().getClientInfo().getAddress();
            taskLogger.logMessage(task, "Received request " + task.getTaskType() + " from " + address);
        }
        return task;
    }

	@Override
	protected String response2Xml(Response<Task> response) {

        Task t = response.getResponseValue();
        Response<String> r = new Response<String>(response.getResponseStatus(), t == null ? null
                : t.getUuid());
        UuidResponseEncoder encoder = new UuidResponseEncoder(r);
        String xml = encoder.toXmlString();
        return xml;
	}

	@Override
	protected LRThinkArgs getArgumentsToResource() {

		LRThinkArgs thinkData = new LRThinkArgs();
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
		data.setSpecies(getArgumentWithDefault("species", "hsa"));
		data.setApplication(getArgumentWithDefault("standalone", "application"));
		data.setIdentifiers(ArrayUtil.toStringArray(getArgumentWithDefault("identifiers", "")));
		
		thinkData.setLrpathArgs(data);

		String adjVal = getArgument("adjustmentMethod");
		if(!adjVal.equals(ThinkbackAdjustmentMethod.DS.toString()) && !adjVal.equals(ThinkbackAdjustmentMethod.AF.toString()))
			throw new IllegalArgumentException("Invalid argument for adjustmentMethod: '" + adjVal + "'");
		
		thinkData.setAdjustmentMethod(ThinkbackAdjustmentMethod.valueOf(adjVal));
		
		List<String> pathways = new ArrayList<String>();
		for (String p : ArrayUtil.toStringArray(getArgument("pathways")))
		{
		    pathways.add(p);
		}
		thinkData.setPathways(pathways);
		
		
		return thinkData;
	}

}
