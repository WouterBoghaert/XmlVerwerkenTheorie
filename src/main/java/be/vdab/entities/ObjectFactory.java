//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.02.07 at 05:32:53 PM CET 
//


package be.vdab.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the be.vdab.entities package. 
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

    private final static QName _Koersen_QNAME = new QName("http://www.vdab.be/koersen", "koersen");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: be.vdab.entities
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Koersen }
     * 
     */
    public Koersen createKoersen() {
        return new Koersen();
    }

    /**
     * Create an instance of {@link Munt }
     * 
     */
    public Munt createMunt() {
        return new Munt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Koersen }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.vdab.be/koersen", name = "koersen")
    public JAXBElement<Koersen> createKoersen(Koersen value) {
        return new JAXBElement<Koersen>(_Koersen_QNAME, Koersen.class, null, value);
    }

}
