
package GUI;

import BUS.NhanVienBUS;
import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import DAO.MyConnect;
import DTO.NhanVien;
import DTO.PhanQuyen;
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
import Customs.XuLyFileExcel;
public final class PnlQuanLyNhanVien extends JPanel{
    private final NhanVienBUS NhanVienBUS = new NhanVienBUS();
    private final PhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
    private final TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
    private JLabel lblTabbedNhanVien, lblTabbedQuyen;
    final ImageIcon tabbedSelected = new ImageIcon("images/ManagerUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("images/ManagerUI/tabbed-btn.png");
    private JPanel pnlCardTabNhanVien;
    private final CardLayout cardNhanVienGroup = new CardLayout();
    private JLabel lblTitleNhanVien, lblMaNV, lblHoNV, lblTenNV, lblGioiTinh, lblChucVu, lblTim;
    private JTextField txtMaNV, txtHoNV, txtTenNV,txtChucVu, txtTim;
    private JButton btnReset, btnThem, btnSua, btnXoa, btnXuat, btnNhap, btnCapTK, btnMKQuyen, btnKhoaTK, btnMoKhoaTK;
    private JTable tblNhanVien;
    private DefaultTableModel modelNhanVien;
    private JComboBox<String> cmbGioiTinh;
    private JLabel lblTitleQuyen, lblNhomQuyen;
    private JButton btnResetQuyen, btnThemQuyen, btnSuaQuyen, btnXoaQuyen;
    private JComboBox<String> cmbQuyen;
    private JCheckBox ckbNhapHang, ckbQLSanPham, ckbQLNhanVien, ckbQLKhachHang, ckbThongKe, ckbKhuyenMai;

    private Font font,fontTabbed;
    private final String placeholderTimKiem = "Nhập từ Khóa mà bạn muốn tìm kiếm...";
    
    public PnlQuanLyNhanVien() {
       addControls();
       addEventsNhanVien();
       addEventsQuyen();
    }
    
    public void addControls() {
        setLayout(new BorderLayout());
        font = new Font("Arial", Font.BOLD, 26);

//        Pnl TOP
        JPanel pnlTop = new JPanel();
        pnlTop.setOpaque(false);
        pnlTop.setPreferredSize(new Dimension(1000, 41));
        pnlTop.setLayout(null);
        pnlTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));
        
        fontTabbed = new Font("Arial", Font.BOLD, 14);
        
        lblTabbedNhanVien = new JLabel("Nhân Viên");
        lblTabbedNhanVien.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedNhanVien.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedNhanVien.setIcon(tabbedSelected);
        lblTabbedNhanVien.setBounds(2, 2, 140, 37);
        lblTabbedNhanVien.setFont(fontTabbed);
        lblTabbedNhanVien.setForeground(Color.white);
        lblTabbedNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedQuyen = new JLabel("Quyền");
        lblTabbedQuyen.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedQuyen.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedQuyen.setIcon(tabbedDefault);
        lblTabbedQuyen.setBounds(143, 2, 140, 37);
        lblTabbedQuyen.setFont(fontTabbed);
        lblTabbedQuyen.setForeground(Color.white);
        lblTabbedQuyen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        pnlTop.add(lblTabbedNhanVien);
        pnlTop.add(lblTabbedQuyen);
        
        //        Thêm pnlTop vào Frame
        this.add(pnlTop, BorderLayout.NORTH);
        
        DefaultTableCellRenderer centerRederer = new DefaultTableCellRenderer();
        centerRederer.setHorizontalAlignment(JLabel.CENTER);
        
        /*
        =========================================================================
                                    PANEL NHÂN VIÊN
        =========================================================================
         */
        
        JPanel pnlNhanVien = new JPanel(new GridBagLayout());
        GridBagConstraints consNV = new GridBagConstraints();

        lblTitleNhanVien = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTitleNhanVien.setFont(font);
        
        lblMaNV = new JLabel("Mã nhân viên");
        lblHoNV = new JLabel("Họ đệm");
        lblTenNV = new JLabel("Tên");
        lblGioiTinh = new JLabel("Giới tính");
        lblChucVu = new JLabel("Chức vụ");
        lblTim = new JLabel("Từ khóa  tìm kiếm");
        
        txtMaNV = new JTextField(20);
        txtMaNV.setEditable(false);
        txtHoNV = new JTextField(20);
        txtTenNV = new JTextField(20);
        txtChucVu = new JTextField(20);
        txtTim = new JTextField(20);
        
        txtTim.setForeground(Color.GRAY);
        txtTim.setText(placeholderTimKiem);
        
        btnReset = new JButton("Reset");
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnXuat = new JButton("Xuất");
        btnNhap = new JButton("Nhập");
        btnCapTK = new JButton("Cấp tài khoản");
        btnMKQuyen = new JButton("MK/Quyền");
        btnKhoaTK = new JButton("Khóa tài khoản");
        btnMoKhoaTK = new JButton("Mở khóa tài khoản");
        
        cmbGioiTinh = new JComboBox<>();
        cmbGioiTinh.addItem("Chọn giới tính");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");
        
//        Panel Title Nhân viên 
        JPanel pnlTitleNhanVien = new JPanel(new GridBagLayout());
        GridBagConstraints consTitleNV = new GridBagConstraints();

