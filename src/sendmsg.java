import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对应PHP代码的Java实现：发送消息到指定接口
 */
public class MessageSender {
    // 常量定义（对应PHP中的变量）
    private static final String MCODE = "你的机器码"; // 替换为实际机器码
    private static final String URL_PRE = "http://msg.fx-i.cn:85/aibot/msg/";
    private static final String URL = URL_PRE + "?act=sendmsg";

    public static void main(String[] args) throws IOException {
        // 1. 构建请求数据（对应PHP中最后赋值的飞书(feishu)数据，若需测试钉钉/企业微信，修改此处即可）
        Map<String, Object> dataMap = buildFeishuData();

        // 2. JSON序列化（对应PHP的json_encode，JSON_UNESCAPED_UNICODE：不转义中文）
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String postJsonData = gson.toJson(dataMap);
        System.out.println("请求JSON数据：");
        System.out.println(postJsonData);

        // 3. 发送HTTP POST请求（对应PHP的http函数）
        String response = httpPost(URL, postJsonData);
        System.out.println("\n响应原始数据：");
        System.out.println(response);

        // 4. 解析响应为Map（对应PHP的json_decode）
        Type responseType = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> responseMap = gson.fromJson(response, responseType);
        System.out.println("\n响应解析后的数据：");
        System.out.println(responseMap);
    }

    /**
     * 构建飞书(feishu)的请求数据（对应PHP中feishu的$data）
     */
    private static Map<String, Object> buildFeishuData() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("mcode", MCODE);
        dataMap.put("app", "feishu");

        // 构建data数组
        List<Object> dataList = new ArrayList<>();

        // 元素1：文件消息
        Map<String, Object> item1 = new HashMap<>();
        item1.put("c", "飞书智能助手");
        item1.put("m", "http://msg.fx-i.cn:85/aibot.txt");
        item1.put("t", "file");
        dataList.add(item1);

        // 元素2：文本消息-测试02
        Map<String, Object> item2 = new HashMap<>();
        item2.put("c", "飞书智能助手");
        item2.put("m", "测试消息02");
        item2.put("t", "text");
        dataList.add(item2);

        // 元素3：文本消息-测试01
        Map<String, Object> item3 = new HashMap<>();
        item3.put("c", "飞书智能助手");
        item3.put("m", "测试消息01");
        item3.put("t", "text");
        dataList.add(item3);

        // 元素4：图片消息
        Map<String, Object> item4 = new HashMap<>();
        item4.put("c", "飞书智能助手");
        item4.put("m", "http://msg.fx-i.cn:85/aibot.png");
        item4.put("t", "img");
        dataList.add(item4);

        // 元素5：嵌套d数组的消息
        Map<String, Object> item5 = new HashMap<>();
        item5.put("c", "飞书智能助手");
        // 构建d数组
        List<Object> dList = new ArrayList<>();
        dList.add(buildDItem("测试消息1", "text"));
        dList.add(buildDItem("测试消息2", "text"));
        dList.add(buildDItem("http://msg.fx-i.cn:85/aibot.txt", "file"));
        dList.add(buildDItem("测试消息3", "text"));
        dList.add(buildDItem("http://msg.fx-i.cn:85/aibot.png", "img"));
        dList.add(buildDItem("测试消息4", "text"));
        item5.put("d", dList);
        dataList.add(item5);

