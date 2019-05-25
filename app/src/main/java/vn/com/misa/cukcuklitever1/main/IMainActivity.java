package vn.com.misa.cukcuklitever1.main;

/**
 * create by lvhung on 5/24/2019
 */
public interface IMainActivity {
    /**
     * xử lý khi user chọn menu trong navigation drawer
     */
    interface Presenter{
        void onMenuSelected();
    }

    /**
     * hiện chức năng user chọn và đóng navigation drawer
     */
    interface View{
        void closeNavDrawer();
        void showMenuFragment();
    }
}
