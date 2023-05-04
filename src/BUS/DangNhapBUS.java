package BUS;

import DAO.DangNhapDAO;
import DTO.PhanQuyen;
import DTO.TaiKhoan;
import Customs.MyDialog;
import DAO.TaiKhoanDAO;

import java.io.*;

public class DangNhapBUS {
    public static TaiKhoan taiKhoanLogin = null;
    public TaiKhoanDAO tkDAO = new TaiKhoanDAO();
    
    public TaiKhoan getTaiKhoanDangNhap(String user, String password, boolean selected) {
        TaiKhoan tk = new TaiKhoan();
        tk.setTaiKhoan(user);
        tk.setMatKhau(password);

        DangNhapDAO dangNhapDAO = new DangNhapDAO();
        TaiKhoan account = dangNhapDAO.dangNhap(tk);
        taiKhoanLogin = account;
        return account;
    }

    public String getTaiKhoanGhiNho() {
        try {
            FileInputStream fis = new FileInputStream("remember.dat");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            br.close();
            return line;
        }catch (Exception e) {
        }
        return "";
    }

    public boolean kiemTraDangNhap(String user, String password) {
        user = user.replaceAll("\\s+", "");
        password = password.replaceAll("\\s+", "");
        boolean result = false;

        TaiKhoan tk = new TaiKhoan();
        tk.setTaiKhoan(user);
        tk.setMatKhau(password);

        DangNhapDAO dangNhapDAO = new DangNhapDAO();
        TaiKhoan account = dangNhapDAO.dangNhap(tk);

       if (account != null) {
            result =  true;
        }
        return result;
    }

        
    public TaiKhoan getTaiKhoan(String taikhoan, String matkhau) {
        return tkDAO.getTaiKhoang(taikhoan, matkhau);
    }
    
    public boolean kiemTraTaiKhoan(String taikhoan) {
        return tkDAO.kiemTraTaiKhoan(taikhoan);
    }
    
    public boolean kiemTraKhoanTrang(String taikhoan) {
        return taikhoan.contains(" ");
    }
}
