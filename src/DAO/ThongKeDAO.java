package DAO;

import DTO.SanPham;
import DTO.HoaDon;
import DTO.DoanhThuNhanVien;
import DTO.DoanhThuKhachHang;
import DTO.DoanhThuQuy;
import DTO.ThongKe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

public class ThongKeDAO {
    public int getSoLuongSP() {
        try {
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM sanpham");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
    public int getSoLuongNhanVien() {
        try {
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM nhanvien");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    public int getSoLuongKhachHang() {
        try {
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM khachhang");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
        
    public ArrayList<HoaDon> getDanhSachHDTheoNgay(Date date) {
        try {
            String sql = "SELECT * FROM hoadon WHERE DATE(NgayLap) = ?";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setDate(1, date);
            ResultSet rs = prep.executeQuery();
            ArrayList<HoaDon> dshd = new ArrayList<>();
            
            while(rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaNV(rs.getInt(2));
                hd.setMaKH(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                hd.setMaGG(rs.getInt(6));
                
                dshd.add(hd);
            }
            return dshd;
        } catch (SQLException e) {
        }
        return null;
    }
        
    public int tinhDoanhThuTheoNgay(Date date) {
        int result = 0;
        try {
            String sql = "SELECT DATE(NgayLap) as ngay, SUM(TongTien) as tong_doanh_thu " +
                            "FROM hoadon " +
                            "GROUP BY DATE(NgayLap) " +
                            "HAVING ngay = ? ";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setDate(1, date);
            
            ResultSet rs = pre.executeQuery();
            if(rs.next()) {
                result = Integer.parseInt(rs.getInt(2)+"");
            }
        } catch (Exception e) {
        }
        return result;
    }
    
    public int tinhDoanhThuThang(int thang, int nam) {
        try {
            String sql = "SELECT SUM(TongTien) FROM hoadon WHERE MONTH(NgayLap) = ? AND YEAR(NgayLap) = ?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, thang);
            pre.setInt(2, nam);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return Integer.parseInt(rs.getInt(1) + "");
            }
        } catch (NumberFormatException | SQLException e) {
        }
        return 0;
    }

    public DoanhThuQuy tinhDoanhThuQuy(int nam) {
       try {
            String sql = "SELECT Nam, " +
             "COALESCE(SUM(CASE WHEN Quy = 1 THEN DoanhThu END), 0) AS DoanhThuQ1, " +
             "COALESCE(SUM(CASE WHEN Quy = 2 THEN DoanhThu END), 0) AS DoanhThuQ2, " +
             "COALESCE(SUM(CASE WHEN Quy = 3 THEN DoanhThu END), 0) AS DoanhThuQ3, " +
             "COALESCE(SUM(CASE WHEN Quy = 4 THEN DoanhThu END), 0) AS DoanhThuQ4 " +
             "FROM ( " +
             "SELECT YEAR(hd.NgayLap) AS Nam, " +
             "QUARTER(hd.NgayLap) AS Quy, " +
             "SUM(hd.TongTien) AS DoanhThu " +
             "FROM hoadon hd " +
             "WHERE YEAR(hd.NgayLap) = ? " +
             "GROUP BY YEAR(hd.NgayLap), QUARTER(hd.NgayLap)) AS T " +
             "GROUP BY Nam";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, nam);
            
            ResultSet rs = pre.executeQuery();
            DoanhThuQuy dtQ = new DoanhThuQuy();
            if(rs.next()) {
                dtQ.setNam(rs.getInt(1));
                dtQ.setDTQuy1(rs.getInt(2));
                dtQ.setDTQuy2(rs.getInt(3));
                dtQ.setDTQuy3(rs.getInt(4));
                dtQ.setDTQuy4(rs.getInt(5));
            }
            return dtQ;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public int tinhDoanhThuTheoNam(int nam) {
        int result = 0;
        try {
            String sql = "SELECT YEAR(NgayLap) as nam, "
                    + " SUM(TongTien) as tong_doanh_thu "
                    + "FROM hoadon "
                    + "WHERE YEAR(NgayLap) = ? GROUP BY nam ";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, nam);
            
            ResultSet rs = pre.executeQuery();
            if(rs.next()) {
                result = Integer.parseInt(rs.getInt(2)+"");
            }
        } catch (Exception e) {
        }
        return result;
    }
    
    public ArrayList<SanPham> getDanhSachSPTonHang() {
        try {
            String sql = "SELECT MaSP, TenSP, SoLuong " +
                            "FROM sanpham " +
                            "ORDER BY SoLuong ASC ";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while(rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setSoLuong(rs.getInt(3));
                
                dssp.add(sp);
            }
            return dssp;
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<SanPham> getDanhSachSPBanTheoNgay(Date date) {
        try {
            String sql = "SELECT sp.MaSP, sp.TenSP, SUM(cthd.SoLuong) AS tong_so_luong " +
                            "FROM sanpham sp " +
                            "JOIN cthoadon cthd ON sp.MaSP = cthd.MaSP " +
                            "JOIN hoadon hd ON cthd.MaHD = hd.MaHD " +
                            "WHERE hd.NgayLap = ? " +
                            "GROUP BY sp.MaSP, sp.TenSP " +
                            "ORDER BY tong_so_luong DESC ";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setDate(1, date);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while(rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setSoLuong(rs.getInt(3));
                
                dssp.add(sp);
            }
            return dssp;
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<SanPham> getDanhSachSPBanTheoThang(int thang, int nam) {
        try {
            String sql = "SELECT sp.MaSP, sp.TenSP, SUM(cthd.SoLuong) AS tong_so_luong " +
                            "FROM sanpham sp " +
                            "JOIN cthoadon cthd ON sp.MaSP = cthd.MaSP " +
                            "JOIN hoadon hd ON cthd.MaHD = hd.MaHD " +
                            "WHERE MONTH(hd.NgayLap) = ? AND YEAR(hd.NgayLap) = ? " +
                            "GROUP BY sp.MaSP, sp.TenSP " +
                            "ORDER BY tong_so_luong DESC ";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, thang);
            pre.setInt(2, nam);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while(rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setSoLuong(rs.getInt(3));
                
                dssp.add(sp);
            }
            return dssp;
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<SanPham> getDanhSachSPBanTheoNam(int nam) {
        try {
            String sql = "SELECT sp.MaSP, sp.TenSP, SUM(cthd.SoLuong) AS tong_so_luong " +
                            "FROM sanpham sp " +
                            "JOIN cthoadon cthd ON sp.MaSP = cthd.MaSP " +
                            "JOIN hoadon hd ON cthd.MaHD = hd.MaHD " +
                            "WHERE YEAR(hd.NgayLap) = ? " +
                            "GROUP BY sp.MaSP, sp.TenSP " +
                            "ORDER BY tong_so_luong DESC";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, nam);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while(rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setSoLuong(rs.getInt(3));
                
                dssp.add(sp);
            }
            return dssp;
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<DoanhThuNhanVien> getDanhSachDTNVTheoNgay(Date date) {
        try {
            String sql = "SELECT nv.MaNV, nv.Ho, nv.Ten, SUM(hd.TongTien) AS doanh_thu " +
                                "FROM nhanvien nv " +
                                "JOIN hoadon hd ON nv.MaNV = hd.MaNV " +
                                "WHERE hd.NgayLap  BETWEEN ? AND ? " +
                                "GROUP BY nv.MaNV, nv.Ho, nv.Ten, hd.NgayLap " +
                                "ORDER BY doanh_thu DESC ";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setDate(1, date);
            pre.setDate(2, date);
            ResultSet rs = pre.executeQuery();
            ArrayList<DoanhThuNhanVien> dsnv = new ArrayList<>();
            while(rs.next()) {
                DoanhThuNhanVien nv = new DoanhThuNhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setTen(rs.getString(2));
                nv.setHo(rs.getString(3));
                nv.setTongTienBan(rs.getInt(4));
                
                dsnv.add(nv);
            }
            return dsnv;
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<DoanhThuNhanVien> getDanhSachDTNVTheoThang(int thang, int nam) {
        try {
            String sql ="SELECT nv.MaNV, nv.Ho, nv.Ten, SUM(hd.TongTien) AS TongTienBan " +
                            "FROM nhanvien nv " +
                            "JOIN hoadon hd ON nv.MaNV = hd.MaNV " +
                            "WHERE MONTH(hd.NgayLap) = ? AND YEAR(hd.NgayLap) = ? " +
                            "GROUP BY nv.MaNV, nv.Ho, nv.Ten " +
                            "ORDER BY TongTienBan DESC ";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, thang);
            pre.setInt(2, nam);
            ResultSet rs = pre.executeQuery();
            ArrayList<DoanhThuNhanVien> dsnv = new ArrayList<>();
            while(rs.next()) {
                DoanhThuNhanVien nv = new DoanhThuNhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setTen(rs.getString(2));
                nv.setHo(rs.getString(3));
                nv.setTongTienBan(rs.getInt(4));
                
                dsnv.add(nv);
            }
            return dsnv;
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<DoanhThuNhanVien> getDanhSachDTNVTheoNam(int nam) {
        try {
            String sql = "SELECT nv.MaNV, nv.Ho, nv.Ten, SUM(hd.TongTien) AS TongTienBan " +
                                    "FROM nhanvien nv " +
                                    "JOIN hoadon hd ON nv.MaNV = hd.MaNV " +
                                    "WHERE YEAR(hd.NgayLap) = ? " +
                                    "GROUP BY nv.MaNV, nv.Ho, nv.Ten " +
                                    "ORDER BY TongTienBan DESC ";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, nam);
            ResultSet rs = pre.executeQuery();
           ArrayList<DoanhThuNhanVien> dsnv = new ArrayList<>();
            while(rs.next()) {
                DoanhThuNhanVien nv = new DoanhThuNhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setTen(rs.getString(2));
                nv.setHo(rs.getString(3));
                nv.setTongTienBan(rs.getInt(4));
                
                dsnv.add(nv);
            }
            return dsnv;
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<DoanhThuKhachHang> getDanhSachDTKHTheoNgay(Date date) {
        try {
            String sql = "SELECT kh.MaKH, kh.ho, kh.Ten, SUM(hd.TongTien) AS doanh_thu " +
                            "FROM khachhang kh " +
                            "JOIN hoadon hd ON kh.MaKH = hd.MaKH " +
                            "WHERE hd.NgayLap BETWEEN ? AND ? " +
                            "GROUP BY kh.MaKH, kh.Ho, kh.Ten " +
                            "ORDER BY doanh_thu DESC ";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setDate(1, date);
            pre.setDate(2, date);
            ResultSet rs = pre.executeQuery();
            ArrayList<DoanhThuKhachHang> dskh = new ArrayList<>();
            while(rs.next()) {
                DoanhThuKhachHang nv = new DoanhThuKhachHang();
                nv.setMaKH(rs.getInt(1));
                nv.setTen(rs.getString(2));
                nv.setHo(rs.getString(3));
                nv.setTongTienMua(rs.getInt(4));
                
                dskh.add(nv);
            }
            return dskh;
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<DoanhThuKhachHang> getDanhSachDTKHTheoThang(int thang, int nam) {
        try {
            String sql = "SELECT kh.MaKH, kh.ho, kh.Ten, SUM(hd.TongTien) AS doanh_thu " +
                            "FROM khachhang kh " +
                            "JOIN hoadon hd ON kh.MaKH = hd.MaKH " +
                            "WHERE MONTH(hd.NgayLap) = ? AND YEAR(hd.NgayLap) = ? " +
                            "GROUP BY kh.MaKH, kh.Ho, kh.Ten " +
                            "ORDER BY doanh_thu DESC";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, thang);
            pre.setInt(2, nam);
            ResultSet rs = pre.executeQuery();
            ArrayList<DoanhThuKhachHang> dskh = new ArrayList<>();
            while(rs.next()) {
                DoanhThuKhachHang nv = new DoanhThuKhachHang();
                nv.setMaKH(rs.getInt(1));
                nv.setTen(rs.getString(2));
                nv.setHo(rs.getString(3));
                nv.setTongTienMua(rs.getInt(4));
                
                dskh.add(nv);
            }
            return dskh;
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<DoanhThuKhachHang> getDanhSachDTKHTheoNam(int nam) {
        try {
           String sql = "SELECT kh.MaKH, kh.ho, kh.Ten, SUM(hd.TongTien) AS doanh_thu " +
                                "FROM khachhang kh " +
                                "JOIN hoadon hd ON kh.MaKH = hd.MaKH " +
                                "WHERE YEAR(hd.NgayLap) = ? " +
                                "GROUP BY kh.MaKH, kh.Ho, kh.Ten " +
                                "ORDER BY doanh_thu DESC";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, nam);
            ResultSet rs = pre.executeQuery();
            ArrayList<DoanhThuKhachHang> dskh = new ArrayList<>();
            while(rs.next()) {
                DoanhThuKhachHang nv = new DoanhThuKhachHang();
                nv.setMaKH(rs.getInt(1));
                nv.setTen(rs.getString(2));
                nv.setHo(rs.getString(3));
                nv.setTongTienMua(rs.getInt(4));
                
                dskh.add(nv);
            }
            return dskh;
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<Integer> getDSNamHD() {
        try {
            ArrayList<Integer> dsNam = new ArrayList<>();
            String sql = "SELECT DISTINCT YEAR(NgayLap) AS Nam " +
                                "FROM hoadon " +
                                "WHERE YEAR(NgayLap) BETWEEN YEAR((SELECT MIN(NgayLap) FROM hoadon)) AND YEAR(CURRENT_DATE()) " +
                                "ORDER BY Nam DESC";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()) {
                dsNam.add(Integer.parseInt(rs.getInt(1) + ""));
            }
            return dsNam;
        } catch (NumberFormatException | SQLException e) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        new MyConnect();
        ThongKeDAO tkDAO = new ThongKeDAO();
        System.out.println(tkDAO.getDanhSachDTKHTheoThang(6,2023));
    }
}
