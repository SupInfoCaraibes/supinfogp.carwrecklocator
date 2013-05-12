/**
 * 
 */
package com.supinfogp.carwrecklocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * @author rgeromegnace
 *
 */
public class CarWreckDataForm extends Activity {
	
	private Spinner spinnerCarBrand = null;
	private Spinner spinnerCarColor = null;
	private TextView editTextCarRegistration = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carwreck_dataform_view);
		
		spinnerCarBrand = (Spinner) findViewById(R.id.spinnerCarBrand);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> carBrandAdapter = ArrayAdapter.createFromResource(this,
		        R.array.car_brands, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		carBrandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerCarBrand.setAdapter(carBrandAdapter);

		spinnerCarColor = (Spinner) findViewById(R.id.spinnerCarColor);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> carColorAdapter = ArrayAdapter.createFromResource(this,
		        R.array.car_colors, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		carColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerCarColor.setAdapter(carColorAdapter);
		
		editTextCarRegistration = (TextView) findViewById(R.id.editTextCarRegistration);
		
		Button btnDataFormOk = (Button)findViewById(R.id.btnDataFormOk);
		btnDataFormOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String carBrand = (String) spinnerCarBrand.getSelectedItem();
				Log.i("CWL", "marque: "+carBrand);
				
				String carColor = (String) spinnerCarColor.getSelectedItem();
				Log.i("CWL", "couleur: "+carColor);
				
				//j'utilise final car le contenu de getText est non modifiable
				final String carRegistration = editTextCarRegistration.getText().toString();
				Log.i("CWL", "immatriculation: "+carRegistration);
				
				Intent result = new Intent();
				String [] data = {carBrand, carColor,carRegistration};
				result.putExtra("com.supinfogp.carwrecklocator.photodata", data);
				setResult(Activity.RESULT_OK, result);
				finish();
			}
		});
		
		Button btnDataFormCancel = (Button)findViewById(R.id.btnDataFormCancel);
		btnDataFormCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setResult(Activity.RESULT_CANCELED);
				finish();
			}
		});
	}

}
