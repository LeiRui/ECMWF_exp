package common;

public class Constant {
    public static String dataNum = "1000000";

    public static boolean isGetSqls = true ; // TODO 记得改！！!!!!

    public static boolean isDiffReplica = true; // TODO 记得改这个！！！

    public static int RF = 3;

    public static String ks = "leslie_4000";
    public static boolean[] isInt = new boolean[]{true, true, true, true, true, true, true, true}; // TODO!!
    public static String[] ckname = new String[]{"level", "start","margin"}; // TODO!!
    public static String[] cf = new String[]{"df1", "df2", "df3"};
    public static int[] pkey = new int[]{2, 3, 4};


    public static String SArecord = "SA_process_record.csv";

    public static boolean isAccelerate = true;

    public static boolean isPrint = true;

    public static boolean isRecordProcess = true;

    // 数据存储参数
    public static int rowSize = 100;

    //单查询代价模型参数
    public static int fetchRowCnt = 100;
    public static double costModel_k = 2.024;
    public static double costModel_b = 16862.946;
    public static double cost_session_around = 242.793;
    public static double cost_request_around = 808.423;
}
