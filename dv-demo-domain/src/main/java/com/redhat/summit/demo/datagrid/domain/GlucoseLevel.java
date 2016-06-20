package com.redhat.summit.demo.datagrid.domain;

import org.infinispan.protostream.annotations.ProtoField;

public class GlucoseLevel {

	private int id;
	
	private int patientId;
	
	private String dateRead;
	
	private String type;
	
	private int value;
	
	private String label;
	
	private String note;

	@ProtoField(number = 1, required = true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	@ProtoField(number = 3, required = true)
	public String getDateRead() {
		return dateRead;
	}

	public void setDateRead(String dateRead) {
		this.dateRead = dateRead;
	}

	@ProtoField(number = 4, required = true)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ProtoField(number = 5, required = true)
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@ProtoField(number = 6, required = true)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@ProtoField(number = 7, required = false, defaultValue = "")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
