package ru.package1.model;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String mobileTelephone;
    private final String work;
    private final String email;
    private final String birthday;
    private final String bmonth;
    private final String year;
    private final String group;


    public ContactData(String firstName, String middleName, String lastName, String nickname, String title, String company, String address, String mobileTelephone, String work, String email, String birthday, String bmonth, String year, String group) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.mobileTelephone = mobileTelephone;
        this.work = work;
        this.email = email;
        this.birthday = birthday;
        this.bmonth = bmonth;
        this.year = year;
        this.group = group;
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
}
