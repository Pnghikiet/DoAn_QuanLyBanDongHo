/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.CTPhieuNhapBUS;
import BUS.DangNhapBUS;
import BUS.LoaiBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import Customs.MyDialog;
import Customs.MyFileChooser;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import DAO.MyConnect;
import DTO.CTPhieuNhap;
import DTO.LoaiSP;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.PhieuNhap;
import DTO.SanPham;
import GUI.DlgQuanLyLoai;
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
public class PnQuanLyNhapHangGUI extends JPanel {
    
    LoaiBUS loaiBUS = new LoaiBUS();
    SanPhamBUS spBUS = new SanPhamBUS();
    NhaCungCapBUS nhacungcapBUS = new NhaCungCapBUS();
    NhanVienBUS nhanvienBUS = new NhanVienBUS();
    DangNhapBUS dangnhapBUS = new DangNhapBUS();
    PhieuNhapBUS phieunhapBUS = new PhieuNhapBUS();
    CTPhieuNhapBUS ctphieunhapBUS = new CTPhieuNhapBUS();
    private TableCellRenderer centerRenderer;
    JPanel pnlnhaphang, pnlphieunhap;
    JTabbedPane tabpnlqlnhaphang;
    DefaultTableModel dtmsanpham, dtmnhaphang, dtmchitietphieunhap, dtmphieunhap;
    JTable tblsanpham, tblnhaphang, tblchitietphieunhap, tblphieunhap;
    JLabel lbltittlepnlnhaphang, lbltittlepnlphieunhap, lbltimkiemnhaphang, lbltittlenhaphang, lblthongtinsanpham, lblthongtinphieunhap,  lblmasp, lbltensp, lbldongia, lblsoluong
            , lblnhacungcap, lblnhanvien, lblAnhSP, lblloaisanpham, lblmotasanpham, lblmapn, lblmanv, lblmancc, lblngaylap, lbltongtien, lbltittlechitietpn, lblchitietsanpham, lblchitietsoluong
            , lblchitietdongia, lblchitietthanhtien;
    JComboBox<String> cmbNcc, cmbLoai;
    JButton btnResetnhaphang, btnnhapsanpham, btnChonAnh, btnxacnhan, btnxoa, btnResetphieunhap, btntimkiem;
    JTextField txttimkiem, txtmasp, txttensp, txtdongia, txtsoluong, txtnhanvien, txtmapn, txtmanv, txtmancc, txtngaylap, txttongtien,
             txtpnsanpham, txtpndongia, txtpnsoluong, txtpnthanhtien, txttientoithieu, txttientoida, txtngaytoithieu, txtngaytoida;
    JScrollPane scrsanpham, scrnhaphang, scrchitietpn;
    
    
    public PnQuanLyNhapHangGUI()
    {
        changLNF("Windows");
        addcontrols();
        addevents();
    }
    
