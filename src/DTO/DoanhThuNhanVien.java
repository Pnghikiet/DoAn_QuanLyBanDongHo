/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ThanhTam
 */
public class DoanhThuNhanVien {
    int maNV;
    String ho;
    String ten;
    int tongTienBan;

    public DoanhThuNhanVien(int maNV, String ho, String ten, int tongTienBan) {
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
        this.tongTienBan = tongTienBan;
    }

    public DoanhThuNhanVien() {
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTongTienBan() {
        return tongTienBan;
    }

    public void setTongTienBan(int tongTienBan) {
        this.tongTienBan = tongTienBan;
    }
    
    
}
