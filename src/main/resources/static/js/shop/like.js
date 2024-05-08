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
      $('#good2').text(res);
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
