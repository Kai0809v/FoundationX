package com.xcu.kai.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// User.java
@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "account")
    private String account;

    @ColumnInfo(name = "password")
    private String password; // 实际中应存储哈希值

    // 构造方法/getter/setter
    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAccount() { return account; }
    public String getPassword() { return password; }
}