package vn.com.misa.cukcuklitever1.add_food;

import android.content.Context;

import java.util.ArrayList;

import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.add_food.entity.Unit;
import vn.com.misa.cukcuklitever1.database.SQLiteFoodDataController;
import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

public class NewFoodModel implements INewFoodContract.IModel {
    private ArrayList<Food> foods;
    private SQLiteFoodDataController mDatabase;
    private Context mContext;

    /**
     * Constructor
     * @param mContext context để lấy database
     */
    NewFoodModel(Context mContext) {
        if (foods == null)
            foods = new ArrayList<>();
        if (mDatabase == null)
            mDatabase = new SQLiteFoodDataController(mContext);
        this.mContext = mContext;
    }

    /**
     * Xử lý các thông tin nhập vào
     * @param name  tên món
     * @param price giá
     * @param unit  đơn vị tính
     * @param color màu sắc
     * @param icon  biểu tượng
     * @param iAddFinished  interface trả về kết quả
     */
    @Override
    public void onAddNewFood(String name, int price, String unit, String color, String icon, IAddFinished iAddFinished) {
        if (name.isEmpty()){
            iAddFinished.onNameFoodEmpty();
        }else if (unit.isEmpty()){
            iAddFinished.onUnitFoodEmpty();
        }else {
            Food food = new Food(name, price, unit, color, icon,false);
            if (mDatabase.insertFood(food)) {
                iAddFinished.onSuccessful();
            }else {
                iAddFinished.onFail(""+mContext.getString(R.string.edit_fail));
            }
        }
    }
}
