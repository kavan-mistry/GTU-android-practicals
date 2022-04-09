package com.oneparcent.practical5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public EditText e1;
    public Button b1;
    public Button b2;
    public TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=(EditText)findViewById(R.id.editTextTextPersonName);
        b1=(Button) findViewById(R.id.button3);
        b2=(Button) findViewById(R.id.button4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i;
                i=Integer.parseInt(e1.getText().toString());
                t=(TextView) findViewById(R.id.textView2);
                double dol;
                dol=i*0.014;
                t.setText(Double.toString(dol));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i;
                i=Integer.parseInt(e1.getText().toString());
                t=(TextView) findViewById(R.id.textView2);
                double euro;
                euro=i*0.012;
                t.setText(Double.toString(euro));
            }
        });
    }
}