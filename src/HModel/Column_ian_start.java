package HModel;

// 起报时刻列 从16010108/20到16123108/20

import java.util.Random;

public class Column_ian_start extends Column_ian{
    private static int[] months = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};

    //给定具体的数值点，返回取其值的点概率
    public double getPointP(int p){
        //TODO 检查不在日期规范之内的输入。现在就暂时靠内部调用保证不会出现非法输入
        return 1.0/732; // 2016年是闰年，2月有29天。732=366*2
    }

    //给定具体的数值范围，返回落在这段范围上的概率；方便起见，默认都是左闭右闭
    public double getRangeP(RangePair rangePair){
        //TODO 默认区间的端点也都是在取值点上的，没有非法调用
        int index1 = parseDate2Index(rangePair.start);
        int index2 = parseDate2Index(rangePair.end);
        return (index2-index1+1)/732.0;
    }


    //直接返回符合在该列上随机取值的一个取值点
    public int getPointV(){
        Random random = new Random();
        int index = random.nextInt(732)+1;
        return parseIndex2Date(index);
    }

    //直接返回符合在该列上随机查询的范围区间的起点（起点、长度随机）
    public RangePair getRangeV(){
        Random random = new Random();
        int index1 = random.nextInt(732)+1; //[1,732]
        int index2 = random.nextInt(732)+1; //[1,732]
        if(index1 > index2) {
            int tmp = index1;
            index1 = index2;
            index2 = tmp;
        } // index1<=index2
        RangePair rangePair = new RangePair(parseIndex2Date(index1),parseIndex2Date(index2));
        return rangePair;
    }

    public int parseDate2Index(int date) {
        date -= 16000000;
        int mon = date/10000;
        date -= mon*10000;
        int day = date/100;
        date -= day*100;

        int index = 0;
        for(int i=0;i<mon-1;i++) {
            index += months[i]*2;
        }
        if(day!=1) {
            index += (day-1)*2;
        }
        if(date==8) {
            index += 1;
        }
        else {
            index += 2;
        }
        return index;
    }

    public int parseIndex2Date(int index) { //TODO index非法检查先不管了
        int sum = 0;
        int mon = 1;
        while(sum < index) {
            sum += months[mon-1]*2;
            mon++;
        }
        mon--; // 月份确定
        sum -= months[mon-1]*2;
        int remains = index - sum;
        int day = (int)Math.ceil(remains/2.0);
        int res= 16000000+mon*10000+day*100;
        if(remains%2==0) {
            return res+20;
        }
        else {
            return res+8;
        }
    }
}
