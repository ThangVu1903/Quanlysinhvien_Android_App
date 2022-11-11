package com.example.appquanlysinhvien;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SinhVienActivity extends AppCompatActivity {
    List<SinhVien> sinhVienList;
    SinhVienAdapter sinhVienAdapter;
    ListView sinhVienLVAX;
    Button insertBT, updateBT;
    int position = 0;
    boolean longClick = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien);
        CreateOrOpenDatabase();
        Intent intent = getIntent();
        String maLop = intent.getStringExtra("maLop");
        sinhVienList = new ArrayList<>();
        SetDataSinhVienTableByMaLop(maLop);
        sinhVienAdapter = new SinhVienAdapter(SinhVienActivity.this, sinhVienList);
        sinhVienLVAX = findViewById(R.id.sinhVienLV);
        sinhVienLVAX.setAdapter(sinhVienAdapter);
        sinhVienLVAX.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                if(longClick==false){
                    Intent intent = new Intent(SinhVienActivity.this, DetailActivity.class);
                    SinhVien sinhVien = sinhVienList.get(position);
                    intent.putExtra("SinhVien", sinhVien);
                    startActivity(intent);
                }
                longClick = false;
            }
        });
        sinhVienLVAX.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                longClick = true;
                AlertDialog.Builder alert = new AlertDialog.Builder(SinhVienActivity.this);
                alert.setTitle("Xóa");
                alert.setMessage("Bạn có chắc chắn xóa không, hàng động này không thể hoàn tác lại?");
                alert.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SinhVien sinhVien = sinhVienList.get(position);
                        DeleteSinhVienTable(sinhVien.getMaSV());
                        SetDataSinhVienTableByMaLop(sinhVien.getMaLop());
                        sinhVienAdapter.notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
                return false;
            }
        });
        insertBT = findViewById(R.id.insertBT);
        insertBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(SinhVienActivity.this);
                dialog.setTitle("Chen du lieu");
                dialog.setContentView(R.layout.activity_dialog);
                dialog.show();
                EditText maSVET = dialog.findViewById(R.id.maSVET);
                EditText tenSVET = dialog.findViewById(R.id.tenSVET);
                EditText maLET = dialog.findViewById(R.id.maLopET);
                EditText namSinhET = dialog.findViewById(R.id.namSinhET);
                EditText toanET = dialog.findViewById(R.id.toanET);
                EditText lyET = dialog.findViewById(R.id.lyET);
                EditText hoaET = dialog.findViewById(R.id.hoaET);
                Button okBT = dialog.findViewById(R.id.okBT);
                Button cancelBT = dialog.findViewById(R.id.cancelBT);
                okBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InsertSinhVienTable(maSVET.getText().toString(),
                                            tenSVET.getText().toString(),
                                            maLET.getText().toString(),
                                            namSinhET.getText().toString(),
                                            Float.parseFloat(toanET.getText().toString()),
                                            Float.parseFloat(lyET.getText().toString()),
                                            Float.parseFloat(hoaET.getText().toString()));
                        SinhVien sinhVien = sinhVienList.get(position);
                        SetDataSinhVienTableByMaLop(sinhVien.getMaLop());
                        sinhVienAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                cancelBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });
        updateBT = findViewById(R.id.updateBT);
        updateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(SinhVienActivity.this);
                dialog.setTitle("Chen du lieu");
                dialog.setContentView(R.layout.activity_dialog);
                dialog.show();
                EditText maSVET = dialog.findViewById(R.id.maSVET);
                EditText tenSVET = dialog.findViewById(R.id.tenSVET);
                EditText maLET = dialog.findViewById(R.id.maLopET);
                EditText namSinhET = dialog.findViewById(R.id.namSinhET);
                EditText toanET = dialog.findViewById(R.id.toanET);
                EditText lyET = dialog.findViewById(R.id.lyET);
                EditText hoaET = dialog.findViewById(R.id.hoaET);
                Button okBT = dialog.findViewById(R.id.okBT);
                Button cancelBT = dialog.findViewById(R.id.cancelBT);
                SinhVien sinhVien = sinhVienList.get(position);
                maSVET.setText(sinhVien.getMaSV());
                tenSVET.setText(sinhVien.getTenSV());
                maLET.setText(sinhVien.getMaLop());
                namSinhET.setText(sinhVien.getNamSinh());
                toanET.setText(String.valueOf(sinhVien.getToan()));
                lyET.setText(String.valueOf(sinhVien.getLy()));
                hoaET.setText(String.valueOf(sinhVien.getHoa()));

                okBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UpdateSinhVienTable(
                                            maSVET.getText().toString(),
                                            tenSVET.getText().toString(),
                                            maLET.getText().toString(),
                                            namSinhET.getText().toString(),
                                            Float.parseFloat(toanET.getText().toString()),
                                            Float.parseFloat(lyET.getText().toString()),
                                            Float.parseFloat(hoaET.getText().toString())
                        );
                        SetDataSinhVienTableByMaLop(sinhVien.getMaLop());
                        sinhVienAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                cancelBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });

    }
    public void SetDataSinhVienTableByMaLop(String maLop){
        sinhVienList.clear();
        try (Cursor cursor = database.query("SinhVien", null, "maLop = ?", new String[]{maLop}, null, null, null)) {
            cursor.moveToFirst();
            String data = "";
            while (cursor.isAfterLast() == false) {
                sinhVienList.add(new SinhVien   (   cursor.getString(0),
                                                    cursor.getString(1),
                                                    cursor.getString(2),
                                                    cursor.getString(3),
                                                    cursor.getFloat(4),
                                                    cursor.getFloat(5),
                                                    cursor.getFloat(6)
                                                )
                                );
                cursor.moveToNext();
            }
        }
    }
    public SQLiteDatabase database;
    public void CreateOrOpenDatabase(){
        database = openOrCreateDatabase("QuanLySinhVien.db",MODE_PRIVATE,null);
        System.out.println("Create database successful");
    }
    public void InsertSinhVienTable(String maSV, String tenSV, String maLop, String namSinh, float toan, float ly, float hoa){
        ContentValues values = new ContentValues();
        values.put("maSV", maSV);
        values.put("tenSV", tenSV);
        values.put("maLop", maLop);
        values.put("namSinh", namSinh);
        values.put("toan",toan);
        values.put("ly",ly);
        values.put("hoa",hoa);
        database.insert("SinhVien", null, values);
        System.out.println("Insert Sinh Vien Table");
    }
    public void UpdateSinhVienTable(String maSV, String tenSV, String maLop, String namSinh, float toan, float ly, float hoa){
        ContentValues values = new ContentValues();
        values.put("tenSV", tenSV);
        values.put("maLop", maLop);
        values.put("namSinh", namSinh);
        values.put("toan", toan);
        values.put("ly",ly);
        values.put("hoa",hoa);
        database.update("SinhVien", values, "maSV = ?", new String[]{maSV});
        System.out.println("Update Sinh Vien Table");
    }
    public void DeleteSinhVienTable(String maSV){
        database.delete("SinhVien","maSV = ?", new String[]{maSV});
        System.out.println("Delete a row of Sinh Vien Table");
    }
}
