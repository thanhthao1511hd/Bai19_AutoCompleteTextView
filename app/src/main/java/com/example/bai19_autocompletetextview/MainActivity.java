package com.example.bai19_autocompletetextview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView lvSinhvien;
    //Cặp đôi để Custom ListView
    MyArrayAdapter adapterSinhvien;
    ArrayList<Student> arrSinhvien=new ArrayList<Student>();

    EditText editMa,editTen,editNamsinh;
    CheckBox chkGender;
    Button btnNamsinh,btnNhapsv;
    // ghê đấy ác đấy sịn đấy idol đấy 
    AutoCompleteTextView autoTextNs;
    //Cặp đôi để dùng cho AutoCompleteTextView
    ArrayList<String>arrAuto=new ArrayList<String>();
    ArrayAdapter<String>adapterAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidgets();
        addEventsForWidgets();
        //fakeData();
    }
    /**
     * hàm lấy các widgets
     */
    public void getFormWidgets()
    {
        editMa=(EditText) findViewById(R.id.editMa);
        editTen=(EditText) findViewById(R.id.editTen);
        editNamsinh=(EditText) findViewById(R.id.editNgaySinh);
        chkGender=(CheckBox) findViewById(R.id.chkGt);
        autoTextNs=(AutoCompleteTextView) findViewById(R.id.autoCompleteNS);
        btnNamsinh=(Button) findViewById(R.id.btnNgaySinh);
        btnNhapsv=(Button) findViewById(R.id.btnNhap);

        lvSinhvien=(ListView) findViewById(R.id.lvsinhvien);
        //Gán DataSource cho Adapter ListView
        adapterSinhvien=new MyArrayAdapter(this,
                R.layout.item_layout,
                arrSinhvien);
        //Gán Adapter vào ListView
        lvSinhvien.setAdapter(adapterSinhvien);
    }
    /**
     * Hàm thiết lập sự kiện cho Button
     */
    public void addEventsForWidgets()
    {
        btnNamsinh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                processBirthday();
            }
        });
        btnNhapsv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                processInput();
            }
        });
    }
    /**
     * Hàm hiển thị DatePickerDialog để chọn năm sinh
     */
    public void processBirthday()
    {
        DatePickerDialog.OnDateSetListener callBack=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                editNamsinh.setText(arg3+"/"+arg2+"/"+arg1);
            }
        };
        //Ở đây Tôi chưa xử lý lấy ngày tháng năm trên EditText đưa vào DatePicker
        //Bạn tự làm
        DatePickerDialog dateDialog=new DatePickerDialog(this, callBack, 1989, 9, 25);
        dateDialog.setTitle("Birthday");
        dateDialog.show();
    }
    /**
     * Hàm xử lý nhập dữ liệu từ giao diện
     */
    public void processInput()
    {
        String ma=editMa.getText()+"";
        String ten=editTen.getText()+"";
        String ns=editNamsinh.getText()+"";
        String nois=autoTextNs.getText()+"";
        boolean gt=chkGender.isSelected();
        SimpleDateFormat dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Student s=new Student(ma, ten, gt, dft.parse(ns), nois);
            arrSinhvien.add(s);
            //Thêm mới xong thì gọi hàm dưới cập nhập lại giao diện
            adapterSinhvien.notifyDataSetChanged();
            //Xử lý cho Autocomplete về nơi sinh
            processAutoComplete(nois);
        } catch (ParseException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    /**
     * Hàm xử lý Autocomplete Nơi sinh
     * @param data
     */
    public void processAutoComplete(String data)
    {
        //Chạy từ đầu chí cuối nếu trùng thì thoát khỏi hàm
        for(int i=0;i<arrAuto.size();i++)
        {
            String x=arrAuto.get(i);
            if(x.trim().equalsIgnoreCase(data.trim()))
                return;
        }
        //nếu xuống đây được tức là chưa tồn tại-> đừa vào arrAuto
        arrAuto.add(data);
        //Đưa vào ADapter
        adapterAuto=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                arrAuto);
        //đưa Adapter vào AutoComplete
        autoTextNs.setAdapter(adapterAuto);
    }
    public void fakeData()
    {
        Student s1=new Student("1", "Đoàn Ái Nương", true, new Date(1980-1900, 2, 2), "TP. Hồ Chí Minh");
        Student s2=new Student("2", "Nguyễn Thùy Trang", true, new Date(1982-1900, 3, 3), "Lâm Đồng");
        Student s3=new Student("3", "Hoàng Văn Phúc", false, new Date(1970-1900, 4, 4), "Hà Nội");
        Student s4=new Student("4", "Đinh Hồng Lợi", false, new Date(1972-1900, 4, 4), "Bắc Giang");
        Student s5=new Student("5", "Nguyễn Hoàng Uyên", true, new Date(1970-1900, 4, 4), "Huê");
        arrSinhvien.add(s1);
        arrSinhvien.add(s2);
        arrSinhvien.add(s3);
        arrSinhvien.add(s4);
        arrSinhvien.add(s5);
        adapterSinhvien.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}