$(document).ready(function() {

    $('.item').change(function() {
        calculateTotalPrice(); // 체크박스가 변경될 때마다 총 가격 계산
    });

    // 구매 버튼 클릭 시 호출되는 함수
//    $('#buyBtn').click(function() {
//        buySelectedItems(); // 선택된 상품을 구매하는 함수 호출
//    });
});

// 총 가격을 계산하는 함수
function calculateTotalPrice() {
    let totalPrice = 0;

    // 체크된 상품의 가격을 총 가격에 더함
    $('.item:checked').each(function() {
        const price = parseInt($(this).closest('tr').find('.price').text().replace('원', ''));
        const count = parseInt($(this).closest('tr').find('.count').text());
        totalPrice += price * count;
    });

    // 총 가격을 화면에 출력
    $('#totalPrice').text(totalPrice);
}

// 선택된 상품을 구매하는 함수
function buySelectedItems() {
    const selectedItems = [];

    // 체크된 상품을 배열에 추가
    $('.item:checked').each(function() {
        const cartId = $(this).closest('tr').find('#cartId').val();
        selectedItems.push(cartId);
    });

    // 선택된 상품들을 구매하는 AJAX 요청
    $.ajax({
        type: 'POST',
        url: `/shop/cartBuy/{id}`, // 선택된 상품들을 구매하는 엔드포인트
        contentType: 'application/json',
        data: JSON.stringify(selectedItems), // 선택된 상품들의 ID 배열을 JSON 형태로 전달
        success: function (res) {
            if (res === 1) {
                console.log(res);
                location.href = `/shop/cartBuyCon`; // 구매 완료 후 이동할 페이지로 수정 필요
            }
        },
        error: function () {
            alert('구매에 실패하였습니다.');
        }
    });
}


/////////////////장바구니 삭제////////////////////////////
//    $('#cartDelete').on('click', cartDeleteFn);
$(document).on('click', '#cartDelete', cartDeleteFn);
    function cartDeleteFn() {

      const cartId=$('#cartId').val();

      $.ajax({
        type: 'POST',
        url: `/shop/cartDelete/${cartId}`,
        success: function (res) {
            if(res==1){
            alert('삭제완료')
            console.log(res)
            document.location.href = document.location.href;
            }
          },
        error: function () {
        alert('실패')
        }
      });
    }

$(document).on('click', '#buyBtn', cartBuyFn);
    function cartBuyFn() {

      const cartId=$('#cartId').val();

      $.ajax({
        type: 'POST',
        url: `/shop/cartBuy/${cartId}`,
        success: function (res) {
            if(res==1){
            console.log(res)
            location.href=`/shop/cartBuyCon`;
            }
          },
        error: function () {
        alert('실패')
        }

      });
    }

document.getElementById('allCheck').addEventListener('click', allCheckFn);

function allCheckFn() {
    const selectAll = document.getElementById('allCheck');
    const checkboxes = document.querySelectorAll('.item');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    });

    calculateTotalPrice(); // 전체 선택 상태가 변경될 때마다 총 가격 재계산
}





