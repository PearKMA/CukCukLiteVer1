package vn.com.misa.cukcuklitever1.edit_food;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.base.BaseActivity;
import vn.com.misa.cukcuklitever1.dialog.DialogPriceFood;

public class EditFoodActivity extends BaseActivity implements View.OnClickListener, IEditFoodContract.IView, DialogPriceFood.OnInputListener {


    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.etNameFood)
    EditText etNameFood;
    @BindView(R.id.tvPriceFood)
    TextView tvPriceFood;
    @BindView(R.id.tvUnitFood)
    TextView tvUnitFood;
    @BindView(R.id.ivColorFood)
    ImageView ivColorFood;
    @BindView(R.id.ivIconFood)
    ImageView ivIconFood;
    @BindView(R.id.cbStatus)
    CheckBox cbStatus;
    @BindView(R.id.btnDelete)
    Button btnDelete;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    private IEditFoodContract.IPresenter mPresenter;
    private Toast mToast;
    private int mPrice;  //Lưu giá bán khi thay đổi
    int id;
    @Override
    protected int getIdLayout() {
        return R.layout.activity_edit_food;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvTitleToolbar.setText("" + getString(R.string.edit_food));
        mPresenter = new EditFoodPresenter(this, this);
        setupView();

    }

    /**
     * update dữ liệu được gửi đến activity
     */
    private void setupView() {
        if (getIntent()!=null){
            id=getIntent().getIntExtra("ID",0);
            etNameFood.setText(""+getIntent().getStringExtra("NAME"));
            mPrice=getIntent().getIntExtra("PRICE",0);
            tvPriceFood.setText(""+String.format("%,d",mPrice));
            tvUnitFood.setText("" + getIntent().getStringExtra("UNIT"));
            ivColorFood.setBackgroundColor( Color.parseColor(""+getIntent().getStringExtra("COLOR")));
            ivIconFood.setBackgroundColor( Color.parseColor(""+getIntent().getStringExtra("COLOR")));
            Glide.with(this).asBitmap().load("" + getIntent().getStringExtra("ICON")).into(ivIconFood);
            cbStatus.setChecked(getIntent().getBooleanExtra("STATUS",false));
        }
        btnAdd.setOnClickListener(this);
        tvPriceFood.setOnClickListener(this);
        tvUnitFood.setOnClickListener(this);
        ivColorFood.setOnClickListener(this);
        ivIconFood.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    /**
     * Lấy dữ liệu user nhập vào và kiểm tra
     */
    private void getInput() {
        String name = etNameFood.getText().toString().trim();
        int price;
        try {
            price = Integer.parseInt(tvPriceFood.getText().toString().trim());
        } catch (NumberFormatException e) {
            price = mPrice;
        }
        String unit = tvUnitFood.getText().toString().trim();
        String color = "#" + Integer.toHexString(ContextCompat.getColor(this, R.color.main_color) & 0x00ffffff);
        String icon = "file:///android_asset/icon/ic_default.png";
        boolean status = cbStatus.isChecked();
        mPresenter.checkInput(id,name, price, unit, color, icon,status);
    }

    /**
     * tạo menu cất khi muốn lưu thông tin nhập vào
     * create by lvhung on 5/28/2019
     *
     * @param menu menu gồm item cất
     * @return menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_get, menu);
        return true;
    }

    /**
     * Xử lý các sự kiện khi chọn vào menu option
     * create by lvhung on 5/28/2019
     *
     * @param item 1 lựa chọn trong menu
     * @return event
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuGet:
                getInput();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                getInput();
                break;
            case R.id.btnDelete:
                mPresenter.deleteFood(id);
                break;
            case R.id.tvPriceFood:
                DialogPriceFood dialog = DialogPriceFood.newInstance();
                dialog.show(getSupportFragmentManager(), "Price");
                break;
            case R.id.tvUnitFood:
                showToast("Đang thi công");
                break;
            case R.id.ivColorFood:
                showToast("Đang thi công");
                break;
            case R.id.ivIconFood:
                showToast("Đang thi công");
                break;
        }
    }

    /**
     * Hiển thị thông báo lỗi lên màn hình
     *
     * @param message nội dung
     */
    private void showToast(String message) {
        mToast = Toast.makeText(this, "" + message, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 200);
        mToast.show();
    }

    /**
     * Chỉnh sửa hoặc xóa thành công
     * @param message   thông tin chi tiết
     */
    @Override
    public void onSucessful(String message) {
        showToast(""+message);
        finish();
    }

    /**
     * Chỉnh sửa hoặc xóa thất bại
     * @param message
     */
    @Override
    public void onFail(String message) {
        showToast(""+message);
    }

    /**
     *Thông báo lỗi tên món chưa có gì
     */
    @Override
    public void nameFoodError() {
        showToast(""+getString(R.string.name_err));
    }

    /**
     * Thông báo lỗi về đơn vị tính
     */
    @Override
    public void unitFoodError() {
        showToast(""+getString(R.string.unit_err));
    }

    /**
     * Lấy dữ liệu được gửi về từ dialog
     *
     * @param input giá đã nhập
     */
    @Override
    public void sendInput(int input) {
        mPrice = input;
        tvPriceFood.setText(String.format("%,d", input));
    }
}
