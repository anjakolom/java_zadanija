package ru.package1.model;

import com.solidfire.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;
@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    //private int id = Integer.MAX_VALUE;
    private int id;
    @Expose
    @Column(name = "firstname")
    private  String firstName;
    @Expose
    @Column(name = "middleName")
    private  String middleName;
    @Expose
    @Column(name = "lastName")
    private  String lastName;
    @Expose
    @Column(name = "nickname")
    private  String nickname;
    @Expose
    @Column(name = "title")
    private  String title;
    @Expose
    @Column(name = "company")
    private  String company;
    @Expose
    @Column(name = "address")
    @Type(type="text")
    private  String address;
    @Expose
    @Column(name = "home")
    @Type(type="text")
    private  String homeTelephone;
    @Expose
    @Column(name = "mobile")
    @Type(type="text")
    private  String mobileTelephone;
    @Expose
    @Column(name = "work")
    @Type(type="text")
    private  String workTelephone;
    @Expose
    @Column(name = "fax")
    @Type(type="text")
    private  String faxTelephone;
    @Expose
    @Transient
    private  String work;
    @Expose
    @Column(name = "email")
    @Type(type="text")
    private  String email;
    @Expose
    @Type(type="text")
    @Column(name = "email2")
    private  String email2;
    @Expose
    @Type(type="text")
    @Column(name = "email3")
    private  String email3;
    @Expose
    @Transient
    private  String birthday;
    @Expose
    @Column(name = "bmonth")
    private  String bmonth;
    @Expose
    @Column(name = "byear")
    private  String year;
    @Expose
    @Transient
    private  String group;
    @Transient
    private  String allPhones;
    @Transient
    private  String allemail;
    @Expose
    @Column(name="photo")
    @Type(type="text")
    private String photo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(title, that.title) &&
                Objects.equals(company, that.company) &&
                Objects.equals(address, that.address) &&
                Objects.equals(mobileTelephone, that.mobileTelephone) &&
                Objects.equals(workTelephone, that.workTelephone) &&
                Objects.equals(email, that.email) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, nickname, title, company, address, mobileTelephone, workTelephone, email);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", mobileTelephone='" + mobileTelephone + '\'' +
                ", work='" + workTelephone + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    /* @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }*/

    public int getId() {
        return id;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }
    public ContactData withHomeTelephone(String telephone) {
        this.homeTelephone = telephone;
        return this;
    }
    public ContactData withMobileTelephone(String mobileTelephone) {
        this.mobileTelephone = mobileTelephone;
        return this;
    }


    public ContactData withWorkTelephone(String telephone) {
        this.workTelephone= telephone;
        return this;
    }

    public ContactData withFaxTelephone(String telephone) {
        this.faxTelephone = telephone;
        return this;
    }

    public ContactData withAllPhones(String telephone) {
        this.allPhones = telephone;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }


    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public ContactData withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData withYear(String year) {
        this.year = year;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withEmail2(String email) {
        this.email2 = email;
        return this;
    }

    public ContactData withEmail3(String email) {
        this.email3 = email;
        return this;
    }

    public ContactData withAllemail(String email) {
        this.allemail = email;
        return this;
    }
    public File getPhoto() {
        if (photo!=null) {
            return new File(photo);
        } else{
            return  null;
        }
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getMobileTelephone() {
        return mobileTelephone;
    }

    public String getWork() {
        return workTelephone;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public String getAllemail() {
        return allemail;
    }

    public void setAllemail(String allemail) {
        this.allemail = allemail;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getYear() {
        return year;
    }

    public String getGroup() {
        return group;
    }

    public String getHomeTelephone() {
        return homeTelephone;
    }

    public String getWorkTelephone() {
        return workTelephone;
    }

    public String getFaxTelephone() {
        return faxTelephone;
    }

    /* @Override
        public String toString() {
            return "ContactData{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    */
    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
}
