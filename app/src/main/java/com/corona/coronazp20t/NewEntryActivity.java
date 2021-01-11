package com.corona.coronazp20t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class NewEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        setTitle("New Entry");

        Intent intent = getIntent();
        Corona corona = (Corona) intent.getSerializableExtra(Adapter.ENTRY);
        Toast.makeText(this, "country: " + corona.getKeyId(), Toast.LENGTH_SHORT).show();

        // pasiimsim visus elementus is xml
        final CheckBox checkBoxLithuania = findViewById(R.id.counrty_lithuania);
        final CheckBox checkBoxLatvia = findViewById(R.id.counrty_latvia);
        final CheckBox checkBoxEstonia = findViewById(R.id.counrty_estonia);
        final CheckBox checkBoxPoland = findViewById(R.id.counrty_poland);

        final RadioGroup radioGroup = findViewById(R.id.radio_group);
        final RadioButton radio500 = findViewById(R.id.radio500);

        final Spinner spinner = findViewById(R.id.last_updated_id);
        ArrayList <String> updateList = new ArrayList<String>();
        updateList.add(corona.getLastUpdate());
        updateList.add(getResources().getString(R.string.new_entry_date1));
        updateList.add(getResources().getString(R.string.new_entry_date2));
        updateList.add(getResources().getString(R.string.new_entry_date3));
        updateList.add(getResources().getString(R.string.new_entry_date4));

        //adapteris reikalingas sujungti isdestyma su sarasu
        ArrayAdapter <String> dataAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                updateList
        );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapateri idedame-susiejame i spineri
        spinner.setAdapter(dataAdapter);

        final EditText editTextConfirmed = findViewById(R.id.confirmed_input);

        Button btnNewEntry = findViewById(R.id.new_entry_btn);

        // uzpildysime visus elementus coronos informacija

        checkBoxLithuania.setText(corona.getKeyId());
        radio500.setText(String.valueOf(corona.getDeaths()));// konvertuojame int i string
        editTextConfirmed.setText(String.valueOf(corona.getConfirmed()));

        // ant mygtuko paspaudimo parodysime vartotojo ivesta- koreguota informacija

        btnNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countries = "";
                if(checkBoxLithuania.isChecked()) {
                    countries+= checkBoxLithuania.getText().toString() + ", ";
                }
                if(checkBoxLatvia.isChecked()) {
                    countries+= checkBoxLatvia.getText().toString() + ", ";
                }
                if(checkBoxEstonia.isChecked()) {
                    countries+= checkBoxEstonia.getText().toString() + ", ";
                }
                if(checkBoxPoland.isChecked()) {
                    countries+= checkBoxPoland.getText().toString() + " ";
                }

                //gauname pasirinta radio buttona is radio groupo
                int selectedRadioGroupId = radioGroup.getCheckedRadioButtonId();
                //surandame radio butona pagal grazinta id is radio grupes
                RadioButton selectedButton = (RadioButton) findViewById(selectedRadioGroupId);
                int deaths = Integer.parseInt(selectedButton.getText().toString());

                String updateDate = String.valueOf(spinner.getSelectedItem());

                String confirmed = editTextConfirmed.getText().toString();

                editTextConfirmed.setError(null);
                if(Validation.isValidNumber(confirmed)) {
                    //public Corona(String lastUpdate, String keyId, int confirmed, int deaths) {
                    Corona corona = new Corona(updateDate, countries, Integer.parseInt(confirmed), deaths);
                    //atvaizduojame vartotojui objekto informacija
                    Toast.makeText(
                            NewEntryActivity.this,
                            "Country(ies): " + corona.getKeyId() + "\n" +
                            "Last update: " + corona.getLastUpdate()+ "\n" +
                             "Confirmed: " + corona.getConfirmed()+ "\n" +
                            "Deaths: " + corona.getDeaths(),
                            Toast.LENGTH_SHORT
                    ).show();
                } else {// blogai ivesti confirmed duomenys
                    editTextConfirmed.setError(getResources().getString(R.string.new_entry_invalid_confirmed));
                    editTextConfirmed.requestFocus();
                }
            }
        });

    }

}