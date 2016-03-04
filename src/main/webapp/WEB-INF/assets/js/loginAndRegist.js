(function ($) {
    "use strict";

    // Options for Message
    //----------------------------------------------
    var options = {
        'btn-loading': '<i class="fa fa-spinner fa-pulse"></i>',
        'btn-success': '<i class="fa fa-check"></i>',
        'btn-error': '<i class="fa fa-remove"></i>',
        'msg-success': 'All Good! Redirecting...',
        'msg-error': 'Wrong login credentials!',
        'useAJAX': true,
    };

    // Register Form
    //----------------------------------------------
    // Validation
    $("#register-form").validate({
        rules: {
            password: {
                required: true,
                minlength: 5
            },
            password_confirm: {
                required: true,
                minlength: 5,
                equalTo: "#register-form [name=password]"
            },
            email: {
                required: true,
                email: true
            }
        },
        errorClass: "form-invalid"
    });

    // Form Submission
    $("#register-form").submit(function (ev) {
        remove_loading($(this));

        //email 유효성 검사
        var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
        if (regex.test($("input[name=email]").val()) === false) {
            alert("email 형식으로 입력해주세요.");
            return false;
        }

        if ($("input[name=password]").val() != $("input[name=password_confirm]").val()) {
            alert("패스워드가 일치하지 않습니다.");
            return false;
        }


        var user = {
            identity: $("input[name=email]").val(),
            roles: "ROLE_USER",
            email: $("input[name=email]").val(),
            password: $("input[name=password]").val(),
            name: $("input[name=name]").val(),
            is_oauth: "F"
        };

        $.ajax({
            url: '/users',
            type: 'post',
            data: user,
            dataType: 'json',
            success: function (responsedData) {
                //회원 등록이 성공한다면, responsedData JSON 변수 첫번째 값으로 success가 들어가고, 실패하면 에러메시지가 들어감.
                if (responsedData[0] === "success") {
                    window.location.replace("/signIn");
                } else {
                    alert(responsedData);
                }
            },
            error: function (data) {
                alert(data);
            }
        });


        return false;

    });


    // Loading
    //----------------------------------------------
    function remove_loading($form) {
        $form.find('[type=submit]').removeClass('error success');
        $form.find('.login-form-main-message').removeClass('show error success').html('');
    }

    function form_loading($form) {
        $form.find('[type=submit]').addClass('clicked').html(options['btn-loading']);
    }

    function form_success($form) {
        $form.find('[type=submit]').addClass('success').html(options['btn-success']);
        $form.find('.login-form-main-message').addClass('show success').html(options['msg-success']);
    }

    function form_failed($form) {
        $form.find('[type=submit]').addClass('error').html(options['btn-error']);
        $form.find('.login-form-main-message').addClass('show error').html(options['msg-error']);
    }


})(jQuery);