        consTitleNV.gridx = 0;
        consTitleNV.gridy = 0;
        consTitleNV.gridwidth = 1;
        consTitleNV.insets = new Insets(10, 10, 10, 10);
        consTitleNV.anchor = GridBagConstraints.CENTER;
        pnlTitleNhanVien.add(lblTitleNhanVien, consTitleNV);
        
        consTitleNV.gridx = 1;
        consTitleNV.gridy = 0;
        consTitleNV.gridwidth = 1;
        consTitleNV.anchor = GridBagConstraints.WEST;
        pnlTitleNhanVien.add(btnReset, consTitleNV);
        
//        Thêm pnlTitleNhanVien vào PanelNhanVien
        consNV.gridx = 0;
        consNV.gridy = 0;
        consNV.anchor = GridBagConstraints.PAGE_START;
        consNV.fill = GridBagConstraints.BOTH;
        consNV.gridwidth = 1;
        pnlNhanVien.add(pnlTitleNhanVien, consNV);
        
       

//        Panel Thông tin Nhân viên
        JPanel pnlTTNV = new JPanel(new GridBagLayout());
        GridBagConstraints consTTNV = new GridBagConstraints();

        consTTNV.gridx = 0;
        consTTNV.gridy = 0;
        consTTNV.gridwidth = 1;
        consTTNV.insets = new Insets(10, 10, 10, 10);
        consTTNV.anchor = GridBagConstraints.WEST;
        pnlTTNV.add(lblMaNV, consTTNV);
        
        consTTNV.gridx = 1;
        consTTNV.gridy = 0;
        consTTNV.gridwidth = 1;
        consTTNV.fill = GridBagConstraints.BOTH;
        pnlTTNV.add(txtMaNV, consTTNV);
        
        consTTNV.gridx = 0;
        consTTNV.gridy = 1;
        consTTNV.gridwidth = 1;
        pnlTTNV.add(lblHoNV, consTTNV);
        
        consTTNV.gridx = 1;
        consTTNV.gridy = 1;
        consTTNV.gridwidth = 1;
        consTTNV.fill = GridBagConstraints.BOTH;
        pnlTTNV.add(txtHoNV, consTTNV);
        
        consTTNV.gridx = 0;
        consTTNV.gridy = 2;
        consTTNV.gridwidth = 1;
        pnlTTNV.add(lblTenNV, consTTNV);
        
        consTTNV.gridx = 1;
        consTTNV.gridy = 2;
        consTTNV.gridwidth = 1;
        consTTNV.fill = GridBagConstraints.BOTH;
        pnlTTNV.add(txtTenNV, consTTNV);
        
        consTTNV.gridx = 0;
        consTTNV.gridy = 3;
        consTTNV.gridwidth = 1;
        pnlTTNV.add(lblGioiTinh, consTTNV);
        
        consTTNV.gridx = 1;
        consTTNV.gridy = 3;
        consTTNV.gridwidth = 1;
        consTTNV.fill = GridBagConstraints.BOTH;
        pnlTTNV.add(cmbGioiTinh, consTTNV);
        
        consTTNV.gridx = 0;
        consTTNV.gridy = 4;
        consTTNV.gridwidth = 1;
        pnlTTNV.add(lblChucVu, consTTNV);
        
        consTTNV.gridx = 1;
        consTTNV.gridy = 4;
        consTTNV.gridwidth = 1;
        consTTNV.fill = GridBagConstraints.BOTH;
        pnlTTNV.add(txtChucVu, consTTNV);
        
//        Panel Tim kiem
        JPanel pnlTimKiem = new JPanel(new GridBagLayout());
        GridBagConstraints consTK = new GridBagConstraints();
        
        consTK.gridx = 0;
        consTK.gridy = 0;
        consTK.gridwidth = 1;
        consTK.insets = new Insets(10, 0, 10, 0);
        pnlTimKiem.add(lblTim, consTK);
        
        consTK.gridx = 1;
        consTK.gridy = 0;
        consTK.gridwidth = 1;
        pnlTimKiem.add(txtTim, consTK);
        
        
//        Panel Button
        JPanel pnlBtn = new JPanel(new GridBagLayout());
        GridBagConstraints consBtn = new GridBagConstraints();
        
        consBtn.gridx = 0;
        consBtn.gridy = 0;
        consBtn.insets = new Insets(10, 10, 10, 10);
        consBtn.fill = GridBagConstraints.BOTH;
        consBtn.gridwidth = 1;
        pnlBtn.add(btnThem, consBtn);
        
        consBtn.gridx = 1;
        consBtn.gridy = 0;
        consBtn.gridwidth = 1;
        pnlBtn.add(btnSua, consBtn);
        
        consBtn.gridx = 2;
        consBtn.gridy = 0;
        consBtn.gridwidth = 1;
        pnlBtn.add(btnXoa, consBtn);
        
        consBtn.gridx = 3;
        consBtn.gridy = 0;
        consBtn.gridwidth = 1;
        pnlBtn.add(btnXuat, consBtn);
        
