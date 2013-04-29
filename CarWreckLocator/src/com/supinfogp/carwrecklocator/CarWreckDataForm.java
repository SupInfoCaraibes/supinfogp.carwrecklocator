/**
 * 
 */
package com.supinfogp.carwrecklocator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * @author rgeromegnace
 *
 */
public class CarWreckDataForm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carwreck_dataform_view);
		
		Spinner spinnerCarBrand = (Spinner) findViewById(R.id.spinnerCarBrand);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> carBrandAdapter = ArrayAdapter.createFromResource(this,
		        R.array.car_brands, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		carBrandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerCarBrand.setAdapter(carBrandAdapter);

		Spinner spinnerCarColor = (Spinner) findViewById(R.id.spinnerCarBrand);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> carColorAdapter = ArrayAdapter.createFromResource(this,
		        R.array.car_colors, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		carColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerCarColor.setAdapter(carColorAdapter);
	}

}
