import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

public class DataBase{
    final int HEADER_LENGTH = 4;
    RandomAccessFile file;
    String fileName;

    DataBase(String fileName) throws IOException{
        File directory = new File(".\\data");

        if(!directory.exists()){
            directory.mkdir();
        }

        this.fileName = ".\\data\\" + fileName;
        file = new RandomAccessFile(this.fileName, "rw");
        if(file.length() < HEADER_LENGTH){
            file.writeInt(0);
        }
    }

    public int create(Game game)throws IOException{

        file.seek(0);
        int newId = file.readInt() + 1;
        game.setId(newId);

        file.seek(0);
        file.writeInt(newId);

        file.seek(file.length());
        file.writeInt(game.id);
        file.writeUTF(game.name);
        file.writeUTF(game.developer);
        file.writeFloat(game.price);
        file.writeInt((int) game.releaseDate.toEpochDay());

        return game.id;
    }

    public Game read(String gameName) throws IOException{
        
        file.seek(HEADER_LENGTH);

        while(file.getFilePointer() < file.length()){

            Game game = new Game();
            game.id = file.readInt();
            game.name = file.readUTF();
            game.developer = file.readUTF();
            game.price = file.readFloat();
            game.releaseDate = LocalDate.ofEpochDay((int) file.readInt());


            if(game.name.compareTo(gameName) == 0){
                return game;
            }
        }
        
        
        return null;
    }
            
}

