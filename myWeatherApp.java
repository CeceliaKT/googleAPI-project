/* 
 * CS211 Project 2 - Weather and Google Map API
 * Cecelia Thomas - cecelia.thomas@bellevuecollege.edu
 * Student ID: 202-515-782
 * 08.05.22 - Summer Quarter
 *
 * Accepts user inputs to present weather information and a map of a given city, utilizing
 * Weather211
 */

package project2;

import java.util.ArrayList;
import java.util.Scanner;

public class myWeatherApp {

static Scanner consol = new Scanner(System.in);

private static ArrayList<String> weatherInfo = new ArrayList<>();
static String mapType;
static int zoom;
	// main method prints a header and runs other two methods in myWeatherApp
	public static void main(String[] args) throws Exception {
		System.out.println("Welcome to Weather 211 - Summer 2022");
		System.out.println();
	   
		inputCityName();
		getWeatherInfo();
	}
	// asks for, receives, and stores inputs for city name, zoom level of map, and map type
	public static void inputCityName() throws Exception{ 
		boolean validCityName=false; 
		while (!validCityName) {
	 
			System.out.println("Input a city name:");
			String city = consol.nextLine();
			System.out.println();
	    
			boolean valid =  Weather211.CityWeather(city);
	    
			if (valid) { 
				
				// ask for map type (roadmap, satellite)
				System.out.println("Select map type: 1) roadmap 2) satellite");
				int map = consol.nextInt();
					
				if (map == 1) {
					mapType = "roadmap";
				} 
				else if (map == 2) {
					mapType = "satellite";
				}
				
				// ask for zoom level (0 ~21)
				System.out.println("Select zoom level of the map: 0 ~ 21 (default=14) ");
					zoom = consol.nextInt();
				
				System.out.println("Current Weather [" + city +"]\n");
				break;
			} else {
				System.out.println("Invalid city name. Type again.\n"); 
			}
		}
	}
	/* this method calls Weather211 to collect weather information and prints it
	 then, it calls Map211 to create a map of the city*/
	public static void getWeatherInfo() throws Exception {
		
		weatherInfo=Weather211.getCityWeatherNow();
			 
		// print text version
		for (int i=0; i<weatherInfo.size(); i++) {
			System.out.println(weatherInfo.get(i));
		}
			 
		// call Map211
		new Map211(weatherInfo, mapType, zoom);
	}
}
