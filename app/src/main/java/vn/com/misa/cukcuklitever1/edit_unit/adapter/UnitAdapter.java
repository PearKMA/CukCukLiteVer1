package vn.com.misa.cukcuklitever1.edit_unit.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.edit_unit.entity.Unit;

public class UnitAdapter extends ArrayAdapter<Unit> {
    private Activity context;
    private int resource;
    private List<Unit> objects;
    private int lastPositon = -1;
    View row;
    ImageView ivCheck;
    TextView tvNameUnit;
    ImageView ivEdit;
    private IEditUnit mListener;
    interface IEditUnit{
        void onEditUnit(String name);
    }
    public void setCallback(IEditUnit mListener){
        this.mListener = mListener;
    }

    public UnitAdapter(@NonNull Activity context, int resource, @NonNull List<Unit> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (row == null)
            row = this.context.getLayoutInflater().inflate(this.resource, null);
        ivCheck = row.findViewById(R.id.ivCheck);
        tvNameUnit = row.findViewById(R.id.tvNameUnit);
        ivEdit = row.findViewById(R.id.ivEdit);

        Unit unit = this.objects.get(position);
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
                notifyDataSetChanged();
            }
        });
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogAddUnit();
            }
        });
        tvNameUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPositon = position;
                notifyDataSetChanged();
            }
        });
        tvNameUnit.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        return row;
    }

    private void createDialogAddUnit() {
        LayoutInflater li = LayoutInflater.from(context);
        View viewDialog = li.inflate(R.layout.dialog_unit, null);

        final AlertDialog alertDialogBuilder = new AlertDialog.Builder(
                context).create();
        alertDialogBuilder.setView(viewDialog);
        TextView tvTitle = viewDialog.findViewById(R.id.tvTitleDialog);
        ImageView ivClose = viewDialog.findViewById(R.id.ivCloseDialog);
        final EditText etInput = viewDialog.findViewById(R.id.etInput);
        Button btnCancel = viewDialog.findViewById(R.id.btnCancel);
        Button btnGet = viewDialog.findViewById(R.id.btnGet);
        tvTitle.setText(this.context.getString(R.string.edit_unit));
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
                    if (check==true&&mListener!=null){
                        mListener.onEditUnit(etInput.getText().toString().trim());
                    }
                }
            }
        });
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();
    }

}
