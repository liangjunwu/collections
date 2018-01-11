<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$("#clear_search_btn").click(function(){  //清空搜索表单数据
			$("#orders_search_form input").val("")
		});
		$("#search_orders_btn").click(function(){
			var platform = $("input[name='platform']").val();
			var account_name = $("input[name='account_name']").val();
			var pay_type = $("input[name='pay_type']").val();
			var beginTime = $("input[name='beginTime']").val();
			var endTime = $("input[name='endTime']").val();
			//console.log(platform + "::" + pay_type + "::" + account_name + "::" + beginTime + "::" + endTime);
			$("#orders_content_datagrid").datagrid("load", 
						{"platform":platform, "account_name":account_name, "pay_type":pay_type, "beginTime":beginTime, "endTime":endTime});
		});
		
		$("#list_platfrom_combobox,#add_platfrom_combobox").combobox({
			valueField:'value',
			textField:'name',
			url:'${pageContext.servletContext.contextPath}/orders/getPlatform'
		});
		
		$("#add_goods_combobox,#edit_goods_combobox").combobox({
			valueField:'gid',
			textField:'goods_name',
			url:'${pageContext.servletContext.contextPath}/goods/getGoods'
		});
		
		 //打开添加的dialog
		$("#add_orders_btn").click(function(){
			$('#add_order_dialog').dialog('open');
		});
		 
		$("#delete_orders_btn").click(function(){
			var rows = $("#orders_content_datagrid").datagrid("getSelections");  //数组，对应选中的Json数据
			if(rows.length > 0){
				$.messager.confirm("提示", "确定删除选中的数据？", function(r){
					if(r){  //用户点击确定
						var ids = [];  //存放id的数组
						for(var i = 0; i < rows.length; i++){
							ids.push(rows[i].order_id);  //将id放入数组
						}
						var idsStr = ids.join(","); // join(str) 返回字符串,用指定的字符串拼接起来   1275,1234,4456,
						$.post(  //jquery的ajax
	        				"${pageContext.servletContext.contextPath}/orders/delete",
	        				{ids:idsStr},   //传递到后台的数据
	        				function(_data){
	        					if(_data.success){  //校验成功
	        						$("#orders_content_datagrid").datagrid("load");
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
		
		$("#edit_orders_btn").click(function(){
			var rows = $("#orders_content_datagrid").datagrid("getSelections");
			if(rows.length == 1){
				$('#eidt_order_dialog').dialog('open');
				$("#eidt_order_form").form('load', rows[0]);  //讲json数据，按照form表单中的名字，对应赋值
			}else{
				$.messager.alert("警告", "请选择一条数据.", "warn");
			}
		});
	});
</script>

<div class="easyui-layout" data-options="border:false, fit:true">
	<div data-options="region:'north',border:false" style="height:100px;">
		<form id="orders_search_form">
			<div>
				<span style="padding-left: 10px;">平台：</span><select id="list_platfrom_combobox" name="platform" style="width:150px;"></select>
				<span style="padding-left: 10px;">帐户名：</span> <input type="text" class="easyui-textbox" name="account_name" style="width:150px;"></input>
				<span style="padding-left: 10px;">支付方式：</span> <select class="easyui-combobox" name="pay_type" style="width:150px;">
								<option value=""></option>
								<option value="wx">微信</option>
								<option value="Alipay">支付宝</option>
							</select>
			</div>
			<div>
				<span style="padding-left: 10px;">创建时间：</span><input type="text" class="easyui-datetimebox" name="beginTime" data-options="editable:false"/> - 
						<input type="text" class="easyui-datetimebox" name="endTime" data-options="editable:false"/>
			</div>
			<div>
				<a class="easyui-linkbutton" id="search_orders_btn" data-options="iconCls:'icon-search'">搜索</a>
				<a class="easyui-linkbutton" id="clear_search_btn" data-options="iconCls:'icon-clear'">清空</a>
			</div>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<!-- striped:隔行换色, paginatioin:true 是否分页 -->
		<table id="orders_content_datagrid" class="easyui-datagrid" 
			data-options="border:false, 
						  striped:true, 
						  fitColumns:true,
						  collapsible: true,
						  rownumbers: true,
						  url:'${pageContext.servletContext.contextPath}/orders/list', 
						  pagination:true,
						  toolbar: '#orders_datagrid_toolbar',
						">
						
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'order_id',width:'8%'">订单编号</th>
					<th data-options="field:'account_name',width:'10%'">账户名</th>
					<th data-options="field:'platform',width:'10%'">平台</th>
					<th data-options="field:'goods_name',width:'12%'">商品名称</th>
					<th data-options="field:'pay_type',width:'12%'">支付方式</th>
					<th data-options="field:'goods_price',width:'8%'">价格</th>
					<th data-options="field:'amount',width:'12%'">购买数量</th>
					<th data-options="field:'totalPrice',width:'8%'">总价格</th>
					<th data-options="field:'create_time',width:'20%'">创建时间</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<div id="orders_datagrid_toolbar">
	<a href="#" class="easyui-linkbutton" id="add_orders_btn" data-options="iconCls:'icon-add',plain:true">添加</a>
	<a href="#" class="easyui-linkbutton" id="edit_orders_btn" data-options="iconCls:'icon-edit',plain:true">编辑</a>
	<a href="#" class="easyui-linkbutton" id="delete_orders_btn" data-options="iconCls:'icon-remove',plain:true">删除</a>
</div>
<jsp:include page="ordersAdd.jsp"></jsp:include>
<jsp:include page="ordersEdit.jsp"></jsp:include>
