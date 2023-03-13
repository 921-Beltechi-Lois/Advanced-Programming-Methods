package Repository;

import Model.Interface_Vegetable;

public class Repository implements Interface_Repository {
    private Interface_Vegetable[] vegetables;
    private int no_of_vegetables;

    public Repository(int size){
        this.vegetables=new Interface_Vegetable[size];
        this.no_of_vegetables=0;
    }
    @Override
    public void add(Interface_Vegetable vegetable) {
        if(this.no_of_vegetables==0){
            this.vegetables=new Interface_Vegetable[10];
        }
        this.vegetables[this.no_of_vegetables]=vegetable;
        this.no_of_vegetables++;
    }

    @Override
    public void remove(int index) {
        Interface_Vegetable[] new_vegetables=new Interface_Vegetable[this.no_of_vegetables-1];

        //int i=0;
//        for(Interface_Vegetable element: new_vegetables)
        for (int i = 0,j=0; i <= this.no_of_vegetables-1; i++)
        {
            if(i==index)
                continue;
            new_vegetables[j]=this.vegetables[i];
            j++;
        }
        this.vegetables=new_vegetables;
        this.no_of_vegetables--;
    }

    @Override
    public Interface_Vegetable[] getVegetables() {
        return this.vegetables;
    }

    @Override
    public int getSize() {
        return this.no_of_vegetables;
    }
}
