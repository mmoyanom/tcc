package com.example.sisctrldiabetes;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;



import com.example.sisctrldiabetes.async.AsyncCallWS;
import com.example.sisctrldiabetes.async.AsyncCallWSal;
import com.example.sisctrldiabetes.async.AsyncResponse;
import com.example.sisctrldiabetes.classes.Food;
import com.example.sisctrldiabetes.classes.Handler;
import com.example.sisctrldiabetes.classes.listFood;
import com.example.sisctrldiabetes.soap.CallSoap;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener{
	
	public Handler helper = new Handler(this);
	@Override
	protected void onCreate(Bundle icicle) {
		
		super.onCreate(icicle);
		
		setContentView(R.layout.activity_main);
		
		/*Button Perfil usuario*/			
		 View btnPerfilUsuario = findViewById(R.id.btnPerfilUsuario);
		 btnPerfilUsuario.setOnClickListener(this);
		 
		 /*Button geracao de insulina*/	
		 View btnGeracaoInsulina = findViewById(R.id.btnGeracaoInsulina);
		 btnGeracaoInsulina.setOnClickListener(this);
		 
		 /*Button Relatorios*/
		 View btnRelatorios = findViewById(R.id.btnRelatorios);
		 btnRelatorios.setOnClickListener(this);
		 		 
		 /*Button Lembretes*/
		 View btnLembretes = findViewById(R.id.btnLembretes);
		 btnLembretes.setOnClickListener(this);
		 	 
		 /*Button Configuracoes*/
		 View btnConfiguracoes = findViewById(R.id.btnConfiguracoes);
		 btnConfiguracoes.setOnClickListener(this);
		 
			System.out.println("Inserting foods...");
			AsyncCallWS task = new AsyncCallWS();
	        task.execute();
	        
			ArrayList<Food> result = new ArrayList<Food>();
	        try {
	        	result =  task.get();
				for(Food fd : result){
					helper.insertFood(fd.getName(),fd.getWeight(),fd.getCarb(),fd.getFiber());
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        /*
			helper.insertFood("food 1","100","300","10");
			helper.insertFood("food 2","90","120","15");
			helper.insertFood("food 3","80","150","20");
			helper.insertFood("food 4","70","230","30");
			*/
		
	}
	
	public void onResume(){
	    super.onResume();
		String user = helper.getUser();
		if (user == "empty"){
			Intent it = new Intent(this, PerfilUsuario.class);
			startActivity(it);	
		}
	}

	public void onClick(View v) {		
		
		//Perfil Usuario
		 if(v.getId()==findViewById(R.id.btnPerfilUsuario).getId())
		 {
			 Intent it = new Intent(this, PerfilUsuario.class);
			 startActivity(it);
	 	 }

		 //Geracao de insulina		
		 if(v.getId()==findViewById(R.id.btnGeracaoInsulina).getId())
		 {
			 Intent it = new Intent(this, GeracaoInsulina.class);
			 startActivity(it);
	 	 }
		 
		 //Relatorios
		 if(v.getId()==findViewById(R.id.btnRelatorios).getId())
		 {
			 Intent it = new Intent(this, Relatorios.class);
			 startActivity(it);
	 	 }
		 		 
		 //Lembretes
		 if(v.getId()==findViewById(R.id.btnLembretes).getId())
		 {
			 Intent it = new Intent(this, Lembretes.class);
			 startActivity(it);
	 	 }
		 
		 //configuracoes
		 if(v.getId()==findViewById(R.id.btnConfiguracoes).getId())
		 {
			 Intent it = new Intent(this, Configuracoes.class);
			 startActivity(it);
	 	 }

	}
	/*
	@Override
	public void processFinish(ArrayList<Food> lsFoods) {
		System.out.println("Inserting foods...");
		try {
			for(Food fd : lsFoods){
				helper.insertFood(fd.getName(),fd.getWeight(),fd.getCarb(),fd.getFiber());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	*/
}
