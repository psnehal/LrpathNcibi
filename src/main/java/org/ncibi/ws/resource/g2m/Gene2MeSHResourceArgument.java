package org.ncibi.ws.resource.g2m;

import org.apache.commons.lang.math.NumberUtils;

public class Gene2MeSHResourceArgument
{
	private String searchTerm = "";
    private String geneid = "";
    private String descriptorid = "";
    private String descriptorSearchTerm = "";
    private String taxid = "0";
    private String symbol = "";
    private String pubLimit ="250";

    public int getDescriptoridInt() {
		return NumberUtils.toInt(descriptorid, -1);
	}

	public void setDescriptorid(String descriptorid) {
		this.descriptorid = descriptorid;
	}

	public void setGeneid(String geneid) {
		this.geneid = geneid;
	}

	public String getGeneid() {
		return geneid;
	}

	public String getDescriptorid() {
		return descriptorid;
	}

	public int getGeneidInt()
    {
		return NumberUtils.toInt(geneid, -1);
    }

	public void setDescriptorSearchTerm(String descriptorSearchTerm) {
		this.descriptorSearchTerm = descriptorSearchTerm;
	}

	public String getDescriptorSearchTerm() {
		return descriptorSearchTerm;
	}

	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}

	public String getTaxid() {
		return taxid;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setPubLimit(String pubLimit) {
		this.pubLimit = pubLimit;
	}

	public String getPubLimit() {
		return pubLimit;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

}
 