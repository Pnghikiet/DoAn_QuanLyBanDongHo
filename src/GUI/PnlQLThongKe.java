
package GUI;

import BUS.GiamGiaBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.ThongKeBUS;
import DTO.DoanhThuKhachHang;
import DTO.DoanhThuNhanVien;
import DTO.GiamGia;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.NhanVien;
import DTO.SanPham;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PnlQLThongKe extends JPanel{
     private JLabel lblTabbedThongKeTongQuat, lblTabbedThongKeChiTiet;
    final ImageIcon tabbedSelected = new ImageIcon("images/ManagerUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("images/ManagerUI/tabbed-btn.png");
    private JPanel pnlCardTabThongKe, pnlHT, pnlTK;
    private final CardLayout cardThongKeGroup = new CardLayout();
    
    private JLabel lblTitleTKTQ, lblTitleTKCT, lblTimKiem, lblTitle;
    private JLabel lblSLSP, lblSP, lblSLNV, lblNV, lblSLKH, lblKH, lblTDT, lblDT;
    private JDateChooser ngayTK;
    
    JScrollPane scrDTTheoNam,scrDTTheoNgay, scrDTNVTheoNgay, scrDTNVTheoThang, scrDTNVTheoNam, scrSPTK;
    JScrollPane scrDTKHTheoNgay, scrDTKHTheoThang, scrDTKHTheoNam, scrSPBCTheoNgay, scrSPBCTheoThang, scrSPBCTheoNam;
    
    private JTable tblDTQuy, tblDTTheoNam, tblDTTheoNgay, tblDTKHTheoNgay, tblDTKHTheoThang, tblDTKHTheoNam;
    private JTable tblDTNVTheoNgay, tblDTNVTheoThang, tblDTNVTheoNam, tblSPTK, tblSPBCTheoNgay, tblSPBCTheoThang, tblSPBCTheoNam;
    private DefaultTableModel modelDTQuy, modelDTTheoNam,  modelDTTheoNgay, modelDTKHTheoNgay, modelDTKHTheoThang, modelDTKHTheoNam;
    private DefaultTableModel modelDTNVTheoNgay, modelDTNVTheoThang, modelDTNVTheoNam, modelSPTK, modelSPBCTheoNgay, modelSPBCTheoThang, modelSPBCTheoNam;
    
    private JComboBox<String> cmbNam, cmbChonLoaiTK,cmbChonTGTK, cmbChonThang, cmbChonNam;
    
    private JTextField txtTimKiem;
    private final String placeholderTimKiem = "Nhập từ khóa mà bạn muốn tìm kiếm...";
    private Font font, fontTabbed, fontTitleTable;
    DecimalFormat dcf = new DecimalFormat("###,###");
    SimpleDateFormat spdf = new SimpleDateFormat("dd/MM/yyyy");
    
    ThongKeBUS thongKeBUS = new ThongKeBUS();
    HoaDonBUS hdBUS = new HoaDonBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();
    KhachHangBUS khBUS = new KhachHangBUS();
    GiamGiaBUS ggBUS = new GiamGiaBUS();
    
    public PnlQLThongKe() {
        addControls();
        addEventsThongKeTongQuat();
        addEventsThongKeChiTiet();
    }
    
    public void addControls() {
        setLayout(new BorderLayout());
        GridBagConstraints consTKTQ = new GridBagConstraints();
        GridBagConstraints consTKCT = new GridBagConstraints();
        font = new Font("Arial", Font.BOLD, 26);

//        Pnl TOP
        JPanel pnlTop = new JPanel();
        pnlTop.setOpaque(false);
        pnlTop.setPreferredSize(new Dimension(1000, 41));
        pnlTop.setLayout(null);
        pnlTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));
        
        fontTabbed = new Font("Arial", Font.BOLD, 14);
        
        lblTabbedThongKeTongQuat = new JLabel("Thống kê tổng quát");
        lblTabbedThongKeTongQuat.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedThongKeTongQuat.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedThongKeTongQuat.setIcon(tabbedSelected);
        lblTabbedThongKeTongQuat.setBounds(2, 2, 140, 37);
        lblTabbedThongKeTongQuat.setFont(fontTabbed);
        lblTabbedThongKeTongQuat.setForeground(Color.white);
        lblTabbedThongKeTongQuat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedThongKeChiTiet = new JLabel("Thống kê chi tiết");
        lblTabbedThongKeChiTiet.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedThongKeChiTiet.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedThongKeChiTiet.setIcon(tabbedDefault);
        lblTabbedThongKeChiTiet.setBounds(143, 2, 140, 37);
        lblTabbedThongKeChiTiet.setFont(fontTabbed);
        lblTabbedThongKeChiTiet.setForeground(Color.white);
        lblTabbedThongKeChiTiet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        pnlTop.add(lblTabbedThongKeTongQuat);
        pnlTop.add(lblTabbedThongKeChiTiet);
        
        //        Thêm pnlTop vào Frame
        this.add(pnlTop, BorderLayout.NORTH);
        
        DefaultTableCellRenderer centerRederer = new DefaultTableCellRenderer();
        centerRederer.setHorizontalAlignment(JLabel.CENTER);
        
        /*
        =========================================================================
                                    PANEL THỐNG KÊ TỔNG QUÁT
        =========================================================================
         */
        
        JPanel pnlThongKeTongQuat = new JPanel(new GridBagLayout());
        pnlThongKeTongQuat.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() - pnlTop.getHeight()));
        
        lblTitleTKTQ = new JLabel("THỐNG KÊ TỔNG QUÁT");
        lblTitleTKTQ.setFont(font);
        
        lblSLSP = new JLabel(thongKeBUS.getSoLuongSP()+"");
        lblSLNV = new JLabel(thongKeBUS.getSoLuongNhanVien()+"");
        lblSLKH = new JLabel(thongKeBUS.getSoLuongKhachHang()+"");
        
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        lblTDT = new JLabel(thongKeBUS.tinhDoanhThuTheoNam(year)+"");
        
        lblSP = new JLabel("Sản phẩm");
        lblKH = new JLabel("Khách hàng");
        lblNV = new JLabel("Nhân viên");
        lblDT = new JLabel("Tổng doanh thu");
        
        cmbNam = new JComboBox<>();
        cmbChonNam = new JComboBox<>();
        
        cmbChonLoaiTK = new JComboBox<>();
        cmbChonLoaiTK.addItem("Doanh thu");
        cmbChonLoaiTK.addItem("Doanh thu nhân viên");
        cmbChonLoaiTK.addItem("Doanh thu khách hàng");
        cmbChonLoaiTK.addItem("Hàng tồn");
        cmbChonLoaiTK.addItem("Sản phẩm bán chạy");

        cmbChonThang = new JComboBox<>();
        cmbChonThang.addItem("Tháng 1");
        cmbChonThang.addItem("Tháng 2");
        cmbChonThang.addItem("Tháng 3");
        cmbChonThang.addItem("Tháng 4");
        cmbChonThang.addItem("Tháng 5");
        cmbChonThang.addItem("Tháng 6");
        cmbChonThang.addItem("Tháng 7");
        cmbChonThang.addItem("Tháng 8");
        cmbChonThang.addItem("Tháng 9");
        cmbChonThang.addItem("Tháng 10");
        cmbChonThang.addItem("Tháng 11");
        cmbChonThang.addItem("Tháng 12");
        
        // Panel Thống kê sản phẩm
        JPanel pnlSanPham = new JPanel(new GridBagLayout());
        pnlSanPham.setSize(new Dimension(396, 200));
        pnlSanPham.setBorder(BorderFactory.createLineBorder(Color.green));
        GridBagConstraints consSP = new GridBagConstraints();
        consSP.gridx = 0;
        consSP.gridy = 0;
        consSP.insets = new Insets(5, 5, 5, 5);
        consSP.anchor = GridBagConstraints.CENTER;
        pnlSanPham.add(lblSLSP, consSP);
        
        consSP.gridx = 0;
        consSP.gridy = 1;
        pnlSanPham.add(lblSP, consSP);
        
        // Panel Thống kê nhân viên
        JPanel pnlNhanVien = new JPanel(new GridBagLayout());
        pnlNhanVien.setSize(new Dimension(396, 200));
        pnlNhanVien.setBorder(BorderFactory.createLineBorder(Color.red));
        GridBagConstraints consNV = new GridBagConstraints();
        consNV.gridx = 0;
        consNV.gridy = 0;
        consNV.insets = new Insets(5, 5, 5, 5);
        consNV.anchor = GridBagConstraints.CENTER;
        pnlNhanVien.add(lblSLNV, consNV);
        
        consNV.gridx = 0;
        consNV.gridy = 1;
        pnlNhanVien.add(lblNV, consNV);
        
        // Panel Thống kê khách hàng
        JPanel pnlKhacHang = new JPanel(new GridBagLayout());
        pnlKhacHang.setSize(new Dimension(396, 200));
        pnlKhacHang.setBorder(BorderFactory.createLineBorder(Color.pink));
        GridBagConstraints consKH = new GridBagConstraints();
        consKH.gridx = 0;
        consKH.gridy = 0;
        consKH.insets = new Insets(5, 5, 5, 5);
        consKH.anchor = GridBagConstraints.CENTER;
        pnlKhacHang.add(lblSLKH, consKH);
        
        consKH.gridx = 0;
        consKH.gridy = 1;
        pnlKhacHang.add(lblKH, consKH);
        
        // Panel Thống kê tổng doanh thu
        JPanel pnlTongDoanhThu = new JPanel(new GridBagLayout());
        pnlTongDoanhThu.setSize(new Dimension(396, 200));
        pnlTongDoanhThu.setBorder(BorderFactory.createLineBorder(Color.yellow));
        GridBagConstraints consDT = new GridBagConstraints();
        consDT.gridx = 0;
        consDT.gridy = 0;
        consDT.insets = new Insets(5, 5, 5, 5);
        consDT.anchor = GridBagConstraints.CENTER;
        pnlTongDoanhThu.add(lblTDT, consDT);
        
        consDT.gridx = 0;
        consDT.gridy = 1;
        pnlTongDoanhThu.add(lblDT, consDT);
        
        
