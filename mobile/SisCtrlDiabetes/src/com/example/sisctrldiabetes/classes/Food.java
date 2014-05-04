package com.example.sisctrldiabetes.classes;

public class Food {
	
	int _id;
	String _name;
    String _weight;
    String _carb;
    String _fiber;
    
    public Food(){

    }
    // constructor
    public Food(int id, String name, String weight, String carb, String fiber){
        this._id = id;
        this._name = name;
        this._weight = weight;
        this._carb = carb;
        this._fiber = fiber;
    }
    
    public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public String get_weight() {
		return _weight;
	}
	public void set_weight(String _weight) {
		this._weight = _weight;
	}
	public String get_carb() {
		return _carb;
	}
	public void set_carb(String _carb) {
		this._carb = _carb;
	}
	public String get_fiber() {
		return _fiber;
	}
	public void set_fiber(String _fiber) {
		this._fiber = _fiber;
	}

    

}
