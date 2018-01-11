<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	li{
		list-style: none;
		margin: 12px 0 0 5px;
		padding: 0;
	}
	li:hover{
		background-color: #0099cc;
	}
	.aLink{
		text-decoration: none;
		font-size: 15px;
		color: #000000;
	}
</style>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:"${pageContext.servletContext.contextPath}/menus/menu",  //请求的url地址
			dataType:"json", //后台返回的数据类型，
			type:"get",
			success: function(_data){
				for(var i = 0; i < _data.length; i++){ 
					var parentItem = _data[i];
					/*
					[{"id":1, 
					  "name":"订单管理", 
					  "url":null, 
					  "children":[{"id":2, "name":"已支付单", "url":"orders/list", "children":[]}, 
					              {"id":3, "name":"未支付单", "url":null, "children":[]}, 
					              {"id":4, "name":"gejiji支付单", "url":null, "children":[]}]}, 
					 {}]
					*/
					var childrenItem = parentItem.children;
					var str = "";
					for(var j = 0; j < childrenItem.length; j++){
						var child = childrenItem[j];
						/// easyui onclick中也要返回false
						str += "<li><a onclick='return addContentToTabs(this)' class='aLink' href='" + child.url + "'>" + child.name + "</a></li>";
					}
					$("#menus_accordion").accordion("add", {
							title: parentItem.name,
							content: str
						}		
					)
				}
			}
		});
	});
	function addContentToTabs(amenu) {
		var menu = $(amenu); // 将dom对象转换为jquery的对象
		var title = menu.html(); //获取a标签中的内容
		var main_content_tabs = $("#main_content_tabs");  //获取到tab面板，在main.jsp文件中
		if(main_content_tabs.tabs('exists', title)){  //tabs中如果存在着对应的标题
			main_content_tabs.tabs('select', title);  //让其选中
		}else{
			main_content_tabs.tabs('add', {  //add 添加
				title: title,  //tab上展示的是a标签中文字
				href: amenu.href,   //请求的地址， href, 是将请求的数据资源，填充到tab
				closable: true //可关闭	
			});
		}
		return false; //阻止a标签连接的触发
	}
</script>

<div data-options="region:'west',width:150, title:'菜单栏', split:true">
	<div id="menus_accordion" class="easyui-accordion" data-options="fit:true,border:false,collapsible:true">
	</div>
</div>