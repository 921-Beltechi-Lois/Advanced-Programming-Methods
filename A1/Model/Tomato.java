package Model;

public class Tomato implements Interface_Vegetable{
    private double weight;

    public Tomato(double weight) {
        this.weight = weight;
    }

    @Override
    public void setWeight(double given_weight) {
        this.weight=given_weight;
    }
    @Override
    public double getWeight(){return this.weight;}

    @Override
    public String toString(){return "Tomato with the weight "+this.weight+" of kg\n";}

}
