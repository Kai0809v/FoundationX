# 
我在这个项目中，在fragment使用视图绑定（自动生成绑定类），创建布局的步骤是这样的：
- activity java  -> activity xml（或自动绑定，规则如下），内有fragment（作为容器），包括了不同fragments组成的navGraph（@navigation/··.xml，包含导航）
- fragment java -> 绑定对应xml文件，生成绑定类（Fragment+Binding），对fragment内部的控件进行操作，其实也可以在activity中一块操作，只是在fragment中

```
public class AccountFragment extends Fragment {
    private FragmentAccountBinding binding;
……
```
- 这是根据对应xml文件自动生成的，fragment_account-->FragmentAccount+Binding

