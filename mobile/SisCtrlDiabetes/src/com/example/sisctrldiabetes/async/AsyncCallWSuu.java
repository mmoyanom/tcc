package com.example.sisctrldiabetes.async;

import java.util.ArrayList;

import com.example.sisctrldiabetes.soap.CallSoap;

import android.os.AsyncTask;

public class AsyncCallWSuu extends AsyncTask<ArrayList<String>, Void, ArrayList<String>>{

	@Override
	protected ArrayList<String> doInBackground(ArrayList<String>... passing) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> passed = passing[0];
		CallSoap cs = new CallSoap();
		
		int resp = 0;
		System.out.println("idDB:"+Integer.parseInt(passed.get(0)));
		System.out.println(passed.get(1));
		System.out.println(passed.get(2));
		System.out.println(Double.parseDouble(passed.get(3)));
		System.out.println(Double.parseDouble(passed.get(4)));
		System.out.println(Double.parseDouble(passed.get(5)));
		
		resp = cs.updateUser(Integer.parseInt(passed.get(0)), 
									passed.get(1),
									passed.get(2),
									Double.parseDouble(passed.get(3)), 
									Double.parseDouble(passed.get(4)), 
									Double.parseDouble(passed.get(5)));
		System.out.println("resp in AsyncCallWSuu"+resp);
		result.add(String.valueOf(resp));
		
		System.out.println("actualizo en la bd");
		
		return result;
	}
	
	@Override
    protected void onPostExecute(ArrayList<String> st) {
		super.onPostExecute(st);
    }

}
