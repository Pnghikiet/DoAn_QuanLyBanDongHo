package DTO;

public class TaiKhoan {

    private int maNV;
    private String taiKhoan;
    private String matKhau;
    private String Quyen;
    public TaiKhoan() {
    }

    public TaiKhoan(int maNV, String taiKhoan, String matKhau, String quyen) {
        this.maNV = maNV;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.Quyen = quyen;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyen() {
        return Quyen;
    }

    public void setQuyen(String Quyen) {
        this.Quyen = Quyen;
    }  
}
