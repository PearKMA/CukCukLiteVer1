package vn.com.misa.cukcuklitever1.edit_food;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.database.SQLiteFoodDataController;
import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

public class EditFoodModel implements IEditFoodContract.IModel {
    private ArrayList<Food> foods;
    private SQLiteFoodDataController mDatabase;
    private Context mContext;
    public EditFoodModel(Activity mContext) {
        if (foods == null)
            foods = new ArrayList<>();
        if (mDatabase == null)
            mDatabase = new SQLiteFoodDataController(mContext);
        this.mContext = mContext;
    }

    @Override
    public void deleteFood(int id,IEditFinish iEditFinish) {
        if (mDatabase.removeFood(id))
            iEditFinish.onSuccessful("");
        else
            iEditFinish.onFail(""+mContext.getString(R.string.remove_fail));
    }

    @Override
    public void editFood(int id,String name, int price, String unit, String color, String icon, boolean status, IEditFinish iEditFinish) {
        if (name.isEmpty()){
            iEditFinish.onNameFoodEmpty();
        }else if (unit.isEmpty()){
            iEditFinish.onUnitFoodEmpty();
        }else {
            Food food = new Food(name, price, unit, color, icon,status);
            if (mDatabase.editFood(food,id)) {
                iEditFinish.onSuccessful(""+mContext.getString(R.string.edit_fail));
            }else {
                iEditFinish.onFail("");
            }
        }
    }
}
