package org.ncibi.ws.resource.wsapi;

import org.ncibi.ws.Response;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;

public class WsApiResource extends AbstractSimpleResponseServerResource<String, WsApiArgs>
{

    @Override
    protected String performResourceAction(WsApiArgs args)
    {
        ApiWrapper wrapper = ApiMap.getWrapperForCall(args.getApiCall());
        if (wrapper != null)
        {
            return wrapper.makeApiCall(args);
        }
        else
        {
            return "Unknown call: " + args.getApiCall();
        }
    }

    @Override
    protected String response2Xml(Response<String> response)
    {
        String xml = response.getResponseValue();
        return xml;
    }

    @Override
    protected WsApiArgs getArgumentsToResource()
    {
        WsApiArgs args = new WsApiArgs();
        args.setApiCall(getAttribute("apicall"));
        args.setArg0(getArgument("arg0"));
        args.setArg1(getArgument("arg1"));
        args.setArg2(getArgument("arg2"));
        args.setArg3(getArgument("arg3"));
        args.setArg4(getArgument("arg4"));
        args.setArg5(getArgument("arg5"));
        args.setArg6(getArgument("arg6"));
        args.setArg7(getArgument("arg7"));
        args.setArg8(getArgument("arg8"));
        args.setArg9(getArgument("arg9"));
        return args;
    }

}
