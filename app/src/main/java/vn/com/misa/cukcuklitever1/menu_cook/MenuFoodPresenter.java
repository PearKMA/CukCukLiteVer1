package vn.com.misa.cukcuklitever1.menu_cook;

import android.app.Activity;
import android.content.Context;

import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

public class MenuFoodPresenter implements IMenuFoodContract.Presenter {
    private IMenuFoodContract.View mView;
    private IMenuFoodContract.Model mModel;

    public MenuFoodPresenter(Activity context, IMenuFoodContract.View mView) {
        this.mView = mView;
        mModel = new MenuFoodModel(context);
    }

    /**
     * hiển thị toàn bộ dữ liệu trong list lên màn hình
     *
     * @param context
     */
    @Override
    public void setFoodData(Context context) {
        if (mView != null)
            mView.showData(mModel.getFood());
    }

    /**
     * Thêm một món
     */
    @Override
    public void addFood() {
        if (mView != null)
            mView.showAddFood();
    }

    /**
     * Sửa món
     *
     * @param food
     */
    @Override
    public void editFood(Food food) {
        if (mView != null)
            mView.showEditFood(food);
    }

    @Override
    public void destroyActivity() {
        mView = null;
    }

}
