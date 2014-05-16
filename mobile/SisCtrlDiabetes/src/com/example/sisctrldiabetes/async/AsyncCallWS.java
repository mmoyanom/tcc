package com.example.sisctrldiabetes.async;

import com.example.sisctrldiabetes.classes.listFood;
import com.example.sisctrldiabetes.soap.CallSoap;

import android.os.AsyncTask;

public class AsyncCallWS extends AsyncTask<listFood, Void, listFood>{

	@Override
	protected listFood doInBackground(listFood... params) {
		CallSoap cs = new CallSoap();
		listFood resp = cs.getFoods();
		return resp;
	}
	@Override
    protected void onPostExecute(listFood lf) {
       super.onPostExecute(lf);

    }
	

	

}
