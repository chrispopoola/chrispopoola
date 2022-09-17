/* SELF ASSESSMENT 

1. readDictionary
- I have the correct method definition [Mark out of 5:	5]
- Comment: Yes
- My method reads the words from the "words.txt" file. [Mark out of 5:	5]
- Comment: Yes
- It returns the contents from "words.txt" in a String array or an ArrayList. [Mark out of 5:	5]
- Comment: Yes

2. readWordList
- I have the correct method definition [Mark out of 5:	5]
- Comment: Yes
- My method reads the words provided (which are separated by commas, saves them to an array or ArrayList of String references and returns it. [Mark out of 5:	5]
- Comment: Yes

3. isUniqueList
- I have the correct method definition [Mark out of 5:	5]
- Comment: Yes
- My method compares each word in the array with the rest of the words in the list. [Mark out of 5:	5]
- Comment: Yes
- Exits the loop when a non-unique word is found. [Mark out of 5:	5]
- Comment: Yes
- Returns true is all the words are unique and false otherwise. [Mark out of 5:	5]
- Comment: Yes

4. isEnglishWord
- I have the correct method definition [Mark out of 5:	5]
- Comment: Yes
- My method uses the binarySearch method in Arrays library class. [Mark out of 3:	3]
- Comment: Yes
- Returns true if the binarySearch method return a value >= 0, otherwise false is returned. [Mark out of 2:	2]
- Comment: Yes

5. isDifferentByOne
- I have the correct method definition [Mark out of 5:	5]
- Comment: Yes
- My method loops through the length of a words comparing characters at the same position in both words searching for one difference. [Mark out of 10:	10]
- Comment: Yes

6. isWordChain
- I have the correct method definition [Mark out of 5:	5]
- Comment: Yes
- My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message [Mark out of 10:	10]
- Comment: Yes

7. main
- Reads all the words from file words.txt into an array or an ArrayList using the any of teh Java.IO classes covered in lectures [Mark out of 10:	10]
- Comment: Yes
- Asks the user for input and calls isWordChain [Mark out of 5:	5]
- Comment: Yes

 Total Mark out of 100 (Add all the previous marks):	100
*/
package introToProgramming;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LCWordLinksPuzzleGame 
{
	Scanner Input = new Scanner (System.in);
	public static String[] DICTIONARY = new String [658964];

	public static void main (String [] args) throws IOException
	{
		File textFile = new File ("/Users/chrispopoola/Documents/words.txt");
		ArrayList <String> dictionary = readDictionary(textFile);
		moveArrayListIntoArray(DICTIONARY, dictionary);
		
		boolean finished = false;
		do 
		{
			ArrayList <String> userList = readWordList();
			if(userList.get(0) == "")
				finished = true;
			else
			{
				boolean validChain = isWordChain(userList);
				if (validChain == true)
					System.out.println("Valid chain of words from Lewis Carroll's word-links game.");
				else
					System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");	
			}
		}
		while (!finished);
	}
	
	public static void moveArrayListIntoArray(String[] StrArr, ArrayList<String> Arrlst) 
	{
		for (int i = 0; i < StrArr.length; i++) 
			StrArr[i] = Arrlst.get(i);
	}
	
	public static ArrayList <String> readDictionary (File textFile) throws IOException 
	{
		ArrayList <String> Dictionary = new ArrayList <String>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
		String word;
		
		while ((word = bufferedReader.readLine()) !=  null)
			Dictionary.add(word);
		
		bufferedReader.close();
		return Dictionary;	
	}
	
	public static ArrayList<String> readWordList ()
	{
		System.out.println("Enter a comma separated list of words (or an empty list to quit):");
		Scanner Input = new Scanner (System.in);
		String userInputLine = Input.nextLine();
		
//		ArrayList <String> userInputList = new ArrayList<String>();
		ArrayList <String> wordList = new ArrayList<String>();
		
		String word = "";
		String lastWord = "";
		int finalIndex = 1;
		
		for (int i = 0; i < userInputLine.length(); i++) 
		{
			if (userInputLine.charAt(i) == ',') 
			{
				if (userInputLine.charAt(i + 1) == ' ')
					i += 1;
				wordList.add(word);
				word = "";
				finalIndex = i + 1;
			} 
			else 
				word += userInputLine.charAt(i);
		}
		
		for (; finalIndex < userInputLine.length(); finalIndex++) 
			lastWord += userInputLine.charAt(finalIndex);
		
		wordList.add(lastWord);
		Input.close();
		return wordList;
	}
	
	public static boolean isUniqueList (ArrayList <String> userInput)
	{
		boolean isUnique = true;
		for (int i = 1; i < userInput.size(); i++)
			{	
				if (userInput.get(i).equals(userInput.get(i-1)))
					isUnique = false;
			}
		return isUnique;
	}
	
	public static boolean isEnglishWord (String word)
	{
		boolean englishWord = false;
		
		int search = Arrays.binarySearch(DICTIONARY, word);
		
		if (search >= 0)
			englishWord = true;
		return englishWord ;
	}
	
	public static boolean isDifferentByOne(String word1, String word2) 
	{
		boolean differentByOne = false;
		
		int sameCharCount = 0;
		
		if (word1.length() == word2.length()) 
		{
			for (int index = 0; index < word1.length(); index++) 
			{
				if (word1.charAt(index) == word2.charAt(index))
					sameCharCount++;
			}
			if (sameCharCount == (word1.length() - 1))
				differentByOne = true;
			else
				differentByOne = false;
		}
		return differentByOne;
	}
	
	public static boolean isWordChain(ArrayList<String> userInput) 
	{
		
		boolean wordChain = false;
		boolean realWord = true; 
		boolean differentByOne = true;
		
		if (isUniqueList(userInput) == true) 
		{
			for (int index = 1; index < userInput.size(); index++) 
			{
				String word = userInput.get(index);
				if (isEnglishWord(word) != true)
					realWord = false;

				if (isDifferentByOne(userInput.get(index - 1), userInput.get(index)) != true)
					differentByOne = false;

			}
		}
		if (realWord == true && differentByOne == true)
			wordChain = true;
		return wordChain;
	}

}
