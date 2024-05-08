package tn.esprit.entities;

public class Internship {
    private int id;
    private  int departement_id;
    private String title;
    private String description;
    private String technology;
    private String typeinternship;
    private String startdate;
    private String period;
    // private String image_url;
    public Internship(int id, int departement_id, String title, String description, String technology, String typeinternship, String startdate, String period) {
        this.id = id;
        this.departement_id = departement_id;
        this.title = title;
        this.description = description;
        this.technology = technology;
        this.typeinternship = typeinternship;
        this.startdate = startdate;
        this.period = period;
    }

    public Internship(int departement_id, String title, String description, String technology, String typeinternship, String startdate, String period) {
        this.departement_id = departement_id;
        this.title = title;
        this.description = description;
        this.technology = technology;
        this.typeinternship = typeinternship;
        this.startdate = startdate;
        this.period = period;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartement_id() {
        return departement_id;
    }

    public void setDepartement_id(int departement_id) {
        this.departement_id = departement_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getTypeinternship() {
        return typeinternship;
    }

    public void setTypeinternship(String typeinternship) {
        this.typeinternship = typeinternship;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
    /*
    public String getImgUrl() {
        return image_url;
    }
*/
    /*
    public void setImgUrl(String image_url) {
        this.image_url=image_url;
    }

     */
}