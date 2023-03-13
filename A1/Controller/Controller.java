package Controller;
import Model.EggPlant;
import Model.Interface_Vegetable;
import Model.Pepper;
import Model.Tomato;
import Repository.Interface_Repository;

import java.util.Objects;

public class Controller {
    private Interface_Repository repository;

    public Controller(Interface_Repository repository){
        this.repository=repository;
    }

    public void add(String type, double weight){
        //type.equals(..)...
        if(Objects.equals(type, "eggplant")){
            EggPlant eggPlant=new EggPlant(weight);
            this.repository.add(eggPlant);
        } else if (Objects.equals(type, "pepper")) {
            Pepper pepper = new Pepper(weight);
            this.repository.add(pepper);
        } else if (Objects.equals(type, "tomato")) {
            Tomato tomato = new Tomato(weight);
            this.repository.add(tomato);
        }
    }

    public void remove(int id){
        this.repository.remove(id);
    }

    public Interface_Vegetable[] getVegetables() {
        return this.repository.getVegetables();
    }

    public int getSize() {
        return this.repository.getSize();
    }

    public Interface_Vegetable[] filterWeight(){
        Interface_Vegetable[] returned_vegetables=new Interface_Vegetable[this.getSize()];
        int len=0;
        //for( Interface_Vegetable element: get_vegetables)
        Interface_Vegetable[] elements=this.repository.getVegetables();
        for(int i=0;i<=this.getSize()-1;i++){
            if(elements[i].getWeight()>=0.2){
                returned_vegetables[len]=elements[i];
                len++;
            }
        }
        return returned_vegetables;
    }
}
