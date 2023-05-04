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
public class DoanhThuKhachHang {
    int maKH;
    String ho;
    String ten;
    int tongTienMua;

    public DoanhThuKhachHang(int maKH, String ho, String ten, int tongTienMua) {
        this.maKH = maKH;
        this.ho = ho;
        this.ten = ten;
        this.tongTienMua = tongTienMua;
    }

    public DoanhThuKhachHang() {
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
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

    public int getTongTienMua() {
        return tongTienMua;
    }

    public void setTongTienMua(int tongTienMua) {
        this.tongTienMua = tongTienMua;
    }
    
    
}
