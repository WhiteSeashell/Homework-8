package datos;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String number;
    private boolean isClient;
    private String id;


    public Contact(String name, String number, String id, boolean isClient) {
        this.setName(name);
        this.setNumber(number);
        this.setClient(isClient);
        this.setId(id);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.isBlank()){
            this.name = name;
        }else{
            this.name = "unknown";
        }

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isClient() {
        return isClient;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contacto: (" +
                "nombre: " + name +
                ", numero: " + number +
                ", cliente:" + isClient +
                ", id: " + id + " " +
                ')';
    }
}
