package org.ncibi.ws.resource;

import java.util.HashMap;

public class NCIBIWebService {
	
	HashMap<String, String> parameterSet = new HashMap<String, String>();
	String application;
	String supportStatement = "Supported by the National Institutes of Health as part of the NIH\'s National Center for Integrative Biomedical Informatics (NCIBI)";
	String grantNumber = "U54 DA021519";
	String supportDetails = "http://www.ncibi.org";
	String copyrightStatement = "Copyright 2010 by the Regents of the University of Michigan";
	String copyrightYear = "2010";
	String copyrightDetails = "http://mimi.ncibi.org/MimiWeb/AboutPage.html#licensing";
	Object results;
	String badRequestResponse;
	HashMap<Integer, String> errorCodes = new HashMap<Integer, String>();
	Integer currentErrorCode;
	
	Boolean resultTag = false;
	
	public NCIBIWebService() {
		super();
	}
	
	public void setErrorCodes(HashMap<Integer, String> errorCodes) {
		this.errorCodes = errorCodes;
	}

	public Integer getCurrentErrorCode() {
		return currentErrorCode;
	}
	
	public String getErrorMessage() {
		return errorCodes.get(currentErrorCode);
	}

	public void setCurrentErrorCode(Integer currentErrorCode) {
		this.currentErrorCode = currentErrorCode;
	}

	public NCIBIWebService(String application) {
		super();
		this.application = application;
	}

	public HashMap<Integer, String> getErrorCodes() {
		return errorCodes;
	}

	public void addErrorCode(Integer errorNumber, String errorMessage) {
		this.errorCodes.put(errorNumber, errorMessage);
	}
	
	public NCIBIWebService(Object results, String application) {
		super();
		this.results = results;
		this.application = application;
	}

	public String getBadRequestResponse() {
		return badRequestResponse;
	}

	public void setBadRequestResponse(String badRequestResponse) {
		this.badRequestResponse = badRequestResponse;
	}

	public Boolean getResultTag() {
		return resultTag;
	}

	public void setResultTag(Boolean resultTag) {
		this.resultTag = resultTag;
	}
	
	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public HashMap<String, String> getParameterSet() {
		return parameterSet;
	}

	public void setParameterSet(HashMap<String, String> parameterSet) {
		this.parameterSet = parameterSet;
	}
	
	public String getCopyrightDetails() {
		return copyrightDetails;
	}

	public void setCopyrightDetails(String copyrightDetails) {
		this.copyrightDetails = copyrightDetails;
	}
	
	public void addParameter(String parameterName, String parameterValue) {
		parameterSet.put(parameterName, parameterValue);
	}
	
	public Object getResults() {
		return results;
	}
	public void setResults(Object results) {
		this.results = results;
	}
	public String getSupportStatement() {
		return supportStatement;
	}
	public void setSupportStatement(String supportStatement) {
		this.supportStatement = supportStatement;
	}
	public String getGrantNumber() {
		return grantNumber;
	}
	public void setGrantNumber(String grantNumber) {
		this.grantNumber = grantNumber;
	}
	public String getSupportDetails() {
		return supportDetails;
	}
	public void setSupportDetails(String supportDetails) {
		this.supportDetails = supportDetails;
	}
	public String getCopyrightStatement() {
		return copyrightStatement;
	}
	public void setCopyrightStatement(String copyrightStatement) {
		this.copyrightStatement = copyrightStatement;
	}
	public String getCopyrightYear() {
		return copyrightYear;
	}
	public void setCopyrightYear(String copyrightYear) {
		this.copyrightYear = copyrightYear;
	}
	

}
