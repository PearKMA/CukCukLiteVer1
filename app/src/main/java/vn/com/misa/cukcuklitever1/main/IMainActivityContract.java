package vn.com.misa.cukcuklitever1.main;

import android.app.Activity;

/**
 * create by lvhung on 5/24/2019
 */
public interface IMainActivityContract {
    /**
     * xử lý khi user chọn menu trong navigation drawer
     * create by lvhung on 5/29/2019
     */
    interface Presenter{
        void onMenuSelected(Activity mActivity);    //xử lý menu thực đơn được chọn
    }

    /**
     * hiện chức năng user chọn và đóng navigation drawer
     * create by lvhung on 5/29/2019
     */
    interface View{
        void closeNavDrawer();
        void showMenuFragment(String title);
    }
}
