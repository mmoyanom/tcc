package com.example.sisctrldiabetes;

import java.util.ArrayList;
import java.util.List;

import com.example.sisctrldiabetes.classes.Food;
import com.example.sisctrldiabetes.classes.Handler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;

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
		 
		 
			String user = helper.getUser();
			if (user == "empty"){
				Intent it = new Intent(this, PerfilUsuario.class);
				startActivity(it);	
			}
			
			System.out.println("Inserting foods...");
			
			helper.insertFood("food 1","100","300","400");
			helper.insertFood("food 2","90","120","340");
			helper.insertFood("food 3","80","150","120");
			helper.insertFood("food 4","70","230","130");
		
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
	
	
	
}
