package Estudos.GenericFile;

import java.time.LocalDate;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

class Game{
    public int id;
    public String name;
    public String developer;
    public float price;
    public LocalDate releaseDate;

    public Game(){
        this(-1, "","", 0F, LocalDate.now());
    }

    public Game(String n, String d, float p, LocalDate rD){
        this(-1, n, d, p, rD);
    }

    public Game(int id, String n, String d, float p, LocalDate rD){
        this.id = id;
        this.name = n;
        this.developer = d;
        this.price = p;
        this.releaseDate = rD;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public String toString(){ 
        return "\nID.........: " + this.id +
               "\nName.......: " + this.name +
               "\nDeveloper..: " + this.developer +
               "\nPrice......: $" + this.price +
               "\nReleaseDate: " + this.releaseDate;
    }

    public byte[] toByteArray() throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.id);
        dos.writeUTF(this.name);
        dos.writeUTF(this.developer);
        dos.writeFloat(this.price);
        dos.writeInt((int) this.releaseDate.toEpochDay());

        return baos.toByteArray();
    }
}