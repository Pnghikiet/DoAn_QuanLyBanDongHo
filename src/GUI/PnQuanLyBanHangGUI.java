/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.CTHoaDonBUS;
import BUS.CTPhieuNhapBUS;
import BUS.DangNhapBUS;
import BUS.HoaDonBUS;
import BUS.LoaiBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import Customs.MyDialog;
import Customs.MyFileChooser;
import DTO.CTHoaDon;
import DTO.CTPhieuNhap;
import DTO.HoaDon;
import DTO.LoaiSP;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.PhieuNhap;
import DTO.SanPham;
import javax.swing.*;
import static Main.Main.changLNF;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author PC
 */
public class PnQuanLyBanHangGUI extends JPanel {
    LoaiBUS loaiBUS = new LoaiBUS();
    SanPhamBUS spBUS = new SanPhamBUS();
    NhaCungCapBUS nhacungcapBUS = new NhaCungCapBUS();
    NhanVienBUS nhanvienBUS = new NhanVienBUS();
    DangNhapBUS dangnhapBUS = new DangNhapBUS();
    HoaDonBUS hoadonBUS = new HoaDonBUS();
    CTHoaDonBUS cthoadonBUS = new CTHoaDonBUS();
    private TableCellRenderer centerRenderer;
    JPanel pnlsanpham, pnlhoadon;
    JTabbedPane tabpnlqlbanhang;
    DefaultTableModel dtmsanpham, dtmgiohang, dtmchitiethoadon, dtmhoadon;
    JTable tblsanpham, tblgiohang, tblchitietphieunhap, tblphieunhap;
    JLabel lbltittlepnlnhaphang, lbltittlepnlphieunhap, lbltimkiemnhaphang, lbltittlenhaphang, lblthongtinsanpham, lblthongtinhoadon,  lblmasp, lbltensp, lbldongia, lblsoluong
            , lblnhacungcap, lblnhanvien, lblAnhSP, lblloaisanpham, lblmotasanpham, lblmahd, lblmanv, lblghichu, lblngaylap, lbltongtien, lbltittlechitiethd, lblchitietsanpham, lblchitietsoluong
            , lblchitietdongia, lblchitietthanhtien, lblanh, lblmakh, lblchitietmahd;
    JComboBox<String> cmbNcc, cmbLoai;
    JButton btnResetnhaphang, btnnhapsanpham, btnxacnhan, btnxoa, btnResetphieunhap, btntimkiem;
    JTextField txttimkiem, txtmasp, txttensp, txtdongia, txtsoluong, txtnhanvien, txtmahd, txtmanv, txtghichu, txtngaylap, txttongtien,
             txthdsanpham, txthddongia, txthdsoluong, txthdthanhtien, txttientoithieu, txttientoida, txtngaytoithieu, txtngaytoida, txtmakh, txthdmahd;
    JScrollPane scrsanpham, scrnhaphang, scrchitietpn;
    JTextArea txtareamota;
    
    public PnQuanLyBanHangGUI()
    {
        changLNF("Windows");
        addcontrols();
        addevents();
    }
    
