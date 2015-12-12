package com.model;

import java.io.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="issue") 
public class Issue implements Serializable{
	
	private String id;
	private String description;
	private String status;
	private String severity;
	
	@XmlElement
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	@XmlElement
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
