package com.example.sisctrldiabetes;



import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.example.sisctrldiabetes.async.AsyncCallWS;
import com.example.sisctrldiabetes.async.AsyncCallWSiu;
import com.example.sisctrldiabetes.async.AsyncCallWSuu;
import com.example.sisctrldiabetes.classes.Food;
import com.example.sisctrldiabetes.classes.Handler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class PerfilUsuario extends Activity implements OnClickListener{
	
	 Handler helper = new Handler(this);

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.perfilusuario);
		
		String user = helper.getUser();
		if (user == "empty"){
		}else{
			String[] data = user.split("-");
				
			TextView tvName = (TextView)findViewById(R.id.txtName);
			TextView tvEmail = (TextView)findViewById(R.id.txtEmail);
			TextView tvPeso = (TextView)findViewById(R.id.txtPeso);
			TextView tvTamanho = (TextView)findViewById(R.id.txtTamanho);
			TextView tvInsulina = (TextView)findViewById(R.id.txtCantMedIns);
			
			tvName.setText(data[1]);
			tvEmail.setText(data[2]);
			tvPeso.setText(data[3]);
			tvTamanho.setText(data[4]);
			tvInsulina.setText(data[5]);
		}
		
		View btnSalvar = findViewById(R.id.btnSalvar);
		btnSalvar.setOnClickListener(this);
	}
	
	public void onResume(){
	    super.onResume();
		String user = helper.getUser();
		Button btnSalvar = (Button)findViewById(R.id.btnSalvar);
		if (user == "empty"){
			btnSalvar.setText("Salvar");
		}else{
			btnSalvar.setText("Atualizar");
		}
			
	}
	
	public void onClick(View v) {		
			 if(v.getId()==findViewById(R.id.btnSalvar).getId())
			 {
				 System.out.println("entro onclick");
				 TextView tvName = (TextView)findViewById(R.id.txtName);
				 TextView tvEmail = (TextView)findViewById(R.id.txtEmail);
				 TextView tvPeso = (TextView)findViewById(R.id.txtPeso);
				 TextView tvTamanho = (TextView)findViewById(R.id.txtTamanho);
				 TextView tvInsulina = (TextView)findViewById(R.id.txtCantMedIns);
				 
				 
				 String user = helper.getUser();
				 if (user == "empty"){
					 helper.insertUser(tvName.getText().toString(), tvEmail.getText().toString(), tvPeso.getText().toString(),
							 		   tvTamanho.getText().toString(), tvInsulina.getText().toString());
					 
					 ArrayList<String> passing = new ArrayList<String>();
					 passing.add(tvName.getText().toString());
					 passing.add(tvEmail.getText().toString());
					 passing.add(tvPeso.getText().toString());
					 passing.add(tvTamanho.getText().toString());
					 passing.add(tvInsulina.getText().toString());
					 
					 AsyncCallWSiu task = new AsyncCallWSiu();
				     task.execute(passing);
				
					 
					 ArrayList<String> result = new ArrayList<String>();
					 try {
				        	result =  task.get();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 
					 helper.updateUserIdDB(Integer.parseInt(result.get(0)));
				     System.out.println("IdBD user update");
					 
					 AlertDialog alertDialog = new AlertDialog.Builder(
							 PerfilUsuario.this).create();
		 
				     // Setting Dialog Title
				     alertDialog.setTitle("Perfil de Usuário");
				        
				     alertDialog.setButton3("OK", new DialogInterface.OnClickListener() {
							
					 @Override
					 	public void onClick(DialogInterface dialog, int which) {
						 finish(); 
					 	}
					 });
				     alertDialog.setMessage("Dados de usuario salvados!");
				     alertDialog.show();
	        
				 }else{
					 
					 int idUserAct = 0;
					 idUserAct = helper.getIdUser();
					 helper.updateUser(tvName.getText().toString(), tvEmail.getText().toString(), tvPeso.getText().toString(),
					 		   tvTamanho.getText().toString(), tvInsulina.getText().toString());
					 int userUp = helper.getIdDBUser();
					 System.out.println("userUp"+userUp);
					 ArrayList<String> passing = new ArrayList<String>();
					 passing.add(String.valueOf(userUp));
					 passing.add(tvName.getText().toString());
					 passing.add(tvEmail.getText().toString());
					 passing.add(tvPeso.getText().toString());
					 passing.add(tvTamanho.getText().toString());
					 passing.add(tvInsulina.getText().toString());
					 
					 AsyncCallWSuu task = new AsyncCallWSuu();
				     task.execute(passing);
				     
				     System.out.println("User update");
					 
					 AlertDialog alertDialog = new AlertDialog.Builder(
							 PerfilUsuario.this).create();
		 
				     // Setting Dialog Title
				     alertDialog.setTitle("Perfil de Usuário");
				        
				     alertDialog.setButton3("OK", new DialogInterface.OnClickListener() {
							
					 @Override
					 	public void onClick(DialogInterface dialog, int which) {
						 finish(); 
					 	}
					 });
				     alertDialog.setMessage("Dados de usuario atualizados!");
				     alertDialog.show();
				     
					 
					 
				 }
			}
	}	
}
