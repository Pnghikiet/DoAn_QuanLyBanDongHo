
package GUI;

import BUS.KhachHangBUS;
import DAO.MyConnect;
import DTO.KhachHang;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
public final class FrmQuanLyKhachHang extends JFrame{
    private final KhachHangBUS khachHangBUS = new KhachHangBUS();
    
    private JLabel lblTabbedKhachHang, lblTabbedKhachHangBiXoa;
    final ImageIcon tabbedSelected = new ImageIcon("images/ManagerUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("images/ManagerUI/tabbed-btn.png");
    private JPanel pnlCardTabKhachHang;
    private final CardLayout cardKhachHangGroup = new CardLayout();
    private JLabel lblTitleKhachHang, lblMaKH, lblHoKH, lblTenKH, lblGioiTinh, lblSoDienThoai, lblTongChiTieu, lblTim, lblTimChiTieu, lblDen;
    private JTextField txtMaKH, txtHoKH, txtTenKH,txtSoDienThoai, txtTongChiTieu, txtTim, txtMinChiTieu, txtMaxChiTieu;
    private JButton btnReset, btnThem, btnSua, btnXoa, btnTimKiem;
    private JTable tblKhachHang;
    private DefaultTableModel modelKhachHang;
    public JComboBox<String> cmbGioiTinh;
    private JLabel lblTitleKhachHangBX, lblMaKHBX, lblHoKHBX, lblTenKHBX, lblGioiTinhBX, lblSoDienThoaiBX, lblTongChiTieuBX, lblTimBX, lblTimChiTieuBX, lblDenBX;
    private JTextField txtMaKHBX, txtHoKHBX, txtTenKHBX,txtSoDienThoaiBX, txtTongChiTieuBX, txtTimBX, txtMinChiTieuBX, txtMaxChiTieuBX;
    private JButton btnResetBX, btnKhoiPhuc, btnSuaBX, btnXoaVinhVien, btnTimKiemBX;
    private JTable tblKhachHangBX;
    private DefaultTableModel modelKhachHangBX;
    public JComboBox<String> cmbGioiTinhBX;
    private Font font,fontTabbed;
    private final String placeholderTimKiem = "Nhập từ khóa mà bạn muốn tìm kiếm...";
    
    public FrmQuanLyKhachHang() {
       addControls();
       addEventsKhachHang();
       addEventsKhachHangBiXoa();
    }
    
    public void addControls() {
        setTitle("Quản lý khách hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        GridBagConstraints cons = new GridBagConstraints();
        GridBagConstraints consBX = new GridBagConstraints();
        font = new Font("Arial", Font.BOLD, 26);

//        Pnl TOP
        JPanel pnlTop = new JPanel();
        pnlTop.setOpaque(false);
        pnlTop.setPreferredSize(new Dimension(1000, 41));
        pnlTop.setLayout(null);
        pnlTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));
        
        fontTabbed = new Font("Arial", Font.BOLD, 14);
        
        lblTabbedKhachHang = new JLabel("Khách hàng");
        lblTabbedKhachHang.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedKhachHang.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedKhachHang.setIcon(tabbedSelected);
        lblTabbedKhachHang.setBounds(2, 2, 140, 37);
        lblTabbedKhachHang.setFont(fontTabbed);
        lblTabbedKhachHang.setForeground(Color.white);
        lblTabbedKhachHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedKhachHangBiXoa = new JLabel("Khách hàng bị xóa");
        lblTabbedKhachHangBiXoa.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedKhachHangBiXoa.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedKhachHangBiXoa.setIcon(tabbedDefault);
        lblTabbedKhachHangBiXoa.setBounds(143, 2, 140, 37);
        lblTabbedKhachHangBiXoa.setFont(fontTabbed);
        lblTabbedKhachHangBiXoa.setForeground(Color.white);
        lblTabbedKhachHangBiXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        pnlTop.add(lblTabbedKhachHang);
        pnlTop.add(lblTabbedKhachHangBiXoa);
        
        //        Thêm pnlTop vào Frame
        this.add(pnlTop, BorderLayout.NORTH);
        
        DefaultTableCellRenderer centerRederer = new DefaultTableCellRenderer();
        centerRederer.setHorizontalAlignment(JLabel.CENTER);
        
        /*
        =========================================================================
                                    PANEL KHÁCH HÀNG
        =========================================================================
         */
        
        JPanel pnlKhachHang = new JPanel(new GridBagLayout());
        
        lblTitleKhachHang = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTitleKhachHang.setFont(font);
        
        lblMaKH = new JLabel("Mã Khách hàng");
        lblHoKH = new JLabel("Họ đệm");
        lblTenKH = new JLabel("Tên");
        lblGioiTinh = new JLabel("Giới tính");
        lblSoDienThoai = new JLabel("Số điện thoại");
        lblTongChiTieu = new JLabel("Tổng chi tiêu");
        lblTim = new JLabel("Từ khóa  tìm kiếm");
        lblTimChiTieu = new JLabel("Chi tiêu từ ");
        lblDen = new JLabel("đến:");
        
        txtMaKH = new JTextField(20);
        txtMaKH.setEditable(false);
        txtHoKH = new JTextField(20);
        txtTenKH = new JTextField(20);
        txtSoDienThoai = new JTextField(20);
        txtTongChiTieu = new JTextField(20);
        txtTongChiTieu.setEditable(false);
        txtTim = new JTextField(20);
        txtMinChiTieu = new JTextField(15);
        txtMaxChiTieu = new JTextField(15);
        
        txtTim.setForeground(Color.GRAY);
        txtTim.setText(placeholderTimKiem);
        
        btnReset = new JButton("Reset");
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTimKiem = new JButton("Tìm kiếm");
        
        cmbGioiTinh = new JComboBox<>();
        cmbGioiTinh.addItem("Chọn giới tính");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");

        
        
        // Panel Tìm kiếm theo chi tiêu
        JPanel pnlTimKiemTheoChiTieu = new JPanel(new GridBagLayout());
        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(5, 5, 5, 5);
        pnlTimKiemTheoChiTieu.add(lblTimChiTieu, cons);
        
        cons.gridx = 1;
        cons.gridy = 0;
        cons.insets = new Insets(5, 5, 5, 5);
        pnlTimKiemTheoChiTieu.add(txtMinChiTieu, cons);
        
        cons.gridx = 2;
        cons.gridy = 0;
        pnlTimKiemTheoChiTieu.add(lblDen, cons);
        
        cons.gridx = 3;
        cons.gridy = 0;
        pnlTimKiemTheoChiTieu.add(txtMaxChiTieu, cons);
        
        cons.gridx = 4;
        cons.gridy = 0;
        pnlTimKiemTheoChiTieu.add(btnTimKiem, cons);
        
//        Panel Title 
        JPanel pnlTitleKhachHang = new JPanel(new GridBagLayout());
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 1;
        cons.insets = new Insets(10, 10, 10, 10);
        cons.anchor = GridBagConstraints.CENTER;
        pnlTitleKhachHang.add(lblTitleKhachHang, cons);
        
        cons.gridx = 1;
        cons.gridy = 0;
        cons.gridwidth = 1;
        cons.anchor = GridBagConstraints.WEST;
        pnlTitleKhachHang.add(btnReset, cons);
        
//        Thêm pnlTitleKhachHang vào PanelKhachHang
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.PAGE_START;
        cons.fill = GridBagConstraints.BOTH;
        cons.gridwidth = 1;
        pnlKhachHang.add(pnlTitleKhachHang, cons);
        
       

//        Panel Thông tin Khách hàng
        JPanel pnlTTKH = new JPanel(new GridBagLayout());
        
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 1;
        cons.insets = new Insets(10, 10, 10, 10);
        cons.anchor = GridBagConstraints.WEST;
        pnlTTKH.add(lblMaKH, cons);
        
        cons.gridx = 1;
        cons.gridy = 0;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.BOTH;
        pnlTTKH.add(txtMaKH, cons);
        
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridwidth = 1;
        pnlTTKH.add(lblHoKH, cons);
        
        cons.gridx = 1;
        cons.gridy = 1;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.BOTH;
        pnlTTKH.add(txtHoKH, cons);
        
        cons.gridx = 0;
        cons.gridy = 2;
        cons.gridwidth = 1;
        pnlTTKH.add(lblTenKH, cons);
        
        cons.gridx = 1;
        cons.gridy = 2;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.BOTH;
        pnlTTKH.add(txtTenKH, cons);
        
        cons.gridx = 0;
        cons.gridy = 3;
        cons.gridwidth = 1;
        pnlTTKH.add(lblSoDienThoai, cons);
        
        cons.gridx = 1;
        cons.gridy = 3;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.BOTH;
        pnlTTKH.add(txtSoDienThoai, cons);
        
        cons.gridx = 0;
        cons.gridy = 4;
        cons.gridwidth = 1;
        pnlTTKH.add(lblGioiTinh, cons);
        
        cons.gridx = 1;
        cons.gridy = 4;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.BOTH;
        pnlTTKH.add(cmbGioiTinh, cons);
        
        cons.gridx = 0;
        cons.gridy = 5;
        cons.gridwidth = 1;
        pnlTTKH.add(lblTongChiTieu, cons);
        
        cons.gridx = 1;
        cons.gridy = 5;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.BOTH;
        pnlTTKH.add(txtTongChiTieu, cons);
        
//        Panel Tim kiem
        JPanel pnlTimKiem = new JPanel(new GridBagLayout());
        
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 1;
        pnlTimKiem.add(lblTim, cons);
        
        cons.gridx = 1;
        cons.gridy = 0;
        cons.gridwidth = 1;
        pnlTimKiem.add(txtTim, cons);
        
        
//        Panel Button
        JPanel pnlBtn = new JPanel(new GridBagLayout());
        
        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(10, 10, 10, 10);
        cons.fill = GridBagConstraints.BOTH;
        cons.gridwidth = 1;
        pnlBtn.add(btnThem, cons);
        
        cons.gridx = 1;
        cons.gridy = 0;
        cons.gridwidth = 1;
        pnlBtn.add(btnSua, cons);
        
        cons.gridx = 2;
        cons.gridy = 0;
        cons.gridwidth = 1;
        pnlBtn.add(btnXoa, cons);

//        add pnl Button vào pnl hiển thị
        
        cons.gridx = 0;
        cons.gridy = 6;
        cons.gridwidth = 2;
        cons.anchor = GridBagConstraints.CENTER;
        pnlTTKH.add(pnlBtn, cons);
        
//        Thêm pnl TTKH vào pnlKhachHang
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.fill = GridBagConstraints.BOTH;
        pnlKhachHang.add(pnlTTKH, cons);
            
//        Them pnl Tim Kiem vao pnlKhachHang
        cons.gridx = 0;
        cons.gridy = 2;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.fill = GridBagConstraints.BOTH;
        cons.anchor = GridBagConstraints.CENTER;
        pnlKhachHang.add(pnlTimKiem, cons);

//        Thêm Pnl Tìm kiếm theo chi tiêu vào pnlKhachHang
        cons.gridx = 0;
        cons.gridy = 3;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.fill = GridBagConstraints.BOTH;
        cons.anchor = GridBagConstraints.CENTER;
        pnlKhachHang.add(pnlTimKiemTheoChiTieu, cons);
        
        JPanel pnlTable = new JPanel(new BorderLayout());
        
        modelKhachHang = new DefaultTableModel();
        
        modelKhachHang.addColumn("Mã KH");
        modelKhachHang.addColumn("Họ đệm");
        modelKhachHang.addColumn("Tên");
        modelKhachHang.addColumn("Giới tính");
        modelKhachHang.addColumn("Số điện thoại");
        modelKhachHang.addColumn("Tổng chi tiêu");
        
        tblKhachHang = new JTable(modelKhachHang);
        
        tblKhachHang.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblKhachHang.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblKhachHang.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblKhachHang.getColumnModel().getColumn(3).setCellRenderer(centerRederer);
        tblKhachHang.getColumnModel().getColumn(4).setCellRenderer(centerRederer);
        tblKhachHang.getColumnModel().getColumn(5).setCellRenderer(centerRederer);
        
        
        JScrollPane scrKhachHang = new JScrollPane(tblKhachHang);  
        pnlTable.add(scrKhachHang, BorderLayout.CENTER);
        cons.gridx = 0;
        cons.gridy = 4;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.fill = GridBagConstraints.BOTH;
        cons.anchor = GridBagConstraints.CENTER;
        pnlKhachHang.add(pnlTable, cons);
        
        ArrayList<Component> order = new ArrayList<>();
        order.add(txtHoKH);
        order.add(txtTenKH);
        order.add(txtSoDienThoai);
        order.add(cmbGioiTinh);
        order.add(btnThem);
        order.add(btnSua);
        order.add(btnXoa);
        order.add(txtTim);
        order.add(txtMinChiTieu);
        order.add(txtMaxChiTieu);
        order.add(btnTimKiem);
        order.add(btnReset);
        
        LayoutFocusTraversalPolicy policy = new LayoutFocusTraversalPolicy() {
            @Override
            public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
                int idx = (order.indexOf(aComponent) + 1) % order.size();
                return order.get(idx);
            }
            
            @Override
            public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
                int idx = order.indexOf(aComponent) - 1;
                if (idx < 0) {
                    idx = order.size() - 1;
                }
                return order.get(idx);
            }
        };
        pnlKhachHang.setFocusTraversalPolicy(policy);
        
                /*
        =========================================================================
                                    PANEL KHÁCH HÀNG BỊ XÓA
        =========================================================================
         */
        JPanel pnlKhachHangBX = new JPanel(new GridBagLayout());
        
        lblTitleKhachHangBX = new JLabel("QUẢN LÝ KHÁCH HÀNG BỊ XÓA");
        font = new Font("Arial", Font.BOLD, 26);
        lblTitleKhachHangBX.setFont(font);
        
        lblMaKHBX = new JLabel("Mã Khách hàng");
        lblHoKHBX = new JLabel("Họ đệm");
        lblTenKHBX = new JLabel("Tên");
        lblGioiTinhBX = new JLabel("Giới tính");
        lblSoDienThoaiBX = new JLabel("Số điện thoại");
        lblTongChiTieuBX = new JLabel("Tổng chi tiêu");
        lblTimBX = new JLabel("Từ khóa  tìm kiếm");
        lblTimChiTieuBX = new JLabel("Chi tiêu từ ");
        lblDenBX = new JLabel("đến:");
        
        txtMaKHBX = new JTextField(20);
        txtMaKHBX.setEditable(false);
        txtHoKHBX = new JTextField(20);
        txtTenKHBX = new JTextField(20);
        txtSoDienThoaiBX = new JTextField(20);
        txtTongChiTieuBX = new JTextField(20);
        txtTongChiTieuBX.setEditable(false);
        txtTimBX = new JTextField(20);
        txtMinChiTieuBX = new JTextField(15);
        txtMaxChiTieuBX = new JTextField(15);
        
        txtTimBX.setForeground(Color.GRAY);
        txtTimBX.setText(placeholderTimKiem);
        
        btnResetBX = new JButton("Reset");
        btnKhoiPhuc = new JButton("Khôi phục");
        btnSuaBX = new JButton("Sửa");
        btnXoaVinhVien = new JButton("Xóa vĩnh viễn");
        btnTimKiemBX = new JButton("Tìm kiếm");
        
        cmbGioiTinhBX = new JComboBox<>();
        cmbGioiTinhBX.addItem("Chọn giới tính");
        cmbGioiTinhBX.addItem("Nam");
        cmbGioiTinhBX.addItem("Nữ");

        
        
        // Panel Tìm kiếm theo chi tiêu
        JPanel pnlTimKiemTheoChiTieuBX = new JPanel(new GridBagLayout());
        consBX.gridx = 0;
        consBX.gridy = 0;
        consBX.insets = new Insets(5, 5, 5, 5);
        pnlTimKiemTheoChiTieuBX.add(lblTimChiTieuBX, consBX);
        
        consBX.gridx = 1;
        consBX.gridy = 0;
        consBX.insets = new Insets(5, 5, 5, 5);
        pnlTimKiemTheoChiTieuBX.add(txtMinChiTieuBX, consBX);
        
        consBX.gridx = 2;
        consBX.gridy = 0;
        pnlTimKiemTheoChiTieuBX.add(lblDenBX, consBX);
        
        consBX.gridx = 3;
        consBX.gridy = 0;
        pnlTimKiemTheoChiTieuBX.add(txtMaxChiTieuBX, consBX);
        
        consBX.gridx = 4;
        consBX.gridy = 0;
        pnlTimKiemTheoChiTieuBX.add(btnTimKiemBX, consBX);
        
