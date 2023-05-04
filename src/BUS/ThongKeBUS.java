/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ThongKeDAO;
import DTO.HoaDon;
import DTO.DoanhThuNhanVien;
import DTO.DoanhThuKhachHang;
import DTO.DoanhThuQuy;
import DTO.SanPham;
import DTO.GiamGia;
import java.text.SimpleDateFormat;

import BUS.GiamGiaBUS;
import DTO.KhachHang;
import DTO.NhanVien;

import java.util.Date;

import java.util.ArrayList;

/**
 * @author User
 */
public class ThongKeBUS {

    public ThongKeDAO thongKeDAO = new ThongKeDAO();

    public int getSoLuongSP() {
        return thongKeDAO.getSoLuongSP();
    }
    
    public int getSoLuongNhanVien() {
        return thongKeDAO.getSoLuongNhanVien();
    }
    
    public int getSoLuongKhachHang() {
        return thongKeDAO.getSoLuongKhachHang();
    }
    
    public ArrayList<HoaDon> getDanhSachHDTheoNgay(Date date) {
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        return thongKeDAO.getDanhSachHDTheoNgay(dateSql);
    }
    
    public ArrayList<HoaDon> timKiemHDTrongDanhSachHDTheoNgay(String tuKhoa, ArrayList<HoaDon> dshd) {
        tuKhoa = tuKhoa.trim().toLowerCase();
        ArrayList<HoaDon> dshdTK = new ArrayList<>();
        SimpleDateFormat spdf = new SimpleDateFormat("dd/MM/yyyy");
        for(HoaDon hd : dshd) {
            String maHD = hd.getMaHD()+"";
            String hoTenNV = layHoTenNhanVien(hd.getMaNV());
            String hoTenKH  = layHoTenKhachHang(hd.getMaKH());
            String ngayLap = spdf.format(hd.getNgayLap());
            String tongTien = hd.getTongTien()+"";
            String maGG = "";
            GiamGiaBUS giamGiaBUS = new GiamGiaBUS();
            
            ArrayList<GiamGia> dsgg = giamGiaBUS.getDanhSachGiamGia();
            
            for(GiamGia gg : dsgg) {
                if(hd.getMaGG() == gg.getMaGG()) {
                    maGG = gg.getTenGG();
                }
            }
            
            if(maHD.toLowerCase().contains(tuKhoa) || hoTenNV.toLowerCase().contains(tuKhoa) || hoTenKH.toLowerCase().contains(tuKhoa) || ngayLap.toLowerCase().contains(tuKhoa) || tongTien.toLowerCase().contains(tuKhoa) || maGG.toLowerCase().contains(tuKhoa)) {
                dshdTK.add(hd);
            }
        }
        return dshdTK;
    }
    
    public int tinhDoanhThuTheoNgay(Date date) {
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        return thongKeDAO.tinhDoanhThuTheoNgay(dateSql);
    }
    
    public double tinhDoanhThuThang(int thang, int nam) {
        return thongKeDAO.tinhDoanhThuThang(thang, nam);
    }
    
    public DoanhThuQuy tinhDoanhThuQuy(int nam){
        return thongKeDAO.tinhDoanhThuQuy(nam);
    }
    
    public int tinhDoanhThuTheoNam(int nam) {
        return thongKeDAO.tinhDoanhThuTheoNam(nam);
    }
    
    public ArrayList<SanPham> getDanhSachSPTonHang() {
        return thongKeDAO.getDanhSachSPTonHang();
    }
    
    public ArrayList<SanPham> getDanhSachSPBanTheoNgay(Date date) {
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        return thongKeDAO.getDanhSachSPBanTheoNgay(dateSql);
    }
    
    public ArrayList<SanPham> getDanhSachSPBanTheoThang(int thang, int nam) {
        return thongKeDAO.getDanhSachSPBanTheoThang(thang, nam);
    }
    
    public ArrayList<SanPham> getDanhSachSPBanTheoNam(int nam) {
        return thongKeDAO.getDanhSachSPBanTheoNam(nam);
    }
    
