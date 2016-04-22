package work.example.mobilewatch;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsSettings extends Activity{
	
	EditText f1,f2,pol,fir,amb;
	Button save,edit;
	SharedPreferences pref;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smssetting);
		save = (Button) findViewById(R.id.smssettingsave);
		edit = (Button) findViewById(R.id.smssettingedit);
		f1=(EditText) findViewById(R.id.smssettingfriend1);
		f2=(EditText) findViewById(R.id.smssettingfriend2);
		pol=(EditText) findViewById(R.id.smssettingpolice);
		fir=(EditText) findViewById(R.id.smssettingfire);
		amb=(EditText) findViewById(R.id.smssettingambulance);
		
		pref = getSharedPreferences("smsdb",Context.MODE_APPEND);
		f1.setText(pref.getString("f1","-"));
		f2.setText(pref.getString("f2","-"));
		pol.setText(pref.getString("pol","-"));
		fir.setText(pref.getString("fir","-"));
		amb.setText(pref.getString("amb","-"));
	}
	
	
	public void edit(View v)
	{
		save.setEnabled(true);
		edit.setEnabled(false);
		f1.setEnabled(true);
		f2.setEnabled(true);
		pol.setEnabled(true);
		fir.setEnabled(true);
		amb.setEnabled(true);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.animationstill, R.anim.animationright_left_back);
	}
	
	public void save(View v)
	{
		
		save.setEnabled(false);
		edit.setEnabled(true);
		f1.setEnabled(false);
		f2.setEnabled(false);
		pol.setEnabled(false);
		fir.setEnabled(false);
		amb.setEnabled(false);
		
		SharedPreferences.Editor editor = pref.edit();
		editor.putString("f1",f1.getText().toString());
		editor.putString("f2",f2.getText().toString());
		editor.putString("pol",pol.getText().toString());
		editor.putString("fir",fir.getText().toString());
		editor.putString("amb",amb.getText().toString());
		editor.commit();
		
		Toast.makeText(this,"Contacts Updated",300).show();
	}
	
	

}