    public void addcontrols()
    {
        tabpnlqlnhaphang = new JTabbedPane();
        pnlnhaphang = new JPanel();
        pnlphieunhap = new JPanel();
        lbltittlepnlnhaphang = new JLabel("KHO HÀNG");
        lbltittlepnlphieunhap = new JLabel("PHIẾU NHẬP");
        lbltimkiemnhaphang = new JLabel("Tìm kiếm");
        lbltittlenhaphang = new JLabel("NHẬP HÀNG");
        lblthongtinphieunhap = new JLabel("THÔNG TIN PHIỀU NHẬP");
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
        lblmapn = new JLabel("MÃ PN");
        lblmanv = new JLabel("Mã NV");
        lblmancc = new JLabel("Mã NCC");
        lblngaylap = new JLabel("Ngày Lập");
        lbltongtien = new JLabel("Tổng Tiền");
        lbltittlechitietpn = new JLabel("Chi Tiết Phiếu Nhập");
        lblchitietsanpham = new JLabel("Sản Phẩm");
        lblchitietsoluong = new JLabel("Số lượng");
        lblchitietdongia = new JLabel("Đơn Giá");
        lblchitietthanhtien = new JLabel("Thành Tiền");
        btnResetnhaphang = new JButton();
        btnChonAnh = new JButton("Chọn Ảnh");
        btnxacnhan = new JButton("Xác Nhận");
        btnxoa = new JButton("Xoá");
        btnnhapsanpham = new JButton("Chọn nhập sản phẩm");
        btnResetphieunhap = new JButton();
        btntimkiem = new JButton();
        txttimkiem = new JTextField();
        txtmasp = new JTextField();
        txttensp = new JTextField();
        txtdongia = new JTextField();
        txtsoluong = new JTextField();
        txtnhanvien = new JTextField();
        txtmapn = new JTextField();
        txtmanv = new JTextField();
        txtmancc = new JTextField();
        txtngaylap = new JTextField();
        txttongtien = new JTextField();
        txtpnsanpham = new JTextField();
        txtpndongia = new JTextField();
        txtpnsoluong = new JTextField();
        txtpnthanhtien = new JTextField();
        txttientoithieu = new JTextField();
        txttientoida = new JTextField();
        txtngaytoithieu = new JTextField();
        txtngaytoida = new JTextField();
        cmbNcc = new JComboBox<>();
        cmbLoai = new JComboBox<>();
        
        
        setSize(1000, 700);
        setLayout(null);
        
        pnlnhaphang.setLayout(null);
        pnlphieunhap.setLayout(null);
        
        tabpnlqlnhaphang.add("Nhập hàng",pnlnhaphang);
        tabpnlqlnhaphang.add("Phiếu nhập",pnlphieunhap);
        tabpnlqlnhaphang.setBounds(0, 00, 983, 690);
        add(tabpnlqlnhaphang);
        
        //Tab nhập hàng
        
        lbltittlepnlnhaphang.setBounds(230, 20, 100, 20);
        pnlnhaphang.add(lbltittlepnlnhaphang);
        
        lbltimkiemnhaphang.setBounds(190, 60, 100, 20);
        pnlnhaphang.add(lbltimkiemnhaphang);
        
        txttimkiem.setBounds( 280, 60, 180, 22);
        pnlnhaphang.add(txttimkiem);
        
        btntimkiem.setBounds(300, 60, 10, 22);
        btntimkiem.setVisible(false);
        pnlnhaphang.add(btntimkiem);
        
        JPanel pnltablesanpham = new JPanel();
        pnltablesanpham.setBounds(10, 120, 600, 205);
        pnltablesanpham.setLayout(null);
        
        dtmsanpham = new DefaultTableModel();
        dtmsanpham.addColumn("Mã SP");
        dtmsanpham.addColumn("Tên Sản Phẩm");
        dtmsanpham.addColumn("Loại");
        dtmsanpham.addColumn("Đơn Giá");
        dtmsanpham.addColumn("Số Lượng");
        
        tblsanpham = new JTable(dtmsanpham);
        tblsanpham.setSize(600, 200);
        
        JTableHeader headertblsanpham = tblsanpham.getTableHeader();
        headertblsanpham.setBackground(Color.white);
        headertblsanpham.setEnabled(false);
        
        TableColumnModel columnModelBanHang = tblsanpham.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(125);
        columnModelBanHang.getColumn(1).setPreferredWidth(200);
        columnModelBanHang.getColumn(2).setPreferredWidth(125);
        columnModelBanHang.getColumn(3).setPreferredWidth(125);
        columnModelBanHang.getColumn(4).setPreferredWidth(125);

        scrsanpham = new JScrollPane(tblsanpham);
        scrsanpham.setBounds(0, 0, 600, 200);
        pnltablesanpham.add(scrsanpham);
        pnlnhaphang.add(pnltablesanpham);
        
        lbltittlenhaphang.setBounds(230, 330, 100, 20);
        pnlnhaphang.add(lbltittlenhaphang);
        
        JPanel pnltablenhaphang = new JPanel();
        pnltablenhaphang.setLayout(null);
        pnltablenhaphang.setBounds(10, 380, 600, 205);
        
        dtmnhaphang = new DefaultTableModel();
        dtmnhaphang.addColumn("Mã SP");
        dtmnhaphang.addColumn("Tên Sản Phẩm");
        dtmnhaphang.addColumn("Loại");
        dtmnhaphang.addColumn("Số lượng");
        dtmnhaphang.addColumn("Đơn Giá");
        dtmnhaphang.addColumn("Thành Tiền");
        
        tblnhaphang = new JTable(dtmnhaphang);
        tblnhaphang.setSize(600, 200);

        JTableHeader headertblnhaphang =  tblnhaphang.getTableHeader();
        headertblnhaphang.setBackground(Color.white);
        headertblnhaphang.setEnabled(false);
        
        TableColumnModel columnmodelnhaphang = tblnhaphang.getColumnModel();
        columnmodelnhaphang.getColumn(0).setPreferredWidth(75);
        columnmodelnhaphang.getColumn(1).setPreferredWidth(200);
        columnmodelnhaphang.getColumn(2).setPreferredWidth(100);
        columnmodelnhaphang.getColumn(3).setPreferredWidth(75);
        columnmodelnhaphang.getColumn(4).setPreferredWidth(100);
        columnmodelnhaphang.getColumn(5).setPreferredWidth(100);
        
        scrnhaphang = new JScrollPane(tblnhaphang);
        scrnhaphang.setBounds(0, 0, 600, 200);
        pnltablenhaphang.add(scrnhaphang);
        pnlnhaphang.add(pnltablenhaphang);
        
        ImageIcon icon = new ImageIcon("images\\Refresh-icon.png");
        btnResetnhaphang.setBounds(310, 19, 28, 28);
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(btnResetnhaphang.getWidth(), btnResetnhaphang.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledicon = new ImageIcon(imgscale);
        btnResetnhaphang.setIcon(scaledicon);
        pnlnhaphang.add(btnResetnhaphang);
        
        //Thông tin sản phẩm
        
        JPanel pnlthongtinsanpham = new JPanel();
        pnlthongtinsanpham.setBounds(650, 0, 400, 700);
        pnlthongtinsanpham.setLayout(null);
        lblthongtinsanpham.setBounds(95, 15, 160, 20);
        pnlthongtinsanpham.add(lblthongtinsanpham);
        
        JPanel pnlthongtinmasanpham, pnlthongtintensanpham, pnlthongdongiasp, pnlthongtinsoluongsp, pnlthongtinnhacungcap
                , pnlthongloaisanpham, pnlmota_hinhan, pnlthongtinnhanvien;
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
        txtnhanvien.setEnabled(false);
        pnlthongtinnhanvien.add(txtnhanvien);
        
        pnlthongtinnhacungcap = new JPanel();
        pnlthongtinnhacungcap.setBounds(0,355, pnlthongtinsanpham.getWidth(), 23);
        pnlthongtinsanpham.add(pnlthongtinnhacungcap);
        pnlthongtinnhacungcap.setLayout(null);
        lblnhacungcap.setBounds(10, 3, 100, 22);
        pnlthongtinnhacungcap.add(lblnhacungcap);
        cmbNcc.setBounds(120, 3, 180, 22);
        pnlthongtinnhacungcap.add(cmbNcc);
        
        btnnhapsanpham.setBounds( 70, 390, 175, 22);
        pnlthongtinsanpham.add(btnnhapsanpham);
        
        btnxoa.setBounds(30, 420, 100, 22);
        pnlthongtinsanpham.add(btnxoa);
        
        btnxacnhan.setBounds(190, 420, 100, 22);
        pnlthongtinsanpham.add(btnxacnhan);
        
        pnlnhaphang.add(pnlthongtinsanpham);
        
        
        
        
        //Tab phiếu nhập
        
        pnlphieunhap.setSize(983, 690);
        
        JPanel pnlthongtinphieunhap = new JPanel();
        pnlthongtinphieunhap.setBounds(0, 0, 400, 690);
        pnlthongtinphieunhap.setLayout(null);
        pnlphieunhap.add(pnlthongtinphieunhap);
        
        lbltittlepnlphieunhap.setBounds(165, 20, 100, 22);
        pnlthongtinphieunhap.add(lbltittlepnlphieunhap);
        
        btnResetphieunhap.setBounds(250, 15, 28, 28);
        btnResetphieunhap.setIcon(scaledicon);
        pnlthongtinphieunhap.add(btnResetphieunhap);
        
        JPanel pnlphieunhapmapn, pnlphieunhapmanv, pnlphieunhapmancc, pnlgaynhap, pnltongtien;
        pnlphieunhapmapn = new JPanel();
        pnlphieunhapmapn.setBounds(0, 55, pnlthongtinphieunhap.getWidth(), 23);
        pnlthongtinphieunhap.add(pnlphieunhapmapn);
        pnlphieunhapmapn.setLayout(null);
        lblmapn.setBounds(50, 3, 100, 22);
        pnlphieunhapmapn.add(lblmapn);
        txtmapn.setBounds(150, 3, 180, 22);
        txtmapn.setEditable(false);
        pnlphieunhapmapn.add(txtmapn);
        
        pnlphieunhapmancc = new JPanel();
        pnlphieunhapmancc.setBounds(0, 105, pnlthongtinphieunhap.getWidth(), 23);
        pnlthongtinphieunhap.add(pnlphieunhapmancc);
        pnlphieunhapmancc.setLayout(null);
        lblmanv.setBounds(50, 3, 100, 22);
        pnlphieunhapmancc.add(lblmanv);
        txtmanv.setBounds(150, 3, 180, 22);
        txtmanv.setEditable(false);
        pnlphieunhapmancc.add(txtmanv);
        
        pnlphieunhapmanv = new JPanel();
        pnlphieunhapmanv.setBounds(0, 155, pnlthongtinphieunhap.getWidth(), 23);
        pnlthongtinphieunhap.add(pnlphieunhapmanv);
        pnlphieunhapmanv.setLayout(null);
        lblmancc.setBounds(50, 3, 100, 22);
        pnlphieunhapmanv.add(lblmancc);
        txtmancc.setBounds(150, 3, 180, 22);
        txtmancc.setEditable(false);
        pnlphieunhapmanv.add(txtmancc);
        
        pnlgaynhap = new JPanel();
        pnlgaynhap.setBounds(0, 205, pnlthongtinphieunhap.getWidth(), 23);
        pnlthongtinphieunhap.add(pnlgaynhap);
        pnlgaynhap.setLayout(null);
        lblngaylap.setBounds(50, 3, 100, 22);
        pnlgaynhap.add(lblngaylap);
        txtngaylap.setBounds(150, 3, 180, 22);
        txtngaylap.setEditable(false);
        pnlgaynhap.add(txtngaylap);
        
        pnltongtien = new JPanel();
        pnltongtien.setBounds(0, 255, pnlthongtinphieunhap.getWidth(), 23);
        pnlthongtinphieunhap.add(pnltongtien);
        pnltongtien.setLayout(null);
        lbltongtien.setBounds(50, 3, 100, 22);
        pnltongtien.add(lbltongtien);
        txttongtien.setBounds(150, 3, 180, 22);
        txttongtien.setEditable(false);
        pnltongtien.add(txttongtien);
        
        JLabel lblpntimkiem = new JLabel("Tìm Kiếm:");
        lblpntimkiem.setBounds(18, 300, 100, 22);
        pnlthongtinphieunhap.add(lblpntimkiem);
        
        JLabel lblden1, lblden2, lblgia, lblngay;
        
        JPanel pnltimkiemtien, pnltimkiemngay;
        
        pnltimkiemtien = new JPanel();
        pnltimkiemtien.setBounds(0, 330, pnlthongtinphieunhap.getWidth(), 30);
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
        pnlthongtinphieunhap.add(pnltimkiemtien);
        
        pnltimkiemngay = new JPanel();
        pnltimkiemngay.setBounds(0, 380, pnlthongtinphieunhap.getWidth(), 30);
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
        pnlthongtinphieunhap.add(pnltimkiemngay);
        
        JPanel pnltablepn = new JPanel();
        pnltablepn.setLayout(null);
        pnltablepn.setBounds(0, 425, pnlthongtinphieunhap.getWidth(), 250);
        
        dtmphieunhap = new DefaultTableModel();
        dtmphieunhap.addColumn("Mã PN");
        dtmphieunhap.addColumn("Ngày lập");
        dtmphieunhap.addColumn("Tổng tiền");
        
        tblphieunhap = new JTable(dtmphieunhap);
        tblphieunhap.setSize(pnlthongtinphieunhap.getWidth(), 210);

        JTableHeader headertblpn =  tblphieunhap.getTableHeader();
        headertblpn.setBackground(Color.white);
        headertblpn.setEnabled(false);
        
        TableColumnModel columnmodelpn = tblphieunhap.getColumnModel();
        columnmodelpn.getColumn(0).setPreferredWidth(tblphieunhap.getWidth()/3);
        columnmodelpn.getColumn(1).setPreferredWidth(tblphieunhap.getWidth()/3);
        columnmodelpn.getColumn(2).setPreferredWidth(tblphieunhap.getWidth()/3);
        
        scrchitietpn = new JScrollPane(tblphieunhap);
        scrchitietpn.setBounds(0, 0, pnlthongtinphieunhap.getWidth(), 210);
        pnltablepn.add(scrchitietpn);
        pnlthongtinphieunhap.add(pnltablepn);
        
        JPanel pnlthongtinchitietpn = new JPanel();
        pnlthongtinchitietpn.setBounds(405, 0, 595, pnlphieunhap.getHeight());
        pnlthongtinchitietpn.setLayout(null);
        pnlphieunhap.add(pnlthongtinchitietpn);
        
        lbltittlechitietpn.setBounds(240, 20, 180, 22);
        pnlthongtinchitietpn.add(lbltittlechitietpn);
        
        JPanel pnlchitietsp ,pnlchitietdongia, pnlchitietsoluong, pnlchitietthanhtien;
        pnlchitietsp = new JPanel();
        pnlchitietsp.setBounds(0, 55, pnlthongtinchitietpn.getWidth(), 23);
        pnlthongtinchitietpn.add(pnlchitietsp);
        pnlchitietsp.setLayout(null);
        lblchitietsanpham.setBounds(175, 3, 100, 22);
        pnlchitietsp.add(lblchitietsanpham);
        txtpnsanpham.setBounds(260, 3, 180, 22);
        txtpnsanpham.setEditable(false);
        pnlchitietsp.add(txtpnsanpham);
        
        pnlchitietsoluong = new JPanel();
        pnlchitietsoluong.setBounds(0, 105, pnlthongtinchitietpn.getWidth(), 23);
        pnlthongtinchitietpn.add(pnlchitietsoluong);
        pnlchitietsoluong.setLayout(null);
        lblchitietsoluong.setBounds(175, 3, 100, 22);
        pnlchitietsoluong.add(lblchitietsoluong);
        txtpnsoluong.setBounds(260, 3, 180, 22);
        txtpnsoluong.setEditable(false);
        pnlchitietsoluong.add(txtpnsoluong);
        
        pnlchitietdongia = new JPanel();
        pnlchitietdongia.setBounds(0, 155, pnlthongtinchitietpn.getWidth(), 23);
        pnlthongtinchitietpn.add(pnlchitietdongia);
        pnlchitietdongia.setLayout(null);
        lblchitietdongia.setBounds(175, 3, 100, 22);
        pnlchitietdongia.add(lblchitietdongia);
        txtpndongia.setBounds(260, 3, 180, 22);
        txtpndongia.setEditable(false);
        pnlchitietdongia.add(txtpndongia);
        
        pnlchitietthanhtien = new JPanel();
        pnlchitietthanhtien.setBounds(0, 205, pnlthongtinchitietpn.getWidth(), 23);
        pnlthongtinchitietpn.add(pnlchitietthanhtien);
        pnlchitietthanhtien.setLayout(null);
        lblchitietthanhtien.setBounds(175, 3, 100, 22);
        pnlchitietthanhtien.add(lblchitietthanhtien);
        txtpnthanhtien.setBounds(260, 3, 180, 22);
        txtpnthanhtien.setEditable(false);
        pnlchitietthanhtien.add(txtpnthanhtien);

        JPanel pnltablechitietpn = new JPanel();
        pnltablechitietpn.setLayout(null);
        pnltablechitietpn.setBounds(10, 240, pnlthongtinchitietpn.getWidth(), 500);
        
        dtmchitietphieunhap = new DefaultTableModel();
        dtmchitietphieunhap.addColumn("Mã SP");
        dtmchitietphieunhap.addColumn("Số lượng");
        dtmchitietphieunhap.addColumn("Đơn Giá");
        dtmchitietphieunhap.addColumn("Thành Tiền");
        
        tblchitietphieunhap = new JTable(dtmchitietphieunhap);
        tblchitietphieunhap.setSize(pnlthongtinchitietpn.getWidth()-30, 490);

        JTableHeader headertblchitietpn =  tblchitietphieunhap.getTableHeader();
        headertblchitietpn.setBackground(Color.white);
        headertblchitietpn.setEnabled(false);
        
        TableColumnModel columnmodelchitietpn = tblchitietphieunhap.getColumnModel();
        columnmodelchitietpn.getColumn(0).setPreferredWidth(tblchitietphieunhap.getWidth()/4);
        columnmodelchitietpn.getColumn(1).setPreferredWidth(tblchitietphieunhap.getWidth()/4);
        columnmodelchitietpn.getColumn(2).setPreferredWidth(tblchitietphieunhap.getWidth()/4);
        columnmodelchitietpn.getColumn(3).setPreferredWidth(tblchitietphieunhap.getWidth()/4);

        
        scrchitietpn = new JScrollPane(tblchitietphieunhap);
        scrchitietpn.setBounds(0, 0, pnlthongtinchitietpn.getWidth()-30, 490);
        pnltablechitietpn.add(scrchitietpn);
        pnlthongtinchitietpn.add(pnltablechitietpn);
        
        
        loadDataLenBangSanPham();
        loadDataCmbLoai();
        loadDataCmbNcc();
        loadDataTableCTPhieuNhap();
        loadDataTablePhieuNhap();
//        loadnhanvien();
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
                txtpndongia.setText("");
                txtpnsanpham.setText("");
                txtpnsoluong.setText("");
                txtpnthanhtien.setText("");
                txtmanv.setText("");
                txtmancc.setText("");
                txtmapn.setText("");
                txtngaylap.setText("");
                txttongtien.setText("");
                loadDataTableCTPhieuNhap();
                loadDataTablePhieuNhap();
            }
        });
        
        btnnhapsanpham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nhapsanpham();
            }
        });
        
        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonAnh();
            }
        });
        
        btnxoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoasanphamnhap();
            }
        });
        
        cmbLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemLoai();
            }
        });
        
        cmbNcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadsanphamtheonhacungcap();
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
        
        tblnhaphang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuliclichtablenhaphang();
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
                xacnhan();
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
        int row = tblnhaphang.getRowCount();
        if(row > 0)
        {
            for(int i = 0; i < row; i++)
            {
                dtmnhaphang.removeRow(0);
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
            dtmsanpham.addRow(vec);
        }
    }
    
    private void loadDataTablePhieuNhap() {
        phieunhapBUS.docDanhSach();
        ArrayList<PhieuNhap> dspn = phieunhapBUS.getListPhieuNhap();
        duaDataVaoTablePhieuNhap(dspn);
    }

    private void duaDataVaoTablePhieuNhap(ArrayList<PhieuNhap> dspn) {
        if (dspn != null) {
            dtmphieunhap.setRowCount(0);
            for (PhieuNhap pn : dspn) {
                Vector vec = new Vector();
                vec.add(pn.getMaPN());
                vec.add(sdf.format(pn.getNgayLap()));
                vec.add(dcf.format(pn.getTongTien()));
                dtmphieunhap.addRow(vec);
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
            ArrayList<PhieuNhap> dspn = new ArrayList<>();
            phieunhapBUS.getListPhieuNhapTheoGia(giatoithieu, giatoida);
            duaDataVaoTablePhieuNhap(dspn);
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
            ArrayList<PhieuNhap> dspn = new ArrayList<>();
            phieunhapBUS.getListPhieuNhapTheoNgay(ngaytoithieu, ngiatoida);
            duaDataVaoTablePhieuNhap(dspn);
        }
    }
    
    private void loadDataTableCTPhieuNhap() {
        dtmchitietphieunhap.setRowCount(0);
        ctphieunhapBUS.docDanhSach();
        ArrayList<CTPhieuNhap> dsct = ctphieunhapBUS.getListPhieuNhap();
        if (dsct != null) {
            for (CTPhieuNhap ct : dsct) {
                Vector vec = new Vector();
                vec.add(ct.getMaSP());
                vec.add(dcf.format(ct.getSoLuong()));
                vec.add(dcf.format(ct.getDonGia()));
                vec.add(dcf.format(ct.getThanhTien()));
                dtmchitietphieunhap.addRow(vec);
            }
        }
    }
    
    private void loadDataTableCTPhieuNhap(String ma) {
        dtmchitietphieunhap.setRowCount(0);
        ctphieunhapBUS.docDanhSach();
        ArrayList<CTPhieuNhap> dsct = ctphieunhapBUS.getListPhieuNhap(ma);
        if (dsct != null) {
            for (CTPhieuNhap ct : dsct) {
                Vector vec = new Vector();
                vec.add(ct.getMaSP());
                vec.add(dcf.format(ct.getSoLuong()));
                vec.add(dcf.format(ct.getDonGia()));
                vec.add(dcf.format(ct.getThanhTien()));
                dtmchitietphieunhap.addRow(vec);
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

    private void loadDataCmbNcc()
    {
        nhacungcapBUS.docDanhSach();
        cmbNcc.removeAllItems();
        ArrayList<NhaCungCap> dsncc = nhacungcapBUS.getListNhaCungCap();
        cmbNcc.addItem("0 - Chọn nhà cung cấp");
        for (NhaCungCap ncc : dsncc) {
            cmbNcc.addItem(ncc.getMaNCC() + " - " + ncc.getTenNCC());
        }
        cmbNcc.addItem("Khác...");
    }
    
    private void xuLyThemLoai() {
        String loai = cmbLoai.getSelectedItem() + "";
        if (loai.equals("Khác..."))
        {
            DlgQuanLyLoai loaiGUI = new DlgQuanLyLoai();
            loaiGUI.setVisible(true);
            loaiGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadDataCmbLoai();
                }                
            });
        }
    }

    private void xuLyThemNcc() {
        String ncc = cmbNcc.getSelectedItem() + "";
        if (ncc.equals("Khác..."))
        {
            DlgQuanLyNcc nccGUI = new DlgQuanLyNcc();
            nccGUI.setVisible(true);
            nccGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadDataCmbNcc();
                }                
            });
        }
    }
    
    private void loadsanphamtheonhacungcap()
    {
        
        String ncc = cmbNcc.getSelectedItem() + "";
        int index = cmbNcc.getSelectedIndex();
        if(index == cmbNcc.getItemCount()-1)
        {
            xuLyThemNcc();
        }
        else if(index != 0)
        {
            String[] ncctmp = ncc.split(" - ");
            String tenncc = ncctmp[1];
            
            dtmsanpham.setRowCount(0);

            ArrayList<SanPham> dssptheoncc = spBUS.getListSanPham();

            DecimalFormat dcf = new DecimalFormat("###,###");
            
            for (SanPham sp : dssptheoncc) {
                if(nhacungcapBUS.gettennhacungcap(sp.getMaNCC()).equals(tenncc))
                {
                    Vector vec = new Vector();
                    vec.add(sp.getMaSP());
                    vec.add(sp.getTenSP());
                    String tenLoai = loaiBUS.getTenLoai(sp.getMaLoai());
                    vec.add(tenLoai);
                    vec.add(dcf.format(sp.getDonGia()));
                    vec.add(dcf.format(sp.getSoLuong()));
                    dtmsanpham.addRow(vec);
                }
            }
            cmbNcc.setEnabled(false);
        }
        else
        {
            xuLyThemNcc();
        }
    }
    
    private void nhapsanpham()
    {
        try {
            String ma= txtmasp.getText();
            String ten = txttensp.getText();
            String[] loaitmp = (cmbLoai.getSelectedItem() + "").split(" - ");
            String loai = loaitmp[1];
            if(ten.isEmpty())
            {
                new MyDialog("Chưa chọn sản phẩm để thêm vào", MyDialog.ERROR_DIALOG);
            }
            else if(loai.equals("0 - Chọn loại"))
            {
                new MyDialog("Vui lòng chọn Loại sản phẩm!", MyDialog.ERROR_DIALOG);
            }
            else if(txtsoluong.getText().isEmpty())
            {
                new MyDialog("Số lượng không được để trống!", MyDialog.ERROR_DIALOG);
            }
            else if(txtdongia.getText().isEmpty())
            {
                new MyDialog("Đơn giá không được để trống!", MyDialog.ERROR_DIALOG);
            }
            else{
                int soluong = Integer.parseInt(txtsoluong.getText());
                int dongia = Integer.parseInt(txtdongia.getText().replace(",", ""));
                int thanhtien = dongia * soluong;

                if(soluong == 0)
                {
                    new MyDialog("Số lượng sản phẩm không đủ", MyDialog.ERROR_DIALOG);
                }
                else
                {
                    DecimalFormat dcf = new DecimalFormat("###,###");
                
                    int row = tblnhaphang.getRowCount();
                    for(int i = 0; i<row ;i++)
                    {
                        String tensp = tblnhaphang.getValueAt(i, 1) + "";
                        int giasp = Integer.parseInt((tblnhaphang.getValueAt(i, 4) + "").replace(",", ""));
                        String loaisp = (tblnhaphang.getValueAt(i, 2)+"").split(" - ")[1];
                        if(tensp.equals(ten) && giasp == dongia && loaisp.equals(loai))
                        {
                            int sl = Integer.parseInt(tblnhaphang.getValueAt(i, 3) + "") + soluong;
                            int thanhtiennew = sl * giasp;
                            tblnhaphang.setValueAt(sl, i, 3);
                            tblnhaphang.setValueAt(thanhtiennew, i, 5);
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
                    dtmnhaphang.addRow(vec);

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
        int row = tblnhaphang.getSelectedRow();
        if(row > -1)
        {
            dtmnhaphang.removeRow(row);
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
            
            txtmasp.setText(ma);
            txttensp.setText(ten);
            txtdongia.setText("");
            txtdongia.requestFocus();
            txtsoluong.setText("");
            
            int flagloai = 0;
            for (int i = 0; i < cmbLoai.getItemCount(); i++) {
                if (cmbLoai.getItemAt(i).contains(loai)) {
                    flagloai = i;
                    break;
                }
            }
            
            cmbLoai.setSelectedIndex(flagloai);
        }
    }
    
    private void xuliclichtablenhaphang()
    {
        int row = tblphieunhap.getSelectedRow();
        if(row > -1)
        {
            String ma = tblsanpham.getValueAt(row, 0) + "";
            String ten = tblsanpham.getValueAt(row, 1) + "";
            String loai = tblsanpham.getValueAt(row, 2) + "";
            String soLuong = tblsanpham.getValueAt(row, 3) + "";
            String donGia = tblsanpham.getValueAt(row, 4) + "";
            
            txtmasp.setText(ma);
            txttensp.setText(ten);
            txtdongia.setText(donGia);
            txtdongia.requestFocus();
            txtsoluong.setText(soLuong);
            
            int flagloai = 0;
            for (int i = 0; i < cmbLoai.getItemCount(); i++) {
                if (cmbLoai.getItemAt(i).contains(loai)) {
                    flagloai = i;
                    break;
                }
            }
            
            cmbLoai.setSelectedIndex(flagloai);            
        }
    }
    
    private void xuliclicktabechitietphieunhap()
    {
        int row = tblchitietphieunhap.getSelectedRow();
        if(row > -1)
        {
            txtpnsanpham.setText( spBUS.getSanPham(tblchitietphieunhap.getValueAt(row, 0) + "").getTenSP());
            txtpnsoluong.setText(tblchitietphieunhap.getValueAt(row, 1) + "");
            txtpndongia.setText(tblchitietphieunhap.getValueAt(row, 2) + "");
            txtpnthanhtien.setText(tblchitietphieunhap.getValueAt(row, 3) + "");
        }
    }
    
    private void xuliclicktablephieunhap()
    {
        int row = tblphieunhap.getSelectedRow();
        if(row > -1)
        {
            ArrayList<PhieuNhap> dspn = phieunhapBUS.getListPhieuNhap();
            String manv,mancc;
            manv = "";
            mancc = "";
            for(PhieuNhap i : dspn)
            {
                if(i.getMaPN() == Integer.parseInt(tblphieunhap.getValueAt(row, 0) + ""))
                {
                    manv = String.valueOf(i.getMaNV());
                    mancc =  String.valueOf(i.getMaNCC());
                    break;
                }
            }
            txtmapn.setText(tblphieunhap.getValueAt(row, 0) + "");
            txtmanv.setText(manv + "");
            txtmancc.setText(mancc + "");
            txtngaylap.setText(tblphieunhap.getValueAt(row, 1) + "");
            txttongtien.setText(tblphieunhap.getValueAt(row, 2)+ "");
            loadDataTableCTPhieuNhap(tblphieunhap.getValueAt(row, 0) + "");
        }
    }
    
    private void xacnhan()
    {
        int row = tblnhaphang.getRowCount();
        if(row == 0)
        {
            new MyDialog("Chưa có gì để nhập hết á!", MyDialog.ERROR_DIALOG);
            return;
        }
        
        String nhanvien = txtnhanvien.getText();
        String[] ncctmp = (cmbNcc.getSelectedItem() + "").split(" - ");
        String ncc = ncctmp[1];
        
        if (ncc.equals("")) {
            new MyDialog("Hãy chọn nhà cung cấp!", MyDialog.ERROR_DIALOG);
            return;
        }

        ArrayList<CTPhieuNhap> dsct = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            int maSP = Integer.parseInt(tblnhaphang.getValueAt(i, 0) + "");
            int soLuong = Integer.parseInt(tblnhaphang.getValueAt(i, 3) + "");
            int donGia = Integer.parseInt((tblnhaphang.getValueAt(i, 4) + "").replace(",", ""));
            int thanhTien = Integer.parseInt((tblnhaphang.getValueAt(i, 5) + "").replace(",", ""));
            CTPhieuNhap ctpn = new CTPhieuNhap(0, maSP, soLuong, donGia, thanhTien);
            dsct.add(ctpn);
        }
        XuatPhieuNhapGUI xuatPhieuNhap = new XuatPhieuNhapGUI(cmbNcc.getSelectedItem() + "", nhanvien, dsct);
        xuatPhieuNhap.setVisible(true);
        if (xuatPhieuNhap.getCheckNhap()) {
            dtmnhaphang.setRowCount(0);
            spBUS.docListSanPham();
            loadDataLenBangSanPham();
            loadDataTableCTPhieuNhap();
            loadDataTablePhieuNhap();
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
