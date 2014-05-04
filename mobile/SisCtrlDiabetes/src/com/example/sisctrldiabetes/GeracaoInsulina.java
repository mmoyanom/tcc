package com.example.sisctrldiabetes;

import java.util.ArrayList;
import java.util.List;

import com.example.sisctrldiabetes.classes.Food;
import com.example.sisctrldiabetes.classes.Handler;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;

public class GeracaoInsulina extends Activity implements OnClickListener{
	
	private ListView listView;
	private TextView txtCarbTotal;
	private TextView txtGlicemia;
	private TextView txtInsCons;
	public static ArrayList<String> ArrayofName = new ArrayList<String>();
	Handler helper = new Handler(this);
	
	public void onCreate(Bundle icicle) {
		
		super.onCreate(icicle);
		
		setContentView(R.layout.geracaoinsulina);
		View btnConsultar = findViewById(R.id.btnConsultar);
		btnConsultar.setOnClickListener(this);
		View btnAgregar = findViewById(R.id.btnAgregar);
		btnAgregar.setOnClickListener(this);
		txtCarbTotal = (TextView)findViewById(R.id.lblTotal);
		txtGlicemia = (TextView)findViewById(R.id.txtGlicemia);
		txtInsCons = (TextView)findViewById(R.id.lblNumInsulina);
		
		
		
		System.out.println("Inserting foods...");
		
		helper.insertFood("food 1","100","300","400");
		helper.insertFood("food 2","90","120","340");
		helper.insertFood("food 3","80","150","120");
		helper.insertFood("food 4","70","230","130");
		
		System.out.println("Reading all foods..");
        
		List<Food> foods = helper.getAllFoods();
		
		for (Food fd : foods) {
            String log = "Id: "+fd.get_id()+" ,Name: " + fd.get_name();
                // Writing Contacts to log
        System.out.println("Read: "+ log);
		}
        try {
        	listView = (ListView)findViewById(R.id.listView1);
        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(GeracaoInsulina.this,
                    android.R.layout.simple_list_item_multiple_choice, ArrayofName);
            
            listView.setAdapter(adapter);

		} catch (Exception e) {
			System.out.println("mensaje de error:"+e.getMessage());
		}
        System.out.println("paso fill gridview");
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
    				int position, long id) {   
					
					int cntChoice = listView.getCount();
					String checked = "";
					String unchecked = "";
					SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
					int carbTotal = 0;
					txtCarbTotal.setText("0");
					txtInsCons.setText("0");
					for(int i = 0; i < cntChoice; i++)
					{
						 
					     if(sparseBooleanArray.get(i) == true) 
					     {
					         checked += listView.getItemAtPosition(i).toString() + "\n";
					         String item = listView.getItemAtPosition(i).toString();
					         int iFood = Integer.parseInt(item.substring(0,item.indexOf(".")));
					         System.out.println("iFood:"+iFood);
					         carbTotal += helper.getCarbById(iFood);
					         System.out.println("carbTotal:"+carbTotal);
					         txtCarbTotal.setText(String.valueOf(carbTotal));
					         
					         int glicemia = 0;
					         if (Integer.parseInt(txtGlicemia.getText().toString())>120){
					        	 glicemia = Integer.parseInt(txtGlicemia.getText().toString()) - 120;
					         }else{
					        	 glicemia = Integer.parseInt(txtGlicemia.getText().toString());
					         }
					         int insulina = 0;
					         int insCons = 0;
					         int insMedia = helper.getInsulinaMedia();
					         insulina = glicemia / (1800/insMedia);
					         insCons = insulina + (carbTotal/(1800/insMedia));
					         System.out.println("insCons:"+insCons);
					         txtInsCons.setText(String.valueOf(insCons));
					         
					         
					         
					         
					         
					     }
					     else  if(sparseBooleanArray.get(i) == false) 
					     {
					         unchecked+= listView.getItemAtPosition(i).toString() + "\n";
					     }

					 }
					
					System.out.println("checked:" + checked);
					System.out.println("unchecked:" + unchecked);
    			   }
        });

			String user = helper.getUser();
			if (user == "empty"){
				Intent it = new Intent(this, PerfilUsuario.class);
				startActivity(it);	
			}
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
		//Consular
		 if(v.getId()==findViewById(R.id.btnConsultar).getId())
		 {
			System.out.println("entro consultar");
	 	 }
		//Agregar
		 if(v.getId()==findViewById(R.id.btnAgregar).getId())
		 {
			System.out.println("entro agregar");
	 	 }
		
	}
	
}
