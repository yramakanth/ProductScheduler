<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
<script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products List</title>
<style>
Table.myGrid {
	padding: 3px;
	margin: 58;
	background: bisque;
	border-collapse: collapse;
	width: 35%;
	align:center;
}
</style>
<script type="text/javascript">
function productsListCall(){
 $.ajax({
  type: "GET",
  dataType: 'json',
  url: "employee/fullData/",
  
  success: function(result){
	  var trHTML = '';
	 $.each(result, function (index, element) { 
	        trHTML += '<tr><td>' + element.product + '</td><td>' +element.completationTime+ '</td><td>'+element.isProcessed+ '</td>' ;
	 }); 
	  $('#productsTable').append(trHTML);
  },
  error: function(){      
   alert('Error while request..');
  }
 });
}
</script>
</head>
<body onload = productsListCall()>
 <div id="result">
 <h1 align="center"> Total Products Available</h1>
 <table align="center" id="productsTable" border='1' class="myGrid">
    <tr>
        <th>Product Name</th>
        <th>Compilation Time (in MIN)</th>
        <th>Status</th>
    </tr>
</table>
 
 </div>
</body>
</html>