package vn.com.misa.cukcuklitever1.menu_cook.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.convert_string.ConvertCurrencyAdapter;
import vn.com.misa.cukcuklitever1.convert_string.IPriceTarget;
import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;
import vn.com.misa.cukcuklitever1.view_custom.CircleImageView;

/**
 * create by lvhung on 5/29/2019
 */
public class MenuFoodAdapter extends ArrayAdapter<Food> {
    private Activity context;       //màn hình sử dụng
    private int resource;           //layout cho từng dòng hiển thị
    private List<Food> objects;     //danh sách nguồn dữ liệu
    private IPriceTarget mPriceTarget;
    public MenuFoodAdapter(@NonNull Activity context, int resource, @NonNull List<Food> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.objects=objects;
        mPriceTarget = new ConvertCurrencyAdapter();
    }

    /**
     * Set dữ liệu cho từng dòng listview
     * @param position  vị trí của item
     * @param convertView   layout của item
     * @param parent    viewgroup
     * @return  row là 1 layout item
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //inflate layout
        LayoutInflater inflater=this.context.getLayoutInflater();
        @SuppressLint("ViewHolder") View row = inflater.inflate(this.resource, null);
        //find id
        CircleImageView ivIconFood =  row.findViewById(R.id.ivIconFood);
        TextView tvNameFood =row.findViewById(R.id.tvNameFood);
        TextView tvPrice =  row.findViewById(R.id.tvPriceFood);
        TextView tvStatus =  row.findViewById(R.id.tvStatusFood);
        //hiển thị dữ liệu của 1 item lên listview
        Food food = this.objects.get(position);
        String PATH = "file:///android_asset/icon/";
        Glide.with(this.context).asBitmap().load(PATH +food.getIcon()).into(ivIconFood);
        ivIconFood.setBackgroundColor(Color.parseColor(food.getColor()));
        tvNameFood.setText(food.getName());
        //chuyển đổi đơn vị tiền
        String price =this.context.getString(R.string.price_food)+ mPriceTarget.getPriceString(food.getPrice());
        tvPrice.setText(price);
        tvStatus.setVisibility(food.isStatus() ?View.VISIBLE:View.INVISIBLE);
        return row;
    }
}
