package model;

public class Singer {
    private String name;
    private int id;

    public Singer(String name,int id){
        this.id=id;
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
