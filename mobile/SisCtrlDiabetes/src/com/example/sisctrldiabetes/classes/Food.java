package com.example.sisctrldiabetes.classes;

import com.google.gson.annotations.SerializedName;

public class Food {
	
	@SerializedName(value = "Id")
	int _id;
	
	@SerializedName(value = "Name")
	String _name;
	
	@SerializedName(value = "Weight")
    Double _weight;
	
	@SerializedName(value = "Carbohydrate")
    Double _carb;
	
	@SerializedName(value = "Fiber")
    Double _fiber;
    
    public Food(){

    }
    // constructor
    public Food(int id, String name, Double weight, Double carb, Double fiber){
        this._id = id;
        this._name = name;
        this._weight = weight;
        this._carb = carb;
        this._fiber = fiber;
    }
    
    public int getId() {
		return _id;
	}
	public void setId(int _id) {
		this._id = _id;
	}
	public String getName() {
		return _name;
	}
	public void setName(String _name) {
		this._name = _name;
	}
	public Double getWeight() {
		return _weight;
	}
	public void setWeight(Double _weight) {
		this._weight = _weight;
	}
	public Double getCarb() {
		return _carb;
	}
	public void setCarb(Double _carb) {
		this._carb = _carb;
	}
	public Double getFiber() {
		return _fiber;
	}
	public void setFiber(Double _fiber) {
		this._fiber = _fiber;
	}

    

}
