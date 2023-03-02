package com.example.calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class HelloController {
    @FXML
    private TextField textField;

    private Text function;

    private String currentNumber = "";

    @FXML
    protected void button0(ActionEvent event) {
        if (!currentNumber.equals("")){
            addNumber("0");
        }
    }
    @FXML
    protected void button1(ActionEvent event) {
        addNumber("1");
    }
    @FXML
    protected void button2(ActionEvent event) {
        addNumber("2");
    }
    @FXML
    protected void button3(ActionEvent event) {
        addNumber("3");
    }
    @FXML
    protected void button4(ActionEvent event) {
        addNumber("4");
    }
    @FXML
    protected void button5(ActionEvent event) {
        addNumber("5");
    }
    @FXML
    protected void button6(ActionEvent event) {
        addNumber("6");
    }
    @FXML
    protected void button7(ActionEvent event) {
        addNumber("7");
    }
    @FXML
    protected void button8(ActionEvent event) {
        addNumber("8");
    }
    @FXML
    protected void button9(ActionEvent event) {
        addNumber("9");
    }
    @FXML
    protected void buttonDivide(ActionEvent event) {
        if (isInt(this.currentNumber)){
            addNumber("/");
        }
    }
    @FXML
    protected void buttonMultiply(ActionEvent event) {
        if (isInt(this.currentNumber)){
            addNumber("*");
        }
    }
    @FXML
    protected void buttonSubtract(ActionEvent event) {
        if (isInt(this.currentNumber)){
            addNumber("-");
        }
    }
    @FXML
    protected void buttonAdd(ActionEvent event) {
        if (isInt(this.currentNumber)){
            addNumber("+");
        }
    }
    @FXML
    protected void buttonClear(ActionEvent event) {
        currentNumber = "";
        updateTextField();
    }

    @FXML
    protected void buttonEquals(ActionEvent event) {
        if (isInt(currentNumber) && hasOperand(currentNumber)){
            currentNumber = String.valueOf(calculate(currentNumber));
            updateTextField();
        }
    }

    private int calculate(String currentNumber) {
        int result = 0;

        int start = 0;
        ArrayList equation = new ArrayList<String>();
        for (int i = 0; i < currentNumber.length(); i++) {
            if(currentNumber.charAt(i) == '/' || currentNumber.charAt(i) == '*' || currentNumber.charAt(i) == '-' || currentNumber.charAt(i) == '+') {

                equation.add(currentNumber.substring(start, i));

                if (currentNumber.charAt(i) == '-' || currentNumber.charAt(i) == '+'){
                    start = i;
                } else {
                    equation.add(currentNumber.charAt(i));
                    start = i+1;
                }
            }
        }
        equation.add(currentNumber.substring(start));

        for (int i = 0; i < equation.size(); i++) {
            if (equation.get(i).toString().equals("*")){
                result += Integer.parseInt(equation.get(i - 1).toString()) * Integer.parseInt(equation.get(i + 1).toString())
                        - Integer.parseInt(equation.get(i-1).toString()) - Integer.parseInt(equation.get(i+1).toString());
                equation.remove(i);
            } else if (equation.get(i).toString().equals("/")) {
                result += Integer.parseInt(equation.get(i - 1).toString()) / Integer.parseInt(equation.get(i + 1).toString())
                        - Integer.parseInt(equation.get(i-1).toString()) - Integer.parseInt(equation.get(i+1).toString());
                equation.remove(i);
            }
            result += Integer.parseInt(equation.get(i).toString());
        }
        return result;
    }

    private void updateTextField(){
        textField.setText(currentNumber);
    }

    private void addNumber (String num){
        currentNumber += num;
        updateTextField();
    }

    private boolean isInt(String currentNumber){
        try {
            int x = Integer.parseInt(currentNumber.substring(currentNumber.length()-1));
            return true;
        } catch (Exception e){
            return false;
        }

    }

    private boolean hasOperand(String currentNumber) {
        int d = currentNumber.indexOf("/");
        int m = currentNumber.indexOf("*");
        int s = currentNumber.indexOf("-");
        int a = currentNumber.indexOf("+");
        if(d!= -1){
            return true;
        }
        if(m!= -1){
            return true;
        }
        if(s!= -1){
            return true;
        }
        if(a!= -1){
            return true;
        }
        return false;
    }

}