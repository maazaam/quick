$(function() {
	function setupBase() {
		$.get("/quick/base/setup");
	}
	function sampleBase() {
		$.get("/quick/base/sample");
	}
	function teardownBase() {
		$.get("/quick/base/teardown");
	}
	$("#link").on("click", "#setup", setupBase);
	$("#link").on("click", "#sample", sampleBase);
	$("#link").on("click", "#teardown", teardownBase);
});
