 package work.example.mobilewatch;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class GetLastLocation {
	
	public static Location get(Context context){
		//Location loc  = new Location(LocationManager.NETWORK_PROVIDER);
		LocationManager lm = (LocationManager) context.getSystemService(LocationManager.NETWORK_PROVIDER);
		Location loc = new Location("DummyLocation");
		try{
		loc= lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		catch(Exception e){
			loc.setLatitude(0);
			loc.setLongitude(0);
		}
		
		SharedPreferences pref=context.getSharedPreferences("perfectloc",Context.MODE_APPEND);
		 Log.e("lat", pref.getString("lat","0"));
		 Log.e("lng", pref.getString("lng","0"));
		 String lat,lng;
		 lat= pref.getString("lat","0");
		 lng=pref.getString("lng","0");
		
		return loc;
	}

}
