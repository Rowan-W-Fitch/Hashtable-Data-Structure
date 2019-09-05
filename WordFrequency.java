import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class WordFrequency {
	
	public static String removeStops(String str, ArrayList<String> list) {
		for(String word: list) {
			if(str.contains(word)) {
				str = str.replaceAll(word, " ");
			}
		}
		str = str.replaceAll("\\.", "");
		str = str.replaceAll(",", "");
		return str;
	}
	
	
	public static void main(String[] args) throws IOException{
		try {
			//read in stopwords
			File stop = new File("stopwords.txt");
			ArrayList<String> stoppers = new ArrayList<String>();
			Scanner scan = new Scanner(stop);
			while(scan.hasNextLine()) {
				String word = " " +scan.nextLine()+ " ";
				stoppers.add(word.toLowerCase());
			}
			scan.close();
			
			//read in file
			File file = new File(args[0]);
			Scanner scanF = new Scanner(file);
			String wordsF = "";
			while(scanF.hasNextLine()) {
				wordsF = wordsF + scanF.nextLine().toLowerCase();
			}
			scanF.close();
			
			//removing all stop words and punctuation
			String wordsF2 = removeStops(wordsF, stoppers);
			//placing only key words into array of words
			SCHashTable table = new SCHashTable();
			String[] WordArray = wordsF2.split(" ");
			//hashing words
			for(String word:WordArray) {
				if(table.containsKey(word)) {
					int val = table.get(word);
					table.put(word, val +1);
				}
				else{
					table.put(word, 1);
				}
			}
			
			System.out.println(table.toString());
			
			//determining topic
			ArrayList<HashNode> topics = new ArrayList<HashNode>();
			for(int i=0; i< table.hash.length; i++) {
				int maxim = 1;
				//int maximum = 1;
				HashNode temp = table.hash[i];
				while(temp!=null) {
					maxim = Math.max(maxim, temp.value);
					if(temp.value == maxim && temp.value > 1) {
						topics.add(temp);
					}
					temp = temp.next;
				}
				
			}
			//getting max of all maximums
			int maxim = 1;
			for(HashNode node: topics) {
				maxim = Math.max(maxim, node.value);
			}
			//printing topic(s)
			String mainIdea = "Topic(s):";
			for(HashNode node: topics) {
				if(node.value == maxim) {
					mainIdea = mainIdea + System.lineSeparator()+ node.key + " "+ "(" + node.value+ " " + "occurances"+")";
				}
			}
			
			System.out.println(mainIdea);
			
		}
		
		catch(IndexOutOfBoundsException e){
			System.out.println("file not selected");
		}	
		catch(FileNotFoundException f) {
			System.out.println("file not found");
		}
	}
	
	//go thru string
	//if contains any values in stopper -> delete
	//delete all punctuation
	//put all strings in hashtable
	//return string(s) w/ highest frequency

}
