package com.example.sisctrldiabetes.soap;


import java.lang.reflect.Type;

import org.ksoap2.SoapEnvelope; 
import org.ksoap2.serialization.PropertyInfo; 
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.sisctrldiabetes.classes.listFood;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;

public class CallSoap {
	
	public final String SOAP_ACTION = "http://tempuri.org/ObterAlimentos";
	
	public  final String OPERATION_NAME = "ObterAlimentos"; 

	public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

	public  final String SOAP_ADDRESS = "http://localhost:61071/appService.asmx";
	
	private Gson gson;
	
	public CallSoap() 
	{ 
	}
	public listFood getFoods()
	{
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		
		try
		{
			httpTransport.call(SOAP_ACTION, envelope);
			Object soapObject= (Object)envelope.getResponse();
			if(soapObject != null){
				String str_result = soapObject.toString();
				Type type = new TypeToken<listFood>(){}.getType();
				listFood rslt = gson.fromJson(str_result, type);
				return rslt;
			}
		}
		catch (Exception exception)
		{
			System.out.println(exception.toString());
		}
		listFood rslt = new listFood();
		return rslt;
	}

}
