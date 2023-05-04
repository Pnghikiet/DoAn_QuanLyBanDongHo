
package DTO;

public class DoanhThuQuy {
    int nam;
    int DTQuy1;
    int DTQuy2;
    int DTQuy3;
    int DTQuy4;

    public DoanhThuQuy(int nam, int DTQuy1, int DTQuy2, int DTQuy3, int DTQuy4) {
        this.nam = nam;
        this.DTQuy1 = DTQuy1;
        this.DTQuy2 = DTQuy2;
        this.DTQuy3 = DTQuy3;
        this.DTQuy4 = DTQuy4;
    }

    public DoanhThuQuy() {
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public int getDTQuy1() {
        return DTQuy1;
    }

    public void setDTQuy1(int DTQuy1) {
        this.DTQuy1 = DTQuy1;
    }

    public int getDTQuy2() {
        return DTQuy2;
    }

    public void setDTQuy2(int DTQuy2) {
        this.DTQuy2 = DTQuy2;
    }

    public int getDTQuy3() {
        return DTQuy3;
    }

    public void setDTQuy3(int DTQuy3) {
        this.DTQuy3 = DTQuy3;
    }

    public int getDTQuy4() {
        return DTQuy4;
    }

    public void setDTQuy4(int DTQuy4) {
        this.DTQuy4 = DTQuy4;
    }
    
    
}
