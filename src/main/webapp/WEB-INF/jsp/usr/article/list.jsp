<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${boardId == null }">
	<c:set var="pageTitle" value="ARTICLE LIST"></c:set>
</c:if>
<c:set var="pageTitle" value="#{board.code } ARTICLE LIST"></c:set>
<%@ include file="../common/head.jspf"%>
<section class="mt-8 text-xl px-4">
	<div class="mx-auto overflow-x-auto">
		<table class="table-box-1 table" border="1">
			<colgroup>
				<col style="width: 10%" />
				<col style="width: 20%" />
				<col style="width: 60%" />
				<col style="width: 10%" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>날짜</th>
					<th>제목</th>
					<th>작성자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articles }">
					<tr class="hover">
						<td>${article.id }</td>
						<td>${article.regDate.substring(0,10) }</td>
						<td><a href="detail?id=${article.id }">${article.title }</a></td>
						<td>${article.extra__writer }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="page">
			<c:if test="${page > 1}">
				<a href="list?<c:if test="${boardId != null}">boardId=${boardId }&</c:if>page=1">◀◀</a>
			</c:if>
			<c:forEach var="i" begin="${pageStartNum }" end="${pageEndNum}" step="1">
				<a href="list?<c:if test="${boardId != null}">boardId=${boardId }&</c:if>page=${i }">${i }</a>
			</c:forEach>
			<c:if test="${totalPage > page }">
				<a href="list?<c:if test="${boardId != null}">boardId=${boardId }&</c:if>page=${page+1}">▶▶</a>
			</c:if>
		</div>
	</div>
</section>



<%@ include file="../common/foot.jspf"%>