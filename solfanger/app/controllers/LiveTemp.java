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

import play.mvc.Controller;

public class LiveTemp extends Controller {
	private static String liveData = null;

	public static void register(String data) {
		if (data == null || data.isEmpty()) {
			return;
		}
		liveData = data;
		Gson gson = new Gson();
		Temp temp = gson.fromJson(data, TempStrings.class).getTemp();
		LiveTempSeries series = getLiveTempSeries();
		series.add(temp);
		series.save();
	}

	public static void saveBatch() {
		try {
			LiveTempSeries series = getLiveTempSeries();
			BufferedReader body = new BufferedReader(new InputStreamReader(
					request.body));
			String line;

			while ((line = body.readLine()) != null) {
				String[] data = line.split(";");
				if (data.length == 21) {
					Temp temp = Temp.generate(data);
					series.add(temp);
				}
			}
			series.save();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static LiveTempSeries getLiveTempSeries() {
		if (LiveTempSeries.count() == 0) {
			System.out.println("creating new");
			return new LiveTempSeries();
		}
		LiveTempSeries series = LiveTempSeries.find().first();
		System.out.println("found series: " + series.get_id() + " size: "
				+ series.map.size());
		return series;
	}

	public static void now() {
		if (liveData == null) {
			return;
		}
		renderJSON(liveData);
	}

	public static void lists() {
		LiveTempSeries series = getLiveTempSeries();
		render(series);
	}

}
