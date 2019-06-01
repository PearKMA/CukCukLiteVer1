package vn.com.misa.cukcuklitever1.menu_cook;

import android.app.Activity;
import android.content.Context;

import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

/**
 * Thao tác với model để trả kết quả lên view
 * create by lvhung on 5/29/2019
 */
public class MenuFoodPresenter implements IMenuFoodContract.Presenter {
    private IMenuFoodContract.View mView;
    private IMenuFoodContract.Model mModel;

    MenuFoodPresenter(Activity context, IMenuFoodContract.View mView) {
        this.mView = mView;
        mModel = new MenuFoodModel(context);
    }

    /**
     * hiển thị toàn bộ dữ liệu trong list lên màn hình
     *create by lvhung on 5/29/2019
     */
    @Override
    public void setFoodData() {
        if (mView != null)
            mView.showData(mModel.getFood());
    }

    /**
     * Thêm một món
     * create by lvhung on 5/29/2019
     */
    @Override
    public void addFood() {
        if (mView != null)
            mView.showAddFood();
    }

    /**
     * Sửa món
     * create by lvhung on 5/29/2019
     * @param food món được sửa
     */
    @Override
    public void editFood(Food food) {
        if (mView != null)
            mView.showEditFood(food);
    }

    /**
     * create by lvhung on 5/29/2019
     * Hủy view khi activity bị hủy
     */
    @Override
    public void destroyActivity() {
        mView = null;
    }

}
