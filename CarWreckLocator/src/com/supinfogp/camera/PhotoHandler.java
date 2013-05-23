package com.supinfogp.camera;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.supinfogp.exif.ExifHelper;

public class PhotoHandler implements PictureCallback {

	private final Context mContext;
	private final String [] mPhotoData;

	public PhotoHandler(Context context, String [] photoData) {
		this.mContext = context;
		this.mPhotoData = photoData;
	}
	@Override
	public void onPictureTaken(byte[] data, Camera arg1) {
		// TODO Auto-generated method stub
		File pictureFileDir = getDir();
		
		if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
			if(!pictureFileDir.isDirectory())
			Log.d("CWL", "Impossible de créer le répertoire pour sauver l'image.");
			Toast.makeText(mContext, "Impossible de créer le répertoire pour sauver l'image.",
					Toast.LENGTH_LONG).show();
			return;

		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss", Locale.FRANCE);
		String date = dateFormat.format(new Date());
		String photoFile = "Image_" + date + ".jpg";

		String filename = pictureFileDir.getPath() + File.separator + photoFile;

		File pictureFile = new File(filename);

		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
			fos.write(data);
			fos.close();
			Toast.makeText(mContext, "Nouvelle image sauvegardée:" + photoFile,
					Toast.LENGTH_LONG).show();
		} catch (Exception error) {
			Log.d("CWL", "Fichier" + filename + "non sauvegardée: "
					+ error.getMessage());
			Toast.makeText(mContext, "Impossible de sauvegarder l'image.",
					Toast.LENGTH_LONG).show();
		}
		
		//Ecriture des information Exif
		try {
			ExifHelper exifHelper = new ExifHelper(filename, mPhotoData);
			exifHelper.InitExifInterface();
			exifHelper.WriteExifData();
		}
		catch(Exception error) {
			Log.d("CWL", "Erreur lors de la sauvegarde Exif: "+error.getMessage());
		}
	}
	
	private File getDir() {
	    File sdDir = Environment
	      .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
	    //return sdDir;
	    return new File(sdDir, "CameraAPIDemo");
	  }}
