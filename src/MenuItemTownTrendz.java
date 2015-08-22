

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.callback.FindCallback;
import org.testng.annotations.Test;

public class MenuItemTownTrendz {
	
	static WebDriver driver;
    static Wait<WebDriver> wait;
    static ArrayList<String> moversParsed = new ArrayList<String>();
    static int skip = 0;
    private static List<ParseObject>allObjects = new ArrayList<ParseObject>();
    static ArrayList<String> hotel_list = new ArrayList<String>();

    
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
    	
    	Parse.initialize("l8c3iZRen3hZZCld2J5Bf3HOfKIbEAlSOprEHOax", "mRJNszGCp4eEvzhBMK4U9S76q3IyuYUWtbFH7xJ0");

    	final ParseQuery<ParseObject> query = ParseQuery.getQuery("TownTrendzBLRRestURL");
    	query.limit(1000);
    	query.findInBackground(getAllObjects());

        driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        Collections.sort(moversParsed);
		for (int i = 0; i < hotel_list.size(); i++) {
			//for (int i = 0; i < 2; i++) {
			System.out.println(moversParsed.get(i)+"/popular-dishes");
			driver.get(moversParsed.get(i)+"/popular-dishes");	
        
			////*[@id='dish-acc']
			//*[@id="dish-acc"]/a[1]/li
       
        
        try{
        	if(driver.findElement(By.xpath("//*[@id='dish-acc']")) != null){
        		WebElement alleles = driver.findElement(By.xpath("//*[@id='dish-acc']"));
        		List<WebElement> elements = alleles.findElements(By.xpath("//a/li"));
        		
        		System.out.println("elements size:" + elements.size());
        		
                for(WebElement element:elements) {
                	ParseObject RestaurantReviews = new ParseObject("TownTrendMenu_1");
                    RestaurantReviews.put("hotelUrl", hotel_list.get(i));
                    //String title = StringUtils.substringBetween(element.getText(), "RATED", "Like");
                    RestaurantReviews.put("hotelPopDish", element.getText());
                    //RestaurantReviews.put("hotelReview",title);
                    RestaurantReviews.save();
                    
                }    
        	}
        } catch (TimeoutException e) {
        	System.out.println("pop dish doesn't exist: TimeoutException");
        } catch (StaleElementReferenceException e) {
        	System.out.println("pop dish doesn't exist: StaleElementReferenceException");
        } catch (NoSuchElementException e) {
        	System.out.println("pop dish doesn't exist :NoSuchElementException ");
        }
     } 
		driver.quit();	
    }
    
    public static void saveParsedMoverData(List<String> hotel_list){
		moversParsed.clear();
		for(int i=0;i<hotel_list.size();i++){
			moversParsed.add(i,hotel_list.get(i));
			
		} 
		
		//System.out.println("moversParsed List: " + moversParsed.size());
	}
    

    public static FindCallback<ParseObject> getAllObjects(){
    	
    	ParseQuery query = new ParseQuery("TownTrendzBLRRestURL");
        return (new FindCallback<ParseObject>() {

        	public void done(List<ParseObject> parseObject, ParseException e) {
        		
        		
				if (e == null && parseObject != null)
				{
					if(!(parseObject.isEmpty())) {
						int size = parseObject.size();
						int i=0;
						while (i < size) {
							hotel_list.add(parseObject.get(i).getString("rest_url"));
							i++;
						}
						
						saveParsedMoverData(hotel_list);
						
					}
					  allObjects.addAll(parseObject);
					  int limit =1000; 
					  if (parseObject.size() == limit){
	                        skip = skip + limit;
	                        ParseQuery query = new ParseQuery("TownTrendzBLRRestURL");
	                        query.skip(skip);
	                        query.limit(limit);
	                        query.findInBackground(getAllObjects());
				}
				
			}
        

}
        });
    }
}
