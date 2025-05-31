package com.xcu.kai.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

// UserDao.java
@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM users WHERE account = :account")
    User getUserByAccount(String account);

    @Delete
    void delete(User user);
}