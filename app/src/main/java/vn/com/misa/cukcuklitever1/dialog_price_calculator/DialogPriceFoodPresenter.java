package vn.com.misa.cukcuklitever1.dialog_price_calculator;

public class DialogPriceFoodPresenter implements IDialogPriceFoodContract.IPresenter{
    private IDialogPriceFoodContract.IView mView;
    private IDialogPriceFoodContract.IModel mModel;

    /**
     * Constructor
     * Edited by lvhung at 6/4/2019
     * @param mView view hiển thị kết quả
     */
    public DialogPriceFoodPresenter(IDialogPriceFoodContract.IView mView) {
        this.mView = mView;
        mModel = new DialogPriceFoodModel();
    }

    /**
     * Nối số khi nhấn các nút 000,0->9
     * Edited by lvhung at 6/4/2019
     * @param originalText chuỗi ban đầu
     * @param number chữ số muốn thêm vào
     */
    @Override
    public void appendNumber(String originalText, String number) {
        String text = originalText + number;
        mView.showResult(text);
    }

    /**
     * Xóa hết dữ liệu
     * Edited by lvhung at 6/4/2019
     */
    @Override
    public void clearAll() {
        mView.showResult("0");
    }

    /**
     * Xóa 1 ký tự
     * Edited by lvhung at 6/4/2019
     * @param originalText
     */
    @Override
    public void clearOne(String originalText) {
        mView.showResult(mModel.clearOne(originalText));
    }

    /**
     * Giảm đơn vị đi 1
     * Edited by lvhung at 6/4/2019
     * @param originalText chuỗi ban đầu
     */
    @Override
    public void decrementNumber(String originalText) {
        mView.showResult(mModel.convertToCurrency(mModel.setDecrement(originalText)));
    }

    /**
     * Tăng giá trị lên 1
     * Edited by lvhung at 6/4/2019
     * @param originalText chuỗi nhập vào
     */
    @Override
    public void incrementNumber(String originalText) {
        mView.showResult(mModel.convertToCurrency(mModel.setIncrement(originalText)));
    }

    /**
     * Thêm dấu ','
     * @param originalText chuỗi ban đầu
     */
    @Override
    public void appendComma(String originalText) {
        mView.showResult(originalText+",");
    }

    /**
     * Cộng các số
     * Edited by lvhung at 6/4/2019
     * @param originalText chuỗi ban đầu
     */
    @Override
    public void addNumber(String originalText) {
        mView.showResult(originalText+"+");
    }

    /**
     * Trừ các số
     * Edited by lvhung at 6/4/2019
     * @param originalText chuỗi ban đầu
     */
    @Override
    public void subNumber(String originalText) {
        mView.showResult(originalText+"-");
    }

    /**
     * Trị tuyệt đối
     * Edited by lvhung at 6/4/2019
     * @param originalText chuỗi ban đầu
     */
    @Override
    public void absNumber(String originalText) {
        double result = mModel.calculateNumber(originalText);
        if (result>=0.0){
            mView.showResult("-"+mModel.convertToCurrency(result));
        }else {
            mView.showResult(mModel.convertToCurrency(Math.abs(result)));
        }
    }

    /**
     * Tính toán xong
     * Edited by lvhung at 6/4/2019
     * @param originalText chuỗi ban đầu
     */
    @Override
    public void calculatorDone(String originalText) {
        mView.calculatorComplete(mModel.convertToCurrency(mModel.calculateNumber(originalText)));
    }

    /**
     * Chuyển đổi số sang tiền tệ
     * Edited by lvhung at 6/4/2019
     * @param priceInput giá nhập vào
     */
    @Override
    public void convertToCurrency(double priceInput) {
        mView.showResult(mModel.convertToCurrency(priceInput));
    }

    /**
     * Chuyển đổi chuỗi về số
     * Edited by lvhung at 6/4/2019
     * @param input chuỗi nhập vào
     */
    @Override
    public void convertStringToDouble(String input) {
        mView.getPrice(mModel.convertStringToDouble(input));
    }
}