    public void addcontrols()
    {
        tabpnlqlbanhang = new JTabbedPane();
        pnlsanpham = new JPanel();
        pnlhoadon = new JPanel();
        lbltittlepnlnhaphang = new JLabel("DANH SÁCH SẢN PHẨM");
        lbltittlepnlphieunhap = new JLabel("HOÁ ĐƠN");
        lbltimkiemnhaphang = new JLabel("Tìm kiếm");
        lbltittlenhaphang = new JLabel("GIỎ HÀNG");
        lblthongtinhoadon = new JLabel("THÔNG TIN HOÁ ĐƠN");
        lblthongtinsanpham = new JLabel("Thông Tin Sản Phẩm");
        lbldongia = new JLabel("Đơn Giá");
        lblmasp = new JLabel("Mã sản phẩm");
        lbltensp = new JLabel("Tên sản phẩm");
        lblsoluong = new JLabel("Số lượng");
        lblnhacungcap = new JLabel("Nhà Cung Cấp");
        lblnhanvien = new JLabel("Nhân Viên");
        lblloaisanpham = new JLabel("Loại");
        lblAnhSP = new JLabel();
        lblmotasanpham = new JLabel("Mô Tả");
        lblmahd = new JLabel("MÃ HD");
        lblmanv = new JLabel("MÃ NV");
        lblghichu = new JLabel("GHI CHÚ");
        lblmakh = new JLabel("Mã KH");
        lblngaylap = new JLabel("Ngày Lập");
        lbltongtien = new JLabel("Tổng Tiền");
        lbltittlechitiethd = new JLabel("Chi Tiết Hoá Đơn");
        lblchitietmahd = new JLabel("Mã HD");
        lblchitietsanpham = new JLabel("Sản Phẩm");
        lblchitietsoluong = new JLabel("Số lượng");
        lblchitietdongia = new JLabel("Đơn Giá");
        lblchitietthanhtien = new JLabel("Thành Tiền");
        lblanh = new JLabel("Ảnh");
        lblmakh = new JLabel("MÃ KH");
        lblchitietmahd = new JLabel("Mã HD");
        btnResetnhaphang = new JButton();
        btnxacnhan = new JButton("Xác Nhận");
        btnxoa = new JButton("Xoá");
        btnnhapsanpham = new JButton("Thêm Vào Giỏ");
        btnResetphieunhap = new JButton();
        btntimkiem = new JButton();
        txttimkiem = new JTextField();
        txtmasp = new JTextField();
        txttensp = new JTextField();
        txtdongia = new JTextField();
        txtsoluong = new JTextField();
        txtnhanvien = new JTextField();
        txtmahd = new JTextField();
        txtmanv = new JTextField();
        txtghichu = new JTextField();
        txtngaylap = new JTextField();
        txttongtien = new JTextField();
        txthdsanpham = new JTextField();
        txthddongia = new JTextField();
        txthdsoluong = new JTextField();
        txthdthanhtien = new JTextField();
        txttientoithieu = new JTextField();
        txttientoida = new JTextField();
        txtngaytoithieu = new JTextField();
        txtngaytoida = new JTextField();
        txtmakh = new JTextField();
        txthdmahd = new JTextField();
        txtareamota = new JTextArea();
        cmbNcc = new JComboBox<>();
        cmbLoai = new JComboBox<>();
        
        
        setSize(1000, 700);
        setLayout(null);
        
        pnlsanpham.setLayout(null);
        pnlhoadon.setLayout(null);
        
        tabpnlqlbanhang.add("Bán Hàng",pnlsanpham);
        tabpnlqlbanhang.add("Hoá Đơn",pnlhoadon);
        tabpnlqlbanhang.setBounds(0, 00, 983, 690);
        add(tabpnlqlbanhang);
        
        //Tab sản phẩm
        
        lbltittlepnlnhaphang.setBounds(230, 20, 180, 20);
        pnlsanpham.add(lbltittlepnlnhaphang);
        
        lbltimkiemnhaphang.setBounds(190, 60, 100, 20);
        pnlsanpham.add(lbltimkiemnhaphang);
        
        txttimkiem.setBounds( 280, 60, 180, 22);
        pnlsanpham.add(txttimkiem);
        
        btntimkiem.setBounds(300, 60, 10, 22);
        btntimkiem.setVisible(false);
        pnlsanpham.add(btntimkiem);
        
        JPanel pnltablesanpham = new JPanel();
        pnltablesanpham.setBounds(10, 120, 600, 205);
        pnltablesanpham.setLayout(null);
        
        dtmsanpham = new DefaultTableModel();
        dtmsanpham.addColumn("Mã SP");
        dtmsanpham.addColumn("Tên Sản Phẩm");
        dtmsanpham.addColumn("Loại");
        dtmsanpham.addColumn("Đơn Giá");
        dtmsanpham.addColumn("Số Lượng");
        dtmsanpham.addColumn("Ảnh");
        dtmsanpham.addColumn("Mô tả sản phẩm");
        
        tblsanpham = new JTable(dtmsanpham);
        tblsanpham.setSize(600, 200);
        
        JTableHeader headertblsanpham = tblsanpham.getTableHeader();
        headertblsanpham.setBackground(Color.white);
        headertblsanpham.setEnabled(false);
        
        TableColumnModel columnModelBanHang = tblsanpham.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(75);
        columnModelBanHang.getColumn(1).setPreferredWidth(200);
        columnModelBanHang.getColumn(2).setPreferredWidth(125);
        columnModelBanHang.getColumn(3).setPreferredWidth(125);
        columnModelBanHang.getColumn(4).setPreferredWidth(100);
        columnModelBanHang.getColumn(5).setPreferredWidth(100);
        columnModelBanHang.getColumn(6).setPreferredWidth(150);

        scrsanpham = new JScrollPane(tblsanpham);
        scrsanpham.setBounds(0, 0, 600, 200);
        pnltablesanpham.add(scrsanpham);
        pnlsanpham.add(pnltablesanpham);
        
        lbltittlenhaphang.setBounds(230, 330, 100, 20);
        pnlsanpham.add(lbltittlenhaphang);
        
        JPanel pnltablenhaphang = new JPanel();
        pnltablenhaphang.setLayout(null);
        pnltablenhaphang.setBounds(10, 380, 600, 205);
        
        dtmgiohang = new DefaultTableModel();
        dtmgiohang.addColumn("Mã SP");
        dtmgiohang.addColumn("Tên Sản Phẩm");
        dtmgiohang.addColumn("Loại");
        dtmgiohang.addColumn("Số lượng");
        dtmgiohang.addColumn("Đơn Giá");
        dtmgiohang.addColumn("Thành Tiền");
        
        tblgiohang = new JTable(dtmgiohang);
        tblgiohang.setSize(600, 200);

        JTableHeader headertblnhaphang =  tblgiohang.getTableHeader();
        headertblnhaphang.setBackground(Color.white);
        headertblnhaphang.setEnabled(false);
        
        TableColumnModel columnmodelnhaphang = tblgiohang.getColumnModel();
        columnmodelnhaphang.getColumn(0).setPreferredWidth(75);
        columnmodelnhaphang.getColumn(1).setPreferredWidth(200);
        columnmodelnhaphang.getColumn(2).setPreferredWidth(100);
        columnmodelnhaphang.getColumn(3).setPreferredWidth(75);
        columnmodelnhaphang.getColumn(4).setPreferredWidth(100);
        columnmodelnhaphang.getColumn(5).setPreferredWidth(100);
        
        scrnhaphang = new JScrollPane(tblgiohang);
        scrnhaphang.setBounds(0, 0, 600, 200);
        pnltablenhaphang.add(scrnhaphang);
        pnlsanpham.add(pnltablenhaphang);
        
        ImageIcon icon = new ImageIcon("images\\Refresh-icon.png");
        btnResetnhaphang.setBounds(380, 19, 28, 28);
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(btnResetnhaphang.getWidth(), btnResetnhaphang.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledicon = new ImageIcon(imgscale);
        btnResetnhaphang.setIcon(scaledicon);
        pnlsanpham.add(btnResetnhaphang);
        
        //Thông tin sản phẩm
        
        JPanel pnlthongtinsanpham = new JPanel();
        pnlthongtinsanpham.setBounds(650, 0, 400, 700);
        pnlthongtinsanpham.setLayout(null);
        lblthongtinsanpham.setBounds(95, 15, 160, 20);
        pnlthongtinsanpham.add(lblthongtinsanpham);
        
        JPanel pnlthongtinmasanpham, pnlthongtintensanpham, pnlthongdongiasp, pnlthongtinsoluongsp, pnlthongtinnhacungcap
                , pnlthongloaisanpham, pnlmota_hinhanh, pnlthongtinnhanvien;
        pnlthongtinmasanpham = new JPanel();
        pnlthongtinmasanpham.setBounds(0, 55, pnlthongtinsanpham.getWidth(), 23);
        pnlthongtinsanpham.add(pnlthongtinmasanpham);
        pnlthongtinmasanpham.setLayout(null);
        lblmasp.setBounds(10, 3, 100, 22);
        pnlthongtinmasanpham.add(lblmasp);
        txtmasp.setBounds(120, 3, 180, 22);
        txtmasp.setEditable(false);
        pnlthongtinmasanpham.add(txtmasp);
        
        pnlthongtintensanpham = new JPanel();
        pnlthongtintensanpham.setBounds(0, 105, pnlthongtinsanpham.getWidth(), 23);
        pnlthongtinsanpham.add(pnlthongtintensanpham);
        pnlthongtintensanpham.setLayout(null);
        lbltensp.setBounds(10, 3, 100, 22);
        pnlthongtintensanpham.add(lbltensp);
        txttensp.setBounds(120, 3, 180, 22);
        txttensp.setEditable(false);
        pnlthongtintensanpham.add(txttensp);
        
        pnlthongdongiasp = new JPanel();
        pnlthongdongiasp.setBounds(0, 155, pnlthongtinsanpham.getWidth(), 23);
        pnlthongtinsanpham.add(pnlthongdongiasp);
        pnlthongdongiasp.setLayout(null);
        lbldongia.setBounds(10, 3, 100, 22);
        pnlthongdongiasp.add(lbldongia);
        txtdongia.setBounds(120, 3, 180, 22);
        txtdongia.setEditable(false);
        pnlthongdongiasp.add(txtdongia);
        
        pnlthongtinsoluongsp = new JPanel();
        pnlthongtinsoluongsp.setBounds(0, 205, pnlthongtinsanpham.getWidth(), 23);
        pnlthongtinsanpham.add(pnlthongtinsoluongsp);
        pnlthongtinsoluongsp.setLayout(null);
        lblsoluong.setBounds(10, 3, 100, 22);
        pnlthongtinsoluongsp.add(lblsoluong);
        txtsoluong.setBounds(120, 3, 180, 22);
        pnlthongtinsoluongsp.add(txtsoluong);
        
        pnlthongloaisanpham = new JPanel();
        pnlthongloaisanpham.setBounds(0, 255, pnlthongtinsanpham.getWidth(), 23);
        pnlthongtinsanpham.add(pnlthongloaisanpham);
        pnlthongloaisanpham.setLayout(null);
        lblloaisanpham.setBounds(10, 3, 100, 22);
        pnlthongloaisanpham.add(lblloaisanpham);
        cmbLoai.setBounds(120, 3, 180, 22);
        cmbLoai.setEnabled(false);
        pnlthongloaisanpham.add(cmbLoai);
        
        pnlthongtinnhanvien = new JPanel();
        pnlthongtinnhanvien.setBounds(0,305, pnlthongtinsanpham.getWidth(), 23);
        pnlthongtinsanpham.add(pnlthongtinnhanvien);
        pnlthongtinnhanvien.setLayout(null);
        lblnhanvien.setBounds(10, 3, 100, 22);
        pnlthongtinnhanvien.add(lblnhanvien);
        txtnhanvien.setBounds(120, 3, 180, 22);
        txtnhanvien.setEditable(false);
        pnlthongtinnhanvien.add(txtnhanvien);
        
        pnlmota_hinhanh = new JPanel();
        pnlmota_hinhanh.setBounds(0, 345, pnlthongtinsanpham.getWidth(), 200);
        pnlthongtinsanpham.add(pnlmota_hinhanh);
        pnlmota_hinhanh.setLayout(null);
        lblanh.setBounds(15, 3, 100, 22);
        pnlmota_hinhanh.add(lblanh);
        lblAnhSP.setBounds(5, 30, 150, 150);
        lblAnhSP.setBorder(BorderFactory.createLineBorder(Color.gray));
        pnlmota_hinhanh.add(lblAnhSP);
        lblmotasanpham.setBounds(170, 3, 100, 22);
        pnlmota_hinhanh.add(lblmotasanpham);
        txtareamota.setBounds(160, 30, 150, 150);
        txtareamota.setBorder(BorderFactory.createLineBorder(Color.black));
        txtareamota.setLineWrap(true);
        txtareamota.setEditable(false);
        pnlmota_hinhanh.add(txtareamota);
        
        btnnhapsanpham.setBounds( 70, 565, 175, 22);
        pnlthongtinsanpham.add(btnnhapsanpham);
        
        btnxoa.setBounds(30, 595, 100, 22);
        pnlthongtinsanpham.add(btnxoa);
        
        btnxacnhan.setBounds(190, 595, 100, 22);
        pnlthongtinsanpham.add(btnxacnhan);
        
        pnlsanpham.add(pnlthongtinsanpham);
        
        
        
        
        //Tab hoá đơn
        
        pnlhoadon.setSize(983, 690);
        
        JPanel pnlthongtinhoadon = new JPanel();
        pnlthongtinhoadon.setBounds(0, 0, 400, 690);
        pnlthongtinhoadon.setLayout(null);
        pnlhoadon.add(pnlthongtinhoadon);
        
        lbltittlepnlphieunhap.setBounds(165, 20, 100, 22);
        pnlthongtinhoadon.add(lbltittlepnlphieunhap);
        
        btnResetphieunhap.setBounds(250, 15, 28, 28);
        btnResetphieunhap.setIcon(scaledicon);
        pnlthongtinhoadon.add(btnResetphieunhap);
        
        JPanel pnlhoadonmahd, pnldoadonmakh, pnlhoadonghichu, pnlhoadonmanv, pnlgaynhap, pnltongtien;
        pnlhoadonmahd = new JPanel();
        pnlhoadonmahd.setBounds(0, 45, pnlthongtinhoadon.getWidth(), 23);
        pnlthongtinhoadon.add(pnlhoadonmahd);
        pnlhoadonmahd.setLayout(null);
        lblmahd.setBounds(50, 3, 100, 22);
        pnlhoadonmahd.add(lblmahd);
        txtmahd.setBounds(150, 3, 180, 22);
        txtmahd.setEditable(false);
        pnlhoadonmahd.add(txtmahd);
        
        pnldoadonmakh = new JPanel();
        pnldoadonmakh.setBounds(0, 85, pnlthongtinhoadon.getWidth(), 23);
        pnlthongtinhoadon.add(pnldoadonmakh);
        pnldoadonmakh.setLayout(null);
        lblmakh.setBounds(50, 3, 100, 22);
        pnldoadonmakh.add(lblmakh);
        txtmakh.setBounds(150, 3, 180, 22);
        txtmakh.setEditable(false);
        pnldoadonmakh.add(txtmakh);
        
        pnlhoadonmanv = new JPanel();
        pnlhoadonmanv.setBounds(0, 125, pnlthongtinhoadon.getWidth(), 23);
        pnlthongtinhoadon.add(pnlhoadonmanv);
        pnlhoadonmanv.setLayout(null);
        lblmanv.setBounds(50, 3, 100, 22);
        pnlhoadonmanv.add(lblmanv);
        txtmanv.setBounds(150, 3, 180, 22);
        txtmanv.setEditable(false);
        pnlhoadonmanv.add(txtmanv);
        
        pnlgaynhap = new JPanel();
        pnlgaynhap.setBounds(0, 165, pnlthongtinhoadon.getWidth(), 23);
        pnlthongtinhoadon.add(pnlgaynhap);
        pnlgaynhap.setLayout(null);
        lblngaylap.setBounds(50, 3, 100, 22);
        pnlgaynhap.add(lblngaylap);
        txtngaylap.setBounds(150, 3, 180, 22);
        txtngaylap.setEditable(false);
        pnlgaynhap.add(txtngaylap);
        
        pnltongtien = new JPanel();
        pnltongtien.setBounds(0, 205, pnlthongtinhoadon.getWidth(), 23);
        pnlthongtinhoadon.add(pnltongtien);
        pnltongtien.setLayout(null);
        lbltongtien.setBounds(50, 3, 100, 22);
        pnltongtien.add(lbltongtien);
        txttongtien.setBounds(150, 3, 180, 22);
        txttongtien.setEditable(false);
        pnltongtien.add(txttongtien);
        
        pnlhoadonghichu = new JPanel();
        pnlhoadonghichu.setBounds(0, 245, pnlthongtinhoadon.getWidth(), 23);
        pnlthongtinhoadon.add(pnlhoadonghichu);
        pnlhoadonghichu.setLayout(null);
        lblghichu.setBounds(50, 3, 100, 22);
        pnlhoadonghichu.add(lblghichu);
        txtghichu.setBounds(150, 3, 180, 22);
        txtghichu.setEditable(false);
        pnlhoadonghichu.add(txtghichu);
        
        JLabel lblpntimkiem = new JLabel("Tìm Kiếm:");
        lblpntimkiem.setBounds(18, 300, 100, 22);
        pnlthongtinhoadon.add(lblpntimkiem);
        
        JLabel lblden1, lblden2, lblgia, lblngay;
        
        JPanel pnltimkiemtien, pnltimkiemngay;
        
        pnltimkiemtien = new JPanel();
        pnltimkiemtien.setBounds(0, 330, pnlthongtinhoadon.getWidth(), 30);
        pnltimkiemtien.setLayout(null);
        lblgia = new JLabel("Giá");
        lblgia.setBounds(5, 3, 80, 22);
        pnltimkiemtien.add(lblgia);
        txttientoithieu.setBounds(50, 3, 130, 24);
        pnltimkiemtien.add(txttientoithieu);
        lblden1 = new JLabel(" Đến ");
        lblden1.setBounds(195, 3, 100, 22);
        pnltimkiemtien.add(lblden1);
        txttientoida.setBounds(235, 3, 130, 24);
        pnltimkiemtien.add(txttientoida);
        pnlthongtinhoadon.add(pnltimkiemtien);
        
        pnltimkiemngay = new JPanel();
        pnltimkiemngay.setBounds(0, 380, pnlthongtinhoadon.getWidth(), 30);
        pnltimkiemngay.setLayout(null);
        lblngay = new JLabel("Ngày");
        lblngay.setBounds(5, 3, 80, 22);
        pnltimkiemngay.add(lblngay);
        txtngaytoithieu.setBounds(50, 3, 130, 24);
        pnltimkiemngay.add(txtngaytoithieu);
        lblden1 = new JLabel(" Đến ");
        lblden1.setBounds(195, 3, 100, 22);
        pnltimkiemngay.add(lblden1);
        txtngaytoida.setBounds(235, 3, 130, 24);
        pnltimkiemngay.add(txtngaytoida);
        pnlthongtinhoadon.add(pnltimkiemngay);
        
        JPanel pnltablepn = new JPanel();
        pnltablepn.setLayout(null);
        pnltablepn.setBounds(0, 425, pnlthongtinhoadon.getWidth(), 250);
        
        dtmhoadon = new DefaultTableModel();
        dtmhoadon.addColumn("Mã HD");
        dtmhoadon.addColumn("Ngày lập");
        dtmhoadon.addColumn("Tổng tiền");
        
        tblphieunhap = new JTable(dtmhoadon);
        tblphieunhap.setSize(pnlthongtinhoadon.getWidth(), 210);

        JTableHeader headertblpn =  tblphieunhap.getTableHeader();
        headertblpn.setBackground(Color.white);
        headertblpn.setEnabled(false);
        
        TableColumnModel columnmodelpn = tblphieunhap.getColumnModel();
        columnmodelpn.getColumn(0).setPreferredWidth(tblphieunhap.getWidth()/3);
        columnmodelpn.getColumn(1).setPreferredWidth(tblphieunhap.getWidth()/3);
        columnmodelpn.getColumn(2).setPreferredWidth(tblphieunhap.getWidth()/3);
        
        scrchitietpn = new JScrollPane(tblphieunhap);
        scrchitietpn.setBounds(0, 0, pnlthongtinhoadon.getWidth(), 210);
        pnltablepn.add(scrchitietpn);
        pnlthongtinhoadon.add(pnltablepn);
        
        JPanel pnlthongtinchitietpn = new JPanel();
        pnlthongtinchitietpn.setBounds(405, 0, 595, pnlhoadon.getHeight());
        pnlthongtinchitietpn.setLayout(null);
        pnlhoadon.add(pnlthongtinchitietpn);
        
        lbltittlechitiethd.setBounds(240, 20, 180, 22);
        pnlthongtinchitietpn.add(lbltittlechitiethd);
        
        JPanel pnlchitietmahd, pnlchitietsp, pnlchitietdongia, pnlchitietsoluong, pnlchitietthanhtien;
        
        pnlchitietmahd = new JPanel();
        pnlchitietmahd.setBounds(0, 45, pnlthongtinchitietpn.getWidth(), 23);
        pnlthongtinchitietpn.add(pnlchitietmahd);
        pnlchitietmahd.setLayout(null);
        lblchitietmahd.setBounds(175, 3, 100, 22);
        pnlchitietmahd.add(lblchitietmahd);
        txthdmahd.setBounds(260, 3, 180, 22);
        txthdmahd.setEditable(false);
        pnlchitietmahd.add(txthdmahd);
        
        pnlchitietsp = new JPanel();
        pnlchitietsp.setBounds(0, 85, pnlthongtinchitietpn.getWidth(), 23);
        pnlthongtinchitietpn.add(pnlchitietsp);
        pnlchitietsp.setLayout(null);
        lblchitietsanpham.setBounds(175, 3, 100, 22);
        pnlchitietsp.add(lblchitietsanpham);
        txthdsanpham.setBounds(260, 3, 180, 22);
        txthdsanpham.setEditable(false);
        pnlchitietsp.add(txthdsanpham);
        
        pnlchitietsoluong = new JPanel();
        pnlchitietsoluong.setBounds(0, 125, pnlthongtinchitietpn.getWidth(), 23);
        pnlthongtinchitietpn.add(pnlchitietsoluong);
        pnlchitietsoluong.setLayout(null);
        lblchitietsoluong.setBounds(175, 3, 100, 22);
        pnlchitietsoluong.add(lblchitietsoluong);
        txthdsoluong.setBounds(260, 3, 180, 22);
        txthdsoluong.setEditable(false);
        pnlchitietsoluong.add(txthdsoluong);
        
        pnlchitietdongia = new JPanel();
        pnlchitietdongia.setBounds(0, 165, pnlthongtinchitietpn.getWidth(), 23);
        pnlthongtinchitietpn.add(pnlchitietdongia);
        pnlchitietdongia.setLayout(null);
        lblchitietdongia.setBounds(175, 3, 100, 22);
        pnlchitietdongia.add(lblchitietdongia);
        txthddongia.setBounds(260, 3, 180, 22);
        txthddongia.setEditable(false);
        pnlchitietdongia.add(txthddongia);
        
        pnlchitietthanhtien = new JPanel();
        pnlchitietthanhtien.setBounds(0, 205, pnlthongtinchitietpn.getWidth(), 23);
        pnlthongtinchitietpn.add(pnlchitietthanhtien);
        pnlchitietthanhtien.setLayout(null);
        lblchitietthanhtien.setBounds(175, 3, 100, 22);
        pnlchitietthanhtien.add(lblchitietthanhtien);
        txthdthanhtien.setBounds(260, 3, 180, 22);
        txthdthanhtien.setEditable(false);
        pnlchitietthanhtien.add(txthdthanhtien);

        JPanel pnltablechitietpn = new JPanel();
        pnltablechitietpn.setLayout(null);
        pnltablechitietpn.setBounds(10, 240, pnlthongtinchitietpn.getWidth(), 500);
        
        dtmchitiethoadon = new DefaultTableModel();
        dtmchitiethoadon.addColumn("Mã HD");
        dtmchitiethoadon.addColumn("Mã SP");
        dtmchitiethoadon.addColumn("Số lượng");
        dtmchitiethoadon.addColumn("Đơn Giá");
        dtmchitiethoadon.addColumn("Thành Tiền");
        
        tblchitietphieunhap = new JTable(dtmchitiethoadon);
        tblchitietphieunhap.setSize(pnlthongtinchitietpn.getWidth()-30, 490);

        JTableHeader headertblchitietpn =  tblchitietphieunhap.getTableHeader();
        headertblchitietpn.setBackground(Color.white);
        headertblchitietpn.setEnabled(false);
        
        TableColumnModel columnmodelchitietpn = tblchitietphieunhap.getColumnModel();
        columnmodelchitietpn.getColumn(0).setPreferredWidth(tblchitietphieunhap.getWidth()/5);
        columnmodelchitietpn.getColumn(1).setPreferredWidth(tblchitietphieunhap.getWidth()/5);
        columnmodelchitietpn.getColumn(2).setPreferredWidth(tblchitietphieunhap.getWidth()/5);
        columnmodelchitietpn.getColumn(3).setPreferredWidth(tblchitietphieunhap.getWidth()/5);
        columnmodelchitietpn.getColumn(4).setPreferredWidth(tblchitietphieunhap.getWidth()/5);

        
        scrchitietpn = new JScrollPane(tblchitietphieunhap);
        scrchitietpn.setBounds(0, 0, pnlthongtinchitietpn.getWidth()-30, 490);
        pnltablechitietpn.add(scrchitietpn);
        pnlthongtinchitietpn.add(pnltablechitietpn);
        
        
        loadDataCmbLoai();
        loadDataLenBangSanPham();
        loadDataTableCTHoaDon();
        loadDataTableHoaDon();
        
        txtnhanvien.setText("26 - Tôn Thành - Tâm");
    }
    
    public void addevents()
    {
        btnResetnhaphang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtmasp.setText("");
                txttensp.setText("");
                txtdongia.setText("");
                txtsoluong.setText("");
                cmbLoai.setSelectedIndex(0);
                cmbNcc.setSelectedIndex(0);
                cmbNcc.setEnabled(true);
                loadDataLenBangSanPham();
                removeallitem();

            }
        });
        
        btnResetphieunhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txthddongia.setText("");
                txthdsanpham.setText("");
                txthdsoluong.setText("");
                txthdthanhtien.setText("");
                txtmanv.setText("");
                txtghichu.setText("");
                txtmahd.setText("");
                txtngaylap.setText("");
                txttongtien.setText("");
                loadDataTableCTHoaDon();
                loadDataTableHoaDon();
            }
        });
        
        btnnhapsanpham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themvaogio();
            }
        });
        
        btnxoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoasanphamnhap();
            }
        });
        
        txttimkiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                btntimkiem.doClick();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                btntimkiem.doClick();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                btntimkiem.doClick();
            }
        });
        
        btntimkiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenBangSanPham(txttimkiem.getText());
            }
        });
        
        tblsanpham.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuliclicktablesanpham();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        tblphieunhap.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuliclicktablephieunhap();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        tblchitietphieunhap.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuliclicktabechitietphieunhap();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        btnxacnhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatHoaDonBanHang();
            }
        });
        
        txttientoithieu.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    txttientoida.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        txttientoida.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    loadDataTablePhieuNhapTheoGia(txttientoithieu.getText(), txttientoida.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        txtngaytoithieu.addKeyListener(new KeyListener() {
             @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    txtngaytoida.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        txtngaytoida.addKeyListener(new KeyListener() {
             @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    loadDataTablePhieuNhapTheoNgay(txtngaytoithieu.getText(), txtngaytoida.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
    
    File fileAnhSP;
    DecimalFormat dcf = new DecimalFormat("###,###");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
    
    private void removeallitem()
    {
        int row = tblgiohang.getRowCount();
        if(row > 0)
        {
            for(int i = 0; i < row; i++)
            {
                dtmgiohang.removeRow(0);
            }
        }
    }
    
    private void loadDataLenBangSanPham() {
        spBUS.docListSanPham();
        dtmsanpham.setRowCount(0);

        ArrayList<SanPham> dssp = spBUS.getListSanPham();

        DecimalFormat dcf = new DecimalFormat("###,###");

        for (SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            String tenLoai = loaiBUS.getTenLoai(sp.getMaLoai());
            vec.add(tenLoai);
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getHinhAnh());
            vec.add(sp.getMoTa());
            dtmsanpham.addRow(vec);
        }
    }
    
    private void loadDataLenBangSanPham(String tukhoa) {
        spBUS.docListSanPham();
        dtmsanpham.setRowCount(0);

        ArrayList<SanPham> dssp = spBUS.getSanPhamTheoTen(tukhoa);

        DecimalFormat dcf = new DecimalFormat("###,###");

        for (SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            String tenLoai = loaiBUS.getTenLoai(sp.getMaLoai());
            vec.add(tenLoai);
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getHinhAnh());
            vec.add(sp.getMoTa());
            dtmsanpham.addRow(vec);
        }
    }
    
    private void loadDataTableHoaDon() {
        hoadonBUS.docDanhSach();
        ArrayList<HoaDon> dspn = hoadonBUS.getListHoaDon();
        duaDataVaoTablePhieuNhap(dspn);
    }

    private void duaDataVaoTablePhieuNhap(ArrayList<HoaDon> dspn) {
        if (dspn != null) {
            dtmhoadon.setRowCount(0);
            for (HoaDon pn : dspn) {
                Vector vec = new Vector();
                vec.add(pn.getMaHD());
                vec.add(sdf.format(pn.getNgayLap()));
                vec.add(dcf.format(pn.getTongTien()));
                dtmhoadon.addRow(vec);
            }
        }
    }
    
    private void loadDataTablePhieuNhapTheoGia(String giatoithieu, String giatoida)
    {
        if(giatoithieu.isEmpty())
        {
            new MyDialog("Giá tối thiểu không được để trống", MyDialog.WARNING_DIALOG);
        }
        else if(giatoida.isEmpty())
        {
            new MyDialog("Giá tối đa không được để trống", MyDialog.WARNING_DIALOG);
        }
        else
        {
            ArrayList<HoaDon> dshd = new ArrayList<>();
            hoadonBUS.getListHoaDonTheoGia(giatoithieu, giatoida);
            duaDataVaoTablePhieuNhap(dshd);
        }
                
    }
    
    private void loadDataTablePhieuNhapTheoNgay(String ngaytoithieu, String ngiatoida)
    {
        
        if(ngaytoithieu.isEmpty())
        {
            new MyDialog("Ngày tối thiểu không được để trống", MyDialog.WARNING_DIALOG);
        }
        else if(ngiatoida.isEmpty())
        {
            new MyDialog("Ngày tối đa không được để trống", MyDialog.WARNING_DIALOG);
        }
        else
        {
            ArrayList<HoaDon> dshd = new ArrayList<>();
            hoadonBUS.getListHoaDonTheoNgay(ngaytoithieu, ngiatoida);
            duaDataVaoTablePhieuNhap(dshd);
        }
    }
    
    private void loadDataTableCTHoaDon() {
        dtmchitiethoadon.setRowCount(0);
        cthoadonBUS.docListCTHoaDon();
        ArrayList<CTHoaDon> dsct = cthoadonBUS.getListCTHoaDon();
        if (dsct != null) {
            for (CTHoaDon ct : dsct) {
                Vector vec = new Vector();
                vec.add(ct.getMaHD());
                vec.add(ct.getMaSP());
                vec.add(dcf.format(ct.getSoLuong()));
                vec.add(dcf.format(ct.getDonGia()));
                vec.add(dcf.format(ct.getThanhTien()));
                dtmchitiethoadon.addRow(vec);
            }
        }
    }
    
    private void loadDataTableCTPhieuNhap(String ma) {
        dtmchitiethoadon.setRowCount(0);
        cthoadonBUS.docListCTHoaDon();
        ArrayList<CTHoaDon> dsct = cthoadonBUS.getListCTHoaDonTheoMaHD(ma);
        if (dsct != null) {
            for (CTHoaDon ct : dsct) {
                Vector vec = new Vector();
                vec.add(ct.getMaSP());
                vec.add(dcf.format(ct.getSoLuong()));
                vec.add(dcf.format(ct.getDonGia()));
                vec.add(dcf.format(ct.getThanhTien()));
                dtmchitiethoadon.addRow(vec);
            }
        }
    }
    
    private void loadAnh(String anh) {
        lblAnhSP.setIcon(getAnhSP(anh));
    }
    
    private void luuFileAnh() {
            BufferedImage bImage = null;
            try {
                File initialImage = new File(fileAnhSP.getPath());
                bImage = ImageIO.read(initialImage);

                ImageIO.write(bImage, "png", new File("images/" + fileAnhSP.getName()));

            } catch (IOException e) {
                System.out.println("Exception occured :" + e.getMessage());
            }
        }
    
    private void xuLyChonAnh() {
        JFileChooser fileChooser = new MyFileChooser("images/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhSP = fileChooser.getSelectedFile();
            lblAnhSP.setIcon(getAnhSP(fileAnhSP.getPath()));
        }
    }
    
    private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("images/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileAnhSP = new File(src);
        } catch (IOException e) {
            fileAnhSP = new File("imgs/anhthe/avatar.jpg");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }
    
    private void loadDataCmbLoai() {
        loaiBUS.docDanhSachLoai();
        cmbLoai.removeAllItems();
        ArrayList<LoaiSP> dsl = new ArrayList<>();
        dsl = loaiBUS.getDanhSachLoai();
        cmbLoai.addItem("0 - Chọn loại");
        for (LoaiSP loai : dsl) {
            cmbLoai.addItem(loai.getMaLoai() + " - " + loai.getTenLoai());
        }
        cmbLoai.addItem("Khác...");
    }
    
    private void themvaogio()
    {
        try {
            
            String ma= txtmasp.getText();
            String ten = txttensp.getText();
            String[] loaitmp = (cmbLoai.getSelectedItem() + "").split(" - ");
            String loai = loaitmp[1];
            SanPham sp = spBUS.getSanPham(ma);
            if(ten.isEmpty())
            {
                new MyDialog("Chưa chọn sản phẩm để thêm vào", MyDialog.ERROR_DIALOG);
            }
            else if(txtsoluong.getText().isEmpty())
            {
                new MyDialog("Chưa nhập số lượng để thêm vào", MyDialog.ERROR_DIALOG);
            }
            else{
                int soluong = Integer.parseInt(txtsoluong.getText());
                int dongia = Integer.parseInt(txtdongia.getText().replace(",", ""));
                int thanhtien = dongia * soluong;

                if(sp.getSoLuong() - soluong < 0)
                {
                    new MyDialog("Số lượng không đủ để thêm vào", MyDialog.ERROR_DIALOG);
                }
                else
                {
                    DecimalFormat dcf = new DecimalFormat("###,###");
                
                    int row = tblgiohang.getRowCount();
                    for(int i = 0; i<row ;i++)
                    {
                        String tensp = tblgiohang.getValueAt(i, 1) + "";
                        int giasp = Integer.parseInt((tblgiohang.getValueAt(i, 4) + "").replace(",", ""));
                        String loaisp = (tblgiohang.getValueAt(i, 2)+"").split(" - ")[1];
                        if(tensp.equals(ten) && giasp == dongia && loaisp.equals(loai))
                        {
                            int sl = Integer.parseInt(tblgiohang.getValueAt(i, 3) + "") + soluong;
                            int thanhtiennew = sl * giasp;
                            tblgiohang.setValueAt(sl, i, 3);
                            tblgiohang.setValueAt(thanhtiennew, i, 5);
                            txtmasp.setText("");
                            txttensp.setText("");
                            txtdongia.setText("");
                            txtsoluong.setText("");
                            cmbLoai.setSelectedIndex(0);
                            return;
                        }
                    }

                    Vector vec = new Vector();
                    vec.add(ma);
                    vec.add(ten);
                    vec.add(cmbLoai.getSelectedItem() + "");
                    vec.add(soluong);
                    vec.add(dcf.format(dongia));
                    vec.add(dcf.format(thanhtien));
                    dtmgiohang.addRow(vec);

                    SanPham sptmp = spBUS.getSanPham(ma);
                    String ncc = nhacungcapBUS.gettennhacungcap(sptmp.getMaNCC());
                    cmbNcc.setSelectedItem(sptmp.getMaNCC() + " - " + ncc);
                    cmbNcc.setEnabled(false);

                    txtmasp.setText("");
                    txttensp.setText("");
                    txtdongia.setText("");
                    txtsoluong.setText("");
                    cmbLoai.setSelectedIndex(0);
                }
            }
        } catch (Exception e) {
            new MyDialog("Nhập số hợp lệ cho Đơn giá và Số lượng!", MyDialog.ERROR_DIALOG);
        }
    }
    
    private void xoasanphamnhap()
    {
        int row = tblgiohang.getSelectedRow();
        if(row > -1)
        {
            dtmgiohang.removeRow(row);
        } else {
            new MyDialog("Chưa chọn sản phẩm để xoá", MyDialog.ERROR_DIALOG);
        }
    }
    
    private void xuliclicktablesanpham()
    {
        int row = tblsanpham.getSelectedRow();
        if(row > - 1)
        {
            String ma = tblsanpham.getValueAt(row, 0) + "";
            String ten = tblsanpham.getValueAt(row, 1) + "";
            String loai = tblsanpham.getValueAt(row, 2) + "";
            String donGia = tblsanpham.getValueAt(row, 3) + "";
            String soLuong = tblsanpham.getValueAt(row, 4) + "";
            String anh = tblsanpham.getValueAt(row, 5) + "";
            String mota = tblsanpham.getValueAt(row, 6) + "";
            
            
            txtmasp.setText(ma);
            txttensp.setText(ten);
            txtdongia.setText(donGia);
            
            txtsoluong.requestFocus();
            txtsoluong.setText("");
            
            int flagloai = 0;
            for (int i = 0; i < cmbLoai.getItemCount(); i++) {
                if (cmbLoai.getItemAt(i).contains(loai)) {
                    flagloai = i;
                    break;
                }
            }
            
            cmbLoai.setSelectedIndex(flagloai);
            txtareamota.setText(mota);
            loadAnh("images\\" + anh);
        }
    }
    
    private void xuliclicktablegiohang()
    {
        int row = tblgiohang.getSelectedRow();
        if(row > - 1)
        {
            String ma = tblgiohang.getValueAt(row, 0) + "";
            String ten = tblgiohang.getValueAt(row, 1) + "";
            String loai = tblgiohang.getValueAt(row, 2) + "";
            String soLuong = tblgiohang.getValueAt(row, 3) + "";
            String donGia = tblgiohang.getValueAt(row, 4) + "";
            
            SanPham sp = spBUS.getSanPham(ma);
            
            String anh = sp.getHinhAnh();
            String mota = sp.getMoTa();
            txtmasp.setText(ma);
            txttensp.setText(ten);
            txtdongia.setText(donGia);
            
            int flagloai = 0;
            for (int i = 0; i < cmbLoai.getItemCount(); i++) {
                if (cmbLoai.getItemAt(i).contains(loai)) {
                    flagloai = i;
                    break;
                }
            }
            
            cmbLoai.setSelectedIndex(flagloai);
            txtareamota.setText(mota);
            loadAnh("images\\" + anh);
        }
    }
    
    private void xuliclicktabechitietphieunhap()
    {
        int row = tblchitietphieunhap.getSelectedRow();
        if(row > -1)
        {
            txthdmahd.setText(tblchitietphieunhap.getValueAt(row, 0) + "");
            txthdsanpham.setText( spBUS.getSanPham(tblchitietphieunhap.getValueAt(row, 1) + "").getTenSP());
            txthdsoluong.setText(tblchitietphieunhap.getValueAt(row, 2) + "");
            txthddongia.setText(tblchitietphieunhap.getValueAt(row, 3) + "");
            txthdthanhtien.setText(tblchitietphieunhap.getValueAt(row, 4) + "");
        }
    }
    
    private void xuliclicktablephieunhap()
    {
        int row = tblphieunhap.getSelectedRow();
        if(row > -1)
        {
            ArrayList<HoaDon> dspn = hoadonBUS.getListHoaDon();
            String manv;
            manv = "";
            for(HoaDon i : dspn)
            {
                if(i.getMaHD() == Integer.parseInt(tblphieunhap.getValueAt(row, 0) + ""))
                {
                    manv = String.valueOf(i.getMaNV());
                    break;
                }
            }
            HoaDon hd = hoadonBUS.getHoaDon(tblphieunhap.getValueAt(row, 0) + "");
            txtmahd.setText(tblphieunhap.getValueAt(row, 0) + "");
            txtmakh.setText(hd.getMaKH() + "");
            txtmanv.setText(manv + "");
            txtghichu.setText(hd.getGhiChu());
            txtngaylap.setText(tblphieunhap.getValueAt(row, 1) + "");
            txttongtien.setText(tblphieunhap.getValueAt(row, 2)+ "");
            loadDataTableCTPhieuNhap(tblphieunhap.getValueAt(row, 0) + "");
        }
    }
    
    private void xuLyXuatHoaDonBanHang() {
        ArrayList<Vector> dsGioHang = new ArrayList<>();
        int row = tblgiohang.getRowCount();
        if (row == 0) return;
        int tongTien = 0;
        for (int i = 0; i < row; i++) {
            Vector vec = new Vector();
            vec.add(tblgiohang.getValueAt(i, 0));
            vec.add(tblgiohang.getValueAt(i, 1));
            vec.add(tblgiohang.getValueAt(i, 3));
            vec.add(tblgiohang.getValueAt(i, 4));
            vec.add(tblgiohang.getValueAt(i, 5));
            tongTien += Integer.parseInt((tblgiohang.getValueAt(i, 5) + "").replace(",", ""));
            dsGioHang.add(vec);
        }

        XuatHoaDonGUI hoaDonUI = new XuatHoaDonGUI(dsGioHang, tongTien, txtnhanvien.getText() + "");
        hoaDonUI.setVisible(true);
        if (hoaDonUI.checkBanHang) {
            dtmgiohang.setRowCount(0);
            loadDataLenBangSanPham();
            loadDataTableCTHoaDon();
            loadDataTableHoaDon();
        }
    }
    
    private void loadnhanvien()
    {
        ArrayList<NhanVien> dsnv = nhanvienBUS.getDanhSachNhanVien();
        for(NhanVien i : dsnv)
        {
            if(String.valueOf(i.getMaNV()).equals(dangnhapBUS.taiKhoanLogin.getMaNV()))
            {
                txtnhanvien.setText(i.getMaNV() + " - " + i.getHo() + " " + i.getTen());
                break;
            }
        }
    }
    
}
