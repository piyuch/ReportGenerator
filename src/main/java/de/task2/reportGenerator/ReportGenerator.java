package de.task2.reportGenerator;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * This class reads a json file and generate an excel sheet report that contains the 
 * required hotel data in the excel sheet.
 * 
 * @author piyush
 */
public class ReportGenerator implements HotelData {
	
    public static void main( String[] args ) throws FileNotFoundException, IOException, ParseException, java.text.ParseException
    {
    	
    	ClassLoader classLoader = ReportGenerator.class.getClassLoader();
	    File jsonFileInputFile = new File(classLoader.getResource("hotelrates.json").getFile()); 
	    File excelFileOutputFile = new File(classLoader.getResource("HotelReport.xlsx").getFile()); 
    	
    	    JSONParser parser = new JSONParser();
    	    Object jsonObject = parser.parse(new FileReader(jsonFileInputFile));  
        	JSONObject json = (JSONObject) jsonObject;
            
      		retrieve_BreakfastIncluded_TargetDay(json,breakfastIncludedList,targetDayList);	   
      		retrieve_Price_RateName_Adults_LengthOfStay(json, priceList, currencyList, numericPriceList, rateNameList, adultsList, lengthOfStayList);   
      		retrieve_ArrivalDate_DepartureDate(targetDayList, arrivedDateList, departureDateList, lengthOfStayList);
      		
    	    // Create object of FileOutputStream
    	    FileOutputStream fout = new FileOutputStream(excelFileOutputFile);
    	    @SuppressWarnings("resource")
			HSSFWorkbook workBook = new HSSFWorkbook();
    	    // Create the spreadsheet
    	    HSSFSheet sheet = workBook.createSheet("Sample sheet");
    	    // Create style
    	    HSSFCellStyle style = workBook.createCellStyle();
    	    // Create font
    	    HSSFFont font = workBook.createFont();
    	    // Create the first row
    	    HSSFRow row = sheet.createRow(0);
    	    
    	    writeDataToExcelsRowColumn(breakfastIncludedList, arrivedDateList, departureDateList, currencyList, 
    	    		numericPriceList, rateNameList, adultsList, sheet,row, style, font);  
    	    
    	    workBook.write(fout);
    	    fout.close();
    	    Desktop.getDesktop().open(new File(excelFileOutputFile.toString()));	   
    	}


	private static void writeDataToExcelsRowColumn(ArrayList<String> list, ArrayList<String> listArrivedDate,
			ArrayList<String> listDepartureDate, ArrayList<String> list7, ArrayList<String> list8,
			ArrayList<String> list9, ArrayList<String> list10, HSSFSheet sheet, HSSFRow row, HSSFCellStyle style, HSSFFont font) {
		
			style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFont(font);
			font.setFontName(HSSFFont.FONT_ARIAL);
			font.setFontHeightInPoints((short)10);
			font.setBold(true);
			
			HSSFCell cell;
			cell = row.createCell(0);
			cell.setCellValue(new HSSFRichTextString("Arrival_Date"));
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(new HSSFRichTextString("Departure_Date"));
			cell.setCellStyle(style);
			 
			cell = row.createCell(2);
			cell.setCellValue(new HSSFRichTextString("Price"));
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue(new HSSFRichTextString("Currency"));
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(new HSSFRichTextString("RateName"));
			cell.setCellStyle(style);
			
			cell = row.createCell(5);
			cell.setCellValue(new HSSFRichTextString("Adults"));
			cell.setCellStyle(style);
			
			cell = row.createCell(6);
			cell.setCellValue(new HSSFRichTextString("Breakfast_Included"));
			cell.setCellStyle(style);
			
			int rowCount = 0;
			
			Cell  cells;
			Iterator<String> listBreakfast= list.iterator();
			Iterator<String> listCurrency= list7.iterator();
			Iterator<String> listPrice= list8.iterator();
			Iterator<String> listRateName= list9.iterator();
			Iterator<String> listAdults= list10.iterator();
			Iterator<String> listArrivalDate= listArrivedDate.iterator();
			Iterator<String> listDeparture = listDepartureDate.iterator();
				while (listBreakfast.hasNext()) {
				    rowCount++;   
				    Object list_break_fast = listBreakfast.next();
				    Object listC =listCurrency.next();
				    Object listPric = listPrice.next();
				    Object rateName= listRateName.next();
				    Object adults= listAdults.next();
				    Object arrivalDate= listArrivalDate.next();
				    Object departuteivalDate= listDeparture.next();
				    
				    Row row1 = sheet.createRow(rowCount);
				    
				    cells  = row1.createCell(0);
				   
				    cells.setCellValue(arrivalDate.toString());
				    
				    cells  = row1.createCell(1);
				       
				    cells.setCellValue(departuteivalDate.toString());
				    
				    cells  = row1.createCell(2);
				   
				    cells.setCellValue(listPric.toString());
				    
				    cells  = row1.createCell(3);
				   
				    cells.setCellValue(listC.toString());
				    
				    cells  = row1.createCell(4);
				       
				    cells.setCellValue(rateName.toString());
				    
				    cells  = row1.createCell(5);
				       
				    cells.setCellValue(adults.toString());
				    
				    cells  = row1.createCell(6);
				       
				    cells.setCellValue(list_break_fast.toString());
				        
				 }
	}

