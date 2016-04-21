$(document).ready(function() {

	jQuery.validator.addMethod("dateFormat", function(value, element) {
		if (value == null)
			return true;
		if (value == "")
			return true;
		if (value == "0000-00-00")
			return false;
		return /^\d\d\d\d?-\d\d-\d\d/.test(value);
	});

	jQuery.validator.addMethod("dateBefore1970", function(value, element) {
		if (value == null)
			return true;
		if (value == "")
			return true;
		if (value == "0000-00-00")
			return false;
		return new Date(value) > new Date("1970-01-01");
	});

	jQuery.validator.addMethod("discVal", function(value, element, params) {
		if ($(params).val() == "")
			return true;
		if ($(params).val() == null)
			return true;
		if (value == null)
			return true;
		if (value == "")
			return true;
		return new Date(value) > new Date($(params).val());
	});

	jQuery.validator.addMethod("myName", function(value, element) {
        var str = value.trim();
		if (str.length == 0)
			return false;
		return this.optional(element) || /^[a-zA-Z0-9\_\-\.\+\s]+$/.test(value);
	});

	$("#formComputer").validate({
		rules : {
			name : {
				required : true,
				minlength : 2,
				myName : true,
			},
			introduced : {
				required : false,
				dateFormat : true,
				dateBefore1970 : true,
			},
			discontinued : {
				required : false,
				dateFormat : true,
				discVal : "#introduced",
				dateBefore1970 : true,
			}
		},
		messages: {
			name : {
				myName : translation['error.name'],
				required : translation['error.required']
			},
			introduced : {
				dateFormat : translation['error.dateFormat'],
				dateBefore1970 : translation['error.dateBefore1970']
			},
			discontinued : {
				dateFormat : translation['error.dateFormat'],
				dateBefore1970 : translation['error.dateBefore1970'],
				discVal : translation['error.dateBefore']
			}
		}
	});
});
