
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
public class FrmQuanLyKhachHang extends JFrame{
    private KhachHangBUS khachHangBUS = new KhachHangBUS();
    
    private JLabel lblTitle, lblMaKH, lblHoKH, lblTenKH, lblGioiTinh, lblSoDienThoai, lblTongChiTieu, lblTim, lblTimChiTieu, lblDen;
    private JTextField txtMaKH, txtHoKH, txtTenKH,txtSoDienThoai, txtTongChiTieu, txtTim, txtMinChiTieu, txtMaxChiTieu;
    private JPanel pnlTimKiemTheoChiTieu;
    public JComboBox<String> cmbGioiTinh;
    public JComboBox<String> cmbLoaiTimKiem;
    public JComboBox<String> cmbTimKiem;
    private JTable tblKhachHang;
    private DefaultTableModel modelKhachHang;
    private JButton btnReset, btnThem, btnSua, btnXoa, btnTimKiem;
    private String placeholderTimKiem = "Nhập từ khóa mà bạn muốn tìm kiếm...";
    private boolean  hoError = false;
    private boolean  tenError = false;
    private boolean  sdtError = false;
    private boolean  minError = false;
    private boolean  maxError = false;
    private int count = 0;
    public FrmQuanLyKhachHang() {
       addControlsKhachHang();
       addEventsKhachHang();
    }
    public void addControlsKhachHang() {
        setTitle("Quản lý khách hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        
        DefaultTableCellRenderer centerRederer = new DefaultTableCellRenderer();
        centerRederer.setHorizontalAlignment(JLabel.CENTER);
        
        
        
        lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        Font font = new Font("Arial", Font.BOLD, 26);
        lblTitle.setFont(font);
        
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
        txtHoKH = new JTextField(20);
        txtTenKH = new JTextField(20);
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
        
        cmbGioiTinh = new JComboBox<String>();
        cmbGioiTinh.addItem("Chọn giới tính");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");
//        cmbLoaiTimKiem = new JComboBox<String>();
//        cmbLoaiTimKiem.setVisible(false);
//        cmbTimKiem = new JComboBox<String>();
        GridBagConstraints cons = new GridBagConstraints();
        
        // Panel Tìm kiếm theo chi tiêu
        pnlTimKiemTheoChiTieu = new JPanel(new GridBagLayout());
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
        
//        pnlTimKiemTheoChiTieu.setVisible(false);
        
        
//        Panel Title
        JPanel pnlTitle = new JPanel(new GridBagLayout());
        
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 1;
        cons.insets = new Insets(10, 10, 10, 10);
        cons.anchor = GridBagConstraints.CENTER;
        pnlTitle.add(lblTitle, cons);
        
        cons.gridx = 1;
        cons.gridy = 0;
        cons.gridwidth = 1;
        cons.anchor = GridBagConstraints.WEST;
        pnlTitle.add(btnReset, cons);
        
//        Thêm pnlTitle vào Frame
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.PAGE_START;
        cons.fill = GridBagConstraints.BOTH;
        cons.gridwidth = 1;
        add(pnlTitle, cons);
        
       

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
        
//        Thêm pnl TTKH vào JFrame
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.fill = GridBagConstraints.BOTH;
        add(pnlTTKH, cons);
            
//        Them pnl Tim Kiem vao JFrame
        cons.gridx = 0;
        cons.gridy = 2;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.fill = GridBagConstraints.BOTH;
        cons.anchor = GridBagConstraints.CENTER;
        add(pnlTimKiem, cons);

//        Thêm Pnl Tìm kiếm theo chi tiêu vào Frm
        cons.gridx = 0;
        cons.gridy = 3;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.fill = GridBagConstraints.BOTH;
        cons.anchor = GridBagConstraints.CENTER;
        add(pnlTimKiemTheoChiTieu, cons);
        
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
        add(pnlTable, cons);
        
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
        
        setFocusTraversalPolicy(policy);
        
        loadDataLenBangKhachHang();
        
        setSize(1000, 700);
        setVisible(true);
    }
    
    private void addEventsKhachHang() {
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
        
//        txtHoKH.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if(tenError || sdtError || minError || maxError) {
//                    System.out.println("Có dialog nào hiển thị" + (++count));
//                    hoError = false;
//                } else {
//                    hoError = true;
//                     System.out.println("Không có dialog nào hiển thị" + (++count));
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {   
//                System.out.println("--------Ho-----------");
//                System.out.println(hoError);
//                System.out.println(tenError);
//                String ho = txtHoKH.getText().trim();
//                if(ho.equals("") && hoError) {
//                    int option = JOptionPane.showOptionDialog(rootPane, "Vui lòng không bỏ trống họ đệm", "Thông báo họ đệm bị bỏ trống", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
//                    if (option == JOptionPane.OK_OPTION) {
//                        txtHoKH.requestFocus();  
////                        hoError = false;
//                    }
//                }
//            }
//        });
//        
//        
//        txtTenKH.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if(hoError || sdtError || minError || maxError) {
//                System.out.println("Có dialog nào hiển thị" + (++count));
//                    tenError = false;
//                } else {
//                    System.out.println("Không có dialog nào hiển thị" + (++count));
//                    tenError = true;
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                System.out.println("--------Ten-----------");
//                System.out.println(hoError);
//                System.out.println(tenError);
//                String ten = txtTenKH.getText().trim();
//                if(ten.equals("") && tenError) {
//                    int option = JOptionPane.showOptionDialog(rootPane, "Vui lòng không bỏ trống tên", "Thông báo tên bị bỏ trống", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
//                    if (option == JOptionPane.OK_OPTION) {
//                        txtTenKH.requestFocus();  
////                        tenError = false;
//                    }
//                }
//            }
//        });
//        
//        
//        txtSoDienThoai.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                if(countDialog == 0) {
//                    countDialog = 1;
//                    String sdt = txtSoDienThoai.getText().trim();
//                    if(sdt.equals("")) {
//                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập số điện thoại khách hàng");
//                        JOptionPane.getRootFrame().dispose();
//                        countDialog = 0;
//                        txtSoDienThoai.requestFocus();
//                    }
//                }
//            }
//        });
//        
//        
//        txtMinChiTieu.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                System.out.println(getOwnedWindows().length);
//                if(getOwnedWindows().length == 0) {
//                    String minChiTieu = txtMinChiTieu.getText().trim();
//                    if(minChiTieu.equals("")) {
//                        JOptionPane.showMessageDialog(rootPane, "Không được để trống chi tiêu nhỏ nhất");
//                        JOptionPane.getRootFrame().dispose();
//                        txtMinChiTieu.requestFocus();
//                    } else {
//                        try {
//                            minChiTieu = minChiTieu.replace(",", "");
//                            int min = Integer.parseInt(minChiTieu);
//                    } catch (Exception ex) {
//                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập chi tiêu với định dạng số!");
//                        JOptionPane.getRootFrame().dispose();
//                        txtMinChiTieu.requestFocus();
//                        } 
//                    }
//                }
//            }
//        });
//        
//        
//        txtMaxChiTieu.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                System.out.println(getOwnedWindows().length);
//                if(getOwnedWindows().length == 0) {
//                    String minChiTieu = txtMinChiTieu.getText().trim();
//                    String maxChiTieu = txtMaxChiTieu.getText().trim();  
//                    if (maxChiTieu.equals("")) {
//                        JOptionPane.showMessageDialog(rootPane, "Không được để trống chi tiêu lớn nhất");
//                        JOptionPane.getRootFrame().dispose();
//                        txtMaxChiTieu.requestFocus();
//                    } else {
//                        try {
//                            minChiTieu = minChiTieu.replace(",", "");
//                            maxChiTieu = maxChiTieu.replace(",", "");
//                            int min = Integer.parseInt(minChiTieu);
//                            int max = Integer.parseInt(maxChiTieu);
//
//                            if(min > max) {
//                                JOptionPane.showMessageDialog(rootPane, "Chi tiêu nhỏ nhất phải nhỏ hơn chi tiêu lớn nhất");
//                                JOptionPane.getRootFrame().dispose();
//                                txtMinChiTieu.requestFocus();
//                            }
//                    } catch (Exception ex) {
//                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập chi tiêu với định dạng số!");
//                        JOptionPane.getRootFrame().dispose();
//                        txtMinChiTieu.requestFocus();
//                        } 
//                    }
//                }
//            }
//        });
        
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
    private void xuLyLiveSearch() {
        String tuKhoa = txtTim.getText().toLowerCase().trim();
        ArrayList<KhachHang> dskh = khachHangBUS.timKiemKhachHang(tuKhoa);
        loadDataLenBangKhachHang(dskh);
        if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
            loadDataLenBangKhachHang();
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


//comit , rollback 