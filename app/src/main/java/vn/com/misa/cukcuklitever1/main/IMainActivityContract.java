package vn.com.misa.cukcuklitever1.main;

import android.app.Activity;

/**
 * create by lvhung on 5/24/2019
 */
public interface IMainActivityContract {
    /**
     * xử lý khi user chọn menu trong navigation drawer
     */
    interface Presenter{
        void onMenuSelected(Activity mActivity);
    }

    /**
     * hiện chức năng user chọn và đóng navigation drawer
     */
    interface View{
        void closeNavDrawer();
        void showMenuFragment(String title);
    }
}
