package com.brunodevesa.adesafio3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    private static final String MYSHPREFS = "MySharedPreferences";
    //private static final String MYBACKGROUNDCOLOR = "MyBackGroundColor";
    private static final int REQUEST_CODE_1 = 1;
    private static final int REQUEST_CODE_2 = 2;


    ArrayAdapter<String> adapter;

    ListView mListView;
    TextView textView;
    List<String> studentsArray;
    String[] resourcesArray;
    EditText et;
    String myFavouriteBackgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resourcesArray = getResources().getStringArray(R.array.students_array);
        studentsArray = Arrays.asList(resourcesArray);

        mListView = (ListView) findViewById(R.id.activity_main_lv_studentsList);
        textView = (TextView) findViewById(R.id.activity_main_tv_listTitle_id);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentsArray);
        mListView.setAdapter(adapter);

        registerForContextMenu(mListView);

        //SharedPreferences stuff (to get shared preferences when activity creation - when app starts too):
        SharedPreferences sharedPreferences = getSharedPreferences(MYSHPREFS, 0);
        myFavouriteBackgroundColor = sharedPreferences.getString("MY_COLOR", "");
        updateUserSettings(myFavouriteBackgroundColor);

    }


    public void updateUserSettings(String favColor) {

        if (favColor.equalsIgnoreCase("blue")) {
            mListView.setBackgroundColor(Color.parseColor("#95BECC"));
            adapter.notifyDataSetChanged(); // Important to notify Adapter !!
            Toast.makeText(this, "background color changed to " + favColor, Toast.LENGTH_SHORT).show();

        }
        else if (favColor.equalsIgnoreCase("red")) {
            mListView.setBackgroundColor(Color.parseColor("#F0A3A3"));
                    adapter.notifyDataSetChanged(); // Important to notify Adapter !!
            Toast.makeText(this, "background color changed to " + favColor, Toast.LENGTH_SHORT).show();

        }
        else if (favColor.equalsIgnoreCase("gray")) {
            mListView.setBackgroundColor(Color.parseColor("#B8B8B8"));
            adapter.notifyDataSetChanged(); // Important to notify Adapter !!
            Toast.makeText(this, "background color changed to " + favColor, Toast.LENGTH_SHORT).show();
        }
        else {
            mListView.setBackgroundColor(Color.WHITE);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle item selection
        switch (item.getItemId()) {
            case R.id.action_edit_preferences:

                Intent intent = new Intent(this, EditPreferencesActivity.class);
                startActivityForResult(intent, REQUEST_CODE_1); // waits for a result that will be called in the onActivityResult()

                //  Toast.makeText(this, et.getText(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * When the invoked activity reply its result, it will do this method
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_1:
                    String msg = data.getStringExtra("EXTRA_MESSAGE");
                    Toast.makeText(this, "message received = " + msg, Toast.LENGTH_SHORT).show();

                    updateUserSettings(msg);

                    //SharedPreferences stuff :
                    SharedPreferences sharedPreferences= getSharedPreferences(MYSHPREFS, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("MY_COLOR", msg);
                    editor.apply();
                    break;
                case REQUEST_CODE_2:
                    String msg2 = data.getStringExtra("NAME_TO_EDIT");
                    int pos = data.getIntExtra("POSITION_TO_EDIT", 0);

                    studentsArray.set(pos, msg2); // update the ArrayList with the changed item
                    adapter.notifyDataSetChanged(); // notify the adapter of the changes - Important to notify Adapter !!

                    Toast.makeText(this, "message received: name=" + msg2 + "position = " + pos, Toast.LENGTH_LONG).show();

            }

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

       // Intent intent;
        String str = adapter.getItem(pos);

        switch (item.getItemId()) {
            case R.id.delete:
                Toast.makeText(this, "Testing delete", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.edit:

                Intent intent = new Intent(this, EditListActivity.class);
                intent.putExtra("NAME_TO_EDIT", str);
                intent.putExtra("POSITION_TO_EDIT", pos);

                startActivityForResult(intent, REQUEST_CODE_2);

              //  Toast.makeText(this, "Testing edit", Toast.LENGTH_SHORT).show();
            default:
                return super.onContextItemSelected(item);
        }

    }
}
