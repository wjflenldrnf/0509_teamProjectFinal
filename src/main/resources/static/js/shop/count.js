function count(type) {
  const price = parseInt($('#price').val());

  let number = $('#result').text();
  let priceSumVal = $('#priceSum').text();

  if (type === 'plus') {
    number = parseInt(number) + 1;
  } else if (type === 'minus') {
    number = parseInt(number) - 1;
  }

   $('#result').text(number);
  $('#priceSum').text( (number * price));

console.log($('#result').text())
console.log($('#priceSum').text())
}

/////////////////////////
$('#cartBtn').on('click', cartBtnFn);
function cartBtnFn() {

  const shopId=$('#shopId').val()  //input 일때 val
  const priceCount=$('#result').text()

  $.ajax({
    type: 'GET',
    url: `/shop/cart/${shopId}/${priceCount}`,

    success: function (res) {
     alert('장바구니에 담았습니다!')

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
        } else {
            location.href=`/shop/cartList`;
        }
    }


/*비로그인*/
$('#cartBtnNo').on('click', cartBtnFnNo);
function cartBtnFnNo() {
alert('로그인후 사용가능합니다')
}





(() => {
  count();
})();