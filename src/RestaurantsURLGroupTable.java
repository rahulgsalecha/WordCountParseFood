



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

public class RestaurantsURLGroupTable {
	
	
    static WebDriver driver;
    static Wait<WebDriver> wait;

    public static void main(String[] args) throws IOException, InterruptedException {
    	
    	PrintStream ps = new PrintStream("//Users//rsalecha//Downloads//jate_test//Blr_restarant_URLs_Grouptable.txt");
        PrintStream orig = System.out;
        System.setOut(ps);

        driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String page;
        Integer PageCount; 
        
        //https://www.grouptable.com/bangalore/restaurants?page=1
        for (int i = 1; i <= 144; i++){
      		        								
        driver.get("https://www.grouptable.com/bangalore/restaurants?page="+ i);	  

        WebElement alleles = driver.findElement(By.xpath("//*[@id='restaurants']"));
        List<WebElement> elements = alleles.findElements(By.xpath("//div/div[1]/h2/a"));
            for(WebElement element:elements) {
        	System.out.println(element.getText() + "," + element.getAttribute("href"));
        }    
        }
        
        //https://www.grouptable.com/bangalore/restaurants?page=1&restaurant_type=Cafe
        for (int i = 1; i <= 17; i++){
      		        								
        driver.get("https://www.grouptable.com/bangalore/restaurants?page="+ i+"&restaurant_type=Cafe");	  

        WebElement alleles = driver.findElement(By.xpath("//*[@id='restaurants']"));
        List<WebElement> elements = alleles.findElements(By.xpath("//div/div[1]/h2/a"));
            for(WebElement element:elements) {
        	System.out.println(element.getText() + "," + element.getAttribute("href"));
        }    
        }
        
        //https://www.grouptable.com/bangalore/restaurants?page=2&restaurant_type=Lounge+Bar
        for (int i = 1; i <= 18; i++){
      		        								
        driver.get("https://www.grouptable.com/bangalore/restaurants?page="+ i+"&restaurant_type=Lounge+Bar");	  

        WebElement alleles = driver.findElement(By.xpath("//*[@id='restaurants']"));
        List<WebElement> elements = alleles.findElements(By.xpath("//div/div[1]/h2/a"));
            for(WebElement element:elements) {
        	System.out.println(element.getText() + "," + element.getAttribute("href"));
        }    
        }
        
      //https://www.grouptable.com/bangalore/restaurants?page=2&restaurant_type=Pub 
        for (int i = 1; i <= 8; i++){
      		        								
        driver.get("https://www.grouptable.com/bangalore/restaurants?page="+ i+"restaurant_type=Pub");	  

        WebElement alleles = driver.findElement(By.xpath("//*[@id='restaurants']"));
        List<WebElement> elements = alleles.findElements(By.xpath("//div/div[1]/h2/a"));
            for(WebElement element:elements) {
        	System.out.println(element.getText() + "," + element.getAttribute("href"));
        }    
        }
        
        //https://www.grouptable.com/bangalore/restaurants?restaurant_type=Night+Club
        //for (int i = 1; i <= 18; i++){
      		        								
        driver.get("https://www.grouptable.com/bangalore/restaurants?restaurant_type=Night+Club");	  

        WebElement alleles = driver.findElement(By.xpath("//*[@id='restaurants']"));
        List<WebElement> elements = alleles.findElements(By.xpath("//div/div[1]/h2/a"));
            for(WebElement element:elements) {
        	System.out.println(element.getText() + "," + element.getAttribute("href"));
        }    
        //}
            
        
   
        System.setOut(orig);
        ps.close();
    }
    
        
}



