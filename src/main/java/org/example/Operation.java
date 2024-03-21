package org.example;

public class Operation {
    private int firstNum;
    private int secondNum;
    private char operation;
    private int result;

    public Operation(int firstNum, int secondNum, char operation, int result) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
        this.operation = operation;
        this.result = result;
    }
    public Operation(int firstNum, int secondNum, char operation) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
        this.operation = operation;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}