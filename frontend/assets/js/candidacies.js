(function ($) {

	$(".dropdown-menu").on('click','a',function(){
		var selectedCity = $(this).text();
		var cityCode = $(this).data('citycode')

		$(".selected-city").html(selectedCity+'<span class="caret"></span>');
		$(".selected-city").data('citycode',cityCode);

		console.log($(".selected-city").data('citycode'))
	})

	    //admin post page에서 각 row를 클릭하면 해당 row의 정보를 가지고 모달창을 띄우는 로직
    $("#candidacy-table").on('click', 'tr', function () {
        var candidacyid = $(this).data('candidacyid');
        // $.ajax({
        //     url: "/candidacies/" + postId,
        //     type: 'get',
        //     dataType: 'json',
        //     success: function (data) {

        //         $("input[name=sticky]").prop('checked', false);

        //         $.ajax({
        //             url: "/restful/stickyPosts/" + postId,
        //             type: 'get',
        //             dataType: 'text',
        //             success: function (isSticky) {
        //                 console.log(isSticky);
        //                 if (isSticky == "true") {
        //                     $("input[name=sticky]").prop('checked', true);
        //                 }
        //             }
        //         });
        //         $('.modal-postId').val(data['postId']);
        //         $('.modal-postTitle').val(data['title']);
        //         $('.modal-existFiles').val(data['existFiles']);
        //         $('#summernote').summernote('code', data['contents']);
                $('#candidacy-modal').modal('toggle');
        //     }
        // });
    });


})(jQuery);