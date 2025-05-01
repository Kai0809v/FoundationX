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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());//绑定布局
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // 设置标签显示模式，显示标签
        navView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

        Menu menu = navView.getMenu();
        menu.getItem(0).setTitle(null);
        menu.getItem(2).setTitle(null);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_account,R.id.navigation_home,  R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //上面这里定义了NavController，然后下面这里把它和BottomNavigationView绑定起来，
        //这是绑定顶部导航栏的，现在使用无actionbar，再使用这行代码会导致崩溃
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);//绑定底部导航栏

        // 添加选中监听器
        navView.setOnItemSelectedListener(item -> {
            // 重置所有项的标题可见性

            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setTitle(null);
            }
            // 设置当前选中项的标题
            // 根据itemId设置对应的标题
            if (item.getItemId() == R.id.navigation_home) {
                item.setTitle(getString(R.string.title_home));
            } else if (item.getItemId() == R.id.navigation_account) {
                item.setTitle(getString(R.string.title_account));
            } else if (item.getItemId() == R.id.navigation_notifications) {
                item.setTitle(getString(R.string.title_notifications));
            }

            return NavigationUI.onNavDestinationSelected(item, navController);
        });
    }

}