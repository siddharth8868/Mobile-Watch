package work.example.mobilewatch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Call extends Activity {
	
	SharedPreferences pref;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.call);
		pref = getSharedPreferences("calldb",Context.MODE_APPEND);
		
	}
	
	
	public void settings(View v)
	{
		Intent i = new Intent(this,CallSettings.class);
		startActivity(i);
		overridePendingTransition(R.anim.animationleft_right,R.anim.animationstill);
		
	}
	
	public void friend1(View v)
	{
		calls(1);
	}
	public void friend2(View v)
	{
		calls(2);

	}
	public void ambulance(View v)
	{
		calls(3);

	}
	public void police(View v)
	{
		calls(4);

	}
	public void fire(View v)
	{
		calls(5);

	}
	public void antiPoison(View v)
	{
		calls(6);

	}
	public void gasLeak(View v)
	{
		calls(7);

	}
	public void reportAbuse(View v)
	{
		calls(8);

	}
	
	public void calls(int i)
	{
		String number="-";
		switch (i) {
		case 1:
			number=pref.getString("f1","-");
			break;
		case 2:
			number=pref.getString("f2","-");
			break;
		case 3:
			number=pref.getString("amb","-");
			break;
		case 4:
			number=pref.getString("pol","-");
			break;
		case 5:
			number=pref.getString("fir","-");
			break;
		case 6:
			number=pref.getString("antip","-");
			break;
		case 7:
			number=pref.getString("gas","-");
			break;
		case 8:
			number=pref.getString("ra","-");
			break;

		}
		
		if(number.equals("-"))
		{
			Toast.makeText(this,"please set the numbers",300).show();
		}
		else{
			Intent is = new Intent(Intent.ACTION_CALL);
			is.setData(Uri.parse("tel:"+number));
			startActivity(is);
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.animationstill,R.anim.animationleft_right_back);
	}

}
