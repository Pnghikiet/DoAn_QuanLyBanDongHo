package Main;

import DAO.MyConnect;
import GUI.DangNhapGUI;

public class Main {

    public static void main(String[] args) {
        new MyConnect();
        DangNhapGUI login = new DangNhapGUI();
        login.showWindow();
    }

    public static void changLNF(String nameLNF) {

    }
}
