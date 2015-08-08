
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
import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.callback.FindCallback;
import org.testng.internal.DynamicGraph.Status;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SeleniumParse {
	
	
    static WebDriver driver;
    static Wait<WebDriver> wait;
    static ArrayList<String> moversParsed = new ArrayList<String>();
    static int skip = 0;
    private static List<ParseObject>allObjects = new ArrayList<ParseObject>();
    static List<String> hotel_list = new ArrayList<String>();

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
    	
    	Parse.initialize("x3w9shkNvQrxPvvDUK11iHG3JBmyEPMnNsbvoDCa", "tlN0OAmxpCOzKVh3K0liNdnluhL3hGMybAG6zrz6");

    	final ParseQuery<ParseObject> query = ParseQuery.getQuery("All_Blr_URL");
    	query.limit(1000);
    	query.findInBackground(getAllObjects());
        
        /*
        hotel_list.add("https://www.zomato.com/bangalore/jalpaan-rajajinagar");
        hotel_list.add("https://www.zomato.com/bangalore/jalpaan-jayanagar");
        hotel_list.add("https://www.zomato.com/chennai/jalpaan-adyar");
        hotel_list.add("https://www.zomato.com/hyderabad/jalpaan-somajiguda");
        hotel_list.add("https://www.zomato.com/chennai/jalpaan-nungambakkam");
        hotel_list.add("https://www.zomato.com/mumbai/jalpaan-borivali-west");
        hotel_list.add("https://www.zomato.com/indore/jalpaan-yn-road");
        hotel_list.add("https://www.zomato.com/hyderabad/jalpaan-pg-road-secunderabad");
        hotel_list.add("https://www.zomato.com/mysore/jalpaan-express-ittige-gudu");
        hotel_list.add("https://www.zomato.com/mysore/jalpaan-express-chamrajpura");
        */
        

        driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        
		//for (int i = 0; i < hotel_list.size(); i++) {
        for (int i = 1; i < 2; i++) {
			System.out.println(moversParsed.get(i));
			driver.get(moversParsed.get(i));	     		        								
        
        //Code to click on Reviews tab
        driver.findElement(By.xpath("//a[contains(@class,'default-section-title everyone empty')]")).click();
        
        Thread.sleep(500);
        
        //Code to click load-more button for all Reviews
        if(driver.findElement(By.xpath("//div[@class = 'load-more']")) != null) {
        	try{
        		while(driver.findElement(By.xpath("//div[@class = 'load-more']")) != null){
        			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class = 'load-more']")));
        			driver.findElement(By.xpath("//div[@class = 'load-more']")).click();
        			}
        		} catch (NoSuchElementException e) {
        	System.out.println("load-more button doesn't exist :NoSuchElementException ");
        } catch (TimeoutException e) {
        	System.out.println("load-more button doesn't exist: TimeoutException");
        } catch (StaleElementReferenceException e) {
        	System.out.println("load-more button doesn't exist: StaleElementReferenceException");
        }
        } else {
        	System.out.println("load-more is null");
        }
        Thread.sleep(500);
        
        WebElement alleles = driver.findElement(By.xpath("//div[contains(@class,'zs-following-list pbot')]"));
        List<WebElement> elements = alleles.findElements(By.xpath("//div[@itemprop='review']"));
    
        System.out.println("elements size:" + elements.size());
        for(WebElement element:elements) {
        	ParseObject RestaurantReviews = new ParseObject("RestaurantReviews");
            RestaurantReviews.put("hotelUrl", hotel_list.get(i));
            RestaurantReviews.put("hotelReview", element.getText());
            RestaurantReviews.save();
        }
        
        
    } 
    }
    
    public static void saveParsedMoverData(List<String> hotel_list){
		moversParsed.clear();
		for(int i=0;i<hotel_list.size();i++){
			moversParsed.add(i,hotel_list.get(i));
			
		} 
		
		//System.out.println("moversParsed List: " + moversParsed.size());
	}
    

    public static FindCallback<ParseObject> getAllObjects(){
    	
    	ParseQuery query = new ParseQuery("All_Blr_URL");
        return (new FindCallback<ParseObject>() {

        	public void done(List<ParseObject> parseObject, ParseException e) {
        		
        		
				if (e == null && parseObject != null)
				{
					if(!(parseObject.isEmpty())) {
						int size = parseObject.size();
						int i=0;
						while (i < size) {
							hotel_list.add(parseObject.get(i).getString("zomatoHotelUrl"));
							i++;
						}
						
						saveParsedMoverData(hotel_list);
						
					}
					  allObjects.addAll(parseObject);
					  int limit =1000; 
					  if (parseObject.size() == limit){
	                        skip = skip + limit;
	                        ParseQuery query = new ParseQuery("All_Blr_URL");
	                        query.skip(skip);
	                        query.limit(limit);
	                        query.findInBackground(getAllObjects());
				}
				
			}
        

}
        });
    }
}

