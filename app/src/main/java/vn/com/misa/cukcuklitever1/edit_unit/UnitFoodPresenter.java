package vn.com.misa.cukcuklitever1.edit_unit;

import android.app.Activity;

public class UnitFoodPresenter implements IUnitFoodContract.IPresenter, IUnitFoodContract.IModel.ICheckFinish {
    private IUnitFoodContract.IView mView;
    private IUnitFoodContract.IModel mModel;

    UnitFoodPresenter(Activity context, IUnitFoodContract.IView mView) {
        this.mView = mView;
        mModel = new UnitFoodModel(context);
    }

    @Override
    public void getAllData() {
        if (mView!=null)
            mView.showData(mModel.getAllUnit());
    }

    @Override
    public void getInputInsert(String name) {
        mModel.insertUnit(name,this);
    }

    @Override
    public void getInputEdit(String name,int id) {
        mModel.editUnit(name,id,this);
    }

    @Override
    public void removeUnit(int id) {
        mModel.removeUnit(id,this);
    }

    @Override
    public void destroyView() {
        if (mView!=null){
            mView = null;
        }
    }

    @Override
    public void onNameUnitEmpty() {
        if (mView!=null)
            mView.showError("Name's unit is empty!");
    }

    @Override
    public void onFail(String message) {
        if (mView!=null)
            mView.showError(message);
    }

    @Override
    public void onSuccessful(String message) {
        if (mView != null) {
            mView.onComplete(message);
        }
    }
}
