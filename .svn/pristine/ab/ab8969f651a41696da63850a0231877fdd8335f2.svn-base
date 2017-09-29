$(function () {
    $.CommonFn = function () {

    }

    $.CommonFn.DataTable = function (elm, config) {

    }

    $.extend($.CommonFn.DataTable, {
        fnSetKey: function (aoData, sKey, mValue) {
            for (var i = 0, iLen = aoData.length; i < iLen; i++) {
                if (aoData[i].name == sKey) {
                    aoData[i].value = mValue;
                }
            }
        },

        fnGetKey: function (aoData, sKey) {
            for (var i = 0, iLen = aoData.length; i < iLen; i++) {
                if (aoData[i].name == sKey) {
                    return aoData[i].value;
                }
            }
            return null;
        } ,
        getRowData: function (elem,datakey,selectedObj) {
            var dataTable = $(elem).data(datakey);
            dataTable.$('tr.row_selected').removeClass('row_selected');
            $(selectedObj).parent().parent().addClass('row_selected');
            var anSelected = dataTable.$('tr.row_selected');

            var row = dataTable.fnGetData(anSelected[0]);
            return row;

        }

    }),
    $.CommonFn.hideAllPopover = function () {
            $('.form-control,.btn').on('click', function (e) {
                $('.form-control').popover('hide');
            });
    };

    $.CommonFn.removeAllErrorSpan = function () {
        $('.error-area').remove();
        $('.error-main-area').remove();
    };

    $.CommonFn.insertErrorSpan = function (obj) {
        var html = '<span class="error-area hide" id="span_error"></span>';
        $(obj + ' .modal-header').append(html);
    };

    $.CommonFn.insertMainErrorSpan = function () {
        var html = '<span class="error-main-area hide" id="span_error"></span>';
        $("#main").after(html);
    };


    $.CommonFn.errorLoadImage = function (obj, context) {
        obj.onerror = null;
        obj.src = "/resources/images/default.png";
    };




})