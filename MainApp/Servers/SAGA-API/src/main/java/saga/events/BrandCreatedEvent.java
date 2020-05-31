package saga.events;

import saga.dto.BrandDTO;

public class BrandCreatedEvent {
    private Long brandId;
    private BrandDTO brandDTO;

    public BrandCreatedEvent() {}

    public BrandCreatedEvent(Long brandId, BrandDTO brandDTO) {
        this.brandId = brandId;
        this.brandDTO = brandDTO;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public BrandDTO getBrandDTO() {
        return brandDTO;
    }

    public void setBrandDTO(BrandDTO brandDTO) {
        this.brandDTO = brandDTO;
    }
}
