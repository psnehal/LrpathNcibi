package org.ncibi.ws.resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.queryParser.ParseException;
import org.ncibi.mimiweb.data.Compound;
import org.ncibi.mimiweb.data.Enzyme;
import org.ncibi.mimiweb.data.GeneInteractionList;
import org.ncibi.mimiweb.data.MetabReaction;
import org.ncibi.mimiweb.data.ResultGeneMolecule;
import org.ncibi.mimiweb.data.hibernate.DenormInteraction;
import org.ncibi.mimiweb.data.hibernate.DenormInteractionAttribute;
import org.ncibi.mimiweb.hibernate.HumDBQueryInterface;
import org.ncibi.mimiweb.lucene.LuceneInterface;
import org.ncibi.ws.mimi.encoder.MetabReactionConverter;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.thoughtworks.xstream.XStream;

public class MiMIResource extends ServerResource
{
    protected boolean calledInPost = false;
    //private String geneInteraction = "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:output method=\"xml\" encoding=\"UTF-8\" /><xsl:template match=\"/\"><ThisInt><xsl:value-of select=\"org.ncibi.mimiweb.data.GeneInteractionList/list/int\"/></ThisInt></xsl:template></xsl:stylesheet>";
    HumDBQueryInterface humdb = HumDBQueryInterface.getInterface();
    Boolean error = false;
    NCIBIWebService nws = new NCIBIWebService("MiMI");
	Object output = new Object();
	String outputString = "<?xml version=\"1.0\"?>";
	HashMap<Integer, String> errorCodes = new HashMap<Integer, String>();
	XStream xstream = new XStream();
	
	
    public MiMIResource() {
		super();
		errorCodes.put(1001, "Unrecognized parameter.");
		errorCodes.put(1002, "Incorrect data type.");		
		nws.setErrorCodes(errorCodes);       
        xstream.alias("NCIBI", org.ncibi.ws.resource.NCIBIWebService.class);
	}

	@Get
    public String doGet()
    {
        String type = getQueryParameter("type");
        Map<String, Object> reqAttributes = getRequestAttributes();
        Integer geneid = null;
        
        if (reqAttributes.containsKey("geneid")) {
        	    	
	        try {
	        	geneid = Integer.parseInt((String) getRequestAttributes().get("geneid"));
	        	nws.addParameter("GeneID", Integer.toString(geneid));
	        }
	        catch(NumberFormatException e) {
	        	error = true;
	        	nws.setCurrentErrorCode(1002);      	
	        }    
	        if(error) {
	        	//do nothing
	        }
	        else if (type == null || type.equals("interactions")) {
		        	
		        calledInPost = false;
		    	LuceneInterface l = LuceneInterface.getInterface();
		    	ResultGeneMolecule gene = null;
				try {
					gene = l.getGeneData(geneid);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	if (gene != null)
		    	{
		        	nws.setResults(new GeneInteractionList(geneid));
			    	xstream.alias("Result", DenormInteraction.class);
			    	xstream.alias("InteractionAttribute", DenormInteractionAttribute.class);
			    	xstream.registerConverter(new GeneInteractionListConverter());
			    	xstream.registerConverter(new DenormInteractionConverter());
			    	xstream.registerConverter(new DenormInteractionAttributeConverter());
		    	}
	        } 
/*	        else if (type.equals("compounds"))
	        {
	        	List<Compound> compounds = humdb.getCompoundsForGeneid(geneid);
	        	
	        	if (compounds != null)
	        	{
		        	nws.setResults(compounds); 	
		        	xstream.alias("Result", Compound.class);
		        	xstream.registerConverter(new CompoundConverter());   
	        	}	        		
	        }*/
	        else if (type.equals("reactions"))
	        {
	        	Enzyme enzyme = humdb.getEnzymeForGeneId(geneid);
	        	if (enzyme != null)	{
		        	nws.setResults(humdb.getEnzymeForGeneId(geneid));
		        	xstream.alias("Reaction", MetabReaction.class);
		        	xstream.registerConverter(new EnzymeConverter());
		        	xstream.registerConverter(new MetabReactionConverter(false));
	        	}
	        }
/*	        else if (type.equals("nlp"))
	        {
	    	    PubmedDBQueryInterface pubmed = PubmedDBQueryInterface.getInterface();
	    	    List<ClassifiedInteraction> cis = pubmed.getNlpInteractionsForGeneId(geneid);
	    	    if (cis != null) {
		        	xstream.alias("Result", ClassifiedInteraction.class);
		        	xstream.registerConverter(new NCIBIWebServiceConverter());
		        	xstream.registerConverter(new ClassifiedInteractionConverter());
	    	    }
	        }
	        */
	        else {
	        	nws.setCurrentErrorCode(1001);
	        }
	        	
    	} // geneid != null
        else if (reqAttributes.containsKey("compoundid"))
        {
        	
	        final String cid = (String) getRequestAttributes().get("compoundid");
	        Compound compound = humdb.getCompoundDetailsForCid(cid);
	        if (compound != null) {
	        	nws.setResults(compound);
		        nws.setResultTag(true);
	        	nws.addParameter("CompoundID", cid);
	        	xstream.registerConverter(new CompoundConverter());  
	        }
        }
        else if (reqAttributes.containsKey("reactionid"))
        {
        	final String rid = (String) getRequestAttributes().get("reactionid"); 
        	MetabReaction mr = humdb.getReactionDetailsForRid(rid);
        	if (mr != null) {
        		nws.setResults(mr);
	        	MetabReactionConverter converter = new MetabReactionConverter(true);
	        	converter.setWithTag(true);              
	        	nws.setResultTag(true);
	        	nws.addParameter("ReactionID", rid);
	        	xstream.registerConverter(converter);  
        	}
        }         
        
        else
        	nws.setCurrentErrorCode(1001);

        xstream.registerConverter(new NCIBIWebServiceConverter());
    	outputString += xstream.toXML(nws);
    	getResponse().setEntity(outputString, MediaType.TEXT_XML);
    	return outputString;
    }
    
    @Post
    public String doPost()
    {
        calledInPost = true;
        String param = getQueryParameter("param");
        return "Hello world from doPost() with param: " + param;
    }
    
    @SuppressWarnings("deprecation")
    private String getQueryParameter(String what)
    {
        if (calledInPost)
        {
            Form form = getRequest().getEntityAsForm(); // new Form(getRequest().getEntity());
            return form.getValues(what);
        }
        else
        {
            return getQuery().getValues(what);
        }
    }
}
