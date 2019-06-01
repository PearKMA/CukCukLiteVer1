package vn.com.misa.cukcuklitever1.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.convert_string.ConvertCurrencyAdapter;
import vn.com.misa.cukcuklitever1.convert_string.IPriceTarget;

/**
 * dialog hiện nhập giá món
 */
public class DialogPriceFood extends DialogFragment implements View.OnClickListener {
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.etPrice)
    EditText etPrice;
    @BindView(R.id.btnDone)
    TextView btnDone;
    @BindView(R.id.tvKeyC)
    TextView tvKeyC;
    @BindView(R.id.tvKeyDecrement)
    TextView tvKeyDecrement;
    @BindView(R.id.tvKeyIncreament)
    TextView tvKeyIncreament;
    @BindView(R.id.tvKeyClear)
    ImageView tvKeyClear;
    @BindView(R.id.tvKey7)
    TextView tvKey7;
    @BindView(R.id.tvKey8)
    TextView tvKey8;
    @BindView(R.id.tvKey9)
    TextView tvKey9;
    @BindView(R.id.tvKeySub)
    TextView tvKeySub;
    @BindView(R.id.tvKey4)
    TextView tvKey4;
    @BindView(R.id.tvKey5)
    TextView tvKey5;
    @BindView(R.id.tvKey6)
    TextView tvKey6;
    @BindView(R.id.tvKeyAdd)
    TextView tvKeyAdd;
    @BindView(R.id.tvKey1)
    TextView tvKey1;
    @BindView(R.id.tvKey2)
    TextView tvKey2;
    @BindView(R.id.tvKey3)
    TextView tvKey3;
    @BindView(R.id.tvKeyAbs)
    TextView tvKeyAbs;
    @BindView(R.id.tvKey0)
    TextView tvKey0;
    @BindView(R.id.tvKey000)
    TextView tvKey000;
    @BindView(R.id.tvKeyComma)
    TextView tvKeyComma;
    Unbinder unbinder;
    double price = -1;
    private IPriceTarget mPriceTarget;

    /**
     * callback trả về giá đã nhập
     * create by lvhung on 5/30/2019
     */
    public interface OnInputListener {

        void sendInput(double input);
    }

    public OnInputListener onInputListener;

    /**
     * Khởi tạo fragment
     * create by lvhung on 5/30/2019
     *
     * @return fragment
     */
    public static DialogPriceFood newInstance(double price) {
        Bundle args = new Bundle();
        args.putDouble("price", price);
        DialogPriceFood fragment = new DialogPriceFood();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Set style cho dialog để kích thước không bị co lại
     * create by lvhung on 5/30/2019
     *
     * @param savedInstanceState trạng thái
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
    }

    /**
     * gán callback khi được khởi tạo
     * create by lvhung on 5/30/2019
     *
     * @param context chứa dialog
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onInputListener = (OnInputListener) getActivity();
        } catch (ClassCastException e) {
            Log.e("TAG", "onAttach: " + e.getMessage());
        }
    }

    /**
     * Hủy ButterKnife
     * create by lvhung on 5/30/2019
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        onInputListener = null;
    }

    /**
     * xử lý các sự kiện khi chọn
     * create by lvhung on 5/30/2019
     *
     * @param inflater           chuyển layout thành dạng java
     * @param container          view chứa dialog
     * @param savedInstanceState trạng thái
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_price, container);
        unbinder = ButterKnife.bind(this, view);
        mPriceTarget = new ConvertCurrencyAdapter();
        //Kiểm tra giá trị nhập vào
        if (getArguments() != null) {
            double priceInput = getArguments().getDouble("price", 0);
            convertToCurrency(priceInput);
        }
        etPrice.setSelectAllOnFocus(true);
        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etPrice.length() == 0) {
                    etPrice.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //Đóng dialog
        ivClose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ivClose.setAlpha(0.5f);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        ivClose.setAlpha(1f);
                        break;
                    case MotionEvent.ACTION_UP:
                        ivClose.setAlpha(1f);
                        getDialog().dismiss();
                        break;
                }
                return true;
            }
        });
        //set listener
        btnDone.setOnClickListener(this);
        tvKeyC.setOnClickListener(this);
        tvKeyDecrement.setOnClickListener(this);
        tvKeyIncreament.setOnClickListener(this);
        tvKeyClear.setOnClickListener(this);
        tvKeyComma.setOnClickListener(this);
        tvKey1.setOnClickListener(this);
        tvKey2.setOnClickListener(this);
        tvKey3.setOnClickListener(this);
        tvKey4.setOnClickListener(this);
        tvKey5.setOnClickListener(this);
        tvKey6.setOnClickListener(this);
        tvKey7.setOnClickListener(this);
        tvKey8.setOnClickListener(this);
        tvKey9.setOnClickListener(this);
        tvKey0.setOnClickListener(this);
        tvKey000.setOnClickListener(this);
        tvKeyAbs.setOnClickListener(this);
        return view;
    }

    /**
     * Chuyển đổi giá
     *
     * @param priceInput
     */
    private void convertToCurrency(double priceInput) {
        etPrice.setText(mPriceTarget.getPriceString(priceInput));
        if (etPrice.getText().toString().equals("0")) {
            etPrice.setSelectAllOnFocus(true);
        } else {
            etPrice.setSelection(etPrice.getText().length());
        }
    }

    /**
     * Chuyển đổi text thành double
     * create by lvhung on 6/1/2019
     *
     * @return double
     */
    private double convertStringToDouble() {
        //Chuyển #.###,## -> ####,## -> ####.##
        String s = etPrice.getText().toString().trim().replace(".", "");
        try {
            return Double.parseDouble(s.replaceAll(",", "."));
        } catch (NumberFormatException e) {
            convertToCurrency(0);
            return 0;
        }
    }

    /**
     * Xử lý sự kiện cho các view
     * create by lvhung on 5/30/2019
     *
     * @param v view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDone:
                price = convertStringToDouble();
                if (onInputListener != null && price >= 0) {
                    onInputListener.sendInput(price);
                    getDialog().dismiss();
                } else {
                    Toast toast;
                    toast = Toast.makeText(getActivity(), getString(R.string.number_negative), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 200);
                    toast.show();
                }

                break;
            case R.id.tvKeyC:
                convertToCurrency(0);
                break;
            case R.id.tvKeyDecrement:

                break;
            case R.id.tvKeyIncreament:

                break;
            case R.id.tvKeyClear:
                break;
            case R.id.tvKey7:
                break;
            case R.id.tvKey8:
                break;
            case R.id.tvKey9:
                break;
            case R.id.tvKey4:
                break;
            case R.id.tvKey5:
                break;
            case R.id.tvKey6:
                break;
            case R.id.tvKey1:
                break;
            case R.id.tvKey2:
                break;
            case R.id.tvKey3:
                break;
            case R.id.tvKey0:
                break;
            case R.id.tvKey000:
                break;
            case R.id.tvKeyComma:
                break;
            case R.id.tvKeyAbs:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
    }
}
