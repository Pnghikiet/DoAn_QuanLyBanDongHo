package BUS;

import DAO.GiamGiaDAO;
import DTO.GiamGia;

import java.util.ArrayList;
import java.util.Date;


public class GiamGiaBUS {

    private ArrayList<GiamGia> listGiamGia = null;
    private GiamGiaDAO giamGiaDAO = new GiamGiaDAO();

    public GiamGiaBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listGiamGia = giamGiaDAO.getDanhSachMaGiam();
    }

    public ArrayList<GiamGia> getDanhSachGiamGia() {
        if (this.listGiamGia == null)
            docDanhSach();
        return this.listGiamGia;
    }

    public boolean themMaGiam(String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        boolean flag = false;
        try {
            int phanTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            GiamGia gg = new GiamGia();
            gg.setTenGG(ten);
            gg.setPhanTramGiam(phanTramGiam);
            gg.setDieuKien(dieuKienGiam);
            gg.setNgayBD(ngayBD);
            gg.setNgayKT(ngayKT);

            flag = giamGiaDAO.themMaGiam(gg);
        } catch (Exception e) {
            return false;
        }
        return flag;
    }

    public boolean suaMaGiam(String ma, String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        boolean flag = false;
        try {
            int maGiam = Integer.parseInt(ma);
            int phanTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            GiamGia gg = new GiamGia();
            gg.setMaGG(maGiam);
            gg.setTenGG(ten);
            gg.setPhanTramGiam(phanTramGiam);
            gg.setDieuKien(dieuKienGiam);
            gg.setNgayBD(ngayBD);
            gg.setNgayKT(ngayKT);

            flag = giamGiaDAO.suaMaGiam(gg);
        } catch (NumberFormatException e) {
            return false;
        }
                return flag;
    }
    

    public boolean xoaMaGiam(String ma) {
        boolean flag = false;
        try {
            int maGiam = Integer.parseInt(ma);

            flag = giamGiaDAO.xoaMaGiam(maGiam);
        } catch (NumberFormatException e) {
            return false;
        }
        return flag;
    }
    
    public ArrayList<GiamGia> timKiemGGTheoTG(Date DateInput) {
        java.sql.Date dateSql = new java.sql.Date(DateInput.getTime());
        return giamGiaDAO.timKiemGGTheoTG(dateSql);
    }
    
    
    public boolean kiemTraHieuLucKhuyenMai(int ma) {
        boolean flag = false;
        try {
            flag = giamGiaDAO.kiemTraHieuLucKhuyenMai(ma);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return flag;
    }
    

    public ArrayList<GiamGia> timKiemGiamGia(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<GiamGia> dsgg = new ArrayList<>();
        for (GiamGia gg : listGiamGia) {
            String ten = gg.getTenGG().toLowerCase();
            String phanTram = gg.getPhanTramGiam() + "";
            String dieuKien = gg.getDieuKien() + "";
            String trangThai = "";
            boolean flag = kiemTraHieuLucKhuyenMai(gg.getMaGG());
            if(flag) {
                trangThai = "Hiệu lực";
            } else {
                trangThai = "Không hiệu lực";
            }
            
            if (ten.contains(tuKhoa) || phanTram.contains(tuKhoa) || dieuKien.contains(tuKhoa) || trangThai.contains(tuKhoa)) {
                dsgg.add(gg);
            }
        }
        return dsgg;
    }
        
    public boolean kiemTraTrungGiamGia(String ten, String phanTram, String dieuKien, java.util.Date ngayBD, java.util.Date ngayKT) {
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        ten = ten.trim().toLowerCase();
        dieuKien = dieuKien.trim().toLowerCase();
        phanTram = phanTram.trim().toLowerCase();
        for(GiamGia gg : listGiamGia) {
            String tenGG = gg.getTenGG().toLowerCase();
            String pt = gg.getPhanTramGiam()+"";
            String dk = gg.getDieuKien()+"";
            java.util.Date start = gg.getNgayBD();
            java.util.Date end = gg.getNgayKT();
            
            int resultS = ngayBD.compareTo(start);
            int resultE = ngayKT.compareTo(end);
            
            if(ten.equals(tenGG) && phanTram.equals(pt) && dieuKien.equals(dk) && resultS == 0 && resultE == 0) {
                return true;
            }
        }
        return false;
    }
    
    public GiamGia getGiamGia(int ma) {
        return giamGiaDAO.getGiamGia(ma);
    }
    
}

