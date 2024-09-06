package Estudos.GenericFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.lang.reflect.Constructor;

public class DataBase <T extends Register> { 
    final int HEADER_LENGTH = 4;
    RandomAccessFile file;
    String fileName;
    Constructor<T> constructor;

    DataBase(String fileName, Constructor<T> constructor) throws IOException{
        File directory = new File(".\\Estudos\\GenericFile\\data");

        if(!directory.exists()){
            directory.mkdir();
        }

        this.fileName = ".\\Estudos\\GenericFile\\data\\" + fileName;
        this.constructor = constructor;
        file = new RandomAccessFile(this.fileName, "rw");
        if(file.length() < HEADER_LENGTH){
            file.writeInt(0);
        }
    }

    public int create(T obj) throws IOException{

        file.seek(0);
        int newId = file.readInt() + 1;
        obj.setId(newId);

        file.seek(0);
        file.writeInt(newId);

        file.seek(file.length());

        byte[] b = obj.toByteArray();
        file.writeByte(' ');
        file.writeShort(b.length);
        file.write(b);

        return obj.getId();
    }

    public T read(int id) throws Exception{
        T obj;
        byte tomb;
        short len;
        byte[] b;
        
        file.seek(HEADER_LENGTH);

        while(file.getFilePointer() < file.length()){

            obj = constructor.newInstance();
            tomb = file.readByte();
            len = file.readShort();
            b = new byte[len];
            file.read(b);


           if(tomb == ' '){
                obj.fromByteArray(b);
                if(obj.getId() == id){
                    return obj;
                }
           }

        }
        
        
        return null;
    }

    public boolean delete(int id) throws Exception{
        T obj;
        byte tomb;
        short len;
        byte[] b;
        Long adress;

        file.seek(HEADER_LENGTH);

        while(file.getFilePointer() < file.length()){
            obj = constructor.newInstance();
            adress = file.getFilePointer();

            tomb = file.readByte();
            len = file.readShort();
            b = new byte[len];
            file.read(b);

            if(tomb == ' '){
                obj.fromByteArray(b);
                if(obj.getId() == id){
                    file.seek(adress);
                    file.write('*');
                    return true;
                }
            }

        }
        
        
        return false;
    }
            
}

