package com.example.appquanlysinhvien;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
        TextView maSVTVAX, tenSVTVAX, maLOPSVTVAX;
        TextView namSinhTVAX;
        TextView toanTVAX;
        TextView hoaTVAX;
        TextView lyTVAX ;
        TextView dtbTVAX ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien_detail);
        Intent intent = getIntent();
        SinhVien sinhVien= (SinhVien) intent.getSerializableExtra("SinhVien");
        AnhXa();
        maSVTVAX.setText(sinhVien.getMaSV());
        tenSVTVAX.setText(sinhVien.getTenSV());
        maLOPSVTVAX.setText(sinhVien.getMaLop());
        namSinhTVAX.setText(sinhVien.getNamSinh());
        toanTVAX.setText(String.valueOf(sinhVien.getToan()));
        lyTVAX.setText(String.valueOf(sinhVien.getLy()));
        hoaTVAX.setText(String.valueOf(sinhVien.getHoa()));
        dtbTVAX.setText(String.valueOf(sinhVien.getDiemTrungBinh()));
    }
    public void AnhXa(){
        maSVTVAX = findViewById(R.id.maDetailSVTV);
        tenSVTVAX = findViewById(R.id.tenDetailSVTV);
        maLOPSVTVAX = findViewById(R.id.maLopDetailSVTV);
        namSinhTVAX = findViewById(R.id.namSinhTV);
        toanTVAX = findViewById(R.id.toanTV);
        hoaTVAX = findViewById(R.id.hoaTV);
        lyTVAX = findViewById(R.id.lyTV);
        dtbTVAX = findViewById(R.id.diemTBTV);
    }
}
