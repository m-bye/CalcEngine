package com.pluralsight.calcengine;

//local date addition
import java.time.LocalDate;
//scanner addition
import java.util.Scanner;

public class App {
    
    public static void main(String[] args) throws Exception {

        //parallel arrays
        double[] leftVals = {
            100.0d, 25.0d, 225.0d, 11.0d
        };

        double[] rightVals = {
            50.0d, 92.0d, 17.0d, 3.0d
        };

        char[] opCodes ={
            'd', 'a', 's', 'm'
        };

        double[] results = new double[opCodes.length];

        /*double value1 = 100.0d;
        double value2 = 50.0d;
        //if 0 was assigned to value2, we would get invalid opCode for 'd'

        double result = 0.0d;
        char opCode = 'd';
        // if opCode was 'a' then result would be assigned 150*/

        if(args.length == 0){
            
            //for loop and switch statement
            for(int i = 0; i < opCodes.length; i++){

                results[i] = execute(opCodes[i], leftVals[i], rightVals[i]);
            
                //move code to execute method
                /*switch(opCodes[i]){

                    case 'a' :
                        results[i] = leftVals[i] + rightVals[i];
                        break;
                    case 's' :
                        results[i] = leftVals[i] - rightVals[i];
                        break;
                    case 'm' :
                        results[i] = leftVals[i] * rightVals[i];
                        break;
                    case 'd' :
                        results[i] =  rightVals[i] != 0 ? leftVals[i] / rightVals[i] : 0.0d;
                        break;
                    default:
                        System.out.println("Invalid opCode" + opCodes[i]);
                        results[i] = 0.0d;
                        break;
    
                }*/

            }

            //for each loop to print all results
            for(double currentResult : results){

                System.out.println(currentResult);

            }  
        }

        //did not include "interactive" in json file
        else if(args.length == 1 && args[0].equals("interactive")){

            executeInteractive();

        }
        else if(args.length == 3){

            handleCommandLine(args);

        }
        else{

            System.out.println("Please provide and op code and two numeric values");

        }  

        //if else statement
        /*if (opCode == 'a'){
            result = value1 + value2;
        }
        else if(opCode == 's'){
            result = value1 - value2;
        }
        else if(opCode == 'm'){
            result = value1 * value2;
        }
        else if(opCode == 'd'){

            //include if statement in block statement
            if(value2 != 0){

                result = value1 / value2;

            }
            
        }
        else{
            System.out.println("Invalid opCode" + opCode);
            result = 0.0d;
        }

        System.out.println(result);*/

    }

    //new handleCommandLine method
    static void executeInteractive(){

        System.out.println("Enter an operation and two numbers: ");

        //suggested code, scanner was not closed
        try (Scanner scanner = new Scanner(System.in)) {
            String userInput = scanner.nextLine();

            String[] parts = userInput.split(" ");

            performOperation(parts);
            //scanner.close();
        }
    }

    private static void performOperation(String[] parts) {

        char opCode = opCodeFromString(parts[0]);
        if(opCode == 'w'){
            handleWhen(parts);
        }
        else{
            double leftVal = valueFromWord(parts[1]);
            double rightVal = valueFromWord(parts[2]);
            double result = execute(opCode, leftVal, rightVal);
            //System.out.println(result);
            displayResult(opCode, leftVal, rightVal, result);

        }
    }

    private static void handleWhen(String[] parts) {

        LocalDate startDate = LocalDate.parse(parts[1]);
        //casto long added
        long daysToAdd = (long) valueFromWord(parts[2]);
        LocalDate newDate = startDate.plusDays(daysToAdd);
        String output = String.format("%s plus %d days is %s", startDate, daysToAdd, newDate);
        System.out.println(output);

    }

    private static void displayResult(char opCode, double leftVal, double rightVal, double result) {

        char symbol = symbolFromOpCode(opCode);
        //StringBuilder build = new StringBuilder(20);
        //build.append(leftVal);
        //build.append(" ");
        //build.append(symbol);
        //build.append(" ");
        //build.append(rightVal);
        //build.append(" = ");
        //build.append(result);
        //String output = build.toString();

        String output = String.format("%.3f %c %.3f = %.3f", leftVal, symbol, rightVal, result);

        System.out.println(output);
        
    }

    //symbol from opCode method
    private static char symbolFromOpCode(char opCode){

        //parallel arrays again!
        char[] opCodes = {'a', 's', 'm', 'd'};
        char[] symbols = {'+', '-', '*', '/'};
        char symbol = ' ';
        for(int index = 0; index < opCodes.length; index++){

            if(opCode == opCodes[index]){
                symbol = symbols[index];
                break;
            }
        }

        return symbol;
    }

    //methods
    private static void handleCommandLine(String[] args){

        //convertions (parse)
        char opCode = args[0].charAt(0);
        double leftVal = Double.parseDouble(args[1]);
        double rightVal = Double.parseDouble(args[2]);

        double result = execute(opCode, leftVal, rightVal);
        System.out.println(result);

    }

    static double execute(char opCode, double leftVal, double rightVal){

        double result;

        switch(opCode){

            case 'a' :
                result = leftVal + rightVal;
                   break;
            case 's' :
                result = leftVal - rightVal;
                break;
            case 'm' :
                result = leftVal * rightVal;
                break;
            case 'd' :
                result =  rightVal != 0 ? leftVal / rightVal : 0.0d;
                break;
            default:
                System.out.println("Invalid opCode" + opCode);
                result = 0.0d;
                break;
    
        }

        return result;

    }

    //more methods
    static char opCodeFromString(String operationName){

        char opCode = operationName.charAt(0);
        return opCode;
    }

    static double valueFromWord(String word){

        String[] numbWords = {

            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
        };

        double value = -1d;
        for(int index = 0; index < numbWords.length; index++){

            if(word.equals(numbWords[index])){

                value = index;
                break;
            }
        }
        if(value == -1d){
            value = Double.parseDouble(word);
        }

        return value;
        //numeric value of the value of the word
    }

}