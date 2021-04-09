import java.io.Serializable;

public class Date implements Serializable, Comparable{
    int YEAR;
    int MONTH;
    int DAY;
    int size;

    public Date(int d, int m, int y){
        YEAR = y;
        MONTH = m;
        DAY = d;
        size = d+m*10+y*100;
    }

    public String toString(){
        return DAY + "." + MONTH + "." + YEAR;
    }
    public int getSize(){
        return size;
    }

    public int compareTo(Object obj){
        Date otherDate = (Date) obj;
        if (size > otherDate.getSize()){
            return 1;
        }else if (size < otherDate.getSize()){
            return -1;
        }
        return 0;
    }
    
}
