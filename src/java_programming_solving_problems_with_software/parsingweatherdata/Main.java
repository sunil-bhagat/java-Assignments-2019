package parsingweatherdata;

import parsingweatherdata.weather.Weather;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Weather weather = new Weather();
        //String output = weather.coldestHourInFile("src/parsingweatherdata/nc_weather/2012/weather-2012-01-01.csv");
        //System.out.println(output);
       // String s = weather.fileWithColdestTemperature("src/parsingweatherdata/nc_weather/2014");
       // String s = weather.lowestHumidityInFile("src/parsingweatherdata/nc_weather/2014/weather-2014-01-01.csv");
      // String s = weather.lowestHumidityInManyFiles("src/parsingweatherdata/nc_weather/2014");
        String s = weather.averageTemperatureWithHumidityInFile("src/parsingweatherdata/nc_weather/2014/weather-2014-03-20.csv",80);
        System.out.println(s);
    }
}
