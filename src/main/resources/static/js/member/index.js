//**************************
(()=>{
alert();
})();
//**************************

document.getElementById('chatbotButton').addEventListener('click', function() {
    // 8099 포트로의 연결을 수행하는 코드 작성
    window.location.href = 'http://localhost:8099'; // 예시: 새 창을 열어 해당 포트로 이동
});