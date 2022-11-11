package com.example.appquanlysinhvien;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    Activity activity;
    List<SinhVien> sinhVienList;

    public SinhVienAdapter(Activity activity, List<SinhVien> sinhVienList) {
        this.activity = activity;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.customor_sinhvien, null);
        TextView maSVTVAX = view.findViewById(R.id.maSVTV);
        TextView tenSVTVAX = view.findViewById(R.id.tenSVTV);
        TextView maLopTVAX = view.findViewById(R.id.maLopSVTV);
        SinhVien sinhVien = sinhVienList.get(i);
        maSVTVAX.setText(sinhVien.getMaSV());
        tenSVTVAX.setText(sinhVien.getTenSV());
        maLopTVAX.setText(sinhVien.getMaLop());
        return view;
    }
}
