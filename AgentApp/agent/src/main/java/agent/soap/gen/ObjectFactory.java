//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.21 at 03:26:00 PM CEST 
//


package agent.soap.gen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the agent.soap.gen package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CommonFault_QNAME = new QName("http://www.vehicle.com/vehicle", "commonFault");
    private final static QName _CreateNewVehicleRequest_QNAME = new QName("http://www.vehicle.com/vehicle", "createNewVehicleRequest");
    private final static QName _CreateNewVehicleResponse_QNAME = new QName("http://www.vehicle.com/vehicle", "createNewVehicleResponse");
    private final static QName _GetBrandsRequest_QNAME = new QName("http://www.vehicle.com/vehicle", "getBrandsRequest");
    private final static QName _GetBrandsResponse_QNAME = new QName("http://www.vehicle.com/vehicle", "getBrandsResponse");
    private final static QName _GetCategoriesRequest_QNAME = new QName("http://www.vehicle.com/vehicle", "getCategoriesRequest");
    private final static QName _GetCategoriesResponse_QNAME = new QName("http://www.vehicle.com/vehicle", "getCategoriesResponse");
    private final static QName _GetFuelsRequest_QNAME = new QName("http://www.vehicle.com/vehicle", "getFuelsRequest");
    private final static QName _GetFuelsResponse_QNAME = new QName("http://www.vehicle.com/vehicle", "getFuelsResponse");
    private final static QName _GetModelsRequest_QNAME = new QName("http://www.vehicle.com/vehicle", "getModelsRequest");
    private final static QName _GetModelsResponse_QNAME = new QName("http://www.vehicle.com/vehicle", "getModelsResponse");
    private final static QName _GetOneVehicleRequest_QNAME = new QName("http://www.vehicle.com/vehicle", "getOneVehicleRequest");
    private final static QName _GetOneVehicleResponse_QNAME = new QName("http://www.vehicle.com/vehicle", "getOneVehicleResponse");
    private final static QName _GetTransmissionsRequest_QNAME = new QName("http://www.vehicle.com/vehicle", "getTransmissionsRequest");
    private final static QName _GetTransmissionsResponse_QNAME = new QName("http://www.vehicle.com/vehicle", "getTransmissionsResponse");
    private final static QName _GetVehiclesRequest_QNAME = new QName("http://www.vehicle.com/vehicle", "getVehiclesRequest");
    private final static QName _GetVehiclesResponse_QNAME = new QName("http://www.vehicle.com/vehicle", "getVehiclesResponse");
    private final static QName _UpdateOneVehicleRequest_QNAME = new QName("http://www.vehicle.com/vehicle", "updateOneVehicleRequest");
    private final static QName _UpdateOneVehicleResponse_QNAME = new QName("http://www.vehicle.com/vehicle", "updateOneVehicleResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: agent.soap.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ErrorFault }
     * 
     */
    public ErrorFault createErrorFault() {
        return new ErrorFault();
    }

    /**
     * Create an instance of {@link Vehicle }
     * 
     */
    public Vehicle createVehicle() {
        return new Vehicle();
    }

    /**
     * Create an instance of {@link BrandArray }
     * 
     */
    public BrandArray createBrandArray() {
        return new BrandArray();
    }

    /**
     * Create an instance of {@link CategoryArray }
     * 
     */
    public CategoryArray createCategoryArray() {
        return new CategoryArray();
    }

    /**
     * Create an instance of {@link FuelArray }
     * 
     */
    public FuelArray createFuelArray() {
        return new FuelArray();
    }

    /**
     * Create an instance of {@link ModelArray }
     * 
     */
    public ModelArray createModelArray() {
        return new ModelArray();
    }

    /**
     * Create an instance of {@link TransmissionArray }
     * 
     */
    public TransmissionArray createTransmissionArray() {
        return new TransmissionArray();
    }

    /**
     * Create an instance of {@link VehicleArray }
     * 
     */
    public VehicleArray createVehicleArray() {
        return new VehicleArray();
    }

    /**
     * Create an instance of {@link Brand }
     * 
     */
    public Brand createBrand() {
        return new Brand();
    }

    /**
     * Create an instance of {@link Model }
     * 
     */
    public Model createModel() {
        return new Model();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link Fuel }
     * 
     */
    public Fuel createFuel() {
        return new Fuel();
    }

    /**
     * Create an instance of {@link Pricelist }
     * 
     */
    public Pricelist createPricelist() {
        return new Pricelist();
    }

    /**
     * Create an instance of {@link Transmission }
     * 
     */
    public Transmission createTransmission() {
        return new Transmission();
    }

    /**
     * Create an instance of {@link Review }
     * 
     */
    public Review createReview() {
        return new Review();
    }

    /**
     * Create an instance of {@link VehicleOccupancy }
     * 
     */
    public VehicleOccupancy createVehicleOccupancy() {
        return new VehicleOccupancy();
    }

    /**
     * Create an instance of {@link BrandDTO }
     * 
     */
    public BrandDTO createBrandDTO() {
        return new BrandDTO();
    }

    /**
     * Create an instance of {@link ModelDTO }
     * 
     */
    public ModelDTO createModelDTO() {
        return new ModelDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ErrorFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "commonFault")
    public JAXBElement<ErrorFault> createCommonFault(ErrorFault value) {
        return new JAXBElement<ErrorFault>(_CommonFault_QNAME, ErrorFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vehicle }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Vehicle }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "createNewVehicleRequest")
    public JAXBElement<Vehicle> createCreateNewVehicleRequest(Vehicle value) {
        return new JAXBElement<Vehicle>(_CreateNewVehicleRequest_QNAME, Vehicle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "createNewVehicleResponse")
    public JAXBElement<Long> createCreateNewVehicleResponse(Long value) {
        return new JAXBElement<Long>(_CreateNewVehicleResponse_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getBrandsRequest")
    public JAXBElement<String> createGetBrandsRequest(String value) {
        return new JAXBElement<String>(_GetBrandsRequest_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BrandArray }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BrandArray }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getBrandsResponse")
    public JAXBElement<BrandArray> createGetBrandsResponse(BrandArray value) {
        return new JAXBElement<BrandArray>(_GetBrandsResponse_QNAME, BrandArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getCategoriesRequest")
    public JAXBElement<String> createGetCategoriesRequest(String value) {
        return new JAXBElement<String>(_GetCategoriesRequest_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoryArray }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CategoryArray }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getCategoriesResponse")
    public JAXBElement<CategoryArray> createGetCategoriesResponse(CategoryArray value) {
        return new JAXBElement<CategoryArray>(_GetCategoriesResponse_QNAME, CategoryArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getFuelsRequest")
    public JAXBElement<String> createGetFuelsRequest(String value) {
        return new JAXBElement<String>(_GetFuelsRequest_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FuelArray }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FuelArray }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getFuelsResponse")
    public JAXBElement<FuelArray> createGetFuelsResponse(FuelArray value) {
        return new JAXBElement<FuelArray>(_GetFuelsResponse_QNAME, FuelArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getModelsRequest")
    public JAXBElement<Long> createGetModelsRequest(Long value) {
        return new JAXBElement<Long>(_GetModelsRequest_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModelArray }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ModelArray }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getModelsResponse")
    public JAXBElement<ModelArray> createGetModelsResponse(ModelArray value) {
        return new JAXBElement<ModelArray>(_GetModelsResponse_QNAME, ModelArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getOneVehicleRequest")
    public JAXBElement<Long> createGetOneVehicleRequest(Long value) {
        return new JAXBElement<Long>(_GetOneVehicleRequest_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vehicle }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Vehicle }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getOneVehicleResponse")
    public JAXBElement<Vehicle> createGetOneVehicleResponse(Vehicle value) {
        return new JAXBElement<Vehicle>(_GetOneVehicleResponse_QNAME, Vehicle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getTransmissionsRequest")
    public JAXBElement<String> createGetTransmissionsRequest(String value) {
        return new JAXBElement<String>(_GetTransmissionsRequest_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransmissionArray }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransmissionArray }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getTransmissionsResponse")
    public JAXBElement<TransmissionArray> createGetTransmissionsResponse(TransmissionArray value) {
        return new JAXBElement<TransmissionArray>(_GetTransmissionsResponse_QNAME, TransmissionArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getVehiclesRequest")
    public JAXBElement<String> createGetVehiclesRequest(String value) {
        return new JAXBElement<String>(_GetVehiclesRequest_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VehicleArray }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VehicleArray }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "getVehiclesResponse")
    public JAXBElement<VehicleArray> createGetVehiclesResponse(VehicleArray value) {
        return new JAXBElement<VehicleArray>(_GetVehiclesResponse_QNAME, VehicleArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vehicle }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Vehicle }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "updateOneVehicleRequest")
    public JAXBElement<Vehicle> createUpdateOneVehicleRequest(Vehicle value) {
        return new JAXBElement<Vehicle>(_UpdateOneVehicleRequest_QNAME, Vehicle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vehicle }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Vehicle }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.vehicle.com/vehicle", name = "updateOneVehicleResponse")
    public JAXBElement<Vehicle> createUpdateOneVehicleResponse(Vehicle value) {
        return new JAXBElement<Vehicle>(_UpdateOneVehicleResponse_QNAME, Vehicle.class, null, value);
    }

}
