package vn.com.misa.cukcuklitever1.edit_unit.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.edit_unit.entity.Unit;

/**
 * adapter cho listview hiển thị unit
 * create by lvhung on 6/5/2019
 */
public class UnitAdapter extends ArrayAdapter<Unit> {
    private Activity context;
    private int resource;
    private List<Unit> objects;
    private int lastPositon = -1;
    private IEditUnit mListener;
    private String nameSelected;

    /**
     * Callback
     * create by lvhung on 6/5/2019
     */
    public interface IEditUnit{
        void onEditUnit(String name,int id);
        void unitSelected(String unit);

        void removeItem(Unit unit);
    }

    /**
     * set callback
     * create by lvhung on 6/5/2019
     * @param mListener listener callback
     */
    public void setCallback(IEditUnit mListener){
        this.mListener = mListener;
    }

    /**
     * constructor
     * create by lvhung on 6/5/2019
     * @param context context chứa adapter
     * @param resource layout
     * @param objects list
     */
    public UnitAdapter(@NonNull Activity context, int resource, @NonNull List<Unit> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        if (context.getIntent()!=null) {
            nameSelected = context.getIntent().getStringExtra("UNIT");
            for (Unit item: objects){
                if (nameSelected!=null&&nameSelected.equalsIgnoreCase(item.getUnit())){
                    lastPositon = objects.indexOf(item);
                    break;
                }
            }
        }

    }

    /**
     * Set view cho mỗi dòng item listview
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = this.context.getLayoutInflater().inflate(this.resource, null);
        ImageView ivCheck = row.findViewById(R.id.ivCheck);
        final TextView tvNameUnit = row.findViewById(R.id.tvNameUnit);
        ImageView ivEdit = row.findViewById(R.id.ivEdit);
        final Unit unit = this.objects.get(position);
        tvNameUnit.setText(unit.getUnit());
        if (lastPositon == position) {
            ivCheck.setBackgroundResource(R.color.green);
        } else {
            ivCheck.setBackgroundResource(R.color.white);
        }
        ivCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPositon = position;
                if (mListener!=null){
                    mListener.unitSelected(objects.get(lastPositon).getUnit());
                }
                notifyDataSetChanged();
            }
        });
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPositon=position;
                if (mListener!=null){
                    mListener.unitSelected(objects.get(lastPositon).getUnit());
                }
                notifyDataSetChanged();
                createDialogAddUnit();
            }
        });
        tvNameUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPositon = position;
                if (mListener!=null){
                    mListener.unitSelected(objects.get(lastPositon).getUnit());
                }
                notifyDataSetChanged();
            }
        });
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPositon = position;
                if (mListener!=null){
                    mListener.unitSelected(objects.get(lastPositon).getUnit());
                }
                notifyDataSetChanged();
            }
        });
        tvNameUnit.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final PopupMenu popup = new PopupMenu(context, tvNameUnit);
                popup.getMenuInflater().inflate(R.menu.menu_delete_item, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        if (i == R.id.mnuRemove) {
                            if (mListener!=null){
                                mListener.removeItem(objects.get(lastPositon));
                            }
                            return true;
                        }
                        else {
                            return onMenuItemClick(item);
                        }
                    }
                });

                popup.show();
                return true;
            }
        });
        return row;
    }

    /**
     * Tạo dialog thêm đơn vị tính
     * create by lvhung on 6/5/2019
     */
    private void createDialogAddUnit() {
        LayoutInflater li = LayoutInflater.from(context);
        @SuppressLint("InflateParams") View viewDialog = li.inflate(R.layout.dialog_edit_unit, null);

        final AlertDialog alertDialogBuilder = new AlertDialog.Builder(
                context).create();
        alertDialogBuilder.setView(viewDialog);
        TextView tvTitle = viewDialog.findViewById(R.id.tvTitleDialog);
        ImageView ivClose = viewDialog.findViewById(R.id.ivCloseDialog);
        final EditText etInput = viewDialog.findViewById(R.id.etInput);
        Button btnCancel = viewDialog.findViewById(R.id.btnCancel);
        Button btnGet = viewDialog.findViewById(R.id.btnGet);
        tvTitle.setText(this.context.getString(R.string.edit_unit));
        etInput.setText(objects.get(lastPositon).getUnit());
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.dismiss();
            }
        });
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etInput.getText().toString().trim();
                if (objects!=null&&objects.size()>0){
                    boolean check = false;
                    for (int i=0;i<objects.size();i++){
                        if (input.equalsIgnoreCase(objects.get(i).getUnit())){
                            Toast.makeText(context,context.getString(R.string.unit_duplicate,objects.get(i).getUnit()),Toast.LENGTH_SHORT).show();
                            check = true;
                            break;
                        }
                    }
                    if (!check&&mListener!=null){
                        mListener.onEditUnit(etInput.getText().toString().trim(),objects.get(lastPositon).getId());
                        alertDialogBuilder.dismiss();
                    }
                }
            }
        });
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();
    }

}