        consBtn.gridx = 4;
        consBtn.gridy = 0;
        consBtn.gridwidth = 1;
        pnlBtn.add(btnNhap, consBtn);
        
//        Panel Button TK
        JPanel pnlBtnTK = new JPanel(new GridBagLayout());
        GridBagConstraints consBtnTK = new GridBagConstraints();
        
        consBtnTK.gridx = 0;
        consBtnTK.gridy = 0;
        consBtnTK.insets = new Insets(10, 10, 10, 10);
        consBtnTK.fill = GridBagConstraints.BOTH;
        consBtnTK.gridwidth = 1;
        pnlBtnTK.add(btnCapTK, consBtnTK);
        
        consBtnTK.gridx = 1;
        consBtnTK.gridy = 0;
        consBtnTK.gridwidth = 1;
        pnlBtnTK.add(btnMKQuyen, consBtnTK);
        
        consBtnTK.gridx = 2;
        consBtnTK.gridy = 0;
        consBtnTK.gridwidth = 1;
        pnlBtnTK.add(btnKhoaTK, consBtnTK);
        
        consBtnTK.gridx = 3;
        consBtnTK.gridy = 0;
        consBtnTK.gridwidth = 1;
        pnlBtnTK.add(btnMoKhoaTK, consBtnTK);

//        add pnl Button vào pnl TTNV
        
        consTTNV.gridx = 0;
        consTTNV.gridy = 5;
        consTTNV.gridwidth = 2;
        consTTNV.anchor = GridBagConstraints.CENTER;
        pnlTTNV.add(pnlBtn, consTTNV);
        

//        add pnl Button TK vào pnl TTNV
        
        consTTNV.gridx = 0;
        consTTNV.gridy = 6;
        consTTNV.gridwidth = 2;
        consTTNV.anchor = GridBagConstraints.CENTER;
        pnlTTNV.add(pnlBtnTK, consTTNV);
        
//        Thêm pnl TTNV vào pnlNhanVien
        consNV.gridx = 0;
        consNV.gridy = 1;
        consNV.gridwidth = 1;
        consNV.gridheight = 1;
        consNV.fill = GridBagConstraints.BOTH;
        pnlNhanVien.add(pnlTTNV, consNV);
            
//        Them pnl Tim Kiem vao pnlNhanVien
        consNV.gridx = 0;
        consNV.gridy = 2;
        consNV.gridwidth = 1;
        consNV.gridheight = 1;
        consNV.fill = GridBagConstraints.BOTH;
        consNV.anchor = GridBagConstraints.CENTER;
        pnlNhanVien.add(pnlTimKiem, consNV);
        
//        Table hiển thị danh sách khách hàng
        JPanel pnlTable = new JPanel(new BorderLayout());
        
        modelNhanVien = new DefaultTableModel();
        
        modelNhanVien.addColumn("Mã NV");
        modelNhanVien.addColumn("Họ đệm");
        modelNhanVien.addColumn("Tên");
        modelNhanVien.addColumn("Giới tính");
        modelNhanVien.addColumn("Chức vụ");
        modelNhanVien.addColumn("Tài khoản");
        
        tblNhanVien = new JTable(modelNhanVien);
        
        tblNhanVien.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblNhanVien.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblNhanVien.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRederer);
        tblNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRederer);
        tblNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRederer);
        
        
        JScrollPane scrNhanVien = new JScrollPane(tblNhanVien);  
        pnlTable.add(scrNhanVien, BorderLayout.CENTER);
        consNV.gridx = 0;
        consNV.gridy = 3;
        consNV.gridwidth = 1;
        consNV.gridheight = 1;
        consNV.weightx = 1.0;
        consNV.weighty = 1.0;
        consNV.fill = GridBagConstraints.BOTH;
        consNV.anchor = GridBagConstraints.CENTER;
        pnlNhanVien.add(pnlTable, consNV);
        
        ArrayList<Component> order = new ArrayList<>();
        order.add(txtHoNV);
        order.add(txtTenNV);
        order.add(cmbGioiTinh);
        order.add(btnThem);
        order.add(btnSua);
        order.add(btnXoa);
        order.add(btnXuat);
        order.add(btnNhap);
        order.add(btnCapTK);
        order.add(btnMKQuyen);
        order.add(btnKhoaTK);
        order.add(btnMoKhoaTK);
        order.add(txtTim);
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
        pnlNhanVien.setFocusTraversalPolicy(policy);
        
                /*
        =========================================================================
                                    PANEL QUYỀN
        =========================================================================
         */
        JPanel pnlQuyen = new JPanel(new GridBagLayout());
        GridBagConstraints consQuyen = new GridBagConstraints();
        
        lblTitleQuyen = new JLabel("QUẢN LÝ QUYỀN");
        font = new Font("Arial", Font.BOLD, 26);
        lblTitleQuyen.setFont(font);
        
        lblNhomQuyen = new JLabel("Nhóm Quyền:");
        
        btnResetQuyen = new JButton("Reset");
        btnThemQuyen = new JButton("Thêm quyền");
        btnSuaQuyen = new JButton("Sửa quyền");
        btnXoaQuyen = new JButton("Xóa quyền");
        
        cmbQuyen = new JComboBox<>();
        
        ckbNhapHang = new JCheckBox("Quản lý nhập hàng");
        ckbQLSanPham = new JCheckBox("Quản lý sản phẩm");
        ckbQLNhanVien = new JCheckBox("Quản lý nhân viên");
        ckbQLKhachHang = new JCheckBox("Quản lý khách hàng");
        ckbThongKe = new JCheckBox("Quản lý thống kê");
        ckbKhuyenMai = new JCheckBox("Quản lý khuyến mãi");
        
