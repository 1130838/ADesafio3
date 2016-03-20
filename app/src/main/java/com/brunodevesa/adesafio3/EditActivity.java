package com.brunodevesa.adesafio3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    TextView tv;
    ListView lv;
    String[] resourcesArray;
    List<String> colorList;
    ArrayAdapter<String> adapter;
    private static final String MYSHPREFS = "MySharedPrefences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tv = (TextView) findViewById(R.id.activity_edit_tv_text_id);
        lv = (ListView) findViewById(R.id.activity_edit_lv_colorList);

        resourcesArray = getResources().getStringArray(R.array.color_array);
        colorList = Arrays.asList(resourcesArray);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, colorList);
        lv.setAdapter(adapter);

        registerForContextMenu(lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = getIntent();
                String selectedFromList = (String) lv.getItemAtPosition(position);
                intent.putExtra("EXTRA_MESSAGE", selectedFromList);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


    }


}
