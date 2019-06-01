package vn.com.misa.cukcuklitever1.convert_string;

import java.util.Locale;

/**
 * Class chuyển đổi giá tiền từ double sang dạng string
 * create by lvhung on 6/1/2019
 */
class ConvertCurrency {
    private static ConvertCurrency mInstance;

    // instance variables here
    private ConvertCurrency() { }

    static ConvertCurrency getInstance() {
        if (mInstance == null) {
            mInstance = new ConvertCurrency();
        }
        return mInstance;
    }
    /**
     * Chuyển đổi tiền tệ, nếu là kiểu int -> string có dạng #.###, double có dạng #.###,##
     * @param price giá được truyền vào
     * @return string được chuyển đổi
     */
    String getPriceString(double price) {
        String ps;
        StringBuilder builder = new StringBuilder(" ");
        if ((price % 1) == 0) {
            //Chuyển đổi double -> #,###
            builder.append(String.format(Locale.US, "%,d", (int) price));
            //Chuyển #,### -> #.###
            ps = builder.toString().replaceAll(",",".");
            return ps;
        } else {
            //Chuyển double -> #,###.##
            builder.append(String.format(Locale.US, "%,.2f", price).replaceAll(",", "."));
            //Chuyển #,###.## -> #.###,##
            builder.replace(builder.lastIndexOf("."),builder.lastIndexOf(".")+1,",");
            return builder.toString();
        }
    }
}
