package models;

import java.io.Serializable;
import java.lang.reflect.Type;
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

@MongoEntity("livetemp")
public class LiveTempSeries extends MongoModel implements Serializable {
	private static final int size= 1000;
	public Map<Long, Temp> map= new FixedMap<Long,Temp>(size);
	
	public void add(Temp temp){
		Long timePoint= temp.time.getTime();
		map.put(timePoint, temp);
	}

}
