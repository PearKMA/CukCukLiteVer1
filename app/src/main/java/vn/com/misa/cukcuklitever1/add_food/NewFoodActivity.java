package vn.com.misa.cukcuklitever1.add_food;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.base.BaseActivity;
import vn.com.misa.cukcuklitever1.dialog.DialogPriceFood;

public class NewFoodActivity extends BaseActivity implements INewFoodContract.IView, View.OnClickListener, DialogPriceFood.OnInputListener {

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
    @BindView(R.id.btnAdd)
    Button btnAdd;
    private INewFoodContract.IPresenter mPresenter;
    private Toast mToast;
    private int mPrice;  //Lưu giá bán khi thay đổi

    /**
     * Xử lý các setup & sự kiện cho view
     *
     * @param savedInstanceState trạng thái
     */
    @Override
    public void initView(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvTitleToolbar.setText("" + getString(R.string.add_food));
        mPresenter = new NewFoodPresenter(this);
        btnAdd.setOnClickListener(this);
        tvPriceFood.setOnClickListener(this);
        tvUnitFood.setOnClickListener(this);
        ivColorFood.setOnClickListener(this);
        ivIconFood.setOnClickListener(this);
    }

    /**
     * Lấy dữ liệu user nhập vào và kiểm tra
     */
    private void getInput() {
        String name = etNameFood.getText().toString().trim();
        int price = 0;
        try {
            price = Integer.parseInt(tvPriceFood.getText().toString().trim());
        } catch (NumberFormatException e) {
            price = mPrice;
        }
        String unit = tvUnitFood.getText().toString().trim();
        String color = "#" + Integer.toHexString(ContextCompat.getColor(this, R.color.main_color) & 0x00ffffff);
        String icon = "file:///android_asset/icon/ic_default.png";
        mPresenter.checkInput(name, price, unit, color, icon);
    }

    /**
     * Lấy id layout để hiện
     * create by lvhung on 5/28/2019
     *
     * @return id
     */
    @Override
    protected int getIdLayout() {
        return R.layout.activity_new_food;
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
     * Thông báo khi tên món bị lỗi
     */
    @Override
    public void nameFoodError() {
        showToast("" + getString(R.string.name_err));
    }

    /**
     * Thông báo khi đơn vị tính bị lỗi
     */
    @Override
    public void unitFoodError() {
        showToast("" + getString(R.string.unit_err));
    }

    /**
     * Thông báo thêm thành công
     */
    @Override
    public void addSuccessful() {
        showToast("" + getString(R.string.add_successfull));
        finish();
    }

    /**
     * Thông báo thêm thất bại
     *
     * @param message kết quả trả về
     */
    @Override
    public void addFoodFail(String message) {
        showToast(message);
    }

    /**
     * Hiển thị thông báo lỗi lên màn hình
     *
     * @param message
     */
    private void showToast(String message) {
        mToast = Toast.makeText(this, "" + message, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 200);
        mToast.show();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                getInput();
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
     * Lấy dữ liệu được gửi về từ dialog
     *
     * @param input
     */
    @Override
    public void sendInput(int input) {
        mPrice = input;
        tvPriceFood.setText(String.format("%,d", input));
    }
}
