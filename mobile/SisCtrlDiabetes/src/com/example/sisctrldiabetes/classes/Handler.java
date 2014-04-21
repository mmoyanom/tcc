package com.example.sisctrldiabetes.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;

public class Handler extends SQLiteOpenHelper{
		
	public Handler(Context ctx){
		super(ctx,"dbCtrlDiabetes",null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		
		String query = "create table user ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
											+ "name TEXT,email TEXT,weight TEXT,size TEXT,insulina TEXT);";
		
		db.execSQL(query);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db,int version_old,int version_new){
		db.execSQL("DROP TABLE IF EXISTS user");
		onCreate(db);
	}
	
	public void insertUser(String name,String email,String weight,String size, String insulina){
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("email", email);
		values.put("weight", weight);
		values.put("size", size);
		values.put("insulina", insulina);
		
		this.getWritableDatabase().insert("user", null, values);
		
	}
	
	public String getUser(){
		String result = "";
		String columns[] = {_ID,"name","email","weight","size","insulina"};
		Cursor c = this.getReadableDatabase().query("user",columns,null,null,null,null,null);
		
		if(c.getCount()<=0){
			return "empty";
		}else{
			
			int id,in,ie,iw,is,ii;
			id = c.getColumnIndex(_ID);
			in = c.getColumnIndex("name");
			ie = c.getColumnIndex("email");
			iw = c.getColumnIndex("weight");
			is = c.getColumnIndex("size");
			ii = c.getColumnIndex("insulina");
			
			c.moveToLast();
			
			result = c.getString(id)+"-"+c.getString(in)+"-"+c.getString(ie)+"-"+c.getString(iw)+"-"+c.getString(is)+"-"+c.getString(ii);
			return result;
			
		}
		
		
	}
	
	public void open(){
		this.getWritableDatabase();
	}
	
	public void close(){
		this.close();
	}
	
	
}
