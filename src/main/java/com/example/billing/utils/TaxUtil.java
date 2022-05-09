package com.example.billing.utils;

public class TaxUtil {
	
	public static double roundToHigherCent(double d){
		int decimal = (int) d;
		double fractional = d - decimal;
		int cent = ((int) (fractional * 100));
		int centUnit = cent % 10;
		int centDecimal = cent / 10;

		double roudFractional = ( (double)centDecimal / 10) ;
		if(centUnit!=0) {
			roudFractional += centUnit<= 5 ? 0.05 : 0.1;			
		}
		
		return decimal + roudFractional;
	}

}
