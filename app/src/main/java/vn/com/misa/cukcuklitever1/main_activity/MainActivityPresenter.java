package vn.com.misa.cukcuklitever1.main_activity;

/**
 * create by lvhung on 5/24/2019
 */
public class MainActivityPresenter implements IMainActivity.Presenter {
    private MainActivity mView;

    public MainActivityPresenter(MainActivity mView) {
        this.mView = mView;
    }

    @Override
    public void onMenuSelected() {
        mView.showMenuFragment();
        mView.closeNavDrawer();
    }
}
