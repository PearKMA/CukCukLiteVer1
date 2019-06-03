package vn.com.misa.cukcuklitever1.dialog_price_calculator;

import vn.com.misa.cukcuklitever1.convert_string.ConvertCurrencyAdapter;
import vn.com.misa.cukcuklitever1.convert_string.IPriceTarget;

public class DialogPriceFoodModel implements IDialogPriceFoodContract.IModel {
    private IPriceTarget mPriceTarget;
    public DialogPriceFoodModel(){
        mPriceTarget= new ConvertCurrencyAdapter();
    }

    @Override
    public double convertStringToDouble(String input) {
        String s = input.replace(".", "");
        try {
            return Double.parseDouble(s.replaceAll(",", "."));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public String convertToCurrency(double priceInput) {
        return mPriceTarget.getPriceString(priceInput);
    }

    @Override
    public double setIncrement(String input) {
        try {
            //Chuyển #.###,## -> ####,## -> ####.##
            String s = input.replace(".", "");
            return Double.parseDouble(s.replaceAll(",", ".")) + 1;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public double setDecrement(String input) {
        try {
            //Chuyển #.###,## -> ####,## -> ####.##
            String s = input.replace(".", "");
            return Double.parseDouble(s.replaceAll(",", ".")) - 1;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public String clearOne(String input) {
        if (input.length() > 1) {
            return input.substring(0, input.length() - 1);
        } else {
            return "0";
        }
    }

    @Override
    public double calculateNumber(String input) {
        try {
            double result = 0;
            String[] n1 = input.split("\\+");
            for (String i : n1) {
                if (i.contains("-")) {
                    String[] n2 = i.split("-");
                    if (!n2[0].isEmpty())
                        result += convertStringToDouble(n2[0]);
                    for (int j = 1; j < n2.length; j++) {
                        if (!n2[j].isEmpty())
                        result -= convertStringToDouble(n2[j]);
                    }
                } else {
                    if (!i.isEmpty())
                    result += convertStringToDouble(i);
                }
            }
            return result;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