	private static void retrieve_BreakfastIncluded_TargetDay(JSONObject json,ArrayList<String> breakfastIncludedList, 
			ArrayList<String> targetDayList) {
			JSONArray jsonArray =  (JSONArray) json.get("hotelRates");
			  for(int n = 0; n < jsonArray.size(); n++)
				 {
				    JSONObject object = (JSONObject) jsonArray.get(n);
				    JSONArray a = (JSONArray) object.get("rateTags");
	                targetDayList.add(object.get("targetDay").toString());
				    for(int i = 0; i < a.size(); i++)
					   {
					     JSONObject jsonObj = (JSONObject) a.get(i);
					     String breakfastIncluded =jsonObj.get("shape").toString();
					      if (breakfastIncluded.equals("true")){
							  breakfastIncludedList.add("1");
							  } 
					      else {
							  breakfastIncludedList.add("0"); 
						     }
				       }   
				   }
	}

	private static void retrieve_Price_RateName_Adults_LengthOfStay(JSONObject json, ArrayList<JSONObject> priceList,
			ArrayList<String> currencyList, ArrayList<String> numericPriceList, ArrayList<String> rateNameList, ArrayList<String> adultsList,
			ArrayList<Integer> lengthOfStayList) {
		    JSONArray jsonArray =  (JSONArray) json.get("hotelRates");
		    for(int n = 0; n < jsonArray.size(); n++)
		    {
			   JSONObject priceObj= (JSONObject) jsonArray.get(n);
			   priceList.add((JSONObject) priceObj.get("price"));
			   rateNameList.add(priceObj.get("rateName").toString());
			   adultsList.add(priceObj.get("adults").toString());
			   lengthOfStayList.add(priceObj.get("los").hashCode()); 
		    }	  
		    for(int i = 0; i < priceList.size(); i++)
		    {
		      JSONObject jsonObj = (JSONObject) priceList.get(i);
		      currencyList.add(jsonObj.get("currency").toString());
		      numericPriceList.add(jsonObj.get("numericFloat").toString());
		    }
	}



	private static void retrieve_ArrivalDate_DepartureDate(ArrayList<String> targetDayList, ArrayList<String> arrivedDateList,
			ArrayList<String> departureDateList, ArrayList<Integer> lengthOfStayList) throws java.text.ParseException {
		    
		Iterator<String> iter = targetDayList.iterator();
		Iterator<Integer> iterlos = lengthOfStayList.iterator();
		  while (iter.hasNext()) {
			String arrivedDate = iter.next();
			Integer losInteger = iterlos.next();
			String formattedDate = formattingDate(arrivedDate);
			arrivedDateList.add(formattedDate);
		    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date = format.parse(formattedDate);    
		    Calendar c = Calendar.getInstance();
		    c.setTime(date);
		    c.add(Calendar.DATE, losInteger);
		    departureDateList.add(format.format(c.getTime()));
		   }
	}
    
    /**
     * This method formats the Date
     */
	private static String formattingDate(String dateInput) throws java.text.ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		Date date = format.parse(dateInput);
		format.applyPattern("yyyy-MM-dd");
		String formattedDate = format.format(date);
		return formattedDate;
	}
   
}
  

