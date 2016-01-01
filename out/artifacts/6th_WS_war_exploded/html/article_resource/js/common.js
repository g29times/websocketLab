// JS获取地址栏参数的方法
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}
// 获取文章id
function getArtId(src) {
	var id = src.substring( src.lastIndexOf("/")+1, src.length-4 );
	return id;
}
// 引入外部HTML生成内容
function dyGenerate(element, url, callback) {
	// 在IE7下测试通过，IE6下必须创建 new ActiveXObject("MSXML2.XMLHTTP.6.0")
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true); // 异步
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4/* && xhr.status == 200*/) {
			console.log('xhr.status: ' + xhr.status);
			if(callback) {
				var object = JSON.parse(xhr.responseText);
				callback(object);
			}

			if(element != null && element.indexOf('#') != -1){ // 根据id
				document.getElementById(element.substring(1, element.length)).innerHTML = xhr.responseText;
			} else if(element != null)
				document.getElementsByTagName(element)[0].innerHTML = xhr.responseText;
			// document.getElementById("tester").innerHTML = 123;
		}
	}
	xhr.send();
}
// 文章插件管理
function pluginManage() {
	$.ajax({
		url: "/6th_WS/PageServlet",
		type: "GET",
		data: "getPlugin",
		complete: function (XMLHttpRequest) {
			console.log(XMLHttpRequest.status);
			console.log(XMLHttpRequest.readyState);
			var plugins = JSON.parse(XMLHttpRequest.responseText);
			for(var p in plugins) {
				//console.log("../_"+plugins[p].replace("#","")+".htm");
				/** [class: . | id: _ | element: name] */
				if(plugins[p].indexOf("#") == -1)
					dyGenerate(plugins[p], "../import.htm");
				else
					dyGenerate(plugins[p], "../_"+plugins[p].replace("#","")+".htm");
//		dyGenerate("#social-bar", "../_social-bar.htm");
//		dyGenerate("#sidebar", "../_sidebar.htm");
//		dyGenerate("#respond", "../_respond.htm");
//		dyGenerate("footer", "../import.htm");
			}
		}
	});
}
// 文章内容管理
function contentManage() {
	$.ajax({
		url: "/6th_WS/PageServlet",
		type: "GET",
		success: function (html) {
			var title, body, info;
			title = $(".post-heading");
			body = $(".excerpt");
			//info =
			if(html.method == "ADD") {
				title.html(html.title);
				body.html(html.body);
			} else if(html.method == "DELETE") {
				title.html("");
				body.html("");
			} else {
				title.html();
				body.html();
			}
		}
	});

	//$.ajax({
	//	url: "/6th_WS/PageServlet",
	//	type: "POST",
	//	data: {
	//		title: $(".post-heading").html(),
	//		body: $(".excerpt").html(),
	//		//info: document.getElementById("ta_info").value,
	//	},
	//	success: function () {
	//		console.log("logged");
	//	},
	//});

	//$.ajax({
	//	url: "/6th_WS/PageServlet",
	//	type: "GET",
	//	success: function (html) {
	//		$.ajax({
	//			url: "/6th_WS/PageServlet",
	//			type: "GET",
	//			data: {query: "content"},
	//			//context: $(".post-heading"),
	//			success: function (area) {
	//				//$(this).html();
	//				var context;
	//				if(area == "TITLE")
	//					context = $(".post-heading");
	//				else if(area == "BODY")
	//					context = $(".excerpt");
	//
	//				if(html.method == "ADD")
	//					context.html(html.content);
	//				else if(html.method == "DELETE")
	//					context.html("");
	//				else
	//					context.html();
	//			}
	//		});
	//	}
	//});
}