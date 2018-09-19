package test;

import HModel.Column_ian_level;
import HModel.Column_ian_margin;
import HModel.Column_ian_start;
import HModel.RangePair;

public class testParse {
    public static void main(String[] args) {
        Column_ian_start column_ian_start = new Column_ian_start();
        int index = column_ian_start.parseDate2Index(16022920);
        int date = column_ian_start.parseIndex2Date(index);
        System.out.println(index);
        System.out.println(date);

        System.out.println(column_ian_start.getPointV());
        RangePair rangePair = column_ian_start.getRangeV();
        System.out.println(rangePair.start +"-"+rangePair.end);

        System.out.println(column_ian_start.getPointP(16092208));
        System.out.println(column_ian_start.getRangeP(rangePair));
        System.out.println(column_ian_start.getRangeP(new RangePair(16010108,16013120)));
        System.out.println("");

        Column_ian_level column_ian_level = new Column_ian_level();
        System.out.println(column_ian_level.getPointV());
        System.out.println(column_ian_level.getPointV());
        RangePair rangePair_level = column_ian_level.getRangeV();
        System.out.println(rangePair_level.start +"-"+rangePair_level.end);
        System.out.println(column_ian_level.getRangeP(rangePair_level));

        Column_ian_margin column_ian_margin = new Column_ian_margin();
        System.out.println(column_ian_margin.getPointV());
        RangePair rangePair_margin = column_ian_margin.getRangeV();
        System.out.println(rangePair_margin.start +"-"+rangePair_margin.end);
        System.out.println(column_ian_margin.getRangeP(rangePair_margin));

    }

}
