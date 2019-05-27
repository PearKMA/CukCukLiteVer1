package vn.com.misa.cukcuklitever1.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.base.BaseActivity;
import vn.com.misa.cukcuklitever1.menu_cook.MenuFoodFragment;
import vn.com.misa.cukcuklitever1.view_custom.CircleImageView;

/**
 * create by lvhung on 5/24/2019
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, IMainActivityContract.View{

    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;            //hiển thị tên fragment đang hiển thị
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;      //layout chính làm việc với các fragment
    private CircleImageView ivAvatar;   //avatar của user
    private ImageView ivLoginFrom;      //ảnh hiển thị cho biết người dùng đăng nhâp từ face, google hay sđt
    TextView tvNameUser;                //tên user
    TextView tvEmail;                   //email user
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;          //
    @BindView(R.id.navView)            //menu
    NavigationView navView;             //

    private IMainActivityContract.Presenter presenter;

    /**
     * get layout id để set content view
     * @return
     */
    @Override
    protected int getIdLayout() {
        return R.layout.activity_main;
    }


    /**
     * setup các view
     * @param savedInstanceState
     */
    @Override
    public void initView(Bundle savedInstanceState) {
        ivAvatar = findViewById(R.id.ivAvatar); //butter knife không bind được
        ivLoginFrom = findViewById(R.id.ivLoginFrom);
        tvNameUser = findViewById(R.id.tvNameUser);
        tvEmail = findViewById(R.id.tvEmail);
        presenter=new MainActivityPresenter(this);
        setUpNavDrawer(savedInstanceState);
    }

    /**
     * setup navigation drawer
     * @param savedInstanceState lưu trạng thái
     */
    private void setUpNavDrawer(Bundle savedInstanceState) {
        navView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, getToolbar(),
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            presenter.onMenuSelected(this);
            navView.setCheckedItem(R.id.nav_menu);
        }
    }

    /**
     * đóng navigation drawer đang mở khi nhấn back
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Xử lý item được chọn trong navigation drawer
     * @param menuItem  item trong menu
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_sale:
                tvTitleToolbar.setText(""+getString(R.string.sale_nav));
                Toast.makeText(this,"Đang thi công!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_menu:
                presenter.onMenuSelected(this);
                break;
            case R.id.nav_report:
                tvTitleToolbar.setText(""+getString(R.string.report_nav));
                Toast.makeText(this,"Đang thi công!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_sync:
                Toast.makeText(this,"Đang thi công!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Toast.makeText(this,"Đang thi công!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_link:
                Toast.makeText(this,"Đang thi công!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_notification:
                Toast.makeText(this,"Đang thi công!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this,"Đang thi công!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rate:
                Toast.makeText(this,"Đang thi công!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_feedback:
                Toast.makeText(this,"Đang thi công!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_info:
                Toast.makeText(this,"Đang thi công!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setPassword:
                Toast.makeText(this,"Đang thi công!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Toast.makeText(this,"Đang thi công!",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    /**
     * đóng navigation drawer khi chọn menu hoặc back
     */
    @Override
    public void closeNavDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    /**
     * add MenuFoodFragment vào activity
     */
    @Override
    public void showMenuFragment(String title) {
        tvTitleToolbar.setText(title);
        getSupportFragmentManager()
                .beginTransaction().
                disallowAddToBackStack()
                .replace(R.id.fragmentContainer, MenuFoodFragment.newInstance(), MenuFoodFragment.TAG).commit();
    }


}
