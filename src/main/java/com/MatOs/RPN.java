package com.MatOs;

import java.util.ArrayList;
import java.util.List;

public class RPN {
    public static double result(String expression, double x) {

        expression = (expression.startsWith("-") ? "0" + expression : expression);

        return calculate(convertToRPN(expression), x);
    }

    public static String convertToRPN(String expression) {
        String converted = "";

        char[] expr = expression.toCharArray();
        List<String> buffer = new ArrayList<>();

        for(char c : expr) {
            if(c >= '0' && c <= '9' || c == '.' || c == 'x') {
                converted += c;
            }else if(c == '(') {
                buffer.add("(");
            }else if(c == ')') {
                for(int i = buffer.size() - 1; buffer.get(i) != "("; i--) {
                    converted += buffer.getLast();
                    buffer.removeLast();
                }
                buffer.removeLast();

            }else{

                for(int i = buffer.size() - 1; i >= 0; i--) {
                    if(priority(buffer.getLast()) >= priority(String.valueOf(c))) {
                        converted += buffer.get(i);
                        buffer.removeLast();
                    }
                }
                converted += ' ';
                buffer.add(String.valueOf(c));
            }
        }

        for(int i = buffer.size() - 1; i >= 0; --i){
            converted += buffer.getLast();
            buffer.removeLast();
        }

        return converted;
    }

    public static double calculate(String converted, double x) {
        List<Double> numbers = new ArrayList<>();
        char[] conv = converted.toCharArray();
        String number = "";

        for(int i = 0; i < conv.length; i++) {
            if(conv[i] >= '0' && conv[i] <= '9' || conv[i] == '.') {
                number += conv[i];
            }else if(conv[i] == 'x') {
                number = String.valueOf(x);
            } else{
                if(!number.isEmpty()) numbers.add(Double.parseDouble(number));
                number = "";

                if(conv[i] == ' ') continue;

                double op2 = numbers.getLast();
                numbers.removeLast();
                double op1 = numbers.getLast();
                numbers.removeLast();

                double temp = switch (conv[i]) {
                    case '+' -> op1 + op2;
                    case '-' -> op1 - op2;
                    case '*' -> op1 * op2;
                    case '/' -> op1 / op2;
                    case '^' -> Math.pow(op1, op2);
                    default -> 0;
                };

                numbers.add(temp);
                if(i != conv.length - 1 && (conv[i + 1] == '+' || conv[i + 1] == '-' ||
                        conv[i + 1] == '*' || conv[i + 1] == '/' || conv[i + 1] == '^')) continue;
                i++;
            }
        }

        return numbers.getFirst();
    }

    public static int priority(String c) {
        return switch (c) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }
}
