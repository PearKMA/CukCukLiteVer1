package vn.com.misa.cukcuklitever1.main;

/**
 * create by lvhung on 5/24/2019
 */
public class MainActivityPresenter implements IMainActivity.Presenter {
    private IMainActivity.View mView;

    public MainActivityPresenter(IMainActivity.View mView) {
        this.mView = mView;
    }

    /**
     * người dùng chọn thực đơn trong navigation drawer
     */
    @Override
    public void onMenuSelected() {
        mView.showMenuFragment();
        mView.closeNavDrawer();
    }
}
