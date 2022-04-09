package com.oneparcent.practical6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final Calendar myCalender = Calendar.getInstance();
    final String myFormat = "dd/mm/yy";
    final SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

    String fieldCalled = "issueDate";
    TextView etBookName, etIssueDate, etDueDate, etSubmitDate, etOverdueCharge;
    TextView tvTotalOverdue;
    Button btnSubmit;
    DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayofMonth) -> {
        myCalender.set(Calendar.YEAR, year);
        myCalender.set(Calendar.MONTH, month);
        myCalender.set(Calendar.DAY_OF_MONTH, dayofMonth);
        updateLable();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBookName = findViewById(R.id.bookName);
        etIssueDate = findViewById(R.id.issueDate);
        etDueDate = findViewById(R.id.dueDate);
        etSubmitDate = findViewById(R.id.submitDate);
        etOverdueCharge = findViewById(R.id.overdueCharge);
        tvTotalOverdue = findViewById(R.id.totalOverdue);
        btnSubmit = findViewById(R.id.submit);

        etIssueDate.setOnClickListener(v -> {
            new DatePickerDialog(MainActivity.this,dateSetListener,
                    myCalender.get(Calendar.YEAR),
                    myCalender.get(Calendar.MONTH),
                    myCalender.get(Calendar.DAY_OF_MONTH)).show();
            fieldCalled = "issueDate";
        });

        etDueDate.setOnClickListener(v -> {
            new DatePickerDialog(MainActivity.this,dateSetListener,
                    myCalender.get(Calendar.YEAR),
                    myCalender.get(Calendar.MONTH),
                    myCalender.get(Calendar.DAY_OF_MONTH)).show();
            fieldCalled = "dueDate";
        });

        etSubmitDate.setOnClickListener(v -> {
            new DatePickerDialog(MainActivity.this,dateSetListener,
                    myCalender.get(Calendar.YEAR),
                    myCalender.get(Calendar.MONTH),
                    myCalender.get(Calendar.DAY_OF_MONTH)).show();
            fieldCalled = "submitDate";
        });

        btnSubmit.setOnClickListener(v -> claculateOverdue());
    }

    public void updateLable(){
        switch (fieldCalled){
            case "issueDate":
                etIssueDate.setText(sdf.format(myCalender.getTime()));
                break;
            case "dueDate":
                etDueDate.setText(sdf.format(myCalender.getTime()));
                break;
            case "submitDate":
                etSubmitDate.setText(sdf.format(myCalender.getTime()));
                break;
        }
    }

    public void claculateOverdue(){
        String dueDateStr = etDueDate.getText().toString().trim();
        String submitDateStr = etSubmitDate.getText().toString().trim();
        String overdueChargeStr = etOverdueCharge.getText().toString().trim();

        if (dueDateStr.isEmpty()){
            Toast.makeText(this, "Please enter due date", Toast.LENGTH_SHORT).show();
            return;
        }
        if (submitDateStr.isEmpty()){
            Toast.makeText(this, "Please enter submit date", Toast.LENGTH_SHORT).show();
            return;
        }
        if (overdueChargeStr.isEmpty()){
            Toast.makeText(this, "Plrase enter  overdue charge", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Date dueDate = sdf.parse(dueDateStr);
            Date submitDate = sdf.parse(submitDateStr);

            long diff = submitDate.getTime() - dueDate.getTime();

            if (diff > 0) {
                int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                double overdueCharge = Double.parseDouble(overdueChargeStr);
                double totalOverdueCharge = days * overdueCharge;

                @SuppressLint("DefaultLocale")
                        String displayText = "Total overdue charge :  ₹ " + String.format("%.2f", totalOverdueCharge) + "/-";
                tvTotalOverdue.setText(displayText);
            }
            else {
                String displayText = "No overdue charge !";
                tvTotalOverdue.setText(displayText);
            }
        }
        catch (ParseException e){
            e.printStackTrace();
        }
    }

}