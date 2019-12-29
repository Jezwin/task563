const getDbData = () => {
$.ajax({
		type: "GET",
		url: "/bin/task",
        dataType: "json",
        success: function(resp) {
           for(var key in resp){

				var obj= resp[key];

               tableCreator(obj);
               tableStatusChecker();

			}
        }});
}

$(function (){
getDbData();
});



const tableCreator = obj => {
$( "#formBody" ).append( "<tr class=row"+obj['formId']+" id=#"+obj['formId']+" contenteditable>"
                                           		+"<td id='#firstName' class='firstName'>"+obj['firstName']+"</td>"
                                           		+"<td id='#lastName' >"+obj['lastName']+"</td>"
												+"<td id='#birthMonth' >"+obj['dob']+"</td>"
												+"<td id='#phoneNumber' >"+obj['phoneNumber']+"</td>"
                                           		+"<td id='#zipCode' >"+obj['zipCode']+"</td>"
												+"<td id='#email' >"+obj['email']+"</td>"
                                           		+"<td id='#radioValue' >"+obj['radioValue']+"</td>"
                                            	+"<td id='#status'>"
                                            		+"<select class=status"+obj['formId']+">"
                                            			+"<option value='fromdb' selected disabled hidden>"+obj['status']+"</option>"
          												+"<option value='inprogress'>In Progress</option>"
          												+"<option value='completed'>Completed</option>"
       												 +"</select>"

                                            	+"</td>"
                                            	+"<td id='#formid' hidden>"+obj['formId']+"</td>"
                                            	+"<td><button class='export-btn "+obj['formId']+"' id=#"+obj['formId']+">Update</button></td>"
                                           	 +"</tr>");

}



    const tableStatusChecker = () => {
     var $TABLE = $('#formTable');
var $rows = $TABLE.find('tr:not(:first)');
    $rows.each(function () {
    var id = $(this).attr('id');
    if($(".status"+id.substr(1)+" option:selected").text()=="Completed"){
		$('tr.row'+id.substr(1)).removeAttr("contenteditable");
    $('button.'+id.substr(1)).remove();
}


  });
}




// A few jQuery helpers for exporting only
jQuery.fn.pop = [].pop;
jQuery.fn.shift = [].shift;


$(document).on("click","#formTable tbody tr td button.export-btn", function() {
    var $TABLE = $('#formTable');
var id = $(this).attr('id');
   var $rows = $TABLE.find('tr.row'+id.substr(1));

    var $rowsh = $TABLE.find('tr');


  var headers = [];
  var data = [];

  // Get the headers (add special header logic here)
  $($rowsh.shift()).find('th').each(function () {
    headers.push($(this).text().toLowerCase().replace(/ /g,''));
  });



  // Turn all existing rows into a loopable array
  $rows.each(function () {
    var $td = $(this).find('td');


    var h = {};
    
    // Use the headers from earlier to name our hash keys
    headers.forEach(function (header, i) {
      h[header] = $td.eq(i).text();
    });

     h['status'] = $(".status"+id.substr(1)+" option:selected").text();

    data.push(h);

  });
  
  // Output the result
   console.log(data);

    $.ajax({
		type: "GET",
		url: "/bin/taskpost",
        data: data[0],
        dataType: "json",
        success: function(resp) {
			console.log(resp);
        }
    });
});