//        Thêm pnlTitleTKTQ vào PanelThongKeTongQuat
        consTKTQ.gridx = 0;
        consTKTQ.gridy = 0;
        consTKTQ.anchor = GridBagConstraints.CENTER;
        consTKTQ.insets = new Insets(20, 20, 20, 20);
        consTKTQ.fill = GridBagConstraints.BOTH;
        pnlThongKeTongQuat.add(lblTitleTKTQ, consTKTQ);
        
        JPanel pnlHTTKTQ = new JPanel(new GridBagLayout());
        GridBagConstraints consHTTKTQ = new GridBagConstraints();
//        Them pnlSp vào pnlHTTKTQ
        consHTTKTQ.gridx = 0;
        consHTTKTQ.gridy = 0;
        consHTTKTQ.insets = new Insets(20, 100, 20, 100);
        consHTTKTQ.gridwidth = 1;
        consHTTKTQ.fill = GridBagConstraints.NONE;
        pnlHTTKTQ.add(pnlSanPham, consHTTKTQ);
        
//        Them pnlKH vào pnlHTTKTQ
        consHTTKTQ.gridx = 1;
        consHTTKTQ.gridy = 0;
        consHTTKTQ.gridwidth = 1;
        pnlHTTKTQ.add(pnlKhacHang, consHTTKTQ);
        
        
//        Them pnlNV vào pnlHTTKTQ
        consHTTKTQ.gridx = 0;
        consHTTKTQ.gridy = 1;
        consHTTKTQ.gridwidth = 1;
        pnlHTTKTQ.add(pnlNhanVien, consHTTKTQ);
        
        
//        Them pnlTDT vào pnlHTTKTQ
        consHTTKTQ.gridx = 1;
        consHTTKTQ.gridy = 1;
        consHTTKTQ.gridwidth = 1;
        pnlHTTKTQ.add(pnlTongDoanhThu, consHTTKTQ);
        
//        Them pnlHTTKTQ vào pnlThongKeTongQuat
        consTKTQ.gridx = 0;
        consTKTQ.gridy = 2;
        consTKTQ.gridwidth = 2;
        consTKTQ.anchor = GridBagConstraints.CENTER;
        consTKTQ.fill = GridBagConstraints.NONE;
        pnlThongKeTongQuat.add(pnlHTTKTQ, consTKTQ);
        
//        Them cmbNam vào pnlThongKeTongQuat
        consTKTQ.gridx = 0;
        consTKTQ.gridy = 3;
        consTKTQ.gridwidth = 2;
        consTKTQ.fill = GridBagConstraints.NONE;
        consTKTQ.anchor = GridBagConstraints.CENTER;
        pnlThongKeTongQuat.add(cmbNam, consTKTQ);

//      Table Doanh thu tổng quát theo năm        
        JPanel pnlTable = new JPanel(new BorderLayout());
        
        modelDTQuy = new DefaultTableModel();
        
        modelDTQuy.addColumn("Quý");
        modelDTQuy.addColumn("Quý 1");
        modelDTQuy.addColumn("Quý 2");
        modelDTQuy.addColumn("Quý 3");
        modelDTQuy.addColumn("Quý 4");
        modelDTQuy.addColumn("Tổng doanh thu");
        
        Vector vec = new Vector();
        vec.add("Doanh thu");
        vec.add(thongKeBUS.tinhDoanhThuQuy(year).getDTQuy1());
        vec.add(thongKeBUS.tinhDoanhThuQuy(year).getDTQuy2());
        vec.add(thongKeBUS.tinhDoanhThuQuy(year).getDTQuy3());
        vec.add(thongKeBUS.tinhDoanhThuQuy(year).getDTQuy4());
        vec.add(thongKeBUS.tinhDoanhThuTheoNam(year));

        modelDTQuy.addRow(vec);
        
        tblDTQuy = new JTable(modelDTQuy);
        
        tblDTQuy.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblDTQuy.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblDTQuy.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblDTQuy.getColumnModel().getColumn(3).setCellRenderer(centerRederer);
        tblDTQuy.getColumnModel().getColumn(4).setCellRenderer(centerRederer);
        tblDTQuy.getColumnModel().getColumn(5).setCellRenderer(centerRederer);
        
//        Thêm pnlTable vào pnlThongKeTongQuat
        JScrollPane scrQuy = new JScrollPane(tblDTQuy);  
        pnlTable.add(scrQuy, BorderLayout.CENTER);
        consTKTQ.gridx = 0;
        consTKTQ.gridy = 4;
        consTKTQ.gridwidth = 1;
        consTKTQ.gridheight = 1;
        consTKTQ.weightx = 1.0;
        consTKTQ.weighty = 1.0;
        consTKTQ.fill = GridBagConstraints.BOTH;
        consTKTQ.anchor = GridBagConstraints.CENTER;
        pnlThongKeTongQuat.add(pnlTable, consTKTQ);
        
        
                /*
        =========================================================================
                                    PANEL THỐNG KÊ CHI TIẾT
        =========================================================================
         */
        JPanel pnlThongKeChiTiet = new JPanel(new GridBagLayout());
        
        lblTitleTKCT = new JLabel("THỐNG KÊ CHI  TIẾT");
        font = new Font("Arial", Font.BOLD, 26);
        lblTitleTKCT.setFont(font);
        
        cmbChonLoaiTK = new JComboBox<>();
        cmbChonLoaiTK.addItem("Doanh thu");
        cmbChonLoaiTK.addItem("Doanh thu nhân viên");
        cmbChonLoaiTK.addItem("Doanh thu khách hàng");
        cmbChonLoaiTK.addItem("Hàng tồn");
        cmbChonLoaiTK.addItem("Sản phẩm bán chạy");
        
        cmbChonTGTK = new JComboBox<>();
        cmbChonTGTK.addItem("Năm");
        cmbChonTGTK.addItem("Tháng");
        cmbChonTGTK.addItem("Ngày");
        
        lblTimKiem = new JLabel("Từ khóa tìm kiếm");
        txtTimKiem = new JTextField(20);
        
        txtTimKiem.setForeground(Color.GRAY);
        txtTimKiem.setText(placeholderTimKiem);
        
        ngayTK = new JDateChooser();
        ngayTK.setDateFormatString("dd/MM/yyyy");
        ngayTK.setDate(new Date());

        

//        Thêm lblTitleTKCT vào pnlThongKeChiTiet
        consTKCT.gridx = 0;
        consTKCT.gridy = 0;
        consTKCT.anchor = GridBagConstraints.CENTER;
        consTKCT.fill = GridBagConstraints.BOTH;
        consTKCT.insets = new Insets(20, 20, 20, 20);
        consTKCT.gridwidth = 1;
        pnlThongKeChiTiet.add(lblTitleTKCT, consTKCT);
        
//        Panel lua chon loan thong ke
        JPanel pnlLoaiTK = new JPanel(new GridBagLayout());
        GridBagConstraints consLoaiTK = new GridBagConstraints();
        
//        Them cmbChonLoaiTK
        consLoaiTK.gridx = 0;
        consLoaiTK.gridy = 0;
        consLoaiTK.gridwidth = 1;
        consLoaiTK.insets = new Insets(10, 10, 10, 10);
        pnlLoaiTK.add(cmbChonLoaiTK, consLoaiTK);
        
//        Them cmbChonTGTK
        consLoaiTK.gridx = 1;
        consLoaiTK.gridy = 0;
        consLoaiTK.gridwidth = 1;
        pnlLoaiTK.add(cmbChonTGTK, consLoaiTK);
        
//        Them cmbChonThang
        consLoaiTK.gridx = 2;
        consLoaiTK.gridy = 0;
        consLoaiTK.gridwidth = 1;
        pnlLoaiTK.add(cmbChonThang, consLoaiTK);
        cmbChonThang.setVisible(false);
        
//        Them cmbChonNam
        consLoaiTK.gridx = 3;
        consLoaiTK.gridy = 0;
        consLoaiTK.gridwidth = 1;
        pnlLoaiTK.add(cmbChonNam, consLoaiTK);
        
//        Them ngayTK
        consLoaiTK.gridx = 4;
        consLoaiTK.gridy = 0;
        consLoaiTK.gridwidth = 1;
        pnlLoaiTK.add(ngayTK, consLoaiTK);
        ngayTK.setVisible(false);
        
//      Thêm pnlLoaiTK vao pnlThongKeChiTiet        
        consTKCT.gridx = 0;
        consTKCT.gridy = 1;
        consTKCT.anchor = GridBagConstraints.CENTER;
        consTKCT.fill = GridBagConstraints.BOTH;
        consTKCT.insets = new Insets(20, 0, 20, 0);
        consTKCT.gridwidth = 1;
        pnlThongKeChiTiet.add(pnlLoaiTK, consTKCT);
        
//        Panel Hiển thị 
        pnlHT = new JPanel(new GridBagLayout());
        GridBagConstraints consHT= new GridBagConstraints();
        
        fontTitleTable = new Font("Arial", Font.BOLD, 20);
        
        lblTitle = new JLabel("BẢNG DOANH THU THEO NĂM");
        lblTitle.setFont(fontTitleTable);
        
        pnlTK = new JPanel(new GridBagLayout());
        GridBagConstraints consTK = new GridBagConstraints();
        
        consTK.gridx = 0;
        consTK.gridy = 0;
        consTK.gridwidth = 1;
        pnlTK.add(lblTimKiem, consTK);
        
        consTK.gridx = 1;
        consTK.gridy = 0;
        consTK.gridwidth = 1;
        consTK.fill = GridBagConstraints.BOTH;
        pnlTK.add(txtTimKiem, consTK);
        
