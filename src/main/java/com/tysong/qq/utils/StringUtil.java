package com.tysong.qq.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 字符串处理及转换工具类
 *
 * @author 
 * @version 2016年10月13日
 * @see org.apache.commons.lang3.StringUtils
 */
public class StringUtil extends StringUtils {

    /**
     *
     */
    private static Pattern NUMERIC_PATTERN = Pattern.compile("^[0-9\\-]+$");

    /**
     *
     */
    private static Pattern NUMERIC_STRING_PATTERN = Pattern.compile("^[0-9\\-\\-]+$");

    /**
     *
     */
    private static Pattern FLOAT_NUMERIC_PATTERN = Pattern.compile("^[0-9\\-\\.]+$");

    /**
     *
     */
    private static Pattern ABC_PATTERN = Pattern.compile("^[a-z|A-Z]+$");

    /**
     *
     */
    public static final String SPLIT_STR_PATTERN = ",|，|;|；|、|\\.|。|-|_|\\(|\\)|\\[|\\]|\\{|\\}|\\\\|/| |　|\"";
    
    /**
    *
    */
    private static Pattern ENGLISH_PATTERN = Pattern.compile("^[a-zA-Z0-9]+$");

    /**
     *
     */
    public static final String STR_COMMA = ",";

    /**
     * SPACE
     */
    public static final String SPACE = " ";

    /**
     * TAB(四个空格)
     */
    public static final String TAB = "    ";

    /**
     * 冒号
     */
    public static final String COLON = "：";

    /**
     * 百分号
     */
    public static final String PERCENT_SIGN = "%";

    /**
     *
     */
    private static Log LOGGER = LogFactory.getLog(StringUtil.class);

    /**
     *
     */
    public static final String STYLE_DEFAULT = "default";

    /**
     * 两位小数
     */
    public static final String STYLE_AMT = ",##0.00";

    /**
     * 没有小数
     */
    public static final String STYLE_ZZINT = "##0";

    /**
     * 0位
     */
    public static final String DECIMALS_ZZINT = "0";

    /**
     * 两位小数
     */
    public static final String STYLE_ZZAMT = "##0.00";

    /**
     * 2位
     */
    public static final String DECIMALS_ZZAMT = "2";

    /**
     * 五位小数
     */
    public static final String STYLE_ZZYIELD = "##0.00000";

    /**
     * 5位
     */
    public static final String DECIMALS_ZZYIELD = "5";

    /**
     * 七位小数
     */
    public static final String STYLE_ZZYIELD_ORG = "##0.0000000";

    /**
     * 7位
     */
    public static final String DECIMALS_ZZYIELD_ORG = "7";

    /**
     * 四位小数
     */
    public static final String STYLE_ZZPRICE = "##0.0000";

    /**
     * 4位
     */
    public static final String DECIMALS_ZZPRICE = "4";

    /**
     * 六位小数
     */
    public static final String STYLE_YIELD = ",##0.000000";

    /**
     * 十二位小数
     */
    public static final String STYLE_PRICE = ",##0.000000000000";

    /**
     * 特殊字符集合
     */
    public static final Map<String, String> SPECIAL_MAP = new HashMap<>();

    /**
     * 特殊字符集合
     */
    public static final Map<String, String> _SPECIAL_MAP = new HashMap<>();
    
    static {
        SPECIAL_MAP.put("'", "&apos;");
        SPECIAL_MAP.put("\"", "&quot;");
        SPECIAL_MAP.put("<", "&lt;");
        SPECIAL_MAP.put(">", "&gt;");
        SPECIAL_MAP.put("&", "&amp;");
        
        _SPECIAL_MAP.put("&apos;", "'");
        _SPECIAL_MAP.put("&quot;", "\"");
        _SPECIAL_MAP.put("&lt;", "<");
        _SPECIAL_MAP.put("&gt;", ">");
        _SPECIAL_MAP.put("&amp;", "&");
    }

    /**
     * 获取一个对象的字符串的值，如果该字符串为空则取默认值
     *
     * @param value 对象
     * @param defaultValue 默认值
     * @return 对象的字符串表示
     */
    public static final String getOrElse(Object value, String defaultValue) {
        if (value != null && isNotEmpty(value.toString())) {
            return value.toString();
        }
        else if (isNotEmpty(defaultValue)) {
            return defaultValue;
        }
        return EMPTY;
    }

    /**
     * 获取对象的非空字符串<br>
     * 如果该对象为空，则返回空字符串
     *
     * @param value 对象
     * @return 字符串
     */
    public static final String getNotNullString(Object value) {
        return getOrElse(value, EMPTY);
    }

    /**
     * 替换字符串中指定的所有子串
     *
     * @param value 需要被替换的字符串
     * @param searchString 需要替换的子串
     * @param replacement 子串将要被替换的字符串
     * @return 替换后的字符串
     */
    public static String replaceRepeat(String value, String searchString, String replacement) {
        String result = value;
        if (isNotEmpty(value)) {
            do {
                result = replace(result, searchString, replacement);
            }
            while (!result.equals(replace(result, searchString, replacement)));
        }
        return result;
    }

    /**
     * 将字符串根据指定字符串分隔为两个字符串
     *
     * @param old 源字符串
     * @param splitor 分隔符
     * @return 新的字符串数组
     */
    public static final String[] splitTwo(String old, String splitor) {
        String[] result = new String[2];
        if (isNotEmpty(old)) {
            int index = old.indexOf(splitor);
            if (index > -1) {
                result[0] = old.substring(0, index);
                result[1] = old.substring(index + splitor.length());
            }
            else {
                result[0] = old;
                result[1] = EMPTY;
            }
        }
        return result;
    }

