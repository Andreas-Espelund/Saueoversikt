import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

import java.util.Calendar;
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
    
    public static Container herd = new Container();
    public static ArrayList<Ram> breedingRams = new ArrayList<>();

    public static void main(String[] args) {
        String path = "Data/metadata.csv";
        importData(path);
        int selection = 0;

        

        //initialisering av program
        
        // Kommandoloekke


        while (selection != 7){
            writeMenu();
            selection = getInput(1,7,"Velg funksjon");

            // skriver ut fullstendig oversikt over pasienter, leger, legemidler og reseptar
            if (selection == 1){
                blank(30);
                System.out.println("Meny 1");

                herd.addSheepObject(new Ewe(10010,new Date(20,01,2020)));
                herd.addSheepObject(new Ewe(20301,new Date(17,01,2021)));
                breedingRams.add(new Ram(40201,new Date(25,03,2015)));
                breedingRams.add(new Ram(30212,new Date(20,01,2017)));
                breedingRams.get(0).setNickname();
                goBack();
            }
            
            else if (selection == 2){
                
                
                writeListMenu();
                int menuIndex = getInput(1, 7, "Listevalg");
                while (menuIndex != 7){
                    if (menuIndex == 1){
                        herd.printArrayList();
                    }

                    else if (menuIndex == 2){
                        System.out.println("soyer u lamb");
                    }

                    else if (menuIndex == 3){
                        System.out.println("soyer m lamb");

                    }

                    else if (menuIndex == 4){
                        System.out.println("vektlister");
                        for (Sheep sh : herd.sortedAgeAscending()){
                            System.out.println(sh);
                        }

                    }

                    else if (menuIndex == 5){
                        System.out.println("foedselsaar");

                    }

                    else if (menuIndex == 6){
                        System.out.println("Tilpassa liste");
                    }
                }
                
                
            }


            //Oppretter og legger til nye elementer i systemet
            else if (selection == 3){
                blank(30);
                createSheep();
                System.out.println("Meny 3");
                goBack();
            }


            //bruker en gitt resept fra listen til en pasient
            else if (selection == 4){
                blank(30);
                registrerBirth();
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
    private static void writeListMenu(){
        blank(30);
        System.out.println("========= Lister =========");
        System.out.println("|1.| Besetning           |");
        System.out.println("|2.| Soyer (utan lamb)   |");
        System.out.println("|3.| Soyer med lamb      |");
        System.out.println("|4.| Vektlister          |");
        System.out.println("|5.| Foedselsaar         |");
        System.out.println("|6.| Tilpassa liste      |");
        System.out.println("|7.| Avslutt             |");
        System.out.println("==========================");
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
    public static int getInput(int start, int stop, String message){
        System.out.print(message+": ");
        try{
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            //if testen sjekkar at tallet er innafor menyselectiona
            if (i < start || i > stop){
                System.out.println("Velg tall mellom " +start+ " og " +stop);
                i = getInput(start,stop,message);
            }
            return i;
        //catch blokka fanger opp om brukeren skriver inn tegn som ikkje er en int
        }catch(InputMismatchException e){
            System.out.println("Ugyldig inntasting! Kun heltall!");
            return getInput(start,stop,message);
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
                herd.addSheepObject(sh);
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
            for(Sheep sh : herd.getNumericalSortedList()){

                String name = writeToFile(sh);
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

    public static void createSheep(){
        System.out.println("velg nummer");
        int ID = generateID();
        System.out.println("velg dato (YYY/MM/DD)");
        Date dat = new Date(getInput(1, 31,"Dag"), getInput(1, 12,"Maaned"),getInput(2000,2100,"Aar"));


        System.out.println("Velg kjonn: 1 - veir \n2 - soye");
        if (getInput(1, 2,"Kjonn") == 1){
            herd.addSheepObject(new Ewe(ID, dat));
        }else{
            herd.addSheepObject(new Ram(ID, dat));
        }

    }

    public static int generateID(){
        int ID = getInput(0, 99999,"Tast inn oremerkenummer");
        if (String.valueOf(ID).length() != 5 || herd.getSheepFromID(ID)!= null){
            System.out.println("Ugyldig lengde, nummer skal vere 5 siffer!");
            ID = generateID();
        }
        return ID;
    }

    public static void registrerBirth(){
        Scanner sc = new Scanner(System.in);
        
        Ewe mother = null;
        Ram father;
        Date date = null;

        while (mother == null){
            try{
                System.out.println(" * Skriv inn mors ID * ");
                Sheep sh = herd.getSheepFromID(Integer.parseInt(sc.nextLine()));
                if (sh instanceof Ewe){
                    mother =(Ewe) sh;
                }
            }catch(Exception e) {
                System.out.println(e);
            }
        }

        father = getRamFromList();

        System.out.println(" * VELG DATO * ");
        date = new Date(getInput(1,31,"Dag"),getInput(1, 12,"Maaned"), getInput(2000, 2100,"Aar"));

        

        int selection = 1;
        while (selection != 2){
            System.out.println(" * REGISTRER LAMB * ");
            int ID = generateID();
            Sheep newLamb;
            System.out.println(" * VELG KJONN * ");
            if (getInput(1, 2,"(1: veir / 2: soye)") == 1){
                newLamb = new Ewe(ID, date);
            }else{
                newLamb = new Ram(ID, date);
            }
            newLamb.setParents(mother, father);
            herd.addSheepObject(newLamb);
            mother.addLamb(newLamb);

            System.out.println(" * KOPPLAMB? * ");

            if (getInput(1, 2, "(1: ja / 2: nei)") == 1){
                newLamb.setBottleLamb();
            }

            System.out.println("* FLEIRE LAMB? *");
            selection = getInput(1, 2, "(1:Ja / 2:Nei)");
        }

        System.out.println("\n=== Oppsummering ===");
        System.out.println("Mor: " + mother.getID());
        System.out.println("Far: " + father.getNickname());
        System.out.println("Foedselsdato" + date);
        System.out.println("Registrerte lamb:");
        for (Sheep sh : mother.getLambs()){
            System.out.println(sh);
        }


    }

    public static Ram getRamFromList(){
        int i = 1;
        System.out.println("Velg far:");
        for (Ram rm : breedingRams){
            System.out.println(i + " - " + rm.getNickname());
            i++;
        }
        int selection = getInput(1, breedingRams.size(),"Velg veir");
        return breedingRams.get(selection-1);
    }

}

    


