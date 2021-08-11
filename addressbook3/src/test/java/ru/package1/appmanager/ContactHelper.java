package ru.package1.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.package1.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobileTelephone());
        type(By.name("work"), contactData.getWork());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            select(By.name("new_group"), contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Secondary'])[1]/preceding::option[1]"));

    }

    public void submitContactCreation() {
        click(By.xpath("//input[21]"));
    }

    public void initNewContact() {
        if (isElementPresent(By.name("submit"))
                && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")) {
            return;
        }
        click(By.linkText("add new"));

    }

    public void selectContact(int index) {
        //click(By.xpath("//img[@alt='Edit']"));
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void selectContactFlag(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContactButton() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public void createContact(ContactData contact, boolean creation) {
        initNewContact();
        fillContactForm(contact, creation);
        submitContactCreation();

    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();

    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=\"entry\"]"));//By.cssSelector("#maintable input[type='checkbox']"));

        System.out.println("Получили список контактов из таблицы: ");
        for (WebElement element : elements) {
            String lastName = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.tagName("td"));
            ContactData contact = new ContactData(id, cells.get(2).getText().trim(), null, cells.get(1).getText().trim(), null, null, null, cells.get(3).getText().trim(), cells.get(5).getText(), null, cells.get(4).getText(), null, null, null, null);
            contacts.add(contact);
            System.out.println(id + "; " + cells.get(1).getText() + "; " + cells.get(2).getText() + "; " + cells.get(3).getText() + "; " + cells.get(4).getText() + "; " + cells.get(5).getText());

        }
        return contacts;
    }
}
