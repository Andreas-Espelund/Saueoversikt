import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.io.Serializable;


class Sheep implements Serializable{
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
    protected String birthdate;
    protected Boolean bottleLamb = false;
    protected LinkedHashMap<String,Double> weights = new LinkedHashMap<>();
    protected Double currentWeight;
    protected Status status = Status.OK;
    protected ArrayList<String> comments = new ArrayList<>();

    
    public Sheep(int ID, String birthdate){
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

    public String getBirthdate(){return birthdate;}

    public Boolean isBottlelamb(){return bottleLamb;}

    public LinkedHashMap<String,Double> getWeights(){return weights;}

    public Status getStatus(){return status;}

    public ArrayList<String> getComments(){return comments;}

    public Double getCurrentWeight(){return currentWeight;}

    public String toString(){
        String info = "-----------------------------\n";
        info = info + "ID: " + ID+"\n";
        info = info + "Foedselsdato: " + birthdate +"\n";
        info = info + "-----------------------------\n";
        return info;
    }

}