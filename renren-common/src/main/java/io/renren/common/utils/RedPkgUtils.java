package io.renren.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class RedPkgUtils {
    /*
    remainSize 红包个数
    remainMoney 红包金额
    */
    public static BigDecimal getRandomMoney(int remainSize, BigDecimal remainMoney) {
        if (remainSize == 1) {
            return remainMoney.setScale(2, RoundingMode.HALF_UP);
        }
        Random random = new Random();
        double luck = random.nextGaussian();
        BigDecimal mean = remainMoney.divide(new BigDecimal(remainSize), 2, RoundingMode.HALF_UP);
        BigDecimal money = mean.add(mean.multiply(new BigDecimal(luck).multiply(new BigDecimal("0.9"))));
        money = money.setScale(2, RoundingMode.FLOOR);
        money = money.compareTo(new BigDecimal("0.01")) < 0 ? new BigDecimal("0.01") : money;

        /*计算出红包金额后，还要考虑剩余金额是否足够剩下的人分，至少每人0.01*/
        /*剩余红包金额*/
        BigDecimal residueMoney = remainMoney.subtract(money);
        /*剩余红包个数*/
        int newRemainPeople = remainSize - 1;
    /* 如果剩余红包金额减去本次计算出的红包值，所得值 / 剩余红包个数 小于 0.01
       有三种解决方案
        1.递归
        2.直接取0.01
        3.剩余红包金额 - （剩余红包数 * 0.01）所得的值，在这个值的区间内取随机数即可。
        我比较懒，直接用第二种。有人说为什么不用第一种，因为递归会带来性能问题。
*/
        BigDecimal result2 = residueMoney.divide(new BigDecimal(newRemainPeople), 3, RoundingMode.HALF_UP);
        if (result2.compareTo(new BigDecimal("0.01")) < 0) {
            money = new BigDecimal("0.01");
        }
        return money;
    }

    public static void main(String[] args) {
        BigDecimal bigDecimala = BigDecimal.valueOf(100);
        int ppp = 0;
        for (int p = 0; p < 10000000; p++) {
            BigDecimal bigDecimal = bigDecimala;
            int count = 10;
            //第一个人获取
            BigDecimal sumR = new BigDecimal(0);
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                BigDecimal randomMoney = getRandomMoney(count,bigDecimal);
                bigDecimal = bigDecimal.subtract(randomMoney);//抢完后剩余的
                sumR = sumR.add(randomMoney);
                count = count - 1;//剩余份数
                sum = sum  + 1;
            }
            if(bigDecimala.compareTo(sumR) == 0){

            }else{
                ppp = ppp + 1;
            }

        }
        System.out.println("分不均的情况出现:"+ppp+"次");
    }
}
