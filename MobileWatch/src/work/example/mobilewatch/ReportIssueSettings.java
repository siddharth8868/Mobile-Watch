package work.example.mobilewatch;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReportIssueSettings extends Activity {
	EditText road,elec,san,wat;
	Button edit,save;
	SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reportsettings);
		pref=getSharedPreferences("reportdb", Context.MODE_APPEND);
		road=(EditText)findViewById(R.id.reportissueroads);
		elec=(EditText)findViewById(R.id.reportissueelec);
		san=(EditText)findViewById(R.id.reportissuesan);
		wat=(EditText)findViewById(R.id.reportissuewat);
		
		edit=(Button)findViewById(R.id.reditbt);
		save=(Button)findViewById(R.id.rsavebt);
		
		road.setText(pref.getString("road", ""));
		elec.setText(pref.getString("elec", ""));
		san.setText(pref.getString("san", ""));
		wat.setText(pref.getString("wat", ""));
		
		
	}
	
	public void save(View v){
		edit.setEnabled(true);
		save.setEnabled(false);
		road.setEnabled(false);
		elec.setEnabled(false);
		san.setEnabled(false);
		wat.setEnabled(false);
		
		SharedPreferences.Editor editor=pref.edit();
		editor.putString("road", road.getText().toString());
		editor.putString("elec", elec.getText().toString());
		editor.putString("san", san.getText().toString());
		editor.putString("wat", wat.getText().toString());
		editor.commit();
		
		Toast.makeText(this,"E-mail's Updated",300).show();
		
	
	}
	
	public void edit(View v){
		edit.setEnabled(false);
		save.setEnabled(true);
		road.setEnabled(true);
		elec.setEnabled(true);
		san.setEnabled(true);
		wat.setEnabled(true);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.animationstill,R.anim.animationright_left_back);
	}
	
}
