package com.supinfogp.location;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Helper classe to use location api.
 * @author rgeromegnace
 *
 */
public class LocationHelper {

	private int providerType = 1;
	static final int NETWORK_PROVIDER = 0;
	static final int GPS_PROVIDER = 1;
	
	private LocationManager locationManager = null;
	private LocationListener locationListener = null;
	

	// Using volatile key word allow, in java version 5 or higher, 
	// to avoid the case when "Singleton.instance" is not null,
	// but not yet instanciate.
	// In java version 1.2 to 1.4 you can use ThreadLocal class.	
	private static volatile LocationHelper instance = null;

	/**
	 * Private constructor.
	 * @param providerType Provider Type, NETWORK or GPS.
	 * @param caller Activity that launch LocationHelper.
	 */
	private LocationHelper(int providerType, Activity caller) {
		// Acquire a reference to the system Location Manager
		locationManager = (LocationManager) caller.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location provider.
				//makeUseOfNewLocation(location);
			}

			public void onStatusChanged(String provider, int status, Bundle extras) {}

			public void onProviderEnabled(String provider) {}

			public void onProviderDisabled(String provider) {}
		};

		// Register the listener with the Location Manager to receive location updates
		switch(providerType) {
			case NETWORK_PROVIDER: {
				locationManager
					.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
			}
			break;
			case GPS_PROVIDER: {
				locationManager
					.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
			}
			break;
			default: {
				Log.w("CWL", "Unknow location provider");
			}
		}
	}

	/**
	 * Instanciate LocationHelper.
	 * @param providerType Provider Type, NETWORK or GPS.
	 * @param caller Activity that launch LocationHelper.
	 * @return
	 */
	public static LocationHelper getInstance(int providerType, Activity caller) {
		if(LocationHelper.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			// Il est TRES important.
			synchronized(LocationHelper.class) {
				if(LocationHelper.instance == null) {
					LocationHelper.instance = new LocationHelper(providerType, caller);
				}
			}
		}
		else if(providerType != LocationHelper.instance.providerType) {
			LocationHelper.instance.endListening();
		}
		return LocationHelper.instance;
	}
	
	/** 
	 * Launch location pulling.
	 */
	public void startListening() {
		// Define a listener that responds to location updates
		this.locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location provider.
				//makeUseOfNewLocation(location);
			}

			public void onStatusChanged(String provider, int status, Bundle extras) {}

			public void onProviderEnabled(String provider) {}

			public void onProviderDisabled(String provider) {}
		};
		
		// Register the listener with the Location Manager to receive location updates
		switch(providerType) {
			case NETWORK_PROVIDER: {
				locationManager
					.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
			}
			break;
			case GPS_PROVIDER: {
				locationManager
					.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
			}
			break;
			default: {
				Log.w("CWL", "Unknow location provider");
			}
		}
	}
	
	/**
	 * End location pulling.
	 */
	public void endListening() {
		this.locationManager.removeUpdates(this.locationListener);
	}
}
