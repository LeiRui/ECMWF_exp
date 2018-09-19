package HModel;

/*
  专门改成适用于【层次、起报时刻、起报时效】这种枚举类型的
 */
public abstract class Column_ian {
    //给定具体的数值点，返回取其值的点概率
    public abstract double getPointP(int p);
    //给定具体的数值范围，返回落在这段范围上的概率；方便起见，默认都是左闭右闭
    public abstract double getRangeP(RangePair rangePair);


    //直接返回符合在该列上随机取值的一个取值点
    public abstract int getPointV();
    //直接返回符合在该列上随机查询的范围区间的起点（起点、长度随机）
    public abstract RangePair getRangeV();

}
