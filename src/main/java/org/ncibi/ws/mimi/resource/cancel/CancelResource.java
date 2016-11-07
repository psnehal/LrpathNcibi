package org.ncibi.ws.mimi.resource.cancel;

import org.hibernate.Session;
import org.ncibi.db.PersistenceSession;
import org.ncibi.db.ws.Task;
import org.ncibi.hibernate.SessionProcedure;
import org.ncibi.hibernate.Sessions;
import org.ncibi.task.CTask;
import org.ncibi.task.TaskStatus;
import org.ncibi.ws.Response;
import org.ncibi.ws.mimi.encoder.CTaskBeanXMLResponseEncoder;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource;

import com.google.inject.Inject;

public class CancelResource extends AbstractSimpleResponseServerResource<CTask, CancelArguments>
{
    private final PersistenceSession persistence;
    private String service;

    @Inject
    public CancelResource(PersistenceSession persistence)
    {
        this.persistence = persistence;
    }

    @Override
    protected CTask performResourceAction(CancelArguments args)
    {
        final Task task = persistence.hqlQuery("from ws.Task where uuid = '" + args.getUuid() + "'").single();
        if (task.getStatus() == TaskStatus.QUEUED)
        {
            task.setStatus(TaskStatus.CANCELED);
            Sessions.withSession(persistence.session(), new SessionProcedure()
            {
                @Override
                public void apply(Session session)
                {
                    session.refresh(task);
                    session.saveOrUpdate(task);
                }
            });
        }

        CTask cTask = new CTask(task.getUuid(), task.getStatus());
        
        return cTask;
    }

    @Override
    protected CancelArguments getArgumentsToResource()
    {
        CancelArguments args = new CancelArguments(getAttribute("uuid"));
        service = getAttribute("service");

        return args;
    }

    @Override
    protected String response2Xml(Response<CTask> response)
    {
        if ("javax".equals(service))
        {
            return encodeAsXMLBean(response);
        }
        else
        {
            return encodeAsNcibiXML(response);
        }
    }

    private String encodeAsXMLBean(Response<CTask> response)
    {
        CTaskBeanXMLResponseEncoder encoder = new CTaskBeanXMLResponseEncoder(response);
        return encoder.toXmlString();
    }

    private String encodeAsNcibiXML(Response<CTask> response)
    {
        // fill in later.
        return null;
    }
}
