package ru.package1.model;

import com.solidfire.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;
@XStreamAlias("contact")
public class ContactData {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private  String firstName;
    @Expose
    private  String middleName;
    @Expose
    private  String lastName;
    @Expose
    private  String nickname;
    @Expose
    private  String title;
    @Expose
    private  String company;
    @Expose
    private  String address;
    @Expose
    private  String homeTelephone;
    @Expose
    private  String mobileTelephone;
    @Expose
    private  String workTelephone;
    @Expose
    private  String faxTelephone;
    @Expose
    private  String work;
    @Expose
    private  String email;
    private  String email2;
    private  String email3;
    @Expose
    private  String birthday;
    @Expose
    private  String bmonth;
    @Expose
    private  String year;
    @Expose
    private  String group;
    private  String allPhones;
    private  String allemail;
    @Expose
    private File photo;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

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
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
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
        return work;
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

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
}
