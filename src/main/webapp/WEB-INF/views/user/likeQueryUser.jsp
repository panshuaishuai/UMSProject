<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<base href="<%=basePath%>">
<%@taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="./plugins/jquery_2.1.3.js"></script>
<script type="text/javascript" src="./js/queryUser.js"></script>

  <link rel="stylesheet" type="text/css" href="iconfont/icon-css/iconfont.css">
  <link rel="stylesheet" type="text/css" href="./css/userCss/queryUser.css">
<body>
<table class="table table-stripe table-hover">
		<thead>
			<tr>
				<th><input type="checkbox" name="user">全选</th>
				<th>姓名</th>
				<th>性别</th>
				<th>账号</th>
				<th>电话</th>
				<th>状态</th>
				<th>部门</th>
				<th>角色</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${listUser}" varStatus="status">
				<tr class="user-remove-id" data-id="${user.id }">
					<td><input type="checkbox" name="usercheck" data-id="${user.id }">${user.id }</td>
					<td>${user.name}</td>
					<td>${user.sex}</td>
					<td>${user.username}</td>
					<td>${user.phone}</td>
					<td>${user.status}</td>
					<td>${user.department.name}</td>
					<td>${user.role.name}</td>
					<td>${user.createDate }</td>
					<td><button class="user-update" data-id="${user.id }">编辑</button>
						<button class="user-remove" data-id="${user.id }">删除</button></td>
				</tr>
			</c:forEach>

		</tbody>
		<%-- <div class="page-parent">
		<div class="page-content">
			共${pages.totalPage }页&nbsp;&nbsp;当前是第${pages.pageNow }页
			<c:if test="${pages.hasFirst }">
				<a class="firstPages" href="javascript:;" target="main">首页</a>
			</c:if>
			<c:if test="${pages.hasPre }">
				<a href="javascript:;" class="pageNows">上一页</a>
			</c:if>
			
			<c:if test="${pages.hasNext }">
				<a href="javascript:;" class="pagenexts">下一页</a>
			</c:if>
			<c:if test="${pages.hasLast }">
				<a href="javascript:;" class="lastpages"> 尾页</a>
			</c:if>
		</div>
	</div> --%>
	</table>
	
	<%-- <script type="text/javascript">
		$(function() {
			$(".firstPages").on("click", function() {
				$("#like-panel").load("queryLikeUser?pageNow=1");
			})
			$(".pageNows").on("click", function() {
				$("#like-panel").load("queryLikeUser?pageNow=${pages.pageNow-1}");
			})
			$(".pagenexts").on("click", function() {
				$("#like-panel").load("queryLikeUser?pageNow=${pages.pageNow+1}");
			})
			$(".lastpages").on("click", function() {
				$("#like-panel").load("queryLikeUser?pageNow=${pages.totalPage}");
			})
		});
	</script> --%>
</body>