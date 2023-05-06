package DAO;

import DTO.SanPham;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SanPhamDAO {

    public ArrayList<SanPham> getListSanPham() {
        try {
            String sql = "SELECT * FROM sanpham";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMaLoai(rs.getInt(3));
                sp.setMaNCC(rs.getInt(4));
                sp.setSoLuong(rs.getInt(5));
                sp.setHinhAnh(rs.getString(6));
                sp.setDonGia(rs.getInt(7));
                sp.setMoTa(rs.getString(8));

                dssp.add(sp);
            }
            return dssp;
        } catch (SQLException e) {
        }

        return null;
    }

    public SanPham getSanPham(int ma) {
        try {
            String sql = "SELECT *FROM sanpham WHERE MaSP=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, ma);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMaLoai(rs.getInt(3));
                sp.setMaNCC(rs.getInt(4));
                sp.setSoLuong(rs.getInt(5));
                sp.setHinhAnh(rs.getString(6));
                sp.setDonGia(rs.getInt(7));
                sp.setMoTa(rs.getString(8));

                return sp;
            }
        } catch (SQLException e) {
        }

        return null;
    }

    public ArrayList<SanPham> getSanPhamTheoLoai(int maLoai) {
        try {
            String sql = "SELECT * FROM sanpham WHERE MaLoai=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maLoai);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMaLoai(rs.getInt(3));
                sp.setMaNCC(rs.getInt(4));
                sp.setSoLuong(rs.getInt(5));
                sp.setHinhAnh(rs.getString(6));
                sp.setDonGia(rs.getInt(7));
                sp.setMoTa(rs.getString(8));

                dssp.add(sp);
            }
            return dssp;
        } catch (SQLException e) {
        }

        return null;
    }

    public ArrayList<SanPham> getSanPhamTheoNCC(int maNCC) {
        try {
            String sql = "SELECT * FROM sanpham WHERE MaNCC=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maNCC);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMaLoai(rs.getInt(3));
                sp.setMaNCC(rs.getInt(4));
                sp.setSoLuong(rs.getInt(5));
                sp.setHinhAnh(rs.getString(6));
                sp.setDonGia(rs.getInt(7));
                sp.setMoTa(rs.getString(8));

                dssp.add(sp);
            }
            return dssp;
        } catch (SQLException e) {
        }

        return null;
    }

    public String getAnh(int ma) {
        try {
            String sql = "SELECT HinhAnh FROM sanpham WHERE MaSP=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, ma);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString("HinhAnh");
            }
        } catch (SQLException e) {
        }
        return "";
    }

    public void capNhatSoLuongSP(int ma, int soLuongMat) {
        SanPham sp = getSanPham(ma);
        int soLuong = sp.getSoLuong();
        sp.setSoLuong(soLuong + soLuongMat);
        try {
            String sql = "UPDATE sanpham SET SoLuong=? WHERE MaSP=" + ma;
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, sp.getSoLuong());
            pre.executeUpdate();
        } catch (SQLException e) {
        }

    }

    public boolean themSanPham(SanPham sp) {
        try {
            String sql = "INSERT INTO SanPham(TenSP, MaLoai, MaNCC, SoLuong, HinhAnh, DonGia, MoTa) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, sp.getTenSP());
            pre.setInt(2, sp.getMaLoai());
            pre.setInt(3, sp.getMaNCC());
            pre.setInt(4, sp.getSoLuong());
            pre.setString(5, sp.getHinhAnh());
            pre.setInt(6, sp.getDonGia());
            pre.setString(7, sp.getMoTa());

            pre.execute();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean nhapSanPhamTuExcel(SanPham sp) {
        try {
            String sql = "DELETE * FROM sanpham; " +
                    "INSERT INTO sanpham(TenSP, MaLoai, MaNCC, SoLuong, HinhAnh, DonGia, MoTa) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, sp.getTenSP());
            pre.setInt(2, sp.getMaLoai());
            pre.setInt(3, sp.getMaNCC());
            pre.setInt(4, sp.getSoLuong());
            pre.setString(5, sp.getHinhAnh());
            pre.setInt(6, sp.getDonGia());
            pre.setString(7, sp.getMoTa());

            pre.execute();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean xoaSanPham(int maSP) {
        try {
            String sql = "DELETE * FROM sanpham WHERE MaSP=" + maSP;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean suaSanPham(SanPham sp) {
        try {
            String sql = "UPDATE sanpham SET "
                    + "TenSP=?, "
                    + "MaLoai=?, MaNCC=?, SoLuong=?, HinhAnh=?, DonGia=?, MoTa=? "
                    + "WHERE MaSP=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, sp.getTenSP());
            pre.setInt(2, sp.getMaLoai());
            pre.setInt(3, sp.getMaNCC());
            pre.setInt(4, sp.getSoLuong());
            pre.setString(5, sp.getHinhAnh());
            pre.setInt(6, sp.getDonGia());
            pre.setString(7, sp.getMoTa());
            pre.setInt(8, sp.getMaSP());

            pre.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
