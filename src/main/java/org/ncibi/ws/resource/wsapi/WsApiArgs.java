package org.ncibi.ws.resource.wsapi;

public class WsApiArgs
{
    private String apiCall = null;
    private String arg0 = null;
    private String arg1 = null;
    private String arg2 = null;
    private String arg3 = null;
    private String arg4 = null;
    private String arg5 = null;
    private String arg6 = null;
    private String arg7 = null;
    private String arg8 = null;   
    private String arg9 = null;
    
    public String getApiCall()
    {
        return this.apiCall;
    }
    
    public void setApiCall(String apiCall)
    {
        this.apiCall = apiCall;
    }

    public String getArg0()
    {
        return getArgValue(arg0);
    }
    
    public String getArg0Name()
    {
        return getArgName(arg0, 0);
    }

    public void setArg0(String arg0)
    {
        this.arg0 = arg0;
    }

    public String getArg1()
    {
        return getArgValue(arg1);
    }
    
    public String getArg1Name()
    {
        return getArgName(arg1, 1);
    }

    public void setArg1(String arg1)
    {
        this.arg1 = arg1;
    }

    public String getArg2()
    {
        return getArgValue(arg2);
    }
    
    public String getArg2Name()
    {
        return getArgName(arg2, 2);
    }

    public void setArg2(String arg2)
    {
        this.arg2 = arg2;
    }

    public String getArg3()
    {
        return getArgValue(arg3);
    }
    
    public String getArg3Name()
    {
        return getArgName(arg3, 3);
    }

    public void setArg3(String arg3)
    {
        this.arg3 = arg3;
    }

    public String getArg4()
    {
        return getArgValue(arg4);
    }
    
    public String getArg4Name()
    {
        return getArgName(arg4, 4);
    }

    public void setArg4(String arg4)
    {
        this.arg4 = arg4;
    }

    public String getArg5()
    {
        return getArgValue(arg5);
    }
    
    public String getArg5Name()
    {
        return getArgName(arg5, 5);
    }

    public void setArg5(String arg5)
    {
        this.arg5 = arg5;
    }

    public String getArg6()
    {
        return getArgValue(arg6);
    }
    
    public String getArg6Name()
    {
        return getArgName(arg6, 6);
    }

    public void setArg6(String arg6)
    {
        this.arg6 = arg6;
    }

    public String getArg7()
    {
        return getArgValue(arg7);
    }
    
    public String getArg7Name()
    {
        return getArgName(arg7, 7);
    }

    public void setArg7(String arg7)
    {
        this.arg7 = arg7;
    }

    public String getArg8()
    {
        return getArgValue(arg8);
    }
    
    public String getArg8Name()
    {
        return getArgName(arg8, 8);
    }

    public void setArg8(String arg8)
    {
        this.arg8 = arg8;
    }

    public String getArg9()
    {
        return getArgValue(arg9);
    }
    
    public String getArg9Name()
    {
        return getArgName(arg9, 9);
    }

    public void setArg9(String arg9)
    {
        this.arg9 = arg9;
    }
    
    private String getArgName(String arg, int argPosition)
    {
        if (arg != null && arg.length() > 1)
        {
            if (arg.startsWith(":"))
            {
                int endingColonIndex = arg.indexOf(':', 1);
                return arg.substring(0, endingColonIndex);
            }
        }
        return "arg" + argPosition;
    }
    
    private String getArgValue(String arg)
    {
        if (arg != null && arg.length() > 1)
        {
            if (arg.startsWith(":"))
            {
                int endingColonIndex = arg.indexOf(':', 1);
                return arg.substring(endingColonIndex);
            }
        }
        return arg;
    }
}
