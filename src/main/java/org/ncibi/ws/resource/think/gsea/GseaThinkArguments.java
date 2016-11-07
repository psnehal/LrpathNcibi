package org.ncibi.ws.resource.think.gsea;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.ncibi.ws.thinkback.ThinkbackAdjustmentMethod;
import org.ncibi.ws.thinkback.ThinkbackEnrichmentMethod;

public class GseaThinkArguments
{
	private ThinkbackAdjustmentMethod adjustMethod;
	private ThinkbackEnrichmentMethod enrichmentMethod;
    private String template;
    private FileItem templateFileItem;
    private String dataset;   
    private FileItem datasetFileItem;
    private String chipset;
    private FileItem chipsetFileItem;
    private String classfile;
    private FileItem classFileItem;
    private List<String> pathways;	

	public ThinkbackEnrichmentMethod getEnrichmentMethod()
    {
        return enrichmentMethod;
    }

    public void setEnrichmentMethod(ThinkbackEnrichmentMethod enrichmentMethod)
    {
        this.enrichmentMethod = enrichmentMethod;
    }

    public ThinkbackAdjustmentMethod getAdjustMethod() {
		return adjustMethod;
	}

	public void setAdjustMethod(ThinkbackAdjustmentMethod adjustMethod) {
		this.adjustMethod = adjustMethod;
	}

	public String getTemplate()
    {
        return template;
    }

    public void setTemplate(String template)
    {
        this.template = template;
    }

    public FileItem getTemplateFileItem()
    {
        return templateFileItem;
    }

    public void setTemplateFileItem(FileItem templateFileItem)
    {
        this.templateFileItem = templateFileItem;
    }

    public String getDataset()
    {
        return dataset;
    }

    public void setDataset(String dataset)
    {
        this.dataset = dataset;
    }

    public FileItem getDatasetFileItem()
    {
        return datasetFileItem;
    }

    public void setDatasetFileItem(FileItem datasetFileItem)
    {
        this.datasetFileItem = datasetFileItem;
    }

    public String getChipset()
    {
        return chipset;
    }

    public void setChipset(String chipset)
    {
        this.chipset = chipset;
    }

    public FileItem getChipsetFileItem()
    {
        return chipsetFileItem;
    }

    public void setChipsetFileItem(FileItem chipsetFileItem)
    {
        this.chipsetFileItem = chipsetFileItem;
    }

    public String getClassfile()
    {
        return classfile;
    }

    public void setClassfile(String classfile)
    {
        this.classfile = classfile;
    }

    public FileItem getClassFileItem()
    {
        return classFileItem;
    }

    public void setClassFileItem(FileItem classFileItem)
    {
        this.classFileItem = classFileItem;
    }
    
    public List<String> getPathways() {
        return pathways;
    }

    public void setPathways(List<String> pathways) {
        this.pathways = pathways;
    }
}
