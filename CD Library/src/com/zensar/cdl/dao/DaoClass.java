package com.zensar.cdl.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.zensar.cdl.bean.CDLibrary;
import com.zensar.cdl.connection.DBConnect;
import com.zensar.cdl.exception.ReleaseYearException;

public class DaoClass implements DaoInterface<CDLibrary,Boolean>{
	Connection conn=null;
	PreparedStatement pst;
	
	public DaoClass(){
		
	}
	@Override
	public CDLibrary[] searchCdDetails(CDLibrary cdl) {
		conn=DBConnect.getConnection();
		CDLibrary cd[]=new CDLibrary[100];
		try
		{
			ResultSet r;
			pst=conn.prepareStatement("select * from CDLibrary where Release_Year=?");
			pst.setInt(1,cdl.getReleaseYear());
			r=pst.executeQuery();
			int i=0;
			
			
			while(r.next()) {
				
				  cd[i]=new CDLibrary(); 
				  cd[i].setCdId(r.getString(1));
				  cd[i].setCdName(r.getString(2)); 
				  cd[i].setProcureDate(r.getString(3));
				  cd[i].setReleaseYear((r.getInt(4))); 
				  cd[i].setPrice(r.getDouble(5));
				i++;
			}
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return cd;
	}

	/*
	 * This method displays all the records entered into table CDLibrary according to the release year.
	 */
	@Override
	public Boolean insertCdDetails(CDLibrary cdl) {
		SimpleDateFormat sdate=new SimpleDateFormat("dd-MM-yyyy");
		
		
		conn=DBConnect.getConnection();
		boolean status=false;
		try {
			java.util.Date udate=sdate.parse(cdl.getProcureDate());
			java.sql.Date sqdate=new Date(udate.getTime());
			pst=conn.prepareStatement("insert into cdlibrary values(?,?,?,?,?)");
			pst.setString(1,cdl.getCdId());
			pst.setString(2, cdl.getCdName());
		    pst.setDate(3, sqdate);
		    pst.setInt(4, cdl.getReleaseYear());
		    pst.setDouble(5, cdl.getPrice());
		    
		   int count= pst.executeUpdate();
		   
		   
		   if(count>0) {
			   status=true;
		   }
		   conn.close();
		   
		
		}catch (Exception e) {
				e.printStackTrace();
		}
		
		return status;
	}
	@Override
	public Boolean deleteCdDetails(CDLibrary cdl) {
		conn=DBConnect.getConnection();
		boolean status=false;
		try {
			pst=conn.prepareStatement("delete from CDLibrary where CDID=?");
			pst.setString(1,cdl.getCdId());
			int count=0;
			
			count=pst.executeUpdate();
			if(count>0)
				status=true;
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public Boolean updateCdDetails(CDLibrary cdl) {
		conn=DBConnect.getConnection();
		boolean status = false;
		try {
			pst=conn.prepareStatement("update CDLibrary set price = ? where CDId = ?");
			pst.setDouble(1, cdl.getPrice());
			pst.setString(2, cdl.getCdId());
			int count = pst.executeUpdate();
			conn.close();
			if(count>0) {
				status=true;
			}
			conn.close();
			
		}catch (Exception e) {
			 System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		      e.printStackTrace();
		}
	
	return status;
	}
	@Override
	public ArrayList<CDLibrary> viewAllCdDetails() {
		conn=DBConnect.getConnection();
		ArrayList<CDLibrary> alo=new ArrayList<CDLibrary>();
	       CDLibrary cdl=new CDLibrary();
		try {
			Statement st = conn.createStatement();
			 ResultSet r=st.executeQuery("select * from CDLibrary");
			 
			 while(r.next()){
				  cdl.setCdId(r.getString(1)); 
				  cdl.setCdName(r.getString(2));
				  cdl.setProcureDate(r.getString(3)); 
				  cdl.setReleaseYear((r.getInt(4)));
				  cdl.setPrice(r.getDouble(5)); 
				  alo.add(cdl);
				  cdl=new CDLibrary();
				 
			 }
			 conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			return alo;
	}
	@Override
	public CDLibrary viewCdDetails(CDLibrary cdl) {
		conn=DBConnect.getConnection();
		CDLibrary cd=new CDLibrary();
		try
		{	
			ResultSet r;
			pst=conn.prepareStatement("select cdid,cdname,release_year,price from CDLibrary where CDID=?");
			pst.setString(1,cdl.getCdId());
			r=pst.executeQuery();
			
			while(r.next()) {
				cd.setCdId(r.getString(1));
				cd.setCdName(r.getString(2));		
				cd.setReleaseYear(r.getInt(3));
				cd.setPrice(r.getDouble(4));
				return cd;
			}
			conn.close();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
}
