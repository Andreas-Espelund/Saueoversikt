import java.util.ArrayList;
//soye
public class Ewe extends Sheep{
    protected ArrayList<Sheep> lambs = new ArrayList<Sheep>();
    protected int expectedLambs = 0; 

    public Ewe(int ID, String birthdate){
        super(ID,birthdate);
    }

    public void addLamb(Sheep lamb){
        lambs.add(lamb);
    }

    public ArrayList<Sheep> getLambs(){return lambs;}

    public void setExpectedLambs(int e){
        expectedLambs = e;
    }

}
