package com.brunodevesa.adesafio3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        TextView tv = (TextView) findViewById(R.id.activity_edit_tv_text_id);
        Button btn = (Button) findViewById(R.id.activity_editList_btn_save_id);
        final EditText et = (EditText) findViewById(R.id.activity_editList_et_textToEdit_id);

        final Intent intent = getIntent();
        String nameToEdit = intent.getStringExtra("NAME_TO_EDIT");

        // Toast.makeText(this, "name to edit : " + nameToEdit, Toast.LENGTH_SHORT).show();
        et.setText(nameToEdit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameEdited = et.getText().toString();
                intent.putExtra("NAME_TO_EDIT", nameEdited);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


    }
}
