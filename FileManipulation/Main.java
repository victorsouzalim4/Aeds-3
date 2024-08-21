import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args){

        Game g1 = new Game("Farcry 5", "Ubisoft", 250.00F, LocalDate.of(2018, 10, 12));
        Game g2 = new Game("Elden Ring", "From Software", 300.00F, LocalDate.of(2021, 9, 5));
        Game g3 = new Game("Portal 2", "Valve", 35.00F, LocalDate.of(2011, 3, 20));


    }
}
