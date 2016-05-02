package de.task2.reportGenerator;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public interface HotelData {
	
	ArrayList<String> breakfastIncludedList = new ArrayList<String>();
	ArrayList<String> targetDayList = new ArrayList<String>();
    ArrayList<String> arrivedDateList = new ArrayList<String>();
    ArrayList<String> departureDateList = new ArrayList<String>();
    ArrayList<JSONObject> priceList = new ArrayList<JSONObject>();
    ArrayList<String> currencyList = new ArrayList<String>();
    ArrayList<String> numericPriceList = new ArrayList<String>();
    ArrayList<String> rateNameList = new ArrayList<String>();
    ArrayList<String> adultsList = new ArrayList<String>();
    ArrayList<Integer> lengthOfStayList =  new ArrayList<Integer>();

}