package vn.com.misa.cukcuklitever1.add_food;

public interface INewFoodContract {

    interface IView {
        void nameFoodError();   //Hiển thị khi user không nhập vào tên món

        void unitFoodError();   //Hiển thị khi user không nhập đơn vị tính

        void addSuccessful();   //hiển thị thêm thành công

        void addFoodFail(String mess);  //Hiển thị thêm thất bại
    }

    interface IPresenter {
        void checkInput(String name, double price, String unit, String color, String icon);    //check các thông tin có đúng hay không

        void onDestroy(); //hủy view khi view bị hủy
    }

    interface IModel {
        interface IAddFinished {
            void onNameFoodEmpty(); //trả vê presenter khi tên trống

            void onUnitFoodEmpty(); //trả về presenter khi đơn vị tính trống

            void onFail(String message);    //trả về nếu thêm thất bại

            void onSuccessful();     //trả về presenter khi thêm thành công
        }

        void onAddNewFood(String name, double price, String unit, String color, String icon, IAddFinished iAddFinished); //xử lý dữ liệu và trả về kết quả
    }
}
