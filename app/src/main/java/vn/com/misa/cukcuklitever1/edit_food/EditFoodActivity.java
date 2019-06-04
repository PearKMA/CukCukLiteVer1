package vn.com.misa.cukcuklitever1.edit_food;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
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

import java.util.Objects;

import butterknife.BindView;
import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.add_food.NewFoodActivity;
import vn.com.misa.cukcuklitever1.base.BaseActivity;
import vn.com.misa.cukcuklitever1.convert_string.ConvertCurrencyAdapter;
import vn.com.misa.cukcuklitever1.convert_string.IPriceTarget;
import vn.com.misa.cukcuklitever1.dialog_price_calculator.DialogPriceFood;
import vn.com.misa.cukcuklitever1.edit_unit.UnitFoodActivity;

/**
 * Chỉnh sửa món ăn trong thực đơn
 * create by lvhung on 5/29/2019
 */
public class EditFoodActivity extends BaseActivity implements View.OnClickListener,
        IEditFoodContract.IView, DialogPriceFood.OnInputListener {
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
    private double mLastPrice;  //Lưu giá bán khi thay đổi
    private String mLastColor, mLastIcon; //Lưu màu sắc, icon khi thay đổi
    private int mLastID; // lưu id
    private IPriceTarget mPriceTarget; //Chuyển đổi tiền

    /**
     * Lấy id layout
     * Edited by lvhung at 5/30/2019
     *
     * @return id để setContentView
     */
    @Override
    protected int getIdLayout() {
        return R.layout.activity_edit_food;
    }

    /**
     * Xử lý các view
     * Edited by lvhung at 5/30/2019
     */
    @Override
    public void initView() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvTitleToolbar.setText(getString(R.string.edit_food));
        mPresenter = new EditFoodPresenter(this, this);
        mPriceTarget = new ConvertCurrencyAdapter();
        setupView();

    }

    /**
     * update dữ liệu được gửi đến activity
     * Edited by lvhung at 5/30/2019
     */
    private void setupView() {
        //Lấy dữ liệu được gửi đến
        if (getIntent() != null) {
            mLastID = getIntent().getIntExtra("ID", 0);
            etNameFood.setText(getIntent().getStringExtra("NAME"));
            mLastPrice = getIntent().getDoubleExtra("PRICE", 0);
            tvPriceFood.setText(mPriceTarget.getPriceString(mLastPrice));
            tvUnitFood.setText(getIntent().getStringExtra("UNIT"));
            mLastColor = getIntent().getStringExtra("COLOR");
            ivColorFood.setBackgroundColor(Color.parseColor("" + mLastColor));
            ivIconFood.setBackgroundColor(Color.parseColor("" + mLastColor));
            mLastIcon = getIntent().getStringExtra("ICON");
            String PATH = "file:///android_asset/icon/";
            Glide.with(this).asBitmap().load(PATH + mLastIcon).into(ivIconFood);
            cbStatus.setChecked(getIntent().getBooleanExtra("STATUS", false));
        }
        etNameFood.setSelection(etNameFood.getText().length());
        btnAdd.setOnClickListener(this);
        tvPriceFood.setOnClickListener(this);
        tvUnitFood.setOnClickListener(this);
        ivColorFood.setOnClickListener(this);
        ivIconFood.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    /**
     * Lấy dữ liệu user nhập vào và kiểm tra
     * Edited by lvhung at 5/30/2019
     */
    private void getInput() {
        String name = etNameFood.getText().toString().trim();
        double price;
        try {
            String s = tvPriceFood.getText().toString().trim().replace(".", "");
            price = Double.parseDouble(s.replace(",", "."));
        } catch (NumberFormatException e) {
            price = mLastPrice;
        }
        String unit = tvUnitFood.getText().toString().trim();
        boolean status = cbStatus.isChecked();
        mPresenter.checkInput(mLastID, name, price, unit, mLastColor, mLastIcon, status);
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

    /**
     * Xử lý sự kiện của các view
     * Edited by lvhung at 5/30/2019
     *
     * @param v view được gán sự kiện
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                getInput();
                break;
            case R.id.btnDelete:
                mPresenter.deleteFood(mLastID);
                break;
            case R.id.tvPriceFood:
                DialogPriceFood dialog = DialogPriceFood.newInstance(mLastPrice);
                dialog.show(getSupportFragmentManager(), "Price");
                break;
            case R.id.tvUnitFood:
                Intent intent = new Intent(EditFoodActivity.this, UnitFoodActivity.class);
                intent.putExtra("UNIT", tvUnitFood.getText().toString().trim());
                startActivityForResult(intent,1);
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
     * Edited by lvhung at 5/30/2019
     *
     * @param message nội dung
     */
    private void showToast(String message) {
        Toast mToast = Toast.makeText(this, "" + message, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 200);
        mToast.show();
    }

    /**
     * Chỉnh sửa hoặc xóa thành công
     * Edited by lvhung at 5/30/2019
     *
     * @param message thông tin chi tiết
     */
    @Override
    public void onSucessful(String message) {
        finish();
    }

    /**
     * Chỉnh sửa hoặc xóa thất bại
     * Edited by lvhung at 5/30/2019
     *
     * @param message nội dung lỗi
     */
    @Override
    public void onFail(String message) {
        showToast("" + message);
    }

    /**
     * Thông báo lỗi tên món chưa có gì
     * Edited by lvhung at 5/30/2019
     */
    @Override
    public void nameFoodError() {
        showToast("" + getString(R.string.name_err));
    }

    /**
     * Thông báo lỗi về đơn vị tính
     * Edited by lvhung at 5/30/2019
     */
    @Override
    public void unitFoodError() {
        showToast("" + getString(R.string.unit_err));
    }

    /**
     * Lấy dữ liệu được gửi về từ dialog
     * Edited by lvhung at 5/30/2019
     *
     * @param input giá đã nhập
     */
    @Override
    public void sendInput(double input) {
        mLastPrice = input;
        tvPriceFood.setText(mPriceTarget.getPriceString(mLastPrice));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroyPresenter();
    }
    /**
     * Lấy kết quả trả về khi chọn đơn vị tính xong
     * Edited by lvhung at 5/30/2019
     * @param requestCode code gửi lên
     * @param resultCode code nhận lại
     * @param data dữ liệu
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==2){
            if (data!=null){
                tvUnitFood.setText(data.getStringExtra("UNIT"));
            }
        }
    }
}
