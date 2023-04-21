package DTO;

import java.util.Date;

public class GiamGia {

    private int maGG;
    private String tenGG;
    private int phanTramGiam;
    private int dieuKien;
    private Date ngayBD;
    private Date ngayKT;

    public GiamGia() {
    }

    public GiamGia(int maGG, String tenGG, int phanTramGiam, int dieuKien, Date ngayBD, Date ngayKT) {
        this.maGG = maGG;
        this.tenGG = tenGG;
        this.phanTramGiam = phanTramGiam;
        this.dieuKien = dieuKien;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
    }

    public int getMaGG() {
        return maGG;
    }

    public void setMaGG(int maGG) {
        this.maGG = maGG;
    }

    public String getTenGG() {
        return tenGG;
    }

    public void setTenGG(String tenGG) {
        this.tenGG = tenGG;
    }

    public int getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(int phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public int getDieuKien() {
        return dieuKien;
    }

    public void setDieuKien(int dieuKien) {
        this.dieuKien = dieuKien;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }
}
