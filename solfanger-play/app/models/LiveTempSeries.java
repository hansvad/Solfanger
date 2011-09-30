package models;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LiveTempSeries   {
	private static final int size= 2016;
	private FixedList<Object[]> t1= new FixedList<Object[]>(size);
	private FixedList<Object[]> t3= new FixedList<Object[]>(size);
	
	public void add(Temp temp){
		Long timePoint= temp.time.getTime();
		t1.add(new Object[]{timePoint,temp.t1});
		t3.add(new Object[]{timePoint,temp.t3});
	}
	
	public String toJson(){
		
		GsonBuilder builder= new GsonBuilder();
//		builder.registerTypeAdapter(FixedMap.class,new FixedMapSerializer());
		Gson gson= builder.create();
		return gson.toJson(this);
		
	}
	
	
	public static void main(String[] args) throws Exception {
		LiveTempSeries s= new LiveTempSeries();
		
		for (int i = 0; i <10; i++) {
			Temp t= new Temp();
			t.time= new Date();
			t.t1=i;
			t.t2=i+4.5;
			s.add(t);
			Thread.sleep(100);
		}
		
		System.out.println(s.toJson());
		// [Date.UTC(1970,  9, 27), 0   ],
	}
	
	
	private class FixedMapSerializer implements JsonSerializer<FixedMap> {
		
		@Override
		public JsonElement serialize(FixedMap map, Type type,
				JsonSerializationContext context) {
			System.out.println("hei");
			return null;
		}
		
	}

}
