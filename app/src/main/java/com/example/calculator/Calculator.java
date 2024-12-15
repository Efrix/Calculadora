package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    private TextView display;
    private StringBuilder currentInput= new StringBuilder();
    private double num1=0, num2=0;
    private String operator ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display= findViewById(R.id.tvNum);

        //Botones
        findViewById(R.id.btnOne).setOnClickListener(v -> appendNumber("1"));
        findViewById(R.id.btnTwo).setOnClickListener(v -> appendNumber("2"));
        findViewById(R.id.btnThree).setOnClickListener(v -> appendNumber("3"));
        findViewById(R.id.btnFour).setOnClickListener(v -> appendNumber("4"));
        findViewById(R.id.btnFive).setOnClickListener(v -> appendNumber("5"));
        findViewById(R.id.btnSix).setOnClickListener(v -> appendNumber("6"));
        findViewById(R.id.btnSeven).setOnClickListener(v -> appendNumber("7"));
        findViewById(R.id.btnEight).setOnClickListener(v -> appendNumber("8"));
        findViewById(R.id.btnNine).setOnClickListener(v -> appendNumber("9"));
        findViewById(R.id.btnZero).setOnClickListener(v -> appendNumber("0"));

        //Botones de operador
        findViewById(R.id.btnSuma).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btnResta).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> setOperator("/"));
        findViewById(R.id.btnMulti).setOnClickListener(v -> setOperator("x"));

        //Boton de igual
        findViewById(R.id.btnResul).setOnClickListener(v -> calculateResult());

        //Botones especiales
        findViewById(R.id.btnClean).setOnClickListener(v -> clearAll());
        findViewById(R.id.btnBorrar).setOnClickListener(v -> backspace());
        findViewById(R.id.btnPoint).setOnClickListener(v -> appendNumber("."));


    }
    private void appendNumber(String number)
    {
        currentInput.append(number);
        display.setText(currentInput.toString());
    }

    private void setOperator(String op) {
        if (currentInput.length() > 0) {
            num1 = Double.parseDouble(currentInput.toString());
            operator = op;
            currentInput.setLength(0);//Limpia el texto para el siguiente numero
            display.setText("");//Limpia la pantalla
        }
    }
    private void calculateResult() {
        if (currentInput.length() > 0 && !operator.isEmpty()) {
            num2 = Double.parseDouble(currentInput.toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "x":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            num1 = result; // Almacena el resultado para operaciones continuas
            currentInput.setLength(0); // Resetea el input
            operator = ""; // Resetea el operador
        }
    }
    private void clearAll() {
        currentInput.setLength(0);
        num1 = 0;
        num2 = 0;
        operator = "";
        display.setText("");
    }
    private void backspace() {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            display.setText(currentInput.toString());
        }
    }


}