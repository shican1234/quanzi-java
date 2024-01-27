package io.renren.common.common;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import io.renren.common.redis.RedisKeys;
import io.renren.common.redis.RedisService;
import io.renren.common.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@SuppressWarnings("ALL")
@Slf4j
@Component
public class StaticData {
    @Autowired
    private Gson gson;
    /**
     * 钻石聊天单价
     * key: wxAppId  value: appSecret
     */
    public static final Long CONIS_PRICE = 2L;
    /**
     * 钻石和魅力值财富值比例
     * key: wxAppId  value: appSecret
     */
    public static final BigDecimal CONIS_RATIO = new BigDecimal(8);
    /**
     * 微信公众号appId
     * key: wxAppId  value: appSecret
     */
    public static final Map<String, String> WX_PROPERTIES = new HashMap<>();
    /**
     * 冻结原因
     */
    public static final Map<String, String> DEACTIVATE_REASON = new HashMap<>();
    /**
     * 男Robot id集合
     */
    public static List<String> MALE_ROBOT_IDS = new ArrayList<>();
    /**
     * 女Robot id集合
     */
    public static List<String> FEMALE_ROBOT_IDS = new ArrayList<>();
    /**
     * 全部Robot id集合
     */
    public static List<String> ALL_ROBOT_IDS = new ArrayList<>();
    /**
     * 女用户昵称的字符库
     */
    public static Map<String, Map<String, List<String>>> FEMALE_NICKNAME_WORDS = new HashMap<>();
    /**
     * 男用户昵称的字符库
     */
    public static Map<String, Map<String, List<String>>> MALE_NICKNAME_WORDS = new HashMap<>();
    /**
     * 策略中固定的用户ID集
     */
    public static Set<String> STABLE_MSG_USER = new HashSet<>();


    /**
     * 打招呼随机文字消息库
     */
    public static List<String> HELLO_MSG_RANDOM_TEXT = new ArrayList<>();

    /**
     * 打招呼回答消息后的随机消息库
     */
    public static Map<Integer, List<String>> HELLO_MSG_RANDOM_ANSWER = new HashMap<>();

    /**
     * 关键词匹配回复
     */
    public static Map<String, List<String>> KEYWORD_REPLYS = new HashMap<>();
    /**
     * 无关联匹配回复
     */
    public static List<String> UNRELATED_REPLYS = new ArrayList<>();
    /**
     * 送礼回复
     */
    public static List<String> GIFT_REPLYS = new ArrayList<>();
    /**
     * 打招呼公共消息库
     */
//    public static Map<String, List<MessageTask>> SAY_HELLO_COMMON = new LinkedHashMap<>();
    /**
     * 策略列表，Male-Daddy
     */
    public static List<String> MD = new ArrayList<>();
    /**
     * ip地址库
     */
    public static Resource IP_RESOURCE;
    /**
     * 聚合天气映射
     */
    public static JSONObject WEATHER_UNI;
    /**
     * 屏蔽策略的渠道集合
     */
    public static List<String> HIDE_STRATEGY_CHANNELS = new ArrayList<>();
    /**
     * 强制定位渠道集合
     */
    public static List<String> NEED_LOCATION_CHANNELS = new ArrayList<>();
    /**
     * 审核2渠道集合
     */
    public static List<String> AUDITING_CHANNELS = new ArrayList<>();
    /**
     * 禁止注册渠道集合
     */
    public static List<String> FORBID_REG_CHANNELS = new ArrayList<>();
    /**
     * 渠道屏蔽策略的版本集合 key: channel
     */
    public static Map<String, String> HIDE_STRATEGY_VERSION_MAP = new HashMap<>();
    /**
     * 渠道屏蔽对应地区策略集合 key: channel
     */
    public static Map<String, String> HIDE_STRATEGY_AREA_MAP = new HashMap<>();
    /**
     * 隐藏一键注册按钮的渠道集合
     */
    public static List<String> HIDE_ONE_KEY_REG_CHANNELS = new ArrayList<>();
    /**
     * 隐藏一键注册按钮的版本集合 key: channel
     */
    public static Map<String, String> HIDE_ONE_KEY_REG_VERSION_MAP = new HashMap<>();
    /**
     * 系统配置
     */
//    public static AppConfig APP_CONFIG;
    /**
     * 消息策略 排序配置
     */
    public static Map<String, Integer> MSG_SORT_CONFIG = new HashMap<>();
    public static Map<String, Integer> MSG_SORT_CONFIG_OLD = new HashMap<>();
    public static Map<String, Integer> MSG_SORT_CONFIG_PLAIN = new HashMap<>();
    /**
     * 消息策略 间隔配置
     */
    public static Map<String, Integer> MSG_INTERVAL_CONFIG = new HashMap<>();
    public static Map<String, Integer> MSG_INTERVAL_CONFIG_OLD = new HashMap<>();
    public static Map<String, Integer> MSG_INTERVAL_CONFIG_PLAIN = new HashMap<>();

