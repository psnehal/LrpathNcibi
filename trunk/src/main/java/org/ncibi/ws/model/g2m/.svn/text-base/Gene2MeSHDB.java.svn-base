package org.ncibi.ws.model.g2m;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.ncibi.db.EntityManagers;
import org.ncibi.db.PersistenceSession;
import org.ncibi.db.PersistenceUnit;
import org.ncibi.hibernate.ColumnAnnotatedResultTransformer;
import org.ncibi.hibernate.SessionFunction;
import org.ncibi.hibernate.Sessions;

public class Gene2MeSHDB {

	static PersistenceSession p;
	
	public Gene2MeSHDB()
	{
		p = new PersistenceUnit(EntityManagers.newEntityManagerFromProject("gene2mesh"));
	}
    
    private List<Gene2MeSHDBResult> runGeneMeSHQuery(final String query)
    {
	    List<Gene2MeSHDBResult> results = Sessions.withSession(p.session(),
				new SessionFunction<List<Gene2MeSHDBResult>>(){
			@SuppressWarnings("unchecked")
			@Override
			public List<Gene2MeSHDBResult> apply(Session session) {
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
    
    private ArrayList<Integer> runG2MPubQuery(final String geneid, final String descriptorid)
    {
    	final String query = "exec pubmed_10n.g2m.getPMIDsForGeneIDAndDescriptorID " + geneid + "," + descriptorid;
    	ArrayList<Integer> results = Sessions.withSession(p.session(),
				new SessionFunction<ArrayList<Integer>>(){
			@SuppressWarnings("unchecked")
			@Override
			public ArrayList<Integer> apply(Session session) {
				ArrayList<Integer> results = (ArrayList<Integer>) session.createSQLQuery(query).addScalar("PMID", Hibernate.INTEGER).list();
				return results;
			}});
		return results;
	 }
    
    public List<Gene2MeSHDBResult> getGenesByMeSH(String searchTerm, String taxid)
    {
    	String query = "exec pubmed_10n.g2m.getGenesByMeSH '" + searchTerm + "'," + taxid + ",0,1000";
    	return runGeneMeSHQuery(query);
    }    
    public List<Gene2MeSHDBResult> getGenesByGeneID(String geneid)
    {
    	String query = "exec pubmed_10n.g2m.getGenesByGeneID '" + geneid + "',0,1000";
    	return runGeneMeSHQuery(query);
    }
    public List<Gene2MeSHDBResult> getGenesByDescriptorID(String descriptorid, String taxid)
    {
    	String query = "exec pubmed_10n.g2m.getGenesByDescriptorID " + descriptorid + "," + taxid + ",0,1000";
    	return runGeneMeSHQuery(query);
    }
    public List<Gene2MeSHDBResult> getGenesByDescOrSymbol(String searchTerm, String taxid)
    {
    	String query = "exec pubmed_10n.g2m.getGenesByDescOrSymbol '" + searchTerm + "'," + taxid + ",0,1000";
    	return runGeneMeSHQuery(query);
    }  
    public List<Gene2MeSHDBResult> getGenesBySymbol(String symbol, String taxid)
    {
    	String query = "exec pubmed_10n.g2m.getGenesBySymbol '" + symbol + "'," + taxid + ",0,1000";
    	return runGeneMeSHQuery(query);
    }   
    public List<Gene2MeSHDBResult> addPublications(List<Gene2MeSHDBResult> results, int pubLimit)
    {
    	Gene2MeSHDBResult current;
    	ArrayList<Integer> pubs;
    	Iterator<Gene2MeSHDBResult> i = results.iterator();
    	while (i.hasNext())
    	{
    		
    		current = i.next();
    		pubs = runG2MPubQuery(Integer.toString(current.getGeneid()),Integer.toString(current.getDescriptorID()));
    		if (pubLimit > 0 && pubLimit < pubs.size())
    			current.setPubs(new Gene2MeSHPubs(pubs.subList(0, pubLimit)));
    		else
    			current.setPubs(new Gene2MeSHPubs(pubs));	    		
    	}
    	return results;
    }
	
}
