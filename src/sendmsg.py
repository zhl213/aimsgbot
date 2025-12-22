import requests
import json

# 常量定义（对应PHP中的变量）
MCODE = "你的机器码"  # 替换为实际机器码
URL_PRE = "http://msg.fx-i.cn:85/aibot/msg/"
URL = f"{URL_PRE}?act=sendmsg"

def http_post(url, json_data):
    """
    发送HTTP POST请求（对应PHP的http函数）
    :param url: 请求地址
    :param json_data: JSON请求数据（字典）
    :return: 响应文本
    """
    try:
        # 构建请求（requests会自动序列化字典为JSON，设置Content-Type: application/json）
        # verify=False 对应PHP的CURLOPT_SSL_VERIFYHOST=false（关闭SSL证书验证）
        # 若需要代理，添加proxies参数：proxies={"http": "http://代理IP:端口", "https": "https://代理IP:端口"}
        response = requests.post(
            url=url,
            json=json_data,  # 自动序列化+设置Content-Type
            verify=False,    # 关闭SSL验证
            # proxies={"http": "http://127.0.0.1:8080"}  # 如需代理，取消注释并配置
        )
        response.raise_for_status()  # 抛出HTTP请求异常（如404、500等）
        return response.text
    except requests.exceptions.RequestException as e:
        print(f"请求失败：{e}")
        raise

def build_dingtalk_data():
    """构建钉钉(dingtalk)的请求数据（对应PHP中dingtalk的$data）"""
    return {
        "mcode": MCODE,
        "app": "dingtalk",
        "data": [
            {"c": "我", "m": "http://msg.fx-i.cn:85/aibot.txt", "t": "file"},
            {"c": "酷服务群", "m": "测试消息02", "t": "text"},
            {"c": "我", "m": "测试消息01", "t": "text"},
            {"c": "酷服务群", "m": "http://msg.fx-i.cn:85/aibot.png", "t": "img"},
            {
                "c": "梵星工作室",
                "d": [
                    {"m": "测试消息1", "t": "text"},
                    {"m": "测试消息2", "t": "text"},
                    {"m": "http://msg.fx-i.cn:85/aibot.txt", "t": "file"},
                    {"m": "测试消息3", "t": "text"},
                    {"m": "http://msg.fx-i.cn:85/aibot.png", "t": "img"},
                    {"m": "测试消息4", "t": "text"},
                ]
            }
        ]
    }

def build_workwx_data():
    """构建企业微信(workwx)的请求数据（对应PHP中workwx的$data）"""
    return {
        "mcode": MCODE,
        "app": "workwx",
        "data": [
            {"c": "bbbb", "m": "http://msg.fx-i.cn:85/aibot.txt", "t": "file"},
            {"c": "测试消息-外部群111", "m": "测试消息02", "t": "text"},
            {"c": "bbbb", "m": "测试消息01", "t": "text"},
            {"c": "fx_test", "m": "http://msg.fx-i.cn:85/aibot.png", "t": "img"},
            {
                "c": "bbbb",
                "d": [
                    {"m": "测试消息1", "t": "text"},
                    {"m": "测试消息2", "t": "text"},
                    {"m": "http://msg.fx-i.cn:85/aibot.txt", "t": "file"},
                    {"m": "测试消息3", "t": "text"},
                    {"m": "http://msg.fx-i.cn:85/aibot.png", "t": "img"},
                    {"m": "测试消息4", "t": "text"},
                ]
            }
        ]
    }

def build_feishu_data():
    """构建飞书(feishu)的请求数据（对应PHP中feishu的$data）"""
    return {
        "mcode": MCODE,
        "app": "feishu",
        "data": [
            {"c": "飞书智能助手", "m": "http://msg.fx-i.cn:85/aibot.txt", "t": "file"},
            {"c": "飞书智能助手", "m": "测试消息02", "t": "text"},
            {"c": "飞书智能助手", "m": "测试消息01", "t": "text"},
            {"c": "飞书智能助手", "m": "http://msg.fx-i.cn:85/aibot.png", "t": "img"},
            {
                "c": "飞书智能助手",
                "d": [
                    {"m": "测试消息1", "t": "text"},
                    {"m": "测试消息2", "t": "text"},
                    {"m": "http://msg.fx-i.cn:85/aibot.txt", "t": "file"},
                    {"m": "测试消息3", "t": "text"},
                    {"m": "http://msg.fx-i.cn:85/aibot.png", "t": "img"},
                    {"m": "测试消息4", "t": "text"},
                ]
            }
        ]
    }

if __name__ == "__main__":
    # 选择要发送的平台数据（可切换为build_dingtalk_data()/build_workwx_data()）
    data = build_feishu_data()

    # JSON序列化（ensure_ascii=False 对应PHP的JSON_UNESCAPED_UNICODE，不转义中文）
    post_json_data = json.dumps(data, ensure_ascii=False)
    print("请求JSON数据：")
    print(post_json_data)
    print("-" * 50)

    # 发送POST请求
    try:
        response_text = http_post(URL, data)  # requests.post的json参数可直接传字典，无需手动序列化
        # 若需手动传序列化后的字符串，可改为：
        # response = requests.post(URL, data=post_json_data, headers={"Content-Type": "application/json"}, verify=False)
        # response_text = response.text
    except Exception as e:
        response_text = str(e)
        print(f"请求异常：{response_text}")
        exit(1)

    # 打印原始响应
    print("响应原始数据：")
    print(response_text)
    print("-" * 50)

    # 解析响应为字典（对应PHP的json_decode($response, 1)）
    try:
        response_data = json.loads(response_text)
        print("响应解析后的数据：")
        print(response_data)
    except json.JSONDecodeError as e:
        print(f"响应解析失败：{e}")
