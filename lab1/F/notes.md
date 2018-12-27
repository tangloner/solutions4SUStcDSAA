### 思路

这道题基本是通过暴力来做，但需要进行一定的预处理。

最重要的处理是通过一个26*26大小的矩阵，表示相邻字母的关系。

例如

对于strength来说：

有一个s与t相邻，则在矩阵matrx\[s\]\[t\]位置+1

其他相邻字母同理，去掉元音相邻的字母。

之后进行暴力即可。

### Coding能力

在进行暴力的过程中，需要通过二进制位的形式进行找到所有的可能组合。

于是需要熟练的掌握左移右移操作。

此外，做多了字符的题目，还需要熟练地通过`字符-'a'`的方式得到下标。

### 注意事项

第一遍做错的原因是直接狸猫换太子，用辅音字母拼成了个新的字符串来计算。

但题目的要求是需要考虑元音字母，因为元音字母隔断了辅音的相邻。