$(document).ready(function () {
  var table = $('#dataTable').DataTable();
  var savedSearchValue = localStorage.getItem('searchValue');
  if (savedSearchValue !== null) {
    table.search(savedSearchValue).draw();
  }

  // Add event listener for the date range filter
  $('#startDate, #endDate').on('change', function () {
    var startDate = $('#startDate').val();
    var endDate = $('#endDate').val();

    // Save the filter values in local storage
    var filterValues = {
      startDate: startDate,
      endDate: endDate
    };
    localStorage.setItem('filterValues', JSON.stringify(filterValues));

    // Perform date range filtering
    table.draw();
  });

  // Add custom date range filtering function
  $.fn.dataTable.ext.search.push(
    function (settings, data, dataIndex) {
      var startDate = $('#startDate').val();
      var endDate = $('#endDate').val();
      var currentDate = new Date(data[3]);

      if (startDate && endDate) {
        var start = new Date(startDate);
        var end = new Date(endDate);
        return (currentDate >= start && currentDate <= end);
      } else if (startDate && !endDate) {
        var start = new Date(startDate);
        return (currentDate >= start);
      } else if (!startDate && endDate) {
        var end = new Date(endDate);
        return (currentDate <= end);
      }
      return true;
    }
  );

  // Add event listener for the search box
  $('#dataTable_filter input').on('keyup', function () {
    var searchValue = $(this).val();
    if (searchValue === '') {
      localStorage.removeItem('searchValue');
    } else {
      localStorage.setItem('searchValue', searchValue);
    }
  });

  // Add event listener for the select filter
  $('#select-filter').on('change', function () {
    var filterValue = $(this).val();

    // Save the filter value in local storage
    localStorage.setItem('filterValue', filterValue);
    table.column(6).search(filterValue).draw();
  });

  // Retrieve and apply saved filter values on page load
  var savedFilterValues = localStorage.getItem('filterValues');
  if (savedFilterValues !== null) {
    savedFilterValues = JSON.parse(savedFilterValues);
    $('#startDate').val(savedFilterValues.startDate);
    $('#endDate').val(savedFilterValues.endDate);
    table.draw();
  }

  var savedFilterValue = localStorage.getItem('filterValue');
  if (savedFilterValue !== null) {
    $('#select-filter').val(savedFilterValue);
    table.column(6).search(savedFilterValue).draw();
  }
});

function check() {
  let text;
  if (confirm("Are you sure to change status this account") === false) {
    return false;
  } else {
    return true;
  }
}