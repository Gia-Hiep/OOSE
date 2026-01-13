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


<script>
function isValidYmd(v) {
  if (v.length !== 10) return false;
  if (v.charAt(4) !== '/' || v.charAt(7) !== '/') return false;

  return true;
}


  function validateSearch() {
    var from = document.getElementById("txtBirthdayFrom").value.trim();
    var to   = document.getElementById("txtBirthdayTo").value.trim();

    // Format check
    if (from !== "" && !isValidYmd(from)) {
      alert("Invalid Birthday (From).");
      return false;
    }

    if (to !== "" && !isValidYmd(to)) {
      alert("Invalid Birthday (To).");
      return false;
    }

    // Range check (string compare là OK vì YYYY/MM/DD)
    if (from !== "" && to !== "" && to < from) {
      alert("There is an error in the range input of Birthday");
      return false;
    }

    return true;
  }
</script>

