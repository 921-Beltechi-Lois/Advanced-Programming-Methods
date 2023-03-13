package View;

import Controller.Controller;
import Model.expression.ArithmeticExpression;
import Model.expression.RelationalExpression;
import Model.expression.ValueExpression;
import Model.expression.VariableExpression;
import Model.programState.ProgramState;
import Model.statement.*;
import Model.type.BoolType;
import Model.type.IntType;
import Model.type.StringType;
import Model.utils.MyDictionary;
import Model.utils.MyList;
import Model.utils.MyStack;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.StringValue;
import Repository.IRepository;
import Repository.Repository;

public class Interpreter {
    public static void main(String[] args){
        IStatement ex1 = new CompoundStatement(new VarDeclStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        ProgramState prg1 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex1);
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller controller1 = new Controller(repo1);

        IStatement ex2 = new CompoundStatement(new VarDeclStatement("a",new IntType()),
                new CompoundStatement(new VarDeclStatement("b",new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        ProgramState prg2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex2);
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller controller2 = new Controller(repo2);

        IStatement ex3 = new CompoundStatement(new VarDeclStatement("a", new BoolType()),
                new CompoundStatement(new VarDeclStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                        new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        ProgramState prg3 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex3);
        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller controller3 = new Controller(repo3);

        IStatement ex4 = new CompoundStatement(new VarDeclStatement("varf", new StringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFile(new VariableExpression("varf")),
                                new CompoundStatement(new VarDeclStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseReadFile(new VariableExpression("varf"))))))))));

        ProgramState prg4 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex4);
        IRepository repo4 = new Repository(prg4, "log4.txt");
        Controller controller4 = new Controller(repo4);



        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", ex1.toString(), controller1));
        menu.addCommand(new RunExampleCommand("2", ex2.toString(), controller2));
        menu.addCommand(new RunExampleCommand("3", ex3.toString(), controller3));
        menu.addCommand(new RunExampleCommand("4", ex4.toString(), controller4)); //ex

        menu.show();
    }
}
