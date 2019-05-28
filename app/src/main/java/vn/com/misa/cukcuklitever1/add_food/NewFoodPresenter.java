package vn.com.misa.cukcuklitever1.add_food;

public class NewFoodPresenter implements INewFoodContract.IPresenter,INewFoodContract.IModel.IAddFinished {
    private INewFoodContract.IModel mModel;
    private INewFoodContract.IView mView;

    public NewFoodPresenter(INewFoodContract.IView mView) {
        this.mView = mView;
        mModel=new NewFoodModel();
    }

    @Override
    public void checkInput(String name, int price, String unit, String color, String icon) {
        if (mView!=null){
            mModel.onAddNewFood(name,price,unit,color,icon,this);
        }
    }

    @Override
    public void onDestroy() {
        if (mView!=null)
            mView=null;
    }

    @Override
    public void onNameFoodEmpty() {
        mView.nameFoodError();
    }

    @Override
    public void onUnitFoodEmpty() {
        mView.unitFoodError();
    }

    @Override
    public void onFail(String message) {

    }

    @Override
    public void onSuccessful() {
        mView.addSuccessful();
    }
}
