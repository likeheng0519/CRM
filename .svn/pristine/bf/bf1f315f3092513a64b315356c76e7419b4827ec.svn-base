$(function () {

    $.CommonUpload = function () {

    }
    $.CommonUpload.initUpload = function (elem,uploadButtonTitle) {
        $(elem).fineUploader({
            request: {
                endpoint: '/upload'
            },
            multiple: false,
            validation: {
                allowedExtensions: ["jpg", "jpeg", "png", "bmp", "gif"],
                sizeLimit: 100000000
            },
            autoUpload: true,
            text: {
                uploadButton: '<span class="glyphicon glyphicon-cloud-upload">'+uploadButtonTitle+'</span>'
            }
        }).on('complete', function (event, id, name, responseJson) {
            $("#sp_thumbnail").attr("src", responseJson.full_path);
            $("#thumbnail_path").val(responseJson.relative_thumbnail_path);
            $("#thumbnail_path").attr("tempdir",responseJson.thumbnail_dir);
            $( elem + ' .qq-upload-success').hide();
        });
    }
    $.CommonUpload.csvUpload = function (element,uploadButtonTitle) {
        $(element).fineUploader({
            request: {
                endpoint: '/encash/batch/upload'
            },
            autoUpload: true,
            multiple: false,
            validation: {
                allowedExtensions: ['csv'],
                sizeLimit: 5242880 // 5 mb = 5 * 1024 * 1024 bytes
            },
            text: {
                uploadButton: '<button class="btn btn-success"><span class="glyphicon glyphicon-cloud-upload"></span> ' + uploadButtonTitle +' </button>'
            }
        }).on('complete', function(event, id, name, responseJson) {
            if(responseJson.success)
            {
                $('.qq-upload-status-text').html('Success');
            }else
            {
                $('.qq-upload-status-text').html('Upload Error');
            }

        });
    }

});