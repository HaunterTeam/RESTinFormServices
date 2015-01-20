
package document.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createPersonResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createPersonResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="singlePerson" type="{http://ws.document/}person" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPersonResponse", propOrder = {
    "singlePerson"
})
public class CreatePersonResponse {

    protected Person singlePerson;

    /**
     * Gets the value of the singlePerson property.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getSinglePerson() {
        return singlePerson;
    }

    /**
     * Sets the value of the singlePerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setSinglePerson(Person value) {
        this.singlePerson = value;
    }

}
