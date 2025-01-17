package com.opet.opetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication

public class OpetapiApplication {

	public static String ilkodu = "34";
	public static String ilceadi = "KARTAL";

	public static void main(String[] args) {

		SpringApplication.run(OpetapiApplication.class, args);

		istek();
	}

	public static void istek(){

		RestTemplate resttemplate = new RestTemplate();

		String url = "https://api.opet.com.tr/api/fuelprices/prices?ProvinceCode=" + ilkodu + "&IncludeAllProducts=true";

		try{

			String response = resttemplate.getForObject(url, String.class);
			veriisleme(response);



		}catch(Exception e){
			e.printStackTrace();
		}


	}

	public static void veriisleme(String veri){

		try{

			ObjectMapper objectmapper = new ObjectMapper();
			JsonNode jsonveri = objectmapper.readTree(veri);

			JsonNode ilcebulundumu = ilcebulma(jsonveri , ilceadi);

			if (ilcebulundumu != null){

				veriyazdır(ilcebulundumu);
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public static JsonNode ilcebulma(JsonNode jsonveri, String ilceadi){

		for (JsonNode json : jsonveri){ // jsonveri değişkenini döndürüyor.

			if (json.path("districtName").asText().equals(ilceadi)){ // Her döngüde farklı json döndürdügü icin eğer ilce adı ile baktığı json veri aynı olursa o veriyi aktarıyor.
				return json;
			}

		}
		return null;
	}
	public static void veriyazdır(JsonNode jsonveri){

		JsonNode fiyatlar = jsonveri.path("prices");

		for (JsonNode fiyat : fiyatlar){

			String productName = fiyat.path("productName").asText();
			double productprice = fiyat.path("amount").asDouble();

			System.out.println(productName + " : " + productprice);

		}

	}

}