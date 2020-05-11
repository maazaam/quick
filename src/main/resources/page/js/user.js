$(function() {
	function showData(data) {
		$.each(data, function(key, val) {
			$("#data input[name='" + key + "']").val(val);
		});
	}
	function showList(data) {
		var html = "";
		$.each(data, function(key, val) {
			html += "<tr>";
			html += "<td>" + val.id + "</td>";
			html += "<td>" + val.name + "</td>";
			html += "<td>" + val.status + "</td>";
			html += "<td><button id='find' type='button' data-id='" + val.id + "'>find</button></td>";
			html += "<td><button id='drop' type='button' data-id='" + val.id + "'>drop</button></td>";
			html += "</tr>";
		});
		$("#list tbody").empty().append(html);
	}
	function listUser() {
		$.get("/quick/user/list", showList);
	}
	function findUser(id) {
		$.get("/quick/user/find/" + id, showData);
	}
	function dropUser(id) {
		$.get("/quick/user/drop/" + id, listUser);
	}
	function saveUser(user) {
		$.post("/quick/user/save", user, listUser);
	}
	listUser();
	$("#list").on("click", "#find", function() {
		var id = $(this).data("id");
		findUser(id);
	});
	$("#list").on("click", "#drop", function() {
		var id = $(this).data("id");
		dropUser(id);
	});
	$("#data").on("click", "#save", function() {
		var user = {};
		$.each($("#data form").serializeArray(), function(idx, ele) {
			if (ele.value) {
				user[ele.name] = ele.value;
			}
		});
		saveUser(JSON.stringify(user));
	});
});
