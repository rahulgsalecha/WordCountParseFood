

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.callback.FindCallback;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
 
public class ZomAddressHtmlUnit {
 
    final static WebClient webClient;

    static ArrayList<String> moversParsed = new ArrayList<String>();
    static int skip = 0;
    private static List<ParseObject>allObjects = new ArrayList<ParseObject>();
    static ArrayList<String> hotel_list = new ArrayList<String>();
    static HtmlSpan RestoName1;
    static HtmlSpan RestoPhone1;
    static HtmlDivision RestoAddress1;
    static HtmlDivision RestoCuisine1;
    static HtmlPage currentPage;
 
    static {
        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setCssEnabled(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(true);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.waitForBackgroundJavaScriptStartingBefore(10000);
        webClient.setJavaScriptTimeout(2147483647);
        webClient.setCssErrorHandler(new SilentCssErrorHandler());
        webClient.waitForBackgroundJavaScript(5000);
        webClient.setRefreshHandler(new ThreadedRefreshHandler());
        webClient.getCookieManager().setCookiesEnabled(true);
    }
 
    public static void main(String[] args) throws Exception {
    	
    	PrintStream ps = new PrintStream("//Users//rsalecha//Downloads//jate_test//All_Blr_restarant_zom_address3.txt");
        PrintStream orig = System.out;
        System.setOut(ps);
        
    	Parse.initialize("l8c3iZRen3hZZCld2J5Bf3HOfKIbEAlSOprEHOax", "mRJNszGCp4eEvzhBMK4U9S76q3IyuYUWtbFH7xJ0");

    	final ParseQuery<ParseObject> query = ParseQuery.getQuery("All_Blr_URL");
    	query.limit(1000);
    	query.findInBackground(getAllObjects());
    	
    	Thread.sleep(10000);
    	
    	Collections.sort(moversParsed); 	
        
		for (int i = 0; i < hotel_list.size(); i++) {
			//System.out.println(moversParsed.get(i));
			currentPage = webClient.getPage(moversParsed.get(i));	
			
			Thread.sleep(5000);
			//ParseObject RestaurantReviews = new ParseObject("ZomBlrAddr12356");
			
			//RestaurantReviews.put("hotelUrl", moversParsed.get(i));
			//RestaurantReviews.save();
		try{
			
			if(currentPage.getFirstByXPath("//html/body/div[3]/div[4]/div/div[2]/div[2]/div/div/div[1]/div/div[3]/div/h1/a/span") !=null){
				RestoName1 = currentPage.getFirstByXPath("//html/body/div[3]/div[4]/div/div[2]/div[2]/div/div/div[1]/div/div[3]/div/h1/a/span");
				//System.out.println(RestoName.getText());
				//RestaurantReviews.put("hotel_name", RestoName1.asText());
				//RestaurantReviews.save();
			}
			if(currentPage.getFirstByXPath("//*[@id='phoneNoString']/div/span/span[1]/span") != null) {
        		RestoPhone1 = currentPage.getFirstByXPath("//*[@id='phoneNoString']/div/span/span[1]/span");
        			//System.out.println(RestoPhone.getText());
        			//RestaurantReviews.put("hotel_phone", RestoPhone1.asText());
        			//RestaurantReviews.save();
        	}
			if(currentPage.getFirstByXPath("//*[@id='mainframe']/div[1]/div/div[1]/div[1]/div[2]/div/div[4]") != null) {
        		RestoAddress1 = currentPage.getFirstByXPath("//*[@id='mainframe']/div[1]/div/div[1]/div[1]/div[2]/div/div[4]");
        			//System.out.println(RestoAddress.getText());
        			//RestaurantReviews.put("hotel_address", RestoAddress1.asText());
        			//RestaurantReviews.save();
        	}
			/*
			if(currentPage.getFirstByXPath("//*[@id='mainframe']/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[1]/div/div[2]") != null) {
        		RestoCuisine1 = currentPage.getFirstByXPath("//*[@id='mainframe']/div[1]/div/div[1]/div[2]/div[1]/div[2]/div[1]/div/div[2]");
        			//System.out.println("Cuisine: "+ RestoCuisine.getText());
        			RestaurantReviews.put("hotel_cuisine", RestoCuisine1.asText());
        			RestaurantReviews.save();
        	}
        	*/
		} catch (NoSuchElementException e) {
        	//System.out.println("Reviews tab button doesn't exist :NoSuchElementException ");
    } catch (TimeoutException e) {
        	//System.out.println("Reviews tab button doesn't exist: TimeoutException");
    } catch (StaleElementReferenceException e) {
        	//System.out.println("Reviews tab button doesn't exist: StaleElementReferenceException");
    }	
		
    
    System.out.println("("+RestoName1.asText() + "),(" + RestoPhone1.asText() + "),(" + RestoAddress1.asText()+ ")," + hotel_list.get(i));
    //RestaurantReviews.save();
		}
       
}
    public static void saveParsedMoverData(List<String> hotel_list){
		moversParsed.clear();
		for(int i=0;i<hotel_list.size();i++){
			moversParsed.add(i,hotel_list.get(i));
			
		} 
		System.out.println(moversParsed.size());
		
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