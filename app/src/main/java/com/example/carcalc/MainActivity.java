package com.example.carcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText costBox;
    private EditText downBox;
    private EditText aprBox;
    private RadioButton loanBox;
    private RadioButton leaseBox;
    private TextView monthNum;
    private SeekBar monthBar;
    private EditText paymentBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        costBox = findViewById(R.id.costBox);
        downBox = findViewById(R.id.downBox);
        aprBox = findViewById(R.id.aprBox);
        loanBox = findViewById(R.id.loanBox);
        leaseBox = findViewById(R.id.leaseBox);
        monthNum = findViewById(R.id.monthNum);
        monthBar = findViewById(R.id.monthBar);
        paymentBox = findViewById(R.id.paymentBox);

        monthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                monthNum.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calculate(View v){
        if (costBox.length()>0 && downBox.length()>0 && aprBox.length()>0){
            String costInput = costBox.getText().toString();
            String downInput = downBox.getText().toString();
            String aprInput = aprBox.getText().toString();
            String monthInput = monthNum.getText().toString();
            double costValue = Double.parseDouble(costInput);
            double downValue = Double.parseDouble(downInput);
            double aprValue = Double.parseDouble(aprInput);
            double monthValue = Double.parseDouble(monthInput);
            if(monthValue>0){
                if (loanBox.isChecked()) {
                    double monthPay = ((aprValue/100) / 12) * (costValue - downValue) / (1 - Math.pow(1 + ((aprValue/100) / 12), -1 * monthValue));
                    paymentBox.setText(String.format("%.2f", monthPay));
                }else{
                    monthNum.setText("36");
                    double monthPay = ((aprValue/100) / 12) * ((costValue/3) - downValue) / (1 - Math.pow(1 + ((aprValue/100) / 12), -36));
                    paymentBox.setText(String.format("%.2f", monthPay));
                }
            }else{
                Toast.makeText(this, "Number of months must be greater than 0", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Required field(s) contain no input", Toast.LENGTH_SHORT).show();
        }
    }
}
