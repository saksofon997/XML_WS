package saga.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import saga.dto.BrandDTO;

public class ReplicateBrandCommand {
    @TargetAggregateIdentifier
    private String brandAggregateId;

    private Long brandId;
    private BrandDTO brandDTO;

    public ReplicateBrandCommand(){

    }

    public ReplicateBrandCommand(String brandAggregateId, Long brandId, BrandDTO brandDTO) {
        this.brandAggregateId = brandAggregateId;
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

    public String getBrandAggregateId() {
        return brandAggregateId;
    }

    public void setBrandAggregateId(String orderAggregateId) {
        this.brandAggregateId = orderAggregateId;
    }
}
