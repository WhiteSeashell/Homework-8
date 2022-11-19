package logica;

import java.io.*;
import java.util.ArrayList;

import datos.*;

public class Main {
    private static final String path = "Y:\\MainFolder\\Java\\newJava\\src\\datos\\hello.obj";
    
    public static void main(String[] args) {
        loadExternalData();
        MenuHandler.select("welcome");

    }

    public static void updateExternalData(){
        FileOutputStream stream = null;
        ObjectOutputStream obj = null;
        try {
            //the past data gets deleted because we are not using new fileoupstres(path,true), but that doesn't matter
            stream = new FileOutputStream(path);
            obj = new ObjectOutputStream(stream);
            obj.writeObject(datos.ContactList.getContacts());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                stream.close();
                obj.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void loadExternalData(){
        File file = new File(path);
        if(!file.exists()){createFile();}
        FileInputStream  fileIn =null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream(path);
            in = new ObjectInputStream(fileIn);
            ArrayList<Contact> contacts = (ArrayList<Contact>) in.readObject();
            datos.ContactList.addAllContacts(contacts);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            System.out.println("nothing to read");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileIn.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void createFile(){
        File file = new File(path);
        try {
            boolean value = file.createNewFile();
            System.out.println("New Java File was created.");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


}
