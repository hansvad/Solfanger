package models;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import play.modules.mongo.MongoEntity;
import play.modules.mongo.MongoModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@MongoEntity("historytemp")
public class HistoryTempSeries extends MongoModel implements Serializable {
	private static final int size= 2016;
	private static final SimpleDateFormat format= new  SimpleDateFormat("yyyyMMdd");
	public Map<String, Temp> map= new FixedMap<String,Temp>(size);
	
	public void add(Temp temp){
		String key=format.format(temp.time);
		map.put(key, temp);
	}

}
