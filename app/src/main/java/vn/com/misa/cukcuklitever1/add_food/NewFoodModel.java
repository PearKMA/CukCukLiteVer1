package vn.com.misa.cukcuklitever1.add_food;

import java.util.ArrayList;

import vn.com.misa.cukcuklitever1.add_food.entity.Unit;

public class NewFoodModel implements INewFoodContract.IModel {
    //private ArrayList<Unit>
    @Override
    public void onAddNewFood(String name, int price, String unit, String color, String icon, IAddFinished iAddFinished) {
        if (name.isEmpty()){
            iAddFinished.onNameFoodEmpty();
        }else if (unit.isEmpty()){
            iAddFinished.onUnitFoodEmpty();
        }else {
            iAddFinished.onSuccessful();
        }
    }
}
