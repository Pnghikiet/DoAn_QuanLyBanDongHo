
package GUI;

import BUS.GiamGiaBUS;
import DAO.MyConnect;
import DTO.GiamGia;
import com.toedter.calendar.JDateChooser;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
public final class PnlQLGiamGia extends JPanel{
    private final GiamGiaBUS GiamGiaBUS = new GiamGiaBUS();
    
    private JLabel lblTitleGiamGia, lblMaGG, lblTenGG, lblPhanTramGG, lblDieuKienGG, lblNgayBD, lblNgayKT, lblTim;
    private JTextField txtMaGG, txtTenGG, txtPhanTramGG,txtDieuKienGG, txtTim;
    private JDateChooser ngayBD, ngayKT;
    private JButton btnReset, btnThem, btnSua, btnXoa;
    private JTable tblGiamGia;
    private DefaultTableModel modelGiamGia;
    private Font font;
    private final String placeholderTimKiem = "Nhập từ khóa mà bạn muốn tìm kiếm...";
    
    public PnlQLGiamGia() {
       addControls();
       addEventsGiamGia();
    }    
    public void addControls() {
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setLayout(new BorderLayout());
        GridBagConstraints cons = new GridBagConstraints();
        font = new Font("Arial", Font.BOLD, 26);

        DefaultTableCellRenderer centerRederer = new DefaultTableCellRenderer();
        centerRederer.setHorizontalAlignment(JLabel.CENTER);
        
        /*
        =========================================================================
                                    PANEL GIAM GIA
        =========================================================================
         */
        
        JPanel pnlGiamGia = new JPanel(new GridBagLayout());
        
        lblTitleGiamGia = new JLabel("QUẢN LÝ GIẢM GIÁ KHUYẾN MÃI");
        lblTitleGiamGia.setFont(font);
        
        lblMaGG = new JLabel("Mã giảm giá");
        lblTenGG = new JLabel("Tên giảm giá");
        lblPhanTramGG = new JLabel("Phần trăm giảm");
        lblDieuKienGG = new JLabel("Điều kiện giảm");
        lblNgayBD = new JLabel("Ngày bắt đầu");
        lblNgayKT = new JLabel("Ngày kết thúc");
        lblTim = new JLabel("Từ khóa  tìm kiếm");
        
        txtMaGG = new JTextField(20);
        txtMaGG.setEditable(false);
        txtTenGG = new JTextField(20);
        txtPhanTramGG = new JTextField(20);
        txtDieuKienGG = new JTextField(20);
        ngayBD = new JDateChooser();
        ngayBD.setDateFormatString("dd/MM/yyyy");
        ngayKT = new JDateChooser();
        ngayKT.setDateFormatString("dd/MM/yyyy");
        txtTim = new JTextField(20);
        
        txtTim.setForeground(Color.GRAY);
        txtTim.setText(placeholderTimKiem);
        
        btnReset = new JButton("Reset");
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        
//        Panel Title 
        JPanel pnlTitleGiamGia = new JPanel(new GridBagLayout());
        GridBagConstraints consTitleGG = new GridBagConstraints();
        consTitleGG.gridx = 0;
        consTitleGG.gridy = 0;
        consTitleGG.gridwidth = 1;
        consTitleGG.insets = new Insets(10, 10, 10, 10);
        consTitleGG.anchor = GridBagConstraints.CENTER;
        pnlTitleGiamGia.add(lblTitleGiamGia, consTitleGG);
        
        consTitleGG.gridx = 1;
        consTitleGG.gridy = 0;
        consTitleGG.gridwidth = 1;
        consTitleGG.anchor = GridBagConstraints.WEST;
        pnlTitleGiamGia.add(btnReset, consTitleGG);
        
//        Thêm pnlTitleGiamGia vào PanelGiamGia
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.PAGE_START;
        cons.fill = GridBagConstraints.BOTH;
        cons.gridwidth = 1;
        pnlGiamGia.add(pnlTitleGiamGia, cons);
        
//        Panel Thông tin Khuyến Mãi
        JPanel pnlTTGG = new JPanel(new GridBagLayout());
        GridBagConstraints consTTGG = new GridBagConstraints();
        consTTGG.gridx = 0;
        consTTGG.gridy = 0;
        consTTGG.gridwidth = 1;
        consTTGG.insets = new Insets(10, 10, 10, 10);
        consTTGG.anchor = GridBagConstraints.WEST;
        pnlTTGG.add(lblMaGG, consTTGG);
        
        consTTGG.gridx = 1;
        consTTGG.gridy = 0;
        consTTGG.gridwidth = 1;
        consTTGG.fill = GridBagConstraints.BOTH;
        pnlTTGG.add(txtMaGG, consTTGG);
        
        consTTGG.gridx = 0;
        consTTGG.gridy = 1;
        consTTGG.gridwidth = 1;
        pnlTTGG.add(lblTenGG, consTTGG);
        
        consTTGG.gridx = 1;
        consTTGG.gridy = 1;
        consTTGG.gridwidth = 1;
        consTTGG.fill = GridBagConstraints.BOTH;
        pnlTTGG.add(txtTenGG, consTTGG);
        
        consTTGG.gridx = 0;
        consTTGG.gridy = 2;
        consTTGG.gridwidth = 1;
        pnlTTGG.add(lblPhanTramGG, consTTGG);
        
        consTTGG.gridx = 1;
        consTTGG.gridy = 2;
        consTTGG.gridwidth = 1;
        consTTGG.fill = GridBagConstraints.BOTH;
        pnlTTGG.add(txtPhanTramGG, consTTGG);
        
        consTTGG.gridx = 0;
        consTTGG.gridy = 3;
        consTTGG.gridwidth = 1;
        pnlTTGG.add(lblDieuKienGG, consTTGG);
        
        consTTGG.gridx = 1;
        consTTGG.gridy = 3;
        consTTGG.gridwidth = 1;
        consTTGG.fill = GridBagConstraints.BOTH;
        pnlTTGG.add(txtDieuKienGG, consTTGG);
        
        consTTGG.gridx = 0;
        consTTGG.gridy = 4;
        consTTGG.gridwidth = 1;
        pnlTTGG.add(lblNgayBD, consTTGG);
        
        consTTGG.gridx = 1;
        consTTGG.gridy = 4;
        consTTGG.gridwidth = 1;
        consTTGG.fill = GridBagConstraints.BOTH;
        pnlTTGG.add(ngayBD, consTTGG);
        
        consTTGG.gridx = 0;
        consTTGG.gridy = 5;
        consTTGG.gridwidth = 1;
        pnlTTGG.add(lblNgayKT, consTTGG);
        
        consTTGG.gridx = 1;
        consTTGG.gridy = 5;
        consTTGG.gridwidth = 1;
        consTTGG.fill = GridBagConstraints.BOTH;
        pnlTTGG.add(ngayKT, consTTGG);
        
//        Panel Tim kiem
        JPanel pnlTimKiem = new JPanel(new GridBagLayout());
        GridBagConstraints consTK = new GridBagConstraints();
        consTK.gridx = 0;
        consTK.gridy = 0;
        consTK.gridwidth = 1;
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

//        add pnl Button vào pnl TTGG
        
        consTTGG.gridx = 0;
        consTTGG.gridy = 6;
        consTTGG.gridwidth = 2;
        consTTGG.anchor = GridBagConstraints.CENTER;
        pnlTTGG.add(pnlBtn, consTTGG);
        
//        Thêm pnl TTGG vào pnlGiamGia
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.fill = GridBagConstraints.BOTH;
        pnlGiamGia.add(pnlTTGG, cons);
            
//        Them pnl Tim Kiem vao pnlGiamGia
        cons.gridx = 0;
        cons.gridy = 2;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.fill = GridBagConstraints.BOTH;
        cons.anchor = GridBagConstraints.CENTER;
        pnlGiamGia.add(pnlTimKiem, cons);
        
        JPanel pnlTable = new JPanel(new BorderLayout());
        
        modelGiamGia = new DefaultTableModel();
        
        modelGiamGia.addColumn("Mã GG");
        modelGiamGia.addColumn("Tên GG");
        modelGiamGia.addColumn("Phần trăm giảm");
        modelGiamGia.addColumn("Điều kiện");
        modelGiamGia.addColumn("Ngày bắt đầu");
        modelGiamGia.addColumn("Ngày kết thúc");
        modelGiamGia.addColumn("Tình trạng");
        
        tblGiamGia = new JTable(modelGiamGia);
        
        tblGiamGia.getColumnModel().getColumn(0).setCellRenderer(centerRederer);
        tblGiamGia.getColumnModel().getColumn(1).setCellRenderer(centerRederer);
        tblGiamGia.getColumnModel().getColumn(2).setCellRenderer(centerRederer);
        tblGiamGia.getColumnModel().getColumn(3).setCellRenderer(centerRederer);
        tblGiamGia.getColumnModel().getColumn(4).setCellRenderer(centerRederer);
        tblGiamGia.getColumnModel().getColumn(5).setCellRenderer(centerRederer);
        tblGiamGia.getColumnModel().getColumn(5).setCellRenderer(centerRederer);
        
//        Thêm pnlTable vào pnlGiamGia
        JScrollPane scrGiamGia = new JScrollPane(tblGiamGia);  
        pnlTable.add(scrGiamGia, BorderLayout.CENTER);
        cons.gridx = 0;
        cons.gridy = 3;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.fill = GridBagConstraints.BOTH;
        cons.anchor = GridBagConstraints.CENTER;
        pnlGiamGia.add(pnlTable, cons);
        
        ArrayList<Component> order = new ArrayList<>();
        order.add(txtTenGG);
        order.add(txtPhanTramGG);
        order.add(txtDieuKienGG);
        order.add(ngayBD);
        order.add(ngayKT);
        order.add(btnThem);
        order.add(btnSua);
        order.add(btnXoa);
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
        pnlGiamGia.setFocusTraversalPolicy(policy);
       
        add(pnlGiamGia);
        
        loadDataLenBangGiamGia();
        
        setSize(1000, 700);
        setVisible(true);
    }
    
