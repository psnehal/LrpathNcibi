package org.ncibi.ws.resource.g2m;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;
import org.ncibi.db.EntityManagers;
import org.ncibi.db.PersistenceSession;
import org.ncibi.db.PersistenceUnit;
import org.ncibi.hibernate.ColumnAnnotatedResultTransformer;
import org.ncibi.hibernate.SessionFunction;
import org.ncibi.hibernate.Sessions;
import org.ncibi.ws.model.g2m.Gene2MeSHDBResult;

@Ignore
public class TestG2MDatabase {

	static PersistenceSession p;
	
	public TestG2MDatabase()
	{
		p = new PersistenceUnit(EntityManagers.newEntityManagerFromProject("gene2mesh"));
	}
    
    private List<Gene2MeSHDBResult> runQuery(final String query)
    {
	    List<Gene2MeSHDBResult> results = Sessions.withSession(p.session(),
				new SessionFunction<List<Gene2MeSHDBResult>>(){
			@Override
			public List<Gene2MeSHDBResult> apply(Session session) {
				@SuppressWarnings("unchecked")
				List<Gene2MeSHDBResult> results = session.createSQLQuery(query).addScalar("dname", Hibernate.STRING).addScalar("geneid", Hibernate.INTEGER)
				.addScalar("taxid", Hibernate.INTEGER).addScalar("descriptorID", Hibernate.INTEGER)
				.addScalar("descriptorID", Hibernate.INTEGER).addScalar("number", Hibernate.STRING)
				.addScalar("uniqueID", Hibernate.STRING).addScalar("Fover", Hibernate.DOUBLE)
				.addScalar("symbol", Hibernate.STRING)
				.addScalar("ChiSq", Hibernate.DOUBLE).addScalar("FisherExact", Hibernate.DOUBLE)
				.addScalar("description", Hibernate.STRING).addScalar("qname", Hibernate.STRING).setResultTransformer(new ColumnAnnotatedResultTransformer(Gene2MeSHDBResult.class)).list();
				return results;
			}});
		return results;
	    }
    
    @Test
    public void getGenesByMeSH()
    {
    	String query = "exec pubmed_10n.g2m.getGenesByMeSH 'diabetes',9606,0,1000";
    	System.out.println(runQuery(query).toString());
    }
	
}
