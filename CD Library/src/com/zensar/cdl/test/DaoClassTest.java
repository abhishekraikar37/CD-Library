package com.zensar.cdl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import com.zensar.cdl.bean.CDLibrary;
import com.zensar.cdl.dao.DaoClass;

public class DaoClassTest {
	//@Test
	public void testSearch() {
		DaoClass da=new DaoClass();
		CDLibrary cdl=new CDLibrary();
		cdl.setReleaseYear(2040);
		
		assertEquals("CD002",da.searchCdDetails(cdl)[0].getCdId());
	}
	//@Test
	public void testInsert() {
		CDLibrary cdl=new CDLibrary("CD003","Raabta","20-21-2089",2040,210);
		DaoClass da=new DaoClass();
		assertEquals(true, da.insertCdDetails(cdl));
		
	}

	//@Test
	public void testDelete() {
		DaoClass da=new DaoClass();
		CDLibrary cdl=new CDLibrary();
		cdl.setCdId("c2");
		assertEquals(true,da.deleteCdDetails(cdl));
	}
	//@Test
	public void testUpdate() {
		DaoClass da=new DaoClass();
		CDLibrary cdl=new CDLibrary();
		cdl.setPrice(210);
		cdl.setCdId("c3");
		assertEquals(true, da.updateCdDetails(cdl));
	}
	
	  @Test 
	  public void testViewAll() {
	  
	  DaoClass da=new DaoClass();
	 assertNotNull(da.viewAllCdDetails()); 
	  }
	 
}

