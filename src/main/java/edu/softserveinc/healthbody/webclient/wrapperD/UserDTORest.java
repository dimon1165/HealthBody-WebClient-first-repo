package edu.softserveinc.healthbody.webclient.wrapperD;

import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.webclient.healthbody.webservice.GroupDTO;

public class UserDTORest {

public class UserDTO {

    protected String age;
    protected String email;
    protected String firstname;
    protected String gender;
    protected String googleApi;
    protected List<GroupDTO> groups;
    protected String health;
    protected String idUser;
    protected String isDisabled;
    protected String lastname;
    protected String login;
    protected String password;
    protected String photoURL;
    protected String roleName;
    protected String score;
    protected String status;
    protected String weight;


    public String getAge() {
        return age;
    }

    public void setAge(String value) {
        this.age = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String value) {
        this.firstname = value;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String value) {
        this.gender = value;
    }

    public String getGoogleApi() {
        return googleApi;
    }

    public void setGoogleApi(String value) {
        this.googleApi = value;
    }

    /**
     * Gets the value of the groups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GroupDTO }
     * 
     * 
     */
    public List<GroupDTO> getGroups() {
        if (groups == null) {
            groups = new ArrayList<GroupDTO>();
        }
        return this.groups;
    }

    /**
     * Gets the value of the health property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealth() {
        return health;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String value) {
        this.idUser = value;
    }


    public String getIsDisabled() {
        return isDisabled;
    }


    public void setIsDisabled(String value) {
        this.isDisabled = value;
    }


    public String getLastname() {
        return lastname;
    }


    public void setLastname(String value) {
        this.lastname = value;
    }


    public String getLogin() {
        return login;
    }


    public void setLogin(String value) {
        this.login = value;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String value) {
        this.password = value;
    }


    public String getPhotoURL() {
        return photoURL;
    }

    /**
     * Sets the value of the photoURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhotoURL(String value) {
        this.photoURL = value;
    }

    /**
     * Gets the value of the roleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String value) {
        this.roleName = value;
    }


    public String getScore() {
        return score;
    }


    public void setScore(String value) {
        this.score = value;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String value) {
        this.status = value;
    }


    public String getWeight() {
        return weight;
    }
  

    public void setWeight(String value) {
        this.weight = value;
    }

}

}
