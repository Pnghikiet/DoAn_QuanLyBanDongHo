package BUS;

import DAO.PhanQuyenDAO;
import DTO.PhanQuyen;

import java.util.ArrayList;

public class PhanQuyenBUS {

    public static PhanQuyen quyenTK = null;
    private PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAO();
    private ArrayList<PhanQuyen> listPhanQuyen = null;

    public void docDanhSachNhomQuyen() {
        this.listPhanQuyen = phanQuyenDAO.getListQuyen();
    }

    public void kiemTraNhomQuyen(String quyen) {
        quyenTK = phanQuyenDAO.getQuyen(quyen);
    }

    public ArrayList<PhanQuyen> getListNhomQuyen() {
        if (listPhanQuyen == null)
            docDanhSachNhomQuyen();
        return this.listPhanQuyen;
    }

    public boolean suaNhomQuyen(String tenNhomQuyen, int nhapHang, int sanPham, int nhanVien, int khachHang, int thongKe) {
        PhanQuyen phanQuyen = new PhanQuyen(tenNhomQuyen, nhapHang, sanPham, nhanVien, khachHang, thongKe);
        return phanQuyenDAO.suaNhomQuyen(phanQuyen);
    }

    public boolean themNhomQuyen(String tenNhomQuyen) {
        PhanQuyen phanQuyen = new PhanQuyen(tenNhomQuyen, 0, 0, 0, 0, 0);
        boolean flag = phanQuyenDAO.themNhomQuyen(phanQuyen);
        return flag;
    }

    private boolean kiemTraTrungTenNhomQuyen(String tenNhomQuyen) {
        docDanhSachNhomQuyen();
        for (PhanQuyen q : listPhanQuyen) {
            if (q.getQuyen().equalsIgnoreCase(tenNhomQuyen))
                return true;
        }
        return false;
    }

    public boolean xoaNhomQuyen(String tenNhomQuyen) {
        boolean flag = phanQuyenDAO.xoaNhomQuyen(tenNhomQuyen);
        return flag;
    }
}
