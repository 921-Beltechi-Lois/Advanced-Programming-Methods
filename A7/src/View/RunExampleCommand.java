package View;

import Controller.Controller;

import java.util.Scanner;

import Exception.InterpreterException;
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
            controller.setDisplayFlag(option.equals("Y"));

            //controller.allSteps();
            controller.allStep();
        } catch (InterpreterException | InterruptedException exception) {
            System.out.println("\u001B[31m" + exception.getMessage() + "\u001B[0m");
        }
    }
}