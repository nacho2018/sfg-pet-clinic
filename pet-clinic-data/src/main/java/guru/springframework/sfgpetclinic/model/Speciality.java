package guru.springframework.sfgpetclinic.model;

public class Speciality extends BaseEntity{

    private String descripion;

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }
}
