
package br.ufsc.das.wsclient;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.ufsc.das.wsclient package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.ufsc.das.wsclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AcceptOrder }
     * 
     */
    public AcceptOrder createAcceptOrder() {
        return new AcceptOrder();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link AcceptOrderFault }
     * 
     */
    public AcceptOrderFault createAcceptOrderFault() {
        return new AcceptOrderFault();
    }

    /**
     * Create an instance of {@link AcceptOrderResponse }
     * 
     */
    public AcceptOrderResponse createAcceptOrderResponse() {
        return new AcceptOrderResponse();
    }

    /**
     * Create an instance of {@link OrderConfirmation }
     * 
     */
    public OrderConfirmation createOrderConfirmation() {
        return new OrderConfirmation();
    }

}
