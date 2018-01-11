<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<<script type="text/javascript">
$(function(){
	$("#edit_goods_save_btn").click(function(){
		$("#eidt_goods_form").submit();
	})
	
	$('#eidt_goods_form').form({
	    url:'${pageContext.servletContext.contextPath}/goods/edit',
	    onSubmit: function(){
	    },
	    success:function(_data){
	    	var data = $.parseJSON(_data);  //使用easyui的的form返回的json数据要调用该方法解析
			if(data.success){ //成功
				$('#eidt_goods_dialog').dialog('close'); //关闭对话框
				$("#goods_content_datagrid").datagrid("load");
				$.messager.show({
					title:"系统信息",  //
					timeout:3000,  //持续时间
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
	});
})

</script>

<div class="easyui-dialog" id="eidt_goods_dialog" style="width:500px; height:200px; padding: 15px;" 
		data-options="modal:true, closed:true, title:'编辑商品', buttons:'#edit_goods_buttons'" >
	<form id="eidt_goods_form">
		<table>
			<tr>
				<th>商品编号</th>
				<td>
					<!-- required:true 表示必填
					 -->
					<input type="text" class="easyui-textbox" name="gid" data-options="required:true,readonly:true"/>
				</td>
				<th>商品名称</th>
				<td>
					<input type="text" class="easyui-textbox" name="goods_name" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>商品价格</th>
				<td>
					<input type="text" class="easyui-textbox" name="goods_price" data-options="required:true"/>
				</td>
				
			</tr>
		</table>
	</form>
</div>
<div id="edit_goods_buttons">
	<a class="easyui-linkbutton" id="edit_goods_save_btn" data-options="iconCls:'icon-save'">保存</a>
	<a class="easyui-linkbutton" id="edit_goods_save_close_btn"
	 data-options="iconCls:'icon-cancel'" onclick="$('#eidt_goods_dialog').dialog('close')">关闭</a>
</div>
