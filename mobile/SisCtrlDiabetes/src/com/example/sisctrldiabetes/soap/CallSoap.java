package com.example.sisctrldiabetes.soap;


import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.FormatterClosedException;

import org.ksoap2.SoapEnvelope; 
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.MarshalDate;
import org.ksoap2.serialization.PropertyInfo; 
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.sisctrldiabetes.classes.Food;
import com.example.sisctrldiabetes.classes.MarshalDouble;
import com.example.sisctrldiabetes.classes.listFood;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.R.string;
import android.text.format.DateFormat;
import android.util.Log;

public class CallSoap {
	
	public final String SOAP_ACTION = "http://tempuri.org/ObterAlimentos";
	public final String SOAP_ACTION1 = "http://tempuri.org/CadastrarUsuario";
	public final String SOAP_ACTION2 = "http://tempuri.org/AtualizarUsuario";
	public final String SOAP_ACTION3 = "http://tempuri.org/insertLunchH";
	public final String SOAP_ACTION4 = "http://tempuri.org/insertLunchD";
	
	public  final String OPERATION_NAME = "ObterAlimentos";
	public  final String OPERATION_NAME1 = "CadastrarUsuario";
	public  final String OPERATION_NAME2 = "AtualizarUsuario";
	public  final String OPERATION_NAME3 = "insertLunchH";
	public  final String OPERATION_NAME4 = "insertLunchD";

	public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

	public  final String SOAP_ADDRESS = "http://192.168.0.13/ws/appService.asmx";
	
	
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
	
	public int insertUser(String name, String email,Double weight,Double size, Double insulina)
	{
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME1);
		
		PropertyInfo p2;
		p2 = new PropertyInfo();
		p2.name = "name";
		p2.type = string.class;
		request.addProperty(p2,name);
		
		PropertyInfo p3;
		p3 = new PropertyInfo();
		p3.name = "email";
		p3.type = int.class;
		request.addProperty(p3,email);
		
		PropertyInfo p4;
		p4 = new PropertyInfo();
		p4.name = "weight";
		p4.type = int.class;
		request.addProperty(p4,weight);
		
		PropertyInfo p5;
		p5 = new PropertyInfo();
		p5.name = "size";
		p5.type = int.class;
		request.addProperty(p5,size);
		
		PropertyInfo p6;
		p6 = new PropertyInfo();
		p6.name = "insulina";
		p6.type = int.class;
		request.addProperty(p6,insulina);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.implicitTypes = true;
		envelope.dotNet = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		envelope.setOutputSoapObject(request);
		
		MarshalDouble md =  new MarshalDouble();
		md.register(envelope);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		
		String str_result="";
		
