package br.ufsc.das.wsclient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.14.redhat-1
 * 2017-10-16T16:30:55.887-02:00
 * Generated source version: 2.7.14.redhat-1
 * 
 */
@WebService(targetNamespace = "http://das.ufsc.br/OrderProcess/", name = "OrderProcess")
@XmlSeeAlso({ObjectFactory.class})
public interface OrderProcess {

    @WebMethod(action = "http://das.ufsc.br/OrderProcess/acceptOrder")
    @RequestWrapper(localName = "acceptOrder", targetNamespace = "http://das.ufsc.br/OrderProcess/", className = "br.ufsc.das.wsclient.AcceptOrder")
    @ResponseWrapper(localName = "acceptOrderResponse", targetNamespace = "http://das.ufsc.br/OrderProcess/", className = "br.ufsc.das.wsclient.AcceptOrderResponse")
    @WebResult(name = "orderConfirmation", targetNamespace = "")
    public br.ufsc.das.wsclient.OrderConfirmation acceptOrder(
        @WebParam(name = "order", targetNamespace = "")
        br.ufsc.das.wsclient.Order order
    ) throws AcceptOrderFault_Exception;
}
