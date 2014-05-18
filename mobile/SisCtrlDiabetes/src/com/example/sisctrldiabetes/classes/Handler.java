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
											+ "name TEXT,email TEXT,weight TEXT,size TEXT,insulina TEXT,idDB INTEGER);";
		
		String query2 = " create table food ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name TEXT,weight TEXT,carb TEXT,fiber TEXT);";
		
		String query3 = " create table h_lunch ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "iduser INTEGER,ins TEXT,gli TEXT,carb TEXT,fiber TEXT,date TEXT,upBD int);";
		
		String query4 = " create table d_lunch ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "idh INTEGER,idfood INTEGER,weight TEXT,carb TEXT,fiber TEXT);";
		
		db.execSQL(query);
		db.execSQL(query2);
		db.execSQL(query3);
		db.execSQL(query4);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db,int version_old,int version_new){
		db.execSQL("DROP TABLE IF EXISTS user;");
		db.execSQL("DROP TABLE IF EXISTS food;");
		db.execSQL("DROP TABLE IF EXISTS h_lunch;");
		db.execSQL("DROP TABLE IF EXISTS d_lunch;");
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
	
	 public boolean updateUserIdDB(int idDB) {
		    ContentValues args = new ContentValues();
		    args.put("idDB", idDB);
		    return this.getWritableDatabase().update("user", args, null, null) > 0;
	}
	 public boolean updateUser(String name,String email,String weight,String size, String insulina) {
		    ContentValues args = new ContentValues();
		    args.put("name", name);
		    args.put("email", email);
		    args.put("weight", weight);
		    args.put("size", size);
		    args.put("insulina", insulina);
		    return this.getWritableDatabase().update("user", args, null, null) > 0;
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
	
	public void insertFood(String name,Double weight,Double carb,Double fiber){
		ContentValues values = new ContentValues();
		System.out.println("insertando de la BD:"+name+","+weight+","+carb+","+fiber);
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
        
        String selectQuery = "SELECT  * FROM food ORDER BY name";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        GeracaoInsulina.ArrayofName.clear();
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Food food = new Food();
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setName(cursor.getString(1));
                food.setWeight(Double.parseDouble(cursor.getString(2)));
                food.setCarb(Double.parseDouble(cursor.getString(3)));
                food.setFiber(Double.parseDouble(cursor.getString(4)));
                
                String name = cursor.getString(0)+".  "+cursor.getString(1) +"                   "+ cursor.getString(2)+"    "+ cursor.getString(3)+"   "+ cursor.getString(4);
                
                GeracaoInsulina.ArrayofName.add(name);
                
                // Adding contact to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        // return contact list
        return foodList;
    }
    
    public ArrayList<Food> getAllFoodsDesc() {
        ArrayList<Food> foodList = new ArrayList<Food>();
        
        String selectQuery = "SELECT  * FROM food ORDER BY carb desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        GeracaoInsulina.ArrayofName.clear();
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Food food = new Food();
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setWeight(Double.parseDouble(cursor.getString(2)));
                food.setCarb(Double.parseDouble(cursor.getString(3)));
                food.setFiber(Double.parseDouble(cursor.getString(4)));
                
                String name = cursor.getString(0)+".  "+cursor.getString(1) +"                   "+ cursor.getString(2)+"    "+ cursor.getString(3)+"   "+ cursor.getString(4);
                
                GeracaoInsulina.ArrayofName.add(name);
                
                // Adding contact to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        // return contact list
        return foodList;
    }
    
    public ArrayList<Food> getAllFoodsAsc() {
        ArrayList<Food> foodList = new ArrayList<Food>();
        
        String selectQuery = "SELECT  * FROM food ORDER BY carb asc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        GeracaoInsulina.ArrayofName.clear();
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Food food = new Food();
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setName(cursor.getString(1));
                food.setWeight(Double.parseDouble(cursor.getString(2)));
                food.setCarb(Double.parseDouble(cursor.getString(3)));
                food.setFiber(Double.parseDouble(cursor.getString(4)));
                
                String name = cursor.getString(0)+".  "+cursor.getString(1) +"                   "+ cursor.getString(2)+"    "+ cursor.getString(3)+"   "+ cursor.getString(4);
                
                GeracaoInsulina.ArrayofName.add(name);
                
                // Adding contact to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        // return contact list
        return foodList;
    }
    
    public Double getCarbById(int id) {
                
        String selectQuery = "SELECT carb FROM food WHERE _id="+id;
        System.out.println("selectQuery:" + selectQuery);
        Double carb = 0.0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                carb = Double.parseDouble(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        return carb;
    }
    
    public Double getFiberById(int id) {
        
        String selectQuery = "SELECT fiber FROM food WHERE _id="+id;
        System.out.println("selectQuery:" + selectQuery);
        Double fiber = 0.0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                fiber = Double.parseDouble(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        return fiber;
    }
    
    public String getWeightById(int id) {
        
        String selectQuery = "SELECT weight FROM food WHERE _id="+id;
        System.out.println("selectQuery:" + selectQuery);
        String weight = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	weight = cursor.getString(0);

            } while (cursor.moveToNext());
        }
        return weight;
    }
    
    public void insertLunchH(int iduser,String ins,String gli,String carb,String fiber,String date,int upBD){
		ContentValues values = new ContentValues();
		values.put("iduser", iduser);
		values.put("ins", ins);
		values.put("gli", gli);
		values.put("carb", carb);
		values.put("fiber", fiber);
		values.put("date", date);
		values.put("upBD", upBD);
		this.getWritableDatabase().insert("h_lunch", null, values);
	}
    
    public void insertLunchD(int idh,int idfood,String weight,String carb,String fiber){
		ContentValues values = new ContentValues();
		values.put("idh", idh);
		values.put("idfood", idfood);
		values.put("weight", weight);
		values.put("carb", carb);
		values.put("fiber", fiber);
		this.getWritableDatabase().insert("d_lunch", null, values);
	}
    
    public int getIdUser() {
        
        String selectQuery = "SELECT _ID FROM user";
        System.out.println("selectQuery:" + selectQuery);
        int id = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	id = Integer.parseInt(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        return id;
    }
    
    public int getIdDBUser() {
        
        String selectQuery = "SELECT idDB FROM user";
        System.out.println("selectQuery:" + selectQuery);
        int id = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	id = Integer.parseInt(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        return id;
    }
    
    public int getLastIdLunchH() {
        
        String selectQuery = "SELECT _ID FROM h_lunch ORDER BY _ID DESC LIMIT 1";
        System.out.println("selectQuery:" + selectQuery);
        int id = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	id = Integer.parseInt(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        return id;
    }
    
    public ArrayList<H_Lunch> getAllLunchHtoSync() {
        ArrayList<H_Lunch> h_LunchList = new ArrayList<H_Lunch>();
        
        String selectQuery = "SELECT  * FROM h_lunch where upBD = 0 ORDER BY _ID desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {            	
            	H_Lunch hl = new H_Lunch();
            	hl.setId(Integer.parseInt(cursor.getString(0)));
            	hl.setIduser(Integer.parseInt(cursor.getString(1)));
            	hl.setIns(cursor.getString(2));
            	hl.setGli(cursor.getString(3));
            	hl.setCarb(cursor.getString(4));
            	hl.setFiber(cursor.getString(5));
            	hl.setDate(cursor.getString(6));
            	hl.setUpBD(Integer.parseInt(cursor.getString(7)));
           
            	h_LunchList.add(hl);
            } while (cursor.moveToNext());
        }

        // return contact list
        return h_LunchList;
    }
    
    public ArrayList<D_Lunch> getAllLunchD(int idH) {
        ArrayList<D_Lunch> d_LunchList = new ArrayList<D_Lunch>();
        
        String selectQuery = "SELECT  * FROM d_lunch where idh ="+idH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
            	D_Lunch dl = new D_Lunch();
            	dl.setId(Integer.parseInt(cursor.getString(0)));
            	dl.setIdH(Integer.parseInt(cursor.getString(1)));
            	dl.setIdFood(Integer.parseInt(cursor.getString(2)));
            	dl.setWeight(cursor.getString(3));
            	dl.setCarb(cursor.getString(4));
            	dl.setFiber(cursor.getString(5));
            	           
            	d_LunchList.add(dl);
            } while (cursor.moveToNext());
        }
        return d_LunchList;
    }
    
    public boolean updateLunchHStatusBD(int id) {
	    ContentValues args = new ContentValues();
	    args.put("upBD",1);
	    return this.getWritableDatabase().update("h_lunch", args, "_ID = "+id, null) > 0;
}
    
    
}
