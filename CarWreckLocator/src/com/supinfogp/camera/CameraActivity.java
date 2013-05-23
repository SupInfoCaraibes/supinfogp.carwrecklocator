//package com.supinfogp.carwrecklocator;
package com.supinfogp.camera;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.supinfogp.carwrecklocator.R;

public class CameraActivity extends Activity {
	private Camera mCamera;
    private CameraPreview mPreview;
    private CameraHelper mCameraHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity_view);

        // Create an instance of Camera
        mCameraHelper = new CameraHelper();
        mCamera = mCameraHelper.getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        
        
        Intent cameraIntent = getIntent();
        
		final String [] photoData = cameraIntent.getStringArrayExtra("com.supinfogp.carwrecklocator.photodata");
		Log.i("CWL", "marque retournée: "+photoData[0]);
		Log.i("CWL", "couleur retournée: "+photoData[1]);
		Log.i("CWL", "immatriculation retournée: "+photoData[2]);
        
        Button btnCapture = (Button)findViewById(R.id.btn_capture);
        btnCapture.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCamera.takePicture(
							null, 
							null, 
							new PhotoHandler(getApplicationContext(), photoData));
			}
		});
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
		super.onPause();
	}
}
