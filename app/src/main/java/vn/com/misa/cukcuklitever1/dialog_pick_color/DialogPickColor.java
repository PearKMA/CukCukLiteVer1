package vn.com.misa.cukcuklitever1.dialog_pick_color;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.misa.cukcuklitever1.R;

public class DialogPickColor extends DialogFragment implements PickColorAdapter.IColorCallback {
    private RecyclerView rvPickColor;
    private TextView tvCancel;
    private PickColorAdapter mAdapter;
    private ArrayList<String> mListColor;
    private IColorDialogReturned mListener;
    public interface IColorDialogReturned{
        void onColorReturned(String color);
    }
    public void setCallback(IColorDialogReturned iColorDialogReturned){
        this.mListener = iColorDialogReturned;
    }
    public static DialogPickColor newInstance(String color) {
        Bundle args = new Bundle();
        args.putString("Color",color);
        DialogPickColor fragment = new DialogPickColor();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_pick_color, container);
        tvCancel = view.findViewById(R.id.tvCancel);
        rvPickColor = view.findViewById(R.id.rvPickColor);

        mListColor=getListColor();
        if (getArguments()!=null) {
            mAdapter = new PickColorAdapter(getActivity(), mListColor,getArguments().getString("Color","#039be5"));
            mAdapter.setCallbackListener(this);
            rvPickColor.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4,
                    GridLayoutManager.VERTICAL, false);
            rvPickColor.setLayoutManager(gridLayoutManager);
            rvPickColor.setAdapter(mAdapter);
        }
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    /**
     * Lấy danh sách màu
     * create by lvhung on 6/5/2019
     * @return list
     */
    private ArrayList<String> getListColor() {
        ArrayList<String> list = new ArrayList<>();
        list.add("#26c6da");
        list.add("#0097a7");
        list.add("#0d47a1");
        list.add("#1565c0");
        list.add("#039be5");
        list.add("#64b5f6");
        list.add("#ff6f00");
        list.add("#ffa000");
        list.add("#ffb300");
        list.add("#ce9600");
        list.add("#8d6e63");
        list.add("#6d4c41");
        list.add("#d32f2f");
        list.add("#ff1744");
        list.add("#f44336");
        list.add("#ec407a");
        list.add("#ad1457");
        list.add("#6a1b9a");
        list.add("#ab47bc");
        list.add("#ba68c8");
        list.add("#00695c");
        list.add("#00897b");
        list.add("#4db6ac");
        list.add("#2e7d32");
        list.add("#43a047");
        list.add("#64dd17");
        list.add("#212121");
        list.add("#5f7c8a");
        list.add("#b0bec5");
        list.add("#455a64");
        list.add("#607d8b");
        list.add("#90a4ae");
        return list;
    }
    /**
     * Thay đổi kích cỡ dialog
     * Edited by lvhung at 6/4/2019
     */
    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
    }

    @Override
    public void onColorPicked(String color) {
        if (mListener!=null&&color!=null){
            mListener.onColorReturned(color);
        }
        dismiss();
    }
}
