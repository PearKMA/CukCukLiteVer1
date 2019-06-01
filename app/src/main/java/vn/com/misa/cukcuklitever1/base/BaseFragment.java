package vn.com.misa.cukcuklitever1.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * base fragment
 * create by lvhung on 5/24/2019
 */
public abstract class BaseFragment extends Fragment {
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * unbind butter knie khi fragment bị hủy
     * Edited by lvhung at 5/30/2019
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * Lấy id layout được định nghĩa ở class con
     * Edited by lvhung at 5/30/2019
     * @return  id
     */
    protected abstract int getLayoutId();

}
