package com.example.sisctrldiabetes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.sisctrldiabetes.classes.Food;
import com.example.sisctrldiabetes.classes.Handler;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.text.format.DateFormat;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;

public class GeracaoInsulina extends Activity implements OnClickListener{
	
	private ListView listView;
	private TextView txtCarbTotal;
	private TextView txtFiberTotal;
	private TextView txtFiberTotalGerado;
	private TextView txtGlicemia;
	private TextView txtInsCons;
	private TextView txtNomAli;
	private TextView txtGramas;
	private TextView txtCarb;
	public static ArrayList<String> ArrayofName = new ArrayList<String>();
	Handler helper = new Handler(this);
	
	public void onCreate(Bundle icicle) {
		
		super.onCreate(icicle);
		
		setContentView(R.layout.geracaoinsulina);
		View btnConsultar = findViewById(R.id.btnConsultar);
		btnConsultar.setOnClickListener(this);
		View btnAgregar = findViewById(R.id.btnAgregar);
		btnAgregar.setOnClickListener(this);
		
		txtFiberTotal = (TextView)findViewById(R.id.txtFiberCant);
		txtFiberTotalGerado = (TextView)findViewById(R.id.lblTotalFibr);
		txtCarbTotal = (TextView)findViewById(R.id.lblTotal);
		txtGlicemia = (TextView)findViewById(R.id.txtGlicemia);
		txtInsCons = (TextView)findViewById(R.id.lblNumInsulina);
		
		txtNomAli = (TextView)findViewById(R.id.txtNomeAlimento);
		txtGramas = (TextView)findViewById(R.id.txtGramas);
		txtCarb = (TextView)findViewById(R.id.txtCarbCant);
		
		listView = (ListView)findViewById(R.id.listView1);
        listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
    				int position, long id) {   
					if(ArrayofName.isEmpty()){
						System.out.println("o usuario quer seleccionar uma lista vacia, fdp!");
					}else {
						int cntChoice = listView.getCount();
						String checked = "";
						String unchecked = "";
						SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
						Double carbTotal = 0.0;
						Double fiberTotal = 0.0;
						txtCarbTotal.setText("0");
						txtFiberTotalGerado.setText("0");
						txtInsCons.setText("0");
						for(int i = 0; i < cntChoice; i++)
						{
							 
						     if(sparseBooleanArray.get(i) == true) 
						     {
						    	 Double fiberAct = 0.0;
						         checked += listView.getItemAtPosition(i).toString() + "\n";
						         String item = listView.getItemAtPosition(i).toString();
						         int iFood = Integer.parseInt(item.substring(0,item.indexOf(".")));
						         System.out.println("iFood:"+iFood);
						         carbTotal += helper.getCarbById(iFood);
						         fiberAct = helper.getFiberById(iFood);
						         
						         if (fiberAct > 0.5){
						        	 fiberTotal += fiberAct; 
						         }
						         
						         System.out.println("carbTotal:"+carbTotal);
						         
						         txtCarbTotal.setText(String.valueOf(carbTotal));
						         txtFiberTotalGerado.setText(String.valueOf(fiberTotal));
						         
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
						         if (fiberTotal > 0){
						        	 carbTotal = carbTotal - fiberTotal;
						         }
						         insCons = (int)(insulina + (carbTotal/(1800/insMedia)));
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

		String user = helper.getUser();
		if (user == "empty"){
			Intent it = new Intent(this, PerfilUsuario.class);
			startActivity(it);	
		}
	}

	@Override
	public void onClick(View v) {
		//Consultar
		 if(v.getId()==findViewById(R.id.btnConsultar).getId())
		 {
			if (txtGlicemia.getText().toString().trim().matches("")){
				Toast.makeText(this, "Você não inseriu a glicemia", Toast.LENGTH_SHORT).show();
			    return;
			}else {
				System.out.println("entro consultar");
				
				 int glicemia = 0;
				 int glicemiaVal = 0; // glicemia para evaluar
		         int insulina = 0;
		         int insCons = 0;
		         int insMedia = helper.getInsulinaMedia();
		         
		         glicemiaVal = Integer.parseInt(txtGlicemia.getText().toString());
		         
		         if (Integer.parseInt(txtGlicemia.getText().toString())>120){
		        	 glicemia = Integer.parseInt(txtGlicemia.getText().toString()) - 120;
		        	 insCons = glicemia / (1800/insMedia);
		         }else{
		        	 insCons = 0;
		         }
		         
		         txtInsCons.setText(String.valueOf(insCons));
		         
		         System.out.println("Reading all foods..");
		         List<Food> foods = null;
		         
		         if (glicemiaVal>120){
		        	 foods = helper.getAllFoodsAsc();
		         }else if(glicemiaVal<80){
		        	 foods = helper.getAllFoodsDesc();
		         }else if(glicemiaVal>=80 && glicemiaVal<=120){
		        	 foods = helper.getAllFoods();
		         }	
		 		
		 		for (Food fd : foods) {
		             String log = "Id: "+fd.get_id()+" ,Name: " + fd.get_name();
		                 // Writing Contacts to log
		         System.out.println("Read: "+ log);
		 		}
		         try {
		         	
		         	ArrayAdapter<String> adapter = new ArrayAdapter<String>(GeracaoInsulina.this,
		                     android.R.layout.simple_list_item_multiple_choice, ArrayofName);
		             
		         	listView.setAdapter(null);
		             listView.setAdapter(adapter);
		             adapter.notifyDataSetChanged();

		 		} catch (Exception e) {
		 			System.out.println("mensaje de error:"+e.getMessage());
		 		}
		         System.out.println("paso fill gridview");
			}

	 	 }
		 
		//Agregar
		 if(v.getId()==findViewById(R.id.btnAgregar).getId())
		 {
			 System.out.println("entro agregar");
			 helper.insertFood(txtNomAli.getText().toString(),
					 		   txtGramas.getText().toString(),
					 		  txtCarb.getText().toString(),
					 		   txtFiberTotal.getText().toString());
			 System.out.println("agrego food");
			 List<Food> foods = helper.getAllFoods();
			 
	        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(GeracaoInsulina.this,
	                    android.R.layout.simple_list_item_multiple_choice, ArrayofName);
	            
	        	listView.setAdapter(null);
	            listView.setAdapter(adapter);
	            System.out.println("actualizar lista");
	            int lastPos = listView.getCount()-1;
	            listView.setItemChecked(lastPos, true);
	            int cntChoice = listView.getCount();
				String checked = "";
				String unchecked = "";
				SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
				Double carbTotal = 0.0;
				Double fiberTotal = 0.0;
				txtCarbTotal.setText("0");
				txtFiberTotalGerado.setText("0");
				txtInsCons.setText("0");
				for(int i = 0; i < cntChoice; i++)
				{
					 
				     if(sparseBooleanArray.get(i) == true) 
				     {
				    	 Double fiberAct = 0.0;
				         checked += listView.getItemAtPosition(i).toString() + "\n";
				         String item = listView.getItemAtPosition(i).toString();
				         int iFood = Integer.parseInt(item.substring(0,item.indexOf(".")));
				         System.out.println("iFood:"+iFood);
				         carbTotal += helper.getCarbById(iFood);
				         fiberAct = helper.getFiberById(iFood);
				         if (fiberAct > 0.5){
				        	 fiberTotal += fiberAct; 
				         }
				          
				         System.out.println("carbTotal:"+carbTotal);
				         txtCarbTotal.setText(String.valueOf(carbTotal));
				         txtFiberTotalGerado.setText(String.valueOf(fiberTotal));
				         
				         int glicemia = 0;
				         int insulina = 0;
				         int insCons = 0;
				         int insMedia = helper.getInsulinaMedia();
				         
				         if (Integer.parseInt(txtGlicemia.getText().toString())>120){
				        	 glicemia = Integer.parseInt(txtGlicemia.getText().toString()) - 120;
				        	 insulina = glicemia / (1800/insMedia);
				        	 if (fiberTotal > 0){
					        	 carbTotal = carbTotal - fiberTotal;
					         }
					         insCons = (int)(insulina + (carbTotal/(1800/insMedia)));
				         }else{
					         insCons = (int)(carbTotal/(1800/insMedia));
				         }
				         
				         
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
		 
		//Salvar
		 if(v.getId()==findViewById(R.id.btnSalvar).getId())
		 {
			 SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
			 
			 if(ArrayofName.isEmpty())
			 {
				 Toast.makeText(this, "Você não inseriu os alimentos", Toast.LENGTH_SHORT).show();
				 return;
			 }else if(sparseBooleanArray == null){
				 Toast.makeText(this, "Você não selecionou nenhum alimento", Toast.LENGTH_SHORT).show();
				 return;
			 }else{
				 //Datos header
				 int idUser = helper.getIdUser();
				 String insulina = txtInsCons.getText().toString();
				 String glicemia = txtGlicemia.getText().toString();
				 String carbTotal = txtCarbTotal.getText().toString();
				 String fiberTotal = txtFiberTotalGerado.getText().toString();
				 int idH = 0;
				 // Date to String
				 SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				 Date today = Calendar.getInstance().getTime();        
				 String sDate = df.format(today);
				 
				 helper.insertLunchH(idUser,insulina,glicemia,carbTotal,fiberTotal,sDate);
				 idH = helper.getLastIdLunchH();
				 int cntChoice = listView.getCount();
				 for(int i = 0; i < cntChoice; i++)
				 {
					 if(sparseBooleanArray.get(i) == true) 
				     {
						 // Datos Details
						 String weight = "";
						 String carbAct = "";
						 String fiberAct = "";
						 String item = listView.getItemAtPosition(i).toString();
						 int iFood = Integer.parseInt(item.substring(0,item.indexOf(".")));
						 weight = helper.getWeightById(iFood);
						 carbAct = String.valueOf(helper.getCarbById(iFood));
						 fiberAct = String.valueOf(helper.getFiberById(iFood));
						 helper.insertLunchD(idH,iFood,weight,carbAct,fiberAct);					 
				     }
				 }
				 
				 AlertDialog alertDialog = new AlertDialog.Builder(
						 GeracaoInsulina.this).create();
	 
		        alertDialog.setTitle("Geração de Insulina");
		        alertDialog.setButton3("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish(); 
						
					}
				});
		        alertDialog.setMessage("Alimentos salvados!");
		        alertDialog.show();
			 }
			 
		 }
	}
	
}
