import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectInputStream;
import java.io.FileWriter;
import java.io.File;


/**
 * main
*/
public class Main {
    public static ArrayList<Sheep> container = new ArrayList<>();

    public static void main(String[] args) {
        String path = "Data/metadata.csv";
        importData(path);
        int selection = 0;

        

        //initialisering av program
        
        // Kommandoloekke


        while (selection != 7){
            writeMenu();
            selection = getInput(1,7);

            // skriver ut fullstendig oversikt over pasienter, leger, legemidler og reseptar
            if (selection == 1){
                blank(30);
                System.out.println("Meny 1");

                container.add(new Ewe(10010,"25.04.20"));
                container.add(new Ewe(20301,"03.05.20"));
                goBack();
            }
            
            else if (selection == 2){
                blank(30);
                System.out.println("Meny2");
                printList();
                goBack();
            }


            //Oppretter og legger til nye elementer i systemet
            else if (selection == 3){
                blank(30);
                
                System.out.println("Meny 3");
                goBack();
            }


            //bruker en gitt resept fra listen til en pasient
            else if (selection == 4){
                blank(30);
                System.out.println("Meny 4");
                
                goBack();
            }


            //undermeny for aa skrive ut forskjellig statestikk
            else if (selection == 5){
                blank(30);
                System.out.println("Meny 5");
                goBack();
            }


            //skriver all data til fil
            else if(selection == 6){
                blank(30);
                System.out.println("Meny 6");
                goBack();
            }

        }
        //kommandoloekka er ferdig
        System.out.println("avslutter...");
        exportData();
    }

    public static void writeMenu(){
        for (int i = 0; i < 30 ; i ++){
            System.out.println();
        }

        System.out.println("========SAUESYSTEM========");
        System.out.println("|1.| Soek paa nummer     |");
        System.out.println("|2.| Vis lister          |");
        System.out.println("|3.| Skriv ut lister     |");
        System.out.println("|4.| Registrer lamming   |");
        System.out.println("|5.| Registrer vekt      |");
        System.out.println("|6.| Registrer utmelding |");
        System.out.println("|7.| Avslutt             |");
        System.out.println("==========================");

    }

    //tar input fra bruker med scanner og lar kun brukaren skrive heiltall mellom 1 og 6
    public static int getInput(int start, int stop){
        System.out.print("Tast inn valg:");
        try{
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            //if testen sjekkar at tallet er innafor menyselectiona
            if (i < start || i > stop){
                System.out.println("Velg tall mellom " +start+ " og " +stop);
                i = getInput(start,stop);
            }
            return i;
        //catch blokka fanger opp om brukeren skriver inn tegn som ikkje er en int
        }catch(InputMismatchException e){
            System.out.println("Ugyldig inntasting! Kun heltall!");
            return getInput(start,stop);
        }

    }

    //ventar til brukeren trykker paa enter for aa gaa tilbake til hovedmenyen
    public static void goBack(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Trykk 'enter' for aa gaa tilbake >");
        sc.nextLine();
    }

    //blanker ut n antall linjer
    public static void blank(int n){
        for (int i = 0; i < n; i++){
            System.out.println();
        }
    }

    //imports data to the program
    public static void importData(String path){
        try{
            File dataList = new File(path);
            Scanner sc = new Scanner(dataList);
            int i = 0;
            while (sc.hasNextLine()){
                Sheep sh = readFromFile(sc.nextLine());
                container.add(sh);
                i++;
            }
            System.out.println(i + " filer lest inn");
        }catch (Exception e){
            System.out.println("Feil i innlesingsfil!");
        }
        
        
    }

    //exports data from the program
    public static void exportData(){
        try{
            FileWriter myWriter = new FileWriter("Data/metadata.csv");
            int i = 0;
            for(Sheep s : container){
                String name = writeToFile(s);
                myWriter.write(name+"\n");
                i++;
            }
            myWriter.close();
            System.out.println(i + " files written");
        }catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
    
    public static String writeToFile(Sheep sheep){
        String filepath =  String.valueOf(sheep.getID());
        try{
            FileOutputStream fileOut = new FileOutputStream("Data/" +filepath);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(sheep);
            objOut.close();
            System.out.println("Export to " + filepath + " sucessfull!");
        }catch (Exception e){
            System.out.println("Export to " + filepath + " failed!") ;
        }finally{
            return filepath;
        }
    }

    public static Sheep readFromFile(String individualPath){
        
        try {
            FileInputStream fileIn = new FileInputStream("Data/" +individualPath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Sheep shp = (Sheep)objectIn.readObject();
 
            System.out.println("Import from " + individualPath + " sucessfull!");
            objectIn.close();
            return shp;
            
        } catch (Exception ex) {
            System.out.println("Import from " + individualPath + " failed!");
            return null;
        }
    }

    public static void printList(){
        System.out.println("SAUEBEHOLDNING");
        for (Sheep s : container){
            System.out.println(s);
        }
    }

    public static void createSheep(){
        int ID = getID();
        
        Date birthdate = new Date(getInput(2000, 2100), getInput(1, 12), getInput(1, 31));

    }

    public static int getID(){
        int ID = getInput(0, 99999)
        if (String.valueOf(ID).length() != 5){
            System.out.println("Ugyldig lengde!");
            getID();
        }
        for ()
        return ID;
    }

    


