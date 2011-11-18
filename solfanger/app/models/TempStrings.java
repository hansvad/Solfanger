package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TempStrings {
	
	public String time;
	public String t1;
	public String t2;
	public String t3;
	public String t4;
	public String p1;
	public String p2;
	public String energi;
	
	public Temp getTemp(){
		Temp temp= new Temp();
		temp.energi=extractDouble(energi);
		temp.t1=extractDouble(t1);
		temp.t2=extractDouble(t2);
		temp.t3=extractDouble(t3);
		temp.t4=extractDouble(t4);
		temp.time=getDate(time);
		temp.p1=getBoolean(p1);
		temp.p2=getBoolean(p2);
		return temp;
	}
	
	private boolean getBoolean(String prosent) {
		return ("100 %".equals(prosent));
	}

	private Date getDate(String time) {
		//Fri Sep 23 20:30:17 +0200 2011
		String modifiedTime=time.replaceFirst("^\\w{3}\\s", "");
		SimpleDateFormat format= new SimpleDateFormat("MMM d HH:mm:ss Z yyyy", new Locale("en", "UK"));
		try {
			return format.parse(modifiedTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	
	}

	private Double extractDouble(String value){
		return Double.valueOf(value.replaceAll("\\s.*", "").trim());
	}

	public static void main(String[] args) {
		TempStrings s= new TempStrings();
		System.out.println(s.getDate("Fri Sep 23 20:30:17 +0200 2011"));
	}
}
