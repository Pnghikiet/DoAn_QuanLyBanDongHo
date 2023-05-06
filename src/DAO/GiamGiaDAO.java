package DAO;

import DTO.GiamGia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

public class GiamGiaDAO {


    public ArrayList<GiamGia> getDanhSachMaGiam() {
        try {
            String sql = "SELECT * FROM giamgia";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<GiamGia> dsgg = new ArrayList<>();
            while (rs.next()) {
                GiamGia gg = new GiamGia();
                gg.setMaGG(rs.getInt(1));
                gg.setTenGG(rs.getString(2));
                gg.setPhanTramGiam(rs.getInt(3));
                gg.setDieuKien(rs.getInt(4));
                gg.setNgayBD(rs.getDate(5));
                gg.setNgayKT(rs.getDate(6));
                dsgg.add(gg);
            }
            return dsgg;
        } catch (Exception e) {
        }
        return null;
    }

    public boolean themMaGiam(GiamGia gg) {
        try {
            String sql = "INSERT INTO giamgia(TenGG, PhanTramGiam, DieuKien, NgayBD, NgayKT) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, gg.getTenGG());
            pre.setInt(2, gg.getPhanTramGiam());
            pre.setInt(3, gg.getDieuKien());

            pre.setTimestamp(4, new java.sql.Timestamp(gg.getNgayBD().getTime()));
            pre.setTimestamp(5, new java.sql.Timestamp(gg.getNgayKT().getTime()));

            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean suaMaGiam(GiamGia gg) {
        try {
            String sql = "UPDATE giamgia SET TenGG=?, PhanTramGiam=?, DieuKien=?, NgayBD=?, NgayKT=? WHERE MaGG=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, gg.getTenGG());
            pre.setInt(2, gg.getPhanTramGiam());
            pre.setInt(3, gg.getDieuKien());

            pre.setTimestamp(4, new java.sql.Timestamp(gg.getNgayBD().getTime()));
            pre.setTimestamp(5, new java.sql.Timestamp(gg.getNgayKT().getTime()));

            pre.setInt(6, gg.getMaGG());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public boolean xoaMaGiam(int maGG) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM giamgia WHERE MaGG=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maGG);

            flag =  pre.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
        return flag;
    }
    
    
    public ArrayList<GiamGia> timKiemGGTheoTG(Date dateInput) {
        try {
            String sql = "SELECT * FROM giamgia WHERE NgayKT >= ?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setDate(1, dateInput);
            ResultSet rs = pre.executeQuery();

            ArrayList<GiamGia> dsgg = new ArrayList<>();

            while (rs.next()) {
                GiamGia gg = new GiamGia();
                gg.setMaGG(rs.getInt(1));
                gg.setTenGG(rs.getString(2));
                gg.setPhanTramGiam(rs.getInt(3));
                gg.setDieuKien(rs.getInt(4));
                gg.setNgayBD(rs.getDate(5));
                gg.setNgayKT(rs.getDate(6));
                dsgg.add(gg);
            }
            return dsgg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public boolean kiemTraHieuLucKhuyenMai(int maGG) {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM giamgia WHERE NgayKT >= DATE(NOW()) AND NgayBD <= DATE(NOW()) AND MaGG = ?";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, maGG);
            ResultSet rs = prep.executeQuery();
            if(rs.next())
                flag = true;
        } catch (SQLException e) {
            return false;
        }
        
        return flag;
    }
           
    public GiamGia getGiamGia(int ma) {
        try {
            String sql = "SELECT * FROM giamgia WHERE MaGG = ?";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, ma);
            ResultSet rs = prep.executeQuery();
            GiamGia gg = new GiamGia();
            if(rs.next()) {
                gg.setMaGG(rs.getInt(1));
                gg.setTenGG(rs.getString(2));
                gg.setPhanTramGiam(rs.getInt(3));
                gg.setDieuKien(rs.getInt(5));
                gg.setNgayBD(rs.getDate(5));
                gg.setNgayKT(rs.getDate(6));
            }
            return gg;
        } catch (SQLException e) {
        }
        return null;
    }
    
}
