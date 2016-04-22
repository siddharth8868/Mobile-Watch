package work.example.mobilewatch;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class MyLoactionService extends Service {

	public static String TABMY = "MYTABOOTAB";

	private LocationRequest mLocationRequest;
	SharedPreferences mPrefs,pref;
	SharedPreferences.Editor mEditor;
	android.location.LocationListener locationListner;
	LocationManager lm;
	boolean mUpdatesRequested = false;
	
	SmsManager sms;
	String number,numberTwo;

	@Override
	public IBinder onBind(Intent intent) {

		//number = intent.getStringExtra("number");
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);

		pref= getSharedPreferences("smsdb",Context.MODE_APPEND);
		number= pref.getString("f1","0");
		numberTwo= pref.getString("f2","0");
		sms = SmsManager.getDefault();

		Location location = GetLastLocation.get(this);
		String text="I'm in Danger\nAt:\nlat:"+location.getLatitude()+"\nlng:"+location.getLongitude()+"\n Refer to:\n"+"http://maps.google.com/maps?z=12&t=m&q=loc:"+location.getLatitude()+"+"+location.getLongitude()+"";
		sms.sendTextMessage(number, null, text, null, null);
		sms.sendTextMessage(numberTwo, null, text, null, null);
		
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListner = new android.location.LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				Toast.makeText(
						MyLoactionService.this,
						"GPSlat/log=" + location.getLatitude() + "/"
								+ location.getLongitude(), Toast.LENGTH_SHORT).show();
				Log.e("location:", "GPSlat/log=" + location.getLatitude() + "/"
						+ location.getLongitude());
				String text="I'm in Danger\nAt:\nlat:"+location.getLatitude()+"\nlng:"+location.getLongitude()+"\n Refer to:\n"+"http://maps.google.com/maps?z=12&t=m&q=loc:"+location.getLatitude()+"+"+location.getLongitude()+"";
				sms.sendTextMessage(number, null, text, null, null);
				sms.sendTextMessage(numberTwo, null, text, null, null);				
			}
		}; 
			
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, (long)900000, (float)10000, locationListner);


	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			lm.removeUpdates(locationListner);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
