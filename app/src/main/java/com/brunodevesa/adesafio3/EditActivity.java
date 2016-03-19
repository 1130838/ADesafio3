package com.brunodevesa.adesafio3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    TextView tv;

    private static final String MYSHPREFS="MySharedPrefences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        tv = (TextView)findViewById(R.id.edit_activity_tv_text_id);

        Intent intent = getIntent();
        String mensagem_passada  = intent.getStringExtra(MYSHPREFS);

        tv.setText(mensagem_passada);

        Toast.makeText(this, "msg passed = " + mensagem_passada, Toast.LENGTH_LONG).show();

    }



}
