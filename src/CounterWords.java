import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class CounterWords {

	private static final boolean FALSE = false;
	static Map<Integer, String> hm = new HashMap<Integer, String>();

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("//Users//rsalecha//Downloads//jate_test//BoondockBistro_Menu.txt"));
		
		List<String> list = new ArrayList<String>();
		
		
		while (s.hasNext()){
		    list.add(s.useDelimiter(",").next());
		}
		s.close();
		
		//int count = countWord("Tamater Shorba","//Users//rsalecha//Downloads//jate_test//jalpaan_text.txt");
		//System.out.println(" Count:" + count);
		
		Iterator<String> MenuListIterator = list.iterator();
		while (MenuListIterator.hasNext()) {
			int count = countWord(MenuListIterator.next(),"//Users//rsalecha//Downloads//jate_test//BoondockBistro_All_Reviews.txt");
		}
		
		Map<Integer, String> newMap = new TreeMap(Collections.reverseOrder());
		newMap.putAll(hm);
		
		for (Map.Entry entry : newMap.entrySet()) {
		    System.out.println(entry.getKey() + ", " + entry.getValue());
		}

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
		//System.out.println(word + "," + count);
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

