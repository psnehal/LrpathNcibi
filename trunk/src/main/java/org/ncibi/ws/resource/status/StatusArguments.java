package org.ncibi.ws.resource.status;

public class StatusArguments
{
    private final String uuid;
    
    public StatusArguments(String uuid)
    {
        this.uuid = uuid;
    }
    
    public String getUuid()
    {
        return this.uuid;
    }
}
