package com.example.sisctrldiabetes.soap;
import org.ksoap2.SoapEnvelope; 
import org.ksoap2.serialization.PropertyInfo; 
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CallSoap {
	
	public final String SOAP_ACTION = "http://tempuri.org/ObterAlimentos";
	
	public  final String OPERATION_NAME = "ObterAlimentos"; 

	public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

	public  final String SOAP_ADDRESS = "http://localhost:61071//appService.asmx";
	
	public CallSoap() 
	{ 
	}
	public String getFoods()
	{
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response=null;
		try
		{
		httpTransport.call(SOAP_ACTION, envelope);
		response = envelope.getResponse();
		}
		catch (Exception exception)
		{
		response=exception.toString();
		}
		return response.toString();
	}

}
