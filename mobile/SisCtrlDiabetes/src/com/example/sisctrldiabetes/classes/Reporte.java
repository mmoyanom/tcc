package com.example.sisctrldiabetes.classes;

import com.google.gson.annotations.SerializedName;

public class Reporte {
	
	@SerializedName(value = "Data")
	String _data;
		
	@SerializedName(value = "Glicemia")
    String _glicemia;
	
	@SerializedName(value = "TotalInsulina")
    String _totalIns;	
	
	@SerializedName(value = "Id")
    String _Id;	
	
	 public Reporte(){

	    }
    
    public Reporte(String data, String glicemia, String totalIns, String Id){
    	this._data = data;
    	this._glicemia = glicemia;
    	this._totalIns = totalIns;
    	this._Id = Id;
    	
    }
    // constructor
    
    
    public String getdata() {
		return _data;
	}
	public void setdata(String _data) {
		this._data = _data;
	}
	public String getGlicemia() {
		return _glicemia;
	}
	public void setGlicemia(String _glicemia) {
		this._glicemia = _glicemia;
	}
	public String getTotalIns() {
		return _totalIns;
	}
	public void setTotalIns(String _totalIns) {
		this._totalIns = _totalIns;
	}
	public String getID() {
		return _Id;
	}
	public void setID(String _Id) {
		this._Id = _Id;
	}
	
}
