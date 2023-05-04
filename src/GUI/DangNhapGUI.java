
package GUI;
import BUS.DangNhapBUS;
import DAO.MyConnect;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DangNhapGUI extends JFrame{
    private JLabel lblTitle, lblTK, lblMK, lblString, lblDK;
    private JTextField txtTK;
    private JPasswordField txtMK;
    private JButton btnLogin;
    private JToggleButton TbtnMK;
    private String placeholderTK = "Nhập tên đăng nhập...";
    private String placeholderMK = "Nhập mật khẩu...";
    private DangNhapBUS dnBUS = new DangNhapBUS();
    public DangNhapGUI() {
        addControls();
        addEvents();
    }
    
    private void addControls() {
        setTitle("");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        Font font = new Font("", Font.BOLD, 14);
        Font dkFont = new Font("", Font.ITALIC, 16);
        Font btnFont = new Font("", Font.BOLD, 18);
        Font titleFont = new Font("", Font.BOLD, 32);
        
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        
        GridBagConstraints cons = new GridBagConstraints();
        
        lblTitle = new JLabel("ĐĂNG NHẬP");
        lblTitle.setFont(titleFont);
        
        lblTK = new JLabel("Tên đăng nhập");
        lblTK.setFont(btnFont);
        
        lblMK = new JLabel("Mật khẩu");
        lblMK.setFont(btnFont);

        
        txtTK = new JTextField(20);
        txtTK.setForeground(Color.GRAY);
        txtTK.setText(placeholderTK);
        txtTK.setFont(font);
        
        txtMK = new JPasswordField(20);
        txtMK.setForeground(Color.GRAY);
        txtMK.setText(placeholderMK);
        txtMK.setFont(font);
        txtMK.setEchoChar('*');
        
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(btnFont);
        btnLogin.setCursor(cursor);
        
        TbtnMK = new JToggleButton("Show/Hide");
        
        
        
        // Sắp xếp phần tử
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 3;
        cons.insets = new Insets(20, 10, 20, 10);
        cons.anchor = GridBagConstraints.CENTER;
        add(lblTitle, cons);
        
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridwidth = 1;
        cons.anchor = GridBagConstraints.WEST;
        add(lblTK, cons);
        
        cons.gridx = 1;
        cons.gridy = 1;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.BOTH;
        add(txtTK, cons);
        
        cons.gridx = 0;
        cons.gridy = 2;
        cons.gridwidth = 1;
        add(lblMK, cons);
        
        cons.gridx = 1;
        cons.gridy = 2;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.BOTH;
        add(txtMK, cons);
        
        cons.gridx = 2;
        cons.gridy = 2;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.NONE;
        add(TbtnMK, cons);
        
        cons.gridx = 0;
        cons.gridy = 3;
        cons.gridwidth = 3;
        cons.anchor = GridBagConstraints.CENTER;
        cons.fill = GridBagConstraints.NONE;
        add(btnLogin, cons);
        
        
        setVisible(true);
        setSize(550, 375);
//        setResizable(false);
    }
    
    private void addEvents() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tk = txtTK.getText().trim();
                String mk = new String(txtMK.getPassword());
                if(tk.equals("") || tk.equals(placeholderTK)) {
                    JOptionPane.showMessageDialog(rootPane, "Không được để trống tên đăng nhập!");
                    txtTK.requestFocus();
                } else if(dnBUS.kiemTraKhoanTrang(tk)) {
                    JOptionPane.showMessageDialog(rootPane, "Tài khoản không được phép có khoảng trắng!");
                    txtTK.requestFocus();
                    txtMK.setText(placeholderMK);
                    txtMK.setForeground(Color.GRAY);
                } else if(mk.equals("") || mk.equals(placeholderMK)) {
                    JOptionPane.showMessageDialog(rootPane, "Không được để trống mật khẩu!");
                    txtMK.requestFocus();
                } else if(!dnBUS.kiemTraTaiKhoan(tk)) {
                    JOptionPane.showMessageDialog(rootPane, "Tài khoản sai. Vui lòng nhập lại!");
                    txtTK.requestFocus();
                    txtMK.setText(placeholderMK);
                    txtMK.setForeground(Color.GRAY);
                } else if(dnBUS.kiemTraTaiKhoan(tk)) {
                    if(dnBUS.kiemTraDangNhap(tk, mk)) {
                         JOptionPane.showMessageDialog(rootPane, "Đăng nhập thành công!");
                         dnBUS.taiKhoanLogin = dnBUS.getTaiKhoan(tk, mk);
                         dispose();
                         MainQuanLyGUI GUI = new MainQuanLyGUI();
                         GUI.showWindow();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Mật khẩu sai, vui lòng nhập lại!");
                        txtMK.requestFocus();
                    }
                }
            }
        });
 
        TbtnMK.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txtMK.setEchoChar((char) 0);
                } else {
                    txtMK.setEchoChar('*');
                }
            }
        });
        
        txtTK.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(txtTK.getText().equals(placeholderTK)) {
                    txtTK.setText("");
                    txtTK.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                    if(txtTK.getText().trim().equals("")) {
                        txtTK.setText(placeholderTK);
                        txtTK.setForeground(Color.GRAY);
                    }
            }
        });
        
        txtMK.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(txtMK.getText().equals(placeholderMK)) {
                    txtMK.setText("");
                    txtMK.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                    if(txtMK.getText().trim().equals("")) {
                        txtMK.setText(placeholderMK);
                        txtMK.setForeground(Color.GRAY);
                    }
            }
        });
    }
    public void showWindow() {
        this.setVisible(true);
    }
}
