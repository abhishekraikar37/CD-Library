import com.zensar.cdl.bean.CDLibrary;
import com.zensar.cdl.dao.DaoClass;

public class Driver {
	public static void main(String[] args) {

		/*
		 * CDLibrary cdl=new CDLibrary("CD002","ABC","10-01-2012",2018,210.0);
		 * 
		 * DaoAdmin da=new DaoAdmin(); if(da.delete(cdl)) System.out.println("Deleted");
		 * else System.out.println("Not deleted");
		 * 
		 * if(da.insert(cdl)) System.out.println("Inserted!"); else
		 * System.out.println("Deleted!");
		 * 
		 * ArrayList<CDLibrary> al=new ArrayList<CDLibrary>(); al=da.viewAll();
		 * Iterator<CDLibrary> it=al.iterator(); while(it.hasNext()) { CDLibrary
		 * cl=it.next(); System.out.println(cl); }
		 */

		DaoClass du = new DaoClass();
		CDLibrary cd = new CDLibrary();
		cd.setCdId("CD001");
		cd.setReleaseYear(2018);
		CDLibrary cdd[]=du.searchCdDetails(cd);
		int i=0;
		while(cdd[i]!=null) {
			System.out.println(cdd[i].getCdId());
			i++;
		}
		cd = du.viewCdDetails(cd);
		System.out.println("ID:" + cd.getCdId() + " Name:" + cd.getCdName());

	}
}
