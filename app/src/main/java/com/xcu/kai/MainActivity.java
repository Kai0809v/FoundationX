package com.xcu.kai;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationBarView;
import com.xcu.kai.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int lastSelectedId = R.id.navigation_home; // 记录上次选中的ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());//绑定布局
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

        Menu menu = navView.getMenu();
        menu.getItem(0).setTitle(null);
        menu.getItem(2).setTitle(null);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_account, R.id.navigation_home, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //上面这里定义了NavController，然后下面这里把它和BottomNavigationView绑定起来，
        //这是绑定顶部导航栏的，现在使用无actionbar，再使用这行代码会导致崩溃
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);//绑定底部导航栏

        // 添加选中监听器，并添加防止快速重复点击的逻辑
        navView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            // 如果点击的是当前已选中的项，则忽略
            if (itemId == lastSelectedId) {
                return false;
            }

            // 更新最后选中的ID
            lastSelectedId = itemId;

            // 重置所有标题
            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setTitle(null);
            }

            // 设置新标题
            if (itemId == R.id.navigation_home) {
                item.setTitle(getString(R.string.title_home));
            } else if (itemId == R.id.navigation_account) {
                item.setTitle(getString(R.string.title_account));
            } else if (itemId == R.id.navigation_notifications) {
                item.setTitle(getString(R.string.title_notifications));
            }

            // 执行导航
            return NavigationUI.onNavDestinationSelected(item, navController);
        });

        // 保留目的地监听器（用于处理返回按钮等情况）
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            // 更新最后选中的ID
            lastSelectedId = destination.getId();

            // 重置所有标题
            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setTitle(null);
            }

            // 设置当前标题
            int destId = destination.getId();
            if (destId == R.id.navigation_home) {
                menu.findItem(R.id.navigation_home).setTitle(getString(R.string.title_home));
            } else if (destId == R.id.navigation_account) {
                menu.findItem(R.id.navigation_account).setTitle(getString(R.string.title_account));
            } else if (destId == R.id.navigation_notifications) {
                menu.findItem(R.id.navigation_notifications).setTitle(getString(R.string.title_notifications));
            }
        });
    }
}