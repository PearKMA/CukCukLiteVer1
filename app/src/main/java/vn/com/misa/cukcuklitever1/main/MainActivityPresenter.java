package vn.com.misa.cukcuklitever1.main;

import android.app.Activity;

import vn.com.misa.cukcuklitever1.R;

/**
 * xử lý và hiển thị lên view
 * create by lvhung on 5/24/2019
 */
public class MainActivityPresenter implements IMainActivityContract.Presenter {
    private IMainActivityContract.View mView;

    /**
     * constructor
     * create by lvhung on 5/29/2019
     * @param mView view
     */
    public MainActivityPresenter(IMainActivityContract.View mView) {
        this.mView = mView;
    }

    /**
     * người dùng chọn thực đơn trong navigation drawer
     * create by lvhung on 5/29/2019
     */
    @Override
    public void onMenuSelected(Activity mActivity) {
        mView.showMenuFragment(""+mActivity.getString(R.string.menu_nav));
        mView.closeNavDrawer();
    }
}
