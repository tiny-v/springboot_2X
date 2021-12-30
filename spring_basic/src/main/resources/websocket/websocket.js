
//web socket 对象数组
var socket=[]; 

//创建socket连接功能函数
function connect(ws) {
//浏览器支持
if ("WebSocket" in window) {
	var host = "ws://127.0.0.1:8080"
	socket[ws]= new WebSocket(host);

	try {

		//连接事件
		socket[ws].onopen = function (msg) {
		  console.log(ws+":连接已建立！");
		};

		//错误事件
		socket[ws].onerror = function (msg) {
			console.log("错误："+msg);
		}

		//消息事件
		socket[ws].onmessage = function (msg) {
		   console.log(ws+" 消息接收:"+msg.data);
			if (typeof msg.data == "string") {
				console.log(dev+":文本消息");
			} else {
				console.log(dev+":非文本消息");
			}
		};

		//关闭事件
		socket[ws].onclose = function (msg) {
			console.log(ws+":socket closed!")
		};
	}catch (ex) {
		log(ex);
	}
} else {
	// 浏览器不支持 WebSocket
	alert("您的浏览器不支持 WebSocket!");
}

}

//创建多个web socket 连接
function StartConn() {
	for(var i=0; i<=4; i++) {
		connect(i);
	}
}
