/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author HP
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mahmoud
 */
public class ThreadRunnable implements Runnable {

    Semaphore sem;
    String path;
    static String longest = "";
    static String shortest = "";
    
    
    private String findShortestWord(String line) {
        String[] words = line.split("\\s+");
        String shortestWord =  words[0];  // Initialize with the first word

        for (String word : words) {
            if (word.length() < shortestWord.length()) {
                shortestWord = word;
            }
        }

        return shortestWord;
    }
    
    private String findLongestWord(String line) {
        String[] words = line.split("\\s+");
        String longestWord = "";

        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        return longestWord;
    }
    public ThreadRunnable(String path) {
        this.path = path;
        this.sem = new Semaphore(1);
    }

    public String getlong() {
        return ThreadRunnable.longest;

    }

    public static String getShort() {

        return ThreadRunnable.shortest;

    }

    @Override
    public void run() {
        int threadId = Integer.parseInt(Thread.currentThread().getName());
        File f1 = new File(path); 
        String[] words = null;  
        FileReader fr = null;
        try {
            fr = new FileReader(f1); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThreadRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br; 
        br = new BufferedReader(fr);
        String s;
        String input = "is";   
        String input2 = "are";
        String input3 = "you";
        int count = 0;   
        int count2 = 0;
        int count3 =0;
        int countWords = 0;
        String longestWord = "";
        String shortestWord = "";
        try {

            while ((s = br.readLine()) != null) 
            {   
                
                String lineLongestWord = findLongestWord(s);
                if (lineLongestWord.length() > longestWord.length()) {
                    longestWord = lineLongestWord;
                }
                
                String lineShortestWord = findShortestWord(s);
                if (lineShortestWord.length() < shortestWord.length() || shortestWord.isEmpty()) {
                    shortestWord = lineShortestWord;
                }
                
                words = s.split(" ");  
                
                for (String word : words) {
                    sem.acquire();
                    countWords++;
                    
                    if (shortest.length() == 0) {
                        shortest = word;
                    } else if (word.length() < shortest.length() && word.length() != 0) {

                        shortest = word;
                    }
                    if (word.length() > longest.length()) {
                        longest = word;
                    }

                    if (word.equals(input)) 
                    {
                        count++;    
                    } else if (word.equals(input2)) {
                        count2++;
                    }  else if (word.equals(input3)) {
                        count3++;
                    }
                    sem.release();
                }
            }

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ThreadRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Thread name : " + threadId + " Number of words in file " +f1.getName()+" is " + countWords);
        System.out.println("Thread name : " + threadId + " The given 'is' is present for " + count + " Times in the file");
        System.out.println("Thread name : " + threadId + " The given 'are' is present for " + count2 + " Times in the file");
        System.out.println("Thread name : " + threadId + " The given 'you' is present for " + count3 + " Times in the file");
        System.out.println("Thread name : " + threadId + " longest word per file is : " + longestWord);
        System.out.println("Thread name : " + threadId + " shortest word per file is : " + shortestWord);
        

        try {
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
