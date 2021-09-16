package com.stockato.batch.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	private static final String stockInfoReqUrl = "https://api.finance.naver.com/service/itemSummary.nhn?itemcode=";

	private String restCallGet(String paramUrl) {
		String result = null;

		try {
			URL url = new URL(paramUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Data-Type", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			osw.flush();
			osw.close();

			if (conn.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				result = sb.toString();
				br.close();
			}

			conn.disconnect();

		} catch (IOException e) {
		}

		return result;
	}

	public int getCurrentStockPriceByCode(String code, String time) throws IOException {

		String url = String.format("https://finance.naver.com/item/sise_time.nhn?code=%s&thistime=%s", code, time);

		Document document = Jsoup.connect(url).get();
		Element body = document.getElementsByTag("body").get(0);
		Element recentTr = body.getElementsByTag("tr").get(2);
		Element recentStockPrice = recentTr.select("td.num").get(0);

		String priceStr = recentStockPrice.text();
		priceStr = priceStr.replaceAll(",", "");

		return Integer.valueOf(priceStr);
	}

	public int getCurrentStockPriceByRest(String code) throws IOException {
		String stockInfo = restCallGet(stockInfoReqUrl + code);

		Map<String, Object> result = new ObjectMapper().readValue(stockInfo, HashMap.class);

		if (result.containsKey("now")) {
			return (int)result.get("now");
		}
		return 0;
	}

	public static void main(String[] args) {
		Test test = new Test();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssS");

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String currentTime = sdf1.format(timestamp);
		try {
			int curPrice = test.getCurrentStockPriceByRest("035720");
			System.out.println(curPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
