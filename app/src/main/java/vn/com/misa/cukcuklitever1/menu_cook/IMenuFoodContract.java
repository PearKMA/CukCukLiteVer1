package vn.com.misa.cukcuklitever1.menu_cook;

import java.util.ArrayList;

import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

/**
 * create by lvhung on 5/29/2019
 */
public interface IMenuFoodContract {
    interface View {
        void showData(ArrayList<Food> listFood);        //hiển thị tất cả thực đơn có trong database

        void showAddFood(); //Hiển thị màn hình thêm món

        void showEditFood(Food food);   //hiện màn hình chỉnh sửa món

    }

    interface Presenter {

        void setFoodData();     //lấy danh sách và hiển thị lên view

        void addFood();     //xử lý thêm món

        void editFood(Food food);   //Xử lý sửa món

        void destroyActivity();     //xử lý khi destroy view
    }

    interface Model {
        ArrayList<Food> getFood();      //lấy toàn bộ danh sách món trong database
    }

}
