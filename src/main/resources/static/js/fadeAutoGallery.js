const autoGallery= document.querySelector('.autoGallery');
const autoGalleryLi= document.querySelectorAll('.autoGallery>ul>li');
const items = document.querySelector('.items');
const itemsLi= document.querySelectorAll('.items>ul>li');
let i=-1;
const arrColor=['#a2dfe6','#a2dfe6','#a2dfe6','#a2dfe6','#a2dfe6'];

function autoGalleryFn(){
  i++;
  // i에 해당하는 li만  fadeIn 적용 , 나머지는 제거
  console.log(`i=${i}`);
  autoGalleryLi.forEach((el,idx) => {
    if(i==idx) {
      el.classList.add('fadeIn');
    } else{
      el.classList.remove('fadeIn');
    }
  });
  itemsLi.forEach((el,idx)=> {
    if(i==idx) {
      el.style.backgroundColor =arrColor[i];
      el.classList.add('on');
    }else{
      el.style.backgroundColor ='#ffffff00';
      el.classList.remove('on');
    }
  });
  console.log(`i end =${i}`);
  if(i >=autoGalleryLi.length-1) i= -1;
}

let setIn = setInterval(autoGalleryFn,3000);

const spanArrows = document.querySelectorAll('span.arrow');

spanArrows.forEach((el,idx)=>{
  el.addEventListener('mouseover',arrowFn);
  el.addEventListener('mouseout',arrowFn);
});


(()=>{
  autoGalleryFn();
})();

