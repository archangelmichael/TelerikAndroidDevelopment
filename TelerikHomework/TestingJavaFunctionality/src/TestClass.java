import java.io.File;
import java.util.Scanner;


public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String output = String.format("Product: %s, Category: %s", "Name", "Category");
		System.out.print(output);
		
		// Get input from console
		Scanner scanner = new Scanner(System.in);
		String input;
		input = scanner.nextLine();
		System.out.println(input);
		
		// Traverse directory recursively
		String path = "../TestingJavaFunctionality/src";
		System.out.println("Path: " + path);
		File f = new File(path);        
		File file[] = f.listFiles();
		for (int i=0; i < file.length; i++)
		{
			System.out.println ( "FileName:" + file[i].getName());
		}
		
		traverse(new File("../MediaPlayer/res/raw/"));
		
	}
	
	public static void traverse (File dir) {
	    if (dir.exists()) {
	        File[] files = dir.listFiles();
	        for (int i = 0; i < files.length; ++i) {
	            File file = files[i];
	            if (file.isDirectory()) {
	                traverse(file);
	            } else {
	            	System.out.println( "FileName:" + file.getName());
	            }
	        }
	    }
	} 
	
	private static int CountWords (String in) {
		String trim = in.trim();
		if (trim.isEmpty()) return 0;
		return trim.split("\\s+").length; //separate string around spaces
	}

}
