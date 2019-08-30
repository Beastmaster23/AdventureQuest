package application.models;

import application.models.player.Attribute;

public class Item extends Attribute {
	private String name;
	private Attribute attribute; 
	
	
	public Item(String name, Attribute attribute) {
		this.name = name;
		this.attribute = attribute;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public Attribute getAttributes() {
		return attribute;
	}
}
