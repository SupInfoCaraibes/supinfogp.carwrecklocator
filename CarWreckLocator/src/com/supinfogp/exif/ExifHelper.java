package com.supinfogp.exif;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.ExifInterface;

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
		mExifFilePath = filePath;
		mData = data;
	}
	
	public void InitExifInterface() {
		try {
			mExifInterface = new ExifInterface(mExifFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void WriteExifData() {
		mExifInterface.setAttribute(CUSTOM_TAG_MARQUE, mData[0]);
		mExifInterface.setAttribute(CUSTOM_TAG_COULEUR, mData[1]);
		mExifInterface.setAttribute(CUSTOM_TAG_IMMATRICULATION, mData[2]);
		//TODO: geo tags
		
		try {
			mExifInterface.saveAttributes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
