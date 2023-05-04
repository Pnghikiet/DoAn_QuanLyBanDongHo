package GUI;

import BUS.LoaiBUS;
import BUS.NhaCungCapBUS;
import BUS.SanPhamBUS;
import DTO.LoaiSP;
import DTO.SanPham;

import static Main.Main.changLNF;

import Customs.XuLyFileExcel;
import Customs.MyDialog;
import Customs.MyFileChooser;
import Customs.MyTable;
import Customs.TransparentPanel;
import DAO.MyConnect;
import DTO.NhaCungCap;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class PnQuanLySanPhamGUI extends JPanel {

    private TableCellRenderer centerRenderer;
    public PnQuanLySanPhamGUI() {
        changLNF("Windows");
        addControlsSanPham();
        addEventsSanPham();
    }

    SanPhamBUS spBUS = new SanPhamBUS();
    LoaiBUS loaiBUS = new LoaiBUS();
    NhaCungCapBUS nhacungcapBUS = new NhaCungCapBUS();
    final Color colorPanel = new Color(247, 247, 247);
    JTable tblSanPham;
    DefaultTableModel dtmSanPham;
    JTextField txtMa, txtTen, txtsoLuong, txtdonGia, txtTimKiem ;
    JComboBox<String> cmbLoai, cmbNcc;
    JButton btnThem, btnSua, btnXoa, btnTim, btnChonAnh, btnReset, btnXuatExcel, btnNhapExcel;
    JLabel lblAnhSP, lbltittle, lblMa, lblTen, lblsoluong, lbldongia, lbltimkiem, lblmota, lblloai, lblncc;
    ImageIcon icon;
    JTextArea txtareaMota;

    private void addControlsSanPham() {
        txtMa = new JTextField();
        txtareaMota = new JTextArea(100, 100);
        txtTen = new JTextField();
        txtTimKiem = new JTextField();
        txtdonGia = new JTextField();
        txtsoLuong = new JTextField();
        cmbLoai = new JComboBox<String>();
        cmbNcc = new JComboBox<>();
        icon = new ImageIcon("images\\Refresh-icon.png");
        btnReset = new JButton();
        btnChonAnh = new JButton("Chọn Ảnh");
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm kiếm");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");
        lbltittle = new JLabel("Quản lý sản phẩm");
        lblMa = new JLabel("Mã Sản Phẩm: ");
        lblTen = new JLabel("Tên Sản Phẩm: ");
        lbldongia = new JLabel("Đơn Giá: ");
        lblloai = new JLabel("Loại Sản Phẩm: ");
        lblmota = new JLabel("Mô tả Sản Phẩm: ");
        lblncc = new JLabel("Nhả Cung Cấp: ");
        lblsoluong = new JLabel("Số Lượng: ");
        lbltimkiem = new JLabel("Tìm Kiếm: ");
        lblAnhSP = new JLabel();
        
        setSize(1000, 700);
        setLayout(null);
        
        lbltittle.setBounds(320, 20, 120, 22);
        add(lbltittle);
        
        lblMa.setBounds(100, 62, 120, 22);
        add(lblMa);
        
        lblTen.setBounds(100, 102, 120, 22);
        add(lblTen);
        
        lblloai.setBounds(100, 142, 120, 22);
        add(lblloai);
        
        lblncc.setBounds(100, 182, 120, 22);
        add(lblncc);
        
        lbldongia.setBounds(100, 222, 120, 22);
        add(lbldongia);
        
        lblsoluong.setBounds(100, 262, 120, 22);
        add(lblsoluong);
        
        lblmota.setBounds(800, 30, 120, 22);
        add(lblmota);
        
        txtMa.setBounds(240, 60, 200, 28);
        txtMa.setEditable(false);
        add(txtMa);
        
        txtTen.setBounds(240, 100, 200, 28);
        add(txtTen);
        
        cmbLoai.setBounds(240, 140, 200, 28);
        add(cmbLoai);
        
        cmbNcc.setBounds(240, 180, 200, 28);
        add(cmbNcc);
        
        txtdonGia.setBounds(240, 220, 200, 28);
        add(txtdonGia);
        
        txtsoLuong.setBounds(240, 260, 200, 28);
        add(txtsoLuong);
        
        txtareaMota.setBounds(750, 55, 200, 200);
        txtareaMota.setLineWrap(true);
        txtareaMota.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(txtareaMota);
        
        lblAnhSP.setBounds(500, 55, 200, 200);
        lblAnhSP.setIcon(getAnhSP(""));
        lblAnhSP.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(lblAnhSP);
        
        btnChonAnh.setBounds(540, 280, 120, 30);
        add(btnChonAnh);
        
        btnThem.setBounds(100, 340, 95, 27);
        add(btnThem);
        
        btnXoa.setBounds(200, 340, 95, 27);
        add(btnXoa);
        
        btnSua.setBounds(300, 340, 95, 27);
        add(btnSua);
        
        btnNhapExcel.setBounds(400, 340, 95, 27);
        add(btnNhapExcel);
        
        btnXuatExcel.setBounds(500, 340, 95, 27);
        add(btnXuatExcel);
        
        btnReset.setBounds(460, 22, 30, 30);
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(btnReset.getWidth(), btnReset.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledicon = new ImageIcon(imgscale);
        btnReset.setIcon(scaledicon);
        add(btnReset);
        
        lbltimkiem.setBounds(160, 400, 100, 22);
        add(lbltimkiem);
        
        txtTimKiem.setBounds(290, 400, 200, 28);
        add(txtTimKiem);
        
        btnTim.setBounds(530, 400, 100, 27);
        btnTim.setVisible(false);
        add(btnTim);
        
        JPanel pnltable = new JPanel();
        
        pnltable.setBounds(0, 450, 990, 290);
        pnltable.setLayout(null);
        
        dtmSanPham = new DefaultTableModel();
        dtmSanPham.addColumn("Mã SP");
        dtmSanPham.addColumn("Tên SP");
        dtmSanPham.addColumn("Loại SP");
        dtmSanPham.addColumn("Nhà Cung Cấp");
        dtmSanPham.addColumn("Đơn giá");
        dtmSanPham.addColumn("Số lượng");
        dtmSanPham.addColumn("Ảnh");
        dtmSanPham.addColumn("Mô Tả");
        
        tblSanPham = new JTable(dtmSanPham);
        
        tblSanPham.setSize(990, 200);
        
        tblSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        
        TableColumnModel columnModelBanHang = tblSanPham.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(50);
        columnModelBanHang.getColumn(1).setPreferredWidth(200);
        columnModelBanHang.getColumn(2).setPreferredWidth(120);
        columnModelBanHang.getColumn(3).setPreferredWidth(120);
        columnModelBanHang.getColumn(4).setPreferredWidth(138);
        columnModelBanHang.getColumn(5).setPreferredWidth(77);
        columnModelBanHang.getColumn(6).setPreferredWidth(100);
        columnModelBanHang.getColumn(7).setPreferredWidth(160);
        

        JScrollPane scrTblSanPham = new JScrollPane(tblSanPham);
        scrTblSanPham.setBounds(0, 0, 985, 212);
        
        pnltable.add(scrTblSanPham);
        
        add(pnltable);
        
        
        loadDataCmbLoai();
        loadDataCmbNcc();
        loadDataLenBangSanPham();
    }

    private void addEventsSanPham() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAnh("");
                loadDataLenBangSanPham();
                txtMa.setText("");
                txtTen.setText("");
                txtdonGia.setText("");
                txtsoLuong.setText("");
                txtareaMota.setText("");
                cmbLoai.setSelectedIndex(0);
                cmbNcc.setSelectedIndex(0);
            }
        });

        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                btnTim.doClick();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                btnTim.doClick();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                btnTim.doClick();
            }
        });
        
        tblSanPham.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblSanPham();
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

        cmbLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemLoai();
            }
        });

        cmbNcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemNcc();
            }
        });
        
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemSanPham();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaSanPham();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaSanPham();
            }
        });

        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonAnh();
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenBangSanPham(txtTimKiem.getText());
            }
        });

        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatFileExcel();
            }
        });
        btnNhapExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyNhapFileExcel();
            }
        });
    }

    private void xuLyNhapFileExcel() {
        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
        if (dlg.getAction() != MyDialog.OK_OPTION) {
            return;
        }

        XuLyFileExcel nhapFile = new XuLyFileExcel();
        nhapFile.nhapExcel(tblSanPham);

        int row = tblSanPham.getRowCount();
        for (int i = 0; i < row; i++) {
            String ten = tblSanPham.getValueAt(i, 1) + "";
            String loai = tblSanPham.getValueAt(i, 2) + "";
            String ncc = tblSanPham.getValueAt(i, 3) + "";
            String donGia = tblSanPham.getValueAt(i, 4) + "";
            String soLuong = tblSanPham.getValueAt(i, 5) + "";
            String anh = tblSanPham.getValueAt(i, 6) + "";
            String moTa = tblSanPham.getValueAt(i, 7) + "";

            spBUS.nhapSanPhamTuExcel(ten, loai, ncc, soLuong,  anh, donGia, moTa);
        }
    }

    private void xuLyXuatFileExcel() {
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblSanPham);
    }

    private void loadAnh(String anh) {
        lblAnhSP.setIcon(getAnhSP(anh));
    }

    private void xuLyClickTblSanPham() {
        int row = tblSanPham.getSelectedRow();
        if (row > -1) {
            String ma = tblSanPham.getValueAt(row, 0) + "";
            String ten = tblSanPham.getValueAt(row, 1) + "";
            String loai = tblSanPham.getValueAt(row, 2) + "";
            String ncc = tblSanPham.getValueAt(row, 3) + "";
            String donGia = tblSanPham.getValueAt(row, 4) + "";
            String soLuong = tblSanPham.getValueAt(row, 5) + "";
            String anh = tblSanPham.getValueAt(row, 6) + "";
            String mota = tblSanPham.getValueAt(row, 7) + "";


            txtMa.setText(ma);
            txtTen.setText(ten);
            txtdonGia.setText(donGia);
            txtsoLuong.setText(soLuong);

            int flagloai = 0;
            for (int i = 0; i < cmbLoai.getItemCount(); i++) {
                if (cmbLoai.getItemAt(i).contains(loai)) {
                    flagloai = i;
                    break;
                }
            }
            
            int flagncc = 0;
            
            for (int i = 0; i < cmbNcc.getItemCount(); i++) {
                if (cmbNcc.getItemAt(i).contains(ncc)) {
                    flagncc = i;
                    break;
                }
            }
            
            cmbLoai.setSelectedIndex(flagloai);
            cmbNcc.setSelectedIndex(flagncc);
            txtareaMota.setText(mota);
            loadAnh("images\\" + anh);
        }
    }

    private void loadDataLenBangSanPham() {
        spBUS.docListSanPham();
        dtmSanPham.setRowCount(0);

        ArrayList<SanPham> dssp = spBUS.getListSanPham();

        DecimalFormat dcf = new DecimalFormat("###,###");

        for (SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            String tenLoai = loaiBUS.getTenLoai(sp.getMaLoai());
            vec.add(tenLoai);
            String ncc = nhacungcapBUS.gettennhacungcap(sp.getMaNCC());
            vec.add(ncc);
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getHinhAnh());
            vec.add(sp.getMoTa());
            dtmSanPham.addRow(vec);
        }
    }

    private void loadDataLenBangSanPham(String tukhoa) {
        spBUS.docListSanPham();
        dtmSanPham.setRowCount(0);

        ArrayList<SanPham> dssp = spBUS.getSanPhamTheoTen(tukhoa);

        DecimalFormat dcf = new DecimalFormat("###,###");

        for (SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            String tenLoai = loaiBUS.getTenLoai(sp.getMaLoai());
            vec.add(tenLoai);
            String ncc = nhacungcapBUS.gettennhacungcap(sp.getMaNCC());
            vec.add(ncc);
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getHinhAnh());
            vec.add(sp.getMoTa());
            dtmSanPham.addRow(vec);
        }
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
    
    private void xuLyThemSanPham() {
        String anh = fileAnhSP.getName();
        boolean flag = spBUS.themSanPham(txtTen.getText(),
                cmbLoai.getSelectedItem() + "",
                cmbNcc.getSelectedItem() + "",
                txtsoLuong.getText(),
                anh,
                txtdonGia.getText(),
                txtareaMota.getText());
        if(flag == true)
        {
            spBUS.docListSanPham();
            loadDataLenBangSanPham();
            luuFileAnh();
            loadAnh("");
            loadDataLenBangSanPham();
            txtMa.setText("");
            txtTen.setText("");
            txtdonGia.setText("");
            txtsoLuong.setText("");
            txtareaMota.setText("");
            cmbLoai.setSelectedIndex(0);
            cmbNcc.setSelectedIndex(0);
        }
    }

    File fileAnhSP;

    private void xuLySuaSanPham() {
        String anh = fileAnhSP.getName();
        boolean flag = spBUS.suaSanPham(txtMa.getText(),
                txtTen.getText(),
                cmbLoai.getSelectedItem() + "",
                cmbNcc.getSelectedItem() + "",
                txtsoLuong.getText(),
                anh,
                txtdonGia.getText(),
                txtareaMota.getText());
        if(flag == true)
        {
            spBUS.docListSanPham();
            loadDataLenBangSanPham();
            luuFileAnh();
        }
    }

    private void xuLyXoaSanPham() {
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            boolean flag = spBUS.xoaSanPham(txtMa.getText());
            if (flag) {
                loadDataLenBangSanPham();
                loadAnh("");
                loadDataLenBangSanPham();
                txtMa.setText("");
                txtTen.setText("");
                txtdonGia.setText("");
                txtsoLuong.setText("");
                txtareaMota.setText("");
                cmbLoai.setSelectedIndex(0);
                cmbNcc.setSelectedIndex(0);
            }
        }
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

    
}