//        Panel Title Quyen
        JPanel pnlTitleQuyen = new JPanel(new GridBagLayout());
        GridBagConstraints consTitleQuyen = new GridBagConstraints();
        consTitleQuyen.gridx = 0;
        consTitleQuyen.gridy = 0;
        consTitleQuyen.gridwidth = 1;
        consTitleQuyen.insets = new Insets(10, 10, 10, 10);
        consTitleQuyen.anchor = GridBagConstraints.CENTER;
        pnlTitleQuyen.add(lblTitleQuyen, consTitleQuyen);
        
        consTitleQuyen.gridx = 1;
        consTitleQuyen.gridy = 0;
        consTitleQuyen.gridwidth = 1;
        consTitleQuyen.anchor = GridBagConstraints.WEST;
        pnlTitleQuyen.add(btnResetQuyen, consTitleQuyen);
        
//        Thêm pnlTitleQuyen vào PanelQuyen
        consQuyen.gridx = 0;
        consQuyen.gridy = 0;
        consQuyen.anchor = GridBagConstraints.PAGE_START;
        consQuyen.fill = GridBagConstraints.BOTH;
        consQuyen.gridwidth = 1;
        pnlQuyen.add(pnlTitleQuyen, consQuyen);
        
//       Pnl Nhóm Quyền
        JPanel pnlNhomQuyen = new JPanel();
        pnlNhomQuyen.add(lblNhomQuyen);
        pnlNhomQuyen.add(cmbQuyen);
        
//        Thêm pnl Nhóm quyền vào PanelQuyen
        consQuyen.gridx = 0;
        consQuyen.gridy = 1;
        consQuyen.anchor = GridBagConstraints.CENTER;
        consQuyen.fill = GridBagConstraints.BOTH;
        consQuyen.gridwidth = 1;
        pnlQuyen.add(pnlNhomQuyen, consQuyen);
        
//        Pnl Danh sách quyền
        JPanel pnlDSQuyen = new JPanel(new GridBagLayout());
        GridBagConstraints consDSQuyen = new GridBagConstraints();
        
        consDSQuyen.gridx = 0;
        consDSQuyen.gridy = 0;
        consDSQuyen.insets = new Insets(10, 10, 10, 10);
        consDSQuyen.fill = GridBagConstraints.BOTH;
        consDSQuyen.gridwidth = 1;
        pnlDSQuyen.add(ckbNhapHang, consDSQuyen);
        
        consDSQuyen.gridx = 0;
        consDSQuyen.gridy = 1;
        consDSQuyen.gridwidth = 1;
        pnlDSQuyen.add(ckbQLSanPham, consDSQuyen);
        
        consDSQuyen.gridx = 0;
        consDSQuyen.gridy = 2;
        consDSQuyen.gridwidth = 1;
        pnlDSQuyen.add(ckbQLNhanVien, consDSQuyen);
        
        consDSQuyen.gridx = 0;
        consDSQuyen.gridy = 3;
        consDSQuyen.gridwidth = 1;
        pnlDSQuyen.add(ckbQLKhachHang, consDSQuyen);
        
        consDSQuyen.gridx = 0;
        consDSQuyen.gridy = 4;
        consDSQuyen.gridwidth = 1;
        pnlDSQuyen.add(ckbThongKe, consDSQuyen);
        
        consDSQuyen.gridx = 0;
        consDSQuyen.gridy = 5;
        consDSQuyen.gridwidth = 1;
        pnlDSQuyen.add(ckbKhuyenMai, consDSQuyen);
        
        
//        Thêm pnl DS quyền vào PanelQuyen
        consQuyen.gridx = 0;
        consQuyen.gridy = 2;
        consQuyen.anchor = GridBagConstraints.CENTER;
        consQuyen.fill = GridBagConstraints.BOTH;
        consQuyen.gridwidth = 1;
        pnlQuyen.add(pnlDSQuyen, consQuyen);

        
//        Panel Button
        JPanel pnlBtnQuyen = new JPanel(new GridBagLayout());
        GridBagConstraints consBtnQuyen = new GridBagConstraints();
        
        consBtnQuyen.gridx = 0;
        consBtnQuyen.gridy = 0;
        consBtnQuyen.insets = new Insets(10, 10, 10, 10);
        consBtnQuyen.fill = GridBagConstraints.BOTH;
        consBtnQuyen.gridwidth = 1;
        pnlBtnQuyen.add(btnThemQuyen, consBtnQuyen);
        
        consBtnQuyen.gridx = 1;
        consBtnQuyen.gridy = 0;
        consBtnQuyen.gridwidth = 1;
        pnlBtnQuyen.add(btnSuaQuyen, consBtnQuyen);
        
        consBtnQuyen.gridx = 2;
        consBtnQuyen.gridy = 0;
        consBtnQuyen.gridwidth = 1;
        pnlBtnQuyen.add(btnXoaQuyen, consBtnQuyen);

