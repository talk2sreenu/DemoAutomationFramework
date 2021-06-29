package com.demo.base;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtils {

	public static Date getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static String getEncodedValue(String tempvalue) {
		String encodedVal = tempvalue;

		try {
			encodedVal = URLEncoder.encode(encodedVal, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		return encodedVal;
	}
	
	public static String getDecodedValue(String encodeVal) {
		String decodeVal = encodeVal;
		
		try {
			decodeVal = URLDecoder.decode(decodeVal, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decodeVal;
	}
	
	public static String dateTimeGenerate(){
	    Format formatter = new SimpleDateFormat("YYYYMMdd_HHmmssSSS");
	    Date date = new Date(System.currentTimeMillis());
	   return formatter.format(date);
	}
}
