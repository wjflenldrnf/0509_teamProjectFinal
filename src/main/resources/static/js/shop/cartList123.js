function priceSum() {
        let totalPrice = 0;
        const items = document.querySelectorAll('.item:checked');

        items.forEach(item => {
            const price = parseInt(item.closest('tr').querySelector('.price').textContent);
            const count = parseInt(item.closest('tr').querySelector('.count').textContent);
            totalPrice += price * count;
        });

        document.getElementById('totalPrice').textContent = totalPrice + '원';
    }

    function count(action, button) {
        const countElement = button.parentNode.parentNode.querySelector('.count');
        let count = parseInt(countElement.textContent);

        if (action === 'plus') {
            count++;
        } else if (action === 'minus' && count > 1) {
            count--;
        }

        countElement.textContent = count;
        priceSum();
    }

    function allCheck(checkbox) {
        const checkboxes = document.querySelectorAll('.item');
        checkboxes.forEach(item => {
            item.checked = checkbox.checked;
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


///////////////////구매///////////////////////////

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




(() => {
  priceSum();
})();