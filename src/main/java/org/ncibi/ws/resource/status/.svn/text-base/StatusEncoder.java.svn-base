package org.ncibi.ws.resource.status;

import java.io.IOException;

import org.ncibi.commons.io.FileInputLineProcessor;
import org.ncibi.task.CTask;
import org.ncibi.task.TaskFileUtil;
import org.ncibi.task.TaskStatus;
import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.AbstractResponseEncoder;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class StatusEncoder extends AbstractResponseEncoder<CTask, CTask>
{
    private String uuid;

    public StatusEncoder()
    {
        super(CTask.class);
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    @Override
    protected String[] persistenceFields()
    {
        String[] fields = new String[] {
                "uuid", "status"
        };

        return fields;
    }

    @Override
    protected void setupAliases(XStream xstream)
    {
    }

    @Override
    protected void marshaller(CTask obj, HierarchicalStreamWriter writer, MarshallingContext context)
    {
    }

    @Override
    public String toXml(Response<CTask> response)
    {
        if (taskIsDone(response))
        {
            return readDoneFile(uuid);
        }
        else
        {
            return super.toXml(response);
        }
    }

    @Override
    public String toRpcXml(Response<CTask> response)
    {
        if (taskIsDone(response))
        {
            return readDoneFile(uuid);
        }
        else
        {
            return super.toRpcXml(response);
        }
    }

    @Override
    public String toJson(Response<CTask> response)
    {
        if (taskIsDone(response))
        {
            return readDoneFile(uuid);
        }
        else
        {
            return super.toJson(response);
        }
    }

    private boolean taskIsDone(Response<CTask> r)
    {
        return r.getResponseValue().getStatus() == TaskStatus.DONE;
    }

    private String readDoneFile(String uuid)
    {
        final StringBuilder sb = new StringBuilder(10000);
        FileInputLineProcessor processor = new FileInputLineProcessor()
        {
            @Override
            public void processLine(String line) throws IOException
            {
                sb.append(line + "\n");
            }
        };

        String filepath = TaskFileUtil.taskFile(uuid);
        processor.process(filepath);
        return sb.toString();
    }
}
