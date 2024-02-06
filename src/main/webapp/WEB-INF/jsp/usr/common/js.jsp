
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	var historyBack = '${historyBack} == true';
	var msg = '${msg}'.trim();

	if (msg) {
		alert(msg);
	}
<<<<<<< HEAD
=======

>>>>>>> 855fc422b26ed22be758632ff22bcbd60d25258f
	if (historyBack) {
		history.back();
	}
</script>