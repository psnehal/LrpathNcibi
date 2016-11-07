package org.ncibi.ws.resource.status;

import org.ncibi.db.PersistenceSession;
import org.ncibi.db.ws.Task;
import org.ncibi.task.CTask;
import org.ncibi.ws.resource.AbstractSimpleResponseServerResource2;

import com.google.inject.Inject;

public class StatusResource2 extends AbstractSimpleResponseServerResource2<CTask, StatusArguments>
{
    private final PersistenceSession persistence;
    private static final StatusEncoder encoder = new StatusEncoder();
    private String uuid;

    @Inject
    public StatusResource2(PersistenceSession persistence)
    {
        super(encoder);
        this.persistence = persistence;
    }
    
    @Override
    protected CTask performResourceAction(StatusArguments args)
    {
        Task task = persistence.hqlQuery("from ws.Task where uuid = '" + args.getUuid() + "'").single();
        CTask ctask = new CTask(task.getUuid(), task.getStatus());
        encoder.setUuid(args.getUuid());
        return ctask;
    }

    @Override
    protected StatusArguments getArgumentsToResource()
    {
        uuid = getAttribute("uuid");
        StatusArguments args = new StatusArguments(uuid);
        return args;
    }
}
