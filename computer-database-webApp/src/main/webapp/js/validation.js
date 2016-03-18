$(document).ready(function() {

	jQuery.validator.addMethod("myDate", function(value, element) {
		if (value == null)
			return true;
		if (value == "")
			return true;
		if (value == "0000-00-00")
			return false;
		return /^\d\d\d\d?-\d\d-\d\d/.test(value);
	}, "Please specify the date in YYYY-MM-DD format");

	jQuery.validator.addMethod("dateBefore1970", function(value, element) {
		if (value == null)
			return true;
		if (value == "")
			return true;
		if (value == "0000-00-00")
			return false;
		return new Date(value) > new Date("1970-01-01");
	}, "you can't enter a date before 1970-01-02");

	jQuery.validator.addMethod("discVal", function(value, element, params) {
		if (value == null)
			return true;
		if (value == "")
			return true;
		if ($(params) == "")
			return true;
		return new Date(value) > new Date($(params).val());
	}, "discontinued Date must be after the introduced date");

	jQuery.validator.addMethod("myName", function(value, element) {
        var str = value.trim();
		if (str.length == 0)
			return false;
		return this.optional(element) || /^[a-zA-Z0-9\_\-\s]+$/.test(value);
	}, "Use a valid username");

	$("#formComputer").validate({
		rules : {
			computerName : {
				required : true,
				minlength : 2,
				myName : true,
			},
			introduced : {
				required : false,
				myDate : true,
				dateBefore1970 : true,
			},
			discontinued : {
				required : false,
				myDate : true,
				discVal : "#introduced",
				dateBefore1970 : true,
			}
		}
	})
});
