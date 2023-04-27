package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHang;

import java.util.ArrayList;

public class KhachHangBUS {

    private ArrayList<KhachHang> listKhachHang = null;
    private final KhachHangDAO khachHangDAO = new KhachHangDAO();

    public void docDanhSach() {
        this.listKhachHang = khachHangDAO.getListKhachHang();
    }

    public ArrayList<KhachHang> getListKhachHang() {
        if (listKhachHang == null)
            docDanhSach();
        return listKhachHang;
    }
    
    public void docDanhSachBX() {
        this.listKhachHang = khachHangDAO.getListKhachHangBiXoa();
    }

    public ArrayList<KhachHang> getListKhachHangBX() {
        if (listKhachHang == null)
            docDanhSachBX();
        return listKhachHang;
    }

    public ArrayList<KhachHang> timKiemKhachHang(int min, int max) {
        ArrayList<KhachHang> dskh = new ArrayList<>();
        for (KhachHang kh : listKhachHang) {
            if (kh.getTongChiTieu() >= min && kh.getTongChiTieu() <= max) {
                dskh.add(kh);
            }
        }
        return dskh;
    }

    public ArrayList<KhachHang> timKiemKhachHang(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<KhachHang> dskh = new ArrayList<>();
        for (KhachHang kh : listKhachHang) {
            String ho = kh.getHo().toLowerCase();
            String ten = kh.getTen().toLowerCase();
            String gioiTinh = kh.getGioiTinh().toLowerCase();
            String sdt = kh.getSoDienThoai();
            if (ho.contains(tuKhoa) || ten.contains(tuKhoa) || gioiTinh.contains(tuKhoa) || sdt.contains(tuKhoa)) {
                dskh.add(kh);
            }
        }
        return dskh;
    }
    
    public boolean kiemTraTrungKhachHang(String ho, String ten, String gioitinh, String sdt) {
        gioitinh = gioitinh.toLowerCase();
        ho = ho.toLowerCase();
        ten = ten.toLowerCase();
        sdt = sdt.toLowerCase();
        for (KhachHang kh : listKhachHang) {
            String hoKH = kh.getHo().toLowerCase();
            String tenKH = kh.getTen().toLowerCase();
            String gioitinhKH = kh.getGioiTinh().toLowerCase();
            String sdtKH = kh.getSoDienThoai();
            
            if(hoKH.equals(ho) && tenKH.equals(ten) && gioitinhKH.equals(gioitinh) && sdtKH.equals(sdt)) {
                return true;
            }
        }
        return false;
    }

    public boolean themKhachHang(String ho, String ten, String gioiTinh, String sdt) {
        KhachHang kh = new KhachHang();
        kh.setHo(ho);
        kh.setTen(ten);
        kh.setGioiTinh(gioiTinh);
        kh.setSoDienThoai(sdt);
        kh.setTongChiTieu(0);
        boolean flag = khachHangDAO.themKhachHang(kh);
        return flag;
    }

    public boolean suaKhachHang(String ma, String ho, String ten, String gioiTinh, String sdt) {
        KhachHang kh = new KhachHang();
        kh.setHo(ho);
        kh.setTen(ten);
        kh.setGioiTinh(gioiTinh);
        kh.setSoDienThoai(sdt);
        boolean flag = khachHangDAO.suaKhachHang(Integer.parseInt(ma), kh);
        return flag;
    }

    public boolean xoaKhachHang(String ma) {
        boolean flag = false;
        try {
            int maKH = Integer.parseInt(ma);
            flag = khachHangDAO.xoaKhachHang(maKH);
        } catch (Exception e) {
           return false;
        }
        return flag;
    }
    

    public boolean xoaKhachHangVinhVien(String ma) {
        boolean flag = false;
        try {
            int maKH = Integer.parseInt(ma);
            flag = khachHangDAO.xoaKhachHangVinhVien(maKH);
        } catch (Exception e) {
           return false;
        }
        return flag;
    }
    

    public boolean khoiPhucKhachHang(String ma) {
        boolean flag = false;
        try {
            int maKH = Integer.parseInt(ma);
            flag = khachHangDAO.khoiPhucKhachHang(maKH);
        } catch (Exception e) {
           return false;
        }
        return flag;
    }
    
}
