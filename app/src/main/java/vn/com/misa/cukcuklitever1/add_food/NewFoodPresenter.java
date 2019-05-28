package vn.com.misa.cukcuklitever1.add_food;

import android.app.Activity;
import android.content.Intent;

public class NewFoodPresenter implements INewFoodContract.IPresenter,INewFoodContract.IModel.IAddFinished {
    private INewFoodContract.IModel mModel;
    private INewFoodContract.IView mView;
    private Activity mContext;

    /**
     * Constructor
     * @param mContext context để xử lý database
     * @param mView view hiển thị khi xử lý xong
     */
    public NewFoodPresenter(Activity mContext,INewFoodContract.IView mView) {
        this.mView = mView;
        this.mContext = mContext;
        mModel=new NewFoodModel(mContext);
    }

    /**
     * Gửi thông tin người dùng nhập vào đến tầng model để xử lý
     * @param name  tên món
     * @param price giá
     * @param unit  đơn vị tính
     * @param color màu sắc
     * @param icon  biểu tượng
     */
    @Override
    public void checkInput(String name, int price, String unit, String color, String icon) {
        if (mView!=null){
            mModel.onAddNewFood(name,price,unit,color,icon,this);
        }
    }

    /**
     * Hủy view khi activity bị hủy
     */
    @Override
    public void onDestroy() {
        if (mView!=null)
            mView=null;
    }

    /**
     * Báo lỗi nếu  tên món trống
     */
    @Override
    public void onNameFoodEmpty() {
        mView.nameFoodError();
    }

    /**
     * Báo lỗi nếu đơn vị tính trống
     */
    @Override
    public void onUnitFoodEmpty() {
        mView.unitFoodError();
    }

    /**
     * Thêm thất bại
     * @param message nội dung
     */
    @Override
    public void onFail(String message) {
        mView.addFoodFail(message);
    }

    /**
     * Gửi 1 broadcast thông báo cập nhật listview thực đơn
     * và thông báo thêm thành công
     */
    @Override
    public void onSuccessful() {
        Intent intent = new Intent("UPDATE_LIST");
        mContext.sendBroadcast(intent);
        mView.addSuccessful();
    }
}
