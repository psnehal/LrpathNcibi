package org.ncibi.ws.resource.status;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.ncibi.lrpath.LRPathResult;
import org.ncibi.task.CTask;
import org.ncibi.task.TaskStatus;
import org.ncibi.ws.Response;
import org.ncibi.ws.ResponseStatus;
import org.ncibi.ws.encoder.status.LRPathRequestStatusResponseEncoder;
import org.ncibi.ws.request.RequestStatus;

@Ignore
public class LRPathRequestStatusResponseEncoderTest
{
    @Test
    public void testEncoder()
    {
        CTask ctask = new CTask("abc123", TaskStatus.QUEUED);

        RequestStatus<List<LRPathResult>> request = new RequestStatus<List<LRPathResult>>(ctask,
                null);
        ResponseStatus status = new ResponseStatus(null, true, "success");
        Response<RequestStatus<List<LRPathResult>>> r = new Response<RequestStatus<List<LRPathResult>>>(
                status, request);
        LRPathRequestStatusResponseEncoder encoder = new LRPathRequestStatusResponseEncoder(r);
        String xml = encoder.toXmlString();
        System.out.println(xml);
    }
}
