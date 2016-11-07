package org.ncibi.ws.resource.lrpathnow;

import java.util.List;

import org.ncibi.lrpath.LRPath;
import org.ncibi.lrpath.LRPathArguments;
import org.ncibi.lrpath.LRPathResult;
import org.ncibi.ws.Response;
import org.ncibi.ws.resource.AbstractResourceHandler;

public class LRPathNowResourceHandler extends AbstractResourceHandler<List<LRPathResult>, LRPathArguments>
{
    private final LRPath lrpath;
    
    public LRPathNowResourceHandler(LRPath lrpath)
    {
        this.lrpath = lrpath;
    }
    
    @Override
    public Response<List<LRPathResult>> createResponse(LRPathArguments args)
    {
        List<LRPathResult> results = lrpath.runAnalysis(args);
        return this.createSuccessResponseWithValueAndArgs(results, args);
    }
}
