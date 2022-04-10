package com.oneparcent.practical10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper myDatabse;
    EditText etName,etSname,etMark;
    Button insertdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabse=new DBHelper (this);
        etName=(EditText)findViewById (R.id.cid);
        etSname=(EditText)findViewById (R.id.cname);
        etMark=(EditText)findViewById (R.id.cOrderid);
        insertdata=(Button)findViewById (R.id.button);
        Add ();
    }
    public void Add(){
        insertdata.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                boolean Insert = myDatabse.Insert(etName.getText().toString(), etSname.getText().toString(), etMark.getText().toString());
                if (Insert=true){
                    Toast.makeText(MainActivity.this, "data inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
