package vn.com.misa.cukcuklitever1.main_activity;

/**
 * create by lvhung on 5/24/2019
 */
public interface IMainActivity {
    interface Presenter{
        void onMenuSelected();
    }
    interface View{
        void closeNavDrawer();
        void showMenuFragment();
    }
}
