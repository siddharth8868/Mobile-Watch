package work.example.mobilewatch;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainPage extends Activity {
	ImageButton callref,msgref;
	SharedPreferences pref,pref1;
	RelativeLayout call,sms,map,report;
	Animation anim1,anim2,anim3,anim4;
	LinearLayout chbg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		callref=(ImageButton)findViewById(R.id.callbt);
		msgref=(ImageButton)findViewById(R.id.smsbt);
		
		call=(RelativeLayout) findViewById(R.id.rcall);
		sms=(RelativeLayout) findViewById(R.id.rsms);
		map=(RelativeLayout) findViewById(R.id.rmaps);
		report=(RelativeLayout) findViewById(R.id.rreport);
		
		chbg=(LinearLayout)findViewById(R.id.llbg);
		
		anim1=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animations1);
		anim2=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animations2);
		anim3=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animations3);
		anim4=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animations4);
		
		call.startAnimation(anim1);
		sms.startAnimation(anim2);
		map.startAnimation(anim3);
		report.startAnimation(anim4);
		
		
		
		pref=getSharedPreferences("calldb", Context.MODE_APPEND);
		pref1=getSharedPreferences("smsdb", Context.MODE_APPEND);
		
		callref.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),Call.class);
				startActivity(i);
				overridePendingTransition(R.anim.animationleft_right,R.anim.animationstill);
				return false;
			}
		});
		
		msgref.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),Sms.class);
				startActivity(i);
				overridePendingTransition(R.anim.animationright_left,R.anim.animationstill);
				return false;
			}
		});
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		anim1=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animations1);
		anim2=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animations2);
		anim3=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animations3);
		anim4=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animations4);
		
		call.startAnimation(anim1);
		sms.startAnimation(anim2);
		map.startAnimation(anim3);
		report.startAnimation(anim4);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		anim1=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animations1);
		anim2=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animations2);
		anim3=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animations3);
		anim4=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animations4);
		
		call.startAnimation(anim1);
		sms.startAnimation(anim2);
		map.startAnimation(anim3);
		report.startAnimation(anim4);
	}
	
	public void help(View v){
	Intent i=new Intent(getApplicationContext(),Help.class);
	startActivity(i);
	}
	
	

	public void call(View v) {
		    String number=pref.getString("f1", "-");
		    
		    if(number.equals("-"))
			{
				Toast.makeText(this,"please set friend-1 number in call settings",300).show();
			}
			else{
				Intent is = new Intent(Intent.ACTION_CALL);
				is.setData(Uri.parse("tel:"+number));
				startActivity(is);
			}
	

	}

	public void sms(View v) {
		
	    String number=pref1.getString("f1", "-");
	    
	    if(number.equals("-"))
		{
			Toast.makeText(this,"please set friend-1 number in sms settings",300).show();
		}
		else{
			Intent i=new Intent(this,MyLoactionService.class);
			i.addCategory(MyLoactionService.TABMY);
			startService(i);
			Toast.makeText(this,"Message Sent",300).show();
		}
	    
	    number=pref1.getString("f2", "-");
	    
	    if(number.equals("-"))
		{
			Toast.makeText(this,"please set friend-2 number in sms settings",300).show();
		}
		else{
			Intent i=new Intent(this,MyLoactionServiceSec.class);
			i.addCategory(MyLoactionServiceSec.TABMY);
			startService(i);
			Toast.makeText(this,"Message Sent",300).show();
		}

	}

	public void maps(View v) {
		Intent i = new Intent(this,Maps.class);
		startActivity(i);
		overridePendingTransition(R.anim.animationleft_right, R.anim.animationstill);
	}

	public void reportIssue(View v) {
		Intent i =new Intent(this,ReportIssue.class);
		startActivity(i);
		overridePendingTransition(R.anim.animationright_left,R.anim.animationstill);

	}

}
