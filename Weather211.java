/* 
 * CS211 Project 2 - Weather and Google Map API
 * Cecelia Thomas - cecelia.thomas@bellevuecollege.edu
 * Student ID: 202-515-782
 * 08.05.22 - Summer Quarter
 *
 * Collects weather information for a valid city name through openweathermap's API
 */

package project2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Weather211 {

	private static String nameOfCity;
	private static  ArrayList<String> weatherInfo211 = new ArrayList<>();
	
	// compiles real-time weather statistics, description, and similar information for an input city name
	public static boolean CityWeather (String cityName) throws Exception {
		boolean validCityName=false;
		
		weatherInfo211.clear();
		
		// try runs if the given city name is valid 
		try {
			nameOfCity = cityName;
			///Create a URL instance
			String firstPartURL = "https://api.openweathermap.org/data/2.5/weather?q=";
			String secondPartURL ="&appid=282b5cd9c73136545896769e827a6f7b"; 
			String theURL = firstPartURL + cityName + secondPartURL;
			URL url = new URL(theURL); 
			

			///Reads information from URL    
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

			//JSON parser object to parse read file
			JSONParser jsonParser = new JSONParser();
			//Read JSON file. All the data for the city is stored in “myObject"
			JSONObject myObject = (JSONObject)jsonParser.parse(br); 
		   
			// 1. add city name to the data structure 
			weatherInfo211.add(cityName);
			 
			// 2. Weather 
			JSONArray weatherArray = (JSONArray)myObject.get("weather");
			JSONObject w = (JSONObject) weatherArray.get(0);
			// get weather info from w
			String weatherNow =(String) w.get("description");
			// add weather info to the data structure (see 1. add city name…  above)
			weatherInfo211.add(weatherNow);    
			          
			// 3. Temp  
			// get temp from myObject
			double cityTemp = (double)((JSONObject) myObject.get("main")).get("temp");
			cityTemp = ((cityTemp - 273.15)*9)/5 + 32;
			String tempNow = "temp: "+ String.format("%.1f", cityTemp)+"\u00B0";
			// add temp to the data structure 
			weatherInfo211.add(tempNow);
			
			// 4. Temp_min  
			// get temp_min from myObject 
			double cityTempMin = (double)((JSONObject) myObject.get("main")).get("temp_min");
			cityTempMin = ((cityTempMin - 273.15)*9)/5 + 32;
			String tempLow="low: "+String.format("%.1f", cityTempMin)+"\u00B0";// add temp_min to the data structure 
			weatherInfo211.add(tempLow);

			// 5. Temp_high   
			// get temp_high from myObject 
			double cityTempHigh = (double)((JSONObject) myObject.get("main")).get("temp_max");
			cityTempHigh = ((cityTempHigh - 273.15)*9)/5 + 32;
			String tempHigh="high: "+String.format("%.1f", cityTempHigh)+"\u00B0";  // add temp_High to the data structure"
			weatherInfo211.add(tempHigh);

			// 6. Humidity 
			// get humidity from myObject  
			long cityHumidity = (long)((JSONObject) myObject.get("main")).get("humidity");
			String humidity = "humidity: "+ Long.toString(cityHumidity)+"%";
			// add humidity to the data structure 
			weatherInfo211.add(humidity);
			
			validCityName = true;
			
		} // catch if the given city name is not valid, does not let the program proceed
		catch (Exception ex) {
			validCityName=false;
			// this was to test if myWeatherApp would correctly prompt the user for other info
		}
		return validCityName;	
	}
	// returns city weather information collected through CityWeather in an ArrayList
	public static ArrayList <String>getCityWeatherNow() throws Exception {
		if(CityWeather(nameOfCity)) {
			return weatherInfo211;
		}
		return null;
	}
}

	
