package vn.com.misa.cukcuklitever1.dialog_price_calculator;

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
        mView.showResult(mModel.convertToCurrency(mModel.setDecrement(originalText)));
    }

    @Override
    public void incrementNumber(String originalText) {
        mView.showResult(mModel.convertToCurrency(mModel.setIncrement(originalText)));
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
            mView.showResult("-"+mModel.convertToCurrency(result));
        }else {
            mView.showResult(mModel.convertToCurrency(Math.abs(result)));
        }
    }

    @Override
    public void calculatorDone(String originalText) {
        mView.calculatorComplete(mModel.convertToCurrency(mModel.calculateNumber(originalText)));
    }

    @Override
    public void convertToCurrency(double priceInput) {
        mView.showResult(mModel.convertToCurrency(priceInput));
    }

    @Override
    public void convertStringToDouble(String input) {
        mView.getPrice(mModel.convertStringToDouble(input));
    }
}
