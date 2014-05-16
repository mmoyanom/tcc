package com.example.sisctrldiabetes.async;

import java.util.ArrayList;

import com.example.sisctrldiabetes.classes.Food;
import com.example.sisctrldiabetes.classes.listFood;
import com.example.sisctrldiabetes.soap.CallSoap;

import android.os.AsyncTask;

public class AsyncCallWS extends AsyncTask<ArrayList<Food>, Void, ArrayList<Food>>{

	@Override
	protected ArrayList<Food> doInBackground(ArrayList<Food>... params) {
		CallSoap cs = new CallSoap();
		ArrayList<Food> resp = cs.getFoods();
		return resp;
	}
	@Override
    protected void onPostExecute(ArrayList<Food> lf) {
       super.onPostExecute(lf);

    }
	

	

}
