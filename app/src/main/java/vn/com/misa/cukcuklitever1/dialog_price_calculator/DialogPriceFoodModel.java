package vn.com.misa.cukcuklitever1.dialog_price_calculator;

import vn.com.misa.cukcuklitever1.convert_string.ConvertCurrencyAdapter;
import vn.com.misa.cukcuklitever1.convert_string.IPriceTarget;

public class DialogPriceFoodModel implements IDialogPriceFoodContract.IModel {
    //class xử lý số thành dạng tiền
    private IPriceTarget mPriceTarget;

    /**
     * constructor
     * Edited by lvhung at 6/4/2019
     */
    DialogPriceFoodModel(){
        mPriceTarget= new ConvertCurrencyAdapter();
    }

    /**
     * Chuyển đổi ngược lại chuỗi thành số
     * Edited by lvhung at 6/4/2019
     * @param input chuỗi nhập vào
     * @return số được chuyển đỏi
     */
    @Override
    public double convertStringToDouble(String input) {
        String s = input.replace(".", "");
        try {
            return Double.parseDouble(s.replaceAll(",", "."));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Chuyển đổi số thành chữ
     * Edited by lvhung at 6/4/2019
     * @param priceInput số nhập vào
     * @return chuỗi dạng #.###,#
     */
    @Override
    public String convertToCurrency(double priceInput) {
        return mPriceTarget.getPriceString(priceInput);
    }

    /**
     * Set tăng giá trị lên 1
     * Edited by lvhung at 6/4/2019
     * @param input chuỗi nhập vào
     * @return số đã tăng giá trị
     */
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

    /**
     * Set giảm giá trị đi 1
     * Edited by lvhung at 6/4/2019
     * @param input chuỗi nhập vào
     * @return số đã giảm
     */
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

    /**
     * Xóa 1 ký tự
     * Edited by lvhung at 6/4/2019
     * @param input chuỗi nhập vào
     * @return chuỗi
     */
    @Override
    public String clearOne(String input) {
        if (input.length() > 1) {
            return input.substring(0, input.length() - 1);
        } else {
            return "0";
        }
    }

    /**
     * Xử lý tính toán số người dùng đã nhập vào, chỉ hỗ trợ dạng cộng trừ các số không có ký tự đặc biệt
     * Edited by lvhung at 6/4/2019
     * @param input chuỗi trên edittext
     * @return  kết quả tính
     */
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
