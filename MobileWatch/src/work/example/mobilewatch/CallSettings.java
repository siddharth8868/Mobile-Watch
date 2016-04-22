package work.example.mobilewatch;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallSettings extends Activity {
	Button save, edit;
	EditText f1, f2, amb, pol, fire, gas, antip, rep;
	SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.callsettings);
		pref = getSharedPreferences("calldb", Context.MODE_APPEND);

		f1 = (EditText) findViewById(R.id.callsettingsfriend1);
		f2 = (EditText) findViewById(R.id.callsettingsfriend2);
		amb = (EditText) findViewById(R.id.callsettingsambulance);
		pol = (EditText) findViewById(R.id.callsettingspolice);
		fire = (EditText) findViewById(R.id.callsettingsfire);
		gas = (EditText) findViewById(R.id.callsettingsgasleak);
		antip = (EditText) findViewById(R.id.callsettingsantipoison);
		rep = (EditText) findViewById(R.id.callsettingsreportabuse);
		edit = (Button) findViewById(R.id.editbt);
		save = (Button) findViewById(R.id.savebt);

		f1.setText(pref.getString("f1", "-"));
		f2.setText(pref.getString("f2", "-"));
		amb.setText(pref.getString("amb", "-"));
		pol.setText(pref.getString("pol", "-"));
		fire.setText(pref.getString("fire", "-"));
		antip.setText(pref.getString("antip", "-"));
		gas.setText(pref.getString("gas", "-"));
		rep.setText(pref.getString("ra", "-"));

	}

	public void edit(View v) {
		save.setEnabled(true);
		edit.setEnabled(false);
		f1.setEnabled(true);
		f2.setEnabled(true);
		amb.setEnabled(true);
		pol.setEnabled(true);
		fire.setEnabled(true);
		gas.setEnabled(true);
		antip.setEnabled(true);
		rep.setEnabled(true);

	}

	public void save(View v) {

		save.setEnabled(false);
		edit.setEnabled(true);
		f1.setEnabled(false);
		f2.setEnabled(false);
		amb.setEnabled(false);
		pol.setEnabled(false);
		fire.setEnabled(false);
		gas.setEnabled(false);
		antip.setEnabled(false);
		rep.setEnabled(false);

		SharedPreferences.Editor editor = pref.edit();
		editor.putString("f1", f1.getText().toString().trim());
		editor.putString("f2", f2.getText().toString().trim());
		editor.putString("amb", amb.getText().toString().trim());
		editor.putString("pol", pol.getText().toString().trim());
		editor.putString("fire", fire.getText().toString().trim());
		editor.putString("gas", gas.getText().toString().trim());
		editor.putString("antip", antip.getText().toString().trim());
		editor.putString("ra", rep.getText().toString().trim());
		
		editor.commit();
		Toast.makeText(this,"Contacts Updated",300).show();

	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.animationstill,R.anim.animationleft_right_back);

	}

}