//        Table DTTheoNam
        modelDTTheoNam = new DefaultTableModel();
        
        modelDTTheoNam.addColumn("Tháng 1");
        modelDTTheoNam.addColumn("Tháng 2");
        modelDTTheoNam.addColumn("Tháng 3");
        modelDTTheoNam.addColumn("Tháng 4");
        modelDTTheoNam.addColumn("Tháng 5");
        modelDTTheoNam.addColumn("Tháng 6");
        modelDTTheoNam.addColumn("Tháng 7");
        modelDTTheoNam.addColumn("Tháng 8");
        modelDTTheoNam.addColumn("Tháng 9");
        modelDTTheoNam.addColumn("Tháng 10");
        modelDTTheoNam.addColumn("Tháng 11");
        modelDTTheoNam.addColumn("Tháng 12");
        modelDTTheoNam.addColumn("Tổng doanh thu");
        
        
        tblDTTheoNam = new JTable(modelDTTheoNam);
        
        tblDTTheoNam.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblDTTheoNam.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblDTTheoNam.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblDTTheoNam.getColumnModel().getColumn(3).setCellRenderer(centerRederer);
        tblDTTheoNam.getColumnModel().getColumn(4).setCellRenderer(centerRederer);
        tblDTTheoNam.getColumnModel().getColumn(5).setCellRenderer(centerRederer);
        tblDTTheoNam.getColumnModel().getColumn(6).setCellRenderer(centerRederer);
        tblDTTheoNam.getColumnModel().getColumn(7).setCellRenderer(centerRederer);
        tblDTTheoNam.getColumnModel().getColumn(8).setCellRenderer(centerRederer);
        tblDTTheoNam.getColumnModel().getColumn(9).setCellRenderer(centerRederer);
        tblDTTheoNam.getColumnModel().getColumn(10).setCellRenderer(centerRederer);
        tblDTTheoNam.getColumnModel().getColumn(11).setCellRenderer(centerRederer);
        tblDTTheoNam.getColumnModel().getColumn(12).setCellRenderer(centerRederer);

        scrDTTheoNam = new JScrollPane(tblDTTheoNam);  
             
//        Table DTTheoNgay       
        modelDTTheoNgay = new DefaultTableModel();
        
        modelDTTheoNgay.addColumn("Mã hóa đơn");
        modelDTTheoNgay.addColumn("Nhân viên");
        modelDTTheoNgay.addColumn("Khách hàng");
        modelDTTheoNgay.addColumn("Ngày lập");
        modelDTTheoNgay.addColumn("Tổng tiền");
        modelDTTheoNgay.addColumn("Mã giảm giá");

        tblDTTheoNgay = new JTable(modelDTTheoNgay);
        
        tblDTTheoNgay.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblDTTheoNgay.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblDTTheoNgay.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblDTTheoNgay.getColumnModel().getColumn(3).setCellRenderer(centerRederer);
        tblDTTheoNgay.getColumnModel().getColumn(4).setCellRenderer(centerRederer);
        tblDTTheoNgay.getColumnModel().getColumn(5).setCellRenderer(centerRederer);

        scrDTTheoNgay = new JScrollPane(tblDTTheoNgay); 
        
             
//        Table DTNVTheoNgay       
        modelDTNVTheoNgay = new DefaultTableModel();
        
        modelDTNVTheoNgay.addColumn("Mã nhân viên");
        modelDTNVTheoNgay.addColumn("Họ");
        modelDTNVTheoNgay.addColumn("Tên");
        modelDTNVTheoNgay.addColumn("Doanh thu");

        tblDTNVTheoNgay = new JTable(modelDTNVTheoNgay);
        
        tblDTNVTheoNgay.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblDTNVTheoNgay.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblDTNVTheoNgay.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblDTNVTheoNgay.getColumnModel().getColumn(3).setCellRenderer(centerRederer);
        
        scrDTNVTheoNgay = new JScrollPane(tblDTNVTheoNgay); 
        
             
//        Table DTNVTheoThang       
        modelDTNVTheoThang = new DefaultTableModel();
        
        modelDTNVTheoThang.addColumn("Mã nhân viên");
        modelDTNVTheoThang.addColumn("Họ");
        modelDTNVTheoThang.addColumn("Tên");
        modelDTNVTheoThang.addColumn("Doanh thu");

        tblDTNVTheoThang = new JTable(modelDTNVTheoThang);
        
        tblDTNVTheoThang.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblDTNVTheoThang.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblDTNVTheoThang.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblDTNVTheoThang.getColumnModel().getColumn(3).setCellRenderer(centerRederer);

        scrDTNVTheoThang = new JScrollPane(tblDTNVTheoThang); 
        
             
//        Table DTNVTheoNam       
        modelDTNVTheoNam = new DefaultTableModel();
        
        modelDTNVTheoNam.addColumn("Mã nhân viên");
        modelDTNVTheoNam.addColumn("Họ");
        modelDTNVTheoNam.addColumn("Tên");
        modelDTNVTheoNam.addColumn("Doanh thu");

        tblDTNVTheoNam = new JTable(modelDTNVTheoNam);
        
        tblDTNVTheoNam.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblDTNVTheoNam.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblDTNVTheoNam.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblDTNVTheoNam.getColumnModel().getColumn(3).setCellRenderer(centerRederer);

        scrDTNVTheoNam = new JScrollPane(tblDTNVTheoNam); 
        
             
//        Table DTKHTheoNgay       
        modelDTKHTheoNgay = new DefaultTableModel();
        
        modelDTKHTheoNgay.addColumn("Mã khách hàng");
        modelDTKHTheoNgay.addColumn("Họ");
        modelDTKHTheoNgay.addColumn("Tên");
        modelDTKHTheoNgay.addColumn("Doanh thu");

        tblDTKHTheoNgay = new JTable(modelDTKHTheoNgay);
        
        tblDTKHTheoNgay.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblDTKHTheoNgay.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblDTKHTheoNgay.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblDTKHTheoNgay.getColumnModel().getColumn(3).setCellRenderer(centerRederer);

        scrDTKHTheoNgay = new JScrollPane(tblDTKHTheoNgay); 
        
             
//        Table DTKHTheoThang       
        modelDTKHTheoThang = new DefaultTableModel();
        
        modelDTKHTheoThang.addColumn("Mã khách hàng");
        modelDTKHTheoThang.addColumn("Họ");
        modelDTKHTheoThang.addColumn("Tên");
        modelDTKHTheoThang.addColumn("Doanh thu");

        tblDTKHTheoThang = new JTable(modelDTKHTheoThang);
        
        tblDTKHTheoThang.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblDTKHTheoThang.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblDTKHTheoThang.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblDTKHTheoThang.getColumnModel().getColumn(3).setCellRenderer(centerRederer);

        scrDTKHTheoThang = new JScrollPane(tblDTKHTheoThang); 
        
             
//        Table DTTheoNgay       
        modelDTKHTheoNgay = new DefaultTableModel();
        
        modelDTKHTheoNgay.addColumn("Mã khách hàng");
        modelDTKHTheoNgay.addColumn("Họ");
        modelDTKHTheoNgay.addColumn("Tên");
        modelDTKHTheoNgay.addColumn("Doanh thu");

        tblDTKHTheoNgay = new JTable(modelDTKHTheoNgay);
        
        tblDTKHTheoNgay.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblDTKHTheoNgay.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblDTKHTheoNgay.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblDTKHTheoNgay.getColumnModel().getColumn(3).setCellRenderer(centerRederer);

        scrDTKHTheoNgay = new JScrollPane(tblDTKHTheoNgay); 
        
             
//        Table DTKHTheoNam       
        modelDTKHTheoNam = new DefaultTableModel();
        
        modelDTKHTheoNam.addColumn("Mã khách hàng");
        modelDTKHTheoNam.addColumn("Họ");
        modelDTKHTheoNam.addColumn("Tên");
        modelDTKHTheoNam.addColumn("Doanh thu");

        tblDTKHTheoNam = new JTable(modelDTKHTheoNam);
        
        tblDTKHTheoNam.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblDTKHTheoNam.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblDTKHTheoNam.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblDTKHTheoNam.getColumnModel().getColumn(3).setCellRenderer(centerRederer);

        scrDTKHTheoNam = new JScrollPane(tblDTKHTheoNam); 
        
             
//        Table SPBCTheoNgay       
        modelSPBCTheoNgay = new DefaultTableModel();
        
        modelSPBCTheoNgay.addColumn("Mã sản phẩm");
        modelSPBCTheoNgay.addColumn("Tên sản phẩm");
        modelSPBCTheoNgay.addColumn("Số lượng");
        
        tblSPBCTheoNgay = new JTable(modelSPBCTheoNgay);
        
        tblSPBCTheoNgay.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblSPBCTheoNgay.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblSPBCTheoNgay.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        
        scrSPBCTheoNgay = new JScrollPane(tblSPBCTheoNgay); 
        
             
//        Table SPBCTheoThang       
        modelSPBCTheoThang = new DefaultTableModel();
        
        modelSPBCTheoThang.addColumn("Mã sản phẩm");
        modelSPBCTheoThang.addColumn("Tên sản phẩm");
        modelSPBCTheoThang.addColumn("Số lượng");
        
        tblSPBCTheoThang = new JTable(modelSPBCTheoThang);
        
        tblSPBCTheoThang.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblSPBCTheoThang.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblSPBCTheoThang.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        
        scrSPBCTheoThang = new JScrollPane(tblSPBCTheoThang); 
        
             
//        Table SPBCTheoNam       
        modelSPBCTheoNam = new DefaultTableModel();
        
        modelSPBCTheoNam.addColumn("Mã sản phẩm");
        modelSPBCTheoNam.addColumn("Tên sản phẩm");
        modelSPBCTheoNam.addColumn("Số lượng");
        
        tblSPBCTheoNam = new JTable(modelSPBCTheoNam);
        
        tblSPBCTheoNam.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblSPBCTheoNam.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblSPBCTheoNam.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        
        scrSPBCTheoNam = new JScrollPane(tblSPBCTheoNam); 
        
        
//        Table SPTK       
        modelSPTK = new DefaultTableModel();
        
        modelSPTK.addColumn("Mã sản phẩm");
        modelSPTK.addColumn("Tên sản phẩm");
        modelSPTK.addColumn("Số lượng");
        
        tblSPTK = new JTable(modelSPTK);
        
        tblSPTK.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblSPTK.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblSPTK.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        
        scrSPTK = new JScrollPane(tblSPTK); 
        