		try
		{
			httpTransport.call(SOAP_ACTION1, envelope);
			Object soapObject= (Object)envelope.getResponse();
			if(soapObject != null){
				str_result = soapObject.toString();
				System.out.println("result:"+str_result);
				return Integer.parseInt(str_result);
			}
		}
		catch (Exception exception)
		{
			System.out.println("error:"+exception.toString());
			return 0;
		}
		return 1;
	}
	
	public int updateUser(int idDB,String name, String email,Double weight,Double size, Double insulina)
	{
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME2);
		
		PropertyInfo p1;
		p1 = new PropertyInfo();
		p1.name = "idDB";
		p1.type = int.class;
		request.addProperty(p1,idDB);
		
		PropertyInfo p2;
		p2 = new PropertyInfo();
		p2.name = "name";
		p2.type = string.class;
		request.addProperty(p2,name);
		
		PropertyInfo p3;
		p3 = new PropertyInfo();
		p3.name = "email";
		p3.type = int.class;
		request.addProperty(p3,email);
		
		PropertyInfo p4;
		p4 = new PropertyInfo();
		p4.name = "weight";
		p4.type = int.class;
		request.addProperty(p4,weight);
		
		PropertyInfo p5;
		p5 = new PropertyInfo();
		p5.name = "size";
		p5.type = int.class;
		request.addProperty(p5,size);
		
		PropertyInfo p6;
		p6 = new PropertyInfo();
		p6.name = "insulina";
		p6.type = int.class;
		request.addProperty(p6,insulina);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.implicitTypes = true;
		envelope.dotNet = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		envelope.setOutputSoapObject(request);
		
		MarshalDouble md =  new MarshalDouble();
		md.register(envelope);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		
		String str_result="";
		
		try
		{
			httpTransport.call(SOAP_ACTION2, envelope);
			Object soapObject= (Object)envelope.getResponse();
			if(soapObject != null){
				str_result = soapObject.toString();
				System.out.println("result:"+str_result);
				return Integer.parseInt(str_result);
			}
		}
		catch (Exception exception)
		{
			System.out.println("error:"+exception.toString());
			return 0;
		}
		return 1;
	}
	
	public int insertLunchH(int idUserBD, Double ins,Double gli,Double carb, Double fiber,String date)
	{
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME3);
		
		PropertyInfo p2;
		p2 = new PropertyInfo();
		p2.name = "idUserBD";
		p2.type = int.class;
		request.addProperty(p2,idUserBD);
		
		PropertyInfo p3;
		p3 = new PropertyInfo();
		p3.name = "insulina";
		p3.type = int.class;
		request.addProperty(p3,ins);
		
		PropertyInfo p4;
		p4 = new PropertyInfo();
		p4.name = "glicemia";
		p4.type = int.class;
		request.addProperty(p4,gli);
		
		PropertyInfo p5;
		p5 = new PropertyInfo();
		p5.name = "carb";
		p5.type = int.class;
		request.addProperty(p5,carb);
		
		PropertyInfo p6;
		p6 = new PropertyInfo();
		p6.name = "fiber";
		p6.type = int.class;
		request.addProperty(p6,fiber);
		
		/*PropertyInfo p7;
		p7 = new PropertyInfo();
		p7.name = "fecha";
		p7.type = String.class;
		request.addProperty(p7,date);*/
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.implicitTypes = true;
		envelope.dotNet = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		envelope.setOutputSoapObject(request);
		
		MarshalDouble md =  new MarshalDouble();
		md.register(envelope);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		
		String str_result="";
		
		try
		{
			httpTransport.call(SOAP_ACTION3, envelope);
			Object soapObject= (Object)envelope.getResponse();
			if(soapObject != null){
				str_result = soapObject.toString();
				System.out.println("result insertLunchH:"+str_result);
				return Integer.parseInt(str_result);
			}
		}
		catch (Exception exception)
		{
			System.out.println("error:"+exception.toString());
			return 0;
		}
		return 1;
	}
	
	public int insertLunchD(int idH,int idFood,Double weight,Double carb, Double fiber)
	{
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME4);
		
		PropertyInfo p2;
		p2 = new PropertyInfo();
		p2.name = "idH";
		p2.type = int.class;
		request.addProperty(p2,idH);
		
		PropertyInfo p3;
		p3 = new PropertyInfo();
		p3.name = "idFood";
		p3.type = int.class;
		request.addProperty(p3,idFood);
		
		PropertyInfo p4;
		p4 = new PropertyInfo();
		p4.name = "weight";
		p4.type = int.class;
		request.addProperty(p4,weight);
		
		PropertyInfo p5;
		p5 = new PropertyInfo();
		p5.name = "carb";
		p5.type = int.class;
		request.addProperty(p5,carb);
		
		PropertyInfo p6;
		p6 = new PropertyInfo();
		p6.name = "fiber";
		p6.type = int.class;
		request.addProperty(p6,fiber);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.implicitTypes = true;
		envelope.dotNet = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		envelope.setOutputSoapObject(request);
		
		MarshalDouble md =  new MarshalDouble();
		md.register(envelope);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		
		String str_result="";
		
		try
		{
			httpTransport.call(SOAP_ACTION4, envelope);
			Object soapObject= (Object)envelope.getResponse();
			if(soapObject != null){
				str_result = soapObject.toString();
				System.out.println("result insertLunchD:"+str_result);
				return Integer.parseInt(str_result);
			}
		}
		catch (Exception exception)
		{
			System.out.println("error:"+exception.toString());
			
			return 0;
		}
		return 1;
	}
	
	
}
