import java.util.ArrayList;
import java.util.Collections;


@SuppressWarnings("unchecked")
public class Container  {
    private ArrayList<Sheep> list = new ArrayList<Sheep>();


    public void addSheepObject(Sheep sh){
        list.add(sh);
    }

    public Sheep getSheepFromIndex(int pos){
        return list.get(pos);
    }

    public int size(){
        return list.size();
    }
    public void removeSheepObject(Sheep sh){
        list.remove(sh);
    }
   
    public ArrayList<Sheep> getNumericalSortedList(){
        ArrayList<Sheep> sortedList = list;
        Collections.sort(sortedList);
        return sortedList;
    }
    public ArrayList<Sheep> getArrayList(){
        return list;
    }

    public ArrayList<Sheep> sortedAgeAscending(){
        ArrayList<Sheep> sortedList = list;
        
        for (Sheep sh :list){
            Boolean inserted = false;
            for(int i = 0; i < sortedList.size() ; i ++){
                if (sortedList.get(i) != null){
                    if (sh.compareAge(sortedList.get(i)) <= 0){
                        sortedList.add(i, sh);
                        inserted = true;
                    }
                }
            }
            if (!inserted){
                sortedList.add(sh);
            }
        }
        return sortedList;
    }
   
    public ArrayList<Sheep> sortedWeightAscending(){
        ArrayList<Sheep> sortedList = list;
        
        for (Sheep sh :list){
            Boolean inserted = false;
            for(int i = 0; i < sortedList.size() ; i ++){
                if (sh.compareWeight(sortedList.get(i)) <= 0){
                    sortedList.add(i, sh);
                    inserted = true;
                }
            }
            if (!inserted){
                sortedList.add(sh);
            }
        }
        return sortedList;
    }

    public Sheep getSheepFromID(int ID){
        for (Sheep sh : list){
            if (sh != null){
                if (sh.getID() == ID){
                    return sh;
                }
            }
        }
        return null;
    }

    public void printArrayList(){
        for (Sheep sh: list){
            System.out.println(sh);
        }
    }
}
