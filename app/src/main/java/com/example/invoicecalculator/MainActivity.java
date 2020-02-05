package com.example.invoicecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText subtotal;
    private TextView discountAmount;
    private TextView discountTotal;
    private TextView totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        subtotal = findViewById(R.id.subtotal_input);
        discountAmount = findViewById(R.id.discount_amount);
        discountTotal = findViewById(R.id.discount_amount_input);
        Button plusOne = findViewById(R.id.plusBtn);
        Button minusOne = findViewById(R.id.minusBtn);
        totalAmount = findViewById(R.id.total_amount);

        plusOne.setOnClickListener((v) -> {
            discountAmount.setText(String.valueOf(getDiscountAmount() + 1).concat("%"));
            if (!subtotal.getText().toString().isEmpty()){
                calculateTotals();
            }
        });

        minusOne.setOnClickListener((v) -> {
            discountAmount.setText(String.valueOf(getDiscountAmount() - 1).concat("%"));
            if (!subtotal.getText().toString().isEmpty()){
                calculateTotals();
            }
        });

        subtotal.setOnClickListener((v) -> {
            calculateTotals();
        });
    }

    private int getDiscountAmount(){
        return Integer.parseInt(discountAmount.getText().toString().replace("%",""));
    }

    private double getSubTotal(){
        return Double.parseDouble(subtotal.getText().toString());
    }

    private String getDiscountTotalString(){
        return String.format(Locale.CANADA,"%.02f", getDiscount());
    }

    private double getDiscount(){
        return getSubTotal() * (getDiscountAmount() / 100.00);
    }

    private double getTotal(){
        return getSubTotal() + getDiscount();
    }

    private String getTotalString(){
        return String.format(Locale.CANADA, "%.02f", getTotal());
    }

    private void calculateTotals(){
        discountTotal.setText("$".concat(getDiscountTotalString()));
        totalAmount.setText("$".concat(getTotalString()));
    }
}
