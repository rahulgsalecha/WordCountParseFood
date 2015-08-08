
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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



public class E2E {
	
	
    static WebDriver driver;
    static Wait<WebDriver> wait;
    static Map<Integer, String> hm = new HashMap<Integer, String>();

    public static void main(String[] args) throws IOException, InterruptedException {
    	
        driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        
        PrintStream ps = new PrintStream("//Users//rsalecha//Downloads//jate_test//Jalpaan_All_Reviews.txt");
        PrintStream orig = System.out;
        System.setOut(ps);
        
        PrintStream final_results = new PrintStream("//Users//rsalecha//Downloads//jate_test//Jalpaan_Final_Results.txt");
        PrintStream results_ps = System.out;
        System.setOut(final_results);
       
        
        List<String> hotel_list = new ArrayList<String>();
        hotel_list.add("https://www.zomato.com/bangalore/jalpaan-rajajinagar");
        /*
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
		for (int i = 0; i < hotel_list.size(); i++) {
			System.out.println(hotel_list.get(i));
			driver.get(hotel_list.get(i));	     		        								
        									        
        //Code to click on Reviews tab
        driver.findElement(By.xpath("//a[contains(@class,'default-section-title everyone empty')]")).click();
        
        //currentURL = driver.getCurrentUrl();
        
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
        System.out.println("alleles:"+alleles.getText().length());
        List<WebElement> elements = alleles.findElements(By.xpath("//div[@itemprop='review']"));
    
        System.out.println(elements.size());
        for(WebElement element:elements) {
        	System.out.println("========================================");
            System.out.println(element.getText());
        }
        
        System.setOut(orig);
        ps.close();
        
    } 
		
		Scanner s = new Scanner(new File("//Users//rsalecha//Downloads//jate_test//Jalpaan_Menu.txt"));
		
		List<String> list = new ArrayList<String>();
		
		while (s.hasNext()){
		    list.add(s.useDelimiter(",").next());
		}
		s.close();
		
		//int count = countWord("Tamater Shorba","//Users//rsalecha//Downloads//jate_test//jalpaan_text.txt");
		//System.out.println(" Count:" + count);
		
		Iterator<String> MenuListIterator = list.iterator();
		while (MenuListIterator.hasNext()) {
			int count = countWord(MenuListIterator.next(),"//Users//rsalecha//Downloads//jate_test//Jalpaan_All_Reviews.txt");
		}
		
		Map<Integer, String> newMap = new TreeMap(Collections.reverseOrder());
		newMap.putAll(hm);
		
		for (Map.Entry entry : newMap.entrySet()) {
			System.out.println("Final Results : Count, Menu Item");
			System.out.println("=================================================");
		    System.out.println(entry.getKey() + ", " + entry.getValue());
		    System.out.println("=================================================");
		}
		
		System.setOut(results_ps);
        final_results.close();
		
        
    }
    
    
    public static int countWord(String word, String file_name) throws FileNotFoundException {
		int count = 0;
		Scanner scanner = new Scanner(new File(file_name));
		boolean found = false;
		
		
		while (scanner.hasNextLine()) {
		    String nextToken = scanner.nextLine();
		   
		    if(found){
		    	found = false;
		    }
		    if (containsIgnoreCase(nextToken,word))
		    {
		    	found = true;
		    	count++;
		    }
		    
		    
		}
		if(count > 0){
			hm.put(count, word);
		}
		return count;
	}
	
	public static boolean containsIgnoreCase(final String str, final String searchStr) {
	    if (str == null || searchStr == null) {
	        return false;
	    }
	    final int len = searchStr.length();
	    final int max = str.length() - len;
	    for (int i = 0; i <= max; i++) {
	        if (str.regionMatches(true, i, searchStr, 0, len)) {
	            return true;
	        }
	    }
	    return false;
	}
	
        
}

