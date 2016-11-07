package org.ncibi.ws.resource.interaction;

import org.apache.commons.lang.math.NumberUtils;

public class GeneGeneInteractionResourceArgument
{
    private final int geneid1;
    private final int geneid2;
       
    public GeneGeneInteractionResourceArgument(String geneid1, String geneid2)
    {
        this.geneid1 = checkAndConvertID(geneid1);
        this.geneid2 = checkAndConvertID(geneid2);
    }
    
    public int getGeneid1()
    {
        return this.geneid1;
    }
    
    public int getGeneid2()
    {
        return this.geneid2;
    }
    
    private int checkAndConvertID(String id)
    {
        int idInt = NumberUtils.toInt(id, -1);
        if (idInt == -1)
        {
            throw new IllegalArgumentException("Invalid geneid: " + id);
        }
        return idInt;
    }
}
