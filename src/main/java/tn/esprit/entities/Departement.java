package tn.esprit.entities;

public class Departement {
    private int id;
    private int headmaster_id;
    private String name;
    private String description;

    public Departement(int id, int headmaster_id, String name, String description) {
        this.id = id;
        this.headmaster_id = headmaster_id;
        this.name = name;
        this.description = description;
    }

    public Departement(int id) {
        this.id = id;
    }

    public Departement() {
    }

    public Departement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Departement(int headmaster_id, String name, String description) {
        this.headmaster_id = headmaster_id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", headmaster_id=" + headmaster_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getHeadmaster_id() {
        return headmaster_id;
    }

    public void setHeadmaster_id(int headmaster_id) {
        this.headmaster_id = headmaster_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
