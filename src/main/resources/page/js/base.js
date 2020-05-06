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
	$("#action").on("click", "#setup", setupBase);
	$("#action").on("click", "#sample", sampleBase);
	$("#action").on("click", "#teardown", teardownBase);
});
