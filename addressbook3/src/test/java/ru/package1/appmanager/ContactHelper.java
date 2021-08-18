package ru.package1.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.package1.model.ContactData;
import ru.package1.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ApplicationManager appl;

    public ContactHelper(ApplicationManager app) {
        super(app.wd);
        this.appl = app;
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

    public void select(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void selectByID(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void delete() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void selectContactFlagByID(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteContactButton() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public void create(ContactData contact, boolean creation) {
        initNewContact();
        fillContactForm(contact, creation);
        submitContactCreation();
        contactCashe = null;

    }

    public void modifyContact(ContactData contact) {
        fillContactForm(contact, false);
        submitContactModification();
        contactCashe = null;
        appl.goTo().ContactPage();
    }

    public void delete(ContactData contact) {
        selectContactFlagByID(contact.getId());
        deleteContactButton();
        closeAlert();
        contactCashe = null;
        appl.goTo().ContactPage();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCashe = null;

    public Contacts all() {
        if (contactCashe != null){
            return new Contacts(contactCashe);
        }
        contactCashe = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=\"entry\"]"));

        System.out.println("Получили список контактов из таблицы: ");
        for (WebElement element : elements) {
            String lastName = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.tagName("td"));
            contactCashe.add(new ContactData().withId(id).withFirstName(cells.get(2).getText().trim()).withLastName(cells.get(1).getText().trim()).withAddress(cells.get(3).getText().trim()).withEmail(cells.get(4).getText().trim()).withAllPhones(cells.get(5).getText().trim()));
            System.out.println(id + "; " + cells.get(1).getText() + "; " + cells.get(2).getText() + "; " + cells.get(3).getText() + "; " + cells.get(4).getText() + "; " + cells.get(5).getText());

        }
        return new Contacts(contactCashe);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        selectByID(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String fax = wd.findElement(By.name("fax")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomeTelephone(home).withMobileTelephone(mobile).withWorkTelephone(work).withFaxTelephone(fax);

    }
}
