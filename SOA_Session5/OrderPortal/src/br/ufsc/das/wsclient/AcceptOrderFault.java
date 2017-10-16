
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
 *         &lt;element name="CEP_fault" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "cepFault"
})
@XmlRootElement(name = "acceptOrderFault")
public class AcceptOrderFault {

    @XmlElement(name = "CEP_fault", required = true)
    protected String cepFault;

    /**
     * Gets the value of the cepFault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCEPFault() {
        return cepFault;
    }

    /**
     * Sets the value of the cepFault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCEPFault(String value) {
        this.cepFault = value;
    }

}
