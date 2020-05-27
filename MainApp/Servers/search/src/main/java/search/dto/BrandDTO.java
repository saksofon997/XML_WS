package search.dto;

import java.util.ArrayList;

public class BrandDTO {

    private Long id;
    private String name;
    private ArrayList<ModelDTO> models;

    public BrandDTO() {
    }

    public BrandDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BrandDTO(Long id, String name, ArrayList<ModelDTO> models) {
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

    public ArrayList<ModelDTO> getModels() {
        return models;
    }

    public void setModels(ArrayList<ModelDTO> models) {
        this.models = models;
    }
}
