package com.example.bai19_autocompletetextview;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MyArrayAdapter extends ArrayAdapter<Student> {

    Activity context;
    int resourceId;
    ArrayList<Student>arrStudent;
    public MyArrayAdapter(Activity context, int resource,
                          ArrayList<Student> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resourceId=resource;
        this.arrStudent=objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            //gắn Layout vào Activity
            convertView= context.getLayoutInflater().inflate(resourceId, null);
        }
        //Lấy Textview để lưu mã và tên
        TextView txtMaTen=(TextView) convertView.findViewById(R.id.txtMaVaTen);
        //Lấy TextView để lưu giới tính, năm sinh, nơi sinh
        TextView txtKhac=(TextView) convertView.findViewById(R.id.txtThongTinKhac);
        //Lấy sinh viên thứ position
        Student s=arrStudent.get(position);
        txtMaTen.setText(s.getId()+" - "+s.getName());
        //Dùng SimpleDateFormat để định dạng ngày tháng dd/MM/YYYY -> 22/12/2012
        SimpleDateFormat dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        txtKhac.setText((s.isGender()?"Nữ-":"Nam-")+
                dft.format(s.getBirthday())+" - "+
                s.getPlaceOfBirth());
        return convertView;
    }
}
