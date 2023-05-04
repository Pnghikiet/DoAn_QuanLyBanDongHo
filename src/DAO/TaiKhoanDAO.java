package DAO;

import BUS.DangNhapBUS;
import DTO.TaiKhoan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TaiKhoanDAO {

    public boolean themTaiKhoan(int maNV, String tenDangNhap, String quyen) {
        boolean flag = false;
        try {
            String sql = "INSERT INTO taikhoan VALUES (?, ?, ?, ?,1)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            pre.setString(2, tenDangNhap);
            pre.setString(3, tenDangNhap);
            pre.setString(4, quyen);
            flag =  pre.executeUpdate() > 0;
            System.out.println("sql chạy tốt");
        } catch (SQLException e) {
            return false;
        }
        return flag;
    }

    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        try {
            String sql = "SELECT * FROM TaiKhoan WHERE TaiKhoan = '" + tenDangNhap + "'";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getTenDangNhapTheoMa(int maNV) {
        try {
            String sql = "SELECT TaiKhoan FROM TaiKhoan WHERE MaNV=" + maNV;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public boolean datLaiMatKhau(int maNV, String tenDangNhap) {
        boolean flag = false;
        try {
            String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, tenDangNhap);
            pre.setInt(2, maNV);
            flag = pre.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
        return flag;
    }

    public boolean datLaiQuyen(int maNV, String quyen) {
        boolean flag = false;
        try {
            String sql = "UPDATE TaiKhoan SET Quyen=? WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, quyen);
            pre.setInt(2, maNV);
            flag = pre.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
        return flag;
    }

    public String getQuyenTheoMa(int maNV) {
        try {
            String sql = "SELECT Quyen FROM TaiKhoan WHERE MaNV=" + maNV;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public boolean khoaTaiKhoan(int maNV) {
        try {
            String sql = "UPDATE TaiKhoan SET TrangThai=0 WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean moKhoaTaiKhoan(int maNV) {
        try {
            String sql = "UPDATE TaiKhoan SET TrangThai=1 WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean xoaVinhVienTaiKhoan(int maNV) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM TaiKhoan WHERE MaNV=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            flag = pre.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
        return flag;
    }
    
    public boolean doiMatKhau(String matKhauCu, String matKhauMoi) {
        try {
            String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE MaNV=? AND MatKhau=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, matKhauMoi);
            pre.setInt(2, DangNhapBUS.taiKhoanLogin.getMaNV());
            pre.setString(3, matKhauCu);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public int getTrangThai(int ma) {
        try {
            String sql = "SELECT TrangThai FROM TaiKhoan WHERE MaNV=" + ma;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }
    
        
    public boolean kiemTraTaiKhoan(String taikhoan) {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM TaiKhoan WHERE TaiKhoan=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, taikhoan);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                flag = true;
            }
        } catch (SQLException e) {
            return false;
        }
        return flag;
    }
    
    public TaiKhoan getTaiKhoang(String tk, String mk) {
        try {
            String sql = "SELECT * FROM TaiKhoan WHERE TaiKhoan = ? AND MatKhau = ?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, tk);
            pre.setString(2, mk);
            
            ResultSet rs = pre.executeQuery();
            TaiKhoan taikhoan = new TaiKhoan();
            if(rs.next()) {
                taikhoan.setMaNV(rs.getInt(1));
                taikhoan.setTaiKhoan(rs.getString(2));
                taikhoan.setMatKhau(rs.getString(3));
                taikhoan.setQuyen(rs.getString(4));
            }
            return taikhoan;
        } catch (Exception e) {
        }
        return null;
    }
}