//        add pnl Button vào pnl Quyền
        
        consQuyen.gridx = 0;
        consQuyen.gridy = 3;
        consQuyen.gridwidth = 2;
        consQuyen.anchor = GridBagConstraints.CENTER;
        pnlQuyen.add(pnlBtnQuyen, consQuyen);
 
        
        ArrayList<Component> orderQuyen = new ArrayList<>();
        orderQuyen.add(cmbQuyen);
        orderQuyen.add(ckbNhapHang);
        orderQuyen.add(ckbQLSanPham);
        orderQuyen.add(ckbQLNhanVien);
        orderQuyen.add(ckbQLKhachHang);
        orderQuyen.add(ckbThongKe);
        orderQuyen.add(ckbKhuyenMai);
        orderQuyen.add(btnThemQuyen);
        orderQuyen.add(btnSuaQuyen);
        orderQuyen.add(btnXoaQuyen);
        orderQuyen.add(btnResetQuyen);
        
        LayoutFocusTraversalPolicy policyQuyen = new LayoutFocusTraversalPolicy() {
            @Override
            public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
                int idx = (orderQuyen.indexOf(aComponent) + 1) % orderQuyen.size();
                return orderQuyen.get(idx);
            }
            
            @Override
            public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
                int idx = orderQuyen.indexOf(aComponent) - 1;
                if (idx < 0) {
                    idx = orderQuyen.size() - 1;
                }
                return orderQuyen.get(idx);
            }
        };
        pnlQuyen.setFocusTraversalPolicy(policyQuyen);
        
        
        pnlCardTabNhanVien = new JPanel(cardNhanVienGroup);
        pnlCardTabNhanVien.add(pnlNhanVien, "1");
        pnlCardTabNhanVien.add(pnlQuyen, "2");
   
        this.add(pnlCardTabNhanVien);
        
        loadDataLenBangNhanVien();
        loadDataLenCmbQuyen();
        
        setSize(1000, 700);
        setVisible(true);
    }
    
    private void addEventsNhanVien() {
        lblTabbedNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedNhanVien.setIcon(tabbedSelected);
                lblTabbedQuyen.setIcon(tabbedDefault);
                cardNhanVienGroup.show(pnlCardTabNhanVien, "1");
                loadDataLenBangNhanVien();
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

        lblTabbedQuyen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedQuyen.setIcon(tabbedSelected);
                lblTabbedNhanVien.setIcon(tabbedDefault);
                cardNhanVienGroup.show(pnlCardTabNhanVien, "2");
                loadDataLenCmbQuyen();
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
                loadDataLenBangNhanVien();
                xuLyReset();
            }
        });
        
        btnReset.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    loadDataLenBangNhanVien();
                    xuLyReset();
                }
            }
        });
        
        btnThem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyThemNhanVien();
                }
            }
        });
        
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemNhanVien();
            }
        });
        
        btnSua.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLySuaNhanVien();
                }
            }
        });
        
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaNhanVien();
            }
        });
        
        btnXoa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyXoaNhanVien();
                }
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaNhanVien();
            }
        });
        
        btnXuat.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyXuatExcel();
                }
            }
        });
        
        btnXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatExcel();
            }
        });
        
        btnNhap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyNhapExcel();
                }
            }
        });
        
        btnNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyNhapExcel();
            }
        });
        
        btnCapTK.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyCapTaiKhoan();
                }
            }
        });
        
        btnCapTK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyCapTaiKhoan();
            }
        });
        
        btnMKQuyen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyResetMatKhau();
                }
            }
        });
        
        btnMKQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyResetMatKhau();
            }
        });
        
        btnKhoaTK.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyKhoaTaiKhoan();
                }
            }
        });
        
        btnKhoaTK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyKhoaTaiKhoan();
            }
        });
        
        btnMoKhoaTK.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyMoKhoaTaiKhoan();
                }
            }
        });
        
        btnMoKhoaTK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyMoKhoaTaiKhoan();
            }
        });
        
        tblNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyChonNhanVien();
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
     
    private void addEventsQuyen() {
        btnResetQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenCmbQuyen();
                xuLyResetQuyen();
            }
        });
        
        btnResetQuyen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    loadDataLenCmbQuyen();
                    xuLyResetQuyen();
                }
            }
        });

        btnThemQuyen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyThemQuyen();
                }
            }
        });
        
        btnThemQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemQuyen();
            }
        });
        
        btnSuaQuyen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLySuaQuyen();
                }
            }
        });
        
        btnSuaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaQuyen();
            }
        });
        
        btnXoaQuyen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyXoaQuyen();
                }
            }
        });
        
        btnXoaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaQuyen();
            }
        });
        
        cmbQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyHienThiChiTietQuyen();
            }
        });
        
        cmbQuyen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyHienThiChiTietQuyen();
                }
            }
        });
    }
    
    //    Phương thức của pnlNhanVien
     private void loadDataLenBangNhanVien() {
         NhanVienBUS.docDanhSach();
        ArrayList<NhanVien> dsnv = NhanVienBUS.getDanhSachNhanVien();
         loadDataLenBangNhanVien(dsnv);
    }
 
     private void loadDataLenBangNhanVien(ArrayList<NhanVien> dsnv) {
        modelNhanVien.setRowCount(0);
         for (NhanVien nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getMaNV());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(nv.getGioiTinh());
            vec.add(nv.getChucVu());
            int trangThai = taiKhoanBUS.getTrangThai(nv.getMaNV() + "");
            if (trangThai == 0) {
                vec.add("Khoá");
            }
            else if(trangThai == 1) {
                vec.add("Hiệu lực");
            }
            else {
                vec.add("Chưa có");
            }
            modelNhanVien.addRow(vec);
        }
     }
     
    public void xuLyReset() {
        txtMaNV.setText("");
        txtHoNV.setText("");
        txtTenNV.setText("");
        txtChucVu.setText("");
        cmbGioiTinh.setSelectedIndex(0);
        txtTim.setText(placeholderTimKiem);
        txtTim.setForeground(Color.GRAY);
    }
    
    private void xuLyChonNhanVien() {
        int row = tblNhanVien.getSelectedRow();
        if(row > -1) {
            String ma = tblNhanVien.getValueAt(row, 0) + "";
            String ho = tblNhanVien.getValueAt(row, 1) + "";
            String ten = tblNhanVien.getValueAt(row, 2) + "";
            String gioiTinh = tblNhanVien.getValueAt(row, 3) + "";
            String cv = tblNhanVien.getValueAt(row, 4) + "";

            txtMaNV.setText(ma);
            txtHoNV.setText(ho);
            txtTenNV.setText(ten);
            int index = tblNhanVien.getValueAt(row, 3).equals("Nam") ? 1 : 2;
            cmbGioiTinh.setSelectedIndex(index);
            txtChucVu.setText(cv);
        }
    }

    private void xuLyThemNhanVien() {
        String ho = txtHoNV.getText().trim();
        String ten = txtTenNV.getText().trim();
        String gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        String cv = txtChucVu.getText().trim();
        
        if(!kiemTra(ho, ten, gioiTinh, cv)) { //!true khi có lỗi và !false khi mà không có lỗi
            if(NhanVienBUS.kiemTraTrungNhanVien(ho, ten, gioiTinh, cv)) {
                int them = JOptionPane.showConfirmDialog(this, "Nhân viên này đã tồn tại, bạn có chắc chắn muốn thêm?", "Thông báo trùng Nhân viên", JOptionPane.YES_NO_OPTION);
                if(them == JOptionPane.YES_OPTION) {
                    boolean flag = NhanVienBUS.themNhanVien(ho, ten, gioiTinh, cv);
                    if(flag) {
                        JOptionPane.showMessageDialog(this, "Thêm nhân viên hàng thành công");
                        NhanVienBUS.docDanhSach();
                        loadDataLenBangNhanVien();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại");
                    }
                }
            } else {
                boolean flag = NhanVienBUS.themNhanVien(ho, ten, gioiTinh, cv);
                if(flag) {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                    NhanVienBUS.docDanhSach();
                    loadDataLenBangNhanVien();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại");
                }
            }
        }
        
    }
    
    private void xuLySuaNhanVien() {
        String ma = txtMaNV.getText().trim();
        String ho = txtHoNV.getText().trim();
        String ten = txtTenNV.getText().trim();
        String gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        String cv = txtChucVu.getText().trim();
        
        if(ma.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên");
        } else if(!kiemTra(ho, ten, gioiTinh, cv)) {
            if(NhanVienBUS.kiemTraTrungNhanVien(ho, ten, gioiTinh, cv)) {
                int sua = JOptionPane.showConfirmDialog(this, "Thông tin nhân viên này đã tồn tại, bạn có chắc chắn muốn sửa?", "Thông báo trùng thông tin nhân viên khác", JOptionPane.YES_NO_OPTION);
                if(sua == JOptionPane.YES_OPTION) {
                    boolean flag = NhanVienBUS.suaNhanVien(ma, ho, ten, gioiTinh, cv);
                    if(flag) {
                        JOptionPane.showMessageDialog(this, "Sửa nhân viên thành công");
                        NhanVienBUS.docDanhSach();
                        loadDataLenBangNhanVien();
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa nhân viên thất bại");
                    }
                }
            } else {
                boolean flag = NhanVienBUS.suaNhanVien(ma, ho, ten, gioiTinh, cv);
                if(flag) {
                    JOptionPane.showMessageDialog(this, "Sửa nhân viên thành công");
                    NhanVienBUS.docDanhSach();
                    loadDataLenBangNhanVien();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa nhân viên thất bại");
                }
            }
        }
    }
       
    private void xuLyXoaNhanVien() {
        String ma = txtMaNV.getText().trim();
        String ho = txtHoNV.getText().trim();
        String ten = txtTenNV.getText().trim();
        String gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        String cv = txtChucVu.getText().trim();
        
        if(ma.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên");
        } else if(!kiemTra(ho, ten, gioiTinh, cv)) {
            int flagXoa = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if(flagXoa == JOptionPane.YES_OPTION) {
                boolean flag = NhanVienBUS.xoaNhanVien(ma);
                if(flag) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    xuLyReset();
                    NhanVienBUS.docDanhSach();
                    loadDataLenBangNhanVien();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại");
                }
            }
        }
    }
    
    private void xuLyNhapExcel() {
        int nhap = JOptionPane.showConfirmDialog(this, "Dữ liệu cũ sẽ bị xoá, tiếp tục?", "Thông báo xác nhận", JOptionPane.YES_NO_OPTION);
        if (nhap == JOptionPane.YES_OPTION) {
            XuLyFileExcel nhapExcel = new XuLyFileExcel();
            nhapExcel.nhapExcel(tblNhanVien);

            int row = tblNhanVien.getRowCount();
            for (int i = 0; i < row; i++) {
                String ho = tblNhanVien.getValueAt(i, 1) + "";
                String ten = tblNhanVien.getValueAt(i, 2) + "";
                String gioiTinh = tblNhanVien.getValueAt(i, 3) + "";
                String chucVu = tblNhanVien.getValueAt(i, 4) + "";

                NhanVienBUS.nhapExcel(ho, ten, gioiTinh, chucVu);
            }
        }
    }

    private void xuLyXuatExcel() {
        XuLyFileExcel xuatExcel = new XuLyFileExcel();
        xuatExcel.xuatExcel(tblNhanVien);
    }
    
    private void xuLyResetMatKhau() {
        String maNV = txtMaNV.getText();
        String trangThaiTK = layTrangThaiTK(maNV);
        
        if (maNV.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên");
            return;
        } else {
            if(trangThaiTK.equals("Chưa có")) {
                JOptionPane.showMessageDialog(this, "Nhân viên này chưa được cấp tài khoản");
            } else {
                DlgQuyen_MatKhau dialog = new DlgQuyen_MatKhau(maNV);
                dialog.setVisible(true);
            }
        }
    }

    private void xuLyCapTaiKhoan() {
        String maNV = txtMaNV.getText();
        String trangThaiTK = layTrangThaiTK(maNV);
        
        if (maNV.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên");
            return;
        } else {
            if(trangThaiTK.equals("Chưa có")) {
                DlgCapTaiKhoan dialog = new DlgCapTaiKhoan(maNV);
                dialog.setVisible(true);
                loadDataLenBangNhanVien();
            } else {
                JOptionPane.showMessageDialog(this, "Nhân viên này đã có tài khoản, không thể cấp tài khoản khác");
            }
        }
    }

    private void xuLyKhoaTaiKhoan() {
        String maNV = txtMaNV.getText().trim();
        String trangThaiTK = layTrangThaiTK(maNV);
        
        if (maNV.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên");
            return;
        } else {
            if(trangThaiTK.equals("Hiệu lực")) {
                int khoa = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khóa tài khoảng này?", "Thông báo xác nhận", JOptionPane.YES_NO_OPTION);
                if(khoa == JOptionPane.YES_OPTION) {
                    TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
                    boolean flag = taiKhoanBUS.khoaTaiKhoan(txtMaNV.getText());
                    if(flag) {
                        JOptionPane.showMessageDialog(this, "Khoá tài khoản thành công");
                        loadDataLenBangNhanVien();  
                    } else {
                        JOptionPane.showMessageDialog(this, "Khóa tài khoản thất bại");
                    }
                }
            } else if(trangThaiTK.equals("Chưa có")){
                JOptionPane.showMessageDialog(this, "Nhân viên này chưa được cấp tài khoản!");
            } else if(trangThaiTK.equals("Khóa")) {
                JOptionPane.showMessageDialog(this, "Tài khoản này đã bị khóa rồi, vui lòng mở khóa để tiếp tục");
            }
        }
    }
    
    private void xuLyMoKhoaTaiKhoan() {
        String maNV = txtMaNV.getText();
        String trangThaiTK = layTrangThaiTK(maNV);
        
        if (maNV.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên");
            return;
        } else {
            if(trangThaiTK.equals("Khóa")) {
                int moKhoa = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn mở khóa tài khoảng này?", "Thông báo xác nhận", JOptionPane.YES_NO_OPTION);
                if(moKhoa == JOptionPane.YES_OPTION) {
                    TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
                    boolean flag = taiKhoanBUS.moKhoaTaiKhoan(maNV);
                    if(flag) {
                        JOptionPane.showMessageDialog(this, "Mở khóa tài khoản thành công");
                        loadDataLenBangNhanVien();  
                    } else {
                        JOptionPane.showMessageDialog(this, "Mở khóa tài khoản thất bại");
                    }
                }
            } else if(trangThaiTK.equals("Chưa có")){
                JOptionPane.showMessageDialog(this, "Nhân viên này chưa được cấp tài khoản!");
            } else if(trangThaiTK.equals("Hiệu lực")) {
                JOptionPane.showMessageDialog(this, "Tài khoản này đang không bị khóa");
            }
        }
    }

