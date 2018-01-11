<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$("#add_order_save_btn").click(function(){
			$("#add_order_form").form({
				url:"${pageContext.servletContext.contextPath}/orders/add",
				success: function(_data){  //
					var data = $.parseJSON(_data);  //使用easyui的的form返回的json数据要调用该方法解析
					if(data.success){ //成功
						$('#add_order_dialog').dialog('close'); //关闭对话框
						$("#orders_content_datagrid").datagrid("load");
						$.messager.show({
							title:"系统信息",  //
							timeout:2000,  //持续时间
							content:data.obj
						});
					}else{
						$.messager.show({
							title:"系统信息",  //
							timeout:4000,  //持续时间
							content:data.obj
						});
					}
				}
			}).submit();
		});
	});	
</script>
<!-- closed:true; 首次进入该页面，该dialog是关闭状态
	 buttons 给dialog添加一组按钮
	 modal: 模态化，添加遮罩层。
  -->
<div class="easyui-dialog" id="add_order_dialog" style="width:500px; height:200px; padding: 15px;" 
		data-options="modal:true, closed:true, title:'添加订单', buttons:'#add_orders_buttons'" >
	<form id="add_order_form">
		<table>
			<tr>
				<th>账户名</th>
				<td>
					<!-- required:true 表示必填
						 validType:'length[3, 6]'表示长度介于3-6之间
					 -->
					<input type="text" class="easyui-textbox" name="account_name" data-options="required:true, validType:'length[3, 6]'"/>
				</td>
				<th>平台</th>
				<td>
					<select name="platform" id="add_platfrom_combobox" data-options="required:true" style="width:172px;">
					</select>
				</td>
			</tr>
			<tr>
				<th>商品</th>
				<td>
					<select id="add_goods_combobox" name="gid" data-options="required:true" style="width:172px;">
					</select>
				</td>
				<th>数量</th>
				<td>
					<!-- min: 最小值
						 max: 最大值
					 -->
					<input type="text" class="easyui-numberbox" name="amount" data-options="required:true,min:1,max:1000"/>
				</td>
			</tr>
			<tr>
				<th>支付方式</th>
				<td>
					<select class="easyui-combobox" name="pay_type" data-options="required:true" style="width:172px;">
						<option value=""></option>
						<option value="wx">微信</option>
						<option value="Alipay">支付宝</option>
					</select>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="add_orders_buttons">
	<a class="easyui-linkbutton" id="add_order_save_btn" data-options="iconCls:'icon-save'">保存</a>
	<a class="easyui-linkbutton" id="add_order_save_close_btn"
	 data-options="iconCls:'icon-cancel'" onclick="$('#add_order_dialog').dialog('close')">关闭</a>
</div>