//        Panel Title
        JPanel pnlTitleKhachHangBX = new JPanel(new GridBagLayout());
        consBX.gridx = 0;
        consBX.gridy = 0;
        consBX.gridwidth = 1;
        consBX.insets = new Insets(10, 10, 10, 10);
        consBX.anchor = GridBagConstraints.CENTER;
        pnlTitleKhachHangBX.add(lblTitleKhachHangBX, consBX);
        
        consBX.gridx = 1;
        consBX.gridy = 0;
        consBX.gridwidth = 1;
        consBX.anchor = GridBagConstraints.WEST;
        pnlTitleKhachHangBX.add(btnResetBX, consBX);
        
//        Thêm pnlTitleKhachHangBX vào PanelKhachHangBX
        consBX.gridx = 0;
        consBX.gridy = 0;
        consBX.anchor = GridBagConstraints.PAGE_START;
        consBX.fill = GridBagConstraints.BOTH;
        consBX.gridwidth = 1;
        pnlKhachHangBX.add(pnlTitleKhachHangBX, consBX);
        
       

//        Panel Thông tin Khách hàng
        JPanel pnlTTKHBX = new JPanel(new GridBagLayout());
        
        consBX.gridx = 0;
        consBX.gridy = 0;
        consBX.gridwidth = 1;
        consBX.insets = new Insets(10, 10, 10, 10);
        consBX.anchor = GridBagConstraints.WEST;
        pnlTTKHBX.add(lblMaKHBX, consBX);
        
        consBX.gridx = 1;
        consBX.gridy = 0;
        consBX.gridwidth = 1;
        consBX.fill = GridBagConstraints.BOTH;
        pnlTTKHBX.add(txtMaKHBX, consBX);
        
        consBX.gridx = 0;
        consBX.gridy = 1;
        consBX.gridwidth = 1;
        pnlTTKHBX.add(lblHoKHBX, consBX);
        
        consBX.gridx = 1;
        consBX.gridy = 1;
        consBX.gridwidth = 1;
        consBX.fill = GridBagConstraints.BOTH;
        pnlTTKHBX.add(txtHoKHBX, consBX);
        
        consBX.gridx = 0;
        consBX.gridy = 2;
        consBX.gridwidth = 1;
        pnlTTKHBX.add(lblTenKHBX, consBX);
        
        consBX.gridx = 1;
        consBX.gridy = 2;
        consBX.gridwidth = 1;
        consBX.fill = GridBagConstraints.BOTH;
        pnlTTKHBX.add(txtTenKHBX, consBX);
        
        consBX.gridx = 0;
        consBX.gridy = 3;
        consBX.gridwidth = 1;
        pnlTTKHBX.add(lblSoDienThoaiBX, consBX);
        
        consBX.gridx = 1;
        consBX.gridy = 3;
        consBX.gridwidth = 1;
        consBX.fill = GridBagConstraints.BOTH;
        pnlTTKHBX.add(txtSoDienThoaiBX, consBX);
        
        consBX.gridx = 0;
        consBX.gridy = 4;
        consBX.gridwidth = 1;
        pnlTTKHBX.add(lblGioiTinhBX, consBX);
        
        consBX.gridx = 1;
        consBX.gridy = 4;
        consBX.gridwidth = 1;
        consBX.fill = GridBagConstraints.BOTH;
        pnlTTKHBX.add(cmbGioiTinhBX, consBX);
        
        consBX.gridx = 0;
        consBX.gridy = 5;
        consBX.gridwidth = 1;
        pnlTTKHBX.add(lblTongChiTieuBX, consBX);
        
        consBX.gridx = 1;
        consBX.gridy = 5;
        consBX.gridwidth = 1;
        consBX.fill = GridBagConstraints.BOTH;
        pnlTTKHBX.add(txtTongChiTieuBX, consBX);
        
