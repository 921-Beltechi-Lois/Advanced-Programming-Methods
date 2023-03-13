package Model;

public class EggPlant implements Interface_Vegetable{
    private double weight;

    public EggPlant(double weight) {
        this.weight = weight;
    }

    @Override
    public void setWeight(double given_weight) {
        this.weight=given_weight;
    }
    @Override
    public double getWeight(){return this.weight;}

    @Override
    public String toString(){return "Eggplant with the weight "+this.weight+" of kg\n";}

}
