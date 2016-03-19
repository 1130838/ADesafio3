package com.brunodevesa.adesafio3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String MYSHPREFS="MySharedPrefences";
    private static final String MYKEY="MyKey";

    ArrayAdapter<String> adapter;

    ListView mListView;
    TextView textView ;
    List<String> things;
    String[] resourcesArray;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resourcesArray = getResources().getStringArray(R.array.students_array);
        things = Arrays.asList(resourcesArray);

        mListView = (ListView)findViewById(R.id.listView);
        textView = (TextView)findViewById(R.id.textView);
        et = (EditText) findViewById(R.id.activity_main_et_textToPass_is);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, things);
        mListView.setAdapter(adapter);

        registerForContextMenu(mListView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }


    // FIX IT !!
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle item selection
        switch (item.getItemId()){
            case R.id.action_edit_preferences:

                Intent intent = new Intent(this, EditActivity.class);
                String msg = String.valueOf(et.getText());
                intent.putExtra(MYSHPREFS,msg);
                startActivity(intent);

              //  Toast.makeText(this, et.getText(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        this.getMenuInflater().inflate(R.menu.context_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;
        long id = info.id;

        Intent intent;
        String str = (String)adapter.getItem(pos);

        switch (item.getItemId()){
            case R.id.delete:
                Toast.makeText(this, "Testing delete", Toast.LENGTH_SHORT).show();
                //intent = new Intent(this, DeleteActivity.class);
                //intent.putExtra(EXTRA_STRING, str);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
}