//        Panel Tim kiem
        JPanel pnlTimKiemBX = new JPanel(new GridBagLayout());
        
        consBX.gridx = 0;
        consBX.gridy = 0;
        consBX.gridwidth = 1;
        pnlTimKiemBX.add(lblTimBX, consBX);
        
        consBX.gridx = 1;
        consBX.gridy = 0;
        consBX.gridwidth = 1;
        pnlTimKiemBX.add(txtTimBX, consBX);
        
        
//        Panel Button
        JPanel pnlBtnBX = new JPanel(new GridBagLayout());
        
        consBX.gridx = 0;
        consBX.gridy = 0;
        consBX.insets = new Insets(10, 10, 10, 10);
        consBX.fill = GridBagConstraints.BOTH;
        consBX.gridwidth = 1;
        pnlBtnBX.add(btnKhoiPhuc, consBX);
        
        consBX.gridx = 1;
        consBX.gridy = 0;
        consBX.gridwidth = 1;
        pnlBtnBX.add(btnSuaBX, consBX);
        
        consBX.gridx = 2;
        consBX.gridy = 0;
        consBX.gridwidth = 1;
        pnlBtnBX.add(btnXoaVinhVien, consBX);

//        add pnl Button vào pnl hiển thị
        
        consBX.gridx = 0;
        consBX.gridy = 6;
        consBX.gridwidth = 2;
        consBX.anchor = GridBagConstraints.CENTER;
        pnlTTKHBX.add(pnlBtnBX, consBX);
        
