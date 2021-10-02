package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private enum Operator {none, add, minus, multiply, divide, equals}

    private double data1 = 0, data2 = 0;
    private Operator opt = Operator.none;
    private boolean hasDot = false;
    private boolean requireClean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickNumericalbtn(View view) {
        int pressID = view.getId();
        TextView curtext = findViewById(R.id.resultText);

        if (opt == Operator.equals) {
            opt = Operator.none;
            curtext.setText("");
        }
        if (requireClean) {
            curtext.setText("");
            requireClean = false;
        }

        switch (pressID) {
            case R.id.btn0:
                curtext.setText(curtext.getText() + "0");
                break;
            case R.id.btn1:
                curtext.setText(curtext.getText() + "1");
                break;
            case R.id.btn2:
                curtext.setText(curtext.getText() + "2");
                break;
            case R.id.btn3:
                curtext.setText(curtext.getText() + "3");
                break;
            case R.id.btn4:
                curtext.setText(curtext.getText() + "4");
                break;
            case R.id.btn5:
                curtext.setText(curtext.getText() + "5");
                break;
            case R.id.btn6:
                curtext.setText(curtext.getText() + "6");
                break;
            case R.id.btn7:
                curtext.setText(curtext.getText() + "7");
                break;
            case R.id.btn8:
                curtext.setText(curtext.getText() + "8");
                break;
            case R.id.btn9:
                curtext.setText(curtext.getText() + "9");
                break;
            case R.id.btnDot:
                if (!hasDot) {
                    curtext.setText(curtext.getText() + ".");
                    hasDot = true;
                } else {

                }
                break;
            default:
                curtext.setText("ERROR");
                Log.d("ERROR", "ERROR: Unknown button was pressed");
        }
    }

    public void onClickFunction(View view) {
        int pressID = view.getId();
        TextView curtext = findViewById(R.id.resultText);
        if (pressID == R.id.btnClear) {
            opt = Operator.none;
            curtext.setText("");
            data1 = 0;
            data2 = 0;
            requireClean = false;
            hasDot = false;
            return;
        }
        String datatext = curtext.getText().toString();
        double numberVal = datatext.length() > 0 ? Double.parseDouble(datatext) : 0;
        if (opt == Operator.none) {
            data1 = numberVal;
            requireClean = true;
            switch (pressID) {
                case R.id.resultText:
                    opt = Operator.equals;
                    data1 = 0;
                    break;
                case R.id.btnPlus:
                    opt = Operator.add;
                    break;
                case R.id.btnMinus:
                    opt = Operator.minus;
                    break;
                case R.id.btnMul:
                    opt = Operator.multiply;
                    break;
                case R.id.btnDiv:
                    opt = Operator.divide;
                    break;
                case R.id.btnClear:
                    opt = Operator.none;
                    break;
            }
        } else {
            double result = 0;
            data2 = numberVal;
            switch (opt) {
                case equals:
                    break;
                case none:
                    break;
                case add:
                    result = data1 + data2;
                    break;
                case minus:
                    result = data1 - data2;
                    break;
                case multiply:
                    result = data1 * data2;
                    break;
                case divide:
                    result = data1 / data2;
                    break;
            }
            data1 = result;
            opt = Operator.none;
            if (result - (int) result != 0) {
                curtext.setText(String.valueOf(result));
            } else {
                curtext.setText(String.valueOf((int) result));
            }
        }
    }
}