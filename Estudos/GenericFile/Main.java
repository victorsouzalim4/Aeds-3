package Estudos.GenericFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {

        DataBase<Game> gameFile;

        Game g1 = new Game("Farcry 5", "Ubisoft", 250.00F, LocalDate.of(2018, 10, 12));
        Game g2 = new Game("Elden Ring", "From Software", 300.00F, LocalDate.of(2021, 9, 5));
        Game g3 = new Game("Portal 2", "Valve", 35.00F, LocalDate.of(2011, 3, 20));

        try {

            File f = new File(".//Estudos//GenericFile//data//game.db");
            f.delete();

            gameFile = new DataBase<>("game.db", Game.class.getConstructor());

            gameFile.create(g1);
            gameFile.create(g2);
            gameFile.create(g3);

            Game g = gameFile.read(3);

            if (g != null) {
                System.out.println(g.toString());
            } else {
                System.out.println("\nGame not found");
            }


            g3.name = "The Witcher 3";

            gameFile.update(g3);

            System.out.println();

            g = gameFile.read(3);


            if (g != null) {
                System.out.println(g.toString());
            } else {
                System.out.println("\nGame not found");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
