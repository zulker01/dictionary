package com.example.dictionaryhashing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> s1 = new ArrayList<>();

    ArrayList<String> s2 = new ArrayList<>();



    //String s1[]={"Muri","Rater","Vora"},s2[]={"khao","Akash","tara"};
    RecyclerView recyclerView;
    ArrayList<String> numberlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*String json;
        try{

            InputStream is = getAssets().open("example_1.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();


            json = new String (buffer,"UTF-8");
           // int len = jsonArray.length();
            int len = 10;

            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj =  jsonArray.getJSONObject(i);
                System.out.println(obj.getString("fruit"));

                Toast.makeText(getApplicationContext(),"successful "+obj.getString("fruit"),Toast.LENGTH_LONG).show();
            }
            Toast.makeText(getApplicationContext(),"successful "+size,Toast.LENGTH_LONG).show();

        }
        catch(FileNotFoundException e) {e.printStackTrace(); }
        catch (IOException e) {e.printStackTrace(); }
        //catch(ParseException e) {e.printStackTrace(); }
        catch(Exception e) {e.printStackTrace(); }


        Toast.makeText(getApplicationContext(),"not successful ",Toast.LENGTH_LONG).show();

         */

        String data = "";
        StringBuffer sbuffer = new StringBuffer();
        InputStream is = null;
        try {
            is = getAssets().open("BengaliDictionary_17.txt");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        int i=0;
        if(is != null) {
            try {
                while ((data = reader.readLine()) != null) {

                    data = data.substring(1,data.length());
                    int wordStart = 0;
                    int wordEnd = data.indexOf("|");
                    String word = data.substring(wordStart,wordEnd);
                    String banglaWord = data.substring(wordEnd+1,data.length());
                    s1.add(word);
                    s2.add(banglaWord);
                    //sbuffer.append(word + "\n"+banglaWord+"\n");

                    is.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.print(sbuffer);
                Toast.makeText(getApplicationContext()," successful "+sbuffer,Toast.LENGTH_LONG).show();
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
}