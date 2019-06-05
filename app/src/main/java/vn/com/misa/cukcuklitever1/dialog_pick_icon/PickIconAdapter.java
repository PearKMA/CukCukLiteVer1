package vn.com.misa.cukcuklitever1.dialog_pick_icon;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.com.misa.cukcuklitever1.R;

public class PickIconAdapter extends RecyclerView.Adapter<PickIconAdapter.ViewHolder> {
    private int selected_position = -1;
    private Activity context;
    private List<String> list;

    /**
     * constructor
     * create by lvhung on 6/5/2019
     * @param context context
     * @param list list
     * @param defaultColor màu mặc định
     */
    PickIconAdapter(Activity context, List<String> list, String defaultColor) {
        this.context = context;
        this.list = list;
        for (String item: list){
            if (defaultColor.equals(item)){
                selected_position = list.indexOf(item);
                break;
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog_pick_color, viewGroup,
                false);
        return new ViewHolder(view);
    }

    /**
     * xử lý giao diện
     * @param viewHolder viewholder
     * @param i vị trí
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.bgItem.setCardBackgroundColor(Color.parseColor(list.get(i)));
            viewHolder.item.setVisibility(View.VISIBLE);
        Glide.with(context).asBitmap().load("file:///android_asset/icon/" + list.get(i)).into(viewHolder.item);
    }

    /**
     * Số lượng item
     * create by lvhung on 6/5/2019
     * @return số lượng
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView item;
        private CardView bgItem;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            bgItem = itemView.findViewById(R.id.bgItem);
            item = itemView.findViewById(R.id.ivCheck);
            bgItem.setOnClickListener(this);
        }

        /**
         * Callback trả về string màu được chọn
         * create by lvhung on 6/5/2019
         * @param v view
         */
        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);


        }
    }
}
