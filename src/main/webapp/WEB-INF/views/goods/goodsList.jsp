<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$("#clear_search_btn").click(function(){  //清空搜索表单数据
			$("#goods_search_form input").val("")
		});
		$("#search_goods_btn").click(function(){
			var goods_name = $("input[name='goods_name']").val();
			var min_price = $("input[name='min_price']").val();
			var max_price = $("input[name='max_price']").val();
			//console.log(goods_name + "::" + min_price + "::" + max_price);
			$("#goods_content_datagrid").datagrid("load", 
						{"goods_name":goods_name, "min_price":min_price, "max_price":max_price});
		});
		
		 //打开添加的dialog
		$("#add_goods_btn").click(function(){
			$('#add_goods_dialog').dialog('open');
		});
		 
		$("#delete_goods_btn").click(function(){
			var rows = $("#goods_content_datagrid").datagrid("getSelections");  //数组，对应选中的Json数据
			if(rows.length > 0){
				$.messager.confirm("提示", "确定删除选中的数据？", function(r){
					if(r){  //用户点击确定
						var ids = [];  //存放id的数组
						for(var i = 0; i < rows.length; i++){
							ids.push(rows[i].gid);  //将id放入数组
						}
						var idsStr = ids.join(","); // join(str) 返回字符串,用指定的字符串拼接起来   1275,1234,4456,
						$.post(  //jquery的ajax
	        				"${pageContext.servletContext.contextPath}/goods/delete",
	        				{ids:idsStr},   //传递到后台的数据
	        				function(_data){
	        					if(_data.success){  //校验成功
	        						$("#goods_content_datagrid").datagrid("load");
	        						$.messager.show({
	        							title:"系统信息",
	        							content: _data.obj,
	        							timeout: 2000
	        						});
	        					}else{
	        						$.messager.show({
	        							title:"系统信息",
	        							content:  _data.obj,
	        							timeout: 2000
	        						});
	        					}
	        				},
	        				"json"
		        		);
					}
				});
			}else{
				$.messager.show({
					title:"系统信息",
					content:"请选择要删除的数据",
					timeout: 2000
				});
			}
		});
		
		$("#edit_goods_btn").click(function(){
			var rows = $("#goods_content_datagrid").datagrid("getSelections");
			if(rows.length == 1){
				$('#eidt_goods_dialog').dialog('open');
				$("#eidt_goods_form").form('load', rows[0]);  //讲json数据，按照form表单中的名字，对应赋值
			}else{
				$.messager.alert("警告", "请选择一条数据.", "warn");
			}
		});
	});
</script>

<div class="easyui-layout" data-options="border:false, fit:true">
	<div data-options="region:'north',border:false" style="height:60px;">
		<form id="goods_search_form">
			<div>
				<span style="padding-left: 10px;">商品名称：</span> <input type="text" class="easyui-textbox" name="goods_name" style="width:150px;"></input>
				<span style="padding-left: 10px;">价格区间：</span> <input type="text" class="easyui-textbox" name="min_price" style="width:150px;"></input>
															- <input type="text" class="easyui-textbox" name="max_price" style="width:150px;"></input>
			</div>
			<div>
				<a class="easyui-linkbutton" id="search_goods_btn" data-options="iconCls:'icon-search'">搜索</a>
				<a class="easyui-linkbutton" id="clear_search_btn" data-options="iconCls:'icon-clear'">清空</a>
			</div>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<!-- striped:隔行换色, paginatioin:true 是否分页 -->
		<table id="goods_content_datagrid" class="easyui-datagrid" 
			data-options="border:false, 
						  striped:true, 
						  fitColumns:true,
						  collapsible: true,
						  rownumbers: true,
						  url:'${pageContext.servletContext.contextPath}/goods/getList', 
						  pagination:true,
						  toolbar: '#goods_datagrid_toolbar',
						">
						
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'gid',width:'12%'">商品编号</th>
					<th data-options="field:'goods_name',width:'12%'">商品名称</th>
					<th data-options="field:'goods_price',width:'12%'">商品价格</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<div id="goods_datagrid_toolbar">
	<a href="#" class="easyui-linkbutton" id="add_goods_btn" data-options="iconCls:'icon-add',plain:true">添加</a>
	<a href="#" class="easyui-linkbutton" id="edit_goods_btn" data-options="iconCls:'icon-edit',plain:true">编辑</a>
	<a href="#" class="easyui-linkbutton" id="delete_goods_btn" data-options="iconCls:'icon-remove',plain:true">删除</a>
</div>
<jsp:include page="goodsAdd.jsp"></jsp:include>
<jsp:include page="goodsEdit.jsp"></jsp:include>