    public static Set<String> ipBlack = new HashSet<>();

    /**
     * 敏感词汇
     */
    public static List<String> SENSITIVE_WORDS = Arrays.asList("微信","vx","wx","QQ","qq","手机");

    /**
     * 已触发注册策略的用户
     */
    public static Set<String> REGISTERED_USER = new HashSet<>();
    public static List<String> ST_ANCHOR_IDS = Arrays.asList("944685425", "757759102", "452106822");

    //KEY:uid  VALUE: lockscreen
//    public static Map<String, LockScreen> LOCK_SCREEN_DATA = new ConcurrentHashMap<>();

    /**
     * 保存客户端请求的随机字符串
     * 有效时间 15 分钟
     * 防止重放攻击
     */
//    public static ExpiringMap<String, Date> REQ_RANDOM_MAP = ExpiringMap.builder().expiration(15, TimeUnit.MINUTES).build();

    /**
     * 公司对应有哪些包名
     */
//    public static Map<String,String> COMPANY_PACKAGE = new HashMap<>();

    /**
     * 公司名称映射
     */
//    public static Map<String, String> COMPANY_NAME_CODE = new HashMap<>();

    /**
     * 需要通知的指定工会
     */
//    public static List<String> NOTIFY_GHID = new ArrayList<>();

    /**
     * 设备号白名单
     */
    public static Set<String> WHITE_DEVICEIDS = new HashSet<>();

    /**
     * 特殊地区替换
     */
    public static Map<String, String> MSG_CITY_REPLACE = new HashMap<>();

    /**
     * 追一库
     */
    public static List<String> ONE_MESSAGE = new ArrayList<>();
    /**
     * 追二库
     */
    public static List<String> TWO_MESSAGE = new ArrayList<>();
    /**
     * 追三库
     */
    public static List<String> THREE_MESSAGE = new ArrayList<>();

    public static List<String> SAY_HELLO_MSG = new ArrayList<>();


    public static List<String> MAN_URL = new ArrayList<>();
    public static List<String> WOMAN_URL = new ArrayList<>();

//
//    public static Map<String, Activity> ACTIVITY_MAP = new HashMap<>();
//
//    public static Map<String, ZhangYiConfig> ZHANGYI_CONFIG = new HashMap<>();
//
//    public static List<LiveVideo> LIVE_VIDEO_LIST = new ArrayList<>();

    public static List<String> WITHDRAW_SETTING_GH = new ArrayList<>();

