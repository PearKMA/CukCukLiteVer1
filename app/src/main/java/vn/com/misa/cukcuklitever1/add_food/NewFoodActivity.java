package vn.com.misa.cukcuklitever1.add_food;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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

import java.util.Objects;

import butterknife.BindView;
import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.base.BaseActivity;
import vn.com.misa.cukcuklitever1.convert_string.ConvertCurrencyAdapter;
import vn.com.misa.cukcuklitever1.convert_string.IPriceTarget;
import vn.com.misa.cukcuklitever1.dialog_price_calculator.DialogPriceFood;
import vn.com.misa.cukcuklitever1.edit_unit.UnitFoodActivity;

/**
 * Hiển thị các thông tin thêm mới món
 */
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
    private double mPrice = 0;  //Lưu giá bán khi thay đổi
    private IPriceTarget mPriceTarget; //Chuyển đổi double sang dạng tiền tệ
    private SharedPreferences pref;
    /**
     * Xử lý các setup & sự kiện cho view
     * Edited by lvhung at 5/30/2019
     */
    @Override
    public void initView() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvTitleToolbar.setText(getString(R.string.add_food));
        mPresenter = new NewFoodPresenter(this, this);
        mPriceTarget = new ConvertCurrencyAdapter();
        btnAdd.setOnClickListener(this);
        tvPriceFood.setOnClickListener(this);
        tvUnitFood.setOnClickListener(this);
        ivColorFood.setOnClickListener(this);
        ivIconFood.setOnClickListener(this);
    }

    /**
     * Lấy dữ liệu user nhập vào và kiểm tra
     * Edited by lvhung at 5/30/2019
     */
    private void getInput() {
        String name = etNameFood.getText().toString().trim();
        double price;
        price = mPresenter.convertToDouble(tvPriceFood.getText().toString().trim());
        String unit = tvUnitFood.getText().toString().trim();
        String color = "#0973b9";
        String icon = "ic_default.png";
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
     * Edited by lvhung at 5/30/2019
     */
    @Override
    public void nameFoodError() {
        showToast("" + getString(R.string.name_err));
    }

    /**
     * Thông báo khi đơn vị tính bị lỗi
     * Edited by lvhung at 5/30/2019
     */
    @Override
    public void unitFoodError() {
        showToast("" + getString(R.string.unit_err));
    }

    /**
     * Thông báo thêm thành công
     * Edited by lvhung at 5/30/2019
     */
    @Override
    public void addSuccessful() {
        showToast("" + getString(R.string.add_successfull));
        finish();
    }

    /**
     * Thông báo thêm thất bại
     * Edited by lvhung at 5/30/2019
     *
     * @param message kết quả trả về
     */
    @Override
    public void addFoodFail(String message) {
        showToast(message);
    }

    /**
     * Hiển thị thông báo lỗi lên màn hình
     * Edited by lvhung at 5/30/2019
     *
     * @param message thông báo
     */
    private void showToast(String message) {
        Toast mToast = Toast.makeText(this, "" + message, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 200);
        mToast.show();
    }

    /**
     * Hủy view
     * Edited by lvhung at 5/30/2019
     */
    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Xử lý sự kiện cho từng nút
     * Edited by lvhung at 5/30/2019
     *
     * @param v view gán sự kiện
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                getInput();
                break;
            case R.id.tvPriceFood:
                DialogPriceFood dialog = DialogPriceFood.newInstance(mPrice);
                dialog.show(getSupportFragmentManager(), "Price");
                break;
            case R.id.tvUnitFood:
                Intent intent = new Intent(NewFoodActivity.this, UnitFoodActivity.class);
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
     * Lấy dữ liệu được gửi về từ dialog
     * Edited by lvhung at 5/30/2019
     *
     * @param input giá được nhập
     */
    @Override
    public void sendInput(double input) {
        mPrice = input;
        tvPriceFood.setText(mPriceTarget.getPriceString(mPrice));
    }

    /**
     * Lấy đơn vị tính cuối cùng được lưu
     * Edited by lvhung at 5/30/2019
     */
    @Override
    protected void onResume() {
        super.onResume();
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        tvUnitFood.setText(pref.getString("UNIT", ""));
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
                pref.edit().putString("UNIT", tvUnitFood.getText().toString()).apply();
            }
        }
    }
}
