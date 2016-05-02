$(document).ready(function() {
	var englishRegex = /^\d\d\d\d?-\d\d-\d\d/;
	var frenchRegex = /^\d\d?-\d\d-\d\d\d\d/;

	jQuery.validator.addMethod("dateFormat", function(value, element) {
		if (value == null)
			return true;
		if (value == "")
			return true;
		if (value == "0000-00-00" || value == "00-00-0000")
			return false;
		return englishRegex.test(value) || frenchRegex.test(value) != null;
	});

	jQuery.validator.addMethod("dateBefore1970", function(value, element) {
		if (value == null)
			return true;
		if (value == "")
			return true;
		if (value == "0000-00-00" || value == "00-00-0000")
			return false;
		if (frenchRegex.test(value)) {
			var values = value.split("-");
			value = values[2] + "-" + values[1] + "-" + values[0];
			console.log(value);
		}
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
		var introduced = $(params).val();
		if (frenchRegex.test(value)) {
			var values = value.split("-");
			value = values[2] + "-" + values[1] + "-" + values[0];
			console.log(value);
			var values = introduced.split("-");
			introduced = values[2] + "-" + values[1] + "-" + values[0];
			console.log(introduced);
		}
		return new Date(value) > new Date(introduced);
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



