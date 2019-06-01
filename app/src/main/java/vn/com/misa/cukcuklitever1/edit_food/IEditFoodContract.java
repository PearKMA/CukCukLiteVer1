package vn.com.misa.cukcuklitever1.edit_food;

public interface IEditFoodContract {
    interface IView {
        void onSucessful(String message);   //sửa hoặc xóa thành công

        void onFail(String message);    //Sửa hoặc xóa thất bại

        void nameFoodError();           //Thông báo chưa nhập tên món

        void unitFoodError();           //Thông báo có lỗi đơn vị tính
    }

    interface IModel {
        interface IEditFinish {
            void onNameFoodEmpty();     //callback tên bị trống

            void onUnitFoodEmpty();     //callback đơn vị tính bị lỗi

            void onFail(String message);        //callback lỗi xử lý

            void onSuccessful(String message); //callback xử lý thành công
        }

        void deleteFood(int id, IEditFinish iEditFinish);   //Xử lý xóa món trong db

        void editFood(int id, String name, double price, String unit, String color, String icon, boolean status, IEditFinish iEditFinish); //xử lý sửa món trong db
    }

    interface IPresenter {
        void checkInput(int id, String name, double price, String unit, String color, String icon, boolean status);    //kiểm tra đầu vào và gửi đến model

        void deleteFood(int id);    //Gọi model xử lý xóa

        void onDestroyPresenter();      //Hủy view
    }
}
