package vn.com.misa.cukcuklitever1.menu_cook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.base.BaseFragment;
import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

/**
 * create by lvhung on 5/24/2019
 */
public class MenuFoodFragment extends BaseFragment implements IMenuFoodContract.View{
    public static final String TAG = "MenuFoodFragment";
    @BindView(R.id.lvListMenu)
    ListView lvListMenu;


    /**
     * Khởi tạo fragment
     *create by lvhung on 5/25/2019
     * @return
     */
    public static MenuFoodFragment newInstance() {
        Bundle args = new Bundle();
        MenuFoodFragment fragment = new MenuFoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Xử lý các view trong fragment, thiết lập option menu
     *create by lvhung on 5/25/2019
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
    }

    /**
     * Xử lý khi option menu được chọn
     *create by lvhung on 5/25/2019
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_add:
                Toast.makeText(getActivity(), "Đang thi công", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * lấy id layout để setContentView
     *create by lvhung on 5/25/2019
     * @return id layout
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu;
    }

    /**
     * Tạo 1 option menu có chức năng thêm
     *create by lvhung on 5/25/2019
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * Show danh sách các món trong thực đơn
     * create by lvhung on 5/25/2019
     * @param listFood data cho listview
     */
    @Override
    public void showData(ArrayList<Food> listFood) {

    }

    /**
     * chuyển sang màn hình thêm món
     * create by lvhung on 5/25/2019
     */
    @Override
    public void showAddFood() {

    }

    /**
     * chuyển sang màn hình sửa/xóa món
     * create by lvhung on 5/25/2019
     */
    @Override
    public void showEditFood() {

    }
}
