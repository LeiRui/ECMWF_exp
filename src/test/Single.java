package test;

import HModel.Column_ian;
import HModel.Column_ian_level;
import HModel.Column_ian_margin;
import HModel.Column_ian_start;
import SA.Unify;
import common.Constant;
import query.QueryPicture;
import replicas.AckSeq;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Single {
    public static void main(String[] args) {
        // 数据分布参数
        BigDecimal totalRowNumber = new BigDecimal(Constant.dataNum);
        List<Column_ian> CKdist = new ArrayList<Column_ian>();
        CKdist.add(new Column_ian_level());  //137
        CKdist.add(new Column_ian_start());  //732
        CKdist.add(new Column_ian_margin()); //53
        int ckn = CKdist.size();

        int[] qpernum = new int[]{1,1,1};
        QueryPicture queryPicture = new QueryPicture(qpernum, 150);

        //控制变量
        int X = 3;

        Unify unify = new Unify(totalRowNumber,
                ckn, CKdist,
                Constant.rowSize, Constant.fetchRowCnt, Constant.costModel_k, Constant.costModel_b, Constant.cost_session_around, Constant.cost_request_around,
                queryPicture,
                X);
        unify.isDiffReplicated = true;
        unify.calculate(new AckSeq[]{new AckSeq(new int[]{3,1,2}),
                new AckSeq(new int[]{2,3,1}),
                new AckSeq(new int[]{2,1,3})
        });


    }
}
