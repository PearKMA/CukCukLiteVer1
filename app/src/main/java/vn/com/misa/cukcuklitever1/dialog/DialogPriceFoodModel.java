package vn.com.misa.cukcuklitever1.dialog;

public class DialogPriceFoodModel implements IDialogPriceFoodContract.IModel {
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
                        result += Double.parseDouble(n2[0]);
                    for (int j = 1; j < n2.length; j++) {
                        result -= Double.parseDouble(n2[j]);
                    }
                } else {
                    result += Double.parseDouble(i);
                }
            }
            return result;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
