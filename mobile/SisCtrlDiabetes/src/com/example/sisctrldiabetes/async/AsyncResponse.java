package com.example.sisctrldiabetes.async;

import java.util.ArrayList;

import com.example.sisctrldiabetes.classes.Food;

public interface AsyncResponse {
	void processFinish(ArrayList<Food> lsFoods);
}