        dataMap.put("data", dataList);
        return dataMap;
    }

    /**
     * 构建企业微信(workwx)的请求数据（对应PHP中workwx的$data）
     * 若需测试，在main方法中调用此方法替换buildFeishuData即可
     */
    private static Map<String, Object> buildWorkwxData() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("mcode", MCODE);
        dataMap.put("app", "workwx");

        List<Object> dataList = new ArrayList<>();
        // 对应PHP中workwx的data元素，此处省略重复逻辑，可参考buildFeishuData实现
        dataList.add(buildItem("bbbb", "http://msg.fx-i.cn:85/aibot.txt", "file"));
        dataList.add(buildItem("测试消息-外部群111", "测试消息02", "text"));
        dataList.add(buildItem("bbbb", "测试消息01", "text"));
        dataList.add(buildItem("fx_test", "http://msg.fx-i.cn:85/aibot.png", "img"));

        Map<String, Object> item5 = new HashMap<>();
        item5.put("c", "bbbb");
        List<Object> dList = new ArrayList<>();
        dList.add(buildDItem("测试消息1", "text"));
        dList.add(buildDItem("测试消息2", "text"));
        dList.add(buildDItem("http://msg.fx-i.cn:85/aibot.txt", "file"));
        dList.add(buildDItem("测试消息3", "text"));
        dList.add(buildDItem("http://msg.fx-i.cn:85/aibot.png", "img"));
        dList.add(buildDItem("测试消息4", "text"));
        item5.put("d", dList);
        dataList.add(item5);

        dataMap.put("data", dataList);
        return dataMap;
    }

    /**
     * 构建钉钉(dingtalk)的请求数据（对应PHP中dingtalk的$data）
     * 若需测试，在main方法中调用此方法替换buildFeishuData即可
     */
    private static Map<String, Object> buildDingtalkData() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("mcode", MCODE);
        dataMap.put("app", "dingtalk");

        List<Object> dataList = new ArrayList<>();
        // 对应PHP中dingtalk的data元素，此处省略重复逻辑，可参考buildFeishuData实现
        dataList.add(buildItem("我", "http://msg.fx-i.cn:85/aibot.txt", "file"));
        dataList.add(buildItem("酷服务群", "测试消息02", "text"));
        dataList.add(buildItem("我", "测试消息01", "text"));
        dataList.add(buildItem("酷服务群", "http://msg.fx-i.cn:85/aibot.png", "img"));

        Map<String, Object> item5 = new HashMap<>();
        item5.put("c", "梵星工作室");
        List<Object> dList = new ArrayList<>();
        dList.add(buildDItem("测试消息1", "text"));
        dList.add(buildDItem("测试消息2", "text"));
        dList.add(buildDItem("http://msg.fx-i.cn:85/aibot.txt", "file"));
        dList.add(buildDItem("测试消息3", "text"));
        dList.add(buildDItem("http://msg.fx-i.cn:85/aibot.png", "img"));
        dList.add(buildDItem("测试消息4", "text"));
        item5.put("d", dList);
        dataList.add(item5);

        dataMap.put("data", dataList);
        return dataMap;
    }

    /**
     * 辅助方法：构建data中的单个元素
     */
    private static Map<String, Object> buildItem(String c, String m, String t) {
        Map<String, Object> item = new HashMap<>();
        item.put("c", c);
        item.put("m", m);
        item.put("t", t);
        return item;
    }

    /**
     * 辅助方法：构建d中的单个元素
     */
    private static Map<String, Object> buildDItem(String m, String t) {
        Map<String, Object> dItem = new HashMap<>();
        dItem.put("m", m);
        dItem.put("t", t);
        return dItem;
    }

    /**
     * 发送HTTP POST请求（对应PHP的http函数）
     * @param url 请求地址
     * @param jsonData JSON请求体
     * @return 响应字符串
     * @throws IOException 异常抛出（可根据需求自行处理）
     */
    private static String httpPost(String url, String jsonData) throws IOException {
        // OkHttp客户端（可全局单例，此处为简化直接创建）
        OkHttpClient client = new OkHttpClient.Builder()
                // 若需要代理，可在此配置（对应PHP的CURLOPT_HTTPPROXYTUNNEL）
                // .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("代理IP", 代理端口)))
                // 若为HTTPS且需要忽略证书验证，需添加SSL配置（对应PHP的CURLOPT_SSL_VERIFYHOST=false）
                .build();

        // 构建请求体（JSON格式，UTF-8编码）
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonData, mediaType);

        // 构建POST请求
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        // 执行请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("请求失败：" + response.code() + " " + response.message());
            }
            // 读取响应体（注意：response.body().string()只能调用一次）
            return response.body().string();
        }
    }
}
