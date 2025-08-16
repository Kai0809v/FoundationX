# 自制类的导入
自制工具类文件顶部的包声明要与文件在项目中的实际路径相匹配<br>
例如我将类LinkSpanUtil.java放在com.xcu.kai.utilities包下，那么这个类的顶部声明应该是：
```
package com.xcu.kai.utilities;
```

# 导入类的原则
当需要导入的类有多个选项时，通常遵循以下原则：
优先选择androidx包下的类，因为它是Android官方推荐的包，包含了最新的功能和改进。
如果没有androidx包下的类，选择android包下的官方类，这些类通常是Android官方提供的，具有广泛的兼容性。
如果以上两个选项都没有，考虑使用java.util等标准库类，这些类是Java标准库提供的，具有跨平台的特性。
需要注意的是，选择合适的类需要考虑API级别要求，以确保代码在不同的Android版本上都能正常运行。

其他常见类的选择原则：
优先androidx包下的类
次选android包下的官方类
最后考虑java.util等标准库类（需注意API级别要求）