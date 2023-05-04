package GUI;

import BUS.LoaiBUS;
import BUS.NhaCungCapBUS;
import DTO.LoaiSP;
import Customs.MyDialog;
import Customs.MyTable;
import DTO.NhaCungCap;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class DlgQuanLyNcc extends javax.swing.JDialog {

    DefaultTableModel dtmNcc;

    public DlgQuanLyNcc() {
        initComponents();
        dtmNcc = new DefaultTableModel();
        dtmNcc.addColumn("Mã Nhà Cung Cấp");
        dtmNcc.addColumn("Tên Nhà Cung Cấp");
        dtmNcc.addColumn("Địa Chỉ");
        dtmNcc.addColumn("Điện Thoại");
        tblNcc.setModel(dtmNcc);
        loadDataLenTblNcc();
        this.setLocationRelativeTo(null);
    }

    NhaCungCapBUS nccBUS = new NhaCungCapBUS();

    private void loadDataLenTblNcc() {
        dtmNcc.setRowCount(0);
        nccBUS.docDanhSach();
        ArrayList<NhaCungCap> dsncc = nccBUS.getListNhaCungCap();
        if (dsncc != null) {
            for (NhaCungCap ncc : dsncc) {
                Vector vec = new Vector();
                vec.add(ncc.getMaNCC());
                vec.add(ncc.getTenNCC());
                vec.add(ncc.getDiaChi());
                vec.add(ncc.getSoDienThoai());
                dtmNcc.addRow(vec);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNcc = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        pnMaLoai = new javax.swing.JPanel();
        lblMaNcc = new javax.swing.JLabel();
        txtMaNcc = new javax.swing.JTextField();
        pnTenLoai = new javax.swing.JPanel();
        lblTenNcc = new javax.swing.JLabel();
        txtTenNcc = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        pnMaLoai1 = new javax.swing.JPanel();
        lblDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        pnTenLoai1 = new javax.swing.JPanel();
        lblDienThoai = new javax.swing.JLabel();
        txtDienThoai = new javax.swing.JTextField();
        pnButton = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setModal(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ NHÀ CUNG CẤP");
        jPanel1.add(jLabel1);

        tblNcc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblNcc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Địa Chỉ", "Điệnv Thoại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNcc.getTableHeader().setReorderingAllowed(false);
        tblNcc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNccMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNcc);

        javax.swing.GroupLayout pnTableLayout = new javax.swing.GroupLayout(pnTable);
        pnTable.setLayout(pnTableLayout);
        pnTableLayout.setHorizontalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pnTableLayout.setVerticalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTableLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        lblMaNcc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMaNcc.setText("Mã nhà cung cấp");
        lblMaNcc.setPreferredSize(new java.awt.Dimension(63, 22));

        txtMaNcc.setEditable(false);
        txtMaNcc.setColumns(15);
        txtMaNcc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnMaLoaiLayout = new javax.swing.GroupLayout(pnMaLoai);
        pnMaLoai.setLayout(pnMaLoaiLayout);
        pnMaLoaiLayout.setHorizontalGroup(
            pnMaLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMaLoaiLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblMaNcc, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMaNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnMaLoaiLayout.setVerticalGroup(
            pnMaLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMaLoaiLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnMaLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.add(pnMaLoai);

        lblTenNcc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTenNcc.setText("Tên nhà cung cấp");

        txtTenNcc.setColumns(15);
        txtTenNcc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenNcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNccActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTenLoaiLayout = new javax.swing.GroupLayout(pnTenLoai);
        pnTenLoai.setLayout(pnTenLoaiLayout);
        pnTenLoaiLayout.setHorizontalGroup(
            pnTenLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTenLoaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTenNcc)
                .addGap(18, 18, 18)
                .addComponent(txtTenNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnTenLoaiLayout.setVerticalGroup(
            pnTenLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTenLoaiLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnTenLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenNcc)))
        );

        jPanel3.add(pnTenLoai);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        lblDiaChi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDiaChi.setText("Địa Chỉ");
        lblDiaChi.setPreferredSize(new java.awt.Dimension(63, 22));

        txtDiaChi.setColumns(15);
        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnMaLoai1Layout = new javax.swing.GroupLayout(pnMaLoai1);
        pnMaLoai1.setLayout(pnMaLoai1Layout);
        pnMaLoai1Layout.setHorizontalGroup(
            pnMaLoai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMaLoai1Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        pnMaLoai1Layout.setVerticalGroup(
            pnMaLoai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMaLoai1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnMaLoai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.add(pnMaLoai1);

        lblDienThoai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDienThoai.setText("Điện Thoại");

        txtDienThoai.setColumns(15);
        txtDienThoai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnTenLoai1Layout = new javax.swing.GroupLayout(pnTenLoai1);
        pnTenLoai1.setLayout(pnTenLoai1Layout);
        pnTenLoai1Layout.setHorizontalGroup(
            pnTenLoai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTenLoai1Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(lblDienThoai)
                .addGap(27, 27, 27)
                .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        pnTenLoai1Layout.setVerticalGroup(
            pnTenLoai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTenLoai1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnTenLoai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDienThoai)))
        );

        jPanel4.add(pnTenLoai1);

        jPanel3.add(jPanel4);

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setPreferredSize(new java.awt.Dimension(80, 35));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnButton.add(btnThem);

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setPreferredSize(new java.awt.Dimension(80, 35));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnButton.add(btnSua);

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setPreferredSize(new java.awt.Dimension(80, 35));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnButton.add(btnXoa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            .addComponent(pnTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblNccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNccMouseClicked
        int row = tblNcc.getSelectedRow();
        if (row > -1) {
            String maLoai = tblNcc.getValueAt(row, 0) + "";
            String tenLoai = tblNcc.getValueAt(row, 1) + "";
            String diachi = tblNcc.getValueAt(row, 2) + "";
            String dienthoai = tblNcc.getValueAt(row, 3) + "";
            txtMaNcc.setText(maLoai);
            txtTenNcc.setText(tenLoai);
            txtDiaChi.setText(diachi);
            txtDienThoai.setText(dienthoai);
        }
    }//GEN-LAST:event_tblNccMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (nccBUS.themNhaCungCap(txtTenNcc.getText(), txtDiaChi.getText(), txtDienThoai.getText())) {
            loadDataLenTblNcc();
            txtDiaChi.setText("");
            txtDienThoai.setText("");
            txtTenNcc.setText("");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            String ma = txtMaNcc.getText();
            if (nccBUS.xoaNhaCungCap(ma)) {
                loadDataLenTblNcc();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String ma = txtMaNcc.getText();
        String ten = txtTenNcc.getText();
        String diachi = txtDiaChi.getText();
        String dienthoai = txtDienThoai.getText();
        if (nccBUS.suaNhaCungCap(ma, ten, diachi, dienthoai)) {
            loadDataLenTblNcc();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtTenNccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNccActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDienThoai;
    private javax.swing.JLabel lblMaNcc;
    private javax.swing.JLabel lblTenNcc;
    private javax.swing.JPanel pnButton;
    private javax.swing.JPanel pnMaLoai;
    private javax.swing.JPanel pnMaLoai1;
    private javax.swing.JPanel pnTable;
    private javax.swing.JPanel pnTenLoai;
    private javax.swing.JPanel pnTenLoai1;
    private javax.swing.JTable tblNcc;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtMaNcc;
    private javax.swing.JTextField txtTenNcc;
    // End of variables declaration//GEN-END:variables
}
