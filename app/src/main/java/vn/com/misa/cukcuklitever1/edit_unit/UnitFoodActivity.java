package vn.com.misa.cukcuklitever1.edit_unit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.base.BaseActivity;
import vn.com.misa.cukcuklitever1.edit_unit.adapter.UnitAdapter;
import vn.com.misa.cukcuklitever1.edit_unit.entity.Unit;

/**
 * Chỉnh sửa đơn vị tính
 * create by lvhung on 5/29/2019
 */
public class UnitFoodActivity extends BaseActivity implements UnitAdapter.IEditUnit, IUnitFoodContract.IView {
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.lvUnit)
    ListView lvUnit;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    private UnitAdapter mAdapter;
    private IUnitFoodContract.IPresenter mPresenter;
    private String lastName;
    /**
     * lấy id layout để setContentView
     * create by lvhung on 5/29/2019
     *
     * @return id
     */
    @Override
    protected int getIdLayout() {
        return R.layout.activity_unit_food;
    }

    /**
     * xử lý các view
     * create by lvhung on 5/29/2019
     */
    @Override
    public void initView() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvTitleToolbar.setText(getString(R.string.title_unit));
        mPresenter = new UnitFoodPresenter(this, this);
        mPresenter.getAllData();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("UNIT", lastName!=null?lastName:"Bao");
                setResult(2, intent);
                finish();
            }
        });
    }

    /**
     * Tạo menu
     * create by lvhung on 6/5/2019
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return true;
    }

    /**
     * Xử lý sự kiện của menu
     * @param item  menu được chọn
     * @return listener
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuAdd:
                createDialogAddUnit();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Sửa tên đơn vị tính
     * create by lvhung on 6/5/2019
     * @param name tên đơn vị tính
     * @param id id đơn vị tính
     */
    @Override
    public void onEditUnit(String name, int id) {
        mPresenter.getInputEdit(name, id);

    }

    /**
     * Lấy tên đơn vị tính đang được chọn
     * create by lvhung on 6/5/2019
     * @param unit tên đơn vị tính
     */
    @Override
    public void unitSelected(String unit) {
        lastName = unit;
    }

    /**
     * Xóa đơn vị tính
     * create by lvhung on 6/5/2019
     * @param unit class unit
     */
    @Override
    public void removeItem(final Unit unit) {
        LayoutInflater li = LayoutInflater.from(this);
        @SuppressLint("InflateParams") View viewDialog = li.inflate(R.layout.dialog_remove_unit, null);

        final AlertDialog alertDialogBuilder = new AlertDialog.Builder(
                this).create();
        alertDialogBuilder.setView(viewDialog);
        TextView tvTitle = viewDialog.findViewById(R.id.tvTitleDialog);
        ImageView ivClose = viewDialog.findViewById(R.id.ivCloseDialog);
        final TextView tvMessage= viewDialog.findViewById(R.id.tvMessage);
        Button btnCancel = viewDialog.findViewById(R.id.btnCancel);
        Button btnGet = viewDialog.findViewById(R.id.btnGet);
        tvTitle.setText(this.getString(R.string.app_name));
        tvTitle.setAllCaps(true);
        tvMessage.setText(String.format(getString(R.string.remove_message),unit.getUnit()));
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
                mPresenter.removeUnit(unit.getId());
            }
        });
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();
    }

    /**
     * Hiển thị list đơn vị tính
     * @param units list đơn vị tính
     */
    @Override
    public void showData(ArrayList<Unit> units) {
            mAdapter = new UnitAdapter(this, R.layout.item_unit_food, units);
            mAdapter.setCallback(this);
            lvUnit.setAdapter(mAdapter);
    }

    /**
     * Thông báo lỗi nếu có lỗi xảy ra khi thêm, sủa xóa đơn vị tính
     * create by lvhung on 6/5/2019
     * @param message chi tiết lỗi
     */
    @Override
    public void showError(String message) {
        showToast(message);
    }

    /**
     * Thông báo thêm, sửa , xóa thành công
     * create by lvhung on 6/5/2019
     * @param message chi tiết
     */
    @Override
    public void onComplete(String message) {
        showToast(message);
    }

    /**
     * Hiển thị tin nhắn lên màn hình
     * create by lvhung on 6/5/2019
     * @param message tin nhắn
     */
    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 120);
        toast.show();
    }

    /**
     * Tạo dialog thêm đơn vị tính
     * create by lvhung on 6/5/2019
     */
    private void createDialogAddUnit() {
        LayoutInflater li = LayoutInflater.from(this);
        @SuppressLint("InflateParams") View viewDialog = li.inflate(R.layout.dialog_edit_unit, null);

        final AlertDialog alertDialogBuilder = new AlertDialog.Builder(
                this).create();
        alertDialogBuilder.setView(viewDialog);
        TextView tvTitle = viewDialog.findViewById(R.id.tvTitleDialog);
        ImageView ivClose = viewDialog.findViewById(R.id.ivCloseDialog);
        final EditText etInput = viewDialog.findViewById(R.id.etInput);
        Button btnCancel = viewDialog.findViewById(R.id.btnCancel);
        Button btnGet = viewDialog.findViewById(R.id.btnGet);
        tvTitle.setText(this.getString(R.string.edit_unit));
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
                mPresenter.getInputInsert(input);
                alertDialogBuilder.dismiss();
            }
        });
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();
    }

    /**
     * Hủy đăng ký view
     * create by lvhung on 6/5/2019
     */
    @Override
    protected void onDestroy() {
        mPresenter.destroyView();
        super.onDestroy();
    }
}
