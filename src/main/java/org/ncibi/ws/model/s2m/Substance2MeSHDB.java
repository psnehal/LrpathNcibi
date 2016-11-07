package org.ncibi.ws.model.s2m;

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

public class Substance2MeSHDB {

	static PersistenceSession p;
	
	public Substance2MeSHDB()
	{
		p = new PersistenceUnit(EntityManagers.newEntityManagerFromProject("substance2mesh"));
	}
    
    private List<Substance2MeSHDBResult> runQuery(final String query)
    {
    	System.out.println("s2m query: " + query);
	    List<Substance2MeSHDBResult> results = Sessions.withSession(p.session(),
				new SessionFunction<List<Substance2MeSHDBResult>>(){
			@SuppressWarnings("unchecked")
			@Override
			public List<Substance2MeSHDBResult> apply(Session session) {
				List<Substance2MeSHDBResult> results = session.createSQLQuery(query).addScalar("cname", Hibernate.STRING).addScalar("CID", Hibernate.INTEGER)
				.addScalar("nPMID", Hibernate.INTEGER)
				.addScalar("descriptorID", Hibernate.INTEGER).addScalar("dname", Hibernate.STRING)
				.addScalar("uniqueID", Hibernate.STRING).addScalar("Fover", Hibernate.DOUBLE)
				.addScalar("preferred", Hibernate.STRING)
				.addScalar("substanceID", Hibernate.INTEGER)
				.addScalar("ChiSq", Hibernate.DOUBLE).addScalar("FisherExact", Hibernate.DOUBLE)
				.addScalar("topLevelHeading", Hibernate.STRING).setResultTransformer(new ColumnAnnotatedResultTransformer(Substance2MeSHDBResult.class)).list();
				return results;
			}});
		return results;
	}
    
    private ArrayList<Integer> runS2MPubQuery(final String substanceid, final String descriptorid)
    {
    	final String query = "exec pubmed_10n.s2m.getPMIDsForSubstanceIDAndDescriptorID " + substanceid + "," + descriptorid;
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
    
    public List<Substance2MeSHDBResult> getSubstancesByMeSH(String searchTerm)
    {
    	String query = "exec s2m.getSubstancesByMeSH '" + searchTerm + "',1000";
    	return runQuery(query);
    }
    
    public List<Substance2MeSHDBResult> getMeSHBySubstance(String searchTerm)
    {
    	String query = "exec s2m.getMeSHBySubstance '" + searchTerm + "',1000";
    	return runQuery(query);
    }
    
    public List<Substance2MeSHDBResult> getSubstancesByDescriptorID(String searchTerm)
    {
    	String query = "exec s2m.getSubstancesByDescriptorID " + searchTerm + ",1000";
    	return runQuery(query);
    }
    
    public List<Substance2MeSHDBResult> getResultsBySubstanceOrDescriptor(String searchTerm)
    {
    	String query = "exec s2m.getResultsBySubstanceOrDescriptor '" + searchTerm + "',1000";
    	return runQuery(query);
    }
    
    public List<Substance2MeSHDBResult> getMeSHBySubstanceID(String searchTerm)
    {
    	String query = "exec s2m.getMeSHBySubstanceID " + searchTerm + ",1000";
    	return runQuery(query);
    }
	
	public List<Substance2MeSHDBResult> collapseResults(List<Substance2MeSHDBResult> results)
	{
		List<Substance2MeSHDBResult> cResults = new ArrayList<Substance2MeSHDBResult>();
		Iterator<Substance2MeSHDBResult> iterator = results.iterator();
		Boolean firstRow = true;
		Substance2MeSHDBResult thisRow = null;
		Substance2MeSHDBResult lastRow = null;
		List<Integer> cidList = new ArrayList<Integer>();
		
		while (iterator.hasNext())
		{
			thisRow = iterator.next();
			if (firstRow)
			{
				cidList.add(thisRow.getCID());
				lastRow = thisRow;
				firstRow = false;
			}
			else if (lastRow.getUniqueID().equals(thisRow.getUniqueID()) && lastRow.getPreferred().equals(thisRow.getPreferred()))
			{
				cidList.add(thisRow.getCID());
				lastRow = thisRow;
			}
			else
			{
				lastRow.setCIDs(cidList);
				cResults.add(lastRow);
				cidList = new ArrayList<Integer>();
				cidList.add(thisRow.getCID());
				lastRow = thisRow;
			}
			
		}
		lastRow.setCIDs(cidList);
		cResults.add(lastRow);
		cidList = new ArrayList<Integer>();
		cidList.add(thisRow.getCID());
		return cResults;
		
	}
	
    public List<Substance2MeSHDBResult> addPublications(List<Substance2MeSHDBResult> results, int pubLimit)
    {
    	Substance2MeSHDBResult current;
    	ArrayList<Integer> pubs;
    	Iterator<Substance2MeSHDBResult> i = results.iterator();
    	while (i.hasNext())
    	{
    		current = i.next();
    		pubs = runS2MPubQuery(Integer.toString(current.getSubstanceID()),Integer.toString(current.getDescriptorID()));
    		if (pubLimit > 0 && pubLimit < pubs.size())
    			current.setPubs(new Substance2MeSHPubs(pubs.subList(0, pubLimit)));	    		
    		else
    			current.setPubs(new Substance2MeSHPubs(pubs));	    		
    	}
    	return results;
    }
	
}
