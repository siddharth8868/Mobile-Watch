package work.example.mobilewatch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Sms extends Activity {
	SmsManager sms;
	EditText msg;
	int pl=0,am=0,fi=0;
	SharedPreferences pref;
	LocationManager lm;
	Location location;
	ImageButton pol,fire,amb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms);
		msg = (EditText) findViewById(R.id.data);
		sms=SmsManager.getDefault();
		pref=getSharedPreferences("smsdb", Context.MODE_APPEND);
		lm= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		pol=(ImageButton)findViewById(R.id.callimgbt);
		amb=(ImageButton)findViewById(R.id.ambimgbt);
		fire=(ImageButton)findViewById(R.id.fireimgbt);

	}

	public void f1(View v) {
		String number=pref.getString("f1","-");
		
		if(number.equals("-"))
		{
			Toast.makeText(this,"please set friend-1 number in sms settings",300).show();
		}
		else{
			Intent i=new Intent(this,MyLoactionService.class);
			i.addCategory(MyLoactionService1.TABMY);
			i.putExtra("number",number);
			startService(i);
		}
		
		Toast.makeText(this,"Intimation Message send to Friend-1",300).show();
		
	}

	public void f2(View v) {
		
		String number=pref.getString("f2","-");
		
		if(number.equals("-"))
		{
			Toast.makeText(this,"please set friend-2 number in sms settings",300).show();
		}
		else{
			Intent i=new Intent(this,MyLoactionServiceSec.class);
			i.addCategory(MyLoactionService1.TABMY);
			i.putExtra("number",number);
			startService(i);
		}
		Toast.makeText(this,"Intimation Message send to Friend-1",300).show();
		
	}

	public void onMsgStop(View v) {
		
		try{
		Intent i=new Intent(this,MyLoactionService.class);
		i.addCategory(MyLoactionService1.TABMY);
		stopService(i);
		
		i=new Intent(this,MyLoactionServiceSec.class);
		i.addCategory(MyLoactionService1.TABMY);
		stopService(i);
		Toast.makeText(this,"Location Intimation Stopped",300).show();
		}
		catch(Exception e){
			
		}
		
	}
	
	public void settings(View v){
		Intent i = new Intent(this,SmsSettings.class);
		startActivity(i);
		overridePendingTransition( R.anim.animationright_left,R.anim.animationstill);

	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.animationstill,R.anim.animationright_left_back);

	}

	
	public void police(View v) {
		pol.setFocusable(true);
		pl=1;
		String ss="police\n"+msg.getText().toString();
		msg.setText(ss);
	}

	public void fire(View v) {
		fire.setFocusable(true);
		fi=1;
		String ss="fire\npolice\nambulance\n"+msg.getText().toString();
		msg.setText(ss);
	}

	public void ambulance(View v) {
		amb.setFocusable(true);
		am=1;
		String ss="ambulance\n"+msg.getText().toString();
		msg.setText(ss);
	}
	
	public void onSend(View v) {
		String dataorg=""+msg.getText().toString();
		String data=""+msg.getText().toString();
		String ss;
		data=data.toLowerCase();
		int aa=0;
		
		if(!(data.indexOf("police")==-1))
		{
			aa=1;
			ss=pref.getString("pol","-");
			if(ss.equals("-"))
			{
				Toast.makeText(getApplicationContext(),"please set police number in sms settings",300).show();
				
			}
			else{
			location =GetLastLocation.get(this);
			String text=dataorg+"\nAt:\nlat:"+location.getLatitude()+"\nlng:"+location.getLongitude()+"\n Refer to:\n"+"http://maps.google.com/maps?z=12&t=m&q=loc:"+location.getLatitude()+"+"+location.getLongitude()+"";
			sms.sendTextMessage(ss, null, text, null, null);
			}
			
		}
		if(!(data.indexOf("fire")==-1))
		{
			aa=1;
			ss=pref.getString("fir","-");
			if(ss.equals("-"))
			{
				Toast.makeText(getApplicationContext(),"please set fire number in sms settings",300).show();
			}
			else{
				location =GetLastLocation.get(this);
				String text=dataorg+"\nAt:\nlat:"+location.getLatitude()+"\nlng:"+location.getLongitude()+"\n Refer to:\n"+"http://maps.google.com/maps?z=12&t=m&q=loc:"+location.getLatitude()+"+"+location.getLongitude()+"";
				sms.sendTextMessage(ss, null, text, null, null);			}
			
		}
		if(!(data.indexOf("ambulance")==-1))
		{
			aa=1;
			ss=pref.getString("amb","-");
			if(ss.equals("-"))
			{
				Toast.makeText(getApplicationContext(),"please set ambulance number in sms settings",300).show();
			}
			else{
				location =GetLastLocation.get(this);
				String text=dataorg+"\nAt:\nlat:"+location.getLatitude()+"\nlng:"+location.getLongitude()+"\n Refer to:\n"+"http://maps.google.com/maps?z=12&t=m&q=loc:"+location.getLatitude()+"+"+location.getLongitude()+"";
				sms.sendTextMessage(ss, null, text, null, null);			}
			
		}
		
		if(aa==1){
			Toast.makeText(this,"Message sent",300).show();
		}
		else
		{
			Toast.makeText(this,"Please select option or type text",300).show();
		}
	}
	
	
}
