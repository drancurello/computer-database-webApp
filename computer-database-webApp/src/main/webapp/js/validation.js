$(document).ready(function() {
	
	$("#formComputer").validate({
		rules: {
			"computerName": {
				"required" : true,
				"minLength" : 2
			},
			"introduced": {
				"myDate" : true,
				"dateBefore1970" : true
			},
			"discontinued": {
				"myDate" : true,
				"discVal" : "#introduced",
				"dateBefore1970" : true
			}
		}
	}) 
});

jQuery.validator.addMethod("myDate", function(value, element) { 
	if (value == null) return true;
	if (value == "") return true;
	if (value == "0000-00-00") return false;
	return this.optional(element) || /^\d\d\d\d?-\d\d-\d\d/.test(value); 
	}, "Please specify the date in YYYY-MM-DD format");

jQuery.validator.addMethod("dateBefore1970", function(value, element) { 
	if (value == null) return true;
	if (value == "") return true;
	if (value == "0000-00-00") return false;
	return new Date(value) > new Date("1970-01-01");
	}, "you can't enter a date before 1970-01-02");


jQuery.validator.addMethod("discVal", function(value, element, params) {
	if (value == null) return true;
	if (value == "") return true;
    return new Date(value) > new Date($(params).val());
}, jQuery.validator.format("discontinued Date must be after the introduced date"));
