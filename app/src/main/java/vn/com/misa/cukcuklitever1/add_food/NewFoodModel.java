package vn.com.misa.cukcuklitever1.add_food;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.database.SQLiteFoodDataController;
import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

/**
 * Xử lý dữ liệu trong database
 * Edited by lvhung at 5/30/2019
 */
public class NewFoodModel implements INewFoodContract.IModel {
    private ArrayList<Food> foods;
    private SQLiteFoodDataController mDatabase;
    private Context mContext;

    /**
     * Constructor
     * Edited by lvhung at 5/30/2019
     * @param mContext context để lấy database
     */
    NewFoodModel(Context mContext) {
        if (foods == null)
            foods = new ArrayList<>();
        if (mDatabase == null)
            mDatabase = new SQLiteFoodDataController(mContext);
        this.mContext = mContext;
    }

    @Override
    public double convertStringToDouble(String input) {
        try {
            //Chuyển #.###,## -> ####,## -> ####.##
            String s = input.replace(".","");
            return Double.parseDouble(s.replaceAll(",", "."));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Xử lý các thông tin nhập vào
     * Edited by lvhung at 5/30/2019
     * @param name  tên món
     * @param price giá
     * @param unit  đơn vị tính
     * @param color màu sắc
     * @param icon  biểu tượng
     * @param iAddFinished  interface trả về kết quả
     */
    @Override
    public void onAddNewFood(String name, double price, String unit, String color, String icon, IAddFinished iAddFinished) {
        if (name.isEmpty()){
            iAddFinished.onNameFoodEmpty();
        }else if (unit.isEmpty()){
            iAddFinished.onUnitFoodEmpty();
        }else {
            Food food = new Food(name, price, unit, color, icon,false);
            if (mDatabase.insertFood(food)) {
                Intent in = new Intent(""+mContext.getString(R.string.broadcast_update));
                mContext.sendBroadcast(in);
                iAddFinished.onSuccessful();
            }else {
                iAddFinished.onFail(""+mContext.getString(R.string.edit_fail));
            }
        }
    }
}
