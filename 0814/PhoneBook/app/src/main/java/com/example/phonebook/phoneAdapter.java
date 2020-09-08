package com.example.phonebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.phonebook.model.Member;

import java.util.ArrayList;
import java.util.List;

public class phoneAdapter extends ArrayAdapter<Member> {
    private Context context;
    private ArrayList<Member> list;
    private int resId;

    public phoneAdapter(@NonNull Context context, int resource, @NonNull List<Member> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = (ArrayList<Member>) objects;
        resId = resource;
    }

    @NonNull
    @Override
    //getview: 어댑터에서 가장 중요한 작업! 데이터 위치(position)를 읽어와서 리소스로 지정한 뷰로 생성하여 반환
    //list의 요소개수만큼 호출되는 함수
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView==null){            //뷰가 없다면 생성
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater: xml등의 리소스를 실제 뷰로 부풀리는 역할을 하는 객체
            itemView = vi.inflate(resId,null);//설계한 자료의 id를 받아서 해당 id로 실제 뷰를 생성.(item_layout.xml)
        }
        final Member m = list.get(position);//현재 위치의 멤버 객체 추출
        if(m != null) {
            TextView t1 = itemView.findViewById(R.id.textView);//이름 tv
            TextView t2 = itemView.findViewById(R.id.textView2);//전화번호 tv
            ImageView imgv = itemView.findViewById(R.id.imageView);//이미지
            imgv.setImageResource(R.drawable.ic_launcher_foreground);//임시로 고정값 사용.

            Button sms = itemView.findViewById(R.id.button17);
            Button call = itemView.findViewById(R.id.button19);

            //뷰에 텍스트 세팅
            if(t1!=null){
                t1.setText(m.getName());
            }
            if(t2!=null){
                t2.setText(m.getTel());
            }

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //action_call: 시스템에서 제공하는 것이기 때문에 permission 설정 필요 => manifestAndroid
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+m.getTel()));//묵시적으로 activity 활성화
                    context.startActivity(intent);
                }
            });
        }
        return itemView;
    }
}
