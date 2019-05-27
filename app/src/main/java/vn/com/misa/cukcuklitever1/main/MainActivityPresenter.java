package vn.com.misa.cukcuklitever1.main;

import android.app.Activity;

import vn.com.misa.cukcuklitever1.R;

/**
 * create by lvhung on 5/24/2019
 */
public class MainActivityPresenter implements IMainActivityContract.Presenter {
    private IMainActivityContract.View mView;

    /**
     * constructor
     * @param mView view
     */
    public MainActivityPresenter(IMainActivityContract.View mView) {
        this.mView = mView;
    }

    /**
     * người dùng chọn thực đơn trong navigation drawer
     */
    @Override
    public void onMenuSelected(Activity mActivity) {
        mView.showMenuFragment(""+mActivity.getString(R.string.menu_nav));
        mView.closeNavDrawer();
    }
}