//        Thêm các phần tử vào pnlHT
        consHT.gridx = 0;
        consHT.gridy = 0;
        consHT.gridwidth = 1;
        consHT.insets = new Insets(20, 20, 20, 20);
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(lblTitle, consHT);
        
        consHT.gridx = 0;
        consHT.gridy = 1;
        consHT.gridwidth = 1;
        consHT.insets = new Insets(20, 20, 20, 20);
        consHT.anchor = GridBagConstraints.CENTER;
        consHT.fill = GridBagConstraints.BOTH;
        pnlHT.add(pnlTK, consHT);
        pnlTK.setVisible(false);
        
        consHT.gridx = 0;
        consHT.gridy = 2;
        consHT.gridwidth = 1;
        consHT.gridheight = 1;
        consHT.weightx = 1.0;
        consHT.weighty = 1.0;
        consHT.fill = GridBagConstraints.BOTH;
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(scrDTTheoNam, consHT);
        
        consHT.gridx = 0;
        consHT.gridy = 3;
        consHT.gridwidth = 1;
        consHT.gridheight = 1;
        consHT.weightx = 1.0;
        consHT.weighty = 1.0;
        consHT.fill = GridBagConstraints.BOTH;
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(scrDTTheoNgay, consHT);
        scrDTTheoNgay.setVisible(false);
        
        consHT.gridx = 0;
        consHT.gridy = 4;
        consHT.gridwidth = 1;
        consHT.gridheight = 1;
        consHT.weightx = 1.0;
        consHT.weighty = 1.0;
        consHT.fill = GridBagConstraints.BOTH;
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(scrDTNVTheoNgay, consHT);
        scrDTNVTheoNgay.setVisible(false);
        
        consHT.gridx = 0;
        consHT.gridy = 5;
        consHT.gridwidth = 1;
        consHT.gridheight = 1;
        consHT.weightx = 1.0;
        consHT.weighty = 1.0;
        consHT.fill = GridBagConstraints.BOTH;
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(scrDTNVTheoThang, consHT);
        scrDTNVTheoThang.setVisible(false);
        
        consHT.gridx = 0;
        consHT.gridy = 6;
        consHT.gridwidth = 1;
        consHT.gridheight = 1;
        consHT.weightx = 1.0;
        consHT.weighty = 1.0;
        consHT.fill = GridBagConstraints.BOTH;
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(scrDTNVTheoNam, consHT);
        scrDTNVTheoNam.setVisible(false);
        
        consHT.gridx = 0;
        consHT.gridy = 7;
        consHT.gridwidth = 1;
        consHT.gridheight = 1;
        consHT.weightx = 1.0;
        consHT.weighty = 1.0;
        consHT.fill = GridBagConstraints.BOTH;
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(scrDTKHTheoNgay, consHT);
        scrDTKHTheoNgay.setVisible(false);
        
        consHT.gridx = 0;
        consHT.gridy = 8;
        consHT.gridwidth = 1;
        consHT.gridheight = 1;
        consHT.weightx = 1.0;
        consHT.weighty = 1.0;
        consHT.fill = GridBagConstraints.BOTH;
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(scrDTKHTheoThang, consHT);
        scrDTKHTheoThang.setVisible(false);
        
        consHT.gridx = 0;
        consHT.gridy = 9;
        consHT.gridwidth = 1;
        consHT.gridheight = 1;
        consHT.weightx = 1.0;
        consHT.weighty = 1.0;
        consHT.fill = GridBagConstraints.BOTH;
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(scrDTKHTheoNam, consHT);
        scrDTKHTheoNam.setVisible(false);
        
        consHT.gridx = 0;
        consHT.gridy = 10;
        consHT.gridwidth = 1;
        consHT.gridheight = 1;
        consHT.weightx = 1.0;
        consHT.weighty = 1.0;
        consHT.fill = GridBagConstraints.BOTH;
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(scrSPTK, consHT);
        scrSPTK.setVisible(false);
        
        consHT.gridx = 0;
        consHT.gridy = 11;
        consHT.gridwidth = 1;
        consHT.gridheight = 1;
        consHT.weightx = 1.0;
        consHT.weighty = 1.0;
        consHT.fill = GridBagConstraints.BOTH;
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(scrSPBCTheoNgay, consHT);
        scrSPBCTheoNgay.setVisible(false);
        
        consHT.gridx = 0;
        consHT.gridy = 12;
        consHT.gridwidth = 1;
        consHT.gridheight = 1;
        consHT.weightx = 1.0;
        consHT.weighty = 1.0;
        consHT.fill = GridBagConstraints.BOTH;
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(scrSPBCTheoThang, consHT);
        scrSPBCTheoThang.setVisible(false);
        
        consHT.gridx = 0;
        consHT.gridy = 13;
        consHT.gridwidth = 1;
        consHT.gridheight = 1;
        consHT.weightx = 1.0;
        consHT.weighty = 1.0;
        consHT.fill = GridBagConstraints.BOTH;
        consHT.anchor = GridBagConstraints.CENTER;
        pnlHT.add(scrSPBCTheoNam, consHT);
        scrSPBCTheoNam.setVisible(false);
        
//        Thêm pnlHTDTTheoNam vào pnlThongKeChiTiet
        consTKCT.gridx = 0;
        consTKCT.gridy = 2;
        consTKCT.gridwidth = 1;
        consTKCT.gridheight = 1;
        consTKCT.weightx = 1.0;
        consTKCT.weighty = 1.0;
        consTKCT.insets = new Insets(50, 0, 50, 0);
        consTKCT.anchor = GridBagConstraints.CENTER;
        consTKCT.fill = GridBagConstraints.BOTH;
        consTKCT.gridwidth = 1;
        pnlThongKeChiTiet.add(pnlHT, consTKCT);

        pnlCardTabThongKe = new JPanel(cardThongKeGroup);
        pnlCardTabThongKe.add(pnlThongKeTongQuat, "1");
        pnlCardTabThongKe.add(pnlThongKeChiTiet, "2");
   
        this.add(pnlCardTabThongKe);
        loadDataLenBangDTTheoNam(year);
        loadDataLenBangDSHDTheoNgay(new Date());
        loadDanhSachNam();
        
        setSize(1000, 700);
        setVisible(true);
    }
    
    public void addEventsThongKeTongQuat() {
        lblTabbedThongKeTongQuat.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedThongKeTongQuat.setIcon(tabbedSelected);
                lblTabbedThongKeChiTiet.setIcon(tabbedDefault);
                cardThongKeGroup.show(pnlCardTabThongKe, "1");
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

        lblTabbedThongKeChiTiet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedThongKeChiTiet.setIcon(tabbedSelected);
                lblTabbedThongKeTongQuat.setIcon(tabbedDefault);
                cardThongKeGroup.show(pnlCardTabThongKe, "2");
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
        
        cmbNam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonNam();
            }
        });
    }
    
    public void addEventsThongKeChiTiet() {
        cmbChonTGTK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               xuLyChonTGTK();
            }
        });
        
        cmbChonLoaiTK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               xuLyChonLoaiTK();
            }
        });
        
        cmbChonNam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               xuLyChonNamTK();
            }
        });
        
        cmbChonThang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               xuLyChonThangTK();
            }
        });
        
        ngayTK.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    Date selectedDate = (Date) evt.getNewValue();
                    xuLyChonNgayTK();
                }
            }
        });
        
                
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                String selectedLoaiTK = cmbChonLoaiTK.getSelectedItem() + "";
                switch(selectedLoaiTK) {
                    case "Doanh thu": 
                        xuLyLiveSearchDT();
                        break;
                    case "Doanh thu nhân viên":
                        xuLyLiveSearchDTNV();
                        break;
                    case "Doanh thu khách hàng":
                        xuLyLiveSearchDTKH();
                        break;
                    case "Hàng tồn":
                        xuLyLiveSearchHT();
                        break;
                    case "Sản phẩm bán chạy":
                        xuLyLiveSearchSPBC();
                        break;
                }
               
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String selectedLoaiTK = cmbChonLoaiTK.getSelectedItem() + "";
                switch(selectedLoaiTK) {
                    case "Doanh thu": 
                        xuLyLiveSearchDT();
                        break;
                    case "Doanh thu nhân viên":
                        xuLyLiveSearchDTNV();
                        break;
                    case "Doanh thu khách hàng":
                        xuLyLiveSearchDTKH();
                        break;
                    case "Hàng tồn":
                        xuLyLiveSearchHT();
                        break;
                    case "Sản phẩm bán chạy":
                        xuLyLiveSearchSPBC();
                        break;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String selectedLoaiTK = cmbChonLoaiTK.getSelectedItem() + "";
                switch(selectedLoaiTK) {
                    case "Doanh thu": 
                        xuLyLiveSearchDT();
                        break;
                    case "Doanh thu nhân viên":
                        xuLyLiveSearchDTNV();
                        break;
                    case "Doanh thu khách hàng":
                        xuLyLiveSearchDTKH();
                        break;
                    case "Hàng tồn":
                        xuLyLiveSearchHT();
                        break;
                    case "Sản phẩm bán chạy":
                        xuLyLiveSearchSPBC();
                        break;
                }
            }
        });
        
           
        txtTimKiem.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(txtTimKiem.getText().equals(placeholderTimKiem)) {
                    txtTimKiem.setText("");
                    txtTimKiem.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                    if(txtTimKiem.getText().trim().equals("")) {
                        txtTimKiem.setText(placeholderTimKiem);
                        txtTimKiem.setForeground(Color.GRAY);
                    }
            }
        });
}
    
