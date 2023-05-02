package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCap;
import Customs.MyDialog;
import DAO.MyConnect;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NhaCungCapBUS {

    private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    private ArrayList<NhaCungCap> listNhaCungCap = null;

    public NhaCungCapBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listNhaCungCap = nhaCungCapDAO.getListNhaCungCap();
    }

    public ArrayList<NhaCungCap> getListNhaCungCap() {
        if (this.listNhaCungCap == null) {
            docDanhSach();
        }
        return this.listNhaCungCap;
    }

    public String gettennhacungcap(int ma)
    {
        for(NhaCungCap i : listNhaCungCap)
        {
            if(i.getMaNCC()==ma)
                return i.getTenNCC();
        }
        return "";
    }
    
    public boolean xoaNhaCungCap(String ma)
    {
        if (ma.trim().equals("")) {
            new MyDialog("Chưa chọn nhà cung cấp để xoá!", MyDialog.SUCCESS_DIALOG);
            return false;
        }
        int maNcc = Integer.parseInt(ma);
        if (nhaCungCapDAO.deleteNCC(maNcc)) {
            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialog("Xoá thất bại! Loại có sản phẩm con", MyDialog.ERROR_DIALOG);
            return false;
        }
    }
    
    public boolean themNhaCungCap(String tenNCC, String diaChi, String dienThoai) {
        if (tenNCC.trim().equals("")) {
            new MyDialog("Hãy nhập tên Nhà cung cấp này!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (diaChi.trim().equals("")) {
            new MyDialog("Hãy nhập địa chỉ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d{10}$");
        if (!pattern.matcher(dienThoai).matches()) {
            new MyDialog("Hãy nhập số điện thoại hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }

        NhaCungCap ncc = new NhaCungCap();
        ncc.setTenNCC(tenNCC);
        ncc.setDiaChi(diaChi);
        ncc.setSoDienThoai(dienThoai);
        boolean flag = nhaCungCapDAO.addNCC(ncc);
        if (flag) {
            new MyDialog("Thêm mới thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm mới thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean suaNhaCungCap(String maNCC, String tenNCC, String diaChi, String dienThoai) {
        if (tenNCC.trim().equals("")) {
            new MyDialog("Hãy nhập tên Nhà cung cấp này!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (diaChi.trim().equals("")) {
            new MyDialog("Hãy nhập địa chỉ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d{10}$");
        if (!pattern.matcher(dienThoai).matches()) {
            new MyDialog("Hãy nhập số điện thoại hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }

        int ma = Integer.parseInt(maNCC);

        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNCC(ma);
        ncc.setTenNCC(tenNCC);
        ncc.setDiaChi(diaChi);
        ncc.setSoDienThoai(dienThoai);
        
        boolean flag = nhaCungCapDAO.updateNCC(ncc);

        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public static void main(String[] args) {
        new MyConnect();
        NhaCungCapBUS nccbus = new NhaCungCapBUS();
        ArrayList<NhaCungCap> dsncc = new ArrayList<>();
        dsncc = nccbus.getListNhaCungCap();
        for(NhaCungCap i : dsncc)
        {
            System.out.println(i.getMaNCC() + " " +i.getTenNCC() + " " + i.getDiaChi() + " " + i.getSoDienThoai());
        }
    }
}
