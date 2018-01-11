<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="aa" class="easyui-accordion" style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">
	<div title="购物平台" style="overflow: auto; line-height: 28px; font-size: 15px;">
		<ul>
			<li>
				<a target="mainFrame" href="${pageContext.servletContext.contextPath}/orders/toList">订单管理</a>
			</li>
			<li>
				<a target="mainFrame" href="${ctx}/account/">帐号管理</a>
			</li>
			<li>
				<a target="mainFrame" href="${ctx}/goods/">商品管理</a>
			</li>
		</ul>
	</div>
	<div title="系统管理" style="overflow: auto; line-height: 28px; font-size: 15px;">
		<ul>
			<li>
				<a target="mainFrame" href="${ctx}/user/">后台帐号</a>
			</li>
			<li>
				<a target="mainFrame" href="${ctx}/role/">角色权限</a>
			</li>
		</ul>
	</div>
</div>
