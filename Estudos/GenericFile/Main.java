package Estudos.GenericFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException{

        DataBase gameFile;

        Game g1 = new Game("Farcry 5", "Ubisoft", 250.00F, LocalDate.of(2018, 10, 12));
        Game g2 = new Game("Elden Ring", "From Software", 300.00F, LocalDate.of(2021, 9, 5));
        Game g3 = new Game("Portal 2", "Valve", 35.00F, LocalDate.of(2011, 3, 20));

        try {

            File f = new File(".\\GenericFile\\data\\game.db");
            f.delete();

            gameFile = new DataBase("game.db");

            gameFile.create(g1);
            gameFile.create(g2);
            gameFile.create(g3);

            Game g = gameFile.read("Elden Ring");

            if(g != null){
                System.out.println(g.toString());
            }else{
                System.out.println("\nGame not found");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        

        
        
    }
}
