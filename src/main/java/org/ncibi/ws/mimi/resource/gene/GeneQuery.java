package org.ncibi.ws.mimi.resource.gene;

import java.util.ArrayList;
import java.util.List;

import org.ncibi.db.pubmed.gin.ClassifiedInteraction;
import org.ncibi.mimiweb.data.Compound;
import org.ncibi.mimiweb.data.Enzyme;
import org.ncibi.mimiweb.data.GeneInteractionList;
import org.ncibi.mimiweb.data.MetabReaction;
import org.ncibi.mimiweb.data.hibernate.DenormInteraction;
import org.ncibi.mimiweb.data.hibernate.DenormInteractionAttribute;
import org.ncibi.mimiweb.data.hibernate.DocumentBriefSimple;
import org.ncibi.mimiweb.data.hibernate.GenePathways;
import org.ncibi.mimiweb.hibernate.HibernateInterface;
import org.ncibi.mimiweb.hibernate.HumDBQueryInterface;
import org.ncibi.mimiweb.hibernate.PubmedDBQueryInterface;
import org.ncibi.ws.encoder.xstream.AbstractNcibiXStreamEncoder;
import org.ncibi.ws.mimi.encoder.MetabReactionConverter;
import org.ncibi.ws.resource.ClassifiedInteractionConverter;
import org.ncibi.ws.resource.CompoundConverter;
import org.ncibi.ws.resource.DenormInteractionAttributeConverter;
import org.ncibi.ws.resource.DenormInteractionConverter;
import org.ncibi.ws.resource.DocumentBriefSimpleConverter;
import org.ncibi.ws.resource.EnzymeConverter;
import org.ncibi.ws.resource.GeneInteractionListConverter;
import org.ncibi.ws.resource.GenePathwayConverter;
import org.ncibi.ws.resource.GeneResultConverter;

import com.thoughtworks.xstream.XStream;

public enum GeneQuery
{
    GENE_REACTIONS
    {
        public GeneResult<Enzyme> queryOnGeneid(Integer geneid)
        {
            Enzyme enzyme = HumDBQueryInterface.getInterface().getEnzymeForGeneId(geneid);
            return new GeneResult<Enzyme>(enzyme);
        }

        public AbstractNcibiXStreamEncoder<GeneResult<Enzyme>> newEncoder(XStream xstream)
        {
            return new AbstractNcibiXStreamEncoder<GeneResult<Enzyme>>(xstream)
            {
                @Override
                protected void setupResultsEncoder()
                {
                    xstream.registerConverter(new GeneResultConverter());
                    xstream.alias("Reaction", MetabReaction.class);
                    xstream.registerConverter(new EnzymeConverter());
                    xstream.registerConverter(new MetabReactionConverter(false));

                }
            };
        }
    },

    GENE_PATHWAYS
    {
        public GeneResult<ArrayList<GenePathways>> queryOnGeneid(Integer geneid)
        {
            ArrayList<GenePathways> genePathways = HibernateInterface.getInterface().getPathwayListForGeneId(
                    geneid);
            return new GeneResult<ArrayList<GenePathways>>(genePathways);
        }

        public AbstractNcibiXStreamEncoder<GeneResult<ArrayList<GenePathways>>> newEncoder(XStream xstream)
        {
            return new AbstractNcibiXStreamEncoder<GeneResult<ArrayList<GenePathways>>>(xstream)
            {
                @Override
                protected void setupResultsEncoder()
                {
                    xstream.registerConverter(new GeneResultConverter());
                    xstream.alias("Result", GenePathways.class);
                    xstream.registerConverter(new GenePathwayConverter());
                }
            };
        }
    },

    GENE_LITERATURE
    {
        public GeneResult<List<ClassifiedInteraction>> queryOnGeneid(Integer geneid)
        {
            List<ClassifiedInteraction> cinteractions = PubmedDBQueryInterface.getInterface()
                    .getNlpInteractionsForGeneId(geneid);
            return new GeneResult<List<ClassifiedInteraction>>(cinteractions);
        }

        public AbstractNcibiXStreamEncoder<GeneResult<List<ClassifiedInteraction>>> newEncoder(XStream xstream)
        {
            return new AbstractNcibiXStreamEncoder<GeneResult<List<ClassifiedInteraction>>>(xstream)
            {
                @Override
                protected void setupResultsEncoder()
                {
                    xstream.registerConverter(new GeneResultConverter());
                    xstream.alias("Result", ClassifiedInteraction.class);
                    xstream.registerConverter(new ClassifiedInteractionConverter());
                }
            };
        }
    },

    GENE_INTERACTIONS
    {
        public GeneResult<GeneInteractionList> queryOnGeneid(Integer geneid)
        {
            GeneInteractionList interactions = new GeneInteractionList(geneid);
            return new GeneResult<GeneInteractionList>(interactions);
        }

        public AbstractNcibiXStreamEncoder<GeneResult<GeneInteractionList>> newEncoder(XStream xstream)
        {
            return new AbstractNcibiXStreamEncoder<GeneResult<GeneInteractionList>>(xstream)
            {
                @Override
                protected void setupResultsEncoder()
                {
                    xstream.registerConverter(new GeneResultConverter());
                    xstream.alias("Result", DenormInteraction.class);
                    xstream.alias("InteractionAttribute", DenormInteractionAttribute.class);
                    xstream.registerConverter(new GeneInteractionListConverter());
                    xstream.registerConverter(new DenormInteractionConverter());
                    xstream.registerConverter(new DenormInteractionAttributeConverter());

                }
            };
        }
    },

    GENE_DOCS
    {
        public GeneResult<ArrayList<DocumentBriefSimple>> queryOnGeneid(Integer geneid)
        {
            ArrayList<DocumentBriefSimple> docs = HibernateInterface.getInterface().getDocumentsForGeneId(
                    geneid);
            return new GeneResult<ArrayList<DocumentBriefSimple>>(docs);
        }

        public AbstractNcibiXStreamEncoder<GeneResult<ArrayList<DocumentBriefSimple>>> newEncoder(
                XStream xstream)
        {
            return new AbstractNcibiXStreamEncoder<GeneResult<ArrayList<DocumentBriefSimple>>>(xstream)
            {
                @Override
                protected void setupResultsEncoder()
                {
                    xstream.registerConverter(new GeneResultConverter());
                    xstream.alias("Result", DocumentBriefSimple.class);
                    xstream.registerConverter(new DocumentBriefSimpleConverter());
                }
            };
        }
    },

    GENE_COMPOUNDS
    {
        public GeneResult<List<Compound>> queryOnGeneid(Integer geneid)
        {
            List<Compound> compounds = HumDBQueryInterface.getInterface().getCompoundsForGeneid(geneid);
            return new GeneResult<List<Compound>>(compounds);
        }

        public AbstractNcibiXStreamEncoder<GeneResult<List<Compound>>> newEncoder(XStream xstream)
        {
            return new AbstractNcibiXStreamEncoder<GeneResult<List<Compound>>>(xstream)
            {
                @Override
                protected void setupResultsEncoder()
                {
                    xstream.registerConverter(new GeneResultConverter());
                    xstream.alias("Result", Compound.class);
                    xstream.registerConverter(new CompoundConverter());
                }
            };
        }
    };

    @SuppressWarnings("unchecked")
    public abstract GeneResult queryOnGeneid(Integer geneid);

    @SuppressWarnings("unchecked")
    public abstract AbstractNcibiXStreamEncoder newEncoder(XStream xstream);
}
