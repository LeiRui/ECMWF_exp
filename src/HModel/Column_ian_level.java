package HModel;

import java.util.Random;

// 层次列
public class Column_ian_level extends Column_ian {
    //给定具体的数值点，返回取其值的点概率
    public double getPointP(int p) {
        if (p < 1 || p > 137) {
            return 0;
        } else {
            return 1.0 / 137;
        }
    }

    //给定具体的数值范围，返回落在这段范围上的概率；方便起见，默认都是左闭右闭
    public double getRangeP(RangePair rangePair) {
        if (rangePair.end <= rangePair.start) {
            return 0;
        }
        if (rangePair.end <= 1) {
            return 0;
        }
        if (rangePair.start >= 137) {
            return 0;
        }
        int min = rangePair.start > 1 ? rangePair.start : 1;
        int max = rangePair.end < 137 ? rangePair.end : 137;
        double res = (max - min + 1) / 137.0;
        return res;
    }


    //直接返回符合在该列上随机取值的一个取值点
    public int getPointV() {
        Random random = new Random();
        return random.nextInt(137) + 1;
    }

    //直接返回符合在该列上随机查询的范围区间的起点（起点、长度随机）
    public RangePair getRangeV() {
        Random random = new Random();
        int start = random.nextInt(137) + 1;
        int end = random.nextInt(137) + 1;
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        RangePair rangePair = new RangePair(start, end);
        return rangePair;
    }

}
