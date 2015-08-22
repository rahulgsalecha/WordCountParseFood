import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.callback.FindCallback;


public class AddressRestaurantBLR {
	
	static WebDriver driver;
	static Wait<WebDriver> wait;
    static ArrayList<String> moversParsed = new ArrayList<String>();
    static int skip = 0;
    private static List<ParseObject>allObjects = new ArrayList<ParseObject>();
    static ArrayList<String> hotel_list = new ArrayList<String>();
    static WebElement RestoName1;
    static WebElement RestoPhone1;
    static WebElement RestoAddress1;
    static WebElement RestoCuisine1;

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
    	/*
    	PrintStream ps = new PrintStream("//Users//rsalecha//Downloads//jate_test//All_ZomAddr.txt");
        PrintStream orig = System.out;
        System.setOut(ps);
    	*/
        Parse.initialize("l8c3iZRen3hZZCld2J5Bf3HOfKIbEAlSOprEHOax", "mRJNszGCp4eEvzhBMK4U9S76q3IyuYUWtbFH7xJ0");    

    	final ParseQuery<ParseObject> query = ParseQuery.getQuery("All_Blr_URL");
    	query.limit(1000);
    	query.findInBackground(getAllObjects());
       
    	Thread.sleep(6000);
    	
    	//driver = new FirefoxDriver();
    	
    	System.setProperty("webdriver.chrome.driver", "/Users/rsalecha/Downloads/chromedriver-1");
    	driver = new ChromeDriver();
    	
    	WebDriverWait wait = new WebDriverWait(driver, 5);
    	
		for (int i = 0; i < hotel_list.size(); i++) {
			System.out.println(moversParsed.get(i));
			driver.get(moversParsed.get(i));	
			
			ParseObject RestaurantReviews = new ParseObject("ZomBlr123");
		
		try{
			if(driver.findElement(By.xpath("//html/body/div[3]/div[4]/div/div[2]/div[2]/div/div/div[1]/div/div[3]/div/h1/a/span")) != null){
				//wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//html/body/div[3]/div[4]/div/div[2]/div[2]/div/div/div[1]/div/div[3]/div/h1/a/span")));
				RestoName1 = driver.findElement(By.xpath("//html/body/div[3]/div[4]/div/div[2]/div[2]/div/div/div[1]/div/div[3]/div/h1/a/span"));
				RestaurantReviews.put("hotel_name", RestoName1.getText());
				RestaurantReviews.save();
			}
			if(driver.findElement(By.xpath("//*[@id='phoneNoString']/div/span/span[1]/span")) != null) {
				//wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='phoneNoString']/div/span/span[1]/span")));
        		RestoPhone1 = driver.findElement(By.xpath("//*[@id='phoneNoString']/div/span/span[1]/span"));
        		RestaurantReviews.put("hotel_phone", RestoPhone1.getText());
        		RestaurantReviews.save();
        	}
			if(driver.findElement(By.xpath("//*[@id='mainframe']/div[1]/div/div[1]/div[1]/div[2]/div/div[4]")) != null) {
				//wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='mainframe']/div[1]/div/div[1]/div[1]/div[2]/div/div[4]")));
        		RestoAddress1 = driver.findElement(By.xpath("//*[@id='mainframe']/div[1]/div/div[1]/div[1]/div[2]/div/div[4]"));
        		
        		RestaurantReviews.put("hotel_address", RestoAddress1.getText());
        		RestaurantReviews.save();
        	}
			/*
			if(driver.findElement(By.xpath("//*[@id='mainframe']/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[1]/div/div[2]")) != null) {
        		RestoCuisine1 = driver.findElement(By.xpath("//*[@id='mainframe']/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[1]/div/div[2]"));
        			//System.out.println("Cuisine: "+ RestoCuisine.getText());
        			RestaurantReviews.put("hotel_cuisine", RestoCuisine1.getText());
        			//RestaurantReviews.save();
        	}
        	*/
		} catch (NoSuchElementException e) {
	        	//System.out.println("Reviews tab button doesn't exist :NoSuchElementException ");
	    } catch (TimeoutException e) {
	        	//System.out.println("Reviews tab button doesn't exist: TimeoutException");
	    } catch (StaleElementReferenceException e) {
	        	//System.out.println("Reviews tab button doesn't exist: StaleElementReferenceException");
	    }		
 
		RestaurantReviews.put("hotelUrl", moversParsed.get(i));
        
        //System.out.println("("+ RestoName1.getText() + "),(" + RestoPhone1.getText() + "),(" + RestoAddress1.getText()+ "),(" + hotel_list.get(i)+")");
        RestaurantReviews.save();
        //driver.navigate().refresh();
   
	} 
		driver.quit(); 
		/*
		System.setOut(orig);
	        ps.close();
	      */  
}
    
   
    public static void saveParsedMoverData(List<String> hotel_list){
		moversParsed.clear();
		for(int i=0;i<hotel_list.size();i++){
			moversParsed.add(i,hotel_list.get(i));
			
		} 
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
