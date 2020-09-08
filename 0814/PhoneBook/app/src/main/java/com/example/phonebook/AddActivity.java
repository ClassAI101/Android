package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.phonebook.model.Member;

public class AddActivity extends AppCompatActivity {
    private Intent intent;
    private EditText name;
    private EditText tel;
    private RadioGroup rg;
    private int phoneType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        intent = getIntent();
        name = findViewById(R.id.name);//이름
        tel = findViewById(R.id.tel);//전화번호
        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rb1://휴대폰
                        phoneType = 1;
                        break;
                    case R.id.rb2://집
                        phoneType = 2;
                        break;
                    case R.id.rb3://직장
                        phoneType = 3;
                        break;
                }
            }
        });
    }
            public void onSave(View view) {
//                ImageView imgv = findViewById(R.id.imageView);//이미지
//                imgv.setImageResource(R.drawable.ic_launcher_foreground);//임시로 고정값 사용.
                String n = name.getText().toString();
                String t = tel.getText().toString();
                Member m = new Member(n,t,phoneType);
                intent.putExtra("m",m);
                setResult(RESULT_OK,intent);
                finish();
            }

}
