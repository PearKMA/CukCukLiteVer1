package vn.com.misa.cukcuklitever1.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import vn.com.misa.cukcuklitever1.R;

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getIdLayout());
        setupToolbar();
        bindViews();
        initView();
    }

    /**
     * Triển khai các view
     */
    public void initView() {

    }

    /**
     * Nếu activity có toolbar nó sẽ tự động add
     */
    public void setupToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * các view con sẽ tự động triển khai butter knife
     */
    private void bindViews() {
        ButterKnife.bind(this);
    }

    /**
     * Trả về toolbar nếu class con cần tới
     *
     * @return toolbar
     */
    @Nullable
    public Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * lấy id layout mà class con sử dụng
     *
     * @return id để setContentView
     */
    protected abstract int getIdLayout();


}