    /**
     * 用户兴趣标签
     */
    public static List<String> INTEREST_TAGES = new ArrayList<>();
    /**
     * 男用户性格标签
     */
    public static List<String> MALE_TAGES = new ArrayList<>();
    /**
     * 男用户性格标签
     */
    public static List<String> FAMALE_TAGES = new ArrayList<>();
    static {


        // 冻结原因
        DEACTIVATE_REASON.put("0", "我已经找到我的理想型了");
        DEACTIVATE_REASON.put("1", "我找不到我想要的");
        DEACTIVATE_REASON.put("2", "我觉得不安全");
        DEACTIVATE_REASON.put("3", "这个网站不适合我");
        DEACTIVATE_REASON.put("4", "我以后再来");
        DEACTIVATE_REASON.put("5", "保密");
        DEACTIVATE_REASON.put("6", "其他");

//        TIMING_PUSH_LIST.add(TimingPush.of("系统为你匹配了10个妹子", "系统为你匹配了10个妹子，快来跟他们打个招呼"));
//        TIMING_PUSH_LIST.add(TimingPush.of("你有QQ或者微信号吗？", "留下你的联系方式，让我联系你…"));
//        TIMING_PUSH_LIST.add(TimingPush.of("有个妹子给你回信了", "你有一条未读信息，点击查看"));
//        TIMING_PUSH_LIST.add(TimingPush.of("您有一条未读信息，请查看", "有人向你打招呼了快来看看吧~"));
//        TIMING_PUSH_LIST.add(TimingPush.of("妹纸给你留言了", "附近有人想和你聊一聊"));
//        TIMING_PUSH_LIST.add(TimingPush.of("有个妹子向你提出了问题", "你是哪里人？我很想知道，快来聊聊~~"));
//        TIMING_PUSH_LIST.add(TimingPush.of("做个让别人嫉妒的男人吧", "多和妹子聊天，保你1个月找到女朋友"));
//        TIMING_PUSH_LIST.add(TimingPush.of("您有一封信，请查看。", "有人向你打招呼了快来看看吧~"));
//        TIMING_PUSH_LIST.add(TimingPush.of("有人给你留言了", "昨天和你聊天的妹子给你留言了"));
//        TIMING_PUSH_LIST.add(TimingPush.of("Hi，快来加我的QQ吧", "点击查看妹子QQ号"));
//        TIMING_PUSH_LIST.add(TimingPush.of("我留了个带QQ的信息", "看到了你给我的信，我的QQ是…"));
//        TIMING_PUSH_LIST.add(TimingPush.of("妹子想和你交换QQ号", "快来和妹子交换QQ号吧"));
//        TIMING_PUSH_LIST.add(TimingPush.of("妹子向你提出了问题", "你是哪里人？我很想知道，快来聊聊~~"));
//        TIMING_PUSH_LIST.add(TimingPush.of("招呼打的多，速配机率就越大", "经验之谈：晚上和妹子聊天更容易速配成功"));
//        TIMING_PUSH_LIST.add(TimingPush.of("好久没来了吧，有人想你了", "妹子发布一条交友信息，喜欢就点赞"));
//        TIMING_PUSH_LIST.add(TimingPush.of("免费找对象还能送话费", "快来找妹子聊天，还能再送100元"));
//        TIMING_PUSH_LIST.add(TimingPush.of("有多位美女更新了她的照片", "附近美女更新了一批照片，点击查看"));
//        TIMING_PUSH_LIST.add(TimingPush.of("又有女孩在看你<点击查看>", "有美女看上你了，快来看看吧！"));
//        TIMING_PUSH_LIST.add(TimingPush.of("你有一条未读信息", "你关注的那个姑娘给你回信了，点击查看"));
//        TIMING_PUSH_LIST.add(TimingPush.of("有人给你回信了", "你有一条未读信息，点击查看"));

        GIFT_REPLYS.add("啊，你给我送了礼物啊");
        GIFT_REPLYS.add("啊啊啊啊，加个微信呗");
        GIFT_REPLYS.add("谢谢哥哥，一起玩不？");
        GIFT_REPLYS.add("哥哥，你是做什么的啊？");
        GIFT_REPLYS.add("哥哥，怎么会给我送礼物啊？");
        GIFT_REPLYS.add("哥哥，5个红酒视频哟。要不要视频啊");
        GIFT_REPLYS.add("好开心，今天第一次收到礼物耶");
        GIFT_REPLYS.add("谢谢哥哥送的礼物。");
        GIFT_REPLYS.add("谢谢送的，么么哒");
        GIFT_REPLYS.add("哇，我今天第一次收到礼物，谢谢哥哥。");
        GIFT_REPLYS.add("再多送我（礼物名称）呗，我们加个微信呗。");
        GIFT_REPLYS.add("哥哥，我还要");
        GIFT_REPLYS.add("哥哥在送我一个呗，我给你看照片");
        GIFT_REPLYS.add("哥哥送个钻石，我给你看你想看的，嘿嘿");
        GIFT_REPLYS.add("10个红酒加微信哟，要不要加的嘛。");

        // 打招呼随机文字消息
        HELLO_MSG_RANDOM_TEXT.add("看到你是附近的人，我也是。");
        HELLO_MSG_RANDOM_TEXT.add("天涯何处无芳草，一边走来一边找，hi你好。");
        HELLO_MSG_RANDOM_TEXT.add("隔着屏幕已经闻到了你的文艺气息");
        HELLO_MSG_RANDOM_TEXT.add("你拍照时的样子，看起来真可爱");
        HELLO_MSG_RANDOM_TEXT.add("小兔子乖乖，把门开开");
        HELLO_MSG_RANDOM_TEXT.add("你的眼睛很有灵气，要是长在我女朋友脸上就好了");
        HELLO_MSG_RANDOM_TEXT.add("走，带你去看世间繁华，吃着麻辣烫喝着娃哈哈");
        HELLO_MSG_RANDOM_TEXT.add("看你很可爱，和你打个招呼");
        HELLO_MSG_RANDOM_TEXT.add("喵，在干嘛");
        HELLO_MSG_RANDOM_TEXT.add("戳你一下，看你在干嘛");
        HELLO_MSG_RANDOM_TEXT.add("大眼睛，萌萌哒");
        HELLO_MSG_RANDOM_TEXT.add("我刚刚上来，你在干嘛？");
        HELLO_MSG_RANDOM_TEXT.add("嗨，小妖精");
        HELLO_MSG_RANDOM_TEXT.add("捉到一只大长腿欧尼");
        HELLO_MSG_RANDOM_TEXT.add("看你照片感觉挺有女人味");
        HELLO_MSG_RANDOM_TEXT.add("看你照片蛮有气质的呢~");
        HELLO_MSG_RANDOM_TEXT.add("你平时都喜欢干嘛呢");
        HELLO_MSG_RANDOM_TEXT.add("很高兴认识你，你本人要比照片更漂亮吧");
        HELLO_MSG_RANDOM_TEXT.add("星座书说咱们两个很般配呢");

        //兴趣标签
        INTEREST_TAGES.add("刷快手");
        INTEREST_TAGES.add("听音乐");
        INTEREST_TAGES.add("厨艺达人");
        INTEREST_TAGES.add("小说");
        INTEREST_TAGES.add("K歌");
        INTEREST_TAGES.add("王者荣耀");
        INTEREST_TAGES.add("吃鸡");
        INTEREST_TAGES.add("运动");
        INTEREST_TAGES.add("驴友");
        INTEREST_TAGES.add("LOL");
        INTEREST_TAGES.add("综艺");
        INTEREST_TAGES.add("吃货");
        INTEREST_TAGES.add("追剧");
        INTEREST_TAGES.add("自驾游");
        INTEREST_TAGES.add("撸猫人士");
        INTEREST_TAGES.add("爱狗人士");
        INTEREST_TAGES.add("棋牌麻将");
        INTEREST_TAGES.add("看电影");
        INTEREST_TAGES.add("看直播");


        MALE_TAGES.add("成熟稳重");
        MALE_TAGES.add("有责任心");
        MALE_TAGES.add("感性");
        MALE_TAGES.add("靠谱");
        MALE_TAGES.add("顾家");
        MALE_TAGES.add("直爽");
        MALE_TAGES.add("幽默");
        MALE_TAGES.add("正太");
        MALE_TAGES.add("小鲜肉");
        MALE_TAGES.add("孝顺");
        MALE_TAGES.add("有上进心");
        MALE_TAGES.add("外向");
        MALE_TAGES.add("内敛");
        MALE_TAGES.add("耿直");
        MALE_TAGES.add("阳光");
        MALE_TAGES.add("完美主义");
        MALE_TAGES.add("浪漫");
        MALE_TAGES.add("逗比");
        MALE_TAGES.add("宅");
        MALE_TAGES.add("话痨");
        MALE_TAGES.add("自律");
        MALE_TAGES.add("热情");
        MALE_TAGES.add("高冷");
        MALE_TAGES.add("文艺");
        MALE_TAGES.add("有爱心");

        FAMALE_TAGES.add("萝莉");
        FAMALE_TAGES.add("御姐");
        FAMALE_TAGES.add("可爱");
        FAMALE_TAGES.add("女神");
        FAMALE_TAGES.add("佛系少女");
        FAMALE_TAGES.add("贤惠");
        FAMALE_TAGES.add("知性");
        FAMALE_TAGES.add("幽默");
        FAMALE_TAGES.add("宅");
        FAMALE_TAGES.add("强势");
        FAMALE_TAGES.add("逗比");
        FAMALE_TAGES.add("可爱");
        FAMALE_TAGES.add("自律");
        FAMALE_TAGES.add("高冷");
        MALE_TAGES.add("浪漫");

    }

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private RedisService redisService;




