package com.rensu.education.hctms.userauth.bean;


public class Tree {
	
	private int id;
	
	private int pId;

	private String name;
	
	private String icon;
	
	private String value;
	
	private Boolean checked;
	
	private Boolean checkAllNodes;
	
	private Boolean open;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getCheckAllNodes() {
		return checkAllNodes;
	}

	public void setCheckAllNodes(Boolean checkAllNodes) {
		this.checkAllNodes = checkAllNodes;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
