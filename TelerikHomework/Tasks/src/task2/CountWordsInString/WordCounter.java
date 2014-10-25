package task2.CountWordsInString;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {
	private static HashMap<String, Integer> words = new HashMap<String, Integer>();
	
	private final static String inputPath = "..\\Tasks\\src\\task2\\CountWordsInString\\input.txt";
	private final static String outputPath = "..\\Tasks\\src\\task2\\CountWordsInString\\output.txt";

	private static BufferedReader reader;
	private static BufferedWriter writer;
	private static int totalWordsCount = 0;
	
	public static void main(String[] args) throws IOException {
		readInput();
		writeOutput();
	}
	
	private static int countWords(String s){

	    int wordCount = 0;
	    StringBuilder word = new StringBuilder();

	    boolean isWord = false;
	    int endOfLine = s.length() - 1;

	    for (int i = 0; i < s.length(); i++) {
	    	char symbol = s.charAt(i);
	        // if the char is a letter, word = true.
	        if (Character.isLetter(symbol) && i != endOfLine) {
	        	word.append(symbol);
	        	isWord = true;
	        } else if (!Character.isLetter(symbol) && isWord) {
	            String newWord = word.toString();
	            if (!words.containsKey(newWord)) {
					words.put(newWord, 1);
				} else {
					words.put(newWord, words.get(newWord) + 1);
				}
	            
	            word = new StringBuilder();
	        	wordCount++;
	            isWord = false;
	        } else if (Character.isLetter(symbol) && i == endOfLine) {
	            wordCount++;
	        }
	    }
	    
	    return wordCount;
	}
	
	private static void readInput() throws IOException {		
		try {
			String sCurrentLine; 
			reader = new BufferedReader(new FileReader(inputPath));

			while ((sCurrentLine = reader.readLine()) != null) {
				totalWordsCount += countWords(sCurrentLine);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			reader.close();
		}
	}
	
	private static void writeOutput() throws IOException {
	    try {
	    	writer = new BufferedWriter(new FileWriter(outputPath));
	        String outputLine;
        	for (Map.Entry<String, Integer> entry : words.entrySet()) {
        		int appearance = entry.getValue();
        		
        		outputLine =  String.format(" %s  =>  %d %s", entry.getKey(), entry.getValue(), appearance == 1 ? "time" : "times");
        		writer.write(outputLine);
            	writer.newLine();
    		}
        	
	        System.out.print("Write Successful");
	    } catch(IOException error) {
	        System.out.println(error.getMessage());
	    } finally {
	        writer.close();
	    }
	}
}
