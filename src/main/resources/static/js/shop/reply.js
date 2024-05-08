function replyWriteFn() {
  const shopId = $('#shopId').val();
  const replyContent = $('#replyContent').val();
  const nick = $('#nick').val();

  const dataVal = {
    'shopId': shopId,
    'content': replyContent,
    'nick': nick,
  };

  $.ajax({
    type: 'POST',
    url: "/reply/replyWrite/",
    data: dataVal, // 없으면 data 타입 form
    success: function () {
      alert('성공'); //서버에서 응답
      $('#replyContent').val("");

replyList();

    },
    error: function (res) {
      alert("실패")
    }
  });

};


function replyList() {
 const id = parseInt($('#shopId').val());
  $.ajax({
    type: 'GET',
    url: `/reply/replyList/${id}`,
    success: function (res) {

      console.log(res);

       let htmlData = ``;
                  res.forEach(el=>{

                  const createTime = new Date(el.createTime);

                  		const formattedCreateTime = `${createTime.getFullYear()}년 ${createTime.getMonth() + 1}월 ${createTime.getDate()}일
                  		${createTime.getHours()}시 ${createTime.getMinutes()}분 ${createTime.getSeconds()}초`;
                  htmlData += `
                                  <div class="reply456">
                                  <div class="replyTop">
                                  <div class="replyTop-left">
                                  <li id="replyId" class="replyId">${el.id}</li>
                                  <li>
                                  <button type="button" class="replyProfile"</button>
                                    </li>
                                                <li>${el.nick}   </li>
                                                <li>${formattedCreateTime}</li>
                                                  </div>
                                                  <div class="replyTop-right">
                                                <li>
                                                  <button type="button" class="replyBtn_good" onClick="replyGoodFn(event,${el.id})" id="replyBtn_good">
                                                  </button>
                                                  <p id="replyGood">${el.good}</p>
                                                </li>
                                                <li>
                                                  <button type="button" class="replyBtn_bad" onClick="replyBadFn(event,${el.id})" id="replyBtn_bad">
                                                  </button>
                                                  <p id="replyBad">${el.bad}</p>
                                                </li>
                                                <li>
                                                  <input type="button" class="replyDeleteBtn" onClick="replyDeleteFn(event,${el.id})" name="replyDeleteBtn">
                                                </li>
                                                </div>
                                  </div>
                                  <div class="replyDown">
                                                <li>${el.content}</li>

                                  </div>
                                  </div>
                  `;
                  });
      $(".tData").html(htmlData); // 하위 요소에
    },
    error: function () {
    }
  });
};

function replyDeleteFn(event, replyId){
 const shopId=$('#shopId').val()
 $.ajax({
    type: 'POST',
    url: `/reply/replyDelete/${replyId}/shop/${shopId}`,

    success: function (res) {
      if(res==1){
         alert('삭제완료')
         console.log(res)

         replyList();
        }
      },
    error: function () {
    alert('실패')
    }
  });
}




//$(document).on('click', '#replyBtn_good', btnReplyGoodFn);
function replyGoodFn(event,replyId) {
  $.ajax({
    type: 'GET',
    url: `/reply/${replyId}/good`,
    success: function (res) {
      alert('좋아요')
      console.log(res)
      $('#replyGood').text(res);
      replyList();
    },
    error: function () {}
  });
}

function replyBadFn(event,replyId) {

  $.ajax({
    type: 'GET',
    url: `/reply/${replyId}/bad`,
    success: function (res) {
      alert('싫어요')
      console.log(res)
      $('#replyBad').text(res);
      replyList();
    },
    error: function () {}
  });
}

/*비로그인*/
function replyWriteFnNo() {

alert('로그인후 사용가능합니다')

}





(() => {
  replyList();
})();






