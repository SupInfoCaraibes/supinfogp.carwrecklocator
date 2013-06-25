package com.supinfogp.exif;

import java.io.IOException;

import android.media.ExifInterface;
import android.util.Log;

public class ExifHelper {
	
	static final String CUSTOM_TAG_MARQUE = "MARQUE"; 
	static final String CUSTOM_TAG_COULEUR = "COULEUR";
	static final String CUSTOM_TAG_IMMATRICULATION = "IMMATRICULATION"; 
	
	private String mExifFilePath;

	private String [] mData;
	
	private ExifInterface mExifInterface;
	
	/**
	 * constructeur à partir du chemin du fichier
	 */
	public ExifHelper(String filePath, String [] data) {
		Log.i("CWL", "ExifHelper constructor");
		mExifFilePath = filePath;
		mData = data;
	}
	
	public void InitExifInterface() {
		try {
			mExifInterface = new ExifInterface(mExifFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i("CWL", "ERREUR EXIF - "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void WriteExifData() {
		try {
			//mExifInterface.setAttribute(CUSTOM_TAG_MARQUE, mData[0]);
			Log.i("CWL", "exif date"+mExifInterface.getAttribute(ExifInterface.TAG_MODEL));
			mExifInterface.setAttribute(ExifInterface.TAG_MAKE, "By Rakoun.com");
			mExifInterface.setAttribute("UserComment", "By Rakoun.com");
			//mExifInterface.setAttribute(ExifInterface.TAG_DATETIME, mData[0]);
			//mExifInterface.setAttribute(CUSTOM_TAG_COULEUR, mData[1]);
			//mExifInterface.setAttribute(CUSTOM_TAG_IMMATRICULATION, mData[2]);
		}
		catch(Exception e) {
			Log.i("CWL", "ERREUR EXIT - "+e.getMessage());
		}
		//TODO: geo tags
		
		try {
			mExifInterface.saveAttributes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
