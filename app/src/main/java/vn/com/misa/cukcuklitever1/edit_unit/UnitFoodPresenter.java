package vn.com.misa.cukcuklitever1.edit_unit;

import android.app.Activity;

public class UnitFoodPresenter implements IUnitFoodContract.IPresenter, IUnitFoodContract.IModel.ICheckFinish {
    private IUnitFoodContract.IView mView;
    private IUnitFoodContract.IModel mModel;

    /**
     * Constructor
     * create by lvhung on 6/5/2019
     * @param context context
     * @param mView view
     */
    UnitFoodPresenter(Activity context, IUnitFoodContract.IView mView) {
        this.mView = mView;
        mModel = new UnitFoodModel(context);
    }

    /**
     * Lấy toàn bộ list từ model trả lên view
     * create by lvhung on 6/5/2019
     */
    @Override
    public void getAllData() {
        if (mView!=null)
            mView.showData(mModel.getAllUnit());
    }

    /**
     * Lấy input từ view để xử lý thêm unit
     * create by lvhung on 6/5/2019
     * @param name tên
     */
    @Override
    public void getInputInsert(String name) {
        mModel.insertUnit(name,this);
    }

    /**
     * Lấy input từ view để sửa unit
     * create by lvhung on 6/5/2019
     * @param name tên
     * @param id id
     */
    @Override
    public void getInputEdit(String name,int id) {
        mModel.editUnit(name,id,this);
    }

    /**
     * Xóa đơn vị tính
     * create by lvhung on 6/5/2019
     * @param id id
     */
    @Override
    public void removeUnit(int id) {
        mModel.removeUnit(id,this);
    }

    /**
     * Hủy đăng ký view
     * create by lvhung on 6/5/2019
     */
    @Override
    public void destroyView() {
        if (mView!=null){
            mView = null;
        }
    }

    /**
     * Lỗi tên nhập vào bị trống
     * create by lvhung on 6/5/2019
     */
    @Override
    public void onNameUnitEmpty() {
        if (mView!=null)
            mView.showError("Name's unit is empty!");
    }

    /**
     * Có lỗi xảy ra trong quá trình thêm, sửa xóa
     * create by lvhung on 6/5/2019
     * @param message chi tiết lỗi
     */
    @Override
    public void onFail(String message) {
        if (mView!=null)
            mView.showError(message);
    }

    /**
     * Xử lý thành công thêm ,sửa, xóa
     * create by lvhung on 6/5/2019
     * @param message chi tiết
     */
    @Override
    public void onSuccessful(String message) {
        if (mView != null) {
            mView.onComplete(message);
        }
    }
}
