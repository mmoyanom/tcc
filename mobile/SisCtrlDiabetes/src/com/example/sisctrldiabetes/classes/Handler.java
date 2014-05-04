package com.example.sisctrldiabetes.classes;

import java.util.ArrayList;
import java.util.List;

import com.example.sisctrldiabetes.GeracaoInsulina;
import com.example.sisctrldiabetes.MainActivity;

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
		String query2 = " create table food ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name TEXT,weight TEXT,carb TEXT,fiber TEXT);";
		
		db.execSQL(query);
		db.execSQL(query2);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db,int version_old,int version_new){
		db.execSQL("DROP TABLE IF EXISTS user;");
		db.execSQL("DROP TABLE IF EXISTS food;");
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
	
	public int getInsulinaMedia(){
		String result = "";
		String columns[] = {"insulina"};
		Cursor c = this.getReadableDatabase().query("user",columns,null,null,null,null,null);
		if(c.getCount()<=0){
			return 0;
		}else{
			int ii;
			ii = c.getColumnIndex("insulina");
			c.moveToLast();
			result = c.getString(ii);
			return Integer.parseInt(result);
		}	
	}
	
	public void insertFood(String name,String weight,String carb,String fiber){
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("weight", weight);
		values.put("carb", carb);
		values.put("fiber", fiber);
		this.getWritableDatabase().insert("food", null, values);
	}
	
	public void open(){
		this.getWritableDatabase();
	}
	
	public void close(){
		this.close();
	}
	

    public ArrayList<Food> getAllFoods() {
        ArrayList<Food> foodList = new ArrayList<Food>();
        
        String selectQuery = "SELECT  * FROM food";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Food food = new Food();
                food.set_id(Integer.parseInt(cursor.getString(0)));
                food.set_name(cursor.getString(1));
                food.set_weight(cursor.getString(2));
                food.set_carb(cursor.getString(3));
                food.set_fiber(cursor.getString(4));
                
                String name = cursor.getString(0)+".       "+cursor.getString(1) +"                   "+ cursor.getString(2)+"        "+ cursor.getString(3);
                GeracaoInsulina.ArrayofName.add(name);
                
                // Adding contact to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        // return contact list
        return foodList;
    }
    
    public int getCarbById(int id) {
                
        String selectQuery = "SELECT carb FROM food WHERE _id="+id;
        System.out.println("selectQuery:" + selectQuery);
        int carb = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                carb = Integer.parseInt(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        return carb;
    }
}
