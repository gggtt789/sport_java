package com.sport.sport;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class SportApplicationSeleniumTests {
    String URL = "http://localhost:8080/";
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void settings() {
        final String ffDriver = "/usr/bin/geckodriver";
        System.setProperty("webdriver.gecko.driver", ffDriver);
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().window().setSize(new Dimension(1200, 1000));
        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);
    }

    @Test(priority = 1)
    public void eventsPageTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.cssSelector("button#Events-button")));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Teams-button")));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Member-button")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "Спортивные события");

        button = driver.findElement(By.cssSelector("#Add-event-button"));
        button.click();
        wait.until(visibilityOfElementLocated(By.cssSelector("form")));

        driver.findElement(By.id("nameEventAddInput")).sendKeys("Event1");
        driver.findElement(By.id("venueEventAddInput")).sendKeys("Venue1");
        driver.findElement(By.id("resultEventAddInput")).sendKeys("Result1");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#dateEventAddInput').setAttribute('value', '2020-08-12T00:00')");

        button = driver.findElement(By.cssSelector("button[type=submit]"));
        button.click();
        wait.until(textToBePresentInElement(By.cssSelector("div.modal-body"), "Событие успешно добавлено!"));
        button = driver.findElement(By.cssSelector("button.btn-close"));;
        button.click();

        button = wait.until(visibilityOfElementLocated(By.cssSelector("button#Events-button")));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Event1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Venue1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "12.08.2020 00:00"));

        WebElement price_filter = driver.findElement(By.cssSelector("li[max-to-min]"));
        price_filter.click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#accordionElems")).getText(), "");

        price_filter = driver.findElement(By.cssSelector("li[min-to-max]"));
        price_filter.click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#accordionElems")).getText(), "");

        WebElement venue_filter = driver.findElement(By.cssSelector("li[venue]"));
        venue_filter.click();
        wait.until(visibilityOfElementLocated(By.cssSelector("form#venueFilterForm")));
        driver.findElement(By.id("venueFilterFormInput")).sendKeys("Venue1");
        button = driver.findElement(By.cssSelector("button[type=submit]"));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(visibilityOfElementLocated(By.cssSelector("div.accordion-item")));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Event1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Venue1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "12.08.2020 00:00"));

        WebElement date_filter = driver.findElement(By.cssSelector("li[date-time]"));
        date_filter.click();
        wait.until(visibilityOfElementLocated(By.cssSelector("form#dateFilterForm")));
        js.executeScript("document.querySelector('#dateFilterFormInput').setAttribute('value', '2020-07-12T00:00')");
        button = driver.findElement(By.cssSelector("button[type=submit]"));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(visibilityOfElementLocated(By.cssSelector("div.accordion-item")));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Event1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Venue1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "12.08.2020 00:00"));

    }

    @Test(priority = 1)
    public void teamsPageTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.cssSelector("button#Teams-button")));
        button.click();
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Member-button")));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Events-button")));
        Assert.assertEquals(driver.getTitle(), "Команды");

        button = driver.findElement(By.cssSelector("#Add-team-button"));
        button.click();

        wait.until(visibilityOfElementLocated(By.cssSelector("form")));

        driver.findElement(By.id("nameTeamAddInput")).sendKeys("Team1");
        driver.findElement(By.id("sportTeamAddInput")).sendKeys("Sport1");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#birthdayTeamAddInput').setAttribute('value', '2020-08-13')");

        button = driver.findElement(By.cssSelector("button[type=submit]"));
        button.click();
        wait.until(textToBePresentInElement(By.cssSelector("div.modal-body"), "Команда успешно добавлена!"));
        button = driver.findElement(By.cssSelector("button.btn-close"));;
        button.click();

        button = wait.until(visibilityOfElementLocated(By.cssSelector("button#Teams-button")));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Team1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Sport1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "2020-08-13"));

        WebElement date_filter = driver.findElement(By.cssSelector("li[birthday]"));
        date_filter.click();
        wait.until(visibilityOfElementLocated(By.cssSelector("form#birthdayFilterForm")));
        js.executeScript("document.querySelector('#birthdayFilterFormInput').setAttribute('value', '2020-08-13')");
        button = driver.findElement(By.cssSelector("button[type=submit]"));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(visibilityOfElementLocated(By.cssSelector("div.accordion-item")));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Team1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Sport1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "2020-08-13"));

        WebElement sport_filter = driver.findElement(By.cssSelector("li[sport]"));
        sport_filter.click();
        wait.until(visibilityOfElementLocated(By.cssSelector("form#sportFilterForm")));
        driver.findElement(By.cssSelector("#sportFilterFormInput")).sendKeys("Sport1");
        button = driver.findElement(By.cssSelector("button[type=submit]"));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(visibilityOfElementLocated(By.cssSelector("div.accordion-item")));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Team1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Sport1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "2020-08-13"));

        WebElement team_name_filter = driver.findElement(By.cssSelector("li[name]"));
        team_name_filter.click();
        wait.until(visibilityOfElementLocated(By.cssSelector("form#nameFilterForm")));
        driver.findElement(By.cssSelector("#nameFilterFormInput")).sendKeys("Team1");
        button = driver.findElement(By.cssSelector("button[type=submit]"));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(visibilityOfElementLocated(By.cssSelector("div.accordion-item")));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Team1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Sport1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "2020-08-13"));

    }

    @Test(priority = 1)
    public void membersPageTest() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.cssSelector("button#Member-button")));
        button.click();
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Events-button")));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Teams-button")));
        Assert.assertEquals(driver.getTitle(), "Спортсмены");

        button = driver.findElement(By.cssSelector("#Add-member-button"));
        button.click();

        wait.until(visibilityOfElementLocated(By.cssSelector("form")));

        driver.findElement(By.id("nameMemberAddInput")).sendKeys("Sportsman1");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#birthdayMemberAddInput').setAttribute('value', '1990-05-15')");

        button = driver.findElement(By.cssSelector("button[type=submit]"));
        button.click();
        wait.until(textToBePresentInElement(By.cssSelector("div.modal-body"), "Спортсмен успешно добавлен!"));
        button = driver.findElement(By.cssSelector("button.btn-close"));;
        button.click();

        button = wait.until(visibilityOfElementLocated(By.cssSelector("#Member-button")));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(visibilityOfElementLocated(By.cssSelector("div.accordion-item")));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Sportsman1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "1990-05-15"));

        WebElement name_filter = driver.findElement(By.cssSelector("li[member-name]"));
        name_filter.click();
        wait.until(visibilityOfElementLocated(By.cssSelector("form#memberNameFilterForm")));
        driver.findElement(By.cssSelector("#memberNameFilterFormInput")).sendKeys("Sportsman1");
        button = driver.findElement(By.cssSelector("button[type=submit]"));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(visibilityOfElementLocated(By.cssSelector("div.accordion-item")));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "Sportsman1"));
        wait.until(textToBePresentInElement(By.cssSelector("div.accordion-item"), "1990-05-15"));

    }

    @Test(priority = 2)
    public void eventPageTest() {
        driver.get(URL);

        WebElement link = wait.until(visibilityOfElementLocated(By.cssSelector("div.accordion-item a")));
        link.click();
        wait.until(stalenessOf(link));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Events-button")));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Teams-button")));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Member-button")));

        wait.until(textToBePresentInElement(By.cssSelector("h2[name-holder]"), "Event1"));
        wait.until(textToBePresentInElement(By.cssSelector("div[place-holder]"), "место: Venue1"));
        wait.until(textToBePresentInElement(By.cssSelector("div[time-holder]"), "время: 12.08.2020 00:00"));
        wait.until(textToBePresentInElement(By.cssSelector("div[result-holder]"), "итог: Неизвестен"));

        WebElement button = wait.until(visibilityOfElementLocated(By.cssSelector("#Edit-event-button")));
        button.click();
        wait.until(visibilityOfElementLocated(By.cssSelector("#EditEventForm")));

        driver.findElement(By.cssSelector("#eventNameInput")).sendKeys("Event2");
        driver.findElement(By.cssSelector("#eventVenueInput")).sendKeys("venue2");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#eventStartInput').setAttribute('value', '2022-04-13T00:00')");
        button = driver.findElement(By.cssSelector("button[type=submit]"));
        button.click();
        wait.until(textToBePresentInElement(By.cssSelector("div.modal-body"), "Данные о событии успешно обновлены!"));
        button = driver.findElement(By.cssSelector("button.btn-close"));;
        button.click();

        driver.get(driver.getCurrentUrl());
        wait.until(textToBePresentInElement(By.cssSelector("h2[name-holder]"), "Event2"));
        wait.until(textToBePresentInElement(By.cssSelector("div[place-holder]"), "место: venue2"));
        wait.until(textToBePresentInElement(By.cssSelector("div[time-holder]"), "время: 13.04.2022 00:00"));
        wait.until(textToBePresentInElement(By.cssSelector("div[result-holder]"), "итог: Неизвестен"));
    }

    @Test(priority = 2)
    public void teamPageTest() {
        driver.get(URL);
        WebElement button = wait.until(visibilityOfElementLocated(By.cssSelector("button#Teams-button")));
        button.click();

        WebElement link = wait.until(visibilityOfElementLocated(By.cssSelector("div.accordion-item a")));
        link.click();
        wait.until(stalenessOf(link));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Events-button")));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Teams-button")));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Member-button")));

        wait.until(textToBePresentInElement(By.cssSelector("h2[name-holder]"), "Team1"));
        wait.until(textToBePresentInElement(By.cssSelector("div[sport-holder]"), "вид спорта: Sport1"));

        button = wait.until(visibilityOfElementLocated(By.cssSelector("#Edit-team-button")));
        button.click();
        wait.until(visibilityOfElementLocated(By.cssSelector("#EditTeamForm")));

        driver.findElement(By.cssSelector("#teamNameInput")).sendKeys("Team2");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#birthdayTeamInput').setAttribute('value', '2019-08-13')");
        button = driver.findElement(By.cssSelector("button[type=submit]"));
        button.click();
        wait.until(textToBePresentInElement(By.cssSelector("div.modal-body"), "Данные о команде успешно обновлены!"));
        button = driver.findElement(By.cssSelector("button.btn-close"));;
        button.click();

        driver.get(driver.getCurrentUrl());
        wait.until(textToBePresentInElement(By.cssSelector("h2[name-holder]"), "Team2"));
    }

    @Test(priority = 2)
    public void memberPageTest() {
        driver.get(URL);
        WebElement button = wait.until(visibilityOfElementLocated(By.cssSelector("button#Member-button")));
        button.click();

        WebElement link = wait.until(visibilityOfElementLocated(By.cssSelector("div.accordion-item a")));
        link.click();
        wait.until(stalenessOf(link));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Events-button")));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Teams-button")));
        wait.until(visibilityOfElementLocated(By.cssSelector("button#Member-button")));

        wait.until(textToBePresentInElement(By.cssSelector("h2[name-holder]"), "Sportsman1"));
        wait.until(textToBePresentInElement(By.cssSelector("div[age-holder]"), "возраст: 31"));
        wait.until(textToBePresentInElement(By.cssSelector("div[team-holder]"), "команда: отсутствует"));
        wait.until(textToBePresentInElement(By.cssSelector("div[role-holder]"), "роль: отсутствует"));

        button = wait.until(visibilityOfElementLocated(By.cssSelector("#Edit-member-button")));
        button.click();
        wait.until(visibilityOfElementLocated(By.cssSelector("#EditMemberForm")));

        driver.findElement(By.cssSelector("#memberNameInput")).sendKeys("Sportsman2");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#birthdayMemberInput').setAttribute('value', '1991-05-12')");
        button = driver.findElement(By.cssSelector("button[type=submit]"));
        button.click();
        wait.until(textToBePresentInElement(By.cssSelector("div.modal-body"), "Данные о спортсмене успешно обновлены!"));
        button = driver.findElement(By.cssSelector("button.btn-close"));;
        button.click();

        driver.get(driver.getCurrentUrl());
        wait.until(textToBePresentInElement(By.cssSelector("h2[name-holder]"), "Sportsman2"));
        wait.until(textToBePresentInElement(By.cssSelector("div[age-holder]"), "возраст: 30"));
        wait.until(textToBePresentInElement(By.cssSelector("div[team-holder]"), "команда: отсутствует"));
        wait.until(textToBePresentInElement(By.cssSelector("div[role-holder]"), "роль: отсутствует"));

    }

    @Test(priority = 3)
    public void deleteObjects() {
        driver.get(URL);

        WebElement button = wait.until(visibilityOfElementLocated(By.cssSelector("button[btn-deleter]")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#accordionElems")).getText(), "");
        button = wait.until(visibilityOfElementLocated(By.cssSelector("#Events-button")));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(not(visibilityOfElementLocated(By.cssSelector("div#accordionElems"))));

        button = driver.findElement(By.cssSelector("button#Teams-button"));
        button.click();
        wait.until(stalenessOf(button));
        button = wait.until(visibilityOfElementLocated(By.cssSelector("button[btn-deleter]")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#accordionElems")).getText(), "");
        button = wait.until(visibilityOfElementLocated(By.cssSelector("#Teams-button")));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(not(visibilityOfElementLocated(By.cssSelector("div#accordionElems"))));

        button = driver.findElement(By.cssSelector("button#Member-button"));
        button.click();
        wait.until(stalenessOf(button));
        button = wait.until(visibilityOfElementLocated(By.cssSelector("button[btn-deleter]")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#accordionElems")).getText(), "");
        button = wait.until(visibilityOfElementLocated(By.cssSelector("#Member-button")));
        button.click();
        wait.until(stalenessOf(button));
        wait.until(not(visibilityOfElementLocated(By.cssSelector("div#accordionElems"))));
    }

    @AfterClass
    public void finish() {
        driver.quit();
    }
}
