package vn.com.misa.cukcuklitever1.dialog;

public class DialogPriceFoodPresenter implements IDialogPriceFoodContract.IPresenter{
    private IDialogPriceFoodContract.IView mView;
    private IDialogPriceFoodContract.IModel mModel;

    public DialogPriceFoodPresenter(IDialogPriceFoodContract.IView mView) {
        this.mView = mView;
        mModel = new DialogPriceFoodModel();
    }

    @Override
    public void appendNumber(String originalText, String number) {
        mView.showResult(originalText+number);
    }

    @Override
    public void clearAll() {
        mView.showResult("0");
    }

    @Override
    public void clearOne(String originalText) {
        mView.showResult(mModel.clearOne(originalText));
    }

    @Override
    public void decrementNumber(String originalText) {
        mView.showResult(String.valueOf(mModel.setDecrement(originalText)));
    }

    @Override
    public void incrementNumber(String originalText) {
        mView.showResult(String.valueOf(mModel.setIncrement(originalText)));
    }

    @Override
    public void appendComma(String originalText) {
        mView.showResult(originalText+",");
    }

    @Override
    public void addNumber(String originalText) {
        mView.showResult(originalText+"+");
    }

    @Override
    public void subNumber(String originalText) {
        mView.showResult(originalText+"-");
    }

    @Override
    public void absNumber(String originalText) {
        double result = mModel.calculateNumber(originalText);
        if (result>=0.0){
            mView.showResult("-"+result);
        }else {
            mView.showResult(String.valueOf(Math.abs(result)));
        }
    }

    @Override
    public void calculatorDone(String originalText) {
        mView.calcuatorComplete(mModel.calculateNumber(originalText)+"");
    }
}