    public ArrayList<SanPham> timKiemSanPham(String tuKhoa, ArrayList<SanPham> dssp) {
        tuKhoa = tuKhoa.trim().toLowerCase();
        ArrayList<SanPham> dstk = new ArrayList<>();
        
        for(SanPham sp : dssp) {
            String maSP =sp.getMaSP()+"";
            String tenSP =sp.getTenSP().toLowerCase();
            String soLuong =sp.getSoLuong()+"";
            
            if(maSP.contains(tuKhoa) || tenSP.contains(tuKhoa) || soLuong.contains(tuKhoa)) {
                dstk.add(sp);
            } 
        }
        return dstk;
    }
    
    public ArrayList<DoanhThuNhanVien> getDanhSachDTNVTheoNgay(Date date) {
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        return thongKeDAO.getDanhSachDTNVTheoNgay(dateSql);
    }
    
    public ArrayList<DoanhThuNhanVien> getDanhSachDTNVTheoThang(int thang, int nam) {
        return thongKeDAO.getDanhSachDTNVTheoThang(thang, nam);
    }
    
    public ArrayList<DoanhThuNhanVien> getDanhSachDTNVTheoNam(int nam) {
        return thongKeDAO.getDanhSachDTNVTheoNam(nam);
    }
    
    public ArrayList<DoanhThuNhanVien> timKiemNhanVien(String tuKhoa, ArrayList<DoanhThuNhanVien> dsnv) {
        tuKhoa = tuKhoa.trim().toLowerCase();
        ArrayList<DoanhThuNhanVien> dstk = new ArrayList<>();
        
        for(DoanhThuNhanVien nv : dsnv) {
            String maNV =nv.getMaNV()+"";
            String ho =nv.getHo().toLowerCase();
            String ten =nv.getTen().toLowerCase();
            String tongTienBan = nv.getTongTienBan()+"";
            
            if(maNV.contains(tuKhoa) || ho.contains(tuKhoa) || ten.contains(tuKhoa) || tongTienBan.contains(tuKhoa)) {
                dstk.add(nv);
            } 
        }
        return dstk;
    }
    
    public ArrayList<DoanhThuKhachHang> getDanhSachDTKHTheoNgay(Date date) {
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        return thongKeDAO.getDanhSachDTKHTheoNgay(dateSql);
    }
    
    public ArrayList<DoanhThuKhachHang> getDanhSachDTKHTheoThang(int thang, int nam) {
        return thongKeDAO.getDanhSachDTKHTheoThang(thang, nam);
    }
    
    public ArrayList<DoanhThuKhachHang> getDanhSachDTKHTheoNam(int nam) {
        return thongKeDAO.getDanhSachDTKHTheoNam(nam);
    }
    
    public ArrayList<Integer> getDSNamHD() {
        return thongKeDAO.getDSNamHD();
    }
    
    public ArrayList<DoanhThuKhachHang> timKiemKhachHang(String tuKhoa, ArrayList<DoanhThuKhachHang> dskh) {
        tuKhoa = tuKhoa.trim().toLowerCase();
        ArrayList<DoanhThuKhachHang> dstk = new ArrayList<>();
        
        for(DoanhThuKhachHang kh : dskh) {
            String maKH =kh.getMaKH()+"";
            String ho =kh.getHo().toLowerCase();
            String ten =kh.getTen().toLowerCase();
            String tongTienMua = kh.getTongTienMua()+"";
            
            if(maKH.contains(tuKhoa) || ho.contains(tuKhoa) || ten.contains(tuKhoa) || tongTienMua.contains(tuKhoa)) {
                dstk.add(kh);
            } 
        }
        return dstk;
    }
    
        
    public String layHoTenNhanVien(int ma) {
            NhanVienBUS nvBUS = new NhanVienBUS();
            String hoTen = "";
            NhanVien nv = nvBUS.getNhanVien(ma);
            try {
            hoTen = nv.getHo() + " " + nv.getTen();
        } catch (Exception e) {
        }
            return hoTen;
        }
        
    
    public String layHoTenKhachHang(int ma) {
            KhachHangBUS khBUS = new KhachHangBUS();
            String hoTen = "";
            KhachHang kh = khBUS.getKhachHang(ma);
            try {
            hoTen = kh.getHo() + " " + kh.getTen();
        } catch (Exception e) {
        }
            return hoTen;
        }
}
