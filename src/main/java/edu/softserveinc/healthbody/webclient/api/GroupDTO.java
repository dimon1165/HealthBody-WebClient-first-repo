
package edu.softserveinc.healthbody.webclient.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for groupDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="groupDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descriptions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scoreGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "groupDTO", propOrder = {
    "count",
    "descriptions",
    "name",
    "scoreGroup"
})
public class GroupDTO {

    protected String count;
    protected String descriptions;
    protected String name;
    protected String scoreGroup;

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCount(String value) {
        this.count = value;
    }

    /**
     * Gets the value of the descriptions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     * Sets the value of the descriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptions(String value) {
        this.descriptions = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the scoreGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScoreGroup() {
        return scoreGroup;
    }

    /**
     * Sets the value of the scoreGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScoreGroup(String value) {
        this.scoreGroup = value;
    }

}
