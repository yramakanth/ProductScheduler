<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" language="javascript"
	src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
<!-- <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script> -->
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
<script
	src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Scheduler</title>
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


/* function dynaAddProds()
{
	if(i<=10)
		{
		i++;
		var tdElement = document.createElement('td');
		tdElement.innerHTML='Child:<input type="text" id="child+'+i+'">
		
		}
	
	}
 */

function addProductsCall(){
	var prodID=$('#prodID').val();
	var compTime=$("#compTime").val();
	
	if(compTime==""||prodID=="")
		{
		alert("Please enter all fields ");
		}
	else {
 $.ajax({
  type: "GET",
  url: "employee/add/",
  data:"id="+$('#prodID').val()+ "&compTime=" + $("#compTime").val(),
  success: function(result){
	  alert("Record Added Succesfully.Click Ok to continue");
	  $('#prodID').val('');
	  $("#compTime").val('');
  },
  error: function(){      
   alert('Error while request..');
  }
 });
}
}
</script>
</head>
<body>
	<form id="myform">
	<h1 align="center">Product Scheduler</h1>
		<ul>
			<li>
				<ul>
					<li><a href="ProductsList.jsp">Products Full List</a></li>
					<li><a href="ProcessedProducts.jsp">Processed List</a></li>
				</ul>
			</li>
		</ul>
		<div>
			<table align="center" cellpadding="0" cellspacing="0" border="1"
				class="myGrid" id="addProducts">
				<tr>
					<td><label for="name" generated="true" class="error"
						style="display: inline;">Product Name</label></td>
					<td><input type="text" id="prodID" value="" class="required"></td>
					<!-- <td colspan="2" align="center"><input type="button"
						value="Add" onclick="dynaAddProds();"></td>
					 -->
				</tr>
				<tr>
					<td>Compilation Time (in MIN)</td>
					<td><input type="number" id="compTime" value=""
						class="required"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button"
						value="Submit" onclick="addProductsCall();"></td>
				</tr>
				
			</table>
		</div>
	</form>
</body>
</html>