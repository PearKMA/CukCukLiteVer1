package vn.com.misa.cukcuklitever1.dialog;

public interface IDialogPriceFoodContract {
    interface IView{
        void showResult(String result);
        void calcuatorComplete(String result);
    }
    interface IPresenter{
        void appendNumber(String originalText,String number);
        void clearAll();
        void clearOne(String originalText);

        void decrementNumber(String originalText);

        void incrementNumber(String originalText);

        void appendComma(String originalText);
        void addNumber(String originalText);

        void subNumber(String originalText);

        void absNumber(String originalText);
        void calculatorDone(String originalText);
    }
    interface IModel{
        double setIncrement(String input);

        double setDecrement(String input);

        String clearOne(String input);

        double calculateNumber(String input);
    }

}
