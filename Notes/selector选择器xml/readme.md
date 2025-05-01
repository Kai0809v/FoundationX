对于不同状态下的图标颜色的切换，可以使用选择器
```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/ic_home_filled" android:state_checked="true"/>
    <item android:drawable="@drawable/ic_home_outline" android:state_checked="false"/>
</selector>
```