//        Thêm pnl TTKHBX vào pnlKhachHangBX
        consBX.gridx = 0;
        consBX.gridy = 1;
        consBX.gridwidth = 1;
        consBX.gridheight = 1;
        consBX.fill = GridBagConstraints.BOTH;
        pnlKhachHangBX.add(pnlTTKHBX, consBX);
            
//        Them pnl Tim Kiem vao pnlKhachHangBX
        consBX.gridx = 0;
        consBX.gridy = 2;
        consBX.gridwidth = 1;
        consBX.gridheight = 1;
        consBX.fill = GridBagConstraints.BOTH;
        consBX.anchor = GridBagConstraints.CENTER;
        pnlKhachHangBX.add(pnlTimKiemBX, consBX);

//        Thêm Pnl Tìm kiếm theo chi tiêu vào pnlKhachHang
        consBX.gridx = 0;
        consBX.gridy = 3;
        consBX.gridwidth = 1;
        consBX.gridheight = 1;
        consBX.fill = GridBagConstraints.BOTH;
        consBX.anchor = GridBagConstraints.CENTER;
        pnlKhachHangBX.add(pnlTimKiemTheoChiTieuBX, consBX);
        
        JPanel pnlTableBX = new JPanel(new BorderLayout());
        
        modelKhachHangBX = new DefaultTableModel();
        
        modelKhachHangBX.addColumn("Mã KH");
        modelKhachHangBX.addColumn("Họ đệm");
        modelKhachHangBX.addColumn("Tên");
        modelKhachHangBX.addColumn("Giới tính");
        modelKhachHangBX.addColumn("Số điện thoại");
        modelKhachHangBX.addColumn("Tổng chi tiêu");
        
        tblKhachHangBX = new JTable(modelKhachHangBX);
        
        tblKhachHangBX.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblKhachHangBX.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblKhachHangBX.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblKhachHangBX.getColumnModel().getColumn(3).setCellRenderer(centerRederer);
        tblKhachHangBX.getColumnModel().getColumn(4).setCellRenderer(centerRederer);
        tblKhachHangBX.getColumnModel().getColumn(5).setCellRenderer(centerRederer);
        
        
        JScrollPane scrKhachHangBX = new JScrollPane(tblKhachHangBX);  
        pnlTableBX.add(scrKhachHangBX, BorderLayout.CENTER);
        consBX.gridx = 0;
        consBX.gridy = 4;
        consBX.gridwidth = 1;
        consBX.gridheight = 1;
        consBX.weightx = 1.0;
        consBX.weighty = 1.0;
        consBX.fill = GridBagConstraints.BOTH;
        consBX.anchor = GridBagConstraints.CENTER;
        pnlKhachHangBX.add(pnlTableBX, consBX);
        
        ArrayList<Component> orderBX = new ArrayList<>();
        orderBX.add(txtHoKHBX);
        orderBX.add(txtTenKHBX);
        orderBX.add(txtSoDienThoaiBX);
        orderBX.add(cmbGioiTinhBX);
        orderBX.add(btnKhoiPhuc);
        orderBX.add(btnSuaBX);
        orderBX.add(btnXoaVinhVien);
        orderBX.add(txtTimBX);
        orderBX.add(txtMinChiTieuBX);
        orderBX.add(txtMaxChiTieuBX);
        orderBX.add(btnTimKiemBX);
        orderBX.add(btnResetBX);
        
        LayoutFocusTraversalPolicy policyBX = new LayoutFocusTraversalPolicy() {
            @Override
            public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
                int idx = (orderBX.indexOf(aComponent) + 1) % orderBX.size();
                return orderBX.get(idx);
            }
            
            @Override
            public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
                int idx = orderBX.indexOf(aComponent) - 1;
                if (idx < 0) {
                    idx = orderBX.size() - 1;
                }
                return orderBX.get(idx);
            }
        };
        pnlKhachHangBX.setFocusTraversalPolicy(policyBX);
        
        
        pnlCardTabKhachHang = new JPanel(cardKhachHangGroup);
        pnlCardTabKhachHang.add(pnlKhachHang, "1");
        pnlCardTabKhachHang.add(pnlKhachHangBX, "2");
   
        this.add(pnlCardTabKhachHang);
        
        loadDataLenBangKhachHang();
        loadDataLenBangKhachHangBX();
        
        setSize(1000, 700);
        setVisible(true);
    }
    
    private void addEventsKhachHang() {
        lblTabbedKhachHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedKhachHang.setIcon(tabbedSelected);
                lblTabbedKhachHangBiXoa.setIcon(tabbedDefault);
                cardKhachHangGroup.show(pnlCardTabKhachHang, "1");
                loadDataLenBangKhachHang();
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

        lblTabbedKhachHangBiXoa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedKhachHangBiXoa.setIcon(tabbedSelected);
                lblTabbedKhachHang.setIcon(tabbedDefault);
                cardKhachHangGroup.show(pnlCardTabKhachHang, "2");
                loadDataLenBangKhachHangBX();
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
        
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenBangKhachHang();
                xuLyReset();
            }
        });
        
        btnReset.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    loadDataLenBangKhachHang();
                    xuLyReset();
                }
            }
        });
        
        btnThem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyThemKhachHang();
                }
            }
        });
        
        btnSua.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLySuaKhachHang();
                }
            }
        });
        
        btnXoa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyXoaKhachHang();
                }
            }
        });
        
        btnTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyTimKiemTheoKhoang();
                }
            }
        });
        
        tblKhachHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyChonKhachHang();
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
        
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKhachHang();
            }
        });
        
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaKhachHang();
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaKhachHang();
            }
        });

        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               xuLyTimKiemTheoKhoang();
            }
        });
   
        txtTim.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(txtTim.getText().equals(placeholderTimKiem)) {
                    txtTim.setText("");
                    txtTim.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                    if(txtTim.getText().trim().equals("")) {
                        txtTim.setText(placeholderTimKiem);
                        txtTim.setForeground(Color.GRAY);
                    }
            }
        });
        
        txtTim.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }
        });
    }
     
    private void addEventsKhachHangBiXoa() {
        btnResetBX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenBangKhachHangBX();
                xuLyResetBX();
            }
        });
        
        btnResetBX.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    loadDataLenBangKhachHangBX();
                    xuLyResetBX();
                }
            }
        });

        btnKhoiPhuc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyKhoiPhucKhachHang();
                }
            }
        });
        
        btnKhoiPhuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyKhoiPhucKhachHang();
            }
        });
        
        btnSuaBX.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLySuaKhachHangBX();
                }
            }
        });
        
        btnSuaBX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaKhachHangBX();
            }
        });
        
        btnXoaVinhVien.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyXoaVinhVienKhachHang();
                }
            }
        });
        
        btnXoaVinhVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaVinhVienKhachHang();
            }
        });
        
        btnTimKiemBX.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyTimKiemTheoKhoangBX();
                }
            }
        });
        
        btnTimKiemBX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemTheoKhoangBX();
            }
        });
        
        tblKhachHangBX.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyChonKhachHangBX();
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

   
        txtTimBX.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(txtTimBX.getText().equals(placeholderTimKiem)) {
                    txtTimBX.setText("");
                    txtTimBX.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                    if(txtTimBX.getText().trim().equals("")) {
                        txtTimBX.setText(placeholderTimKiem);
                        txtTimBX.setForeground(Color.GRAY);
                    }
            }
        });
        
        txtTimBX.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                xuLyLiveSearchBX();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                xuLyLiveSearchBX();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                xuLyLiveSearchBX();
            }
        });
    }
    
     private void loadDataLenBangKhachHang() {
        khachHangBUS.docDanhSach();
        ArrayList<KhachHang> dskh = khachHangBUS.getListKhachHang();
        loadDataLenBangKhachHang(dskh);
    }

    private void loadDataLenBangKhachHang(ArrayList<KhachHang> dskh) {
        modelKhachHang.setRowCount(0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for (KhachHang kh : dskh) {
            Vector vec = new Vector();
            vec.add(kh.getMaKH());
            vec.add(kh.getHo());
            vec.add(kh.getTen());
            vec.add(kh.getGioiTinh());
            vec.add(kh.getSoDienThoai());
            vec.add(dcf.format(kh.getTongChiTieu()));
            modelKhachHang.addRow(vec);
        }
    }
    
    
     private void loadDataLenBangKhachHangBX() {
        khachHangBUS.docDanhSachBX();
        ArrayList<KhachHang> dskh = khachHangBUS.getListKhachHangBX();
        loadDataLenBangKhachHangBX(dskh);
    }

    private void loadDataLenBangKhachHangBX(ArrayList<KhachHang> dskh) {
        modelKhachHangBX.setRowCount(0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for (KhachHang kh : dskh) {
            Vector vec = new Vector();
            vec.add(kh.getMaKH());
            vec.add(kh.getHo());
            vec.add(kh.getTen());
            vec.add(kh.getGioiTinh());
            vec.add(kh.getSoDienThoai());
            vec.add(dcf.format(kh.getTongChiTieu()));
            modelKhachHangBX.addRow(vec);
        }
    }
    
    private void xuLyChonKhachHang() {
        int row = tblKhachHang.getSelectedRow();
        if(row > -1) {
            String ma = tblKhachHang.getValueAt(row, 0) + "";
            String ho = tblKhachHang.getValueAt(row, 1) + "";
            String ten = tblKhachHang.getValueAt(row, 2) + "";
            String gioiTinh = tblKhachHang.getValueAt(row, 3) + "";
            String sdt = tblKhachHang.getValueAt(row, 4) + "";
            String tongChiTieu = tblKhachHang.getValueAt(row, 5) + "";
            
            txtMaKH.setText(ma);
            txtHoKH.setText(ho);
            txtTenKH.setText(ten);
            int index = tblKhachHang.getValueAt(row, 3).equals("Nam") ? 1 : 2;
            cmbGioiTinh.setSelectedIndex(index);
            txtSoDienThoai.setText(sdt);
            txtTongChiTieu.setText(tongChiTieu);
        }
    }
    
    
    private void xuLyChonKhachHangBX() {
        int row = tblKhachHangBX.getSelectedRow();
        if(row > -1) {
            String ma = tblKhachHangBX.getValueAt(row, 0) + "";
            String ho = tblKhachHangBX.getValueAt(row, 1) + "";
            String ten = tblKhachHangBX.getValueAt(row, 2) + "";
            String gioiTinh = tblKhachHangBX.getValueAt(row, 3) + "";
            String sdt = tblKhachHangBX.getValueAt(row, 4) + "";
            String tongChiTieu = tblKhachHangBX.getValueAt(row, 5) + "";
            
            txtMaKHBX.setText(ma);
            txtHoKHBX.setText(ho);
            txtTenKHBX.setText(ten);
            int index = tblKhachHangBX.getValueAt(row, 3).equals("Nam") ? 1 : 2;
            cmbGioiTinhBX.setSelectedIndex(index);
            txtSoDienThoaiBX.setText(sdt);
            txtTongChiTieuBX.setText(tongChiTieu);
        }
    }
    
    public void xuLyReset() {
        txtMaKH.setText("");
        txtHoKH.setText("");
        txtTenKH.setText("");
        cmbGioiTinh.setSelectedIndex(0);
        txtSoDienThoai.setText("");
        txtTongChiTieu.setText("");
        txtTim.setText(placeholderTimKiem);
        txtTim.setForeground(Color.GRAY);
        txtMinChiTieu.setText("");
        txtMaxChiTieu.setText("");
    }
    
    
    public void xuLyResetBX() {
        txtMaKHBX.setText("");
        txtHoKHBX.setText("");
        txtTenKHBX.setText("");
        cmbGioiTinhBX.setSelectedIndex(0);
        txtSoDienThoaiBX.setText("");
        txtTongChiTieuBX.setText("");
        txtTimBX.setText(placeholderTimKiem);
        txtTimBX.setForeground(Color.GRAY);
        txtMinChiTieuBX.setText("");
        txtMaxChiTieuBX.setText("");
    }
    
    private void xuLyThemKhachHang() {
        String ho = txtHoKH.getText().trim();
        String ten = txtTenKH.getText().trim();
        String gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        String sdt = txtSoDienThoai.getText().trim();

        if(!kiemTra(ho, ten, gioiTinh, sdt)) { //!true khi có lỗi và !false khi mà không có lỗi
            if(khachHangBUS.kiemTraTrungKhachHang(ho, ten, gioiTinh, sdt)) {
                int xoa = JOptionPane.showConfirmDialog(rootPane, "Khách hàng này đã tồn tại, bạn có chắc chắn muốn thêm?", "Thông báo trùng khách hàng", JOptionPane.YES_NO_OPTION);
                if(xoa == JOptionPane.YES_OPTION) {
                    boolean flag = khachHangBUS.themKhachHang(ho, ten, gioiTinh, sdt);
                    if(flag) {
                        JOptionPane.showMessageDialog(rootPane, "Thêm khách hàng thành công");
                        khachHangBUS.docDanhSach();
                        loadDataLenBangKhachHang();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Thêm khách hàng thất bại");
                    }
                }
            } else {
                boolean flag = khachHangBUS.themKhachHang(ho, ten, gioiTinh, sdt);
                if(flag) {
                    JOptionPane.showMessageDialog(rootPane, "Thêm khách hàng thành công");
                    khachHangBUS.docDanhSach();
                    loadDataLenBangKhachHang();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Thêm khách hàng thất bại");
                }
            }
        }
        
    }
    
    private void xuLySuaKhachHang() {
        String ma = txtMaKH.getText().trim();
        String ho = txtHoKH.getText().trim();
        String ten = txtTenKH.getText().trim();
        String gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        String sdt = txtSoDienThoai.getText().trim();
        
        if(ma.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn khách hàng");
        } else if(!kiemTra(ho, ten, gioiTinh, sdt)) {
            if(khachHangBUS.kiemTraTrungKhachHang(ho, ten, gioiTinh, sdt)) {
                int sua = JOptionPane.showConfirmDialog(rootPane, "Thông tin khách hàng này đã tồn tại, bạn có chắc chắn muốn sửa?", "Thông báo trùng thông tin khách hàng khác", JOptionPane.YES_NO_OPTION);
                if(sua == JOptionPane.YES_OPTION) {
                    boolean flag = khachHangBUS.suaKhachHang(ma, ho, ten, gioiTinh, sdt);
                    if(flag) {
                        JOptionPane.showMessageDialog(rootPane, "Sửa khách hàng thành công");
                        khachHangBUS.docDanhSach();
                        loadDataLenBangKhachHang();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Sửa khách hàng thất bại");
                    }
                }
            } else {
                boolean flag = khachHangBUS.suaKhachHang(ma, ho, ten, gioiTinh, sdt);
                if(flag) {
                    JOptionPane.showMessageDialog(rootPane, "Sửa khách hàng thành công");
                    khachHangBUS.docDanhSach();
                    loadDataLenBangKhachHang();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Sửa khách hàng thất bại");
                }
            }
        }
    }
    
    private void xuLySuaKhachHangBX() {
        String ma = txtMaKHBX.getText().trim();
        String ho = txtHoKHBX.getText().trim();
        String ten = txtTenKHBX.getText().trim();
        String gioiTinh = cmbGioiTinhBX.getSelectedItem() + "";
        String sdt = txtSoDienThoaiBX.getText().trim();
        
        if(ma.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn khách hàng");
        } else if(!kiemTraBX(ho, ten, gioiTinh, sdt)) {
            if(khachHangBUS.kiemTraTrungKhachHang(ho, ten, gioiTinh, sdt)) {
                int sua = JOptionPane.showConfirmDialog(rootPane, "Thông tin khách hàng này đã tồn tại, bạn có chắc chắn muốn sửa?", "Thông báo trùng thông tin khách hàng khác", JOptionPane.YES_NO_OPTION);
                if(sua == JOptionPane.YES_OPTION) {
                    boolean flag = khachHangBUS.suaKhachHang(ma, ho, ten, gioiTinh, sdt);
                    if(flag) {
                        JOptionPane.showMessageDialog(rootPane, "Sửa khách hàng thành công");
                        khachHangBUS.docDanhSachBX();
                        loadDataLenBangKhachHangBX();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Sửa khách hàng thất bại");
                    }
                }
            } else {
                boolean flag = khachHangBUS.suaKhachHang(ma, ho, ten, gioiTinh, sdt);
                if(flag) {
                    JOptionPane.showMessageDialog(rootPane, "Sửa khách hàng thành công");
                    khachHangBUS.docDanhSachBX();
                    loadDataLenBangKhachHangBX();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Sửa khách hàng thất bại");
                }
            }
        }
    }
    
    private void xuLyXoaKhachHang() {
        String ma = txtMaKH.getText().trim();
        String ho = txtHoKH.getText().trim();
        String ten = txtTenKH.getText().trim();
        String gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        String sdt = txtSoDienThoai.getText().trim();
        
        if(ma.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn khách hàng");
        } else if(!kiemTra(ho, ten, gioiTinh, sdt)) {
            int flagXoa = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if(flagXoa == JOptionPane.YES_OPTION) {
                boolean flag = khachHangBUS.xoaKhachHang(ma);
                if(flag) {
                    JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
                    xuLyReset();
                    khachHangBUS.docDanhSach();
                    loadDataLenBangKhachHang();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Xóa thất bại");
                }
            }
        }
    }
    
    private void xuLyXoaVinhVienKhachHang() {
        String ma = txtMaKHBX.getText().trim();
        String ho = txtHoKHBX.getText().trim();
        String ten = txtTenKHBX.getText().trim();
        String gioiTinh = cmbGioiTinhBX.getSelectedItem() + "";
        String sdt = txtSoDienThoaiBX.getText().trim();
        
        if(ma.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn khách hàng");
        } else if(!kiemTra(ho, ten, gioiTinh, sdt)) {
            int flagXoa = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa vĩnh viễn khách hàng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if(flagXoa == JOptionPane.YES_OPTION) {
                boolean flag = khachHangBUS.xoaKhachHang(ma);
                if(flag) {
                    JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
                    xuLyReset();
                    khachHangBUS.docDanhSachBX();
                    loadDataLenBangKhachHangBX();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Xóa thất bại");
                }
            }
        }
    }
    
    private void xuLyKhoiPhucKhachHang() {
        String ma = txtMaKHBX.getText().trim();
        String ho = txtHoKHBX.getText().trim();
        String ten = txtTenKHBX.getText().trim();
        String gioiTinh = cmbGioiTinhBX.getSelectedItem() + "";
        String sdt = txtSoDienThoaiBX.getText().trim();
        
        if(ma.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn khách hàng");
        } else if(!kiemTraBX(ho, ten, gioiTinh, sdt)) {
            int flagXoa = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn khôi phục?", "Xác nhận khôi phục", JOptionPane.YES_NO_OPTION);
            if(flagXoa == JOptionPane.YES_OPTION) {
                boolean flag = khachHangBUS.khoiPhucKhachHang(ma);
                if(flag) {
                    JOptionPane.showMessageDialog(rootPane, "Khôi phục thành công");
                    xuLyReset();
                    khachHangBUS.docDanhSachBX();
                    loadDataLenBangKhachHangBX();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Khôi phục thất bại");
                }
            }
        }
    }
    
    private void xuLyTimKiemTheoKhoang() {
        String minChiTieu = txtMinChiTieu.getText().trim();
        String maxChiTieu = txtMaxChiTieu.getText().trim();  
        ArrayList<KhachHang> dskh = new ArrayList<>();
        if(minChiTieu.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống chi tiêu nhỏ nhất");
            txtMinChiTieu.requestFocus();
        } else if (maxChiTieu.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống chi tiêu lớn nhất");
            txtMaxChiTieu.requestFocus();
        } else {
            try {
                minChiTieu = minChiTieu.replace(",", "");
                maxChiTieu = maxChiTieu.replace(",", "");
                int min = Integer.parseInt(minChiTieu);
                int max = Integer.parseInt(maxChiTieu);

                if(min <= max) {
                    dskh = khachHangBUS.timKiemKhachHang(min, max);    
                    if(dskh.size() > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Số kết quả tìm được là " + dskh.size());
                        loadDataLenBangKhachHang(dskh);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy khách hàng");
                        loadDataLenBangKhachHang();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Chi tiêu nhỏ nhất phải nhỏ hơn chi tiêu lớn nhất");
                    txtMinChiTieu.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập chi tiêu với định dạng số!");
                txtMinChiTieu.requestFocus();
                loadDataLenBangKhachHang();
            } 
        }
    }
    
    
    private void xuLyTimKiemTheoKhoangBX() {
        String minChiTieu = txtMinChiTieuBX.getText().trim();
        String maxChiTieu = txtMaxChiTieuBX.getText().trim();  
        ArrayList<KhachHang> dskh = new ArrayList<>();
        if(minChiTieu.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống chi tiêu nhỏ nhất");
            txtMinChiTieuBX.requestFocus();
        } else if (maxChiTieu.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống chi tiêu lớn nhất");
            txtMaxChiTieuBX.requestFocus();
        } else {
            try {
                minChiTieu = minChiTieu.replace(",", "");
                maxChiTieu = maxChiTieu.replace(",", "");
                int min = Integer.parseInt(minChiTieu);
                int max = Integer.parseInt(maxChiTieu);

                if(min <= max) {
                    dskh = khachHangBUS.timKiemKhachHang(min, max);    
                    if(dskh.size() > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Số kết quả tìm được là " + dskh.size());
                        loadDataLenBangKhachHangBX(dskh);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tìm thấy khách hàng");
                        loadDataLenBangKhachHangBX();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Chi tiêu nhỏ nhất phải nhỏ hơn chi tiêu lớn nhất");
                    txtMinChiTieuBX.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập chi tiêu với định dạng số!");
                txtMinChiTieuBX.requestFocus();
                loadDataLenBangKhachHangBX();
            } 
        }
    }
    
    private void xuLyLiveSearch() {
        String tuKhoa = txtTim.getText().toLowerCase().trim();
        ArrayList<KhachHang> dskh = khachHangBUS.timKiemKhachHang(tuKhoa);
        loadDataLenBangKhachHang(dskh);
        if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
            loadDataLenBangKhachHang();
    }
    
    private void xuLyLiveSearchBX() {
        String tuKhoa = txtTimBX.getText().toLowerCase().trim();
        ArrayList<KhachHang> dskh = khachHangBUS.timKiemKhachHang(tuKhoa);
        loadDataLenBangKhachHangBX(dskh);
        if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
            loadDataLenBangKhachHangBX();
    }
    
    private boolean kiemTra(String ho, String ten, String gioiTinh, String sdt) {
        if(ho.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập họ đệm khách hàng");
            txtHoKH.requestFocus();
            return true;
        } else if(ten.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập tên khách hàng");
            txtTenKH.requestFocus();
            return true;
        } else if(sdt.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số điện thoại");
            txtSoDienThoai.requestFocus();
            return true;
        } else if(!kiemTraSoDienThoai(sdt)) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại phải có độ dài 10 ký tự và bắt đầu bằng 03,09,...");
            txtSoDienThoai.requestFocus();
            return true;
        } else if (gioiTinh.equals("Chọn giới tính")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn giới tính");
            return true;
        } 
        return false;
    }
    
    private boolean kiemTraBX(String ho, String ten, String gioiTinh, String sdt) {
        if(ho.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập họ đệm khách hàng");
            txtHoKHBX.requestFocus();
            return true;
        } else if(ten.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập tên khách hàng");
            txtTenKHBX.requestFocus();
            return true;
        } else if(sdt.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số điện thoại");
            txtSoDienThoaiBX.requestFocus();
            return true;
        } else if(!kiemTraSoDienThoai(sdt)) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại phải có độ dài 10 ký tự và bắt đầu bằng 03,09,...");
            txtSoDienThoaiBX.requestFocus();
            return true;
        } else if (gioiTinh.equals("Chọn giới tính")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn giới tính");
            return true;
        } 
        return false;
    }
    
    private boolean kiemTraSoDienThoai(String sdt) {
        Pattern pattern = Pattern.compile("^0\\d{9}$");
        Matcher matcher = pattern.matcher(sdt);
        return matcher.matches();
    }
    
    public static void main(String[] args) {
        new MyConnect();
        new FrmQuanLyKhachHang();
    }
}