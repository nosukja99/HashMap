package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.text.NumberFormat;

public class Main {

    public static void main(String[] args) throws Exception {
        int input;
        String value;
        String output = "";

        HashMap<Integer, String> myMap = new HashMap<Integer, String>();
        File fout = new File("out.txt");
        Path p = Paths.get("out.txt");
        boolean exists = Files.exists(p);
        boolean notExists = Files.notExists(p);
        if (exists)
        {
            System.out.println("File already exists.");
            myMap = readLines(fout);
            System.out.println(myMap);
        }
        else if (notExists) {
            System.out.println("New File created.");
            fout = new File("out.txt");
        }
        else {
            System.out.println("File's status is unknown!");
        }

        //User input
        System.out.println("Prompt: Enter a number: ");
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        input = Integer.parseInt(buf.readLine());
        value = NumberToWard(input);

        if(!myMap.containsKey(input))
        {
            System.out.println("Response: You entered "+value);
            System.out.println("This number will be added to the map");
            myMap.put(input,value);
            FileWriter fileWriter = new FileWriter(fout, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            //System.out.println("before file check "+input+" "+value);
            bw.write(input+" "+value);
            bw.newLine();
            bw.close();
            //System.out.println("Response: You entered "+value);
        }
        else
        {
            System.out.println("Response: You entered "+value);
            System.out.println("This number already exit.");
        }
        System.out.println("In the HashMap: \n");
        System.out.println(Arrays.asList(myMap));
        // System.out.println(Collections.singletonList(myMap));
    }



    public static String NumberToWard(int num)
    {
        String[] units = { "", "One", "Two", "Three", "Four",
                "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
                "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
                "Eighteen", "Nineteen" };
        String[] tens = {
                "", 		// 0
                "",		// 1
                "Twenty", 	// 2
                "Thirty", 	// 3
                "Forty", 	// 4
                "Fifty", 	// 5
                "Sixty", 	// 6
                "Seventy",	// 7
                "Eighty", 	// 8
                "Ninety" 	// 9
        };

        if(num<0)
        {
            return "Minus "+ NumberToWard(-num);
        }
        if (num < 20)
        {
            return units[num];
        }

        if (num < 100)
        {
            return tens[num / 10] + ((num % 10 != 0) ? " " : "") + units[num % 10];
        }

        if (num < 1000)
        {
            return units[num / 100] + " Hundred" + ((num % 100 != 0) ? " " : "") + NumberToWard(num % 100);
        }

        if (num < 100000)
        {
            return NumberToWard(num / 1000) + " Thousand" + ((num % 10000 != 0) ? " " : "") + NumberToWard(num % 1000);
        }

        if (num < 10000000)
        {
            return NumberToWard(num / 100000) + " Lakh" + ((num % 100000 != 0) ? " " : "") + NumberToWard(num % 100000);
        }
        return NumberToWard(num / 10000000) + " Crore" + ((num % 10000000 != 0) ? " " : "") + NumberToWard(num % 10000000);
    }

    public static HashMap<Integer, String> readLines (File file) throws Exception {
        BufferedReader reader = new BufferedReader((new FileReader(file)));
        HashMap<Integer, String> myMap = new HashMap<Integer, String>();
        String line = reader.readLine();

        String value;
        while (line != null)
        {
            //System.out.println("Read line from file: "+line);
            String[] splited = line.split(" ", 2);
            //System.out.println("Array length "+splited.length);
            //System.out.println("#########"+splited[0]+"&&&& "+splited[1]);
            if (splited.length>=1)
            {
                String key = splited[0];
                myMap.put(Integer.valueOf(key), splited[1]);
                line = reader.readLine();
                //System.out.println(myMap);
            }else {
                System.out.println("ignoring line: " + line);
            }
        }
        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
        //System.out.println(myMap);
        reader.close();
        return myMap;
    }
}