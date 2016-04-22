 package work.example.mobilewatch;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ReportIssue extends Activity {
	
	Spinner sp;
	EditText sub,pro;
	TextView lat,lng;
	Uri uri;
	ImageView img;
	
	final int REQUEST_PICKMAP_ID=1;
	final int REQUEST_PICKIMAGE_ID=2;
	SharedPreferences pref;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reportissue);
		sp=(Spinner)findViewById(R.id.reportissuespinner);
		sub=(EditText)findViewById(R.id.reportissuesubject);
		pro=(EditText)findViewById(R.id.reportissueproblem);
		lat=(TextView)findViewById(R.id.reportissuelat);
		lng=(TextView)findViewById(R.id.reportissuelng);
		img=(ImageView) findViewById(R.id.reportissueimagepic);
		img.setAdjustViewBounds(true);
		uri=Uri.fromFile(new File("/sdcard/flashCropped.png"));
		pref= getSharedPreferences("reportdb",Context.MODE_APPEND);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.animationstill,R.anim.animationright_left_back);
	}
	
	
	public void selectMap(View v)
	{
		Intent i = new Intent(this,PickMap.class);
		startActivityForResult(i, REQUEST_PICKMAP_ID);
	}
	
	public void pickimage(View V)
	{
		Intent camera =new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	    camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
	    startActivityForResult(camera,REQUEST_PICKIMAGE_ID);
	}
	
	
	public void submit(View v)
	{
		if(lat.getText().toString().equals("-"))
		{
			Toast.makeText(getApplicationContext(),"please select loction from map",300).show();
		}
		else{
		String sps="";
			if(sp.getSelectedItem().toString().equals("Roads"))
		{
			sps=pref.getString("road","");
		}
		else if (sp.getSelectedItem().toString().equals("Electricity")) {
			sps=pref.getString("elec","");
		}
		else if (sp.getSelectedItem().toString().equals("Water")) {
			sps=pref.getString("wat","");
		}
		else if (sp.getSelectedItem().toString().equals("Sewage")) {
			sps=pref.getString("san","");
		}
		
		String[] str={sps};
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("text/plain");
		i.putExtra(Intent.EXTRA_EMAIL,str);
		i.putExtra(Intent.EXTRA_SUBJECT,""+sub.getText().toString());
		i.putExtra(Intent.EXTRA_TEXT,""+pro.getText().toString());
		if(img.getVisibility() == View.VISIBLE)
		{
			i.putExtra(Intent.EXTRA_STREAM, uri);
		}
		startActivity(i);
		
		}
	}
	
	public void settings(View v)
	{
		Intent i = new Intent(this,ReportIssueSettings.class);
		startActivity(i);
		overridePendingTransition(R.anim.animationright_left,R.anim.animationstill);

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == REQUEST_PICKMAP_ID)
		{
			if(resultCode == RESULT_OK)
			{
				Log.e("msss","result ok");
				String slat,slng;
				slat = data.getStringExtra("lat");
				slng = data.getStringExtra("lng");
				lat.setText(slat);
				lng.setText(slng);
			}
			else{
				Toast.makeText(this,"Location not selected",300).show();
			}
		}
		
		if(requestCode == REQUEST_PICKIMAGE_ID)
		{
			if(resultCode == RESULT_OK)
			{
				img.setVisibility(View.VISIBLE);
				img.setImageURI(uri);
			}
			else{
				Toast.makeText(this,"picture not selected",300).show();
			}
		}
	}

	
}
