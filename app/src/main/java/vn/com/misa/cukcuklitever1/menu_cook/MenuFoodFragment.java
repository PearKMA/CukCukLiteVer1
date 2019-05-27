package vn.com.misa.cukcuklitever1.menu_cook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.add_food.NewFoodActivity;
import vn.com.misa.cukcuklitever1.base.BaseFragment;
import vn.com.misa.cukcuklitever1.menu_cook.adapter.MenuFoodAdapter;
import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

/**
 * create by lvhung on 5/24/2019
 */
public class MenuFoodFragment extends BaseFragment implements IMenuFoodContract.View{
    public static final String TAG = "MenuFoodFragment";
    @BindView(R.id.lvListMenu)
    ListView lvListMenu;
    private MenuFoodAdapter mAdapter;
    private IMenuFoodContract.Presenter mPresenter;
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
     * @param view layout chính
     * @param savedInstanceState sao lưu trạng thái
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mPresenter = new MenuFoodPresenter(getActivity(),this);
        mPresenter.setFoodData(getActivity());
    }

    /**
     * Xử lý khi option menu được chọn
     *create by lvhung on 5/25/2019
     * @param item item menu được chọn
     * @return  hành dộng
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuAdd:
                mPresenter.addFood();
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
     * @param menu  menu
     * @param inflater  chuyển đổi layout thành dạng code
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
    public void showData(final ArrayList<Food> listFood) {
        if (mAdapter==null) {
            mAdapter = new MenuFoodAdapter(getActivity(), R.layout.item_menu_food, listFood);
            lvListMenu.setAdapter(mAdapter);
        }
        lvListMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.editFood(listFood.get(position));
            }
        });
    }

    /**
     * chuyển sang màn hình thêm món
     * create by lvhung on 5/25/2019
     */
    @Override
    public void showAddFood() {
        startActivity(new Intent(getActivity(), NewFoodActivity.class));
    }

    /**
     * chuyển sang màn hình sửa/xóa món
     * create by lvhung on 5/25/2019
     */
    @Override
    public void showEditFood(Food food) {
        Toast.makeText(getActivity(),food.getName()+"",Toast.LENGTH_SHORT).show();
    }
}
