package com.example.appquanlysinhvien;

import java.io.Serializable;

public class SinhVien implements Serializable {
    String maSV, tenSV, maLop, namSinh;
    float toan, ly, hoa;

    public SinhVien(String maSV, String tenSV, String maLop, String namSinh, float toan, float ly, float hoa) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.maLop = maLop;
        this.namSinh = namSinh;
        this.toan = toan;
        this.ly = ly;
        this.hoa = hoa;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public float getToan() {
        return toan;
    }

    public void setToan(float toan) {
        this.toan = toan;
    }

    public float getLy() {
        return ly;
    }

    public void setLy(float ly) {
        this.ly = ly;
    }

    public float getHoa() {
        return hoa;
    }

    public void setHoa(float hoa) {
        this.hoa = hoa;
    }

    public float getDiemTrungBinh(){
        return  (toan + ly + hoa)/3;
    }
}
