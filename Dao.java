package com.RestAPInew;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class Dao {
	final static String DriverName="oracle.jdbc.driver.OracleDriver";
	final static String URL="jdbc:oracle:thin:@oracledb.co1i88lxl3vs.us-east-2.rds.amazonaws.com:1521:oracleDB";
	final static String username="rootsystem";
	final static String password="harsha123";
	
	public static List<PointBean> fetch() throws ClassNotFoundException,SQLException{
		
		ArrayList<PointBean> al=new ArrayList<>();
		   
		 Connection con=null;
		try {
				Class.forName(DriverName);
				 con=DriverManager.getConnection(URL, username, password);
				String sql = "select * from WEATHER3 order by TIME asc";
				//System.out.println(sql);
				PreparedStatement pst=con.prepareStatement(sql);
				/*Date d=new Date(20140201);
				System.out.println(d);
				pst.setDate(1,d);
				pst.setInt(2, 65);
				pst.setInt(3, 45);*/
				ResultSet rs=pst.executeQuery();
				//System.out.println(rs);
				java.util.Date d=new java.util.Date(20140201);
				
				while(rs.next()){
					//System.out.println(rs.getFetchSize());
					d=rs.getDate(1);
					PointBean tmp=new PointBean(d.toString());
					al.add(tmp);
					//String out="{\"DATE\": \""+d.toString().replace("-", "")+"\"}";
					
					System.out.println(".toString() : "+d.toString());
				}
				
				
			
				
			
				//con.commit();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		return al;
			
		
	}
public static PointBean insertTemp(TempBean tmpe) throws ClassNotFoundException,SQLException{
		
		ArrayList<PointBean> al=new ArrayList<>();
		  System.out.println("tmpe" +tmpe); 
		  PointBean pb = null;
		 Connection con=null;
		try {
				Class.forName(DriverName);
				 con=DriverManager.getConnection(URL, username, password);
				/*Date dt=new Date(0);
				DateFormat dtfmt=DateFormat.getDateInstance();*/
				String sql = "insert into WEATHER3 values(TO_DATE(?,'YYYYMMDD'),?,?)";
				System.out.println(sql);
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setString(1, tmpe.getDt());
				
				pst.setDouble(2, Float.parseFloat(tmpe.getTmax()));
				pst.setDouble(3, Float.parseFloat(tmpe.getTmin()));
				/*Date d=new Date(20140201);
				System.out.println(d);
				pst.setDate(1,d);
				pst.setInt(2, 65);
				pst.setInt(3, 45);*/
				ResultSet rs=pst.executeQuery();
				//System.out.println(rs);
				Date d=new Date(20140201);
				
				
				
				pb=new PointBean(tmpe.getDt());
				
			al.add(pb);
				
			System.out.println(".toString() : "+tmpe.getDt());
				//con.commit();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		return pb;
			
		
	}
public static TempBean fetchTemp(String date) throws ClassNotFoundException,SQLException{
	
	ArrayList<TempBean> al=new ArrayList<>();
	TempBean tmpb=null;   
	 Connection con=null;
	try {
			Class.forName(DriverName);
			 con=DriverManager.getConnection(URL, username, password);
			/*Date dt=new Date(0);
			DateFormat dtfmt=DateFormat.getDateInstance();*/
			String sql = "select * from weather3 where TIME=TO_DATE(?,'YYYYMMDD')";
			System.out.println(sql);
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, date);
			/*Date d=new Date(20140201);
			System.out.println(d);
			pst.setDate(1,d);
			pst.setInt(2, 65);
			pst.setInt(3, 45);*/
			ResultSet rs=pst.executeQuery();
			//System.out.println(rs);
			Date d=new Date(20140201);
			
			while(rs.next()){
				//System.out.println(rs.getFetchSize());
				
				d=rs.getDate(1);
				String Tmax=rs.getString(2);
				String Tmin=rs.getString(3);
			 tmpb=new TempBean(d.toString(), Tmax, Tmin);
				//String out="{\"DATE\": \""+d.toString().replace("-", "")+"\",\"TMAX\":"+Tmax+",\"TMIN\":"+Tmin+"}";
				al.add(tmpb);
				//System.out.println(".toString() : "+d.toString());
			}
			
			
		
			
		
			//con.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	return tmpb;
		
	
}
public static String deleteTemp(String date) throws ClassNotFoundException,SQLException{
	
	ArrayList<PointBean> al=new ArrayList<>();
	  System.out.println("tmpe" +date); 
	 Connection con=null;
	try {
			Class.forName(DriverName);
			 con=DriverManager.getConnection(URL, username, password);
			/*Date dt=new Date(0);
			DateFormat dtfmt=DateFormat.getDateInstance();*/
			String sql = "delete from weather3 where TIME=TO_DATE(?,'YYYYMMDD')";
			System.out.println(sql);
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1,date);
			
			/*Date d=new Date(20140201);
			System.out.println(d);
			pst.setDate(1,d);
			pst.setInt(2, 65);
			pst.setInt(3, 45);*/
			ResultSet rs=pst.executeQuery();
			//System.out.println(rs);
			Date d=new Date(20140201);
			
			
			
			PointBean pb=new PointBean(date);
			
		al.add(pb);
			
		System.out.println(".toString() : "+date);
			//con.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	return "Hello";
		
	
}

public static List<TempBean> fetchForcast(String date) throws ClassNotFoundException,SQLException{

	System.out.println("Date : " + date);
	
	// returns the directly from data base
	TempBean tm = Dao.fetchTemp(date);
	System.out.println("forecast size" +tm);
	List<TempBean> fivedata = new ArrayList<TempBean>();

	/* String fin = Date1.substring(4, 6)+"-"+Date1.substring(6); */
TempBean tmpbn=null;
	// future date
	
	if (null==tm) {	
		String ODate = null;
		DateTime dt = new DateTime(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)),
				Integer.parseInt(date.substring(6)), 0, 0, 0, 0);
		for (int i = 1; i <= 5; i++) {
			Connection con=null;
			try {
					Class.forName(DriverName);
					 con=DriverManager.getConnection(URL, username, password);
					 String sql = "select AVG(TMAX),AVG(TMIN) from weather3 where TO_CHAR(TIME,'mmddyyyy') like ?";
					PreparedStatement pst=con.prepareStatement(sql);				
					pst.setString(1,date.substring(4)+"%");				
					int p=	pst.executeUpdate();
				System.out.println(p);
				ResultSet rs=pst.executeQuery();
				//System.out.println(rs);
				//Date d=new Date(20140201);
				
				while(rs.next()){
					//System.out.println(rs.getFetchSize());
					/*d=rs.getDate(1);*/
					String tmax=rs.getString(1);
					String tmin=rs.getString(2);
					tmpbn=new TempBean(date, tmax, tmin);
					fivedata.add(tmpbn);
					System.out.println("date is :"+date+" tmax is : "+tmax+" tmin is : "+tmin);
				}
					//con.commit();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}

			dt = dt.plusDays(1);
			ODate = date;
			date = dt.toString().substring(0, 10).replace("-", "");

			

		}

	}
	
	

	/*
	 * if ( weekdata.size() > 0 & weekdata.size() < 7 ) { int datesToPredict=
	 * (7-weekdata.size()); //prediction for remaining dates should be done }
	 */
	return fivedata;

}
}