//    Phương thức của pnl Quyền
     private void loadDataLenCmbQuyen() {
         phanQuyenBUS.docDanhSachNhomQuyen();
        ArrayList<PhanQuyen> dsq = phanQuyenBUS.getListNhomQuyen();
        cmbQuyen.removeAllItems();
        cmbQuyen.addItem("Chọn quyền");
        for (PhanQuyen pq : dsq) {
            cmbQuyen.addItem(pq.getQuyen());
        }
    }
    
     public void xuLyResetQuyen() {
        cmbQuyen.setSelectedIndex(0);
        ckbNhapHang.setSelected(false);
        ckbQLSanPham.setSelected(false);
        ckbQLNhanVien.setSelected(false);
        ckbQLKhachHang.setSelected(false);
        ckbThongKe.setSelected(false);
        ckbKhuyenMai.setSelected(false);
    }
     
    private void xuLyHienThiChiTietQuyen(){
        ArrayList<PhanQuyen> dsq = phanQuyenBUS.getListNhomQuyen();
        PhanQuyen phanQuyen = new PhanQuyen();
        for (PhanQuyen pq : dsq) {
            if (pq.getQuyen().equals(cmbQuyen.getSelectedItem())) {
                phanQuyen.setQuyen(pq.getQuyen());
                phanQuyen.setNhapHang(pq.getNhapHang());
                phanQuyen.setQlSanPham(pq.getQlSanPham());
                phanQuyen.setQlNhanVien(pq.getQlNhanVien());
                phanQuyen.setQlKhachHang(pq.getQlKhachHang());
                phanQuyen.setThongKe(pq.getThongKe());
                break;
            }
        }
        ckbNhapHang.setSelected(false);
        ckbQLSanPham.setSelected(false);
        ckbQLNhanVien.setSelected(false);
        ckbQLKhachHang.setSelected(false);
        ckbThongKe.setSelected(false);
        ckbKhuyenMai.setSelected(false);
        if (phanQuyen.getNhapHang() == 1) {
            ckbNhapHang.setSelected(true);
        }
        if (phanQuyen.getQlSanPham() == 1) {
            ckbQLSanPham.setSelected(true);
        }
        if (phanQuyen.getQlNhanVien() == 1) {
            ckbQLNhanVien.setSelected(true);
        }
        if (phanQuyen.getQlKhachHang() == 1) {
            ckbQLKhachHang.setSelected(true);
        }
        if (phanQuyen.getThongKe() == 1) {
            ckbThongKe.setSelected(true);
        }
        if (phanQuyen.getQlKhuyenMai()== 1) {
            ckbKhuyenMai.setSelected(true);
        }
    }

    private void xuLySuaQuyen() {
        if (cmbQuyen.getSelectedIndex() < 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhóm quyền để sửa");
            return;
        }
        String tenQuyen = cmbQuyen.getSelectedItem() + "";
        int nhapHang = ckbNhapHang.isSelected() ? 1 : 0;
        int sanPham = ckbQLSanPham.isSelected() ? 1 : 0;
        int nhanVien = ckbQLNhanVien.isSelected() ? 1 : 0;
        int khachHang = ckbQLKhachHang.isSelected() ? 1 : 0;
        int thongKe = ckbThongKe.isSelected() ? 1 : 0;
        int khuyenMai = ckbKhuyenMai.isSelected() ? 1 : 0;

        boolean flag = phanQuyenBUS.suaNhomQuyen(tenQuyen, nhapHang, sanPham, nhanVien, khachHang, thongKe, khuyenMai);
        if (flag) {
            JOptionPane.showMessageDialog(this, "Sửa nhóm quyền thành công");
            loadDataLenCmbQuyen();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa nhóm quyền thất bại");
        }
    }
 
    private void xuLyXoaQuyen() {
        if (cmbQuyen.getSelectedIndex() < 1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhóm quyền để xóa");
            return;
        }
        int xoa = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa", "Thông báo xác nhận", JOptionPane.YES_NO_OPTION);
        if (xoa == JOptionPane.YES_OPTION) {
            String tenQuyen = cmbQuyen.getSelectedItem() + "";
            boolean flag = phanQuyenBUS.xoaNhomQuyen(tenQuyen);
            if (flag) {
                loadDataLenCmbQuyen();
            }
        }
    }

    private void xuLyThemQuyen() {
        String tenQuyen = JOptionPane.showInputDialog("Nhập tên quyền");

        boolean flag = phanQuyenBUS.themNhomQuyen(tenQuyen);
        if (flag) {
            loadDataLenCmbQuyen();
        }
    }
    
    private void xuLyLiveSearch() {
        String tukhoa = txtTim.getText().toLowerCase().trim();
        ArrayList<NhanVien> dsnv = NhanVienBUS.timKiemNhanVien(tukhoa);
        loadDataLenBangNhanVien(dsnv);
        if(tukhoa.equals(placeholderTimKiem.toLowerCase().trim())) 
            loadDataLenBangNhanVien();
    }
    
    private boolean kiemTra(String ho, String ten, String gioiTinh, String cv) {
        if(ho.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ đệm Nhân Viên");
            txtHoNV.requestFocus();
            return true;
        } else if(ten.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên Nhân Viên");
            txtTenNV.requestFocus();
            return true;
        } else if(cv.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Chức vụ nhân viên");
            txtChucVu.requestFocus();
            return true;
        } else if (gioiTinh.equals("Chọn giới tính")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính");
            return true;
        } 
        return false;
    }

    private String layTrangThaiTK(String maNV) {
        int trangThai = taiKhoanBUS.getTrangThai(maNV);
            if (trangThai == 0) {
                return "Khóa";
            }
            else if(trangThai == 1) {
                return "Hiệu lực";
            }
            else {
                return "Chưa có";
            }
    }
}