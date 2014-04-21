package com.example.sisctrldiabetes;



import com.example.sisctrldiabetes.classes.Handler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
	
	public void onClick(View v) {		
			 if(v.getId()==findViewById(R.id.btnSalvar).getId())
			 {
				 System.out.println("entro onclick");
				 TextView tvName = (TextView)findViewById(R.id.txtName);
				 TextView tvEmail = (TextView)findViewById(R.id.txtEmail);
				 TextView tvPeso = (TextView)findViewById(R.id.txtPeso);
				 TextView tvTamanho = (TextView)findViewById(R.id.txtTamanho);
				 TextView tvInsulina = (TextView)findViewById(R.id.txtCantMedIns);
				 
				 helper.insertUser(tvName.getText().toString(), tvEmail.getText().toString(), tvPeso.getText().toString(),
						 		   tvTamanho.getText().toString(), tvInsulina.getText().toString());
				 
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
	        
	        //Intent it = new Intent(this, MainActivity.class);
			 //startActivity(it);
	        
	        // Showing Alert Message
	        alertDialog.show();
		 	}
	}	
}
