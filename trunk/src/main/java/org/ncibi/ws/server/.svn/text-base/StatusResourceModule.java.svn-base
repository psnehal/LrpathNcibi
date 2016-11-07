package org.ncibi.ws.server;

import org.ncibi.db.PersistenceSession;
import org.ncibi.task.CTask;
import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.mimi.encoder.ResponseCTaskXStreamEncoder;
import org.ncibi.ws.resource.status.StatusResource;
import org.restlet.resource.ServerResource;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.thoughtworks.xstream.XStream;

public class StatusResourceModule extends AbstractModule
{
    private final PersistenceSession persistence;
    private final XStream xstream;
    
    public StatusResourceModule(PersistenceSession persistence, XStream xstream)
    {
        this.persistence = persistence;
        this.xstream = xstream;
    }
    
    @Override
    protected void configure()
    {
        bind(ServerResource.class).to(StatusResource.class);
        bind(PersistenceSession.class).toInstance(persistence);
        bind(new TypeLiteral<XmlEncoder<Response<CTask>>>(){}).to(ResponseCTaskXStreamEncoder.class);
        bind(XStream.class).toInstance(xstream);
    }
}
