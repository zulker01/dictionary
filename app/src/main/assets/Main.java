package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    static int prime = 769;
    static int m = 100;
    public String dataset = "BanglaDictionary.txt";
    public String perfectAB = "";
    public String keyFile = "";
    static int primary_a = 11;
    static int primary_b = 15;
    static  ArrayList<String> s1 = new ArrayList<>();
    static  ArrayList<String> s2 = new ArrayList<>();
    static  ArrayList<String> filtered_s1 = new ArrayList<>();
    static  ArrayList<String> filtered_s2 = new ArrayList<>();
    static ArrayList<Long> key = new ArrayList<>();
    static ArrayList<Integer> keysForSearch = new ArrayList<>(); // hash indices to be searched

    static ArrayList<ArrayList<Long> > x
            = new ArrayList<ArrayList<Long> >();

    public static void main(String[] args) {
	// write your code here



        for(int i=0;i<m;i++)
        {
            x.add(new ArrayList<Long>());
        }
    /*
        s1.add("faito");
        s1.add("mairo");
        s1.add("lakka");
        s1.add("dacca");
        s1.add("fappa");
        s1.add("lalla");
        s1.add("akka");
        s1.add("adda");
        s1.add("acca");
      */
        String test = "";
        int p = 0;
        try {
            File myObj = new File("E:\\intellij project\\test_hashing\\src\\com\\company\\BanglaDictionary.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //data = data.substring(1,data.length());
                int wordStart = 0;
                int wordEnd = data.indexOf("|");
                String word = data.substring(wordStart,wordEnd);
                String banglaWord = data.substring(wordEnd+1,data.length());
                s1.add(word);
                s2.add(banglaWord);
                System.out.println(data);
                p++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("total line : "+p);
        Long testKey;
        int hashvalue;
        int total_wordcount=0;

        //get unique key value
        for(int i=0;i<s1.size();i++)
        {
            test = s1.get(i);
            testKey = getAscii(test);
            key.add(testKey);
            hashvalue = hash(primary_a,primary_b,testKey);
            if(x.get(hashvalue).contains(testKey))
            {
                continue;
            }
            else {
                total_wordcount++;
                x.get(hashvalue).add(testKey);
                filtered_s1.add(s1.get(i));
                filtered_s2.add(s2.get(i));
                System.out.println("Hash value of "+s1.get(i)+" = "+hashvalue);
            }
        }
        System.out.println("total word in primary hashtable :" + total_wordcount+" s1 s2 filtered s1 s2 size "+s1.size()+" "+s1.size()+" "+filtered_s1.size()+" "+filtered_s2.size()+"\n");
        //print primary hash table in file
        try {
            File myObj = new File("E:\\intellij project\\test_hashing\\src\\com\\company\\primary_hashing.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("E:\\intellij project\\test_hashing\\src\\com\\company\\primary_hashing.txt");

            for(int i=0;i<m;i++)
            {
                myWriter.write(String.valueOf(x.get(i))+"\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        /*
        //print new words  in file
        try {
            File myObj = new File("E:\\intellij project\\test_hashing\\src\\com\\company\\BanglaDictionary.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("E:\\intellij project\\test_hashing\\src\\com\\company\\BanglaDictionary.txt");
            myWriter.write("muri 2khao2");
            for(int i=0;i<filtered_s1.size();i++)
            {
                myWriter.write(filtered_s1.get(i)+"|"+filtered_s2.get(i)+"\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        */




        //implement perfect hashing :
        perfect_hashing();


    }

    private static void perfect_hashing() {
        for(int i=0;i<m;i++)
        {
            if(x.get(i).size()<=1)
            {
                continue;
            }
            else
            {
                keysForSearch.add(i);
            }
            /*

            else
            {
                //findPerfectAB()
                Set<Longeger> s =
                        new HashSet<Longeger>();
                Long len = x.get(i).size();
                while(true)
                {
                    Long a = (Long) (Math.random()*20)+2;
                    Long b = (Long) (Math.random()*20)+2;
                    System.out.println(" a "+a+" b "+b);
                    for(Long j=0;j<len;j++)
                    {
                        s.add(hash(a,b,x.get(i).get(j)));
                        System.out.println("hash of "+x.get(i).get(j)+" "+hash(a,b,x.get(i).get(j)));
                    }
                    if(s.size()==len)
                    {
                        System.out.println("congratulations for i = "+i+" ab is "+a+" "+b);
                        break;

                    }
                }

            }
            */
        }
        System.out.println("keys to search :");
        System.out.println(keysForSearch);
        //write the key value in a file
        try {
            File myObj = new File("E:\\intellij project\\test_hashing\\src\\com\\company\\keysToSearch.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("E:\\intellij project\\test_hashing\\src\\com\\company\\keysToSearch.txt");


                myWriter.write(String.valueOf(keysForSearch));


            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for(int i=0;i<keysForSearch.size();i++){
            find_perfect_ab(keysForSearch.get(i));
        }
       // find_perfect_ab();


    }
    private static void find_perfect_ab(int i)
    {
        //findPerfectAB()
        Set<Integer> s =
                new HashSet<Integer>();
        int len = x.get(i).size();
        int counter = 0;
        while(true)
        {
            if(counter>10000) break;

            int a = (int)(Math.random()*100) +2;
            int b = (int) (Math.random()*100)+2;
            System.out.println(" a "+a+" b "+b);
            counter++;
            for(int j=0;j<len;j++)
            {
                s.add(hash(a,b,x.get(i).get(j)));
                System.out.println("hash of "+x.get(i).get(j)+" "+hash(a,b,x.get(i).get(j)));
            }
            if(s.size()==len)
            {
                System.out.println("congratulations for i = "+i+" ab is "+a+" "+b);
                try {
                    File myObj = new File("E:\\intellij project\\test_hashing\\src\\com\\company\\perfectAB.txt");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    } else {
                        System.out.println("File already exists.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                try {
                    FileWriter myWriter = new FileWriter("E:\\intellij project\\test_hashing\\src\\com\\company\\perfectAB.txt",true);


                    myWriter.write(i+" "+a+" "+b+"\n");


                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                break;

            }
        }
    }
    private static void retrieve(String word)
    {
        Long key = getAscii(word);
        int primaryHash = hash(primary_a,primary_b,key);
        //find a,b for primary hash

    }

    public static Long getAscii(String word)  {
        Long sum ;
        sum = Long.valueOf(0);
        int len = word.length();
        for(int i=0; i<word.length(); i++)
        {
            int asciiValue = word.charAt(i);
            sum = 2*sum + asciiValue;
            //System.out.println(str.charAt(i) + "=" + asciiValue);
        }
        //System.out.println("ASCII of " + word + "=" + sum);
        return sum;
    }

    public static int hash(int a,int b,Long key)
    {
        int hash = (int) ((((a*key)+b)%prime)%m);
        if(hash<0) hash = -1*hash;
        return hash;
    }
}
