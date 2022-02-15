package com.oneparcent.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText n1;
    EditText n2;
    Button b;
    TextView ans;
    public void sum(View e)
    {
        int i,j,an;
        ans=(TextView)findViewById(R.id.textView2);
        i=Integer.parseInt(n1.getText().toString());
        j=Integer.parseInt(n2.getText().toString());
        an=i+j;
        ans.setText(Integer.toString(an));
    }
    public void sub(View e)
    {
        int i,j,an;
        ans=(TextView)findViewById(R.id.textView2);
        i=Integer.parseInt(n1.getText().toString());
        j=Integer.parseInt(n2.getText().toString());
        an=i-j;
        ans.setText(Integer.toString(an));
    }
    public void mul(View e)
    {
        int i,j,an;
        ans=(TextView)findViewById(R.id.textView2);
        i=Integer.parseInt(n1.getText().toString());
        j=Integer.parseInt(n2.getText().toString());
        an=i*j;
        ans.setText(Integer.toString(an));
    }
    public void div(View e)
    {
        int i,j,an;
        ans=(TextView)findViewById(R.id.textView2);
        i=Integer.parseInt(n1.getText().toString());
        j=Integer.parseInt(n2.getText().toString());
        an=i/j;
        ans.setText(Integer.toString(an));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n1=(EditText)findViewById(R.id.editTextNumberDecimal3);
        n2=(EditText)findViewById(R.id.editTextNumberDecimal4);

    }
}