    /**
     * 判断是否数字表示
     *
     * @param src 源字符串
     * @return 是否数字的标志
     */
    public static boolean isNumeric(String src) {
        boolean returnValue = false;
        if (src != null && src.length() > 0) {
            Matcher m = NUMERIC_PATTERN.matcher(src);
            if (m.find()) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    /**
     * 判断是否数字表示
     *
     * @param src 源字符串
     * @return 是否数字的标志
     */
    public static boolean isNumericString(String src) {
        boolean returnValue = false;
        if (src != null && src.length() > 0) {
            Matcher m = NUMERIC_STRING_PATTERN.matcher(src);
            if (m.find()) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    /**
     * 判断是否纯字母组合
     *
     * @param src 源字符串
     * @return 是否纯字母组合的标志
     */
    public static boolean isABC(String src) {
        boolean returnValue = false;
        if (src != null && src.length() > 0) {
            Matcher m = ABC_PATTERN.matcher(src);
            if (m.find()) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    /**
     * 判断是否浮点数字表示
     *
     * @param src 源字符串
     * @return 是否数字的标志
     */
    public static boolean isFloatNumeric(String src) {
        boolean returnValue = false;
        if (src != null && src.length() > 0) {
            Matcher m = FLOAT_NUMERIC_PATTERN.matcher(src);
            if (m.find()) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    /**
     * 把string array or list用给定的符号symbol连接成一个字符串
     *
     * @param array 数据集合
     * @param symbol 分隔符
     * @return 字符串
     */
    public static String joinString(List<?> array, String symbol) {
        String result = "";
        if (array != null) {
            for (int i = 0; i < array.size(); i++ ) {
                String temp = EMPTY;
                if(null != array.get(i)) {
                    temp = array.get(i).toString();
                }
                if (temp != null && temp.trim().length() > 0) {
                    result += (temp + symbol);
                }
            }
            if (result.length() > 1) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    /**
     * 主要功能:截取字符串不编码 注意事项:无
     *
     * @param subject 对象
     * @param size 位置
     * @return 字符串
     */
    public static String subStringNotEncode(String subject, int size) {
        if (subject != null && subject.length() > size) {
            subject = subject.substring(0, size) + "...";
        }
        return subject;
    }
    
    public static  String getDomainForUrl(String url){
        //使用正则表达式过滤，
        String re = "((http|ftp|https)://)(([a-zA-Z0-9._-]+)|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(([a-zA-Z]{2,6})|(:[0-9]{1,4})?)";
        String str = "";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(re);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        //若url==http://127.0.0.1:9040或www.baidu.com的，正则表达式表示匹配
        if (matcher.matches()) {
            str = url;
        } else {
            String[] split2 = url.split(re);
            if (split2.length > 1) {
                String substring = url.substring(0, url.length() - split2[1].length());
                str = substring;
            } else {
                str = split2[0];
            }
        }
        return str;
    }

    public static void main(String[] args) {
    	System.out.println(getDomainForUrl("收录查询 zhinianblog.com"));;
	}
    /**
     * 截取字符串 超出的字符用symbol代替
     *
     * @param len 字符串长度 长度计量单位为一个GBK汉字 两个英文字母计算为一个单位长度
     * @param str 字符串
     * @param symbol 符号
     * @return 字符串
     */
    public static String getLimitLengthString(String str, int len, String symbol) {
        int iLen = len * 2;
        int counterOfDoubleByte = 0;
        String strRet = "";
        try {
            if (str != null) {
                byte[] b = str.getBytes("GBK");
                if (b.length <= iLen) {
                    return str;
                }
                for (int i = 0; i < iLen; i++ ) {
                    if (b[i] < 0) {
                        counterOfDoubleByte++ ;
                    }
                }
                if (counterOfDoubleByte % 2 == 0) {
                    strRet = new String(b, 0, iLen, "GBK") + symbol;
                    return strRet;
                }
                else {
                    strRet = new String(b, 0, iLen - 1, "GBK") + symbol;
                    return strRet;
                }
            }
            else {
                return "";
            }
        }
        catch (Exception ex) {
            return str.substring(0, len);
        }
        finally {
            strRet = null;
        }
    }

    /**
     * Description:判断list是否为空
     *
     * @param list 需要判断的list
     * @return 是否为空
     */
    public static boolean isEmptyList(List<?> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Description:判断list是否不为空
     *
     * @param list 需要判断的list
     * @return 是否不为空
     */
    public static boolean isNotEmptyList(List<?> list) {
        return !isEmptyList(list);
    }

    /**
     * 截取字符串 超出的字符用"..."代替
     *
     * @param len 字符串长度 长度计量单位为一个GBK汉字 两个英文字母计算为一个单位长度
     * @param str 字符串
     * @return 截取字符串
     */
    public static String getLimitLengthString(String str, int len) {
        return getLimitLengthString(str, len, "...");
    }

    /**
     * 截取字符，不转码
     *
     * @param subject 字符串
     * @param size 位置
     * @return 字符串
     */
    public static String subStrNotEncode(String subject, int size) {
        if (subject.length() > size) {
            subject = subject.substring(0, size);
        }
        return subject;
    }

    /**
     * 把string array or list用给定的符号symbol连接成一个字符串
     *
     * @param array 数组
     * @param symbol 分隔符
     * @return 字符串
     */
    public static String joinString(String[] array, String symbol) {
        String result = "";
        if (array != null) {
            for (int i = 0; i < array.length; i++ ) {
                String temp = array[i];
                if (temp != null && temp.trim().length() > 0) {
                    result += (temp + symbol);
                }
            }
            if (result.length() > 1) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    /**
     * 取得字符串的实际长度（考虑了汉字的情况）
     *
     * @param srcStr 源字符串
     * @return 字符串的实际长度
     */
    public static int getStringLen(String srcStr) {
        int returnValue = 0;
        if (srcStr != null) {
            char[] theChars = srcStr.toCharArray();
            for (int i = 0; i < theChars.length; i++ ) {
                returnValue += (theChars[i] <= 255) ? 1 : 2;
            }
        }
        return returnValue;
    }

    /**
     * 检查数据串中是否包含非法字符集
     *
     * @param str 字符串
     * @return [true]|[false] 包含|不包含
     */
    public static boolean check(String str) {
        String sIllegal = "'\"";
        int len = sIllegal.length();
        if (null == str) {
            return false;
        }
        for (int i = 0; i < len; i++ ) {
            if (str.indexOf(sIllegal.charAt(i)) != -1) {
                return true;
            }
        }

        return false;
    }

    /**
     * ************************************************************************* getHideEmailPrefix
     * - 隐藏邮件地址前缀。
     *
     * @param email - EMail邮箱地址 例如: linwenguo@koubei.com 等等...
     * @return 返回已隐藏前缀邮件地址, 如 *********@koubei.com.
     * @version 1.0 (2006.11.27) Wilson Lin
     */
    public static String getHideEmailPrefix(String email) {
        if (null != email) {
            int index = email.lastIndexOf('@');
            if (index > 0) {
                email = repeat("*", index).concat(email.substring(index));
            }
        }
        return email;
    }

    /**
     * ************************************************************************* repeat -
     * 通过源字符串重复生成N次组成新的字符串。
     *
     * @param src - 源字符串 例如: 空格(" "), 星号("*"), "浙江" 等等...
     * @param num - 重复生成次数
     * @return 返回已生成的重复字符串
     * @version 1.0 (2006.10.10) Wilson Lin
     */
    public static String repeat(String src, int num) {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < num; i++ ) {
            s.append(src);
        }
        return s.toString();
    }

    /**
     * 根据指定的字符把源字符串分割成一个集合（空字符串或者null会返回空的集合）
     *
     * @param pattern 格式化字符串
     * @param src 来源
     * @return 字符串集合
     */
    public static List<String> parseString2List(String pattern, String src) {
        if (isEmpty(src)) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<String>();
        String[] result = src.split(pattern);
        for (int i = 0; i < result.length; i++ ) {
            list.add(result[i]);
        }
        return list;
    }

    /**
     * 格式化一个float
     *
     * @param format 要格式化成的格式 such as #.00, #.#
     * @param f 浮点型
     * @return 字符串
     */
    public static String formatFloat(float f, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(f);
    }

    /**
     * 自定义的分隔字符串函数 例如: 1,2,3 =&gt; [1,2,3] 3个元素 ,2,3 =&gt; [,2,3] 3个元素 ,2,3,=&gt;[,2,3,] 4个元素
     * ,,,=&gt;[,,,] 4个元素 5.22算法修改，为提高速度不用正则表达式 两个间隔符,,返回""元素（空字符串或者null会返回空的集合）
     *
     * @param split 分割字符 默认,
     * @param src 输入字符串
     * @return 分隔后的list
     * @author Robin
     */
    public static List<String> splitToList(String split, String src) {
        if (isEmpty(src)) {
            return new ArrayList<>();
        }
        // 默认,
        String sp = ",";
        if (split != null && split.length() == 1) {
            sp = split;
        }
        List<String> r = new ArrayList<String>();
        int lastIndex = -1;
        int index = src.indexOf(sp);
        if (-1 == index && src != null) {
            r.add(src);
            return r;
        }
        while (index >= 0) {
            if (index > lastIndex) {
                r.add(src.substring(lastIndex + 1, index));
            }
            else {
                r.add("");
            }

            lastIndex = index;
            index = src.indexOf(sp, index + 1);
            if (index == -1) {
                r.add(src.substring(lastIndex + 1, src.length()));
            }
        }
        return r;
    }

    /**
     * 把 名=值 参数表转换成字符串 (a=1,b=2 =&gt; a=1&amp;b=2)
     *
     * @param map 键值对集合
     * @return 字符串
     */
    public static String linkedHashMapToString(LinkedHashMap<String, String> map) {
        if (map != null && map.size() > 0) {
            String result = "";
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String name = it.next();
                String value = map.get(name);
                result += (result.equals("")) ? "" : "&";
                result += String.format("%s=%s", name, value);
            }
            return result;
        }
        return null;
    }

    /**
     * 解析字符串返回 名称=值的参数表 (a=1&amp;b=2 =&gt; a=1,b=2)
     *
     * @param str 字符串
     * @return 名称=值的参数表 (a=1&amp;b=2 =&gt; a=1,b=2)
     */
    public static LinkedHashMap<String, String> toLinkedHashMap(String str) {
        if (str != null && !str.equals("") && str.indexOf("=") > 0) {
            LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
            String name = null;
            String value = null;
            int i = 0;
            while (i < str.length()) {
                char c = str.charAt(i);
                switch (c) {
                    case 61: // =
                        value = "";
                        break;
                    case 38: // &
                        if (name != null && value != null && !name.equals("")) {
                            result.put(name, value);
                        }
                        name = null;
                        value = null;
                        break;
                    default:
                        if (value != null) {
                            value = (value != null) ? (value + c) : "" + c;
                        }
                        else {
                            name = (name != null) ? (name + c) : "" + c;
                        }
                }
                i++ ;

            }

            if (name != null && value != null && !name.equals("")) {
                result.put(name, value);
            }

            return result;

        }
        return null;
    }

    /**
     * 根据输入的多个解释和下标返回一个值
     *
     * @param captions 例如:"无,爱干净,一般,比较乱"
     * @param index 1
     * @return 字符串值
     */
    public static String getCaption(String captions, int index) {
        if (index > 0 && captions != null && !captions.equals("")) {
            String[] ss = captions.split(",");
            if (ss != null && ss.length > 0 && index < ss.length) {
                return ss[index];
            }
        }
        return null;
    }

    /**
     * 数字转字符串,如果num&lt;=0 则输出&quot;&quot;
     *
     * @param num 数字
     * @return 字符串
     */
    public static String numberToString(Number num) {
        if (num == null) {
            return null;
        }
        String result = EMPTY;
        if (num instanceof Short && (Short)num > 0) {
            result = Short.toString((Short)num);
        }
        if (num instanceof Integer && (Integer)num > 0) {
            result = Integer.toString((Integer)num);
        }
        else if (num instanceof Long && (Long)num > 0) {
            result = Long.toString((Long)num);
        }
        else if (num instanceof Float && (Float)num > 0) {
            result = Float.toString((Float)num);
        }
        else if (num instanceof Double && (Double)num > 0) {
            result = Double.toString((Double)num);
        }
        else if (num instanceof BigDecimal) {
            result = ((BigDecimal)num).stripTrailingZeros().toPlainString();
        }
        return result;
    }

    /**
     * 货币转字符串
     *
     * @param money 货币
     * @param style 样式 [default]要格式化成的格式 such as #.00, #.#
     * @return 字符串
     */
    public static String moneyToString(Object money, String style) {
        
        if (money != null && style != null
            && (money instanceof Double || money instanceof Float)) {
            Double num = (Double)money;

            if (style.equalsIgnoreCase("default")) {
                // 缺省样式 0 不输出 ,如果没有输出小数位则不输出.0
                if (num == 0) {
                    // 不输出0
                    return "";
                }
                else if ((num * 10 % 10) == 0) {
                    // 没有小数
                    return Integer.toString(num.intValue());
                }
                else {
                    // 有小数
                    return num.toString();
                }

            }
            else {
                DecimalFormat df = new DecimalFormat(style);
                return df.format(num);
            }
        }
        else if (money != null && style != null
            && money instanceof BigDecimal) {
            Double num = ((BigDecimal)money).doubleValue();

            if (style.equalsIgnoreCase("default")) {
                // 缺省样式 0 不输出 ,如果没有输出小数位则不输出.0
                if (num == 0) {
                    // 不输出0
                    return "";
                }
                else if ((num * 10 % 10) == 0) {
                    // 没有小数
                    return Integer.toString(num.intValue());
                }
                else {
                    // 有小数
                    return num.toString();
                }

            }
            else {
                DecimalFormat df = new DecimalFormat(style);
                return df.format(num);
            }
        }
        return null;
    }

    /**
     * 在sou中是否存在finds 如果指定的finds字符串有一个在sou中找到,返回true;
     *
     * @param sou 查找字符串
     * @param finds 查询集合
     * @return 是否
     */
    public static boolean strPos(String sou, String... finds) {
        if (sou != null && finds != null && finds.length > 0) {
            for (int i = 0; i < finds.length; i++ ) {
                if (sou.indexOf(finds[i]) > -1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 主要功能:sou中是否存在finds 如果指定的finds字符串有一个在sou中找到 注意事项:无
     *
     * @param sou 查找字符串
     * @param finds 查询集合
     * @return 是否
     */
    public static boolean strPos(String sou, List<String> finds) {
        if (sou != null && finds != null && finds.size() > 0) {
            for (String s : finds) {
                if (sou.indexOf(s) > -1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 主要功能:sou中是否存在finds 如果指定的finds字符串有一个在sou中找到 注意事项:无
     *
     * @param sou 查找字符串
     * @param finds 查询集合
     * @return 是否
     */
    public static boolean strPos(String sou, String finds) {
        List<String> t = splitToList(",", finds);
        return strPos(sou, t);
    }

    /**
     * 判断两个字符串是否相等 如果都为null则判断为相等,一个为null另一个not null则判断不相等 否则如果s1=s2则相等
     *
     * @param s1 参数1
     * @param s2 参数2
     * @return 是否
     */
    public static boolean equals(String s1, String s2) {
        if (s1 == null) {
            return s1 == s2;
        }
        else if (null != s1 && null != s2) {
            return s1.equals(s2);
        }
        return false;
    }

    /**
     * 判断两个字符串是否相等 如果都为null则判断为相等,一个为null另一个not null则判断不相等 否则如果s1=s2则相等
     * 忽略字符串的前后空格
     *
     * @param s1 参数1
     * @param s2 参数2
     * @return 是否
     */
    public static boolean equalsIgnoreSpace(String s1, String s2) {
        if (s1 == null) {
            return s1 == s2;
        }
        else if (null != s1 && null != s2) {
            return s1.trim().equals(s2.trim());
        }
        return false;
    }

    /**
     * 判断thisValue是否与others任意一个相等<br/>
     * 如果thisValue为null，返回false
     *
     * @param thisValue 比较对象
     * @param others 被比较对象
     * @return thisValue是否与others任意一个相等时，返回true
     * @see
     * @since 1.0
     */
    public static boolean equals(String thisValue, String... others) {
        if (thisValue == null) {
            for (String other : others) {
                if (thisValue == other) {
                    return true;
                }
            }
        }
        for (String other : others) {
            if (thisValue.equals(other)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 主要功能:字符串转换整形 注意事项:无
     *
     * @param s 字符串
     * @return int
     */
    public static int toInt(String s) {
        if (s != null && !"".equals(s.trim())) {
            try {
                return Integer.parseInt(s);
            }
            catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    /**
     * 主要功能:转换成Double 注意事项:无
     *
     * @param s 字符串
     * @return Double
     */
    public static double toDouble(String s) {
        if (s != null && !"".equals(s.trim())) {
            return Double.parseDouble(s);
        }
        return 0;
    }

    /**
     * 主要功能: sting to long 注意事项:无
     *
     * @param s string
     * @return long
     */
    public static long toLong(String s) {
        try {
            if (s != null && !"".equals(s.trim())) {
                return Long.parseLong(s);
            }
        }
        catch (Exception exception) {

        }
        return 0L;
    }

    /**
     * 过滤用户输入的URL地址（防治用户广告） 目前只针对以http或www开头的URL地址 本方法调用的正则表达式，不建议用在对性能严格的地方例如:循环及list页面等
     *
     * @author fengliang
     * @param str 需要处理的字符串
     * @return 返回处理后的字符串
     */
    public static String removeURL(String str) {
        if (str != null) {
            str = str.toLowerCase().replaceAll("(http|https|www|com|cn|org|\\.)+", "");
        }
        return str;
    }

    /**
     * 随即生成指定位数的含数字验证码字符串
     *
     * @param bit 指定生成验证码位数
     * @return String 含数字验证码字符串
     */
    public static String numRandom(int bit) {
        if (bit == 0) {
            bit = 6; // 默认6位
        }
        String str = "";
        str = "0123456789";// 初始化种子
        return RandomStringUtils.random(bit, str);// 返回6位的字符串
    }

    /**
     * 随即生成指定位数的含验证码字符串
     *
     * @param bit 指定生成验证码位数
     * @return String 字符串
     */
    public static String random(int bit) {
        if (bit == 0) {
            bit = 6; // 默认6位
        }
        // 因为o和0,l和1很难区分,所以,去掉大小写的o和l
        String str = "";
        str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";// 初始化种子
        return RandomStringUtils.random(bit, str);// 返回6位的字符串
    }

    /**
     * Wap页面的非法字符检查
     *
     * @param str 字符
     * @return 过滤后字符串
     */
    public static String replaceWapStr(String str) {
        if (str != null) {
            str = str.replaceAll("<span class=\"keyword\">", "");
            str = str.replaceAll("</span>", "");
            str = str.replaceAll("<strong class=\"keyword\">", "");
            str = str.replaceAll("<strong>", "");
            str = str.replaceAll("</strong>", "");

            str = str.replace('$', '＄');

            str = str.replaceAll("&amp;", "＆");
            str = str.replace('&', '＆');

            str = str.replace('<', '＜');

            str = str.replace('>', '＞');

        }
        return str;
    }

    /**
     * 字符串转float 如果异常返回0.00
     *
     * @param s 输入的字符串
     * @return 转换后的float
     */
    public static Float toFloat(String s) {
        try {
            return Float.parseFloat(s);
        }
        catch (NumberFormatException e) {
            return new Float(0);
        }
    }

    /**
     * 页面中去除字符串中的空格、回车、换行符、制表符
     *
     * @param str 字符串
     * @return 去除字符串中的空格、回车、换行符、制表符
     */
    public static String replaceBlank(String str) {
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");
        }
        return str;
    }

    /**
     * 全角生成半角
     *
     * @param qJstr 全角生成半角
     * @return 全角生成半角
     */
    public static String q2B(String qJstr) {
        String outStr = "";
        String tStr = "";
        byte[] b = null;
        for (int i = 0; i < qJstr.length(); i++ ) {
            try {
                tStr = qJstr.substring(i, i + 1);
                b = tStr.getBytes("unicode");
            }
            catch (java.io.UnsupportedEncodingException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error(e);
                }
            }
            if (b[3] == -1) {
                b[2] = (byte)(b[2] + 32);
                b[3] = 0;
                try {
                    outStr = outStr + new String(b, "unicode");
                }
                catch (java.io.UnsupportedEncodingException ex) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error(ex);
                    }
                }
            }
            else {
                outStr = outStr + tStr;
            }
        }
        return outStr;
    }

    /**
     * 转换编码
     *
     * @param s 源字符串
     * @param fencode 源编码格式
     * @param bencode 目标编码格式
     * @return 目标编码
     */
    public static String changCoding(String s, String fencode, String bencode) {
        String str;
        try {
            if (isNotEmpty(s)) {
                str = new String(s.getBytes(fencode), bencode);
            }
            else {
                str = "";
            }
            return str;
        }
        catch (UnsupportedEncodingException e) {
            return s;
        }
    }

    /**
     * 过滤特殊字符
     *
     * @param str 待过滤的字符串
     * @return 过滤后的字符串
     * @see
     * @since 1.0
     */
    public static String StringFilter(String str) {
        if (isEmpty(str)) {
            return str;
        }
        // 清除掉所有特殊字符
        String regEx = "[']";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    
    /**
     * 定义script的正则表达式
     */
    private static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";

    /**
     * 定义style的正则表达式
     */
    private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";

    /**
     * 定义HTML标签的正则表达式
     */
    private static final String REGEX_HTML = "<[^>]+>";

    /**
     * 删除html标签，防止js注入
     *
     * @param htmlStr 字符串
     * @return 过滤后的字符串
     * @see
     * @since 1.0
     */
    public static String delHTMLTag(String htmlStr) {
        if (isEmpty(htmlStr)) {
            return htmlStr;
        }
        Pattern p_script = Pattern.compile(REGEX_SCRIPT, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(REGEX_STYLE, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        return htmlStr.trim(); // 返回文本字符串
    }

    /**
     * 主要功能:替换HTML特殊字符 注意事项:无
     *
     * @param str input String 来源字符串
     * @return 替换后字符串
     */
    public static String removeHTMLLableExe(String str) {
        str = stringReplace(str, ">\\s*<", "><");
        str = stringReplace(str, "&nbsp;", " ");// 替换空格
        str = stringReplace(str, "<br ?/?>", "\n");// 去<br><br />
        str = stringReplace(str, "<([^<>]+)>", "");// 去掉<>内的字符
        str = stringReplace(str, "\\s\\s\\s*", " ");// 将多个空白变成一个空格
        str = stringReplace(str, "^\\s*", "");// 去掉头的空白
        str = stringReplace(str, "\\s*$", "");// 去掉尾的空白
        str = stringReplace(str, " +", " ");
        return str;
    }

    /**
     * 除去html标签
     *
     * @param str 源字符串
     * @return 目标字符串
     */
    public static String removeHTMLLable(String str) {
        str = stringReplace(str, "\\s", "");// 去掉页面上看不到的字符
        str = stringReplace(str, "<br ?/?>", "\n");// 去<br><br />
        str = stringReplace(str, "<([^<>]+)>", "");// 去掉<>内的字符
        str = stringReplace(str, "&nbsp;", " ");// 替换空格
        str = stringReplace(str, "&(\\S)(\\S?)(\\S?)(\\S?);", "");// 去<br><br />
        return str;
    }

    /**
     * 去掉HTML标签之外的字符串
     *
     * @param str 源字符串
     * @return 目标字符串
     */
    public static String removeOutHTMLLable(String str) {
        str = stringReplace(str, ">([^<>]+)<", "><");
        str = stringReplace(str, "^([^<>]+)<", "<");
        str = stringReplace(str, ">([^<>]+)$", ">");
        return str;
    }

    /**
     * 字符串替换
     *
     * @param str 源字符串
     * @param sr 正则表达式样式
     * @param sd 替换文本
     * @return 结果串
     */
    public static String stringReplace(String str, String sr, String sd) {
        String regEx = sr;
        Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        str = m.replaceAll(sd);
        return str;
    }

    /**
     * 将html的省略写法替换成非省略写法
     *
     * @param str html字符串
     * @param pt 标签如table
     * @return 结果串
     */
    public static String fomateToFullForm(String str, String pt) {
        String regEx = "<" + pt + "\\s+([\\S&&[^<>]]*)/>";
        Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        String[] sa = null;
        String sf = "";
        String sf2 = "";
        String sf3 = "";
        for (; m.find();) {
            sa = p.split(str);
            if (sa == null) {
                break;
            }
            sf = str.substring(sa[0].length(), str.indexOf("/>", sa[0].length()));
            sf2 = sf + "></" + pt + ">";
            sf3 = str.substring(sa[0].length() + sf.length() + 2);
            str = sa[0] + sf2 + sf3;
            sa = null;
        }
        return str;
    }

    /**
     * 得到字符串的子串位置序列
     *
     * @param str 字符串
     * @param sub 子串
     * @param b true子串前端,false子串后端
     * @return 字符串的子串位置序列
     */
    public static int[] getSubStringPos(String str, String sub, boolean b) {
        // int[] i = new int[(new Integer((str.length()-stringReplace( str , sub
        // , "" ).length())/sub.length())).intValue()] ;
        String[] sp = null;
        int l = sub.length();
        sp = splitString(str, sub);
        if (sp == null) {
            return null;
        }
        int[] ip = new int[sp.length - 1];
        for (int i = 0; i < sp.length - 1; i++ ) {
            ip[i] = sp[i].length() + l;
            if (i != 0) {
                ip[i] += ip[i - 1];
            }
        }
        if (b) {
            for (int j = 0; j < ip.length; j++ ) {
                ip[j] = ip[j] - l;
            }
        }
        return ip;
    }

    /**
     * 根据正则表达式分割字符串
     *
     * @param str 源字符串
     * @param ms 正则表达式
     * @return 目标字符串组
     */
    public static String[] splitString(String str, String ms) {
        String regEx = ms;
        Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        String[] sp = p.split(str);
        return sp;
    }

    /**
     * 根据正则表达式提取字符串,相同的字符串只返回一个 传入一个字符串，把符合pattern格式的字符串放入字符串数组
     * java.util.regex是一个用正则表达式所订制的模式来对字符串进行匹配工作的类库包
     *
     * @param str 源字符串
     * @param pattern 正则表达式
     * @return 目标字符串数据组
     */
    public static String[] getStringArrayByPattern(String str, String pattern) {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(str);
        // 范型
        Set<String> result = new HashSet<String>();// 目的是：相同的字符串只返回一个。。。 不重复元素
        // boolean find() 尝试在目标字符串里查找下一个匹配子串。
        while (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++ ) {
                // 返回当前查找所获得的匹配组的数量。
                // org.jeecgframework.core.util.LogUtil.info(matcher.group(i));
                result.add(matcher.group(i));

            }
        }
        String[] resultStr = null;
        if (result.size() > 0) {
            resultStr = new String[result.size()];
            return result.toArray(resultStr);// 将Set result转化为String[] resultStr
        }
        return resultStr;

    }

    /**
     * 得到第一个b,e之间的字符串,并返回e后的子串
     *
     * @param s 源字符串
     * @param b 标志开始
     * @param e 标志结束
     * @return b,e之间的字符串
     */
    public static String[] midString(String s, String b, String e) {
        int i = s.indexOf(b) + b.length();
        int j = s.indexOf(e, i);
        String[] sa = new String[2];
        if (i < b.length() || j < i + 1 || i > j) {
            sa[1] = s;
            sa[0] = null;
            return sa;
        }
        else {
            sa[0] = s.substring(i, j);
            sa[1] = s.substring(j);
            return sa;
        }
    }

    /**
     * 带有前一次替代序列的正则表达式替代
     *
     * @param s 字符串
     * @param pf 正则表达式
     * @param pb pb
     * @param start 开始位置
     * @return 字符串
     */
    public static String stringReplace(String s, String pf, String pb, int start) {
        Pattern patternHand = Pattern.compile(pf);
        Matcher matcherHand = patternHand.matcher(s);
        int gc = matcherHand.groupCount();
        int pos = start;
        String sf1 = "";
        String sf2 = "";
        String sf3 = "";
        int if1 = 0;
        String strr = "";
        while (matcherHand.find(pos)) {
            sf1 = matcherHand.group();
            if1 = s.indexOf(sf1, pos);
            if (if1 >= pos) {
                strr += s.substring(pos, if1);
                pos = if1 + sf1.length();
                sf2 = pb;
                for (int i = 1; i <= gc; i++ ) {
                    sf3 = "\\" + i;
                    sf2 = replaceAll(sf2, sf3, matcherHand.group(i));
                }
                strr += sf2;
            }
            else {
                return s;
            }
        }
        strr = s.substring(0, start) + strr;
        return strr;
    }

    /**
     * 存文本替换
     *
     * @param s 源字符串
     * @param sf 子字符串
     * @param sb 替换字符串
     * @return 替换后的字符串
     */
    public static String replaceAll(String s, String sf, String sb) {
        int i = 0;
        int j = 0;
        int l = sf.length();
        boolean b = true;
        boolean o = true;
        String str = "";
        do {
            j = i;
            i = s.indexOf(sf, j);
            if (i > j) {
                str += s.substring(j, i);
                str += sb;
                i += l;
                o = false;
            }
            else {
                str += s.substring(j);
                b = false;
            }
        }
        while (b);
        if (o) {
            str = s;
        }
        return str;
    }

    /**
     * 主要功能:字符串的替换 注意事项:无
     *
     * @param strSource 来源字符串
     * @param strOld 待替换字符串
     * @param strNew 替换字符串
     * @return 字符串
     */
    public static String replace(String strSource, String strOld, String strNew) {
        if (strSource == null) {
            return null;
        }
        int i = 0;
        if ((i = strSource.indexOf(strOld, i)) >= 0) {
            char[] cSrc = strSource.toCharArray();
            char[] cTo = strNew.toCharArray();
            int len = strOld.length();
            StringBuffer buf = new StringBuffer(cSrc.length);
            buf.append(cSrc, 0, i).append(cTo);
            i += len;
            int j = i;
            while ((i = strSource.indexOf(strOld, i)) > 0) {
                buf.append(cSrc, j, i - j).append(cTo);
                i += len;
                j = i;
            }
            buf.append(cSrc, j, cSrc.length - j);
            return buf.toString();
        }
        return strSource;
    }

    /**
     * 判断是否与给定字符串样式匹配
     *
     * @param str 字符串
     * @param pattern 正则表达式样式
     * @return 是否匹配是true,否false
     */
    public static boolean isMatch(String str, String pattern) {
        Pattern patternHand = Pattern.compile(pattern);
        Matcher matcherHand = patternHand.matcher(str);
        boolean b = matcherHand.matches();
        return b;
    }

    /**
     * 截取字符串
     *
     * @param s 源字符串
     * @param jmp 跳过jmp
     * @param sb 取在sb
     * @param se 于se
     * @return 之间的字符串
     */
    public static String subStringExe(String s, String jmp, String sb, String se) {
        if (isEmpty(s)) {
            return "";
        }
        int i = s.indexOf(jmp);
        if (i >= 0 && i < s.length()) {
            s = s.substring(i + 1);
        }
        i = s.indexOf(sb);
        if (i >= 0 && i < s.length()) {
            s = s.substring(i + 1);
        }
        if ("".equals(se)) {
            return s;
        }
        else {
            i = s.indexOf(se);
            if (i >= 0 && i < s.length()) {
                s = s.substring(i + 1);
            }
            return s;
        }
    }

    /**
     * 用要通过URL传输的内容进行编码
     *
     * @param src 源字符串
     * @return 经过编码的内容
     */
    public static String urlEncode(String src) {
        String returnValue = "";
        try {
            if (src != null) {
                returnValue = URLEncoder.encode(src, "GBK");

            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            returnValue = src;
        }

        return returnValue;
    }

    /**
     * 字符串格式转换
     *
     * @param str 传入字符串 <br>
     *            &amp;#31119;test&amp;#29031;&amp;#27004;&amp;#65288;&amp;#21271;&amp;#22823;&amp;#38376;&amp;#
     *            24635 ;&amp;#24215;&amp;#65289;&amp;#31119;
     * @return 经过解码的内容
     */
    public static String getGBK(String str) {
        return transfer(str);
    }

    /**
     * 主要功能:字符串转换 注意事项:无
     *
     * @param str 字符串
     * @return 格式化后字符串
     */
    public static String transfer(String str) {
        Pattern p = Pattern.compile("&#\\d+;");
        Matcher m = p.matcher(str);
        while (m.find()) {
            String old = m.group();
            str = str.replaceAll(old, getChar(old));
        }
        return str;
    }

    /**
     * 主要功能:获取字符 注意事项:无
     *
     * @param str 源字符串
     * @return 字符
     */
    public static String getChar(String str) {
        String dest = str.substring(2, str.length() - 1);
        char ch = (char)Integer.parseInt(dest);
        return "" + ch;
    }

    /**
     * 泛型方法(通用)，把list转换成以&quot;,&quot;<br>
     * 相隔的字符串 调用时注意类型初始化（申明类型）<br>
     * 如：<code>List&lt;java.lang.Integer&gt; intList = new ArrayList&lt;java.lang.Integer&gt;();</code><br>
     * 调用方法：<code>StringUtil.listTtoString(intList);</code> 效率：list中4条信息，1000000次调用时间为850ms左右
     *
     * @author fengliang
     * @serialData 2008-01-09
     * @param <T> 泛型
     * @param list list列表
     * @return 以&quot;,&quot;相隔的字符串
     */
    public static <T> String listTtoString(List<T> list) {
        if (list == null || list.size() < 1) {
            return "";
        }
        Iterator<T> i = list.iterator();
        if (!i.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (;;) {
            T e = i.next();
            sb.append(e);
            if (!i.hasNext()) {
                return sb.toString();
            }
            sb.append(",");
        }
    }

    /**
     * 把整形数组转换成以“,”相隔的字符串
     *
     * @author fengliang
     * @serialData 2008-01-08
     * @param a 数组a
     * @return 以“,”相隔的字符串
     */
    public static String intArraytoString(int[] a) {
        if (a == null) {
            return "";
        }
        int iMax = a.length - 1;
        if (iMax == -1) {
            return "";
        }
        StringBuilder b = new StringBuilder();
        for (int i = 0;; i++ ) {
            b.append(a[i]);
            if (i == iMax) {
                return b.toString();
            }
            b.append(",");
        }
    }

    /**
     * 判断是否是空字符串 null和"" null返回result,否则返回字符串
     *
     * @param s 来源字符串
     * @param result 返回结果
     * @return 返回字符串
     */
    public static String getStringIfEmpty(String s, String result) {
        if (isNotEmpty(s)) {
            return s;
        }
        return result;
    }

    /**
     * 判断对象是否为空
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNotEmpty(Object str) {
        boolean flag = true;
        if (str != null && !str.equals("")) {
            if (str.toString().length() > 0) {
                flag = true;
            }
        }
        else {
            flag = false;
        }
        return flag;
    }

    /**
     * 全角字符变半角字符
     *
     * @param str 源字符串
     * @return 全角字符变半角字符
     */
    public static String full2Half(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < str.length(); i++ ) {
            char c = str.charAt(i);

            if (c >= 65281 && c < 65373) {
                sb.append((char)(c - 65248));
            }
            else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 全角括号转为半角
     *
     * @param str 字符串
     * @return 全角括号转为半角
     */
    public static String replaceBracketStr(String str) {
        if (str != null && str.length() > 0) {
            str = str.replaceAll("（", "(");
            str = str.replaceAll("）", ")");
        }
        return str;
    }

    /**
     * 将list用传入的分隔符组装为String
     *
     * @param list 源数据
     * @param slipStr 分隔符
     * @return String
     */
    public static String listToStringSlipStr(List<String> list, String slipStr) {
        StringBuffer returnStr = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++ ) {
                returnStr.append(list.get(i)).append(slipStr);
            }
        }
        if (returnStr.toString().length() > 0) {
            return returnStr.toString().substring(0, returnStr.toString().lastIndexOf(slipStr));
        }
        else {
            return "";
        }
    }

    /**
     * 获取从start开始用*替换len个长度后的字符串
     *
     * @param str 要替换的字符串
     * @param start 开始位置
     * @param len 长度
     * @return 替换后的字符串
     */
    public static String getMaskStr(String str, int start, int len) {
        if (isEmpty(str)) {
            return str;
        }
        if (str.length() < start) {
            return str;
        }

        // 获取*之前的字符串
        String ret = str.substring(0, start);

        // 获取最多能打的*个数
        int strLen = str.length();
        if (strLen < start + len) {
            len = strLen - start;
        }

        // 替换成*
        for (int i = 0; i < len; i++ ) {
            ret += "*";
        }

        // 加上*之后的字符串
        if (strLen > start + len) {
            ret += str.substring(start + len);
        }

        return ret;
    }

    /**
     * 根据传入的分割符号,把传入的字符串分割为List字符串
     *
     * @param slipStr 分隔的字符串
     * @param src 字符串
     * @return 列表
     */
    public static List<String> stringToStringListBySlipStr(String slipStr, String src) {

        if (src == null) {
            return null;
        }
        List<String> list = new ArrayList<String>();
        String[] result = src.split(slipStr);
        for (int i = 0; i < result.length; i++ ) {
            list.add(result[i]);
        }
        return list;
    }

    /**
     * 截取字符串
     *
     * @param str 原始字符串
     * @param len 要截取的长度
     * @param tail 结束加上的后缀
     * @return 截取后的字符串
     */
    public static String getHtmlSubString(String str, int len, String tail) {
        if (str == null || str.length() <= len) {
            return str;
        }
        int length = str.length();
        char c = ' ';
        String tag = null;
        String name = null;
        int size = 0;
        String result = "";
        boolean isTag = false;
        List<String> tags = new ArrayList<String>();
        int i = 0;
        for (int end = 0, spanEnd = 0; i < length && len > 0; i++ ) {
            c = str.charAt(i);
            if (c == '<') {
                end = str.indexOf('>', i);
            }

            if (end > 0) {
                // 截取标签
                tag = str.substring(i, end + 1);
                int n = tag.length();
                if (tag.endsWith("/>")) {
                    isTag = true;
                }
                else if (tag.startsWith("</")) { // 结束符
                    name = tag.substring(2, end - i);
                    size = tags.size() - 1;
                    // 堆栈取出html开始标签
                    if (size >= 0 && name.equals(tags.get(size))) {
                        isTag = true;
                        tags.remove(size);
                    }
                }
                else { // 开始符
                    spanEnd = tag.indexOf(' ', 0);
                    spanEnd = spanEnd > 0 ? spanEnd : n;
                    name = tag.substring(1, spanEnd);
                    if (name.trim().length() > 0) {
                        // 如果有结束符则为html标签
                        spanEnd = str.indexOf("</" + name + ">", end);
                        if (spanEnd > 0) {
                            isTag = true;
                            tags.add(name);
                        }
                    }
                }
                // 非html标签字符
                if (!isTag) {
                    if (n >= len) {
                        result += tag.substring(0, len);
                        break;
                    }
                    else {
                        len -= n;
                    }
                }

                result += tag;
                isTag = false;
                i = end;
                end = 0;
            }
            else { // 非html标签字符
                len-- ;
                result += c;
            }
        }
        // 添加未结束的html标签
        for (String endTag : tags) {
            result += "</" + endTag + ">";
        }
        if (i < length) {
            result += tail;
        }
        return result;
    }

    /**
     * 主要功能:属性字符串替换 from _ to . 注意事项:无
     *
     * @param property 属性字符串
     * @return 替换后属性字符串
     */
    public static String getProperty(String property) {
        if (property.contains("_")) {
            return property.replaceAll("_", "\\.");
        }
        return property;
    }

    /**
     * 解析前台encodeURIComponent编码后的参数
     *
     * @param property encodeURIComponent (encodeURIComponent(no))
     * @return 参数编码
     */
    public static String getEncodePra(String property) {
        String trem = "";
        if (isNotEmpty(property)) {
            try {
                trem = URLDecoder.decode(property, "UTF-8");
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return trem;
    }

    /**
     * 主要功能:判断一个字符串是否都为数字 注意事项:无
     *
     * @param strNum 源字符串
     * @return boolean
     */
    public boolean isDigit(String strNum) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher(strNum);
        return matcher.matches();
    }

    /**
     * 主要功能:截取数字 注意事项:无
     *
     * @param content 源字符串
     * @return 数字
     */
    public String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     * 主要功能:截取非数字 注意事项:无
     *
     * @param content 源字符串
     * @return 字符串
     */
    public String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     * 判断某个字符串是否存在于数组中
     *
     * @param stringArray 原数组
     * @param source 查找的字符串
     * @return 是否找到
     */
    public static boolean contains(String[] stringArray, String source) {
        // 转换为list
        List<String> tempList = Arrays.asList(stringArray);
        // 利用list的包含方法,进行判断
        return tempList.contains(source) ? true : false;
    }

    /**
     * 首字母大写
     *
     * @param realName 源字符串
     * @return 首字母大写
     */
    public static String firstUpperCase(String realName) {
        return replaceOnce(realName, realName.substring(0, 1),
            realName.substring(0, 1).toUpperCase());
    }

    /**
     * 首字母小写
     *
     * @param realName 源字符串
     * @return 首字母小写
     */
    public static String firstLowerCase(String realName) {
        return replaceOnce(realName, realName.substring(0, 1),
            realName.substring(0, 1).toLowerCase());
    }

    /**
     * 用来处理 "a,b,c,d"这样以逗号分割的字符串
     *
     * @param params 传入的要被分割的字符串
     * @return "'a','b','c'"
     */
    public static String changeArrayToStringWithChar(String params) {
        if (isNotEmpty(params)) {
            String[] tempArray = params.split(",");
            String tempString = "";
            for (int i = 0; i < tempArray.length; i++ ) {
                tempString += "'" + tempArray[i] + "'";
                tempString += ((i + 1) == tempArray.length) ? "" : ",";
            }
            return tempString;
        }
        return "";
    }

    /**
     * 将windows形式地址的"\"转成UNIX形式地址的"/"
     *
     * @param path 文件路径
     * @return UNIX形式地址
     */
    public static String slashify(String path) {
        return path.replaceAll("\\\\+", "/").replaceAll("//+", "/");
    }

    /**
     * 主要功能:字符串转HTML 注意事项:无
     *
     * @param str 源字符串
     * @return HTML
     */
    public static String strToHtml(String str) {
        str = stringReplace(str, "<", "<");
        str = stringReplace(str, ">", ">");
        str = stringReplace(str, " ", "&nbsp;");
        str = stringReplace(str, "\t", "    ");
        str = stringReplace(str, "\n", "<br />");
        return str;
    }

    /**
     * 获取非空字符串
     *
     * @param source 源字符串
     * @return 获取非空字符串
     */
    public static String stringValueOf(String source) {
        return (source == null || "".equals(source)) ? "" : source;
    }

    /**
     * 获取非空字符串，如果源字符串为空，则替换为其它字符
     *
     * @param source 源字符串
     * @param replace 替换字符串
     * @return 非空字符串
     */
    public static String stringValueOf(String source, String replace) {
        return (source == null || "".equals(source)) ? replace : source;
    }

    /**
     * 取得一个非空的整数值
     *
     * @param value 源字符串
     * @return 整数
     */
    public static int integerValueOf(String value) {
        int defaultValue = 0;
        try {
            defaultValue = Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            defaultValue = 0;
        }
        return defaultValue;
    }

    /**
     * 取得一个非空的整数值
     *
     * @param value 源数据
     * @param defaultValue 默认数据
     * @return 整数值
     */
    public static int integerValueOf(String value, int defaultValue) {
        return integerValueOf(value) == 0 ? defaultValue : integerValueOf(value);
    }

    /**
     * 取得一个非空的长整数值
     *
     * @param value 源数据
     * @return long
     */
    public static long longValueOf(String value) {
        long defaultValue = 0;
        try {
            defaultValue = Long.parseLong(value);
        }
        catch (NumberFormatException e) {
            defaultValue = 0;
        }
        return defaultValue;
    }

    /**
     * 取得一个非空的整数值
     *
     * @param value 源数据
     * @param defaultValue 默认值
     * @return long
     */
    public static long longValueOf(String value, long defaultValue) {
        return longValueOf(value) == 0 ? defaultValue : longValueOf(value);
    }

    /**
     * 主要功能:获取字符串长度 注意事项:无
     *
     * @param str 源字符串
     * @return 长度
     */
    public static int length(String str) {
        if (str == null) {
            return 0;
        }
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < str.length(); i++ ) {
            String temp = str.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            }
            else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * <b>截取指定字节长度的字符串，不能返回半个汉字</b>
     *
     * @param str 源字符串
     * @param length 长度
     * @param endWith endWith
     * @return 截取指定字节长度的字符串
     */
    public static String substring(String str, int length, String endWith) {
        int textLength = str.length();
        int byteLength = 0;
        StringBuffer returnStr = new StringBuffer();
        for (int i = 0; i < textLength && byteLength < length * 2; i++ ) {
            String stri = str.substring(i, i + 1);
            if (stri.getBytes().length == 1) {// 英文
                byteLength++ ;
            }
            else {// 中文s
                byteLength += 2;
            }
            returnStr.append(stri);
        }
        try {
            if (byteLength < str.getBytes("GBK").length) {// getBytes("GBK")每个汉字长2，getBytes("UTF-8")每个汉字长度为3
                returnStr.append(endWith);
            }
        }
        catch (UnsupportedEncodingException e) {
            return str;
        }
        return returnStr.toString();
    }

    /**
     * 根据长度生成随机字符串数
     *
     * @param length 表示生成字符串的长度
     * @return "abcdefghijklmnopqrstuvwxyz0123456789"
     */
    public static String getRandomString(Integer length) {
        if (length == null) {
            length = 10;
        }
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++ ) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 主要功能:剔除字符串数组中重复的项 注意事项:无
     *
     * @param arr 传入的字符串数组，其中有重复的项
     * @return String[] resp 传出的字符串数组，已经剔除重复的项了
     */
    public static String[] getUnRepeatedStr(String[] arr) {
        Set<String> rep = new HashSet<String>();
        for (int i = 0; i < arr.length; i++ ) {
            rep.add(arr[i]);
        }
        String[] resp = rep.toArray(new String[] {});
        return resp;
    }

    /**
     * 去掉下划线，并把下划线后一个字母大写
     *
     * @param value String
     * @param needCase 是否需要转小写
     * @return String
     */
    public static String dealString(final String value, boolean needCase) {
        String temp = value;
        if (isNotEmpty(temp) && needCase) {
            temp = temp.toLowerCase();
        }
        StringBuilder result = new StringBuilder();
        if (temp.contains("_")) {
            result.append(temp.substring(0, temp.indexOf("_")));
            if (temp.indexOf("_") + 1 <= temp.length() && temp.indexOf("_") + 2 <= temp.length()) {
                result.append(
                    temp.substring(temp.indexOf("_") + 1, temp.indexOf("_") + 2).toUpperCase());
            }
            if (temp.indexOf("_") + 2 <= temp.length()) {
                result.append(temp.substring(temp.indexOf("_") + 2));
            }
            temp = dealString(result.toString(), false);
        }
        else {
            return temp;
        }

        return temp;
    }

    /**
     * SQL中的模糊查询的入参，根据模糊查询的类型，返回%拼接后的字符串。 类型：1代表左模糊查询（%字符串），2代表右模糊查询（字符串%），3代表整个模糊查询（%字符串%）
     *
     * @param str 字符串
     * @param type 模糊查询类型
     * @return 拼接后的字符串
     */
    public static String toLikeString(String str, int type) {
        if (isEmpty(str)) {
            return str;
        }
        if (type == 1 || type == 3) {
            str = PERCENT_SIGN + str;
        }
        if (type == 2 || type == 3) {
            str = str + PERCENT_SIGN;
        }
        return str;
    }

    /**
     * SQL中的模糊查询的入参，返回全模糊查询的字符串。（%字符串%）
     *
     * @param str 字符串
     * @return 拼接后的字符串
     */
    public static String toLikeString(String str) {
        return toLikeString(str, 3);
    }

    /**
     * 判断是否数字字母组合
     *
     * @param src 源字符串
     * @return 是否数字字母组合的标志
     */
    public static boolean isEnglish(String src) {
        boolean returnValue = false;
        if (src != null && src.length() > 0) {
            Matcher m = ENGLISH_PATTERN.matcher(src);
            if (m.find()) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    /**
     * 消除文件名中的时间戳
     *
     * @param filename 文件名
     * @return 文件名
     */
    public static final String trimFileName(String filename) {
        if (isNotEmpty(filename)) {
            int index = filename.lastIndexOf("_");
            String[] names = splitTwo(filename, ".");
            if (index > 0 && names.length == 2) {
                // if(filename.substring(index + 1).length() == 18 + names[1].length()) {
                filename = filename.substring(0, index) + "." + names[1];
                // }
            }
        }
        return filename;
    }
    
    /**
     * 金额格式化
     * 支持字符串和BigDecimal格式
     * 
     * @param amt 需要格式化的金额
     * @param style 格式化样式
     * @return 文件名
     */
    public static final String amtFormat(Object amt, String style) {
        if(amt == null || style == null) {
            return null;
        }
        DecimalFormat df = new DecimalFormat(style);
        df.setRoundingMode(RoundingMode.HALF_UP);
        if (amt instanceof String) {
            return df.format(new BigDecimal((String)amt));
        }
        if (amt instanceof Number) {
            return df.format(amt);
        }
        return null;
    }
    
    /**
     * 数字转百分数字符串（带百分号）
     * 保留小数点后两位小数，如1.12%
     * @param decimal 需要格式化的数字
     * @return String
     */
    public static final String decimalToPercent(BigDecimal decimal) {
        if(decimal != null) {
            return decimal.multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_DOWN).toPlainString() + PERCENT_SIGN;
        }
            
        return null;
    }
    

    /**
     * null时返回0
     *
     * @param item BigDecimal
     * @return 变换后的值
     * @see
     * @since 1.0
     */
    public static BigDecimal nullToZero(BigDecimal item) {
        if (item == null) {
            return BigDecimal.ZERO;
        }
        else {
            return item;
        }
    }

    /**
     * 生成随机数字和字母, 根据长度生成指定长度的随机字母和数字
     * 
     * @param length 长度
     * @return 随机串
     * @see
     * @since 1.0
     */
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        // 参数length，表示生成几位随机数
        for (int i = 0; i < length; i++ ) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            }
            else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
    
    /**
     * 获取中文所占的长度
     * 
     * @param str
     * @return 
     */
    public static int getStrRealLength(String str) {
        int m = 0;
        char arr[] = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
                char c = arr[i];
                // 中文字符(根据Unicode范围判断),中文字符长度为2
                if ((c >= 0x0391 && c <= 0xFFE5)) {
                    m = m + 2;
                }else {
                    // 英文字符
                    m = m + 1;
                }
        }
        return m;
    }
    
    
    /**
	 * 判断经过浏览器编码后的链接是否合法
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isUtf8Url(String text) {
		text = text.toLowerCase();
		int p = text.indexOf("%");
		if (p != -1 && text.length() - p > 9) {
			text = text.substring(p, p + 9);
		}
		return Utf8codeCheck(text);
	}
	
	/**
	 * 编码是否有效
	 * 
	 * @param text
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean Utf8codeCheck(String text) {
		String sign = "";
		if (text.startsWith("%e")) {
			for (int i = 0, p = 0; p != -1; i++) {
				p = text.indexOf("%", p);
				if (p != -1) {
					p++;
				}
				sign += p;
			}
		}
		return sign.equals("147-1");
	}
	
	/**
	 * 将经过浏览器编码后的url解码
	 * 
	 * @param text
	 * @return
	 */
	public static String Utf8URLdecode(String text) {
		String result = "";
		int p = 0;
		if (text != null && text.length() > 0) {
			text = text.toLowerCase();
			p = text.indexOf("%e");
			if (p == -1) {
				return text;
			}
			while (p != -1) {
				result += text.substring(0, p);
				text = text.substring(p, text.length());
				if (text == "" || text.length() < 9) {
					return result;
				}
				result += CodeToWord(text.substring(0, 9));
				text = text.substring(9, text.length());
				p = text.indexOf("%e");
			}
		}
		return result + text;
	}
	
	/**
	 * utf8URL编码转字符
	 * 
	 * @param text
	 * @return
	 */
	public static final String CodeToWord(String text) {
		String result;
		if (Utf8codeCheck(text)) {
			byte[] code = new byte[3];
			code[0] = (byte) (Integer.parseInt(text.substring(1, 3), 16) - 256);
			code[1] = (byte) (Integer.parseInt(text.substring(4, 6), 16) - 256);
			code[2] = (byte) (Integer.parseInt(text.substring(7, 9), 16) - 256);
			try {
				result = new String(code, "UTF-8");
			} catch (UnsupportedEncodingException ex) {
				result = null;
			}
		} else {
			result = text;
		}
		return result;
	}
	
	/**
     * 字符串转成BigDecimal
     * 
     * @param str 字符串
     * @return 返回值
     * @see
     * @since 1.0
     */
    public static BigDecimal strToBigDecimal(String str) {
        if(!StringUtil.isNotEmpty(str)) {
            return null;
        }
        else {
            return new BigDecimal(str);
        }
    }
    
    /**
     * BigDecimal转成字符串
     * 
     * @param str 字符串
     * @return 返回值
     * @see
     * @since 1.0
     */
    public static String bigDecimalToStr(BigDecimal str) {
        if(!StringUtil.isNotEmpty(str)) {
            return null;
        }
        else {
            return str.toString();
        }
    }
    
    /**
     * 获取符合正则表达式的字符串
     * 
     * @param str 原字符串
     * @param rgex 正则表达式
     * @return 结果
     */
    public static String getRgexStrSimple(String str, String rgex){
    	if(!StringUtil.isNotEmpty(str) || !StringUtil.isNotEmpty(rgex)) {
    		return null;
    	}
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(str);
        while(m.find()){
            return m.group(1);
        }
        return null;
    }
}
