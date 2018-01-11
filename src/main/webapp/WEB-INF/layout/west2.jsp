<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	var setting  = {
		data:{
			// [{"id":1, "url":"", "name":"", "pid":0, "children":[]}, {}, {}]
			simpleData:{
				enable: true,  //开启简单树
				idKey: 'id',   //每条数据的id,是哪个字段
				pIdKey: 'pid',  //标注当前节点的父节点的 字段
				rootPId: 0  //根节点的值
			}
		},
		async: {
			enable: true,   //开启异步加载
			dataType:'json',  //返回的数据类型
			url:'${pageContext.servletContext.contextPath}/menus/treeMenu',  //请求的url地址
			type:'post' //请求方式
		},
		view:{
			fontCss:{
				color:"#ff0011"
			}
		},
		callback:{
			onClick: addToTabs
		}
	};
	
	$(function(){
		var menuTree = $.fn.zTree.init($("#menuTree"), setting);
		
		setTimeout(function(){   //setTimeout是js自带的方法，表示过多长时间执行某个事件
			menuTree.expandAll(true);		
		}, 1000);
		 //展开或关闭所有节点
	});
	
	function addToTabs(event, treeId, treeNode) {
		//{"id":1, "url":"", "name":"", "pid":0, "children":[], page:'../orders/order'}
		var title = treeNode.name;
		var main_content_tabs = $("#main_content_tabs"); 
		if(treeNode.page != null){  //对于父节点，page为空
			if(main_content_tabs.tabs('exists', title)){  //存在
				main_content_tabs.tabs('select', title);  //让其选中
			}else{  //不存在
				main_content_tabs.tabs('add', {  //add 添加
					title: title,  //tab上展示的是a标签中文字
					href: treeNode.page,   //请求的地址， href, 是将请求的数据资源，填充到tab
					closable: true //可关闭	
				});
			}
		}
	}
</script>

<div data-options="region:'west',width:150, title:'菜单栏', split:true">
	<ul id="menuTree" class="ztree"></ul>
</div>