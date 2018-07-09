package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender =  findViewById(R.id.RadioGroupGender);
    }



    @Override
    protected void onPause() {
        super.onPause();
        String name = etName.getText().toString();
        String GPA = etGPA.getText().toString();
        int checkedID = rgGender.getCheckedRadioButtonId();

        Float fGPA = Float.parseFloat(GPA);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("uName",name);
        prefEdit.putFloat("uGPA",fGPA);
        prefEdit.putInt("uGender",checkedID);
        prefEdit.commit();
    }

        @Override
        protected void onResume() {
            super.onResume();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

            int gdData = prefs.getInt("uGender",-1);
            String nData = prefs.getString("uName","");
            float res = prefs.getFloat("uGPA",0);
            String gData = Float.toString(res);

            rgGender.check(gdData);
            etName.setText(nData);
            etGPA.setText(gData);

        }
}
