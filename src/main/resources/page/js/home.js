$(function() {
	function baseHome() {
		window.location.href = "/quick/page/base.html";
	}
	function itemHome() {
		window.location.href = "/quick/page/item.html";
	}
	function userHome() {
		window.location.href = "/quick/page/user.html";
	}
	$("#link").on("click", "#base", baseHome);
	$("#link").on("click", "#item", itemHome);
	$("#link").on("click", "#user", userHome);
});
