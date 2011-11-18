package controllers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import models.HistoryTempSeries;
import models.Temp;
import models.TempStrings;

import com.google.gson.Gson;

import play.cache.Cache;
import play.mvc.Controller;

public class History extends Controller {
	private static final String HIST_CACHE = "historySeries";

	public static void register(String data) {
		if (data == null || data.isEmpty()) {
			return;
		}
		Gson gson = new Gson();
		Temp temp = gson.fromJson(data, TempStrings.class).getTemp();
		HistoryTempSeries series = getHistory();
		series.add(temp);
		Cache.set(HIST_CACHE, series);
		series.save();
	}

	public static void saveBatch() {
		try {
			HistoryTempSeries series = getHistory();
			BufferedReader body = new BufferedReader(new InputStreamReader(
					request.body));
			String line;
			int lines=0;

			while ((line = body.readLine()) != null) {
				lines++;
				String[] data = line.split(";");
				if (data.length == 21) {
					Temp temp = Temp.generate(data);
					series.add(temp);
				}
			}
			Cache.set(HIST_CACHE, series);
			series.save();
			System.out.println("Saved history "+ lines +" elements" );
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static HistoryTempSeries getHistory() {
		HistoryTempSeries series= Cache.get(HIST_CACHE, HistoryTempSeries.class);
		if (series!=null){
			return series;
		}
		if (HistoryTempSeries.count() == 0) {
			return new HistoryTempSeries();
		}
		series = HistoryTempSeries.find().first();
		Cache.set(HIST_CACHE, series);
		return series;
	}

	public static void lists() {
		HistoryTempSeries series = getHistory();
		render(series);
	}

}
