package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
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
    
	
}
