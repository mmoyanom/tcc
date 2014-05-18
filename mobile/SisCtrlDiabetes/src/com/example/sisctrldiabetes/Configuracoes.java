package com.example.sisctrldiabetes;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.example.sisctrldiabetes.async.AsyncCallWSdl;
import com.example.sisctrldiabetes.async.AsyncCallWShl;
import com.example.sisctrldiabetes.async.AsyncCallWSuu;
import com.example.sisctrldiabetes.classes.D_Lunch;
import com.example.sisctrldiabetes.classes.Food;
import com.example.sisctrldiabetes.classes.H_Lunch;
import com.example.sisctrldiabetes.classes.Handler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Configuracoes extends Activity implements OnClickListener{
	
	public Handler helper = new Handler(this);

	public void onCreate(Bundle icicle) {
		
		super.onCreate(icicle);
		setContentView(R.layout.configuracoes);		
		
		View btnSinc = findViewById(R.id.btnSinc);
		btnSinc.setOnClickListener(this);
	}
	
	public void onResume(){
	    super.onResume();
	    Handler helper = new Handler(this);
		String user = helper.getUser();
		if (user == "empty"){
			Intent it = new Intent(this, PerfilUsuario.class);
			startActivity(it);	
		}
	}

	@Override
	public void onClick(View v) {
		//Sincronizar
		 if(v.getId()==findViewById(R.id.btnSinc).getId())
		 {
			 System.out.println("entro sync");
			 ArrayList<H_Lunch> listHLunch =  new ArrayList<H_Lunch>();
			 listHLunch = helper.getAllLunchHtoSync();
			 
			 System.out.println("listHLunch cant."+listHLunch.size() );
			 
			 if (listHLunch.size()>0){
				 for (H_Lunch lh : listHLunch ) {
					 ArrayList<D_Lunch> listDLunch =  new ArrayList<D_Lunch>();
					 ArrayList<String> passing = new ArrayList<String>();
					 int uBD = helper.getIdDBUser();
					 ArrayList<String> HBD = new ArrayList<String>();
					 int hBD = 0;
					 System.out.println("get H_lunch BD local"+ String.valueOf(uBD)+","+lh.getIns()+","+
							 			lh.getGli()+","+lh.getCarb()+","+lh.getFiber()+","+lh.getDate());
					 
					 passing.add(String.valueOf(uBD));
					 passing.add(lh.getIns().replace(",", "."));
					 passing.add(lh.getGli().replace(",", "."));
					 passing.add(lh.getCarb().replace(",", "."));
					 passing.add(lh.getFiber().replace(",", "."));
					 passing.add(lh.getDate());
					
					 AsyncCallWShl task = new AsyncCallWShl();
				     task.execute(passing);
				     
				     try {
				    	 HBD =  task.get();
				    	 System.out.println("HBD: "+ HBD.get(0));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				     hBD = Integer.parseInt(HBD.get(0));
				     System.out.println("hBD :"+hBD);
				     
					 listDLunch = helper.getAllLunchD(lh.getId());
					 for (D_Lunch ld : listDLunch ) {
						 ArrayList<String> passingD = new ArrayList<String>();
						 System.out.println("get D_lunch BD local"+ String.valueOf(hBD)+","+String.valueOf(ld.getIdFood())+","+
						 			ld.getWeight()+","+ld.getCarb()+","+ld.getFiber());	
						 passingD.add(String.valueOf(hBD));
						 passingD.add(String.valueOf(ld.getIdFood()));
						 passingD.add(ld.getWeight().replace(",", "."));
						 passingD.add(ld.getCarb().replace(",", "."));
						 passingD.add(ld.getFiber().replace(",", "."));
						 
						 AsyncCallWSdl taskD = new AsyncCallWSdl();
					     taskD.execute(passingD);
					 }
					 
					 helper.updateLunchHStatusBD(lh.getId());
					 System.out.println("luch header updated status");
		 		}
				 
				 AlertDialog alertDialog = new AlertDialog.Builder(
						Configuracoes.this).create();
	 
			     // Setting Dialog Title
			     alertDialog.setTitle("Configurações");
			        
			     alertDialog.setButton3("OK", new DialogInterface.OnClickListener() {
						
				 @Override
				 	public void onClick(DialogInterface dialog, int which) {
					 finish(); 
				 	}
				 });
			     alertDialog.setMessage("Dados de alimentos consumidos salvados!");
			     alertDialog.show();
			 }else{
				 AlertDialog alertDialog = new AlertDialog.Builder(
							Configuracoes.this).create();
		 
				     // Setting Dialog Title
				     alertDialog.setTitle("Configurações");
				        
				     alertDialog.setButton3("OK", new DialogInterface.OnClickListener() {
							
					 @Override
					 	public void onClick(DialogInterface dialog, int which) {
						 finish(); 
					 	}
					 });
				     alertDialog.setMessage("Não tem dados para atualizar...");
				     alertDialog.show();
			 }
				 
			 
			 
			 
		 }
	}
}	
	
