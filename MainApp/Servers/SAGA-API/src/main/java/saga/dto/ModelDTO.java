package saga.dto;

public class ModelDTO {

    private Long id;
    private String name;
    private BrandDTO brandDTO;

    public ModelDTO() {
    }

    public ModelDTO(Long id, String name, BrandDTO brandDTO) {
        this.id = id;
        this.name = name;
        this.brandDTO = brandDTO;
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

    public BrandDTO getBrandDTO() {
        return brandDTO;
    }

    public void setBrandDTO(BrandDTO brandDTO) {
        this.brandDTO = brandDTO;
    }
}
