



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

public class RestaurantsURLTownTrendz {
	
	
    static WebDriver driver;
    static Wait<WebDriver> wait;

    public static void main(String[] args) throws IOException, InterruptedException {
    	
    	PrintStream ps = new PrintStream("//Users//rsalecha//Downloads//jate_test//Blr_restarant_URLs_TownTrendz.txt");
        PrintStream orig = System.out;
        System.setOut(ps);

        driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String page;
        Integer PageCount; 
        
        for (int i = 1; i <= 219; i++){
      		        								
        driver.get("http://www.towntrendz.com/bangalore/restaurants/page/"+ i);	  

        WebElement alleles = driver.findElement(By.xpath("//*[@id='restaurants_ul_list']"));
        List<WebElement> elements = alleles.findElements(By.xpath("//li/div[1]/div[1]/p[1]/a"));
            for(WebElement element:elements) {
        	System.out.println(element.getText() + "," + element.getAttribute("href"));
        }
        
        }
   	/*
        try{
        
        while(driver.findElement(By.xpath("//*[@id='restaurants_loading_container']/div[4]/ul/li/a/i")).isEnabled()) {
        	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='restaurants_loading_container']/div[4]/ul/li/a/i")));
        	driver.findElement(By.xpath("//*[@id='restaurants_loading_container']/div[4]/ul/li/a/i")).click();
        
        WebElement alleles1 = driver.findElement(By.xpath("//*[@id='restaurants_ul_list']"));
        List<WebElement> elements1 = alleles1.findElements(By.xpath("//li/div[1]/div[1]/p[1]/a"));
            for(WebElement element:elements1) {
        	System.out.println(element.getText() + "," + element.getAttribute("href"));
        }
        }
        } catch (NoSuchElementException e) {
        	System.out.println("NoSuchElementException ");
        } catch (TimeoutException e) {
        	System.out.println("TimeoutException");
        } catch (StaleElementReferenceException e) {
        	System.out.println("StaleElementReferenceException");
        }
     */   
        System.setOut(orig);
        ps.close();
    }
    
        
}



