package Repository;
import Model.Interface_Vegetable;
public interface Interface_Repository {
    void add(Interface_Vegetable vegetable);
    void remove(int index);
    Interface_Vegetable[] getVegetables();
    int getSize();
}
