### 思路：

这道题需要通过三分才能确定走向。

### 注意

这道题大部分的类型也是`double`类型。

`%.0f`可以用于小数四舍五入的取整。

题目中求最小，是个先减后增的凹函数，三分的时候如何收敛，什么条件应该`l=m`，什么条件`r=mm`。

 别傻乎乎的照这个凸函数做了。

### 关于结果

对于区间不等分，将`mid`等分再等分得到`mmid`。

最后的结果返回`mid`的`getDistant`值。

按照上面两步做，会比较精确，即使`r-l>1e-4`也可以很好的出来结果。