    private void addEventsGiamGia() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenBangGiamGia();
                xuLyReset();
            }
        });
        
        btnReset.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    loadDataLenBangGiamGia();
                    xuLyReset();
                }
            }
        });
        
        btnThem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyThemGiamGia();
                }
            }
        });
        
        btnSua.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLySuaGiamGia();
                }
            }
        });
        
        btnXoa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    xuLyXoaGiamGia();
                }
            }
        });

        tblGiamGia.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyChonGiamGia();
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
                xuLyThemGiamGia();
            }
        });
        
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaGiamGia();
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaGiamGia();
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
     private void loadDataLenBangGiamGia() {
        GiamGiaBUS.docDanhSach();
        ArrayList<GiamGia> dsgg = GiamGiaBUS.getDanhSachGiamGia();
        loadDataLenBangGiamGia(dsgg);
    }

    private void loadDataLenBangGiamGia(ArrayList<GiamGia> dsgg) {
        modelGiamGia.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat dcf = new DecimalFormat(">###,###");
        for (GiamGia gg : dsgg) {
            Vector vec = new Vector();
            vec.add(gg.getMaGG());
            vec.add(gg.getTenGG());
            vec.add(gg.getPhanTramGiam());
            vec.add(dcf.format(gg.getDieuKien()));
            vec.add(sdf.format(gg.getNgayBD()));
            vec.add(sdf.format(gg.getNgayKT()));
            boolean flag = GiamGiaBUS.kiemTraHieuLucKhuyenMai(gg.getMaGG());
            if(flag) {
                vec.add("Hiệu lực");
            } else {
                vec.add("Không hiệu lực");
            }
            modelGiamGia.addRow(vec);
        }
    }
    
    private void xuLyChonGiamGia() {
        int row = tblGiamGia.getSelectedRow();
        if(row > -1) {
            String ma = tblGiamGia.getValueAt(row, 0) + "";
            String ten = tblGiamGia.getValueAt(row, 1) + "";
            String phanTramGG = tblGiamGia.getValueAt(row, 2) + "";
            String dieuKien = tblGiamGia.getValueAt(row, 3) + "";
            String start = tblGiamGia.getValueAt(row, 4) + "";
            String end = tblGiamGia.getValueAt(row, 5) + "";
            
            
            dieuKien = dieuKien.replace(">", "");
            dieuKien = dieuKien.replace(",", "");
            
            Date dateStart = new Date();
            Date dateEnd = new Date();
            
            try {
                dateStart = new SimpleDateFormat("dd/MM/yyyy").parse(start);
                dateEnd = new SimpleDateFormat("dd/MM/yyyy").parse(end);
            } catch (ParseException e) {
            }
            
            txtMaGG.setText(ma);
            txtTenGG.setText(ten);
            txtPhanTramGG.setText(phanTramGG);
            txtDieuKienGG.setText(dieuKien);
            ngayBD.setDate(dateStart);
            ngayKT.setDate(dateEnd);
        }
    }
    
    public void xuLyReset() {
        txtMaGG.setText("");
        txtTenGG.setText("");
        txtPhanTramGG.setText("");
        txtDieuKienGG.setText("");
        ngayBD.setDate(null);
        ngayKT.setDate(null);
        txtTim.setText(placeholderTimKiem);
        txtTim.setForeground(Color.GRAY);
    }
    
    private void xuLyThemGiamGia() {
        String ten = txtTenGG.getText().trim();
        String phanTram = txtPhanTramGG.getText().trim();
        String dieuKien = txtDieuKienGG.getText().trim();
        Date start = ngayBD.getDate();
        Date end = ngayKT.getDate();

        if(!kiemTra(ten, phanTram, dieuKien)) { //!true khi có lỗi và !false khi mà không có lỗi
            if(GiamGiaBUS.kiemTraTrungGiamGia(ten,phanTram,dieuKien,start,end)) {
                int xoa = JOptionPane.showConfirmDialog(this, "Mã giảm này đã tồn tại, bạn có chắc chắn muốn thêm?", "Thông báo trùng mã giảm", JOptionPane.YES_NO_OPTION);
                if(xoa == JOptionPane.YES_OPTION) {
                    boolean flag = GiamGiaBUS.themMaGiam(ten, phanTram,dieuKien,start,end);
                    if(flag) {
                        JOptionPane.showMessageDialog(this, "Thêm mã giảm thành công");
                        GiamGiaBUS.docDanhSach();
                        loadDataLenBangGiamGia();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm mã giảm thất bại");
                    }
                }
            } else {
                boolean flag = GiamGiaBUS.themMaGiam(ten, phanTram, dieuKien, start, end);
                if(flag) {
                    JOptionPane.showMessageDialog(this, "Thêm mã giảm thành công");
                    GiamGiaBUS.docDanhSach();
                    loadDataLenBangGiamGia();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm mã giảm thất bại");
                }
            }
        }
        
    }
    
    private void xuLySuaGiamGia() {
        String ma = txtMaGG.getText().trim();
        String ten = txtTenGG.getText().trim();
        String phanTram = txtPhanTramGG.getText().trim();
        String dieuKien = txtDieuKienGG.getText().trim();
        Date start = ngayBD.getDate();
        Date end = ngayKT.getDate();
        
        if(ma.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa chọn mã giảm");
        } else if(!kiemTra(ten, phanTram, dieuKien)) {
            if(GiamGiaBUS.kiemTraTrungGiamGia(ten, phanTram, dieuKien, start, end)) {
                int sua = JOptionPane.showConfirmDialog(this, "Mã giảm này đã tồn tại, bạn có chắc chắn muốn sửa?", "Thông báo trùng mã giảm", JOptionPane.YES_NO_OPTION);
                if(sua == JOptionPane.YES_OPTION) {
                    boolean flag = GiamGiaBUS.suaMaGiam(ma, ten, phanTram, dieuKien, start, end);
                    if(flag) {
                        JOptionPane.showMessageDialog(this, "Sửa mã giảm thành công");
                        GiamGiaBUS.docDanhSach();
                        loadDataLenBangGiamGia();
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa mã giảm thất bại");
                    }
                }
            } else {
                boolean flag = GiamGiaBUS.suaMaGiam(ma, ten, phanTram, dieuKien, start, end);
                if(flag) {
                    JOptionPane.showMessageDialog(this, "Sửa mã giảm thành công");
                    GiamGiaBUS.docDanhSach();
                    loadDataLenBangGiamGia();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa mã giảm thất bại");
                }
            }
        }
    }

    private void xuLyXoaGiamGia() {
        String ma = txtMaGG.getText().trim();
        String ten = txtTenGG.getText().trim();
        String phantram = txtPhanTramGG.getText().trim();
        String dieuKien = txtDieuKienGG.getText().trim();
        
        if(ma.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa chọn mã giảm giá");
        } else if(!kiemTra(ten, phantram, dieuKien)) {
            int flagXoa = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if(flagXoa == JOptionPane.YES_OPTION) {
                boolean flag = GiamGiaBUS.xoaMaGiam(ma);
                if(flag) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    xuLyReset();
                    GiamGiaBUS.docDanhSach();
                    loadDataLenBangGiamGia();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại");
                }
            }
        }
    }
    
    private void xuLyLiveSearch() {
        String tuKhoa = txtTim.getText().toLowerCase().trim();
        ArrayList<GiamGia> dskh = GiamGiaBUS.timKiemGiamGia(tuKhoa);
        loadDataLenBangGiamGia(dskh);
        if(tuKhoa.equals(placeholderTimKiem.toLowerCase())) 
            loadDataLenBangGiamGia();
    }
   
    private boolean kiemTra(String ten, String phanTram, String dieuKien) {
        dieuKien = dieuKien.replace(">", "");
        dieuKien = dieuKien.replace(",", "");
        phanTram = phanTram.replace("%", "");
        
                
        String errorPT = "";
        String errorDK = "";
        String errorNgayBD = "";
        String errorNgayKT = "";
        String errorDate = "";
        
        try {
            Date start = ngayBD.getDate();
            Date end = ngayKT.getDate();
            if(start.getTime() > end.getTime()) 
                errorDate = "THOI_HANG_KHONG_HOP_LE";
        } catch (Exception e) {
        }
        
        try {
            int pt = Integer.parseInt(phanTram);
            if(pt < 0 || pt > 100)
                errorPT = "VUOT_KHOANG_CHO_PHEP";
        } catch (Exception e) {
            errorPT = "KHONG_PHAI_SO";
        }
        
        
        try {
            int dk = Integer.parseInt(dieuKien);
        } catch (Exception e) {
            errorDK = "KHONG_PHAI_SO";
        }
        
        try {
           Date start = ngayBD.getDate(); 
        } catch (Exception e) {
            errorPT = "KHONG_HOP_LE";
        }
        
        try {
           Date end = ngayKT.getDate(); 
        } catch (Exception e) {
            errorPT = "KHONG_HOP_LE";
        }

        
        if(ten.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tên giảm giá");
            txtTenGG.requestFocus();
            return true;
        } else if(phanTram.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập phần trăm giảm giá");
            txtPhanTramGG.requestFocus();
            return true;
        } else if(errorPT.equals("VUOT_KHOANG_CHO_PHEP")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trong khoảng 0 đến 100");
            txtPhanTramGG.requestFocus();
            return true;
        } else if(errorPT.equals("KHONG_PHAI_SO")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
            txtPhanTramGG.requestFocus();
            return true;
        } else if(dieuKien.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập điều kiện");
            txtDieuKienGG.requestFocus();
            return true;
        } else if(errorDK.equals("KHONG_PHAI_SO")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
            txtDieuKienGG.requestFocus();
            return true;
        } else if(ngayBD.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu");
            ngayBD.requestFocus();
            return true;
        } else if(errorNgayBD.equals("KHONG_HOP_LE")) {
            JOptionPane.showMessageDialog(this, "Ngày nhập vào không hợp lệ");
            ngayBD.requestFocus();
            return true;
        } else if(ngayKT.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày kết thúc");
            ngayKT.requestFocus();
            return true;
        } else if(errorNgayKT.equals("KHONG_HOP_LE")) {
            JOptionPane.showMessageDialog(this, "Ngày nhập vào không hợp lệ");
            ngayKT.requestFocus();
            return true;
        } else if(errorDate.equals("THOI_HANG_KHONG_HOP_LE")) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu");
            ngayKT.requestFocus();
            return true;
        } 
        return false;
    }
}