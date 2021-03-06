package ru.package1.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.package1.model.GroupData;
import ru.package1.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {
    public  ApplicationManager appl;

    public GroupHelper(ApplicationManager app) {
        super(app.wd);
        this.appl = app;

    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroupByID(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCashe = null;
        appl.goTo().groupPage();
    }

    public void modify(GroupData group) {
        selectGroupByID(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCashe = null;
        appl.goTo().groupPage();

    }

    public void delete(GroupData group) {
        selectGroupByID(group.getId());
        deleteSelectedGroups();
        groupCashe = null;
        appl.goTo().groupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Groups groupCashe = null;

    public Groups all() {
        if (groupCashe != null){
            return new Groups(groupCashe);
        }
        groupCashe = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        System.out.println("???????????????? ???????????? ??????????: ");
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCashe.add(new GroupData().withId(id).withName(name));
            System.out.println(name+"; ");
        }
        return new Groups(groupCashe);
    }


}
