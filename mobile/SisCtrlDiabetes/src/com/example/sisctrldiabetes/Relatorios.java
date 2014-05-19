package com.example.sisctrldiabetes;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.example.sisctrldiabetes.classes.Food;
import com.example.sisctrldiabetes.classes.Handler;
import com.example.sisctrldiabetes.classes.Reporte;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class Relatorios extends FragmentActivity implements OnClickListener{

	
	Handler helper = new Handler(this);
	
	public void onCreate(Bundle icicle) {
		
		super.onCreate(icicle);
		setContentView(R.layout.relatorios);
	
		

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
		
		TextView txtDe = (TextView)findViewById(R.id.TxtDe);
        TextView txtPara = (TextView)findViewById(R.id.txtFim);
        
        if (txtDe.getText().toString().trim().matches("")){
        	
        	Toast.makeText(this,"Selecione as datas para consulta.", Toast.LENGTH_SHORT).show();
        }
        else
        {
        	
        	  String txtDataDe = new SimpleDateFormat("MM/dd/yyyy").format(new Date(txtDe.getText().toString()));
              String txtDataPara = new SimpleDateFormat("MM/dd/yyyy").format(new Date(txtPara.getText().toString()));
              
              List<Reporte> Cab = helper.Reporte(txtDataDe, txtDataPara);
        
        	if(Cab.size() == 0){
        		
        		Toast.makeText(this,"Não existem dados para mostrar.", Toast.LENGTH_SHORT).show();
        	}
        	else{
        
        
		AlertDialog alertDialog = new AlertDialog.Builder(
				 Relatorios.this).create();
		 if(v.getId()==findViewById(R.id.btnEnviarEmail).getId())
		 {
					 
			String Fnamexls="RelatorioAlimentos"  + ".xls";
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
                      
              try {
            	  
            
              System.out.println("total cadastrados:"+ Cab.size());
              
             
              int Pos = 1;
              int Sheet = 0;
              for (Reporte rCab : Cab) {
            	  
            	  WritableSheet sheet = workbook.createSheet("Refeição" + new SimpleDateFormat("yyyy/MM/dd").format(new Date(rCab.getdata())), Sheet);
            	  
            	  Label lblCabData = new Label(0,0,"Data");
                  Label lblCabHora = new Label(1,0,"Hora");
                  Label lblCabGlic = new Label(2,0,"Glicemia");
                  Label lblCabInsu = new Label(3,0,"Total de Insulina");
            	  
            	  String Time = new SimpleDateFormat("HH:mm:ss").format(new Date(rCab.getdata()));
            	  String Data = new SimpleDateFormat("yyyy/MM/dd").format(new Date(rCab.getdata()));
            	  
            	  Label lblData = new Label(0,1,Data);
                  Label lblHora = new Label(1,1,Time);
                  Label lblGlic = new Label(2,1,rCab.getGlicemia());
                  Label lblInsu = new Label(3,1,rCab.getTotalIns());
            	  
                  
                  System.out.println("total cadastrados:"+ rCab.getdata());
                  
                  sheet.addCell(lblCabData);                  
                  sheet.addCell(lblCabHora);
                  sheet.addCell(lblCabGlic);
                  sheet.addCell(lblCabInsu);
                  
                  sheet.addCell(lblData);
                  sheet.addCell(lblHora);
                  sheet.addCell(lblGlic);
                  sheet.addCell(lblInsu);                 
                  
                  
                  List<Food> ListaAlimentos = helper.ReporteAlimentos(rCab.getID());
                  int posAlimento = 5;
                  
                  Label lblRef = new Label(1,4,"Refeição");
                  Label lblGramas = new Label(2,4,"Gramas");
                  sheet.addCell(lblRef);
                  sheet.addCell(lblGramas);
                  for (Food rFoo : ListaAlimentos) {         	                  	  
                  
                	  Label lblName = new Label(1,posAlimento,rFoo.getName());
                	  Label lblCarb = new Label(2,posAlimento,rFoo.getCarb().toString());
                      sheet.addCell(lblName);
                      sheet.addCell(lblCarb);
                      posAlimento++;
                  }
                  
                  Pos++;
                  Sheet++;
		 		}
                                      
            
                             
            } catch (RowsExceededException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
            } catch (WriteException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
            }
          

              workbook.write();
     
	        alertDialog.setTitle("Relatorios");	        
	        alertDialog.setButton3("OK", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish(); 					
				}
			});
	        
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
          
          
          
 		 String[] to = { helper.getMail() };         
         enviar(to, "Relatorio de " + txtDe.getText() + " Até " + txtPara.getText(),
                "Relatorio", file.getPath());
       
		 }
        }
       }       
        
	}
	
		private void enviar(String[] to, 
	    String asunto, String mensaje, String file) {
		
		
	    Intent emailIntent = new Intent(Intent.ACTION_SEND);
	    //emailIntent.setType("plain/text");
	    //emailIntent.setData(Uri.parse("mailto:"));
	    	    
	    emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
	    emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
	    emailIntent.putExtra(Intent.EXTRA_EMAIL, to);	    
	    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(file));
	    emailIntent.setType("application/excel");
	    //startActivity(Intent.createChooser(emailIntent, "Email "));
	    startActivity(emailIntent);  
	}

		EditText mEdit;	
	 
		public void selectDateDe(View view) {
			DialogFragment newFragment = new SelectDateFragment();
			newFragment.show(getSupportFragmentManager(), "DatePicker");
		}
			
		public void populateSetDate(int year, int month, int day) {
			mEdit = (EditText)findViewById(R.id.TxtDe);
			mEdit.setText(day+"/"+month+"/"+year);		
		}
		
		@SuppressLint("ValidFragment")
		public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
		@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				final Calendar calendar = Calendar.getInstance();
				int yy = calendar.get(Calendar.YEAR);
				int mm = calendar.get(Calendar.MONTH);
				int dd = calendar.get(Calendar.DAY_OF_MONTH);
				return new DatePickerDialog(getActivity(), this, yy, mm, dd);
			}
		 
			public void onDateSet(DatePicker view, int yy, int mm, int dd) {
				populateSetDate(yy, mm+1, dd);
			}		
		}	
	
		public void selectDatePara(View view) {
			DialogFragment newFragment = new SelectDateFragmentPara();
			newFragment.show(getSupportFragmentManager(), "DatePicker");
		}
			
		public void populateSetDatePara(int year, int month, int day) {
			mEdit = (EditText)findViewById(R.id.txtFim);
			mEdit.setText(day+"/"+month+"/"+year);
		
		}
		@SuppressLint("ValidFragment")
		public class SelectDateFragmentPara extends DialogFragment implements DatePickerDialog.OnDateSetListener {
		@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				final Calendar calendar = Calendar.getInstance();
				int yy = calendar.get(Calendar.YEAR);
				int mm = calendar.get(Calendar.MONTH);
				int dd = calendar.get(Calendar.DAY_OF_MONTH);
				return new DatePickerDialog(getActivity(), this, yy, mm, dd);
			}
		 
			public void onDateSet(DatePicker view, int yy, int mm, int dd) {
				populateSetDatePara(yy, mm+1, dd);
			}		
		}	

}
