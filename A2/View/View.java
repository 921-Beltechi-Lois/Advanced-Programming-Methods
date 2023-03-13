package View;

import Controller.Controller;
import Model.expression.ArithmeticExpression;
import Model.expression.ValueExpression;
import Model.expression.VariableExpression;
import Model.programState.ProgramState;
import Model.statement.*;
import Exception.ADTException;
import Exception.StatementExecutionException;
import Exception.ExpressionEvaluationException;
import Model.type.BoolType;
import Model.type.IntType;
import Model.utils.*;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.Value;
import Repository.IRepository;
import Repository.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class View {
    public void start() {
        boolean done = false;
        while (!done) {
            try {
                showMenu();
                Scanner readOption = new Scanner(System.in);
                int option = readOption.nextInt();
                if (option == 0) {
                    done = true;
                } else if (option == 1) {
                    runProgram1();
                } else if (option == 2) {
                    runProgram2();
                } else if (option == 3) {
                    runProgram3();
                } else {
                    System.out.println("Invalid input!");
                }
            } catch (Exception exception) {
                System.out.println("\u001B[31m" + exception.getMessage() + "\u001B[0m");
            }
        }
    }

    private void showMenu() {
        System.out.println("MENU: ");
        System.out.println("0. Exit.");
        System.out.println("1. Run the first program: \n\tint v;\n\tv=2;\n\tPrint(v)");
        System.out.println("2. Run the second program: \n\tint a;\n\tint b;\n\ta=2+3*5;\n\tb=a+1;\n\tPrint(b)");
        System.out.println("3. Run the third program: \n\tbool a;\n\tint v;\n\ta=true;\n\t(If a Then v=2 Else v=3);\n\tPrint(v)");
        System.out.println("Choose an option: ");
    }

    private void runProgram1() throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException {
        IStatement ex1 = new CompoundStatement(new VarDeclStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        runStatement(ex1);
    }

    private void runProgram2() throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException {
        IStatement ex2 = new CompoundStatement(new VarDeclStatement("a",new IntType()),
                new CompoundStatement(new VarDeclStatement("b",new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        runStatement(ex2);
    }

    private void runProgram3() throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException {
        IStatement ex3 = new CompoundStatement(new VarDeclStatement("a", new BoolType()),
                new CompoundStatement(new VarDeclStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                        new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        runStatement(ex3);
    }

    private void runStatement(IStatement statement) throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException {
        MyIStack<IStatement> executionStack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();

        ProgramState state = new ProgramState(executionStack, symbolTable, output, statement);

        IRepository repository = new Repository(state);
        Controller controller = new Controller(repository);

        System.out.println("Do you want to display the steps?[Y/n]");
        Scanner readOption = new Scanner(System.in);
        String option = readOption.next();
        controller.setDisplayFlag(Objects.equals(option, "Y"));

        controller.allSteps();
        System.out.println("Result is" + state.getOut().toString().replace('[', ' ').replace(']', ' '));
    }

}
