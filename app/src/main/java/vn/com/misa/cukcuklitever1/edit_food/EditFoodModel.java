package vn.com.misa.cukcuklitever1.edit_food;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.database.SQLiteFoodDataController;
import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

/**
 * Xử lý thêm /sửa trong database
 * create by lvhung on 5/29/2019
 */
public class EditFoodModel implements IEditFoodContract.IModel {
    private SQLiteFoodDataController mDatabase;
    private Context mContext;

    /**
     * constructor
     * @param mContext context
     */
    EditFoodModel(Activity mContext) {
        if (mDatabase == null)
            mDatabase = new SQLiteFoodDataController(mContext);
        this.mContext = mContext;
    }

    /**
     * Xóa món trong database
     * @param id    id món cần xóa
     * @param iEditFinish   trả về kết quả xóa
     */
    @Override
    public void deleteFood(int id,IEditFinish iEditFinish) {
        if (mDatabase.removeFood(id)) {
            Intent intent = new Intent(""+mContext.getString(R.string.broadcast_update));
            mContext.sendBroadcast(intent);
            iEditFinish.onSuccessful("");
        }
        else
            iEditFinish.onFail(""+mContext.getString(R.string.remove_fail));
    }

    /**
     * Chỉnh sửa món trong database
     * @param id    id của món
     * @param name  tên món
     * @param price giá
     * @param unit  đơn vị tính
     * @param color màu sắc
     * @param icon  biểu tượng
     * @param status    tình trạng bán
     * @param iEditFinish   trả kết quả chỉnh sửa
     */
    @Override
    public void editFood(int id,String name, double price, String unit, String color, String icon, boolean status, IEditFinish iEditFinish) {
        if (name.isEmpty()){
            iEditFinish.onNameFoodEmpty();
        }else if (unit.isEmpty()){
            iEditFinish.onUnitFoodEmpty();
        }else {
            Food food = new Food(name, price, unit, color, icon,status);
            if (mDatabase.editFood(food,id)) {
                Intent intent = new Intent(""+mContext.getString(R.string.broadcast_update));
                mContext.sendBroadcast(intent);
                iEditFinish.onSuccessful("");
            }else {
                iEditFinish.onFail(""+mContext.getString(R.string.edit_fail));
            }
        }
    }
}
