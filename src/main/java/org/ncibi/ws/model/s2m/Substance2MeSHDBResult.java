package org.ncibi.ws.model.s2m;

import java.util.List;


public class Substance2MeSHDBResult {
	
	private String cname;	
	private String preferred;
	private int CID;
	private int substanceID;
	private String dname;
	private String topLevelHeading;
	private String uniqueID;
	private int descriptorID;
	private int nPMID;
	private double ChiSq;
	private double FisherExact;
	private double Fover;
	private List<Integer> CIDs;
	private Substance2MeSHPubs pubs;
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPreferred() {
		return preferred;
	}
	public void setPreferred(String preferred) {
		this.preferred = preferred;
	}
	public int getCID() {
		return CID;
	}
	public void setCID(int cID) {
		CID = cID;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getTopLevelHeading() {
		return topLevelHeading;
	}
	public void setTopLevelHeading(String topLevelHeading) {
		this.topLevelHeading = topLevelHeading;
	}
	public String getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	public int getDescriptorID() {
		return descriptorID;
	}
	public void setDescriptorID(int descriptorID) {
		this.descriptorID = descriptorID;
	}
	public int getnPMID() {
		return nPMID;
	}
	public void setnPMID(int nPMID) {
		this.nPMID = nPMID;
	}
	public double getChiSq() {
		return ChiSq;
	}
	public void setChiSq(double chiSq) {
		ChiSq = chiSq;
	}
	public double getFisherExact() {
		return FisherExact;
	}
	public void setFisherExact(double fisherExact) {
		FisherExact = fisherExact;
	}
	public double getFover() {
		return Fover;
	}
	public void setFover(double fover) {
		Fover = fover;
	}
	public Substance2MeSHPubs getPubs() {
		return pubs;
	}
	public void setPubs(Substance2MeSHPubs pubs) {
		this.pubs = pubs;
	}
	@Override
	public String toString() {
		return "Metab2MeSHDBResult [CID=" + CID + ", ChiSq=" + ChiSq
				+ ", FisherExact=" + FisherExact + ", Fover=" + Fover
				+ ", cname=" + cname + ", descriptorID=" + descriptorID
				+ ", dname=" + dname + ", nPMID=" + nPMID + ", preferred="
				+ preferred + ", pubs=" + pubs + ", topLevelHeading="
				+ topLevelHeading + ", uniqueID=" + uniqueID + "]";
	}
	public void setCIDs(List<Integer> cIDs) {
		CIDs = cIDs;
	}
	public List<Integer> getCIDs() {
		return CIDs;
	}
	public void setSubstanceID(int substanceID) {
		this.substanceID = substanceID;
	}
	public int getSubstanceID() {
		return substanceID;
	}
	
}
	
