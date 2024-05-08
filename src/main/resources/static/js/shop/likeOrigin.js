$('#btnGood').on('click', btnGoodFn);

function btnGoodFn() {
  const id = parseInt($('#shopId').val());

  $.ajax({
    type: 'GET',
    url: `/shop/sell/${id}/good`,
    success: function (res) {
      alert('좋아요')
      console.log(res)
      $('#good').text(res);
    },
    error: function () {}
  });
}

$('#btnBad').on('click', btnBadFn);

function btnBadFn() {
  const id = parseInt($('#shopId').val());

  $.ajax({
    type: 'GET',
    url: `/shop/sell/${id}/bad`,
    success: function (res) {
      alert('싫어요')
      console.log(res)
      $('#bad').text(res);
    },
    error: function () {}
  });
}


function count(type) {
  // 결과를 표시할 element
//   $('.result');
//   $('.priceSum');
  const price = parseInt($('#price').val());

  // 현재 화면에 표시된 값
  let number = $('#result').text();
  let priceSumVal = $('#priceSum').text();

  // 더하기/빼기
  if (type === 'plus') {
    number = parseInt(number) + 1;
  } else if (type === 'minus') {
    number = parseInt(number) - 1;
  }

  // 결과 출력
   $('#result').text(number);
  $('#priceSum').text( (number * price));

console.log($('#result').text())
console.log($('#priceSum').text())
}

////////////////////////////////////////
$('#cartBtn2').on('click',(e)=>{

e.preventDefault();
const priceCount=$('#result').text()
const shopId=$('#shopId').val()

location.href=`/shop/cart/${shopId}/${priceCount}`;

});

/////////////////////////
$('#cartBtn').on('click', cartBtnFn);
function cartBtnFn() {

  const shopId=$('#shopId').val()  //input 일때 val
  const priceCount=$('#result').text()

  $.ajax({
    type: 'GET',
    url: `/shop/cart/${shopId}/${priceCount}`,

    success: function (res) {
     alert('장바구니 담')
     console.log(res)
     test();

      },
    error: function () {
    alert('실패')
    }

  });
}

function test() {
        if (!confirm("장바구니로 이동하시겠습니까?")) {
            alert("네");
        } else {
            location.href=`/shop/cartList`;
        }
    }


//////////////////////////댓글/////////////////////////////

function replyList() {
  const shopId = $('#shopId').val();

  console.log(shopId);
  $.ajax({
    type: 'GET',
    url: '/reply/replyList/' +shopId,
    success: function (res) {

      console.log(res);

       let htmlData = ``;
                  res.forEach(el=>{
                  htmlData += `
                      <tr>
                                  <td>${el.id}</td>
                                  <td>${el.content}</td>
                                  <td>${el.nick}</td>
                                  <td>${el.good}</td>
                                  <td>${el.bad}</td>
                                  <td>${el.createTime}</td>
                                  </tr>
                  `;
                  });
//      $(".tData").html(htmlData); // 하위 요소에
    },
    error: function () {
    }
  });

};


const replyBtn = $('#replyBtn');
// replyBtn 을 클릭하면 ajaxWrite 함수를 실행
replyBtn.on('click', ajaxWrite);

function ajaxWrite() {
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
    url: "/reply/replyWrite",
    data: dataVal, // 없으면 data 타입 form
    success: function (res) {
      alert('성공'); //서버에서 응답

  let htmlData = `<tr>
            <td>${res.id}</td>
            <td>${res.content}</td>
            <td>${res.nick}</td>
            <td>${res.good}</td>
            <td>${res.bad}</td>
            <td>${res.createTime}</td>
            </tr>
        `;
            $(".tData").append(htmlData);


      replyList(); // 덧글 리스트 get

    },
    error: function () {
      alert("실패")
    }

  });

};

////////////////////////삭제///////////////////////////
function replyDeleteBtnFn() {

  const shopId=$('#shopId').val()
  const replyId=$('#replyId').val()  //input 일때 val


 const dataVal = {
    'shopId': shopId,
    'replyId': replyId
  };

  $.ajax({
    type: 'post',
    url: `/reply/replyDelete/${replyId}/shop/${shopId}`,
    data: dataVal,
    success: function (res) {
      alert('삭제완료')
      console.log(res)

      replyList();

      },
    error: function () {
    alert('실패')
    }
  });
}

$('#replyDeleteBtn').on('click', test2);
function test2() {
        if (!confirm("삭제하실거에요?")) {

        } else {
        replyDeleteBtnFn();
        }
    }

////////////////////////////삭제2////////////////////////////////////
$('#replyDeleteBtn2').on('click',(e)=>{

  e.preventDefault();
  const shopId=$('#shopId').val()
  const replyId=$('#replyId').val()
  deleteReply();

});

function deleteReply() {
        if (!confirm("삭제하시겠습니까?")) {
            alert("취소");
        } else {
          const shopId=$('#shopId').val()
          const replyId=$('#replyId').val()
            location.href=`/reply/replyDelete/${replyId}/shop/${shopId}`;

        }
    }



(() => {
  replyList();
})();