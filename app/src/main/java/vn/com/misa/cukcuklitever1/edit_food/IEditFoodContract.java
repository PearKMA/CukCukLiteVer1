package vn.com.misa.cukcuklitever1.edit_food;

public interface IEditFoodContract {
    interface IView {
        void onSucessful(String message);
        void onFail(String message);
        void nameFoodError();

        void unitFoodError();
    }
    interface IModel{
        interface IEditFinish{
            void onNameFoodEmpty();

            void onUnitFoodEmpty();

            void onFail(String message);

            void onSuccessful(String message);
        }
        void deleteFood(int id,IEditFinish iEditFinish);
        void editFood(int id,String name,int price, String unit, String color,String icon,boolean status,IEditFinish iEditFinish);
    }
    interface IPresenter{
        void checkInput(int id, String name, int price, String unit, String color, String icon,boolean status);

        void deleteFood(int id);
        void onDestroy();
    }
}
