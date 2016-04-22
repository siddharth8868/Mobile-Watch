package work.example.mobilewatch;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Maps extends Activity {

	Intent i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);
	}

	public void hospital(View v) {

		i = new Intent(android.content.Intent.ACTION_VIEW, 
			    Uri.parse("http://maps.google.co.in/maps?q=hospitals"));
			startActivity(i);
	}

	public void fire(View v) {
		i = new Intent(android.content.Intent.ACTION_VIEW, 
			    Uri.parse("http://maps.google.co.in/maps?q=fire station"));
			startActivity(i);
	}

	public void police(View v) {
		i = new Intent(android.content.Intent.ACTION_VIEW, 
			    Uri.parse("http://maps.google.co.in/maps?q=police"));
			startActivity(i);
	}

	public void pharmacy(View v) {
		i = new Intent(android.content.Intent.ACTION_VIEW, 
			    Uri.parse("http://maps.google.co.in/maps?q=pharmacy"));
			startActivity(i);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.animationstill, R.anim.animationleft_right_back);
	}

}
