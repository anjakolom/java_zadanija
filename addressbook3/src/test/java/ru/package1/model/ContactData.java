package ru.package1.model;

import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private  String firstName;
    private  String middleName;
    private  String lastName;
    private  String nickname;
    private  String title;
    private  String company;
    private  String address;
    private  String homeTelephone;
    private  String mobileTelephone;
    private  String workTelephone;
    private  String faxTelephone;
    private  String work;
    private  String email;
    private  String birthday;
    private  String bmonth;
    private  String year;
    private  String group;
    private  String allPhones;

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
