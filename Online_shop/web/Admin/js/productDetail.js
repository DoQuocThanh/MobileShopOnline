$(document).ready(function () {
    $('#fileInput').on('change', function () {
        var formData = new FormData($(this).parents('form')[0]);
        $.ajax({
            url: "UploadServlet",
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: function (response) {
                $('#productPicture').attr('src', '../img/' + response);
                console.log(response);
                $('#srcProductDetail').attr('value', response);

            },
            error: function (xhr, status, error) {
                console.error(error);
            }
        });
    });
});
