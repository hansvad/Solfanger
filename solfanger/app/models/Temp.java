package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Temp {

	public Date time;
	public double t1;
	public double t2;
	public double t3;
	public double t4;
	public double energi;

	@Override
	public String toString() {
		return "Temp [time=" + time + ", t1=" + t1 + ", t2=" + t2 + ", t3="
				+ t3 + ", t4=" + t4 + ", energi=" + energi + "]";
	}

	public static Temp generate(String[] data) {
		
		Temp temp = new Temp();
		temp.time = extractDate(data[0]);
		temp.t1 = extractDouble(data[1]);
		temp.t2 = extractDouble(data[2]);
		temp.t3 = extractDouble(data[3]);
		temp.t4 = extractDouble(data[4]);
		
		return temp;
	}

	public static Date extractDate(String time) {
		// Fri Sep 23 20:30:17 +0200 2011
		String modifiedTime = time.replaceFirst("^\\w{3}\\s", "");
		SimpleDateFormat format = new SimpleDateFormat("MMM dd HH:mm:ss yyyy",new Locale("en", "UK"));
		try {
			return format.parse(modifiedTime);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private static Double extractDouble(String value) {
		return Double.valueOf(value.replaceAll("\\s.*", "").trim());
	}
	
	public static void main(String[] args) {
		String data= "Mon Oct 10 00:00:00 2011;4.9;28.2;30.7;28.4;0;0;143;591;11;2;0;00:04;0;0;0;0;0;2320962;1.03;4";
		Temp s= Temp.generate(data.split(";"));
		System.out.println(s);
	}

}
