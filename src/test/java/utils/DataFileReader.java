package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataFileReader {

    public static List<String> items = new ArrayList<>();

    public List<String> readInputFile() throws IOException {
        String fileName = FileReaderManager.getInstance().getConfigFileReader().getInputFile();
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String input1 = line.replaceAll("[\\s]", "");
            Matcher m = Pattern.compile(FileReaderManager.getInstance().getConfigFileReader().getRegistrationNoPattern()).matcher(input1);
            while (m.find()) {
                items.add(m.group());
            }
        }
        fr.close();

        return items;
    }

    public String readOutputFile(String regNo) throws IOException {
        String fileName = FileReaderManager.getInstance().getConfigFileReader().getOutputFile();
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        String[] words = null;
        String expectedLine = null;
        int count = 0;
        while ((line = br.readLine()) != null) {
            //process the line
            words = line.split(",");  //Split the word using space
            for (String word : words) {
                if (word.equals(regNo))   //Search for the given word
                {
                    count++;    //If Present increase the count by one
                    expectedLine = line.replaceAll(regNo + ",", "");
                }
            }
        }
        if (count != 0)  //Check for count not equal to zero
        {
            System.out.println("The given word is present for " + count + " Times in the file");
        } else {
            System.out.println("The given word is not present in the file");
        }

        fr.close();

        return expectedLine;

    }
}
