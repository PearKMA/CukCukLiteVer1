package vn.com.misa.cukcuklitever1.menu_cook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import vn.com.misa.cukcuklitever1.R;
import vn.com.misa.cukcuklitever1.base.BaseFragment;

/**
 * create by lvhung on 5/24/2019
 */
public class MenuFragment extends BaseFragment {
    public static final String TAG="MenuFragment";

    /**
     * Khởi tạo fragment
     * @return
     */
    public static MenuFragment newInstance() {
        Bundle args = new Bundle();
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Xử lý các view trong fragment, thiết lập option menu
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_add:
                Toast.makeText(getActivity(),"Đang thi công",Toast.LENGTH_SHORT).show();
                return true;
                default:
            return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
