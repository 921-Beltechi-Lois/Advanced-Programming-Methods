package Model;

public class Pepper implements Interface_Vegetable{
    private double weight;

    public Pepper(double weight) {
        this.weight = weight;
    }

    @Override
    public void setWeight(double given_weight) {
        this.weight=given_weight;
    }
    @Override
    public double getWeight(){return this.weight;}

    @Override
    public String toString(){return "Pepper with the weight "+this.weight+" of kg\n";}

}
