package com.zensar.cdl.bean;

public class CDLibrary {
	private String cdId;
	String cdName;
	String procureDate;
	int releaseYear;
	double price;
	public CDLibrary() {
		
	}
	public CDLibrary(String cdId, String cdName, String procureDate, int releaseYear, double price) {
		super();
		this.cdId = cdId;
		this.cdName = cdName;
		this.procureDate = procureDate;
		this.releaseYear = releaseYear;
		this.price = price;
	}
	@Override
	public String toString() {
		return cdId + "\t" + cdName + "\t\t" + procureDate + "\t\t"
				+ releaseYear + "\t" + price ;
	}
	public String getCdId() {
		return cdId;
	}
	public void setCdId(String cdId) {
		this.cdId = cdId;
	}
	public String getCdName() {
		return cdName;
	}
	public void setCdName(String cdName) {
		this.cdName = cdName;
	}
	public String getProcureDate() {
		return procureDate;
	}
	public void setProcureDate(String procureDate) {
		this.procureDate = procureDate;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
