package vn.com.misa.cukcuklitever1.edit_unit;

import java.util.ArrayList;

import vn.com.misa.cukcuklitever1.edit_unit.entity.Unit;

public interface IUnitFoodContract {
    interface IView {
        void showData(ArrayList<Unit> units);

        void showError(String message);

        void onComplete(String message);
    }

    interface IModel {
        ArrayList<Unit> getAllUnit();

        void editUnit(String name, int id,ICheckFinish iCheckFinish);

        void removeUnit(int id,ICheckFinish iCheckFinish);

        void insertUnit(String name, ICheckFinish iCheckFinish);

        interface ICheckFinish {
            void onNameUnitEmpty();     //callback tên bị trống

            void onFail(String message);        //callback lỗi xử lý

            void onSuccessful(String message); //callback xử lý thành công
        }
    }
    interface IPresenter{
        void getAllData();

        void getInputInsert(String name);

        void getInputEdit(String name, int id);

        void removeUnit(int id);
        void destroyView();
    }
}
