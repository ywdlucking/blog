<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<footer>
  <p class="ft-copyright">土豆说 Design by 神奇小土豆  沪公网安备 31011202001459号</p>
  <div id="tbox"> 
	  <a id="togbook" href="javascript:$('#fedback').modal('show')"></a> 
	  <a id="gotop" href="javascript:document.documentElement.scrollTop = document.body.scrollTop =0"></a>   
  </div>
</footer>
<script type="text/javascript">
	function limitTextArea() {
		var max = 400;
		var field = $("#fedback_textarea").val();
		if(field.length>max){
			field = field.substring(0,max);
		}else{
			$("#fedback_counter").text(max-field.length);
		}
		$("#fedback_textarea").val(field)
	}
	
	function submitFedback() {
		var content = $("#fedback_textarea").val();
		var email = $("#fedback_email").val();
		if(content == ""){
			$('#fedback').modal('hide');
			window.wxc.xcConfirm("请填写反馈信息", window.wxc.xcConfirm.typeEnum.info);
			return;
		}
		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/comment/saveFedback.do',
			data : {
				content:content,
				email:email
			},
			dataType:'json',
			success : function(result){
				if(result.success){
					window.wxc.xcConfirm("反馈信息提交成功", window.wxc.xcConfirm.typeEnum.success);
					$('#fedback').modal('hide');
					$("#fedback_textarea").val("");
					$("#fedback_email").val("");
				}else{
					window.wxc.xcConfirm("反馈信息提交失败", window.wxc.xcConfirm.typeEnum.error);
				}
			}
		});
	}
	
	 function searchToggle(obj, evt){
         var container = $(obj).closest('.search-wrapper');

         if(!container.hasClass('active')){
               container.addClass('active');
               evt.preventDefault();
         }
         else if(container.hasClass('active') && $(obj).closest('.input-holder').length == 0){
               container.removeClass('active');
               // clear input
               container.find('.search-input').val('');
               // clear and hide result container when we press close
               container.find('.result-container').fadeOut(100, function(){$(this).empty();});
         }
     }

     function submitFn(obj, evt){
         value = $(obj).find('.search-input').val().trim();

         _html = "Yup yup! Your search text sounds like this: ";
         if(!value.length){
             _html = "Yup yup! Add some text friend :D";
         }
         else{
             _html += "<b>" + value + "</b>";
         }

         $(obj).find('.result-container').html('<span>' + _html + '</span>');
         $(obj).find('.result-container').fadeIn(100);

         evt.preventDefault();
     }
</script>

<div class="modal fade" id="fedback" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel"><span style="font-weight: bold;">意见反馈</span></h4>
      </div>
      <div class="modal-body">
      	<div class="modal-body-p">
      	  	<div><label><span style="color: red;">*</span>反馈内容（必填）</label></div>
      	  	<div><textarea id="fedback_textarea" oninput="limitTextArea()" rows="" cols="" placeholder="欢迎提出您的宝贵意见(400字以内)，感谢您的支持!"></textarea></div>
      	  	<div style="float: right;">还可以输入<span id="fedback_counter">400</span>个字符</div>
      	  	<div style="margin-top: 20px;"><label>联系邮箱</label></div>
      	  	<div><input id="fedback_email" type="text" placeholder="请留下您的联系邮箱，以便我们及时回复您"/></div>
      	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-success" onclick="submitFedback()">提交</button>
      </div>
    </div>
  </div>
</div>