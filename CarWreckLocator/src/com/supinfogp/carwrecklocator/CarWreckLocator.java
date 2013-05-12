package com.supinfogp.carwrecklocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.supinfogp.camera.CameraActivity;

public class CarWreckLocator extends Activity {
	
	static final int CAR_WECK_DATA_FORM_REQUEST = 0;
	
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == CAR_WECK_DATA_FORM_REQUEST) {
			if (resultCode == RESULT_OK) {
				String [] photoData = data.getStringArrayExtra("com.supinfogp.carwrecklocator.photodata");
				Log.i("CWL", "marque retournée: "+photoData[0]);
				Log.i("CWL", "couleur retournée: "+photoData[1]);
				Log.i("CWL", "immatriculation retournée: "+photoData[2]);
				Intent intentCamera = new Intent(getApplicationContext(), CameraActivity.class);
				startActivity(intentCamera);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wreck_locator);
        
        final Button btnTakePhoto = (Button)this.findViewById(R.id.btn_take_photo);
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intentDataForm = new Intent(getApplicationContext(), CarWreckDataForm.class);
				startActivityForResult(intentDataForm, CAR_WECK_DATA_FORM_REQUEST);
				
				//Intent intentCamera = new Intent(getApplicationContext(), CameraActivity.class);
				//startActivity(intentCamera);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_car_wreck_locator, menu);
        return true;
    }
    
}
