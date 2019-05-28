package vn.com.misa.cukcuklitever1.edit_unit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.base.BaseActivity;

public class UnitFoodActivity extends BaseActivity {


    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.lvUnit)
    ListView lvUnit;
    @BindView(R.id.btnAdd)
    Button btnAdd;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_unit_food;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvTitleToolbar.setText("" + getString(R.string.title_unit));
    }
}
