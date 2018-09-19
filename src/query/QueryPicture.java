package query;

import HModel.Column_ian;
import HModel.RangePair;
import common.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 查询集的描述参数类
 */
public class QueryPicture {
    // 排序键个数，也即单列范围查询不同列数量
    public int ckn;
    // 单查询列之间的比重分布 以一批次内执行各几次为描述方式
    public int[] qpernum; // [ckn]

    public int totalQueryBatchNum;
    public int totalQueryNum;

    public QueryPicture(int[] qpernum, int totalQueryBatchNum) {
        this.qpernum = qpernum;
        this.totalQueryBatchNum = totalQueryBatchNum;
        ckn = qpernum.length;
        int sum = 0;
        for (int i = 0; i < qpernum.length; i++) {
            sum += qpernum[i];
        }
        totalQueryNum = sum * totalQueryBatchNum;
    }

    // 输入各列真实分布参数、总查询批次，输出符合描述参数分布的模拟确定查询参数集合
    public List<RangeQuery[]> getDefinite(List<Column_ian> CKdist) {
        List<RangeQuery[]> res = new ArrayList<>();
        for (int i = 0; i < ckn; i++) {
            RangeQuery[] res_ = new RangeQuery[totalQueryBatchNum * qpernum[i]];
            res.add(res_);
        }

        for (int i = 0; i < ckn; i++) {
            Column_ian columnIan = CKdist.get(i);
            RangeQuery[] res_ = res.get(i);
            int singleSum = res_.length;
            for (int j = 0; j < singleSum; j++) {
                // 确定这个batch内第i个ck列的单范围查询的描述参数数值
                RangePair rangePair = columnIan.getRangeV();
                int qck_r1_abs = rangePair.start;
                int qck_r2_abs = rangePair.end;

                int[] qck_p_abs = new int[ckn];
                for (int z = 0; z < ckn; z++) {// 单查询对点查列视为均匀随机取值吧
//                    qck_p_abs[z] = columnIan.getPointV();
                    qck_p_abs[z] = CKdist.get(z).getPointV();
                }

                res_[j] = new RangeQuery(i + 1, qck_r1_abs, qck_r2_abs, true, true, qck_p_abs);
            }
        }
        return res;
    }

    public static String getSql(String ks, String cf, int pkey, List<Column_ian> CKdist, RangeQuery q) {
        String q_format = "select count(*) from " + ks + "." + cf + " where name = 'FRACTION_OF_CLOUD_COVER' " +
                "and pk=" + pkey; //TODO 现在是复合分区键了
        int ckn = CKdist.size();
        for (int k = 0; k < ckn; k++) {
            if (k == q.qckn - 1) { // qckn从1开始
                String tmp = " and %s>=%d and %s<=%d";
                q_format += String.format(tmp, Constant.ckname[k], (int) q.qck_r1_abs, Constant.ckname[k], (int) q.qck_r2_abs);
            } else {
                q_format += " and " + Constant.ckname[k] + "=" + (int) q.qck_p_abs[k];
            }
        }
        q_format += " allow filtering;";
        return q_format;
    }
}
