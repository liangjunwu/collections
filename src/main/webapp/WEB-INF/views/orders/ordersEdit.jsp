<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript">
	$(function(){
		$("#edit_order_save_btn").click(function(){
			$("#eidt_order_form").form({
				url:"${pageContext.servletContext.contextPath}/orders/edit",
				success: function(_data){  //
					var data = $.parseJSON(_data);  //使用easyui的的form返回的json数据要调用该方法解析
					if(data.success){ //成功
						$('#eidt_order_dialog').dialog('close'); //关闭对话框
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
    
<div class="easyui-dialog" id="eidt_order_dialog" style="width:500px; height:200px; padding: 15px;" 
		data-options="modal:true, closed:true, title:'编辑订单', buttons:'#edit_orders_buttons'" >
	<form id="eidt_order_form">
		<table>
			<tr>
				<th>账户名</th>
				<td>
					<input type="text" class="easyui-textbox" name="account_name" data-options="readonly:true"/>
				</td>
				<th>平台</th>
				<td>	
					<input type="text" class="easyui-textbox" name="platform" data-options="readonly:true"/>
				</td>
			</tr>
			<tr>
				<th>商品</th>
				<td>
					<select id="edit_goods_combobox" name="gid" data-options="required:true" style="width:172px;">
					</select>
				</td>
				<th>数量</th>
				<td>
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
				<th></th>
				<td>
					<input type="hidden" class="easyui-numberbox" name="order_id" data-options="required:true"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="edit_orders_buttons">
	<a class="easyui-linkbutton" id="edit_order_save_btn" data-options="iconCls:'icon-save'">保存</a>
	<a class="easyui-linkbutton" id="edit_order_save_close_btn"
	 data-options="iconCls:'icon-cancel'" onclick="$('#eidt_order_dialog').dialog('close')">关闭</a>
</div>
