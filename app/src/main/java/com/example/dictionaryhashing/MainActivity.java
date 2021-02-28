package com.example.dictionaryhashing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> s1 = new ArrayList<>();

    ArrayList<String> s2 = new ArrayList<>();


    static int prime = 769;
    static int m = 100;
    public String dataset = "BanglaDictionary.txt";
    public String perfectAB = "";
    public String keyFile = "";
    static int primary_a = 11;
    static int primary_b = 15;

    static  ArrayList<String> filtered_s1 = new ArrayList<>();
    static  ArrayList<String> filtered_s2 = new ArrayList<>();
    static ArrayList<Long> key = new ArrayList<>();
    static ArrayList<Integer> keysForSearch = new ArrayList<>(); // hash indices to be searched

    static ArrayList<ArrayList<Long> > x
            = new ArrayList<ArrayList<Long> >();

    static ArrayList<ArrayList<Long> > hashTable
            = new ArrayList<ArrayList<Long> >();
    static ArrayList<Integer > banglaHashTable
            = new ArrayList<Integer >();

    EditText searcInput;
    String searchInput="";
    RecyclerView recyclerView;
    ArrayList<String> numberlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searcInput = (EditText) findViewById(R.id.searchInput);
        searchInput = searcInput.getText().toString();
        //creating 2D array
        for(int i=0;i<m;i++)
        {
            x.add(new ArrayList<Long>());
        }
        //creating 2D array
        for(int i=0;i<m;i++)
        {
            hashTable.add(new ArrayList<Long>());
        }

        for(int i=0;i<m;i++)
        {
           // banglaHashTable.add(new ArrayList<Integer>());

        }

        String data = "";
        StringBuffer sbuffer = new StringBuffer();
        InputStream is = null;
        try {
            is = getAssets().open("BanglaDictionary.txt");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        if(is != null) {
            try {
                while ((data = reader.readLine()) != null) {

                    //data = data.substring(1,data.length());
                    int wordStart = 0;
                    int wordEnd = data.indexOf("|");
                    String word = data.substring(wordStart,wordEnd);
                    String banglaWord = data.substring(wordEnd+1,data.length());
                    s1.add(word);
                    s2.add(banglaWord);
                    //sbuffer.append(word + "\n"+banglaWord+"\n");
                    System.out.println("this is data "+data);
                    is.close();


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*
        System.out.println(sbuffer);
        System.out.println("size = = = "+(s1.size()));
                Toast.makeText(getApplicationContext()," successful "+sbuffer,Toast.LENGTH_LONG).show();
        */
        Long testKey;
        int hashvalue;
        int total_wordcount=0;
        String test = "";
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

        //build total hash table :
        for(int i=0;i<filtered_s1.size();i++)
        {
            String a="",b="";
            int secondary_a = 0,secondary_b=0;
            test = s1.get(i);
            testKey = getAscii(test);

            hashvalue = hash(primary_a,primary_b,testKey);
            // find a,b for the hashvalue
            InputStream is1 = null;
            try {
                is1 = getAssets().open("perfectAB.txt");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(is));

            if(is1 != null) {
                try {
                    while ((data = reader1.readLine()) != null) {

                        int iStart = 0;
                        String ini = data.substring(iStart,data.indexOf(" "));
                        int init = Integer.parseInt(ini);
                        if(init==hashvalue) break;

                        data = data.substring(data.indexOf(" ")+1,data.length());
                         a = data.substring(iStart,data.indexOf(" "));
                          b = data.substring(data.indexOf(" ")+1,data.length());
                          secondary_a = Integer.parseInt(a);
                          secondary_b = Integer.parseInt(b);
                        is.close();


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(a!="")
            {
                hashTable.get(hashvalue).add(hash(secondary_a,secondary_b,testKey), testKey);
            }
        }
                recyclerView = findViewById(R.id.recyclerView);

        MyAdapter myAdapter = new MyAdapter(this,s1,s2);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));


/*
//create object of listview
        ListView listView=(ListView)findViewById(R.id.listview);

//create ArrayList of String
        final ArrayList<String> arrayList=new ArrayList<>();

//Add elements to arraylist
        arrayList.add("android");
        arrayList.add("is");
        arrayList.add("great");
        arrayList.add("and I love it");
        arrayList.add("It");
        arrayList.add("is");
        arrayList.add("better");
        arrayList.add("then");
        arrayList.add("many");
        arrayList.add("other");
        arrayList.add("operating system.");
        arrayList.add("android");
        arrayList.add("is");
        arrayList.add("great");
        arrayList.add("and I love it");
        arrayList.add("It");
        arrayList.add("is");
        arrayList.add("better");
        arrayList.add("then");
        arrayList.add("many");
        arrayList.add("other");
        arrayList.add("operating system.");

//Create Adapter
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

//assign adapter to listview
        listView.setAdapter(arrayAdapter);

//add listener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"clicked item:"+i+" "+arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });

        */
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
        /*
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
           */

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
