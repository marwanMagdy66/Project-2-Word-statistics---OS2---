/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.threadcreat;

/**
 *
 * @author HP
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class FindDir {

    private List allTextFiles = new ArrayList();
    private String fileExtension = ".txt";

    public List getFiles() {
        return allTextFiles;
    }
    
    public void crawlFold(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
//                System.out.println(listOfFiles[i]);
                if (listOfFiles[i].getName().endsWith(fileExtension)) {
                    allTextFiles.add(listOfFiles[i].getAbsolutePath());
                }
            } else if (listOfFiles[i].isDirectory()) {
                crawlFold(path + File.separator + listOfFiles[i].getName());
            }
        }
    }
}
