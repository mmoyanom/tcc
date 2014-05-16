package com.example.sisctrldiabetes;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.example.sisctrldiabetes.classes.Handler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;

public class Relatorios extends Activity implements OnClickListener{
	
	public void onCreate(Bundle icicle) {
		
		super.onCreate(icicle);
		setContentView(R.layout.relatorios);
	
		Handler helper = new Handler(this);
		String user = helper.getUser();
		if (user == "empty"){
			Intent it = new Intent(this, PerfilUsuario.class);
			startActivity(it);	
		}
		
		View btnEnviarEmail = findViewById(R.id.btnEnviarEmail);
		btnEnviarEmail.setOnClickListener(this);
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
		// TODO Auto-generated method stub
		
		AlertDialog alertDialog = new AlertDialog.Builder(
				 Relatorios.this).create();
		 if(v.getId()==findViewById(R.id.btnEnviarEmail).getId())
		 {
					 
			String Fnamexls="testfile4"  + ".xls";
            File sdCard = Environment.getExternalStorageDirectory();
        	File directory = new File (sdCard.getAbsolutePath() + "/Relatorio");
        	directory.mkdirs();
        	File file = new File(directory, Fnamexls);

          WorkbookSettings wbSettings = new WorkbookSettings();

          wbSettings.setLocale(new Locale("en", "EN"));

          WritableWorkbook workbook;
          try {
            int a = 1;
              workbook = Workbook.createWorkbook(file, wbSettings);
              //workbook.createSheet("Report", 0);
              WritableSheet sheet = workbook.createSheet("First Sheet", 0);
              Label label = new Label(0, 2, "SECOND");
              Label label1 = new Label(0,1,"first");
              Label label0 = new Label(0,0,"HEADING");
              Label label3 = new Label(1,0,"Heading2");
              Label label4 = new Label(1,1,String.valueOf(a));
              try {
                               sheet.addCell(label);
                                sheet.addCell(label1);
                               sheet.addCell(label0);
                               sheet.addCell(label4);
                               sheet.addCell(label3);
                        } catch (RowsExceededException e) {
                               // TODO Auto-generated catch block
                               e.printStackTrace();
                        } catch (WriteException e) {
                               // TODO Auto-generated catch block
                               e.printStackTrace();
                        }
          

              workbook.write();
              alertDialog.setMessage("O arquivo foi criado em: " + file.getPath());
              alertDialog.show();
              
              
              try {
	                        workbook.close();
	               } 
              catch (WriteException e) 
	               {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	               }
              //createExcel(excelSheet);
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
          
 		 String[] to = { "valeri.1109@gmail.com" };         
        enviar(to, "Relatorio de Até",
                "Relatorio", file.getPath());
       
		 }
	}
	
	private void enviar(String[] to, 
	    String asunto, String mensaje, String file) {
	    Intent emailIntent = new Intent(Intent.ACTION_SEND);
	    emailIntent.setType("plain/text");
	    emailIntent.setData(Uri.parse("mailto:"));
	    //String[] to = direccionesEmail;
	    //String[] cc = copias;
	    emailIntent.putExtra(Intent.EXTRA_EMAIL, to);		    
	    emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
	    emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
	    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(file));
	    emailIntent.setType("application/excel");
	    startActivity(Intent.createChooser(emailIntent, "Email "));
		    
	}

}
