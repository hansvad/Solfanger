package controllers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import models.LiveTempSeries;
import models.Temp;
import models.TempStrings;

import com.google.gson.Gson;

import play.cache.Cache;
import play.mvc.Controller;

public class LiveTemp extends Controller {
	private static final String LIVE_CACHE = "liveSeries";
	private static Temp latest = null;

	public static void register(String data) {
		if (data == null || data.isEmpty()) {
			return;
		}
		Gson gson = new Gson();
		Temp temp = gson.fromJson(data, TempStrings.class).getTemp();
		latest= temp;
		LiveTempSeries series = getLiveTempSeries();
		series.add(temp);
		Cache.set(LIVE_CACHE, series);
		series.save();
	}

	public static void saveBatch() {
		try {
			LiveTempSeries series = getLiveTempSeries();
			BufferedReader body = new BufferedReader(new InputStreamReader(
					request.body));
			String line;
			int lines=0;

			while ((line = body.readLine()) != null) {
				String[] data = line.split(";");
				if (data.length == 21) {
					Temp temp = Temp.generate(data);
					series.add(temp);
				}
				lines++;
			}
			Cache.set(LIVE_CACHE, series);
			series.save();
			System.out.println("Livetemp saved " + lines);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static LiveTempSeries getLiveTempSeries() {
		LiveTempSeries series= Cache.get(LIVE_CACHE, LiveTempSeries.class);
		if (series!=null){
			System.out.println(series.map.size());
			return series;
		}
		if (LiveTempSeries.count() == 0) {
			return new LiveTempSeries();
		}
		series = LiveTempSeries.find().first();
		Cache.set(LIVE_CACHE, series);
		return series;
	}

	public static void now() {
		renderJSON(latest);
	}

	public static void lists() {
		LiveTempSeries series = getLiveTempSeries();
		response.cacheFor("10mn");
		render(series);
	}

}
