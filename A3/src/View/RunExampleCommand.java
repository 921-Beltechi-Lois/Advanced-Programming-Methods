package View;

import Controller.Controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import Exception.ADTException;
import Exception.ExpressionEvaluationException;
import Exception.StatementExecutionException;
public class RunExampleCommand extends Command{
    private final Controller controller;

    public RunExampleCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Do you want to display the steps?[Y/n]");
            Scanner readOption = new Scanner(System.in);
            String option = readOption.next();
//            controller.setDisplayFlag(Objects.equals(option, "Y"));
            controller.setDisplayFlag(option.equals("Y"));

            controller.allSteps();
        } catch (ExpressionEvaluationException | ADTException | StatementExecutionException | IOException exception) {
            System.out.println("\u001B[31m" + exception.getMessage() + "\u001B[0m");
        }
    }
}