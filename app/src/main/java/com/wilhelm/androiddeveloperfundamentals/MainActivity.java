package com.wilhelm.androiddeveloperfundamentals;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.function.Predicate;

public class MainActivity extends AppCompatActivity {

    Predicate<String> isNumeric = phoneNr -> {
        if (phoneNr == null)
            return false;
        else return
                phoneNr.matches("^[0-9]+$");
    };


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText emailText = findViewById(R.id.emailText);
        EditText phoneNumber = findViewById(R.id.phoneText);
        Button button = findViewById(R.id.submitButton);
        CheckBox checkBox = findViewById(R.id.checkbox);

        TextView output = findViewById(R.id.output);


        button.setOnClickListener(v -> {
            String inputMail = emailText.getText().toString();
            String phoneNr = phoneNumber.getText().toString();
            if (TextUtils.isEmpty(inputMail) || !inputMail.matches(Patterns.EMAIL_ADDRESS.pattern())) {
                emailText.setError("Fill in E-mail!");
                return;
            } else
                emailText.setError(null);
            if (!isNumeric.test(phoneNr)) {
                phoneNumber.setError("Fill in Phone");
                return;
            } else
                phoneNumber.setError(null);
            if (!checkBox.isChecked()) {
                //checkBox.setError("Check Box");
                Toast.makeText(MainActivity.this, "Check checkBox", Toast.LENGTH_LONG).show();
                return;
            }

            output.setText(getString(R.string.output, inputMail, phoneNr));


        });
    }


}