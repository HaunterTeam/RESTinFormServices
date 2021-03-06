
package document.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for person complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="person">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="birthdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="currentHealth" type="{http://ws.document/}measure" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fb_id" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="lastBMI" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="lastname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="measureHistory" type="{http://ws.document/}measure" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="oldBMI" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person", propOrder = {
    "birthdate",
    "currentHealth",
    "fbId",
    "firstname",
    "id",
    "lastBMI",
    "lastname",
    "measureHistory",
    "oldBMI"
})
public class Person {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar birthdate;
    @XmlElement(nillable = true)
    protected List<Measure> currentHealth;
    @XmlElement(name = "fb_id")
    protected double fbId;
    protected String firstname;
    protected Long id;
    protected double lastBMI;
    protected String lastname;
    @XmlElement(nillable = true)
    protected List<Measure> measureHistory;
    protected double oldBMI;

    /**
     * Gets the value of the birthdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the value of the birthdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthdate(XMLGregorianCalendar value) {
        this.birthdate = value;
    }

    /**
     * Gets the value of the currentHealth property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the currentHealth property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCurrentHealth().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Measure }
     * 
     * 
     */
    public List<Measure> getCurrentHealth() {
        if (currentHealth == null) {
            currentHealth = new ArrayList<Measure>();
        }
        return this.currentHealth;
    }

    /**
     * Gets the value of the fbId property.
     * 
     */
    public double getFbId() {
        return fbId;
    }

    /**
     * Sets the value of the fbId property.
     * 
     */
    public void setFbId(double value) {
        this.fbId = value;
    }

    /**
     * Gets the value of the firstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the value of the firstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
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
     * Gets the value of the lastBMI property.
     * 
     */
    public double getLastBMI() {
        return lastBMI;
    }

    /**
     * Sets the value of the lastBMI property.
     * 
     */
    public void setLastBMI(double value) {
        this.lastBMI = value;
    }

    /**
     * Gets the value of the lastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the value of the lastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastname(String value) {
        this.lastname = value;
    }

    /**
     * Gets the value of the measureHistory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the measureHistory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeasureHistory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Measure }
     * 
     * 
     */
    public List<Measure> getMeasureHistory() {
        if (measureHistory == null) {
            measureHistory = new ArrayList<Measure>();
        }
        return this.measureHistory;
    }

    /**
     * Gets the value of the oldBMI property.
     * 
     */
    public double getOldBMI() {
        return oldBMI;
    }

    /**
     * Sets the value of the oldBMI property.
     * 
     */
    public void setOldBMI(double value) {
        this.oldBMI = value;
    }

}