    @PostConstruct
    public void init() {
        //加载昵称字符库
        loadNicknameArray();
//        loadZKData("one.xlsx","one");
//        loadZKData("two.xlsx","two");
//        loadZKData("three.xlsx","three");
//        loadZKData("sayHello.xlsx","sayHello");
//        loadTxUrlData("man.xlsx","man");
//        loadTxUrlData("woman.xlsx","woman");

//        try {
//            Resource res = resourceLoader.getResource("classpath:s/s.txt");
//            try (InputStream resource = res.getInputStream()) {
//                List<String> doc =
//                        new BufferedReader(new InputStreamReader(resource,
//                                StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
//                MD = doc;
//            }
////            for(String s : MD){
////                System.out.println(s);
////            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Resource res = resourceLoader.getResource("classpath:s/ip-block.txt");
//            try (InputStream resource = res.getInputStream()) {
//                List<String> ips =
//                        new BufferedReader(new InputStreamReader(resource,
//                                StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
//                for (String ip : ips) {
//                    ipBlack.add(ip);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }



    /**
     * 加载打招呼回答后的消息库
     */


    /**
     * 加载用户昵称字符库
     */
    private void loadNicknameArray() {
        long start = System.currentTimeMillis();
        InputStream inputStream = null;
        Resource res = resourceLoader.getResource("classpath:config/nickname_arrays.json");
        try {
            inputStream = res.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = JSONUtil.getMapFromJsonFile(inputStream);
        StaticData.MALE_NICKNAME_WORDS = (Map<String, Map<String, List<String>>>) map.get("male");
        StaticData.FEMALE_NICKNAME_WORDS = (Map<String, Map<String, List<String>>>) map.get("female");
        log.warn("用户昵称字符库初始化成功。[ " + (System.currentTimeMillis() - start) + "ms ]");
    }


    /**
     * 加载IP库
     */
//    private void loadIpResource() {
//        long start = System.currentTimeMillis();
//        IP_RESOURCE = resourceLoader.getResource("classpath:config/qqzeng-ip-utf8.dat");
//        log.warn("IP库初始化成功。[ " + (System.currentTimeMillis() - start) + "ms ]");
//    }




}
