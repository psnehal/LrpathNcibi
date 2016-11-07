package org.ncibi.ws.mimi.encoder;

import org.ncibi.mimiweb.data.Compound;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CompoundConverter implements Converter
{

    @Override
    @SuppressWarnings("unchecked")
    public boolean canConvert(Class arg0)
    {
        return arg0.equals(Compound.class);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context)
    {
        Compound compound = (Compound) value;
        writer.startNode("Result");
        writer.startNode("Compound");
        
        writer.startNode("CompoundID");
        writer.setValue(compound.getCid());
        writer.endNode();
        
        writer.startNode("CompoundName");
        writer.setValue(compound.getName());
        writer.endNode();
        
        writer.startNode("MolecularFormula");
        writer.setValue(compound.getMf());
        writer.endNode();
        
        writer.startNode("MolecularWeight");
        writer.setValue(compound.getMolecularWeight().toString());
        writer.endNode();
        
        String casnum = compound.getCasnum();
        
        if (casnum != null)
        {
            writer.startNode("CASNumber");
            writer.setValue(compound.getCasnum());
            writer.endNode();
        }
        writer.endNode();
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader arg0, UnmarshallingContext arg1)
    {
        throw new UnsupportedOperationException("unmarshal call is not supported.");
    }
}
