package vn.com.misa.cukcuklitever1.menu_cook;

import android.content.Context;

import java.util.ArrayList;

import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

public interface IMenuFoodContract {
    interface View {
        void showData(ArrayList<Food> listFood);        //hiển thị tất cả thực đơn có trong database

        void showAddFood();

        void showEditFood(Food food);

    }

    interface Presenter {

        void setFoodData(Context context);

        void addFood();

        void editFood(Food food);

        void destroyActivity();
    }

    interface Model {
        ArrayList<Food> getFood();
    }

}
