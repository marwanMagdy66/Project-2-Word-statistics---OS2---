

package javaapplication4;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadCreat {

    public static void main(String[] args) {
        
        List threadOfFiles = new ArrayList();
        FindDir folderCrawler = new FindDir();
        System.out.print("Enter Path:");
        String aaa = "";
        Scanner sc=new Scanner(System.in);
        aaa=sc.nextLine();
        folderCrawler.crawlFold(aaa);
        //folderCrawler.crawlFold("C:\\Users\\DELL\\Desktop\\IR - project");
        System.out.println("All text files in the folder \"project\" and its sub directories are :\n---------------------------------------------------------------");
        for (Object textFileName : folderCrawler.getFiles()) {
            ThreadRunnable t = new ThreadRunnable((String) textFileName);
            threadOfFiles.add(t);
        }

        for (int i = 0; i < threadOfFiles.size(); i++) {
            try {
                Thread x = new Thread((Runnable) threadOfFiles.get(i), "" + (i+1));
                x.start();
                x.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadCreat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("THE lONGEST WORD is " + ThreadRunnable.longest);
        System.out.println("THE SHORTEST WORD is " + ThreadRunnable.shortest);
        System.out.println("NUMBER OF THREADS " + threadOfFiles.size());
    }
}
