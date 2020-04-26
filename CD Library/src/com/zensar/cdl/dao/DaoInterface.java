package com.zensar.cdl.dao;

import java.util.ArrayList;

public interface DaoInterface<T,P>{
	T[] searchCdDetails(T cdl);
	T viewCdDetails(T cdl);
	P insertCdDetails(T cdl);
	P deleteCdDetails(T cdl);
	P updateCdDetails(T cdl);
	ArrayList<T> viewAllCdDetails();
}

