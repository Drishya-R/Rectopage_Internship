package com.example.json_project;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=(ListView)findViewById(R.id.listview);
        ArrayAdapter arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listview.setAdapter(arrayAdapter);
        get_json();
    }

    public void get_json()
    {
        String json;
        try {
            InputStream is = getAssets().open("json_task4.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray("questions");
           /* int j=1;*/
            for(int i=0; i<jsonArray.length();i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                String Question_num = object.getString("question_number");
                String Question = object.getString("question");
                String options = object.getString("options");
                arrayList.add(Question_num + ")" + Question + options);
            }



        }catch (IOException e)
        {
            e.printStackTrace();
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
}
