package com.example.sisctrldiabetes.soap;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.FormatterClosedException;

import org.ksoap2.SoapEnvelope; 
import org.ksoap2.serialization.PropertyInfo; 
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.sisctrldiabetes.classes.Food;
import com.example.sisctrldiabetes.classes.listFood;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;

public class CallSoap {
	
	public final String SOAP_ACTION = "http://tempuri.org/ObterAlimentos";
	
	public  final String OPERATION_NAME = "ObterAlimentos"; 

	public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

	public  final String SOAP_ADDRESS = "http://172.16.13.144/ws/appService.asmx";
	
	//private Gson gson;
	
	public CallSoap() 
	{ 
	}
	public ArrayList<Food> getFoods()
	{
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		
		String str_result="";
		
		try
		{
			httpTransport.call(SOAP_ACTION, envelope);
			Object soapObject= (Object)envelope.getResponse();
			if(soapObject != null){
				str_result = soapObject.toString();
				System.out.println("result:"+str_result);
				Type type = new TypeToken<Collection<Food>>(){}.getType();
				Gson gson = new Gson();
				ArrayList<Food> rslt = gson.fromJson(str_result, type);
					for(Food fd : rslt){
						System.out.println("insert"+fd.getName()+","+fd.getWeight()+","+fd.getCarb()+","+fd.getFiber());
					}
				
				return rslt;
			}
		}
		catch (Exception exception)
		{
			System.out.println("error:"+exception.toString());
		}
		ArrayList<Food> rslt = new ArrayList<Food>();
		return rslt;
	}

}
