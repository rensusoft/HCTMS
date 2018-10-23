    

    function datepickerTime(timeFrom,timeTo){

        $('#' + timeFrom).datepicker({
		    dateFormat : 'yy-mm-dd',
	        dayNamesMin : ['日','一','二','三','四','五','六'],
		    monthNames : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		    monthNamesShort : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		    showOn : 'button',
		    buttonImage : '../../../img/calendar.gif',
		    buttonImageOnly : true,
	        changeMonth: true,
		    changeYear: true,
	        onClose: function( selectedDate ) {
	        $('#' + timeTo).datepicker( 'option', 'minDate', selectedDate );
	      }
	    });
	    $('#' + timeTo).datepicker({
		    dateFormat : 'yy-mm-dd',
		    dayNamesMin : ['日','一','二','三','四','五','六'],
		    monthNames : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		    monthNamesShort : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		    showOn : 'button',
		    buttonImage : '../../../img/calendar.gif',
		    buttonImageOnly : true,
	        changeMonth: true,
		    changeYear: true,
	        onClose: function( selectedDate ) {
	        $('#' + timeFrom).datepicker( 'option', 'maxDate', selectedDate );
	      }
	    });

    }



function dateSelect(time){
    $( '#' + time ).datepicker({
	  dateFormat : 'yy-mm-dd',
	  dayNamesMin : ['日','一','二','三','四','五','六'],
	  monthNames : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
	  monthNamesShort : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
	  showOn : 'button',
	  buttonImage : '../../../img/calendar.gif',
	  buttonImageOnly : true,
      changeMonth: true,
	  changeYear: true,
	  showButtonPanel: true,
	  closeText : '关闭',
	  currentText : '今天'
    });
}
    
