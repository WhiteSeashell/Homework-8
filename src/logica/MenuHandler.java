package logica;

import java.util.ArrayList;
import java.util.HashMap;

import ui.MenuConsole;
import datos.*;



public class MenuHandler {

    private static ArrayList<String> optionLog = new ArrayList<>();

    public static void removeContact(Contact u){
        ContactList.deleteContact(u);
    }

    public static void editContact(Contact u, String name, String id, boolean isCustumer){
        u.setName(name);
        u.setId(id);
        u.setClient(isCustumer);
    }

    public static Contact getContact(String number){
        for(Contact u: ContactList.getContacts()){
            if(u.getNumber().equals(number)){return u;}
        }
        return null;
    }
    public static HashMap<String,String> search(String name){
        HashMap<String,String> matches = new HashMap<>();
        for(Contact u: ContactList.getContacts()){
            if(u.getName().matches("(.*)"+name+"(.*)")){matches.put(u.getName(),u.toString());}
        }
        return matches;
    }

    public static boolean validar(String number){
        String pattern = String.format("\\d{%d}", number.length());
        if(!number.matches(pattern)){ return false;}
        for(Contact u: ContactList.getContacts()){
            // the number is already being used
            if(u.getNumber().equals(number)){return false;}
        }
        return true;
    }

    public static boolean createContact(String[] info){
        if(!validar(info[1])){return false;}
        ContactList.addContacts(new Contact(info[0], info[1], info[2], Boolean.parseBoolean(info[3])));
        return true;
        //the contact was created successfully

    }

    public static void select(String option){
        while(!option.equals("exit")){
            optionLog.add(option);
            switch (option){
                case "welcome":
                    option = MenuConsole.welcome();
                    break;
                case "new contact":
                    option = MenuConsole.newContact();
                    break;
                case "search contact":
                    option = MenuConsole.searchContact();
                    break;
                case "edit contact":
                    option = MenuConsole.editContact();
                    break;
                case "delete contact":
                    option = MenuConsole.removeContact();
                    break;
                default:
                    MenuConsole.invalidOption();
                    optionLog.remove(optionLog.size()-1);
                    option = optionLog.get(optionLog.size()-1);
                    break;
            }
        }
    }
}
