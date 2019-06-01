package vn.com.misa.cukcuklitever1.convert_string;

public class ConvertCurrencyAdapter implements IPriceTarget {
    private ConvertCurrency convertCurrency = ConvertCurrency.getInstance();

    @Override
    public String getPriceString(double input) {
        return convertCurrency.getPriceString(input);
    }
}
