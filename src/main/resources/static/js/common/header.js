//**************************
/*(()=>{
alert();
})();*/
//**************************

const search=document.querySelector('.header .nav .left-menu .search');
//const searchSub=search.querySelector('.search-sub');
const searchSub=document.querySelector('.header .nav .left-menu .search .search-sub');

search.addEventListener('click',()=>{
        if(searchSub.classList.contains('off')){
            searchSub.classList.remove('off');
            searchSub.classList.add('on');
        }

});

const close = document.querySelector('.header .nav .left-menu .search .search-sub .head span.close');

close.addEventListener('click', (event)=>{

 event.stopPropagation();

if(searchSub.classList.contains('on')){
            searchSub.classList.remove('on');
            searchSub.classList.add('off');
        }
  });
