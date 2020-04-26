package com.zensar.cdl.main;

import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.zensar.cdl.bean.CDLibrary;
import com.zensar.cdl.dao.DaoClass;
import com.zensar.cdl.dao.DaoInterface;
import com.zensar.cdl.exception.InvalidPriceException;
import com.zensar.cdl.exception.ReleaseYearException;

public class CDLibraryMain {
	public static void printLine() {
		System.out.println();
		for(int i=0;i<85;i++)
			System.out.print("-");
		System.out.println();
	}
	
	
	/*
	 * Main function which implements all the requirement. 
	 * It Provides The user interface.
	 */

	public static void main(String[] args) {
		int ch;
		String con;
		CDLibrary cdl;
		int count=0;
		CDLibrary cd = new CDLibrary();
		Scanner sc = new Scanner(System.in);
		CDLibraryMain cdm=new CDLibraryMain();
		DaoInterface<CDLibrary,Boolean> da;
		DaoInterface<CDLibrary,Boolean> ui;
		
		System.out.println("-------------------------------WELCOME TO CD LIBRARY---------------------------------");
		String cdid,cdname,pdate;
		int ryear;
		double price;
		String main;
		do {
			/* 
			 * Main Menue Options
			 */
			System.out.println("1. Admin");
			System.out.println("2. User");
			System.out.println("3. Exit");
			CDLibraryMain.printLine();
			System.out.print("Select user or Exit: ");
			int choice = sc.nextInt();
		switch (choice) {
		case 1:
			do {
			da=new DaoClass();
			
			/* 
			 * All Admin Options
			 */
			if(count==0)
				System.out.println("-----------------------------------WELCOME ADMIN-------------------------------------");
			count++;
			System.out.println("1. Insert CD details.");
			System.out.println("2. Delete CD from Library."); 
			System.out.println("3. Update CD Price.");
			System.out.println("4. View All Records");
			System.out.println("5. Search CD details.");
			CDLibraryMain.printLine();
			System.out.print("Enter your choice: ");
			int admin_choice = sc.nextInt();
			switch (admin_choice) {
			case 1:
				
				System.out.print("Enter CDID:");
				cdid = sc.next();
				cd.setCdId(cdid);
				CDLibraryMain.printLine();
				System.out.print("Enter CD Name:");
				cdname = sc.next();
				cd.setCdName(cdname);
				CDLibraryMain.printLine();
				System.out.print("Enter Procure Date(DD-MM-YYYY):");
				pdate = sc.next();
				cd.setProcureDate(pdate);
				CDLibraryMain.printLine();
				System.out.print("Enter Released Year:");
				
				try {
					ryear = sc.nextInt();
					if(ryear>Year.now().getValue())
						throw new ReleaseYearException(ryear);
					cd.setReleaseYear(ryear);
					CDLibraryMain.printLine();
					System.out.print("Enter Price of CD:");
					price = sc.nextDouble();
					cd.setPrice(price);
					CDLibraryMain.printLine();
					if(price<0)
						throw new InvalidPriceException(price);
					
					// insert method
					
					if(da.insertCdDetails(cd)) 
						System.out.println("Inserted!"); 
					else
						 System.out.println("Not Inserted!");
					CDLibraryMain.printLine();
				}catch(InvalidPriceException ip) {
					System.out.println(ip);
				}
				catch(ReleaseYearException ry) {
					System.out.println(ry);
				}
				break;
			
			case 2:
				CDLibraryMain.printLine();
				System.out.println("Enter the CD ID to delete the CD");
				cdid = sc.next();
				cd.setCdId(cdid);
				CDLibraryMain.printLine();
				//delete method
				
				if(da.deleteCdDetails(cd)) 
					System.out.println("Deleted!"); 
				else
					 System.out.println("Not Deleted");				
				CDLibraryMain.printLine();
				break;
				
			case 3:
				CDLibraryMain.printLine();
				System.out.print("Enter the CD ID:");
				
				cdid = sc.next();
				CDLibraryMain.printLine();
				System.out.print("Enter the new Price:");
				try {	
					price = sc.nextDouble();
					CDLibraryMain.printLine();
					if(price<0)
						throw new InvalidPriceException(price);
					cd.setCdId(cdid);
					
					cd.setPrice(price);
					
					// update method
					
					if(da.updateCdDetails(cd))
						System.out.println("Updated!");
					else
						System.out.println("Not Updated!");
					CDLibraryMain.printLine();
				}catch(InvalidPriceException ip) {
					System.out.println(ip);
				}
				break;
				
			case 4:
				CDLibraryMain.printLine();
				System.out.println("Following are the Records");
				CDLibraryMain.printLine();
				// view all method
				
				ArrayList<CDLibrary> al=new ArrayList<CDLibrary>(); 
				al=da.viewAllCdDetails();
				 Iterator<CDLibrary> it=al.iterator(); 
				 System.out.println("Details of All CD's available in Library:");
					CDLibraryMain.printLine();

				 System.out.println("CDID\tCD Name\t\tProcure Date\t\tRelease Year\tPrice");
					CDLibraryMain.printLine();

				 System.out.println();
				 while(it.hasNext()) { 
					 CDLibrary cl=it.next(); 
					 System.out.println(cl); 
				}
				
				 CDLibraryMain.printLine();
				break;
			case 5:
				CDLibraryMain.printLine();
				int year;
				System.out.print("Enter Release Year:");
				year=sc.nextInt();
				cdl=new CDLibrary();
				CDLibraryMain.printLine();
				cdl.setReleaseYear(year);
				CDLibrary cdVal[]=da.searchCdDetails(cdl);
				if(cdVal[0]!=null) {
					int i=0;
					System.out.println("CD details released in Year:"+year);
					CDLibraryMain.printLine();

					 System.out.println("CDID\tCD Name\t\tProcure Date\t\tRelease Year\tPrice");
						CDLibraryMain.printLine();

					 System.out.println();
					while(cdVal[i]!=null) {
						System.out.println(cdVal[i]);
					i++;
					}
				}
				else
					System.out.println("No CD's released in given year.");
				CDLibraryMain.printLine();
				break;
				
			default:
				System.out.println("Invalid Choice!!");
			}	
			
			System.out.print("Do you want to continue(y/n)?");
			 con=sc.next();
			 CDLibraryMain.printLine();
			}while(con.equals("y")||con.equals("Y"));
			
			break;
			case 2:
				count=0;
				do {
				ui=new DaoClass();
				
				/*
				 * Visitor's options
				 */	
				if(count==0)
					System.out.println("-----------------------------------WELCOME VISITOR-----------------------------------");
				System.out.println("1.Search CD from Library.");
				System.out.println("2.View CD details.");
				CDLibraryMain.printLine();
				System.out.print("Enter Your choice:");
				ch=sc.nextInt();
				count++;
				switch(ch) {
				case 1:
					CDLibraryMain.printLine();
					int year;
					System.out.print("Enter Release Year:");
					year=sc.nextInt();
					cdl=new CDLibrary();
					CDLibraryMain.printLine();
					cdl.setReleaseYear(year);
					CDLibrary cdVal[]=ui.searchCdDetails(cdl);
					if(cdVal[0]!=null) {
						int i=0;
						System.out.println("CD details released in Year:"+year);
						CDLibraryMain.printLine();

						 System.out.println("CDID\tCD Name\t\tProcure Date\t\tRelease Year\tPrice");
							CDLibraryMain.printLine();

						 System.out.println();
						while(cdVal[i]!=null) {
							System.out.println(cdVal[i]);
						i++;
						}
						
					}
					else
						System.out.println("No CD's released in given year.");
					CDLibraryMain.printLine();

					break;
				case 2:
					CDLibraryMain.printLine();
					String cid;
					System.out.print("Enter CD Id:");
					cid=sc.next();
					cdl=new CDLibrary();
					cdl.setCdId(cid);
					CDLibraryMain.printLine();
					
					
					CDLibrary cdlib=ui.viewCdDetails(cdl);
					if(cdlib!=null) {
						System.out.println("CD Details are:");
						CDLibraryMain.printLine();

						 System.out.println("CDID\tCD Name\t\tRelease Year\tPrice");
							CDLibraryMain.printLine();

						 System.out.println();
						
						
						System.out.println(cdlib.getCdId()+"\t"+cdlib.getCdName()+"\t\t"+cdlib.getReleaseYear()+"\t\t"+cdlib.getPrice());
					
					}
					else {
						System.out.println("No cd with given Id!");
						
					}
					CDLibraryMain.printLine();

					break;
				default:
					System.out.println("Invalid Choice!!");	
				
				}
				System.out.print("Do you want to continue(y/n)?");
				 con=sc.next();
					CDLibraryMain.printLine();

				}while(con.equals("y")||con.equals("Y"));
				
				break;
				
				
			case 3:
				CDLibraryMain.printLine();
				System.out.println("Exiting...\nExited");
				return;
			default:
				System.out.println("Invalid Choice!!");
			
							
		}
		CDLibraryMain.printLine();

		System.out.print("Back To main Menue(Y/N)?");
		main=sc.next();
		count=0;
		CDLibraryMain.printLine();
		}while(main.equals("y")||main.equals("Y"));
		System.out.println("Exiting...\nExited");
	}
}
