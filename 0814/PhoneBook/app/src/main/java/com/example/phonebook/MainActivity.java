package com.example.phonebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.phonebook.model.Member;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Member> datas;
    private ArrayAdapter aa;//여러개의 view를 담을 수 있는 컨테이너 **읽어와서 view 객체 생성해주는 중간단계 역할
    private ListView listView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv1);

        datas = new ArrayList<>();
        aa = new phoneAdapter(this, R.layout.item_layout, datas);//this: activity(application을 상속-context를 상속)
        listView.setAdapter(aa);//어댑터 연결: listView는 화면을 출력할 때 어댑터로부터 데이터를 받아서 해당 데이터를 화면에 출력
        //이벤트 리스너: 이벤트가 발생했는가 기다렸다가 발생하면 핸들러를 호출
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//리스터를 listview에 부착
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {//i: 인덱스
                Member m  = datas.get(i);
                String s = m.getName() + " / " + m.getTel();
                Toast.makeText(MainActivity.this, s ,Toast.LENGTH_SHORT).show();
            }
        });
        registerForContextMenu(listView);//리스트 뷰에 콘텍스트 메뉴 붙이기
    }

    //options menu 생성 *context menu와 별개의 개념
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,Menu.FIRST,0,"add"); //Menu.FIRST==1
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case 1:
                System.out.println("add 메뉴로 이동");
                Intent intent = new Intent(this, AddActivity.class);//명시적으로 activity 활성화
                startActivityForResult(intent,1);
                break;
        }
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    Member m = (Member) data.getSerializableExtra("m");
                    datas.add(m);
                    break;
            }
        }
    }
}