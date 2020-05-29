package vehicle.model;

import java.util.ArrayList;

public class Brand {

    private Long id;
    private String name;
    private ArrayList<Model> models;

    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Brand(Long id, String name, ArrayList<Model> models) {
        this.id = id;
        this.name = name;
        this.models = models;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Model> getModels() {
        return models;
    }

    public void setModels(ArrayList<Model> models) {
        this.models = models;
    }
}
