package com.example.sisctrldiabetes;

import com.example.sisctrldiabetes.classes.Handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GeracaoInsulina extends Activity{
	
	public void onCreate(Bundle icicle) {
		
		super.onCreate(icicle);
		
		setContentView(R.layout.geracaoinsulina);

		 Handler helper = new Handler(this);
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
	
}
