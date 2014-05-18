package com.example.sisctrldiabetes.async;

import java.util.ArrayList;

import com.example.sisctrldiabetes.classes.Food;
import com.example.sisctrldiabetes.classes.User;
import com.example.sisctrldiabetes.soap.CallSoap;

import android.os.AsyncTask;

public class AsyncCallWSiu extends AsyncTask<ArrayList<String>, Void, ArrayList<String>>{

	@Override
	protected ArrayList<String> doInBackground(ArrayList<String>... passing) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> passed = passing[0];
		CallSoap cs = new CallSoap();
		
		int resp = 0;
		
		resp = cs.insertUser(passed.get(0), 
									passed.get(1),
									Double.parseDouble(passed.get(2)), 
									Double.parseDouble(passed.get(3)), 
									Double.parseDouble(passed.get(4)));	
		result.add(String.valueOf(resp));
		
		System.out.println("agrego en la bd");
		
		return result;
	}
	
	@Override
    protected void onPostExecute(ArrayList<String> st) {
		super.onPostExecute(st);
    }
	

}
