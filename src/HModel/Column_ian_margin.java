package HModel;

import java.util.Random;

//0,3,6,9,12,15,18,21,24,27,30,33,36,39,42,45,48,51,54,57,60,63,66,69,72,78,84
//86
// 起报时效列
// if t<=72, then i = t/3, 0<=i<=24; if t>72, then t=72+(i-24)*6, i=24+(t-72)/6, 25<=i<=52
public class Column_ian_margin extends Column_ian {
    //给定具体的数值点，返回取其值的点概率
    public double getPointP(int v) {
        if (v < 0 || v > 240) {
            return 0;
        }
        if (v <= 72) {
            if (v % 3 != 0) {
                return 0;
            } else {
                return 1 / 53.0;
            }
        } else {
            if ((v - 72) % 6 != 0) {
                return 0;
            } else {
                return 1 / 53.0;
            }
        }
    }

    //给定具体的数值范围，返回落在这段范围上的概率；方便起见，默认都是左闭右闭
    public double getRangeP(RangePair rangePair) {
        int start = rangePair.start;
        int end = rangePair.end;
        int sum = 0;
        int count_start = 0;
        while (count_start <= 240) {
            if (start <= count_start && count_start <= end) {
                sum++;
            }
            if (count_start < 72) {
                count_start += 3;
            } else {
                count_start += 6;
            }
        }
        return sum / 53.0;
    }


    //直接返回符合在该列上随机取值的一个取值点
    public int getPointV() {
        Random random = new Random();
        int index = random.nextInt(53); //[0,52]
        return parseFunc(index);
    }

    //直接返回符合在该列上随机查询的范围区间的起点（起点、长度随机）
    public RangePair getRangeV() {
        Random random = new Random();
        int index1 = random.nextInt(53); //[0,52]
        int index2 = random.nextInt(53); //[0,52]
        if(index1 > index2) {
            int tmp = index1;
            index1 = index2;
            index2 = tmp;
        } // index1<=index2
        RangePair rangePair = new RangePair(parseFunc(index1),parseFunc(index2));
        return rangePair;
    }

    //分段函数
    private int parseFunc(int index) {
        if(index<=24) {
            return index*3;
        }
        else {
            return 72+(index-24)*6;
        }
    }
}
