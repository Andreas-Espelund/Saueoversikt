import java.util.Calendar;
import java.util.Scanner;

public class Ram extends Sheep{
    protected String nickname = "no name given";
    public Ram(int ID, Date birthday){
        super(ID,birthday);
    }
    public void setNickname(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Skriv inn kallenavn for "+ID + ": " );
        nickname = sc.nextLine();
        System.out.println("Navn er satt til: " + nickname + "\n Trykk 'e' for aa endre, eller 'enter' for aa bekrefte" );
        if(sc.nextLine().toLowerCase().equals("e")){
            setNickname();
        }
        
    }
    public String getNickname(){
        return nickname;
    }

    public String toString(){
        if (isBottlelamb()){
            return "|| ID: " +ID + " | Veir (K)||";
        }
        return "|| ID: " +ID + " | Veir ||";
    }
}
