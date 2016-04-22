package work.example.mobilewatch;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class PickMap extends Activity {
	
	String lat="-",lng="-";
	GoogleMap map;
	MarkerOptions marker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pickmap);
		marker =new MarkerOptions();
		map = ((MapFragment)getFragmentManager().findFragmentById(R.id.pickmapfragment)).getMap();
		
		LocationManager lm= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location loc =GetLastLocation.get(this);
        Toast.makeText(getBaseContext(), "lat:"+loc.getLatitude()+"\n"+"lng:"+loc.getLongitude(), Toast.LENGTH_SHORT).show();

		LatLng lt=new LatLng(loc.getLatitude(),loc.getLongitude());
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(lt, 17));
		marker.position(lt);
		map.addMarker(marker);
		
		map.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public void onMapClick(LatLng lt) {
				map.clear();
				marker.position(lt);
				map.addMarker(marker);
				lat=""+lt.latitude;
				lng=""+lt.longitude;
				
		
			}
		});
		
	}
	

	
		

	
	
	public void selected(View v)
	{
		Intent i=new Intent(this,ReportIssue.class);
		if(lat.equals("-")  || lng.equals("-"))
		{
			setResult(RESULT_CANCELED, i);
		}
		else{
		Log.e("msss","got some data\n"+lat+"\n"+lng);
		i.putExtra("lat",lat);
		i.putExtra("lng",lng);
		setResult(RESULT_OK,i);
		}
		finish();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("satellite");
		menu.add("normal");
		menu.add("terrian");
		menu.add("hybrid");
		menu.add("none");
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
String title=item.getTitle().toString();
		
		if(title.equals("satellite"))
		{
			map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		}
		else if(title.equals("normal"))
		{
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		}
		else if(title.equals("terrian"))
		{
			map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
		}
		else if(title.equals("hybrid"))
		{
			map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			
		}else if(title.equals("none"))
		{
			map.setMapType(GoogleMap.MAP_TYPE_NONE);
		}
		
		return super.onOptionsItemSelected(item);
	}
}
