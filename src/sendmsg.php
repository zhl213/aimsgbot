<?php
$urlpre='http://msg.fx-i.cn:85/aibot/msg/';
$url=$urlpre.'?act=sendmsg';

$mcode='你的机器码';

$data=[
    "mcode"=>$mcode,
    "app"=>"dingtalk",
    "data"=>[
        ["c"=>"我",'m'=>'http://msg.fx-i.cn:85/aibot.txt','t'=>'file',],
        ["c"=>"酷服务群","m"=>"测试消息02","t"=>"text"],
        ["c"=>"我","m"=>"测试消息01","t"=>"text"],
        ["c"=>"酷服务群",'m'=>'http://msg.fx-i.cn:85/aibot.png','t'=>'img',],
				['c'=>'梵星工作室',
				 'd'=>[
							[
								'm'=>'测试消息1',
								't'=>'text',
							],
							[
								'm'=>'测试消息2',
								't'=>'text',
							],
							[
								'm'=>'http://msg.fx-i.cn:85/aibot.txt',
								't'=>'file',
							],
							[
								'm'=>'测试消息3',
								't'=>'text',
							],
							[
								'm'=>'http://msg.fx-i.cn:85/aibot.png',
								't'=>'img',
							],
							[
								'm'=>'测试消息4',
								't'=>'text',
							],
					],
				],

    ]

];
$data=[
    "mcode"=>$mcode,
    "app"=>"workwx",
    "data"=>[
        ["c"=>"bbbb",'m'=>'http://msg.fx-i.cn:85/aibot.txt','t'=>'file',],
        ["c"=>"测试消息-外部群111","m"=>"测试消息02","t"=>"text"],
        ["c"=>"bbbb","m"=>"测试消息01","t"=>"text"],
        ["c"=>"fx_test",'m'=>'http://msg.fx-i.cn:85/aibot.png','t'=>'img',],
				['c'=>'bbbb',
				 'd'=>[
							[
								'm'=>'测试消息1',
								't'=>'text',
							],
							[
								'm'=>'测试消息2',
								't'=>'text',
							],
							[
								'm'=>'http://msg.fx-i.cn:85/aibot.txt',
								't'=>'file',
							],
							[
								'm'=>'测试消息3',
								't'=>'text',
							],
							[
								'm'=>'http://msg.fx-i.cn:85/aibot.png',
								't'=>'img',
							],
							[
								'm'=>'测试消息4',
								't'=>'text',
							],
					],
				],

    ]

];

$data=[
    "mcode"=>$mcode,
    "app"=>"feishu",
    "data"=>[
        ["c"=>"飞书智能助手",'m'=>'http://msg.fx-i.cn:85/aibot.txt','t'=>'file',],
        ["c"=>"飞书智能助手","m"=>"测试消息02","t"=>"text"],
        ["c"=>"飞书智能助手","m"=>"测试消息01","t"=>"text"],
        ["c"=>"飞书智能助手",'m'=>'http://msg.fx-i.cn:85/aibot.png','t'=>'img',],
				['c'=>'飞书智能助手',
				 'd'=>[
							[
								'm'=>'测试消息1',
								't'=>'text',
							],
							[
								'm'=>'测试消息2',
								't'=>'text',
							],
							[
								'm'=>'http://msg.fx-i.cn:85/aibot.txt',
								't'=>'file',
							],
							[
								'm'=>'测试消息3',
								't'=>'text',
							],
							[
								'm'=>'http://msg.fx-i.cn:85/aibot.png',
								't'=>'img',
							],
							[
								'm'=>'测试消息4',
								't'=>'text',
							],
					],
				],

    ]

];

$postJsonData = json_encode($data,JSON_UNESCAPED_UNICODE);
print_R($postJsonData);
$response = http($url,$postJsonData);
print_R($response);
$response=json_decode($response,1);
print_R($response);
	

function http($url,$data){
	$ch = curl_init($url);
	curl_setopt_array($ch,array(
		CURLOPT_HTTPPROXYTUNNEL=>TRUE,
		CURLOPT_POST=>TRUE,
		CURLOPT_HEADER=>FALSE,
		CURLOPT_RETURNTRANSFER=>TRUE,
		CURLOPT_POSTFIELDS=>$data,
		CURLOPT_SSL_VERIFYHOST=>false,
	));
	$response=curl_exec($ch);
	if($response === FALSE){ die(curl_error($ch)); }
	return $response;
}
