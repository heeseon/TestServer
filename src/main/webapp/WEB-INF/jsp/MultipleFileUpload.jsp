<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<script>


function AddMoreFile(tableID) {
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var col1 = row.insertCell(0);
    var colInput = document.createElement("input");
    colInput.type = "file";
    colInput.name="files";
    col1.appendChild(colInput);
}

</script>
 
 
<h1>Spring Multiple File Upload example</h1>
 
<form:form method="post" action="uploadMultipleFiles.do"  enctype="multipart/form-data">
 
    <p>Select files to upload. Click Add button to add more file.</p>
 
    <TABLE id="fuTable"  border="1">
    <TR>
        <TD> <input name="files" type="file"></TD>
    </TR>
    
    </TABLE>
    
    <br>   <input  type="button" value="Add More File"  onclick="AddMoreFile('fuTable')">
    <input type="submit" value="Upload">
</form:form>