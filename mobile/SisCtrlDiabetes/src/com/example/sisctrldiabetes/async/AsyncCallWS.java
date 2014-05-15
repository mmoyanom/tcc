package com.example.sisctrldiabetes.async;

import com.example.sisctrldiabetes.soap.CallSoap;

import android.os.AsyncTask;

public class AsyncCallWS extends AsyncTask<Void, Void, Void>{

	@Override
	protected Void doInBackground(Void... params) {
		CallSoap cs = new CallSoap();
		String resp = cs.getFoods();
		System.out.println(resp);
		return null;
	}
	

	

}
