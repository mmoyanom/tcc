package com.example.sisctrldiabetes.async;

import java.util.ArrayList;

import com.example.sisctrldiabetes.MainActivity;
import com.example.sisctrldiabetes.classes.Food;
import com.example.sisctrldiabetes.soap.CallSoap;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class AsyncCallWSal extends AsyncTask<ArrayList<Food>, Void, ArrayList<Food>> {
	public AsyncResponse delegate = null;
	ProgressDialog dialog;
	
	public AsyncCallWSal(MainActivity activity) {
        dialog = new ProgressDialog(activity);
    }
	
	@Override
    protected void onPreExecute() {
		super.onPreExecute();
		System.out.println("entro onPreExecute");
        this.dialog.setTitle("Baixando alimentos...");
        this.dialog.setMessage("Aguardar por favor...");
        this.dialog.setIndeterminate(true);
        this.dialog.show();
    }
	
	@Override
	protected ArrayList<Food> doInBackground(ArrayList<Food>... params) {
		CallSoap cs = new CallSoap();
		ArrayList<Food> resp = cs.getFoods();
		return resp;
	}
	
	
	@Override
    protected void onPostExecute(ArrayList<Food> lf) {
		System.out.println("entro onPostExecute");
		if (dialog.isShowing()) {
            dialog.dismiss();
        }
		
		delegate.processFinish(lf);
		
    }
	

}
