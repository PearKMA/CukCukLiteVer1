package vn.com.misa.cukcuklitever1.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.com.misa.cukcuklitever1.R;

public class DialogPriceFood extends DialogFragment {
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.etPrice)
    EditText etPrice;
    @BindView(R.id.btnDone)
    Button btnDone;
    Unbinder unbinder;
    int price=-1;
    public interface OnInputListener {
        void sendInput(int input);
    }

    public OnInputListener onInputListener;
    public static DialogPriceFood newInstance() {
        Bundle args = new Bundle();
        DialogPriceFood fragment = new DialogPriceFood();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_price, container);
        unbinder = ButterKnife.bind(this, view);
        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etPrice.length() == 0){
                    etPrice.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        ivClose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
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
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    price = Integer.parseInt(etPrice.getText().toString());
                    if (onInputListener!=null&&price>=0){
                        onInputListener.sendInput(price);
                        getDialog().dismiss();
                    }else {
                        etPrice.setText("0");
                    }
                } catch (NumberFormatException e) {
                    etPrice.setText("0");
                }
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onInputListener = (OnInputListener) getActivity();
        } catch (ClassCastException e) {
            Log.e("TAG", "onAttach: " + e.getMessage());
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
