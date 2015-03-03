package org.jastemf.converter.ecore;

public class JastAddSpec {
	
	private String resourceName;
	private String contents;
	
	public JastAddSpec(String resourceName, String contents){
		this.resourceName = resourceName;
		this.contents = contents;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
}
