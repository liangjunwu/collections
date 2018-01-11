<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			</tr>
		</table>
	</form>
</div>
<div id="edit_orders_buttons">
	<a class="easyui-linkbutton" id="edit_order_save_btn" data-options="iconCls:'icon-save'">保存</a>
	<a class="easyui-linkbutton" id="edit_order_save_close_btn"
	 data-options="iconCls:'icon-cancel'" onclick="$('#eidt_order_dialog').dialog('close')">关闭</a>
</div>
