package vn.com.misa.cukcuklitever1.edit_food;

import android.app.Activity;
import android.content.Intent;

/**
 * Xử lý giữa thông tin chỉnh sửa với database
 * create by lvhung on 5/29/2019
 */
public class EditFoodPresenter implements IEditFoodContract.IPresenter, IEditFoodContract.IModel.IEditFinish {
    private IEditFoodContract.IView mView;
    private IEditFoodContract.IModel mModel;
    EditFoodPresenter(Activity mContext, IEditFoodContract.IView mView) {
        this.mView = mView;
        mModel = new EditFoodModel(mContext);
    }

    /**
     * tên món bị trống
     */
    @Override
    public void onNameFoodEmpty() {
        if (mView != null)
            mView.nameFoodError();
    }

    /**
     * Đơn vị tính có lỗi
     */
    @Override
    public void onUnitFoodEmpty() {
        if (mView != null)
            mView.unitFoodError();
    }

    /**
     * Sửa hoặc xóa bị lỗi
     * @param message chi tiết lỗi
     */
    @Override
    public void onFail(String message) {
        if (mView != null)
            mView.onFail(message);
    }

    /**
     * Sửa hoặc xóa thành công
     * @param message chi tiết tin
     */
    @Override
    public void onSuccessful(String message) {
        mView.onSucessful("" + message);
    }

    /**
     * Kiểm tra đầu vào để chỉnh sửa trong databasr
     * @param name tên món
     * @param price giá
     * @param unit  đơn vị tính
     * @param color màu sắc
     * @param icon  biểu tươgnj
     * @param status    tình trạng
     */
    @Override
    public void checkInput(int id,String name, int price, String unit, String color, String icon, boolean status) {
        mModel.editFood(id,name,price,unit,color,icon,status,this);
    }


    /**
     * Xóa món
     * @param id khóa của món
     */
    @Override
    public void deleteFood(int id) {
        mModel.deleteFood(id,this);
    }

    /**
     * Hủy view
     */
    @Override
    public void onDestroyPresenter() {
        if (mView != null)
            mView = null;
    }
}
