
//<!--궁금한것-->
//${'.ajax2'}.on('click',ajax2Fn);
//function ajax2Fn() {
//  $.ajax({
//    type:"GET",
//    url:"/admin/shopList",
//    success:function(res){
//    console.log(res)
//
//      let data=`
//        res.forEach((el,index)=>{
//          data+=`
//         <tr>
//              <td> ${shop.id}</td>
//              <td> ${shop.title}</td>
//              <td> ${shop.content}</td>
//              <td> ${shop.price}</td>
//              <td> ${shop.hit}</td>
//              <td> ${shop.good}</td>
//              <td> ${shop.bad}</td>
//              <td> ${shop.createTime}</td>
//              <td> ${shop.updateTime}</td>
//              <td> @{/admin/detail/{id}(id=${shop.id})}</td>
//
//              </tr>
//       `;
//        });
//         data+=``; console.log(data);
//         $('.list-data').html(data);
//    },
//    error:function() {}
//});
//}


function setInnerHTML() {
  const element = document.getElementById('my_div');
  element.innerHTML
    = '<div> innder </div>';
}



$('#first').on('click',ajax1Fn);
  const title="1";
  function ajax1Fn() {
  $.ajax({
  type:"GET",
  url:"/index/shop1/"+title, //검색어

  success:function(res) {
  alert("조회성공");
  const data = `

  <ul>
    <li>${shop.title}</li>
    <li>${shop.content}</li>
    <li>${shop.price}</li>

  </ul>
  `;
  $('.shopList').html(data)
  },
  error:function(){
    alert("조회실패");
  }
  })
 }






$('.list-container>li').each((idx,el)=>{

  console.log(idx)
  console.log(el)
});







