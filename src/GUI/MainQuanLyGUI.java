package GUI;

import BUS.DangNhapBUS;
import BUS.PhanQuyenBUS;
import DTO.PhanQuyen;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainQuanLyGUI extends JFrame {

    public MainQuanLyGUI() {
        this.setTitle("Phần mềm quản lý bán đồng hồ");
        this.setSize(1250, 700);
        addControls();
        addEvents();
    }

    public void showWindow() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    JButton btnDoiMatKhau;
    JButton btnDangXuat;
    JPanel pnTitle, pnMenuLeft, pnCard, pnBanHang, pnKhuyenMai, pnNhapHang, pnSanPham, pnNhanVien, pnKhachHang, pnThongKe, pnBanHangChoose, pnKhuyenMaiChoose, pnNhapHangChoose, pnSanPhamChoose, pnNhanVienChoose, pnKhachHangChoose, pnThongKeChoose;
    PnQuanLyBanHangGUI banHangPanel;
    PnlQLGiamGia khuyenMaiPanel;
    PnQuanLyNhapHangGUI nhapHangPanel;
    PnQuanLySanPhamGUI sanPhamPanel;
    PnlQuanLyNhanVien nhanVienPanel;
    PnlQuanLyKhachHang khachHangPanel;
    PnlQLThongKe thongKePanel;
    
    DangNhapBUS dnBUS = new DangNhapBUS();
    PhanQuyenBUS pqBUS = new PhanQuyenBUS();
    
    JLabel btnClose, btnMinimize, lblBanHang, lblKhuyenMai, lblNhapHang, lblSanPham, lblNhanVien, lblKhachHang, lblThongKe;
    final Color clLeftItem = new Color(63, 74, 89);
    final Color clLeftItemHover = new Color(72, 88, 107);
    final Color clLeftItemSelected = new Color(51, 202, 187);
    final Font fontChoose = new Font("Arial", Font.BOLD, 24);
    ArrayList<JPanel> listMenuLeft;
    CardLayout cardMenuLeftGroup = new CardLayout();

    private void addControls() {
        int width = this.getWidth();
        int height = this.getHeight();

        Container con = getContentPane();

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());

        /*
        ============================================================
                                TITLE BAR
        ============================================================
         */
        pnTitle = new JPanel(null);
        pnTitle.setPreferredSize(new Dimension(width, 46));

        btnDoiMatKhau = new JButton("Đổi mật khẩu");
        btnDoiMatKhau.setBounds(0, 0, 140, 46);
        btnDoiMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTitle.add(btnDoiMatKhau);
        
        btnDangXuat = new JButton("Đăng xuất");
        btnDangXuat.setBounds(150, 0, 100, 46);
        btnDangXuat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTitle.add(btnDangXuat);
        
        pnMain.add(pnTitle, BorderLayout.NORTH);
        /*
        ============================================================
                                SIDE BAR MENU
        ============================================================
         */
        pnMenuLeft = new JPanel();
        pnMenuLeft.setPreferredSize(new Dimension(250, height - pnTitle.getHeight()));
        pnMenuLeft.setBackground(clLeftItem);
        pnMenuLeft.setLayout(new BoxLayout(pnMenuLeft, BoxLayout.Y_AXIS));

        pnBanHangChoose = new JPanel(new BorderLayout());
        pnKhuyenMaiChoose = new JPanel(new BorderLayout());
        pnSanPhamChoose = new JPanel(new BorderLayout());
        pnKhachHangChoose = new JPanel(new BorderLayout());
        pnNhanVienChoose = new JPanel(new BorderLayout());
        pnThongKeChoose = new JPanel(new BorderLayout());
        pnNhapHangChoose = new JPanel(new BorderLayout());
        
        
        JLabel lblAvatar = new JLabel(new ImageIcon("images/ManagerUI/logo.jpg"), JLabel.CENTER);
        lblAvatar.setPreferredSize(new Dimension(250, 140));
        lblAvatar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pnMenuLeft.add(lblAvatar);

        lblBanHang = new JLabel("Quản lý bán hàng");
        lblKhuyenMai = new JLabel("Quản lý khuyến mãi");
        lblNhapHang = new JLabel("Quản lý nhập hàng");
        lblSanPham = new JLabel("Quản lý sản phẩm");
        lblNhanVien = new JLabel("Quản lý nhân viên");
        lblKhachHang = new JLabel("Quản lý khách hàng");
        lblThongKe = new JLabel("Quản lý thống kê");

        lblBanHang.setForeground(Color.WHITE);
        lblKhuyenMai.setForeground(Color.WHITE);
        lblNhapHang.setForeground(Color.WHITE);
        lblSanPham.setForeground(Color.WHITE);
        lblNhanVien.setForeground(Color.WHITE);
        lblKhachHang.setForeground(Color.WHITE);
        lblThongKe.setForeground(Color.WHITE);
        
        lblBanHang.setFont(fontChoose);
        lblKhuyenMai.setFont(fontChoose);
        lblNhapHang.setFont(fontChoose);
        lblSanPham.setFont(fontChoose);
        lblNhanVien.setFont(fontChoose);
        lblKhachHang.setFont(fontChoose);
        lblThongKe.setFont(fontChoose);
        
        pnBanHangChoose.add(lblBanHang, BorderLayout.CENTER);
        pnKhuyenMaiChoose.add(lblKhuyenMai, BorderLayout.CENTER);
        pnSanPhamChoose.add(lblSanPham, BorderLayout.CENTER);
        pnKhachHangChoose.add(lblKhachHang, BorderLayout.CENTER);
        pnNhanVienChoose.add(lblNhanVien, BorderLayout.CENTER);
        pnNhapHangChoose.add(lblNhapHang, BorderLayout.CENTER);
        pnThongKeChoose.add(lblThongKe, BorderLayout.CENTER);
        
        lblBanHang.setHorizontalAlignment(JLabel.CENTER);
        lblKhuyenMai.setHorizontalAlignment(JLabel.CENTER);
        lblSanPham.setHorizontalAlignment(JLabel.CENTER);
        lblKhachHang.setHorizontalAlignment(JLabel.CENTER);
        lblNhanVien.setHorizontalAlignment(JLabel.CENTER);
        lblNhapHang.setHorizontalAlignment(JLabel.CENTER);
        lblThongKe.setHorizontalAlignment(JLabel.CENTER);
        
        pnBanHangChoose.setPreferredSize(new Dimension(200, 60));
        pnKhuyenMaiChoose.setPreferredSize(new Dimension(200, 60));
        pnSanPhamChoose.setPreferredSize(new Dimension(200, 60));
        pnKhachHangChoose.setPreferredSize(new Dimension(200, 60));
        pnNhanVienChoose.setPreferredSize(new Dimension(200, 60));
        pnNhapHangChoose.setPreferredSize(new Dimension(200, 60));
        pnThongKeChoose.setPreferredSize(new Dimension(200, 60));
        
        listMenuLeft = new ArrayList<>();
        listMenuLeft.add(pnBanHangChoose);
        listMenuLeft.add(pnKhuyenMaiChoose);
        listMenuLeft.add(pnSanPhamChoose);
        listMenuLeft.add(pnNhanVienChoose);
        listMenuLeft.add(pnKhachHangChoose);
        listMenuLeft.add(pnNhapHangChoose);
        listMenuLeft.add(pnThongKeChoose);
        
        for (JPanel pnl : listMenuLeft) {
            pnl.setBorder(BorderFactory.createLineBorder(Color.white));
            pnl.setVisible(false);
            pnl.setOpaque(true);
            pnl.setBackground(clLeftItem);
            pnl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pnMenuLeft.add(pnl);
        }
        
        lblBanHang.setBackground(clLeftItemSelected);
        lblBanHang.setVisible(true);

        pnMain.add(pnMenuLeft, BorderLayout.WEST);
        
        /*
        ============================================================
                                CARD PANEL           
        ============================================================
         */
        pnCard = new JPanel(cardMenuLeftGroup);

        pnBanHang = new JPanel();
        pnKhuyenMai = new JPanel();
        pnSanPham = new JPanel();
        pnNhanVien = new JPanel();
        pnKhachHang = new JPanel();
        pnNhapHang = new JPanel();
        pnThongKe = new JPanel();

        pnCard.add(pnBanHang, "1");
        pnCard.add(pnKhuyenMai, "2");
        pnCard.add(pnSanPham, "3");
        pnCard.add(pnNhanVien, "4");
        pnCard.add(pnKhachHang, "5");
        pnCard.add(pnNhapHang, "6");
        pnCard.add(pnThongKe, "7");
        
        //==========ADD PANEL BÁN HÀNG (Ko phân quyền)==========
        banHangPanel = new PnQuanLyBanHangGUI();
        pnBanHang.setLayout(new BorderLayout());
        pnBanHang.add(banHangPanel, BorderLayout.CENTER);
        pnBanHangChoose.setVisible(true);
        pnBanHangChoose.setBackground(clLeftItemSelected);

        //======XỬ LÝ PHÂN QUYỀN=======
        PhanQuyen quyen = pqBUS.getNhomQuyen(dnBUS.taiKhoanLogin.getQuyen());


        if(quyen.getQlKhuyenMai() == 1) {
            khuyenMaiPanel = new PnlQLGiamGia();
            pnKhuyenMai.setLayout(new BorderLayout());
            pnKhuyenMai.add(khuyenMaiPanel, BorderLayout.CENTER);
            pnKhuyenMaiChoose.setVisible(true);
        }

        if (quyen.getQlSanPham() == 1) {
            sanPhamPanel = new PnQuanLySanPhamGUI();
            pnSanPham.setLayout(new BorderLayout());
            pnSanPham.add(sanPhamPanel, BorderLayout.CENTER);
            pnSanPhamChoose.setVisible(true);
        }

        if (quyen.getQlNhanVien() == 1) {
            nhanVienPanel = new PnlQuanLyNhanVien();
            pnNhanVien.setLayout(new BorderLayout());
            pnNhanVien.add(nhanVienPanel, BorderLayout.CENTER);
            pnNhanVienChoose.setVisible(true);
        }

        if (quyen.getQlKhachHang() == 1) {
            khachHangPanel = new PnlQuanLyKhachHang();
            pnKhachHang.setLayout(new BorderLayout());
            pnKhachHang.add(khachHangPanel, BorderLayout.CENTER);
            pnKhachHangChoose.setVisible(true);
        }

        if (quyen.getNhapHang()== 1) {
            nhapHangPanel = new PnQuanLyNhapHangGUI();
            pnNhapHang.setLayout(new BorderLayout());
            pnNhapHang.add(nhapHangPanel, BorderLayout.CENTER);
            pnNhapHangChoose.setVisible(true);
        }

        if (quyen.getThongKe() == 1) {
            thongKePanel = new PnlQLThongKe();
            pnThongKe.setLayout(new BorderLayout());
            pnThongKe.add(thongKePanel, BorderLayout.CENTER);
            pnThongKeChoose.setVisible(true);
        }

        pnMain.add(pnCard);
        /*
        ============================================================
                                CARD PANEL           
        ============================================================
         */
        con.add(pnMain);
    }

    int xMouse, yMouse;

    private void addEvents() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                moverFrame(e.getXOnScreen(), e.getYOnScreen());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });

        btnDoiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DlgDoiMatKhau().setVisible(true);
            }
        });

        btnDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DangNhapGUI dnGUI = new DangNhapGUI();
                dnGUI.showWindow();
            }
        });
        

        for (JPanel pnl : listMenuLeft) {
            pnl.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JPanel pnlDisable : listMenuLeft) {
                        pnlDisable.setBackground(clLeftItem);
                    }
                    pnl.setBackground(clLeftItemSelected);

                    // Xử lý lật trang theo menu
                    String cardName = "";
                    if (pnl == pnBanHangChoose) {
                        cardName = "1";
                    } else if (pnl == pnKhuyenMaiChoose) {
                        cardName = "2";
                    } else if (pnl == pnSanPhamChoose) {
                        cardName = "3";
                    } else if (pnl == pnNhanVienChoose) {
                        cardName = "4";
                    } else if (pnl == pnKhachHangChoose) {
                        cardName = "5";
                    } else if (pnl == pnNhapHangChoose) {
                        cardName = "6";
                    } else {
                        cardName = "7";
                    }
                    cardMenuLeftGroup.show(pnCard, cardName);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (pnl.getBackground().equals(clLeftItem)) {
                        pnl.setBackground(clLeftItemHover);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (pnl.getBackground().equals(clLeftItemHover)) {
                        pnl.setBackground(clLeftItem);
                    }
                }
            });
        }
    }

    private void moverFrame(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void thuNhoFrame() {
        this.setState(Frame.ICONIFIED);
    }

    private void thoatChuongTrinh() {
//        banHangPanel.xuLyThoat();
        Main.Main.changLNF("Nimbus");
        System.exit(0);
    }
}
