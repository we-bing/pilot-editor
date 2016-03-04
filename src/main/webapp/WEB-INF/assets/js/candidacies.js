(function ($) {
    $("#candidacy-keywords").tagit();
    $(".dropdown-menu").on('click', 'a', function () {
        var selectedCity = $(this).text();
        var cityCode = $(this).data('citycode')

        $(".selected-city").html(selectedCity + '<span class="caret"></span>');
        $(".selected-city").data('citycode', cityCode);

        console.log($(".selected-city").data('citycode'))
    });

    $('.show-candidacies-btn').on('click', function () {
        var cityCode = $(".selected-city").data('citycode');
        var districtName = $("#district-input").val();
        window.location.replace("/candidacies?cityCode=" + cityCode + "&districtName=" + districtName);
    });


    //admin post page에서 각 row를 클릭하면 해당 row의 정보를 가지고 모달창을 띄우는 로직
    $("#candidacy-table").on('click', 'tr', function () {
        // 이전에 클릭한 후보의 공약 정보 키워드 정보들을 삭제
        $(".pledge").remove();
        $("#candidacy-keywords").tagit("removeAll");

        var candidacyid = $(this).data('candidacyid');

        $.ajax({
            url: "/candidacies/" + candidacyid,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                $('#modal-candidacy-id').val(data['candidacy_id']);
                $('#modal-district-code').val(data['district_code']);
                $('#candidacy-name').val(data['name']);
                $('#candidacy-birth').val(data['birth']);
                $('#district-name').val(data['district_name']);
                $("input:radio[name=candidacyStatus]:input[value='" + data['candidacy_status'] + "']").attr("checked", true);
                $('#candidacy-modal').modal('toggle');
            }
        });

        $.ajax({
            url: "/candidacies/" + candidacyid + "/keywords",
            type: 'get',
            dataType: 'json',
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#candidacy-keywords").tagit("createTag", data[i]['keyword_name']);
                }

            }
        });

        $.ajax({
            url: "/candidacies/" + candidacyid + "/pledges",
            type: 'get',
            dataType: 'json',
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var div = "<div class='pledge'><div class='form-group'><label>공약 제목</label> <button type='button' class='pull-right btn btn-default btn-sm delete-pledge'>" +
                        "<span class='glyphicon glyphicon-minus' aria-hidden='true'></span>" +
                        "</button>" +
                        "<input type='text' class='form-control pledge-title' name='pledge-title' value='" + data[i]['pledge_title']
                        + "' placeholder='공약 제목 입력하세요.'>" +
                        "</div>" +
                        "<div class='form-group'>" +
                        "<label>공약 상세</label>" +
                        "<textarea class='form-control pledge-description' name='pledge-description' rows='3' placeholder='공약 요약 입력하세요.'>" +
                        data[i]['pledge_description'] + "</textarea>" +
                        "</div>" +
                        "</div>";

                    $("#pledge-list").append(div);
                }
            }
        });
    });

    $("#district-input").autocomplete({
        source: function (request, response) {
            $.ajax({
                url: "/cities/" + $('.selected-city').data('citycode') + "/districts",
                method: "get",
                contentType: "application/json; charset=UTF-8",
                dataType: "json",
                data: {
                    q: request.term
                },
                success: function (data) {
                    var result = new Array();
                    for (var i = 0; i < data.length; i++) {
                        result[i] = data[i]['district_name'];
                    }
                    response(result);
                }
            });
        }
    });

    $(".add-pledge").on('click', function () {
        var div = "<div class='pledge'><div class='form-group'><label>공약 제목</label> <button type='button' class='pull-right btn btn-default btn-sm delete-pledge'>" +
            "<span class='glyphicon glyphicon-minus' aria-hidden='true'></span>" +
            "</button>" +
            "<input type='text' class='form-control pledge-title' name='pledge-title' value='' placeholder='공약 제목 입력하세요.'>" +
            "</div>" +
            "<div class='form-group'>" +
            "<label>공약 상세</label>" +
            "<textarea class='form-control pledge-description' name='pledge-description' rows='3' placeholder='공약 요약 입력하세요.'></textarea>" +
            "</div>" +
            "</div>";

        $("#pledge-list").append(div);
    });

    $("#pledge-list").on('click', '.delete-pledge', function () {
        if (confirm('해당 공약을 삭제하시겠습니까?')) {
            var thisPledge = $(this).parents('.pledge');
            thisPledge.remove();
            alert('삭제하였습니다.');
        } else {
            alert('취소하셨습니다.');
        }
    });

    $('#candidacy-modal-form').submit(function () {

        var keywords = $("#candidacy-keywords").tagit("assignedTags");

        $.ajax({
            url: '/candidacies/' + $('#modal-candidacy-id').val() + '/keywords',
            type: 'put',
            data: JSON.stringify(keywords),
            contentType: "application/json; charset=UTF-8",
            dataType: 'text',
            success: function (isSuccess) {
                var pledges = [];
                for (var i = 0; i < $("input[name=pledge-title]").length; i++) {
                    var pledge = {
                        pledge_title: $("input[name=pledge-title]")[i].value,
                        pledge_description: $("textarea[name=pledge-description]")[i].value
                    };
                    pledges.push(pledge);
                }
                $.ajax({
                    url: '/candidacies/' + $('#modal-candidacy-id').val() + '/pledges',
                    type: 'put',
                    data: JSON.stringify(pledges),
                    contentType: "application/json; charset=UTF-8",
                    dataType: 'text',
                    success: function (isSuccess) {

                        var candidacyStatus = {
                            cadidacy_id: $('#modal-candidacy-id').val(),
                            candidacy_status: $(':radio[name="candidacyStatus"]:checked').val()
                        };
                        $.ajax({
                            url: '/candidacies/' + $('#modal-candidacy-id').val() + '/status',
                            type: 'put',
                            data: JSON.stringify(candidacyStatus),
                            contentType: "application/json; charset=UTF-8",
                            dataType: 'text',
                            success: function (isSuccess) {
                                alert("변경되었습니다");
                                //window.location.replace("/candidacies/" + postId);
                            }
                        });
                    }
                });
            }
        });

        return false;
    });

})(jQuery);