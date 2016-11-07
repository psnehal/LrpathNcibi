package org.ncibi.ws.mimi.encoder;

import org.ncibi.db.ws.Task;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class TaskConverter implements Converter
{
    @SuppressWarnings("unchecked")
    @Override
    public boolean canConvert(Class cls)
    {
        return Task.class.equals(cls);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context)
    {
        Task task = (Task) value;
        writer.startNode("Task");
        
        writer.startNode("uuid");
        writer.setValue(task.getUuid());
        writer.endNode();
        
        writer.startNode("status");
        writer.setValue(task.getStatus().toString());
        writer.endNode();
        
        writer.startNode("CommandType");
        writer.setValue(task.getTaskType().toString());
        writer.endNode();
        
        writer.endNode(); // "Task" startNode
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
    {
  
        return null;
    }

}
