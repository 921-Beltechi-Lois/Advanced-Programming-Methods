/*
5. Intr-o sera se cultiva rosii, ardei si vinete.
Sa se afiseze toate legumele cu greutatea mai mare
de 0.2 kg.

 */

import Controller.Controller;
import Repository.Repository;
import View.MyView;


public class Main {
    public static void main(String[] args) {
//        try {
            Repository repository = new Repository(10);


            Controller controller = new Controller(repository);
            controller.add("eggplant", (float) 0.2);
            controller.add("pepper", (float) 0.1);
            controller.add("tomato", (float) 0.1);

            MyView view = new MyView(controller);
            view.start();

//        } catch (ArithmeticException arithmeticException) {
//            System.out.println(arithmeticException.getMessage());
//        }
    }
}
