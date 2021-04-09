import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Calendar;


class Sheep implements Serializable, Comparable{
    enum Status{
        OK,
        SICK,
        MISSING,
        DEAD,
        BUTCHERED
    }
    protected int ID;
    protected Sheep mother;
    protected Sheep father;
    protected Date birthdate;
    protected Boolean bottleLamb = false;
    protected LinkedHashMap<String,Double> weights = new LinkedHashMap<>();
    protected Double currentWeight;
    protected Status status = Status.OK;
    protected ArrayList<String> comments = new ArrayList<>();

    
    public Sheep(int ID, Date birthdate){
        this.ID = ID;
        this.birthdate = birthdate;
        
    }


    //SETTERS
    public void setParents(Sheep mother,Sheep father){
        this.mother = mother;
        this.father = father;
    }

    public void setBottleLamb(){
        bottleLamb = true;
    }

    public void addWeight(String date, Double weight){
        weights.put(date,weight);
        currentWeight = weight;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public void addComment(String comment){
        comments.add(comment);
    }

    //GETTERS

    public int getID(){return ID;}
    
    public Sheep getMother(){return mother;}

    public Sheep getFather(){return father;}

    public Date getBirthdate(){return birthdate;}

    public Boolean isBottlelamb(){return bottleLamb;}

    public LinkedHashMap<String,Double> getWeights(){return weights;}

    public Status getStatus(){return status;}

    public ArrayList<String> getComments(){return comments;}

    public Double getCurrentWeight(){return currentWeight;}

    @Override
    public int compareTo(Object obj){
        Sheep otherSheep = (Sheep)obj;
        if (ID < otherSheep.getID()){
            return 1;
        }else if (ID < otherSheep.getID()){
            return -1;
        }
        return 0;
    }

    public int compareWeight(Object obj){
        Sheep otherSheep = (Sheep) obj;
        if (currentWeight > otherSheep.getCurrentWeight()){
            return 1;
        }else if (currentWeight < otherSheep.getCurrentWeight()){
            return -1;
        }
        return 0;
    }

    public int compareAge(Object obj){
        Sheep otherSheep = (Sheep) obj;
        return birthdate.compareTo(otherSheep.getBirthdate());

    }

}