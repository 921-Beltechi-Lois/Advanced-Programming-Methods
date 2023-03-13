package View;

import Controller.Controller;
import Exceptions.InputException;
import Model.Interface_Vegetable;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class MyView {
    private final Controller controller;

    public MyView(Controller controller) {
        this.controller = controller;
    }

    private static void printMenu() {
        System.out.println("Choose your option: ");
        System.out.println("1. Display all vegetables.");
        System.out.println("2. Add a vegetable.");
        System.out.println("3. Remove a vegetable.");
        System.out.println("4. Display all the vegetables that have the weight greater than a given weight.");
        System.out.println("5. Exit.");
    }

    private void printVegetables() {
        System.out.println("->\n");
        if (this.controller.getSize() == 0) {
            System.out.println("No vegetables!");
        } else {
            Interface_Vegetable[] vegetables = this.controller.getVegetables();
            int index;
            for (index = 0; index <= this.controller.getSize() - 1; index++)
//                for (Interface_Vegetable element : vegetables)
            {
                System.out.println(vegetables[index].toString());
            }
        }
    }

    private void addVegetable() throws InputException {
        System.out.println("Enter type(eggplant / pepper / tomato): ");
        Scanner read_veg = new Scanner(System.in);
        String type = read_veg.nextLine();
        if (Objects.equals(type, "tomato") || Objects.equals(type, "eggplant") || Objects.equals(type, "pepper")) {
            System.out.println("Enter weight: ");

            Scanner readWeight = new Scanner(System.in);
            String weight = readWeight.nextLine();
            double double_weight = Double.parseDouble(weight);


            if (double_weight <= 0) {
                throw new InputException("Invalid weight!");
            }
            this.controller.add(type, double_weight);
        } else {
            throw new InputException("Invalid type!");
        }

    }

    private void removeVegetable() throws InputException {
        if (this.controller.getSize() != 0) {
            System.out.println("Enter index: ");
            Scanner readIndex = new Scanner(System.in);
            int index = readIndex.nextInt();
            if (index >= 0 && index <= this.controller.getSize() - 1) {
                this.controller.remove(index);
            } else {
                throw new InputException("Invalid index!");
            }
        } else {
            throw new InputException("There is nothing left to remove!");
        }
    }

    private void displayFilteredVegetables() throws InputException {
        if (this.controller.getSize() != 0) {
            Interface_Vegetable[] filteredVegetables = this.controller.filterWeight();

            if (filteredVegetables[0] == null) {
                System.out.println("There are no vegetables that have the weight greater than 0.2 kg.");
            } else {
                int len = filteredVegetables.length;
                for (int index = 0; index <= len - 1; index++) {
                    if (filteredVegetables[index] != null)
                        System.out.println((index) + ". " + filteredVegetables[index].toString());
                }
            }
        } else {
            throw new InputException("The list is empty!");
        }
    }

    public void start() {
        while (true) {
            try {
                printMenu();
                Scanner readOption = new Scanner(System.in);
                int option = readOption.nextInt();
                if (option == 1) {
                    this.printVegetables();
                } else if (option == 2) {
                    this.addVegetable();
                } else if (option == 3) {
                    this.removeVegetable();
                } else if (option == 4) {
                    this.displayFilteredVegetables();
                } else if (option == 5) {
                    return;
                } else {
                    System.out.println("Invalid input!");
                }

            }
            catch (NumberFormatException ExceptionDoubleNumber) {
                System.out.println("You did not enter any double number");
            }
            catch(InputMismatchException e) {
                System.out.println("Needs a number.");
            }
            catch (InputException inputException) {
                System.out.println(inputException.getMessage());
            }
        }
    }
}