$(function() {
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
	function showData(data) {
		$.each(data, function(key, val) {
			$("#data input[name='" + key + "']").val(val);
		});
	}
	function listItem() {
		$.get("/quick/item/list", showList);
	}
	function findItem(id) {
		$.get("/quick/item/find/" + id, showData);
	}
	function dropItem(id) {
		$.get("/quick/item/drop/" + id, listItem);
	}
	function saveItem(item) {
		$.post("/quick/item/save", item, listItem);
	}
	listItem();
	$("#list").on("click", "#find", function() {
		var id = $(this).data("id");
		findItem(id);
	});
	$("#list").on("click", "#drop", function() {
		var id = $(this).data("id");
		dropItem(id);
	});
	$("#data").on("click", "#save", function() {
		var item = {};
		$.each($("#data").serializeArray(), function(idx, ele) {
			if (ele.value) {
				item[ele.name] = ele.value;
			}
		});
		saveItem(JSON.stringify(item));
	});
});
