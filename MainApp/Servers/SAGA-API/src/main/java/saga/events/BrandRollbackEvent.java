package saga.events;

public class BrandRollbackEvent {
    private Long brandId;

    public BrandRollbackEvent(){

    }

    public BrandRollbackEvent(Long brandId) {
        this.brandId = brandId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
