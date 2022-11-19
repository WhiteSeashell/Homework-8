package ui;


import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

import logica.MenuHandler;



public class MenuConsole {
    private static Scanner sc = new Scanner(System.in);
    private static String answer;
    private static final String separator = "\n****************************************************************************\n";

    public static String welcome(){
        System.out.println(separator);
        System.out.println("Bienvenido a la agenda de Ecomún.\n");
        System.out.println("¿Que desea hacer? seleccione la opcion indicada:");
        System.out.println("\t1.Buscar contactos");
        System.out.println("\t2.Añadir contactos");
        System.out.println("\t3.Editar contactos");
        System.out.println("\t4.Eliminar contactos");
        System.out.println("\t5.Salir");
        answer = sc.nextLine();
        return switch (answer) {
            case "1" -> "search contact";
            case "2" -> "new contact";
            case "3" -> "edit contact";
            case "4" -> "delete contact";
            case "5" -> "exit";
            default -> "";
        };

    }

    public static String newContact(){

        String[] info = new String[4];
        System.out.println(separator);
        System.out.println("Usted a seleccionado crear un nuevo contacto: \n");
        System.out.println("Ingrese el nombre del nuevo contacto: ");
        info[0] = sc.nextLine();
        System.out.println("Ingrese el numero del nuevo contacto: ");
        info[1] = sc.nextLine();
        System.out.println("Ingrese la id del nuevo contacto: ");
        info[2] = sc.nextLine();
        System.out.println("¿Es el nuevo contacto un cliente o un proveedor?\n" +
                "Ingrese 1 si es cliente, 2 o cualquier otra tecla si es un proveedor:");
        info[3] = sc.nextLine().equals("1")? "true" : "false";
        if(MenuHandler.createContact(info)){
            System.out.println(separator);
            System.out.println("Se añadio exitosamente el contacto.");
            System.out.println("¿Que desea hacer? seleccione la opcion indicada:");
            return newContactOption();
        }else{
            System.out.println(separator);
            System.out.println("No se pudo añadir el nuevo contacto: numero no valido");
            System.out.println("¿Que desea hacer? seleccione la opcion indicada:");
            return newContactOption();
        }

    }

    private static String newContactOption(){
        System.out.println("\t1.Añadir contactos.");
        System.out.println("\t2.Regresar al menu principal.");
        answer = sc.nextLine();
        answer = answer.equals("1")? "new contact" : answer.equals("2")? "welcome":"";
        return answer;
    }


    public static String searchContact(){
        System.out.println(separator);
        System.out.println("Usted a seleccionado buscar contacto:");
        System.out.println("Ingrese el nombre el contacto: ");
        answer = sc.nextLine();
        System.out.println();
        HashMap<String,String> matches = MenuHandler.search(answer);
        if(matches.isEmpty()){
            System.out.println("No hay coincidencias para la busqueda");
        }
        int i = 1;
        String tempName;
        for(String u: matches.keySet()){
            tempName = u.replaceAll(answer,String.format("\033[1;34m%s\033[1;39m",answer));
            System.out.printf("%d.%s%n",i,tempName);
            System.out.printf("\t%s%n%n",matches.get(u));
        }

        System.out.println("Presione enter para volver.");
        sc.nextLine();

        return "welcome";
    }

    public static void invalidOption(){
        System.out.println(separator);
        System.out.println("La opción no es valida, vuelva a intentarlo");
    }

    public static String editContact(){
        String[] info = new String[3];
        System.out.println(separator);
        System.out.println("Usted a seleccionado editar contacto:");
        System.out.println("Ingrese el numero telefonico del contacto: ");
        System.out.println("Nota: si no esta seguro de el numero telefonico puede verlo en la opción de buscar contacto");
        String number = sc.nextLine();
        if (MenuHandler.getContact(number) == null){
            System.out.println("No se ha encontrado ningún registro que tenga el número telefónico indicado");
            return editContactOption();

        }
        System.out.println("se ha encontrado un contacto con el numero: " + number);
        System.out.println(MenuHandler.getContact(number).toString());
        System.out.println("Ingrese el nuevo nombre: ");
        info[0] = sc.nextLine();
        System.out.println("Ingrese el nuevo id: ");
        info[1] = sc.nextLine();
        System.out.println("Ingrese el nuevo rol, 1 si es cliente, 2 u otra tecla si es proveedor: ");
        info[2] = sc.nextLine();
        info[2] = info[2].equals(1)? "true":"false";
        MenuHandler.editContact(MenuHandler.getContact(number),info[0],info[1],Boolean.parseBoolean(info[2]));
        System.out.println("El contacto ha sido editado con exito");
        System.out.println(MenuHandler.getContact(number));
        return editContactOption();
    }

    private static String editContactOption(){
        System.out.println();
        System.out.println("¿Que desea hacer? seleccione la opción correspondiente");
        System.out.println("\t1.Editar contacto.");
        System.out.println("\t2.Regresar al menu principal.");
        answer = sc.nextLine();
        answer = answer.equals("1")? "edit contact" : answer.equals("2")? "welcome":"";
        return answer;
    }

    public static String removeContact(){
        System.out.println(separator);
        System.out.println("Usted a seleccionado eliminar contacto:");
        System.out.println("Ingrese el numero telefonico del contacto: ");
        System.out.println("Nota: si no esta seguro de el numero telefonico puede verlo en la opción de buscar contacto");
        String number = sc.nextLine();
        if (MenuHandler.getContact(number) == null){
            System.out.println("No se ha encontrado ningún registro que tenga el número telefónico indicado");
            return editContactOption();

        }
        System.out.println("se ha encontrado un contacto con el numero: " + number);
        System.out.println(MenuHandler.getContact(number).toString());
        MenuHandler.removeContact(MenuHandler.getContact(number));
        System.out.println("El contacto se ha eliminado exitosamente");
        return editContactOption();
    }

    private static String removeContactOption(){
        System.out.println();
        System.out.println("¿Que desea hacer? seleccione la opción correspondiente");
        System.out.println("\t1.Editar contacto.");
        System.out.println("\t2.Regresar al menu principal.");
        answer = sc.nextLine();
        answer = answer.equals("1")? "edit contact" : answer.equals("2")? "welcome":"";
        return answer;
    }







}
