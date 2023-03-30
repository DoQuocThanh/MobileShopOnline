$(document).ready(function () {
    var table = $('#dataTable').DataTable({
        "columnDefs": [{
                "targets": [0, 2, 3], // Replace with an array of indices for columns to disable searching
                "searchable": false
            }]
    });

    var savedSearchValue = localStorage.getItem('searchValueCus');
    if (savedSearchValue !== null) {
        table.search(savedSearchValue).draw();
    }


    $('#dataTable_filter input').on('keyup', function () {
        var searchValue = $(this).val();
        if (searchValue === '') {
            localStorage.removeItem('searchValueCus');
        } else {
            localStorage.setItem('searchValueCus', searchValue);
        }
    });

    // Add event listener for the select filter
    $('#select-filter').on('change', function () {
        var filterValue = $(this).val();

        // Save the filter value in local storage
        localStorage.setItem('filterValueCus', filterValue);

        table.column(4).search(filterValue).draw();
    });
    var savedFilterValue = localStorage.getItem('filterValueCus');
    if (savedFilterValue !== null) {
    $('#select-filter').val(savedFilterValue);
    table.column(4).search(savedFilterValue).draw();
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