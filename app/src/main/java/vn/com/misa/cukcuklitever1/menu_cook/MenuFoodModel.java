package vn.com.misa.cukcuklitever1.menu_cook;

import android.content.Context;

import java.util.ArrayList;

import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.menu_cook.database.SQLiteFoodDataController;
import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

public class MenuFoodModel implements IMenuFoodContract.Model {
    private ArrayList<Food> foods;
    private SQLiteFoodDataController mDatabase;
    private Context mContext;
    public MenuFoodModel(Context mContext) {
        if (foods == null)
            foods = new ArrayList<>();
        if (mDatabase == null)
            mDatabase = new SQLiteFoodDataController(mContext);
        this.mContext = mContext;
    }

    @Override
    public ArrayList<Food> getFood() {
        foods = mDatabase.getAllFood();
        return foods;
    }
}