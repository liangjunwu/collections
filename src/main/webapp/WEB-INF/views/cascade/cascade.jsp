<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function(){
		var province = $("#province");
		var city = $("#city");
		var district = $("#district");
		
		$.ajax({
			url:"${pageContext.servletContext.contextPath}/cascade/getProvince",  //请求的url地址
			async: "true",         //true 表示异步
			data: {method:"getProvinceList"},   //表示请求后端所携带的参数
			dataType: "json",       //dataType表示后端往前端传回来的数据类型
			type: "post",           //表示请求的方式
			success: function(_data){  //
				//[{"id":1,"name":"北京","orderid":0},{"id":2,"name":"天津","orderid":0}]
				for(var i = 0; i < _data.length; i++){
					province.append("<option value='" + _data[i].id + "'>" + _data[i].name + "</option>");
				}	
			}
		});
		
		province.change(function(){
			$("#city > option:gt(0)").remove();
			$("#district > option:gt(0)").remove();
			var provId = $(this).val(); //
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/cascade/getCity",  //请求的url地址
				async: "true",         //true 表示异步
				data: {"provId":provId, method:"getCity"},               //表示请求后端所携带的参数
				dataType: "json",       //dataType表示后端往前端传回来的数据类型
				type: "post",           //表示请求的方式
				success: function(_data){  //
					for(var i = 0; i < _data.length; i++){
						city.append("<option value='" + _data[i].id + "'>" + _data[i].name + "</option>");
					}	
				}
			});		
		});
	
	
		city.change(function(){
			$("#district > option:gt(0)").remove();
			var cityId = $(this).val();
			$.ajax({
				url:"${pageContext.servletContext.contextPath}/cascade/getDistrict",
				async:"true",
				data:{"cityId":cityId, method:"getDistrict"},
				dataType:"json",
				type:"post",
				success:function(_data){
					for(var i = 0; i < _data.length; i++){
						district.append("<option value ='" + _data[i].id + "'> " + _data[i].name + "</option>");
					}
				}
			});
		});
		
	});
	
</script>

<div class="easyui-layout" data-options="border:false, fit:true">
	<div data-options="region:'north',border:false" style="height:100px;">
		<form id="cascade_form">
			<div>
				省份：<select id="province">
						<option value="">请选择省份</option>
					  </select>&nbsp;
				城市: <select id="city">
						<option value="">请选择城市</option>
					  </select>&nbsp;
				地区: <select id="district">
						<option value="">请选择地区</option>
					  </select>
			</div>
			
		</form>
	</div>
	
</div>