//    Thống kê tổng quát
    
    public void loadDanhSachNam() {
        ArrayList<Integer> dsNam = thongKeBUS.getDSNamHD();
        cmbNam.removeAllItems();
        cmbChonNam.removeAllItems();
        
        for(int nam : dsNam) {
            cmbNam.addItem(nam+"");
            cmbChonNam.addItem(nam+"");
        }
    }
    
    public void xuLyChonNam() {
        String selected = cmbNam.getSelectedItem()+"";
        try {
            int nam = Integer.parseInt(selected);
            lblSLSP.setText(thongKeBUS.getSoLuongSP()+"");
            lblSLNV.setText(thongKeBUS.getSoLuongNhanVien()+"");
            lblSLKH.setText(thongKeBUS.getSoLuongKhachHang()+"");
            lblTDT.setText(thongKeBUS.tinhDoanhThuTheoNam(nam)+"");
            
            modelDTQuy.setRowCount(0);
            Vector vec = new Vector();
            vec.add("Doanh thu");
            vec.add(dcf.format(thongKeBUS.tinhDoanhThuQuy(nam).getDTQuy1()));
            vec.add(dcf.format(thongKeBUS.tinhDoanhThuQuy(nam).getDTQuy2()));
            vec.add(dcf.format(thongKeBUS.tinhDoanhThuQuy(nam).getDTQuy3()));
            vec.add(dcf.format(thongKeBUS.tinhDoanhThuQuy(nam).getDTQuy4()));
            vec.add(dcf.format(thongKeBUS.tinhDoanhThuTheoNam(nam)));

        modelDTQuy.addRow(vec);
            
        } catch (Exception e) {
        }
    }
    
