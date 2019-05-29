package vn.com.misa.cukcuklitever1.menu_cook;

import android.content.Context;

import java.util.ArrayList;

import vn.com.misa.cukcuklitever1.database.SQLiteFoodDataController;
import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

/**
 * Thao tác với database
 * create by lvhung on 5/29/2019
 */
public class MenuFoodModel implements IMenuFoodContract.Model {
    private ArrayList<Food> foods;
    private SQLiteFoodDataController mDatabase;

    MenuFoodModel(Context mContext) {
        if (foods == null)
            foods = new ArrayList<>();
        if (mDatabase == null)
            mDatabase = new SQLiteFoodDataController(mContext);
    }

    /**
     * Lấy toàn bộ list trong csdl
     * create by lvhung on 5/29/2019
     * @return list food
     */
    @Override
    public ArrayList<Food> getFood() {
        foods = mDatabase.getAllFood();
        return foods;
    }
}
