package task1.ReverseString;

import java.util.Scanner;

public class StringReverser {

	public static void main(String[] args) {
		String input;
		Scanner scanner = new Scanner(System.in);
		input = scanner.nextLine();
		String reverse = new StringBuffer(input).reverse().toString();
		System.out.println(reverse);

	}

}
