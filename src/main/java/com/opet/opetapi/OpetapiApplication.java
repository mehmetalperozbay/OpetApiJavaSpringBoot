package com.opet.opetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Collections;

@SpringBootApplication
public class OpetapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpetapiApplication.class, args);
		istek();
	}

	public static void istek() {
		RestTemplate resttemplate = new RestTemplate();
		String url = "https://www.petrolofisi.com.tr/Fuel/Search";

		// Form verileri
		String formData = "template=1&cityId=02&districtId=03502";

		// HTTP başlıkları
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.ALL));
		headers.set("Accept-Language", "tr-TR,tr;q=0.9,en-US;q=0.8,en;q=0.7");
		headers.set("Connection", "keep-alive");
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Origin", "https://www.petrolofisi.com.tr");
		headers.set("Referer", "https://www.petrolofisi.com.tr/akaryakit-fiyatlari");
		headers.set("Sec-Fetch-Dest", "empty");
		headers.set("Sec-Fetch-Mode", "cors");
		headers.set("Sec-Fetch-Site", "same-origin");
		headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36");
		headers.set("X-Requested-With", "XMLHttpRequest");
		headers.set("sec-ch-ua", "\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"");
		headers.set("sec-ch-ua-mobile", "?0");
		headers.set("sec-ch-ua-platform", "\"Windows\"");

		HttpEntity<String> httpentity = new HttpEntity<>(formData, headers);
		String response = resttemplate.exchange(url, HttpMethod.POST, httpentity, String.class).getBody();

		// Yanıtı yazdırma
		System.out.println("Response: " + response);

		// Jsoup ile HTML parse etme
		Document document = Jsoup.parse(response);

		// Tablodan Kurşunsuz 95 fiyatını çekme
		Elements kurşunsuz95Elements = document.select("td:has(span.with-tax)");

		for (Element element : kurşunsuz95Elements) {
			String fiyat = element.select("span.with-tax").text();
			System.out.println("Kurşunsuz 95 Fiyatı: " + fiyat);
		}
	}
}
