
package document.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for savePersonMeasurementResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="savePersonMeasurementResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="measurehistory" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "savePersonMeasurementResponse", propOrder = {
    "measurehistory"
})
public class SavePersonMeasurementResponse {

    protected Long measurehistory;

    /**
     * Gets the value of the measurehistory property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMeasurehistory() {
        return measurehistory;
    }

    /**
     * Sets the value of the measurehistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMeasurehistory(Long value) {
        this.measurehistory = value;
    }

}
