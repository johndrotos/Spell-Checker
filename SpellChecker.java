import java.util.*;
import java.io.*;

public class SpellChecker implements SpellCheckerInterface{

    //instance variables
    File myFile;
    File dictionary;
    HashSet<String> hashSet;

    //constructor
    public SpellChecker(String filename){
        
        hashSet = new HashSet<String>();

        dictionary = new File(filename);
        try{
            Scanner s = new Scanner(dictionary);
            while(s.hasNext()){
                hashSet.add(s.next());
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Error: FileNotFoundException");
        }
    }

    //methods
    @Override
    public ArrayList<String> getIncorrectWords(String filename){

        ArrayList<String> incorrect = new ArrayList<String>();

        myFile = new File(filename);
        try{
            Scanner s = new Scanner(myFile);
            
            while(s.hasNext()){
                String a = s.next();
                //converts it to lowercase
                a = a.toLowerCase();

                //creates new string with only the non-punctuation characters
                String b = new String();

                for(int i = 0; i < a.length(); i++){
                    char x = a.charAt(i);
                    if((x > 47 && x < 58) || (x > 96 && x < 123)){
                        b = b + x;
                    }
                }
                if(!hashSet.contains(b)){
                    incorrect.add(b);
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Error: FileNotFoundException");
            return null;
        }

        return incorrect;

    }

    
	@Override
    public Set<String> getSuggestions(String word){
        String incorrectWord = word;
        HashSet<String> possibleFixes = new HashSet<String>();
        
        //add one character
        //copies the word up to i, then iterates through
        //a-z, adding the character and copying over
        //the rest of the original characters
        for(int i = 0; i < word.length()+1; i++){
            String testWord = new String();
            for(int j = 0; j < i; j++){
                testWord = testWord + word.charAt(j);
            }
            char a = 'a';
            while(a != 122){
                String testWord2 = testWord;
                testWord2 = testWord + a;
                for(int k = i+1; k < (word.length() + 1); k++){
                    testWord2 = testWord2 + word.charAt(k-1);
                }
                if(hashSet.contains(testWord2)){
                    possibleFixes.add(testWord2);
                }
                a++;
            }
            
        }


        //remove one character
        //copies the word up to i, then adds the rest of the
        //characters shifted down one
        for(int i = 0; i < word.length(); i++){
            String testWord = new String();
            for(int j = 0; j < i; j++){
                testWord = testWord + word.charAt(j);
            }
            for(int k = i; k < word.length()-1; k++){
                testWord = testWord + word.charAt(k+1);
            }
            if(hashSet.contains(testWord)){
                possibleFixes.add(testWord);
            }
        }


        //swap adjacent characters
        for(int i = 0; i < word.length()-1; i++){
            String testWord = new String();
            for(int j = 0; j < i; j++){
                testWord = testWord + word.charAt(j);
            }
            testWord = testWord + word.charAt(i+1) + word.charAt(i);
            for(int k = i + 2; k < word.length(); k++){
                testWord = testWord + word.charAt(k);
            }
            if(hashSet.contains(testWord)){
                possibleFixes.add(testWord);
            }
        }

        return possibleFixes;
    }

}