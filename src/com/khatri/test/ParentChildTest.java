package com.khatri.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ParentChildTest {

	public static void main(String[] args) throws IOException {

		String inputHTMLTags = new String(Files.readAllBytes(Paths.get("src/com/khatri/resources/htmlFile.txt")));
		System.out.println(inputHTMLTags);

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter 1 to enter the line number or 2 to enter the tag name:");
		int userInput1 = sc.nextInt();

		switch (userInput1) {
		case 1:
			System.out.print("Enter the line number:");
			int lineNumber = sc.nextInt();
			findchildUsingLineNumber(inputHTMLTags, lineNumber);
			break;
		case 2:
			System.out.print("Enter the tag name:");
			sc.nextLine();
			String tagName = sc.nextLine();
			findchildUsingTagName(inputHTMLTags, tagName);
			break;
		default:
			System.out.println("Invalid Option at Switch");
		}
		sc.close();
	}

	public static void findchildUsingLineNumber(String inputHTMLTags, int lineNumber) {

		ArrayList<String> child = new ArrayList();
		String[] parts = inputHTMLTags.split(System.lineSeparator());
		String start = parts[lineNumber - 1].trim();
		String end = "</" + start.substring(1);

		for (int i = lineNumber; i < parts.length && !parts[i].trim().equals(end); i++) {
			if (parts[i].trim().startsWith("<") && !parts[i].trim().startsWith("</")) {
				String val = parts[i].trim().replace("</", "").replace("<", "").replace("/>", "").replace(">", "");

				if (i < parts.length && parts[i].trim().endsWith(">") && !parts[i].trim().endsWith("/>")) {
					String start1 = parts[i].trim();
					String end1 = "</" + start1.substring(1);
					while (i < parts.length && !parts[i].trim().equals(end1)) {
						i++;
					}
				}
				if (!child.contains(val)) {
					child.add(val);
				}
			}
		}

		System.out.print("Output: " + child);
	}

	public static void findchildUsingTagName(String inputHTMLTags, String tagName) {

		ArrayList<String> child = new ArrayList();

		String[] parts = inputHTMLTags.split(System.lineSeparator());
		int lineNumber = 0;

		for (int i = 0; i < parts.length; i++) {
			if (("<" + tagName + ">").equals(parts[i].trim())) {
				lineNumber = i + 1;

				break;
			}
          
		}
		
		String start = parts[lineNumber - 1].trim();
		String end = "</" + start.substring(1);
		
		for (int i = lineNumber; i < parts.length && !parts[i].trim().equals(end); i++) {
			if (parts[i].trim().startsWith("<") && !parts[i].trim().startsWith("</")) {
				String val = parts[i].trim().replace("</", "").replace("<", "").replace("/>", "").replace(">", "");

				if (i < parts.length && parts[i].trim().endsWith(">") && !parts[i].trim().endsWith("/>")) {
					String start1 = parts[i].trim();
					String end1 = "</" + start1.substring(1);
					while (i < parts.length && !parts[i].trim().equals(end1)) {
						i++;
					}
				}
				if (!child.contains(val)) {
					child.add(val);
				}
			}
		}

		System.out.print("Output: " + child);
	}

}
