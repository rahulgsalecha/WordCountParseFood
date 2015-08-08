



import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.DynamicGraph.Status;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class RestaurantsURL {
	
	
    static WebDriver driver;
    static Wait<WebDriver> wait;

    public static void main(String[] args) throws IOException, InterruptedException {
    	
    	PrintStream ps = new PrintStream("//Users//rsalecha//Downloads//jate_test//All_Blr_restarant_URLs.txt");
        PrintStream orig = System.out;
        System.setOut(ps);

    	
        driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        int i = 1;
        String page;
        Integer PageCount; 
      		        								
        driver.get("https://www.zomato.com/bangalore/restaurants");	     
        
        WebElement alleles = driver.findElement(By.xpath("//div[contains(@class,'search-result-all')]"));
        List<WebElement> elements = alleles.findElements(By.xpath("//a[@class='result-title']"));
            for(WebElement element:elements) {
        	System.out.println(i + "," + element.getText() + "," + element.getAttribute("href"));
            i++; 
        }
   	
        try{
        while(driver.findElement(By.xpath("//li[contains(@class, 'active next')]")).isEnabled()) {
        
        WebElement Page = driver.findElement(By.xpath("//li[contains(@class, 'current')]"));
        page = Page.getText();
        PageCount = Integer.valueOf(page);
        
        /*
        System.out.println("=============");
        System.out.println("Page :" + PageCount);
        System.out.println("=============");
        */
        if(PageCount % 5 == 0) {
        	driver.findElement(By.xpath("//a[contains(@title,'Next 5 Pages')]")).click();
        } else {
        
        driver.findElement(By.xpath("//a[contains(@title,'Go to Page " + (PageCount+1) + "')]")).click();
        }
        driver.getCurrentUrl();
        
        driver.get(driver.getCurrentUrl());
        WebElement alleles1 = driver.findElement(By.xpath("//div[contains(@class,'search-result-all')]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='result-title']")));
        List<WebElement> elements1 = alleles1.findElements(By.xpath("//a[@class='result-title']"));
    
        for(WebElement element:elements1) {
            System.out.println(i + "," + element.getText() + "," + element.getAttribute("href"));
            i++; 
        }

        }
        } catch (NoSuchElementException e) {
        	System.out.println("NoSuchElementException ");
        } catch (TimeoutException e) {
        	System.out.println("TimeoutException");
        } catch (StaleElementReferenceException e) {
        	System.out.println("StaleElementReferenceException");
        }
        
        System.setOut(orig);
        ps.close();
    }
    
        
}



