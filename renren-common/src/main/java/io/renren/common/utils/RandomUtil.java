package io.renren.common.utils;


import io.renren.common.common.StaticData;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.UUID;


@Slf4j
public class RandomUtil {

    private static final Random random = new Random();

    public static String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 获取一个随机4位数的短信验证码
     */
    public static String getRandomSmsCode() {
        Random random = new Random();
        return random.nextInt(9000 - 1) + 1000 + "";
    }
    /**
     * 根据性别获取随机昵称
     * @param gender
     * @return
     */
    public static String getRandomNickname(int gender) {
        List<String> adj_words = null;
        List<String> nouns_words = null;
        switch (gender) {
            case 0:
                adj_words = (List<String>) StaticData.MALE_NICKNAME_WORDS.get("adj");
                nouns_words = (List<String>) StaticData.MALE_NICKNAME_WORDS.get("noun");
                break;
            case 1:
                adj_words = (List<String>) StaticData.FEMALE_NICKNAME_WORDS.get("adj");
                nouns_words = (List<String>) StaticData.FEMALE_NICKNAME_WORDS.get("noun");
                break;
            default:
                //没有获取到性别默认按男用户处理
                adj_words = (List<String>) StaticData.MALE_NICKNAME_WORDS.get("adj");
                nouns_words = (List<String>) StaticData.MALE_NICKNAME_WORDS.get("noun");
        }
        return adj_words.get(random.nextInt(adj_words.size())) + nouns_words.get(random.nextInt(nouns_words.size()));
    }

}

