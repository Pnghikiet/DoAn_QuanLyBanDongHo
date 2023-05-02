package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoan;

public class TaiKhoanBUS {

    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public String getTenDangNhapTheoMa(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.getTenDangNhapTheoMa(maNV);
    }

    public String getQuyenTheoMa(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.getQuyenTheoMa(maNV);
    }
    
    public boolean datLaiMatKhau(String ma, String tenDangNhap) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.datLaiMatKhau(maNV, tenDangNhap);
    }

    
    public boolean datLaiQuyen(String ma, String quyen) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.datLaiQuyen(maNV, quyen);
    }

    
    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        return taiKhoanDAO.kiemTraTrungTenDangNhap(tenDangNhap);
    }

    public boolean themTaiKhoan(String ma, String tenDangNhap, String Quyen) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.themTaiKhoan(maNV, tenDangNhap, Quyen);
    }

    public boolean khoaTaiKhoan(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.khoaTaiKhoan(maNV);
    }

    public boolean moKhoaTaiKhoan(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.moKhoaTaiKhoan(maNV);
}

    public boolean doiMatKhau(String matKhauCu, String matKhauMoi, String nhapLaiMatKhau) {
        return taiKhoanDAO.doiMatKhau(matKhauCu, matKhauMoi);
    }
    
    public int getTrangThai(String maNV) {
        int ma = Integer.parseInt(maNV);
        return taiKhoanDAO.getTrangThai(ma);
    }

}
