package org.ncibi.ws.resource.nlp.parser.stanford;

import org.ncibi.db.ws.TaskType;
import org.ncibi.db.ws.SplitterArgs;
import org.ncibi.db.ws.Task;
import org.ncibi.mqueue.task.TaskQueuer;
import org.ncibi.task.logger.TaskLogger;
import org.ncibi.ws.Response;
import org.ncibi.ws.encoder.XmlEncoder;
import org.ncibi.ws.resource.nlp.AbstractNlpResource;

import com.google.inject.Inject;

public class StanfordParserResource extends AbstractNlpResource
{
    @Inject
    public StanfordParserResource(final TaskQueuer<SplitterArgs> taskQueuer,
                final TaskLogger taskLogger, final XmlEncoder<Response<Task>> encoder)
    {
        super(taskQueuer, taskLogger, encoder);
    }

    @Override
    protected TaskType nlpCommandType()
    {
        return TaskType.STANFORD_PARSER;
    } 
}
