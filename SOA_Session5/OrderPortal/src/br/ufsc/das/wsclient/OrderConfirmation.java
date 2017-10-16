
package br.ufsc.das.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderConfirmation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderConfirmation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clientAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clientCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clientState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderConfirmation", propOrder = {
    "orderId",
    "clientName",
    "clientAddress",
    "clientCity",
    "clientState",
    "quantity",
    "total"
})
public class OrderConfirmation {

    @XmlElement(name = "OrderId")
    protected int orderId;
    @XmlElement(required = true)
    protected String clientName;
    @XmlElement(required = true)
    protected String clientAddress;
    @XmlElement(required = true)
    protected String clientCity;
    @XmlElement(required = true)
    protected String clientState;
    protected int quantity;
    protected float total;

    /**
     * Gets the value of the orderId property.
     * 
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     */
    public void setOrderId(int value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the clientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the value of the clientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientName(String value) {
        this.clientName = value;
    }

    /**
     * Gets the value of the clientAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientAddress() {
        return clientAddress;
    }

    /**
     * Sets the value of the clientAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientAddress(String value) {
        this.clientAddress = value;
    }

    /**
     * Gets the value of the clientCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientCity() {
        return clientCity;
    }

    /**
     * Sets the value of the clientCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientCity(String value) {
        this.clientCity = value;
    }

    /**
     * Gets the value of the clientState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientState() {
        return clientState;
    }

    /**
     * Sets the value of the clientState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientState(String value) {
        this.clientState = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the total property.
     * 
     */
    public float getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(float value) {
        this.total = value;
    }

}
