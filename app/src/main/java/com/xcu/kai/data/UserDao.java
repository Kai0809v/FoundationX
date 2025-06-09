package com.xcu.kai.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// UserDao.java
@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM users WHERE account = :account")
    User getUserByAccount(String account);

    @Query("SELECT * FROM users LIMIT :limit OFFSET :offset")
    List<User> getUsers(int limit, int offset);

    @Query("SELECT COUNT(*) FROM users")
    int getTotalCount();

    @Delete
    void delete(User user);

    @Delete
    void deleteUsers(List<User> users);
}