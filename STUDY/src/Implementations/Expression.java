package Implementations;

import MyFramework.Collection.DeqStack;


public class Expression {
    private static DeqStack<Integer> operandStack;
    private static DeqStack<Character> operatorStack;
    private static StringBuilder postfix;
    
    
    public static int postfixEvaluation(String expression) {
        operandStack = new DeqStack<>();
        String[] tokens = expression.split(" ", -1);
        for (String token : tokens) {
            if (token.matches("\\d+")) {
                operandStack.push(Integer.parseInt(token));
            } else if (token.length() == 1 && isOperator(token.charAt(0))) {
                operandStack.push(evalOp(token.charAt(0)));
            }
        }
        int result = operandStack.pop();
        if (operandStack.isEmpty())
            return result;
        else
            throw new IllegalStateException();
    }
    
    private static int evalOp(char op) {
        int LHS = operandStack.pop();
        int RHS = operandStack.pop();
        
        return switch (op) {
            case '+' -> RHS + LHS;
            case '-' -> RHS - LHS;
            case '*' -> RHS * LHS;
            case '/' -> RHS / LHS;
            default -> 0;
        };
        
    }
    
    public static String postfixToInfix(String expression) {
        DeqStack<String> infixStack = new DeqStack<>();
        for (char token : expression.trim().toCharArray()) {
            if (Character.isLetterOrDigit(token)) {
                infixStack.push(String.valueOf(token));
            } else if (isOperator(token)) {
                String RHS = infixStack.pop();
                String LHS = infixStack.pop();
                infixStack.push("(" + LHS + " " + token + " " + RHS + ")");
            }
        }
        String result = infixStack.pop();
        if (infixStack.isEmpty())
            return result;
        else
            throw new IllegalStateException();
    }
    
    
    public static String infixToPostfix(String infix) {
        postfix = new StringBuilder();
        operatorStack = new DeqStack<>();
        
        for (char token : infix.trim().toCharArray()) {
            if (Character.isLetterOrDigit(token)) {
                postfix.append(token);
                postfix.append(" ");
            } else if (token == '(') {
                operatorStack.push(token);
            } else if (token == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop());
                    postfix.append(" ");
                }
                operatorStack.pop(); // Remove the '(' operator
            } else if (isOperator(token)) { // Token is an operator
                processOperator(token);
            }
        }
        
        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
            postfix.append(" ");
        }
        
        return postfix.toString();
    }
    
    private static void processOperator(char op) {
        if (operatorStack.isEmpty()) {
            operatorStack.push(op);
        } else {
            char topOp = operatorStack.peek();
            if (precedence(op) > precedence(topOp)) {
                operatorStack.push(op);
            } else {
                while (!operatorStack.isEmpty() && precedence(op) <= precedence(topOp)) {
                    postfix.append(operatorStack.pop());
                    postfix.append(" ");
                    if (!operatorStack.isEmpty()) {
                        topOp = operatorStack.peek();
                    }
                }
                operatorStack.push(op);
            }
        }
    }
    
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
    
    private static int precedence(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0; // Default precedence for unknown operators
        };
    }
    
}
