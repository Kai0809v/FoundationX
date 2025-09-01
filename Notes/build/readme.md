# build
## app/build.gradle.*(kts/groovy)
配置res资源目录 ，在此文件的Android{ }块内添加如下代码

```kts
sourceSets {
    named("main") {
        res.srcDirs(
            "src/main/res",
            "src/main/res-2"
        )
    }
}
```
这样可以整合两个资源目录，有重复者以后者为准，这样遵循标准且易于管理
### 怎么添加多个资源目录？
右键main->New->Directory->res-2或选择resources再根据需要命名