//    Thống kê chi tiết
    public void xuLyChonTGTK() {
        String selectedNam = cmbChonNam.getSelectedItem()+"";
        String selectedThang = cmbChonThang.getSelectedItem()+"";
        String[] thang = selectedThang.split(" ");
        int year = Integer.parseInt(selectedNam);
        int month = Integer.parseInt(thang[1]);
        
        String selected = cmbChonTGTK.getSelectedItem()+"";
        String selectedLoaiTK = cmbChonLoaiTK.getSelectedItem() + "";
        switch(selected) {
            case "Năm": 
                switch(selectedLoaiTK) {
                    case "Doanh thu":
                        loadDataLenBangDTTheoNam(year);
                        
                        pnlTK.setVisible(false);
                        lblTitle.setText("BẢNG DOANH THU THEO NĂM");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(true);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(false);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                    case "Doanh thu nhân viên":
                        loadDataLenBangDTNVTheoNam(year);
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("BẢNG DOANH THU THEO NĂM");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(true);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(false);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                    case "Doanh thu khách hàng":
                        loadDataLenBangDTKHTheoNam(year);
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("BẢNG DOANH THU THEO NĂM");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(true);
                        scrSPTK.setVisible(false);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                    case "Hàng tồn":
                        loadDataLenBangDSHangTon();
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("DANH SÁCH HÀNG TỒN");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(true);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                    case "Sản phẩm bán chạy":
                        loadDataLenBangDSBCTheoNam(year);
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("DANH SÁCH SẢN PHẨM BÁN CHẠY THEO NĂM");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(false);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(true);
                        break;
                }
                cmbChonLoaiTK.setVisible(true);
                cmbChonNam.setVisible(true);
                cmbChonThang.setVisible(false);
                ngayTK.setVisible(false);
                break;
            case "Tháng": 
                switch(selectedLoaiTK) {
                    case "Doanh thu":
                        pnlTK.setVisible(false);
                        lblTitle.setText("KHÔNG CÓ THỐNG KÊ DOANH THU THEO THÁNG");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(false);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                    case "Doanh thu nhân viên":
                        loadDataLenBangDTNVTheoThang(month, year);
                        cmbChonThang.setSelectedIndex(month - 1);
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("BẢNG DOANH THU THEO THÁNG");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(true);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(false);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                    case "Doanh thu khách hàng":
                        loadDataLenBangDTKHTheoThang(month, year);
                        cmbChonThang.setSelectedIndex(month - 1);
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("BẢNG DOANH THU THEO THÁNG");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(true);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(false);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                    case "Hàng tồn":
                        loadDataLenBangDSHangTon();
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("DANH SÁCH HÀNG TỒN");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(true);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                    case "Sản phẩm bán chạy":
                        loadDataLenBangDSBCTheoThang(month, year);
                        cmbChonThang.setSelectedIndex(month - 1);
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("DANH SÁCH SẢN PHẨM BÁN CHẠY THEO THÁNG");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(false);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(true);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                }
                cmbChonLoaiTK.setVisible(true);
                cmbChonNam.setVisible(true);
                cmbChonThang.setVisible(true);
                ngayTK.setVisible(false);
                break;
            case "Ngày":
                Date date = null;
                if(ngayTK.getDate() == null) {
                     date = new Date();
                } else {
                    date = ngayTK.getDate();
                }
                switch(selectedLoaiTK) {
                    case "Doanh thu":
                        loadDataLenBangDSHDTheoNgay(date);
                        ngayTK.setDate(date);
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("BẢNG DANH SÁCH HÓA ĐƠN THEO NGÀY");
                        scrDTTheoNgay.setVisible(true);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(false);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                    case "Doanh thu nhân viên":
                        loadDataLenBangDTNVTheoNgay(date);
                        ngayTK.setDate(date);
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("BẢNG DOANH THU THEO NGÀY");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(true);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(false);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                    case "Doanh thu khách hàng":
                        loadDataLenBangDTKHTheoNgay(date);
                        ngayTK.setDate(date);
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("BẢNG DOANH THU THEO NGÀY");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(true);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(false);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                    case "Hàng tồn":
                        loadDataLenBangDSHangTon();
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("DANH SÁCH HÀNG TỒN");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(true);
                        scrSPBCTheoNgay.setVisible(false);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                    case "Sản phẩm bán chạy":
                        loadDataLenBangDSBCTheoNgay(date);
                        ngayTK.setDate(date);
                        pnlTK.setVisible(true);
                        
                        lblTitle.setText("DANH SÁCH SẢN PHẨM BÁN CHẠY THEO NGÀY");
                        scrDTTheoNgay.setVisible(false);
                        scrDTTheoNam.setVisible(false);
                        scrDTNVTheoNgay.setVisible(false);
                        scrDTNVTheoThang.setVisible(false);
                        scrDTNVTheoNam.setVisible(false);
                        scrDTKHTheoNgay.setVisible(false);
                        scrDTKHTheoThang.setVisible(false);
                        scrDTKHTheoNam.setVisible(false);
                        scrSPTK.setVisible(false);
                        scrSPBCTheoNgay.setVisible(true);
                        scrSPBCTheoThang.setVisible(false);
                        scrSPBCTheoNam.setVisible(false);
                        break;
                }
                cmbChonLoaiTK.setVisible(true);
                cmbChonThang.setVisible(false);
                cmbChonNam.setVisible(false);
                ngayTK.setVisible(true);
                break;
        }
    }
    
    public void xuLyChonLoaiTK() {
        String selectedNam = cmbChonNam.getSelectedItem()+"";
        int nam = Integer.parseInt(selectedNam);
        String selected = cmbChonLoaiTK.getSelectedItem() + "";
        switch(selected) {
            case "Doanh thu":
                loadDataLenBangDTTheoNam(nam);
                lblTitle.setText("BẢNG DOANH THU THEO NĂM");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(true);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(false);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
               cmbChonTGTK.setVisible(true);
               cmbChonNam.setVisible(true);
               cmbChonThang.setVisible(false);
               ngayTK.setVisible(false);
               pnlTK.setVisible(false);
               cmbChonTGTK.setSelectedIndex(0);
                break;
            case "Doanh thu nhân viên":
                loadDataLenBangDTNVTheoNam(nam);
                pnlTK.setVisible(true);
                
                lblTitle.setText("BẢNG DOANH THU THEO NĂM");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(true);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(false);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
                cmbChonNam.setVisible(true);
                cmbChonTGTK.setVisible(true);
                cmbChonThang.setVisible(false);
                ngayTK.setVisible(false);
                cmbChonTGTK.setSelectedIndex(0);
                break;
            case "Doanh thu khách hàng":
                loadDataLenBangDTKHTheoNam(nam);
                pnlTK.setVisible(true);
                
                lblTitle.setText("BẢNG DOANH THU THEO NĂM");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(true);
                scrSPTK.setVisible(false);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
                cmbChonNam.setVisible(true);
                cmbChonTGTK.setVisible(true);
                cmbChonThang.setVisible(false);
                ngayTK.setVisible(false);
                cmbChonTGTK.setSelectedIndex(0);
                break;
            case "Hàng tồn":
                loadDataLenBangDSHangTon();
                pnlTK.setVisible(true);
                
                lblTitle.setText("DANH SÁCH HÀNG TỒN");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(true);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
                cmbChonTGTK.setVisible(false);
                cmbChonNam.setVisible(false);
                cmbChonThang.setVisible(false);
                ngayTK.setVisible(false);
                break;
            case "Sản phẩm bán chạy":
                loadDataLenBangDSBCTheoNam(nam);
                pnlTK.setVisible(true);
                
                lblTitle.setText("DANH SÁCH SẢN PHẨM BÁN CHẠY THEO NĂM");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(false);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(true);
                
                cmbChonNam.setVisible(true);
                cmbChonTGTK.setVisible(true);
                cmbChonThang.setVisible(false);
                ngayTK.setVisible(false);
                cmbChonTGTK.setSelectedIndex(0);
                break;
        }
    }
    
    public void xuLyChonNamTK() {
        String selectedNam = cmbChonNam.getSelectedItem()+"";
        String selectedTGTK = cmbChonTGTK.getSelectedItem() + "";
        int nam = Integer.parseInt(selectedNam);
        String selected = cmbChonLoaiTK.getSelectedItem() + "";
        switch(selected) {
            case "Doanh thu":
                
                if(selectedTGTK.equals("Tháng")) {
                    lblTitle.setText("KHÔNG CÓ THỐNG KÊ DOANH THU THEO THÁNG");
                    pnlTK.setVisible(false);
                } else {
                    loadDataLenBangDTTheoNam(nam);
                    lblTitle.setText("BẢNG DOANH THU THEO NĂM");
                    pnlTK.setVisible(false);
                    scrDTTheoNgay.setVisible(false);
                    scrDTTheoNam.setVisible(true);
                    scrDTNVTheoNgay.setVisible(false);
                    scrDTNVTheoThang.setVisible(false);
                    scrDTNVTheoNam.setVisible(false);
                    scrDTKHTheoNgay.setVisible(false);
                    scrDTKHTheoThang.setVisible(false);
                    scrDTKHTheoNam.setVisible(false);
                    scrSPTK.setVisible(false);
                    scrSPBCTheoNgay.setVisible(false);
                    scrSPBCTheoThang.setVisible(false);
                    scrSPBCTheoNam.setVisible(false);

                   cmbChonTGTK.setVisible(true);
                   cmbChonNam.setVisible(true);
                   ngayTK.setVisible(false);
                }
                
                break;
            case "Doanh thu nhân viên":
                if(selectedTGTK.equals("Tháng")) {
                    String selectedThang = cmbChonThang.getSelectedItem() + "";
                    String[] thang = selectedThang.split(" ");
                    int month = Integer.parseInt(thang[1]);
                    
                    loadDataLenBangDTNVTheoThang(month, nam);
                    pnlTK.setVisible(true);
                    
                    lblTitle.setText("BẢNG DOANH THU THEO THÁNG");
                    scrDTTheoNgay.setVisible(false);
                    scrDTTheoNam.setVisible(false);
                    scrDTNVTheoNgay.setVisible(false);
                    scrDTNVTheoThang.setVisible(true);
                    scrDTNVTheoNam.setVisible(false);
                    scrDTKHTheoNgay.setVisible(false);
                    scrDTKHTheoThang.setVisible(false);
                    scrDTKHTheoNam.setVisible(false);
                    scrSPTK.setVisible(false);
                    scrSPBCTheoNgay.setVisible(false);
                    scrSPBCTheoThang.setVisible(false);
                    scrSPBCTheoNam.setVisible(false);

                    cmbChonNam.setVisible(true);
                    cmbChonTGTK.setVisible(true);
                    cmbChonThang.setVisible(true);
                    ngayTK.setVisible(false);
                } else {
                    loadDataLenBangDTNVTheoNam(nam);
                    pnlTK.setVisible(true);
                    
                    lblTitle.setText("BẢNG DOANH THU THEO NĂM");
                    scrDTTheoNgay.setVisible(false);
                    scrDTTheoNam.setVisible(false);
                    scrDTNVTheoNgay.setVisible(false);
                    scrDTNVTheoThang.setVisible(false);
                    scrDTNVTheoNam.setVisible(true);
                    scrDTKHTheoNgay.setVisible(false);
                    scrDTKHTheoThang.setVisible(false);
                    scrDTKHTheoNam.setVisible(false);
                    scrSPTK.setVisible(false);
                    scrSPBCTheoNgay.setVisible(false);
                    scrSPBCTheoThang.setVisible(false);
                    scrSPBCTheoNam.setVisible(false);

                    cmbChonNam.setVisible(true);
                    cmbChonTGTK.setVisible(true);
                    ngayTK.setVisible(false);
                }
                break;
            case "Doanh thu khách hàng":
                if(selectedTGTK.equals("Tháng")) {
                    String selectedThang = cmbChonThang.getSelectedItem() + "";
                    String[] thang = selectedThang.split(" ");
                    int month = Integer.parseInt(thang[1]);
                    pnlTK.setVisible(true);
                    
                    loadDataLenBangDTKHTheoThang(month, nam);
                    lblTitle.setText("BẢNG DOANH THU THEO THÁNG");
                    scrDTTheoNgay.setVisible(false);
                    scrDTTheoNam.setVisible(false);
                    scrDTNVTheoNgay.setVisible(false);
                    scrDTNVTheoThang.setVisible(false);
                    scrDTNVTheoNam.setVisible(false);
                    scrDTKHTheoNgay.setVisible(false);
                    scrDTKHTheoThang.setVisible(true);
                    scrDTKHTheoNam.setVisible(false);
                    scrSPTK.setVisible(false);
                    scrSPBCTheoNgay.setVisible(false);
                    scrSPBCTheoThang.setVisible(false);
                    scrSPBCTheoNam.setVisible(false);

                    cmbChonNam.setVisible(true);
                    cmbChonTGTK.setVisible(true);
                    cmbChonThang.setVisible(true);
                    ngayTK.setVisible(false);
                } else {
                    loadDataLenBangDTKHTheoNam(nam);
                    pnlTK.setVisible(true);
                    
                    lblTitle.setText("BẢNG DOANH THU THEO NĂM");
                    scrDTTheoNgay.setVisible(false);
                    scrDTTheoNam.setVisible(false);
                    scrDTNVTheoNgay.setVisible(false);
                    scrDTNVTheoThang.setVisible(false);
                    scrDTNVTheoNam.setVisible(false);
                    scrDTKHTheoNgay.setVisible(false);
                    scrDTKHTheoThang.setVisible(false);
                    scrDTKHTheoNam.setVisible(true);
                    scrSPTK.setVisible(false);
                    scrSPBCTheoNgay.setVisible(false);
                    scrSPBCTheoThang.setVisible(false);
                    scrSPBCTheoNam.setVisible(false);

                    cmbChonNam.setVisible(true);
                    cmbChonTGTK.setVisible(true);
                    cmbChonThang.setVisible(false);
                    ngayTK.setVisible(false);
                }
                break;
            case "Hàng tồn":
                loadDataLenBangDSHangTon();
                pnlTK.setVisible(true);
                
                lblTitle.setText("DANH SÁCH HÀNG TỒN");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(true);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
                cmbChonTGTK.setVisible(false);
                cmbChonNam.setVisible(false);
                cmbChonThang.setVisible(false);
                ngayTK.setVisible(false);
                break;
            case "Sản phẩm bán chạy":
                if(selectedTGTK.equals("Tháng")) {
                    String selectedThang = cmbChonThang.getSelectedItem() + "";
                    String[] thang = selectedThang.split(" ");
                    int month = Integer.parseInt(thang[1]);
                    
                    pnlTK.setVisible(true);
                    loadDataLenBangDSBCTheoThang(month, nam);
                    lblTitle.setText("DANH SÁCH SẢN PHẨM BÁN CHẠY THEO THÁNG");
                    scrDTTheoNgay.setVisible(false);
                    scrDTTheoNam.setVisible(false);
                    scrDTNVTheoNgay.setVisible(false);
                    scrDTNVTheoThang.setVisible(false);
                    scrDTNVTheoNam.setVisible(false);
                    scrDTKHTheoNgay.setVisible(false);
                    scrDTKHTheoThang.setVisible(false);
                    scrDTKHTheoNam.setVisible(false);
                    scrSPTK.setVisible(false);
                    scrSPBCTheoNgay.setVisible(false);
                    scrSPBCTheoThang.setVisible(true);
                    scrSPBCTheoNam.setVisible(false);

                    cmbChonNam.setVisible(true);
                    cmbChonTGTK.setVisible(true);
                    cmbChonThang.setVisible(true);
                    ngayTK.setVisible(false);
                } else {
                    loadDataLenBangDSBCTheoNam(nam);
                    pnlTK.setVisible(true);
                    
                    lblTitle.setText("DANH SÁCH SẢN PHẨM BÁN CHẠY THEO NĂM");
                    scrDTTheoNgay.setVisible(false);
                    scrDTTheoNam.setVisible(false);
                    scrDTNVTheoNgay.setVisible(false);
                    scrDTNVTheoThang.setVisible(false);
                    scrDTNVTheoNam.setVisible(false);
                    scrDTKHTheoNgay.setVisible(false);
                    scrDTKHTheoThang.setVisible(false);
                    scrDTKHTheoNam.setVisible(false);
                    scrSPTK.setVisible(false);
                    scrSPBCTheoNgay.setVisible(false);
                    scrSPBCTheoThang.setVisible(false);
                    scrSPBCTheoNam.setVisible(true);

                    cmbChonNam.setVisible(true);
                    cmbChonTGTK.setVisible(true);
                    cmbChonThang.setVisible(false);
                    ngayTK.setVisible(false);
                }
                break;
        }
    }
    
    public void xuLyChonThangTK() {
        String selectedNam = cmbChonNam.getSelectedItem()+"";
        String selectedThang = cmbChonThang.getSelectedItem() + "";
        String[] thang = selectedThang.split(" ");
        int month = Integer.parseInt(thang[1]);
        int nam = Integer.parseInt(selectedNam);
        String selected = cmbChonLoaiTK.getSelectedItem() + "";
        switch(selected) {
            case "Doanh thu":
                lblTitle.setText("KHÔNG CÓ THỐNG KÊ DOANH THU THEO THÁNG");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false); 
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(false);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
               cmbChonTGTK.setVisible(true);
               cmbChonNam.setVisible(true);
               cmbChonThang.setVisible(true);
               ngayTK.setVisible(false);
               pnlTK.setVisible(false);
                break;
            case "Doanh thu nhân viên":
                loadDataLenBangDTNVTheoThang(month, nam);
                pnlTK.setVisible(true);
                
                lblTitle.setText("BẢNG DOANH THU THEO THÁNG");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(true);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(false);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
                cmbChonNam.setVisible(true);
                cmbChonTGTK.setVisible(true);
                cmbChonThang.setVisible(true);
                ngayTK.setVisible(false);
                break;
            case "Doanh thu khách hàng":
                
                pnlTK.setVisible(true);
                loadDataLenBangDTKHTheoThang(month, nam);
                lblTitle.setText("BẢNG DOANH THU THEO THÁNG");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(true);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(false);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
                cmbChonNam.setVisible(true);
                cmbChonTGTK.setVisible(true);
                cmbChonThang.setVisible(true);
                ngayTK.setVisible(false);
                break;
            case "Hàng tồn":
                pnlTK.setVisible(true);
                loadDataLenBangDSHangTon();
                lblTitle.setText("DANH SÁCH HÀNG TỒN");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(true);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
                cmbChonTGTK.setVisible(false);
                cmbChonNam.setVisible(false);
                cmbChonThang.setVisible(false);
                ngayTK.setVisible(false);
                break;
            case "Sản phẩm bán chạy":
                pnlTK.setVisible(true);
                loadDataLenBangDSBCTheoThang(month, nam);
                lblTitle.setText("DANH SÁCH SẢN PHẨM BÁN CHẠY THEO THÁNG");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(false);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(true);
                scrSPBCTheoNam.setVisible(false);
                
                cmbChonNam.setVisible(true);
                cmbChonTGTK.setVisible(true);
                cmbChonThang.setVisible(true);
                ngayTK.setVisible(false);
                break;
        }
    }
    
    public void xuLyChonNgayTK() {
        Date date = ngayTK.getDate();
        String selected = cmbChonLoaiTK.getSelectedItem() + "";
        switch(selected) {
            case "Doanh thu":
                loadDataLenBangDSHDTheoNgay(date);
                pnlTK.setVisible(true);
                lblTitle.setText("BẢNG DANH SÁCH HÓA ĐƠN THEO NGÀY");
                scrDTTheoNgay.setVisible(true);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(false);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
               cmbChonTGTK.setVisible(true);
               cmbChonNam.setVisible(false);
               cmbChonThang.setVisible(false);
               ngayTK.setVisible(true);
                break;
            case "Doanh thu nhân viên":
                pnlTK.setVisible(true);
                loadDataLenBangDTNVTheoNgay(date);
                lblTitle.setText("BẢNG DOANH THU THEO NGÀY");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(true);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(false);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
                cmbChonNam.setVisible(false);
                cmbChonTGTK.setVisible(true);
                cmbChonThang.setVisible(false);
                ngayTK.setVisible(true);
                break;
            case "Doanh thu khách hàng":
                pnlTK.setVisible(true);
                loadDataLenBangDTKHTheoNgay(date);
                lblTitle.setText("BẢNG DOANH THU THEO NGÀY");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(true);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(false);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
                cmbChonNam.setVisible(false);
                cmbChonTGTK.setVisible(true);
                cmbChonThang.setVisible(false);
                ngayTK.setVisible(true);
                break;
            case "Hàng tồn":
                loadDataLenBangDSHangTon();
                lblTitle.setText("DANH SÁCH HÀNG TỒN");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(true);
                scrSPBCTheoNgay.setVisible(false);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
                cmbChonTGTK.setVisible(false);
                cmbChonNam.setVisible(false);
                cmbChonThang.setVisible(false);
                ngayTK.setVisible(false);
                break;
            case "Sản phẩm bán chạy":
                pnlTK.setVisible(true);
                loadDataLenBangDSBCTheoNgay(date);
                lblTitle.setText("DANH SÁCH SẢN PHẨM BÁN CHẠY THEO NGÀY");
                scrDTTheoNgay.setVisible(false);
                scrDTTheoNam.setVisible(false);
                scrDTNVTheoNgay.setVisible(false);
                scrDTNVTheoThang.setVisible(false);
                scrDTNVTheoNam.setVisible(false);
                scrDTKHTheoNgay.setVisible(false);
                scrDTKHTheoThang.setVisible(false);
                scrDTKHTheoNam.setVisible(false);
                scrSPTK.setVisible(false);
                scrSPBCTheoNgay.setVisible(true);
                scrSPBCTheoThang.setVisible(false);
                scrSPBCTheoNam.setVisible(false);
                
                cmbChonNam.setVisible(false);
                cmbChonTGTK.setVisible(true);
                cmbChonThang.setVisible(false);
                ngayTK.setVisible(true);
                break;
        }
    }
    
    public void loadDataLenBangDTTheoNam(int year) {
        modelDTTheoNam.setRowCount(0);
        
        Vector vec = new Vector();
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuThang(1,year)));
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuThang(2,year)));
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuThang(3,year)));
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuThang(4,year)));
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuThang(5,year)));
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuThang(6,year)));
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuThang(7,year)));
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuThang(8,year)));
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuThang(9,year)));
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuThang(10,year)));
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuThang(11,year)));
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuThang(12,year)));
        vec.add(dcf.format(thongKeBUS.tinhDoanhThuTheoNam(year)));

        modelDTTheoNam.addRow(vec);
    }
    
    public void loadDataLenBangDSHDTheoNgay(Date date) {
        ArrayList<HoaDon> dshd = thongKeBUS.getDanhSachHDTheoNgay(date);
        modelDTTheoNgay.setRowCount(0);
        
        for(HoaDon hd : dshd) {
            Vector vec = new Vector();
            vec.add(hd.getMaHD());
            vec.add(layHoTenNhanVien(hd.getMaNV()));
            vec.add(layHoTenKhachHang(hd.getMaKH()));
            vec.add(spdf.format(hd.getNgayLap()));
            vec.add(dcf.format(hd.getTongTien()));
            vec.add(layMaGiamGia(hd.getMaGG()));
            modelDTTheoNgay.addRow(vec);
        }
    }
    
    public void loadDataLenBangDSHDTheoNgay(ArrayList<HoaDon> dshd) {
        modelDTTheoNgay.setRowCount(0);
        
        for(HoaDon hd : dshd) {
            Vector vec = new Vector();
            vec.add(hd.getMaHD());
            vec.add(layHoTenNhanVien(hd.getMaNV()));
            vec.add(layHoTenKhachHang(hd.getMaKH()));
            vec.add(spdf.format(hd.getNgayLap()));
            vec.add(dcf.format(hd.getTongTien()));
            vec.add(layMaGiamGia(hd.getMaGG()));
            modelDTTheoNgay.addRow(vec);
        }
    }
    
    public void loadDataLenBangDSHangTon() {
        ArrayList<SanPham> dssp = thongKeBUS.getDanhSachSPTonHang();
        modelSPTK.setRowCount(0);
        
        for(SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            vec.add(sp.getSoLuong());
            modelSPTK.addRow(vec);
        }
    }
    
    
    public void loadDataLenBangDSHangTon(ArrayList<SanPham> dssp) {
        modelSPTK.setRowCount(0);
        
        for(SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            vec.add(sp.getSoLuong());
            modelSPTK.addRow(vec);
        }
    }
    
    public void loadDataLenBangDTNVTheoNgay(Date date) {
        ArrayList<DoanhThuNhanVien> dsnv = thongKeBUS.getDanhSachDTNVTheoNgay(date);
        modelDTNVTheoNgay.setRowCount(0);
        
        for(DoanhThuNhanVien nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getMaNV());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(dcf.format(nv.getTongTienBan()));
            modelDTNVTheoNgay.addRow(vec);
        }
    }
    
    
    public void loadDataLenBangDTNVTheoNgay( ArrayList<DoanhThuNhanVien> dsnv) {
        modelDTNVTheoNgay.setRowCount(0);
        
        for(DoanhThuNhanVien nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getMaNV());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(dcf.format(nv.getTongTienBan()));
            modelDTNVTheoNgay.addRow(vec);
        }
    }
    
    
    public void loadDataLenBangDTNVTheoThang(int thang, int nam) {
        ArrayList<DoanhThuNhanVien> dsnv = thongKeBUS.getDanhSachDTNVTheoThang(thang, nam);
        modelDTNVTheoThang.setRowCount(0);
        
        for(DoanhThuNhanVien nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getMaNV());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(dcf.format(nv.getTongTienBan()));
            modelDTNVTheoThang.addRow(vec);
        }
    }
    
    
    public void loadDataLenBangDTNVTheoThang(ArrayList<DoanhThuNhanVien> dsnv) {
        modelDTNVTheoThang.setRowCount(0);
        
        for(DoanhThuNhanVien nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getMaNV());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(dcf.format(nv.getTongTienBan()));
            modelDTNVTheoThang.addRow(vec);
        }
    }
    
    
    public void loadDataLenBangDTNVTheoNam(int nam) {
        ArrayList<DoanhThuNhanVien> dsnv = thongKeBUS.getDanhSachDTNVTheoNam(nam);
        modelDTNVTheoNam.setRowCount(0);
        
        for(DoanhThuNhanVien nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getMaNV());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(dcf.format(nv.getTongTienBan()));
            modelDTNVTheoNam.addRow(vec);
        }
    }
    
        
    public void loadDataLenBangDTNVTheoNam(ArrayList<DoanhThuNhanVien> dsnv) {
        modelDTNVTheoNam.setRowCount(0);
        
        for(DoanhThuNhanVien nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getMaNV());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(dcf.format(nv.getTongTienBan()));
            modelDTNVTheoNam.addRow(vec);
        }
    }
    
        
    public void loadDataLenBangDTKHTheoNgay(Date date) {
        ArrayList<DoanhThuKhachHang> dskh = thongKeBUS.getDanhSachDTKHTheoNgay(date);
        modelDTKHTheoNgay.setRowCount(0);
        
        for(DoanhThuKhachHang kh : dskh) {
            Vector vec = new Vector();
            vec.add(kh.getMaKH());
            vec.add(kh.getHo());
            vec.add(kh.getTen());
            vec.add(dcf.format(kh.getTongTienMua()));
            modelDTKHTheoNgay.addRow(vec);
        }
    }
    
        
    public void loadDataLenBangDTKHTheoNgay(ArrayList<DoanhThuKhachHang> dskh) {
        modelDTKHTheoNgay.setRowCount(0);
        
        for(DoanhThuKhachHang kh : dskh) {
            Vector vec = new Vector();
            vec.add(kh.getMaKH());
            vec.add(kh.getHo());
            vec.add(kh.getTen());
            vec.add(dcf.format(kh.getTongTienMua()));
            modelDTKHTheoNgay.addRow(vec);
        }
    }
    
        
    public void loadDataLenBangDTKHTheoThang(int thang, int nam) {
        ArrayList<DoanhThuKhachHang> dskh = thongKeBUS.getDanhSachDTKHTheoThang(thang, nam);
        modelDTKHTheoThang.setRowCount(0);
        
        for(DoanhThuKhachHang kh : dskh) {
            Vector vec = new Vector();
            vec.add(kh.getMaKH());
            vec.add(kh.getHo());
            vec.add(kh.getTen());
            vec.add(dcf.format(kh.getTongTienMua()));
            modelDTKHTheoThang.addRow(vec);
        }
    }
    
        
    public void loadDataLenBangDTKHTheoThang(ArrayList<DoanhThuKhachHang> dskh ) {
        modelDTKHTheoThang.setRowCount(0);
        
        for(DoanhThuKhachHang kh : dskh) {
            Vector vec = new Vector();
            vec.add(kh.getMaKH());
            vec.add(kh.getHo());
            vec.add(kh.getTen());
            vec.add(dcf.format(kh.getTongTienMua()));
            modelDTKHTheoThang.addRow(vec);
        }
    }
    
        
    public void loadDataLenBangDTKHTheoNam(int nam) {
        ArrayList<DoanhThuKhachHang> dskh = thongKeBUS.getDanhSachDTKHTheoNam(nam);
        modelDTKHTheoNam.setRowCount(0);
        
        for(DoanhThuKhachHang kh : dskh) {
            Vector vec = new Vector();
            vec.add(kh.getMaKH());
            vec.add(kh.getHo());
            vec.add(kh.getTen());
            vec.add(dcf.format(kh.getTongTienMua()));
            modelDTKHTheoNam.addRow(vec);
        }
    }
    
        
    public void loadDataLenBangDTKHTheoNam(ArrayList<DoanhThuKhachHang> dskh ) {
        modelDTKHTheoNam.setRowCount(0);
        
        for(DoanhThuKhachHang kh : dskh) {
            Vector vec = new Vector();
            vec.add(kh.getMaKH());
            vec.add(kh.getHo());
            vec.add(kh.getTen());
            vec.add(dcf.format(kh.getTongTienMua()));
            modelDTKHTheoNam.addRow(vec);
        }
    }
    
        
    public void loadDataLenBangDSBCTheoNgay(Date date) {
        ArrayList<SanPham> dssp = thongKeBUS.getDanhSachSPBanTheoNgay(date);
        modelSPBCTheoNgay.setRowCount(0);
        
        for(SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            vec.add(sp.getSoLuong());
            modelSPBCTheoNgay.addRow(vec);
        }
    }
    
        
    public void loadDataLenBangDSBCTheoNgay(ArrayList<SanPham> dssp) {
        modelSPBCTheoNgay.setRowCount(0);
        
        for(SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            vec.add(sp.getSoLuong());
            modelSPBCTheoNgay.addRow(vec);
        }
    }
    
        
    public void loadDataLenBangDSBCTheoThang(int thang, int nam) {
        ArrayList<SanPham> dssp = thongKeBUS.getDanhSachSPBanTheoThang(thang, nam);
        modelSPBCTheoThang.setRowCount(0);
        
        for(SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            vec.add(sp.getSoLuong());
            modelSPBCTheoThang.addRow(vec);
        }
    }
    
        
        
    public void loadDataLenBangDSBCTheoThang(ArrayList<SanPham> dssp) {
        modelSPBCTheoThang.setRowCount(0);
        
        for(SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            vec.add(sp.getSoLuong());
            modelSPBCTheoThang.addRow(vec);
        }
    }
    
        
    public void loadDataLenBangDSBCTheoNam(int nam) {
        ArrayList<SanPham> dssp = thongKeBUS.getDanhSachSPBanTheoNam(nam);
        modelSPBCTheoNam.setRowCount(0);
        
        for(SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            vec.add(sp.getSoLuong());
            modelSPBCTheoNam.addRow(vec);
        }
    }
    
        
    public void loadDataLenBangDSBCTheoNam(ArrayList<SanPham> dssp) {
        modelSPBCTheoNam.setRowCount(0);
        
        for(SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            vec.add(sp.getSoLuong());
            modelSPBCTheoNam.addRow(vec);
        }
    }
    
    public String layHoTenNhanVien(int ma) {
            String hoTen = "";
            NhanVien nv = nvBUS.getNhanVien(ma);
            try {
            hoTen = nv.getHo() + " " + nv.getTen();
        } catch (Exception e) {
        }
            return hoTen;
        }
        
    
    public String layHoTenKhachHang(int ma) {
            String hoTen = "";
            KhachHang kh = khBUS.getKhachHang(ma);
            try {
            hoTen = kh.getHo() + " " + kh.getTen();
        } catch (Exception e) {
        }
            return hoTen;
        }
        
    public String layMaGiamGia(int ma) {
            String maGG = "";
            GiamGia gg = ggBUS.getGiamGia(ma);
            try {
            maGG = gg.getTenGG();
        } catch (Exception e) {
        }
            return maGG;
        }
            
    private void xuLyLiveSearchDT() {
        Date date = ngayTK.getDate();
        String tuKhoa = txtTimKiem.getText().toLowerCase().trim();
        ArrayList<HoaDon> dshd = thongKeBUS.timKiemHDTrongDanhSachHDTheoNgay(tuKhoa, thongKeBUS.getDanhSachHDTheoNgay(date));
        loadDataLenBangDSHDTheoNgay(dshd);
        if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
            loadDataLenBangDSHDTheoNgay(date);
    }
    
            
    private void xuLyLiveSearchDTNV() {
        String tuKhoa = txtTimKiem.getText().toLowerCase().trim();
        String selectTGTK = cmbChonTGTK.getSelectedItem()+"";
        ArrayList<DoanhThuNhanVien> dsnv = new ArrayList<>();
        int nam = Integer.parseInt(cmbChonNam.getSelectedItem() + "");
        String[] thang = (cmbChonThang.getSelectedItem() + "").split(" ");
        int month = Integer.parseInt(thang[1]);
        Date date = ngayTK.getDate();

        switch(selectTGTK) {
            case "Năm":
                dsnv = thongKeBUS.timKiemNhanVien(tuKhoa, thongKeBUS.getDanhSachDTNVTheoNam(nam));
                loadDataLenBangDTNVTheoNam(dsnv);
                
                if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
                    loadDataLenBangDTNVTheoNam(nam);
                break;
            case "Tháng":
                dsnv = thongKeBUS.timKiemNhanVien(tuKhoa, thongKeBUS.getDanhSachDTNVTheoThang(month, nam));
                loadDataLenBangDTNVTheoThang(dsnv);
                
                if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
                    loadDataLenBangDTNVTheoThang(month, nam);
                break;
            case "Ngày":
                dsnv = thongKeBUS.timKiemNhanVien(tuKhoa, thongKeBUS.getDanhSachDTNVTheoNgay(date));
                loadDataLenBangDTNVTheoNgay(dsnv);
                
                if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
                    loadDataLenBangDTNVTheoNgay(date);
                break;
        }
    }
            
    private void xuLyLiveSearchDTKH() {
        String tuKhoa = txtTimKiem.getText().toLowerCase().trim();
        String selectTGTK = cmbChonTGTK.getSelectedItem()+"";
        ArrayList<DoanhThuKhachHang> dskh = new ArrayList<>();
        int nam = Integer.parseInt(cmbChonNam.getSelectedItem() + "");
        String[] thang = (cmbChonThang.getSelectedItem() + "").split(" ");
        int month = Integer.parseInt(thang[1]);
        Date date = ngayTK.getDate();

        switch(selectTGTK) {
            case "Năm":
                dskh = thongKeBUS.timKiemKhachHang(tuKhoa, thongKeBUS.getDanhSachDTKHTheoNam(nam));
                loadDataLenBangDTKHTheoNam(dskh);
                
                if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
                    loadDataLenBangDTKHTheoNam(nam);
                break;
            case "Tháng":
                dskh = thongKeBUS.timKiemKhachHang(tuKhoa, thongKeBUS.getDanhSachDTKHTheoThang(month, nam));
                loadDataLenBangDTKHTheoThang(dskh);
                
                if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
                    loadDataLenBangDTKHTheoThang(month, nam);
                break;
            case "Ngày":
                dskh = thongKeBUS.timKiemKhachHang(tuKhoa, thongKeBUS.getDanhSachDTKHTheoNgay(date));
                loadDataLenBangDTKHTheoNgay(dskh);
                
                if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
                    loadDataLenBangDTKHTheoNgay(date);
                break;
        }
    }
            
    private void xuLyLiveSearchSPBC() {
        String tuKhoa = txtTimKiem.getText().toLowerCase().trim();
        String selectTGTK = cmbChonTGTK.getSelectedItem()+"";
        ArrayList<SanPham> dssp = new ArrayList<>();
        int nam = Integer.parseInt(cmbChonNam.getSelectedItem() + "");
        String[] thang = (cmbChonThang.getSelectedItem() + "").split(" ");
        int month = Integer.parseInt(thang[1]);
        Date date = ngayTK.getDate();

        switch(selectTGTK) {
            case "Năm":
                dssp = thongKeBUS.timKiemSanPham(tuKhoa, thongKeBUS.getDanhSachSPBanTheoNam(nam));
                loadDataLenBangDSBCTheoNam(dssp);
                
                if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
                    loadDataLenBangDSBCTheoNam(nam);
                break;
            case "Tháng":
                dssp = thongKeBUS.timKiemSanPham(tuKhoa, thongKeBUS.getDanhSachSPBanTheoThang(month, nam));
                loadDataLenBangDSBCTheoThang(dssp);
                
                if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
                    loadDataLenBangDSBCTheoThang(month, nam);
                break;
            case "Ngày":
                dssp = thongKeBUS.timKiemSanPham(tuKhoa, thongKeBUS.getDanhSachSPBanTheoNgay(date));
                loadDataLenBangDSBCTheoNgay(dssp);
                
                if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
                    loadDataLenBangDSBCTheoNgay(date);
                break;
        }
    }
            
    private void xuLyLiveSearchHT() {
        String tuKhoa = txtTimKiem.getText().toLowerCase().trim();
        ArrayList<SanPham> dssp = thongKeBUS.timKiemSanPham(tuKhoa, thongKeBUS.timKiemSanPham(tuKhoa, thongKeBUS.getDanhSachSPTonHang()));
        loadDataLenBangDSHangTon(dssp);
        if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
            loadDataLenBangDSHangTon();
    }
}
