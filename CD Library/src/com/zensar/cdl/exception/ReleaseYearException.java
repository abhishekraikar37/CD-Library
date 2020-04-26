package com.zensar.cdl.exception;

public class ReleaseYearException extends Exception{
	int year;
	public ReleaseYearException(int year) {
	this.year=year;
	}
	public String toString() {
		return "Release Year can not be Future Year i.e"+year;
	
	}
	}


