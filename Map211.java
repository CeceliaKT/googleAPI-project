/* 
 * CS211 Project 2 - Weather and Google Map API
 * Cecelia Thomas - cecelia.thomas@bellevuecollege.edu
 * Student ID: 202-515-782
 * 08.05.22 - Summer Quarter
 *
 * Generates and HTML file containing a city map of specified type and zoom level, and displays
 * weather information for that region
 */

package project2;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Map211 {

//Data Structures (minimum requirement)
static String html;
static String weather;
static String mapFileName="myMap.html";
	//takes in and cleans data from Weather211 and myWeatherApp as parameters to write onto the HTML file
	Map211 (ArrayList<String> weatherInfo, String mapType, int zoom) throws IOException {

		String city=weatherInfo.get(0);

		// Create a single string data using weatherInfo
		// eg) BELLEVUE | clear sky | temp: 80.6° | low: 72.2° | high: 84.7° | humidity: 56% |

		weather= " "+city.toUpperCase()+"   | " + weatherInfo.get(1)+"   | " + weatherInfo.get(2)+"   | " + weatherInfo.get(3)+"   | " + weatherInfo.get(4)+"   | " + weatherInfo.get(5);

		// write a HTML file
		writeHTML(weather,city, mapType, zoom);

		// run html file from java code
		String url = mapFileName;   // you can find this html file in the project folder
		File htmlFile = new File(url);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}
	//writes weather information onto a Google API given map in an HTML file
	public static void writeHTML(String weatherNow, String city, String mapType, int zoom) {
		html="<!DOCTYPE html>"
		+ "<html>"
		+ "<body>"
		+ "<h2>"
		+ weatherNow
		+ "</h2>"
		+ "<iframe"
		+ "  width=1200"
		+ "  height=900"
		+ "  style=border:0"
		+ "  loading=lazy"
		+ "  allowfullscreen"
		+ "  referrerpolicy=\"no-referrer-when-downgrade\""
		+ "src=\"https://www.google.com/maps/embed/v1/place?key=AIzaSyDAZkxXz9sIq9kB4REbPUHbEw-_-GO1BXw&q="+ city +"&zoom="+ zoom +"&maptype=" + mapType+"\""
		+ "</iframe>"
		+ "</body>"
		+ "</html>";
		
		File f= new File (mapFileName);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(html);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
