package io.renren.common.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * json解析工具类
 * @author qingpeng
 */
@Slf4j
public class JSONUtil {

    static Gson gson = new Gson();
    static JsonParser jParser = new JsonParser();

    /**
     * 将对象转为json字符串
     */
    public static String toJsonStr(Object object) {
        return gson.toJson(object);
    }

    /**
     * 将json字符串转为json对象
     */
    public static JsonObject toJsonObject(String str) {
        return jParser.parse(str).getAsJsonObject();
    }

    /**
     * 将json字符串转为json对象
     */
    public static JsonArray toJsonArray(String str) {
        return jParser.parse(str).getAsJsonArray();
    }

    /**
     * 获取String的值
     */
    public static String getValueString(JsonElement element, String key) {
        JsonObject object = element.getAsJsonObject();
        if (object == null || object.get(key) == null) {
            return "";
        }
        return object.get(key).getAsString();
    }

    /**
     * 将json字符串转换为Map对象
     */
    public static Map<String, Map<String, Object>> toJson(String jsonStr) {
        return gson.fromJson(jsonStr, new TypeToken<HashMap<String, Map<String, Object>>>() {
        }.getType());
    }

    /**
     * 将对象转为json格式
     */
    public static Map<String, Object> getMapFromJsonFile(InputStream fis) {
        try {
            byte[] fisBytes = new byte[fis.available()];
            fis.read(fisBytes);
            fis.close();
            String jsonStr = new String(fisBytes, "utf-8").replaceAll("//.*[\\n|\\r\\n]", "");
            jsonStr = jsonStr.replaceAll("/\\*[\\s\\S]*\\*/", "");
            jsonStr = jsonStr.replaceAll("#.*[\\n|\\r\\n]", "");
            Map<String, Object> map = gson.fromJson(jsonStr, new TypeToken<HashMap<String, Object>>() {
            }.getType());
            return map;
        } catch (Exception e) {
            log.error("读取json文件出错，系统退出...", e);
            System.exit(1);
        }
        return null;
    }


    /**
     * 将对象转为json格式
     */
    public static Map<String, Map<String, Object>> getMapFromJsonFile1(String filePath) {
        try {
            //FileInputStream fis = new FileInputStream("config/nickname_arrarys.json");
            FileInputStream fis = new FileInputStream(filePath);
            byte[] fisBytes = new byte[(int) fis.available()];
            fis.read(fisBytes);
            fis.close();
            String jsonStr = new String(fisBytes, "utf-8").replaceAll("//.*[\\n|\\r\\n]", "");
            jsonStr = jsonStr.replaceAll("/\\*[\\s\\S]*\\*/", "");
            jsonStr = jsonStr.replaceAll("#.*[\\n|\\r\\n]", "");
            Map<String, Map<String, Object>> map = gson.fromJson(jsonStr, new TypeToken<HashMap<String, Map<String, Object>>>() {
            }.getType());
            return map;
        } catch (Exception e) {
            log.error("读取json文件'" + filePath + "'出错，系统退出...", e);
            System.exit(1);
        }
        return null;
    }

    /**
     * 默认每次缩进两个空格
     */
    private static final String empty="    ";

    public static String consoleLog(Object json){
        String jsonStr = json.toString();
        try {
            int empty=0;
            char[]chs=jsonStr.toCharArray();
            StringBuilder stringBuilder=new StringBuilder("\n");
            for (int i = 0; i < chs.length;) {
                //若是双引号，则为字符串，下面if语句会处理该字符串
                if (chs[i]=='\"') {

                    stringBuilder.append(chs[i]);
                    i++;
                    //查找字符串结束位置
                    for ( ; i < chs.length;) {
                        //如果当前字符是双引号，且前面有连续的偶数个\，说明字符串结束
                        if ( chs[i]=='\"'&&isDoubleSerialBackslash(chs,i-1)) {
                            stringBuilder.append(chs[i]);
                            i++;
                            break;
                        } else{
                            stringBuilder.append(chs[i]);
                            i++;
                        }

                    }
                }else if (chs[i]==',') {
                    stringBuilder.append(',').append('\n').append(getEmpty(empty));

                    i++;
                }else if (chs[i]=='{'||chs[i]=='[') {
                    empty++;
                    stringBuilder.append(chs[i]).append('\n').append(getEmpty(empty));

                    i++;
                }else if (chs[i]=='}'||chs[i]==']') {
                    empty--;
                    stringBuilder.append('\n').append(getEmpty(empty)).append(chs[i]);

                    i++;
                }else {
                    stringBuilder.append(chs[i]);
                    i++;
                }


            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return jsonStr;
        }

    }
    private static boolean isDoubleSerialBackslash(char[] chs, int i) {
        int count=0;
        for (int j = i; j >-1; j--) {
            if (chs[j]=='\\') {
                count++;
            }else{
                return count%2==0;
            }
        }

        return count%2==0;
    }
    /**
     * 缩进
     * @param count
     * @return
     */
    private static String getEmpty(int count){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append(empty) ;
        }

        return stringBuilder.toString();
    }

}
