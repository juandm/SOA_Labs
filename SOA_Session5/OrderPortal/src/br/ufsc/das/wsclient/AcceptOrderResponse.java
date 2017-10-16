
package br.ufsc.das.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderConfirmation" type="{http://das.ufsc.br/OrderProcess/}OrderConfirmation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orderConfirmation"
})
@XmlRootElement(name = "acceptOrderResponse")
public class AcceptOrderResponse {

    @XmlElement(required = true)
    protected OrderConfirmation orderConfirmation;

    /**
     * Gets the value of the orderConfirmation property.
     * 
     * @return
     *     possible object is
     *     {@link OrderConfirmation }
     *     
     */
    public OrderConfirmation getOrderConfirmation() {
        return orderConfirmation;
    }

    /**
     * Sets the value of the orderConfirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderConfirmation }
     *     
     */
    public void setOrderConfirmation(OrderConfirmation value) {
        this.orderConfirmation = value;
    }

}
