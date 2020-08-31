function showFilter() {
    document.body.style.overflow = "hidden";

    var curtain = document.getElementById("screenCurtain");
    curtain.style.display = "block";

    var dlg = document.getElementById("dlg-filter");
    dlg.style.display = "block";
}

function validateFilterDates() {
    try {
        var lblError = document.getElementById("err");
        lblError.style.display = "none";

        var startDate = document.forms["dlg-filter"]["startDate"].valueAsDate;
        var endDate = document.forms["dlg-filter"]["endDate"].valueAsDate;
        if (startDate != null && endDate != null && startDate > endDate) {
            showFilterErrorMessage("Error: Start Date is before End Date");
            return false;
        }

        return true;
    } catch (err) {
        showFilterErrorMessage(err);
        return false;
    }
}

function showFilterErrorMessage(message) {
    var lblError = document.getElementById("err");
    lblError.style.display = "block";
    lblError.innerHTML = message;

}

function cancelDialog(dlgName) {
    document.body.style.overflow = "auto";

    var curtain = document.getElementById("screenCurtain");
    curtain.style.display = "none";

    var dlg = document.getElementById(dlgName);
    dlg.style.display = "none";
}

function showDetails(stationName) {
    document.body.style.overflow = "hidden";

    var curtain = document.getElementById("screenCurtain");
    curtain.style.display = "block";

    var dlg = document.getElementById("dlg-details");
    dlg.style.display = "block";

    var lblStatioName = document.getElementById("detail-station");
    lblStatioName.textContent = stationName;

    var tblBody = document.getElementById("detail-readings");
    tblBody.innerHTML = '';

    $.ajax({
        url: "station/" + stationName,
        cache: false,
        dataType: "json",
        success: function (data) { addDataToTable(tblBody, data); }
    });
}

function addDataToTable(tblBody, data) {
    if (typeof data == null)
        return;

    var dataLength = data.length;
    for (var i = 0; i < dataLength; i++) {
        var rowData = data[i];
        var row = tblBody.insertRow();

        var cell = row.insertCell(0);
        cell.innerHTML = rowData.Province;
        var cell = row.insertCell(1);
        cell.className = "date";
        cell.innerHTML = rowData.FormattedDate;
        var cell = row.insertCell(2);
        cell.className = "number";
        cell.innerHTML = rowData.MeanTemp;
        var cell = row.insertCell(3);
        cell.className = "number extra-long";
        cell.innerHTML = rowData.HighestMonthlyTemp;
        var cell = row.insertCell(4);
        cell.className = "number extra-long";
        cell.innerHTML = rowData.LowestMonthlyTemp;
    }
}