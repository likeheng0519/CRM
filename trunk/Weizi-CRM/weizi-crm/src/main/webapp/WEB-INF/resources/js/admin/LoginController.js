$(function () {

    var _defaultConfig = {
        userNameID: "#username",
        passwordID: "#password",
        errorID: "#hide",
        ERROR_MSGS: {}

    };
    $.LoginController = function (elm, config) {
        // if this contructor wasn't newed, then new it...
        if (this === window) {
            return new $.LoginController(elm, config || {});
        }
        // store the basics
        this.item = $(elm);
        this.config = new $.LoginController.Config(config);

        // don't rerun the plugin if it is already present
        if (this.item.data('loginController')) {
            return;
        }
        // register this controlset with the element
        this.item.data('loginController', this);
    }

    $.extend($.LoginController, {
        login: function (config) {
            var username = $(config.userNameID).val();
            var password = $(config.passwordID).val();
            var ctx = $("#ctx").val();
            $(".inputwrapper").removeClass("has-error");
            $(config.errorID).addClass("hide");
            if (username.length == 0) {
                $(config.errorID).html(config.ERROR_MSGS['login.error.account.required']);
                $(config.userNameID).focus();
                $(config.userNameID).parent().addClass("has-error");
                $(config.userNameID).addClass("has-error");
                $(config.passwordID).parent().addClass("has-error");
                $(config.errorID).removeClass("hide");
                return false;
            }

            if (password.length == 0||password.length > 12||password.length < 6) {
                $(config.errorID).html(config.ERROR_MSGS['login.error.password.required']);
                $(config.passwordID).focus();
                $(config.passwordID).parent().addClass("has-error");
                $(config.passwordID).addClass("has-error");
                $(config.errorID).removeClass("hide");
                return false;
            }

            var rememberMe=$("#remember").is(':checked');

            $.ajax({
                type: "POST",
                url: '/user/login',
                cache: false,
                data: {
                    'username': username,
                    'password': password,
                    'rememberMe': rememberMe,
                    'ctx':ctx
                },
                success: function (data) {
                    window.location.href = data.redirecturl + "?_=" + (new Date()).valueOf();
                },
                error: function (xhr, textStatus, errorThrown) {
                    var response = jQuery.parseJSON(xhr.responseText);
                    var message = response.message;
                    var lowerCaseMessage = message.toLowerCase();
                    if (lowerCaseMessage.indexOf("password") >= 0) {
                        $(config.passwordID).focus();
                        $(config.passwordID).parent().addClass("has-error");
                        $(config.passwordID).addClass("has-error");
                    } else {
                        $(config.userNameID).focus();
                        $(config.userNameID).parent().addClass("has-error");
                        $(config.userNameID).addClass("has-error");
                    }
                    $(config.passwordID).parent().addClass("has-error");
                    $(config.passwordID).val("");
                    $(config.errorID).html(config.ERROR_MSGS[response.message]);
                    $(config.errorID).removeClass("hide");
                }
            })
        },

        logout:function(){
            var ctx = $("#ctx").val();
            $.ajax({
                type: "POST",
                url: '/user/logout',
                cache: false,
                success: function (data) {

                    window.location.href = "/"+ctx+"/login?_=" + (new Date()).valueOf();
                }
            })
        }
    });
});