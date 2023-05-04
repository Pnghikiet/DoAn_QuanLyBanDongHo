package BUS;

import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DTO.NhanVien;

import java.util.ArrayList;

public class NhanVienBUS {

    private NhanVienDAO nvDAO = new NhanVienDAO();
    private TaiKhoanDAO tkDAO = new TaiKhoanDAO();
    
    private TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
    private ArrayList<NhanVien> listNhanVien = null;

    public NhanVienBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listNhanVien = nvDAO.getDanhSachNhanVien();
    }

    public ArrayList<NhanVien> getDanhSachNhanVien() {
        if (this.listNhanVien == null)
            docDanhSach();
        return this.listNhanVien;
    }

    public boolean themNhanVien(String ho, String ten, String gioiTinh, String ChucVu) {
        NhanVien nv = new NhanVien();
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(ChucVu);
        boolean flag = nvDAO.themNhanVien(nv);
        return flag;
    }

    public boolean suaNhanVien(String ma, String ho, String ten, String gioiTinh, String ChucVu) {
        NhanVien nv = new NhanVien();
        int maNV = Integer.parseInt(ma);
        nv.setMaNV(maNV);
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(ChucVu);
        boolean flag = nvDAO.suaNhanVien(nv);
        return flag;
    }

    public ArrayList<NhanVien> timKiemNhanVien(String tuKhoa) {
        String taiKhoan = "";
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        for (NhanVien nv : listNhanVien) {
            int trangThai = taiKhoanBUS.getTrangThai(nv.getMaNV() + "");
            if (trangThai == 0) {
                taiKhoan = "Khóa";
            }
            else if(trangThai == 1) {
                taiKhoan = "Hiệu lực";
            }
            else {
                taiKhoan = "Chưa có";
            }
            
            if (nv.getHo().toLowerCase().contains(tuKhoa) || nv.getTen().toLowerCase().contains(tuKhoa) ||
                    nv.getGioiTinh().toLowerCase().contains(tuKhoa) || nv.getChucVu().toLowerCase().contains(tuKhoa) || taiKhoan.toLowerCase().contains(tuKhoa)) {
                dsnv.add(nv);
            }
        }
        return dsnv;
    }

    public boolean xoaNhanVien(String ma) {
        boolean flagNV = false;
        int maNV = Integer.parseInt(ma);
        boolean flagTK = tkDAO.xoaVinhVienTaiKhoan(maNV);
        if(flagTK) {
            flagNV= nvDAO.xoaNhanVien(maNV);
        } else {
            flagNV= nvDAO.xoaNhanVien(maNV);
        }
        return flagNV;
    }

    public boolean nhapExcel(String ho, String ten, String gioiTinh, String ChucVu) {
        NhanVien nv = new NhanVien();
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(ChucVu);
        boolean flag = nvDAO.nhapNhanVienTuExcel(nv);
        return flag;
    }
    
    public boolean kiemTraTrungNhanVien(String ho, String ten, String gioitinh, String cv) {
        gioitinh = gioitinh.toLowerCase();
        ho = ho.toLowerCase();
        ten = ten.toLowerCase();
        cv = cv.toLowerCase();
        
        for (NhanVien NV : listNhanVien) {
            String hoNV = NV.getHo().toLowerCase();
            String tenNV = NV.getTen().toLowerCase();
            String gioitinhNV = NV.getGioiTinh().toLowerCase();
            String cvNV = NV.getChucVu().toLowerCase();
            
            if(hoNV.equals(ho) && tenNV.equals(ten) && gioitinhNV.equals(gioitinh) && cvNV.equals(cv)) {
                return true;
            }
        }
        return false;
    }

    public NhanVien getNhanVien(int MaNV) {
        return nvDAO.getNhanVien(MaNV);
    }
    
}
