package vn.com.misa.cukcuklitever1.edit_unit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import java.util.ArrayList;

import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.database.SQLiteFoodDataController;
import vn.com.misa.cukcuklitever1.edit_unit.entity.Unit;
import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

public class UnitFoodModel implements IUnitFoodContract.IModel {
    private ArrayList<Unit> units;
    private SQLiteFoodDataController mDatabase;
    private Context mContext;

    UnitFoodModel(Activity mContext) {
        this.mContext = mContext;
        if (mDatabase == null)
            mDatabase = new SQLiteFoodDataController(mContext);
    }

    @Override
    public ArrayList<Unit> getAllUnit() {
        return mDatabase.getAllUnit();
    }

    @Override
    public void editUnit(String name, int id, ICheckFinish iCheckFinish) {
        if (name.isEmpty()) {
            iCheckFinish.onNameUnitEmpty();
        } else {
            if (mDatabase.editUnit(new Unit(name), id)) {
                iCheckFinish.onSuccessful(mContext.getString(R.string.edit_successful));
                Intent intent = new Intent(mContext.getString(R.string.broadcast_update));
                mContext.sendBroadcast(intent);
            } else {
                iCheckFinish.onFail("Error when edit. Try again!");
            }
        }
    }

    @Override
    public void removeUnit(final int id, final ICheckFinish iCheckFinish) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ArrayList<Food> foods = mDatabase.getAllFood();
                units = mDatabase.getAllUnit();
                boolean check = false;
                String name = "";
                for (Food food : foods) {
                    for (Unit unit : units){
                        if (unit.getUnit().equalsIgnoreCase(food.getUnit())){
                            check = true;
                            name = unit.getUnit();
                            break;
                        }
                    }
                }
                if (check){
                    iCheckFinish.onFail(mContext.getString(R.string.unit_duplicate,name));
                }else {
                    mDatabase.removeUnit(id);
                    iCheckFinish.onSuccessful(mContext.getString(R.string.remove_successful));
                }
            }
        });
    }

    @Override
    public void insertUnit(final String name, final ICheckFinish iCheckFinish) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (name.isEmpty()){
                    iCheckFinish.onNameUnitEmpty();
                }else {
                    ArrayList<Food> foods = mDatabase.getAllFood();
                    units = mDatabase.getAllUnit();
                    boolean check = false;
                    String name = "";
                    for (Food food : foods) {
                        for (Unit unit : units) {
                            if (unit.getUnit().equalsIgnoreCase(food.getUnit())) {
                                check = true;
                                name = unit.getUnit();
                                break;
                            }
                        }
                    }
                    if (check) {
                        iCheckFinish.onFail(mContext.getString(R.string.unit_duplicate, name));
                    } else {
                        mDatabase.insertUnit(new Unit(name));
                        iCheckFinish.onSuccessful(mContext.getString(R.string.add_successfull));
                    }
                }
            }
        });
    }
}
