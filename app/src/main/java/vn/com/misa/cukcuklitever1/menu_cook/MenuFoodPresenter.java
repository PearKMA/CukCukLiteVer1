package vn.com.misa.cukcuklitever1.menu_cook;

import android.content.Context;

import vn.com.misa.cukcuklitever1.main.IMainActivity;

public class MenuFoodPresenter implements IMenuFoodContract.Presenter {
    private IMenuFoodContract.View mView;
    private IMenuFoodContract.Model mModel;

    public MenuFoodPresenter(IMenuFoodContract.View mView) {
        this.mView = mView;
        mModel=new MenuFoodModel((Context) mView);
    }

    @Override
    public void setFoodData(Context context) {
        mView.showData(mModel.getFood());
    }

    @Override
    public void addFood() {
        mView.showAddFood();
    }

    @Override
    public void editFood() {
        mView.showEditFood();
    }

}
