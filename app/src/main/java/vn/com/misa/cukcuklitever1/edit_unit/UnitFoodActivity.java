package vn.com.misa.cukcuklitever1.edit_unit;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.base.BaseActivity;

/**
 * Chỉnh sửa đơn vị tính
 * create by lvhung on 5/29/2019
 */
public class UnitFoodActivity extends BaseActivity {


    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.lvUnit)
    ListView lvUnit;
    @BindView(R.id.btnAdd)
    Button btnAdd;

    /**
     * lấy id layout để setContentView
     * create by lvhung on 5/29/2019
     * @return  id
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvTitleToolbar.setText("" + getString(R.string.title_unit));
    }
}
