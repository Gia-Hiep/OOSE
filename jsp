<%
boolean disFirst = request.getAttribute("disFirst") != null && (Boolean) request.getAttribute("disFirst");
boolean disPrev  = request.getAttribute("disPrev")  != null && (Boolean) request.getAttribute("disPrev");
boolean disNext  = request.getAttribute("disNext")  != null && (Boolean) request.getAttribute("disNext");
boolean disLast  = request.getAttribute("disLast")  != null && (Boolean) request.getAttribute("disLast");
%>

<div>
  <a id="btnFirst" href="#"
     <%= disFirst ? "style='pointer-events:none;color:gray;'" : "" %>>&lt;&lt;</a>
  &nbsp;
  <a id="btnPrevious" href="#"
     <%= disPrev ? "style='pointer-events:none;color:gray;'" : "" %>>&lt;</a>
  &nbsp;
  <a id="btnNext" href="#"
     <%= disNext ? "style='pointer-events:none;color:gray;'" : "" %>>&gt;</a>
  &nbsp;
  <a id="btnLast" href="#"
     <%= disLast ? "style='pointer-events:none;color:gray;'" : "" %>>&gt;&gt;</a>
</div>


<%
boolean disDelete = request.getAttribute("disDelete") != null && (Boolean) request.getAttribute("disDelete");
%>

<div>
  <button id="btnAddNew" type="button">Add New</button>
  <button id="btnDelete" type="button" <%= disDelete ? "disabled" : "" %>>Delete</button>
</div>
