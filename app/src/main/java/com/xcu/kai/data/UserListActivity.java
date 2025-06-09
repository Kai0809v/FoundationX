package com.xcu.kai.data;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcu.kai.R;
import com.xcu.kai.utilities.UserAdapter;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserListActivity extends AppCompatActivity {
    private UserAdapter adapter;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final int PAGE_SIZE = 20;
    private int currentPage = 0;
    // 添加分页控制标志
    private boolean isLoading = false;
    private boolean hasMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        Button btnDelete = findViewById(R.id.btn_delete);

        adapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // 添加分页滚动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                // 添加分页加载条件
                if (!isLoading && hasMore) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        loadUsers(currentPage + 1);
                    }
                }
            }
        });

        adapter.setOnItemLongClickListener((view, position) -> {
            adapter.toggleSelection(position);
            return true;
        });

        loadUsers(currentPage);

        btnDelete.setOnClickListener(v -> deleteSelectedUsers());
    }

    // 提取删除操作为独立方法
    private void deleteSelectedUsers() {
        List<User> selectedUsers = adapter.getSelectedUsers();
        if (selectedUsers.isEmpty()) return;

        executor.execute(() -> {
            AppDatabase.getInstance(this)
                    .userDao()
                    .deleteUsers(selectedUsers);

            runOnUiThread(() -> {
                adapter.clearSelected();
                // 优化：重新加载当前页而非总是第0页
                loadUsers(Math.max(0, currentPage));
            });
        });
    }

    private void loadUsers(int page) {
        if (isLoading) return;

        isLoading = true;
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(this);
            int total = db.userDao().getTotalCount();
            int offset = page * PAGE_SIZE;

            if (offset < total) {
                List<User> users = db.userDao().getUsers(PAGE_SIZE, offset);
                runOnUiThread(() -> {
                    if (page == 0) {
                        adapter.setUsers(users);
                    } else {
                        adapter.appendUsers(users);
                    }
                    currentPage = page;
                    hasMore = (offset + PAGE_SIZE) < total;
                    isLoading = false;
                });
            } else {
                runOnUiThread(() -> {
                    hasMore = false;
                    isLoading = false;
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 添加线程池关闭逻辑
        if (executor != null && !executor.isShutdown()) {
            executor.shutdownNow();
        }
    }
}
