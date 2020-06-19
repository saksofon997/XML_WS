//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.19 at 09:59:26 PM CEST 
//


package agent.soap.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for vehicle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vehicle"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="brand" type="{http://www.vehicle.com/fuel}brand" minOccurs="0"/&gt;
 *         &lt;element name="category" type="{http://www.vehicle.com/fuel}category" minOccurs="0"/&gt;
 *         &lt;element name="cdw" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="childSeats" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="fuel" type="{http://www.vehicle.com/fuel}fuel" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="images" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="locationLatitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="locationLongitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="mileage" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="model" type="{http://www.vehicle.com/fuel}model" minOccurs="0"/&gt;
 *         &lt;element name="numberOfReviews" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="numberOfStars" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ownerId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="pricelist" type="{http://www.vehicle.com/fuel}pricelist" minOccurs="0"/&gt;
 *         &lt;element name="seats" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="transmission" type="{http://www.vehicle.com/fuel}transmission" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vehicle", propOrder = {
    "brand",
    "category",
    "cdw",
    "childSeats",
    "deleted",
    "fuel",
    "id",
    "images",
    "locationLatitude",
    "locationLongitude",
    "mileage",
    "model",
    "numberOfReviews",
    "numberOfStars",
    "ownerId",
    "pricelist",
    "seats",
    "transmission"
})
public class Vehicle {

    protected Brand brand;
    protected Category category;
    protected boolean cdw;
    protected int childSeats;
    protected boolean deleted;
    protected Fuel fuel;
    protected Long id;
    @XmlElement(nillable = true)
    protected List<String> images;
    protected double locationLatitude;
    protected double locationLongitude;
    protected long mileage;
    protected Model model;
    protected int numberOfReviews;
    protected int numberOfStars;
    protected Long ownerId;
    protected Pricelist pricelist;
    protected int seats;
    protected Transmission transmission;

    /**
     * Gets the value of the brand property.
     * 
     * @return
     *     possible object is
     *     {@link Brand }
     *     
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * Sets the value of the brand property.
     * 
     * @param value
     *     allowed object is
     *     {@link Brand }
     *     
     */
    public void setBrand(Brand value) {
        this.brand = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link Category }
     *     
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link Category }
     *     
     */
    public void setCategory(Category value) {
        this.category = value;
    }

    /**
     * Gets the value of the cdw property.
     * 
     */
    public boolean isCdw() {
        return cdw;
    }

    /**
     * Sets the value of the cdw property.
     * 
     */
    public void setCdw(boolean value) {
        this.cdw = value;
    }

    /**
     * Gets the value of the childSeats property.
     * 
     */
    public int getChildSeats() {
        return childSeats;
    }

    /**
     * Sets the value of the childSeats property.
     * 
     */
    public void setChildSeats(int value) {
        this.childSeats = value;
    }

    /**
     * Gets the value of the deleted property.
     * 
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     */
    public void setDeleted(boolean value) {
        this.deleted = value;
    }

    /**
     * Gets the value of the fuel property.
     * 
     * @return
     *     possible object is
     *     {@link Fuel }
     *     
     */
    public Fuel getFuel() {
        return fuel;
    }

    /**
     * Sets the value of the fuel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Fuel }
     *     
     */
    public void setFuel(Fuel value) {
        this.fuel = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the images property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the images property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getImages() {
        if (images == null) {
            images = new ArrayList<String>();
        }
        return this.images;
    }

    /**
     * Gets the value of the locationLatitude property.
     * 
     */
    public double getLocationLatitude() {
        return locationLatitude;
    }

    /**
     * Sets the value of the locationLatitude property.
     * 
     */
    public void setLocationLatitude(double value) {
        this.locationLatitude = value;
    }

    /**
     * Gets the value of the locationLongitude property.
     * 
     */
    public double getLocationLongitude() {
        return locationLongitude;
    }

    /**
     * Sets the value of the locationLongitude property.
     * 
     */
    public void setLocationLongitude(double value) {
        this.locationLongitude = value;
    }

    /**
     * Gets the value of the mileage property.
     * 
     */
    public long getMileage() {
        return mileage;
    }

    /**
     * Sets the value of the mileage property.
     * 
     */
    public void setMileage(long value) {
        this.mileage = value;
    }

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link Model }
     *     
     */
    public Model getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model }
     *     
     */
    public void setModel(Model value) {
        this.model = value;
    }

    /**
     * Gets the value of the numberOfReviews property.
     * 
     */
    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    /**
     * Sets the value of the numberOfReviews property.
     * 
     */
    public void setNumberOfReviews(int value) {
        this.numberOfReviews = value;
    }

    /**
     * Gets the value of the numberOfStars property.
     * 
     */
    public int getNumberOfStars() {
        return numberOfStars;
    }

    /**
     * Sets the value of the numberOfStars property.
     * 
     */
    public void setNumberOfStars(int value) {
        this.numberOfStars = value;
    }

    /**
     * Gets the value of the ownerId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the value of the ownerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOwnerId(Long value) {
        this.ownerId = value;
    }

    /**
     * Gets the value of the pricelist property.
     * 
     * @return
     *     possible object is
     *     {@link Pricelist }
     *     
     */
    public Pricelist getPricelist() {
        return pricelist;
    }

    /**
     * Sets the value of the pricelist property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pricelist }
     *     
     */
    public void setPricelist(Pricelist value) {
        this.pricelist = value;
    }

    /**
     * Gets the value of the seats property.
     * 
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Sets the value of the seats property.
     * 
     */
    public void setSeats(int value) {
        this.seats = value;
    }

    /**
     * Gets the value of the transmission property.
     * 
     * @return
     *     possible object is
     *     {@link Transmission }
     *     
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * Sets the value of the transmission property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transmission }
     *     
     */
    public void setTransmission(Transmission value) {
        this.transmission = value;
    }

}
