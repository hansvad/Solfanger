package controllers;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import models.LiveTempSeries;
import models.Temp;
import models.TempStrings;

import com.google.gson.Gson;

import play.mvc.Controller;

public class LiveTemp extends Controller{
	private static String liveData=null;
	private static LiveTempSeries series= new LiveTempSeries(); 
	
	public static void register(String data){
		if (data== null || data.isEmpty()){
			return;
		}
		liveData= data;
		Gson gson= new Gson();
		Temp temp=gson.fromJson(data,TempStrings.class ).getTemp();
		series.add(temp);
	}
	public static void now(){
		if (liveData==null){
			return;
		}
		try {
			response.out.write(liveData.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void lists(){
		try {
			Gson gson= new Gson();
			response.out.write(series.toJson().getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	

}
