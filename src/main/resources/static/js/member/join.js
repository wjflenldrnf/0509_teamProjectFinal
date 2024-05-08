//**************************
//(()=>{
//alert();
//})();
//**************************

$('#emailChkBtn').on('click', emailChkBtnFn);

function emailChkBtnFn() {
    const emailVal = $('#email').val();
    if (emailVal.length <= 0) {
        alert('email을 입력하세요')
        $('#email').focus();
        return false;
    }
    const dataVal = {
        'email': emailVal
    };


    $.ajax({
        type: 'GET',
        url: 'join/emailChecked',
        data: dataVal,
        success: function (res) {

            console.log(res);
            if (res == 1) {
                alert("Available email for registration")
            } else {
                alert("email already exists!!")
            }
        },
        error: function () {

        }
    });
}

////////////////////////////// member, seller 회원가입창 구분 ////////////////////////////////////
//$('#memberJoin').on('click', memberJoinFn);
//$('#sellerJoin').on('click', sellerJoinFn);
//
//function memberJoinFn() {
//
//    $.ajax({
//            type: "post",
//            url: "/member/join/member",
//            success: function (res) {
//
//                let html = ``;
//
//                html += `
//                    <form th:action="@{/member/join}" method="POST" th:object="${memberDto}">
//                        <h1 class="title">회원가입1</h1>
//                        <ul>
//                            <li>
//                                <label for="email">email</label>
//                                <input type="email" name="email" id="email" placeholder="이메일입력">
//                                <input type="button" name="emailChkBtn" id="emailChkBtn" value="emailChkBtn">
//                            </li>
//                            <p th:if="${#fields.hasErrors('email')}" th:errors="*{email}" class="error"></p>
//
//
//                            <li>
//                                <label for="userPw">userPw</label>
//                                <input type="text" name="userPw" id="userPw" placeholder="비밀번호입력">
//                            </li>
//
//
//                            <p th:if="${#fields.hasErrors('userPw')}" th:errors="*{userPw}" class="error"></p>
//
//
//                            <li>
//                                <label for="name">userName</label>
//                                <input type="text" name="name" id="name" placeholder="이름입력">
//                            </li>
//
//
//                            <p th:if="${#fields.hasErrors('name')}" th:errors="*{name}" class="error"></p>
//
//
//                            <li>
//                                <label for="nickName">nickName</label>
//                                <input type="text" name="nickName" id="nickName" placeholder="닉네임입력">
//                            </li>
//
//
//                            <p th:if="${#fields.hasErrors('nickName')}" th:errors="*{nickName}" class="error"></p>
//
//
//                            <li>
//                                <label>성별</label>
//                                <input type="radio" id="male" name="gender" value="M" checked/>
//                                <label for="male">Male</label>
//                                <input type="radio" id="female" name="gender" value="F"/>
//                                <label for="female">Female</label>
//                            </li>
//
//
//                            <p th:if="${#fields.hasErrors('gender')}" th:errors="*{gender}" class="error"></p>
//
//
//                            <li>
//                                <label for="mainAddress">address</label>
//                            <li>
//                                <input type="text" id="postcode" placeholder="우편번호">
//                                <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
//                                <input type="text" id="mainAddress" placeholder="주소"><br>
//                                <input type="text" id="detailAddress" placeholder="상세주소">
//                                <input type="text" id="extraAddress" placeholder="참고항목">
//                            </li>
//                            <input type="text" name="address" id="address">
//                            </li>
//
//
//                            <p th:if="${#fields.hasErrors('address')}" th:errors="*{address}" class="error"></p>
//
//
//                            <li>
//                                <label for="note">content</label>
//                                <textarea name="note" id="note" cols="41" rows="5"></textarea>
//                            </li>
//
//                            <!--                <li>-->
//                            <!--                    <label for="number">number</label>-->
//                            <!--                    <input type="text" name="number" id="number" placeholder="번호입력">-->
//                            <!--                </li>-->
//
//                            <!--                <li class="class2">-->
//                            <!--                    <label for="number">핸드폰번호</label>-->
//                            <!--                    <div class="right">-->
//                            <!--                        <select name="number" id="number" class="number">-->
//                            <!--                            <option value="">::선택::</option>-->
//                            <!--                            <option value="010">010</option>-->
//                            <!--                            <option value="011">011</option>-->
//                            <!--                            <option value="012">012</option>-->
//                            <!--                        </select> - -->
//                            <!--                        <input type="text" name="number" class="number" maxlength="4">-->
//                            <!--                        <input type="text" name="number" class="number" maxlength="4">-->
//                            <!--                    </div>-->
//                            <!--                </li>-->
//                            <li>
//                                <label>전화번호</label>
//                                <input type="text" name="number" id="number" placeholder="번호입력" maxlength="13">
//                            </li>
//
//
//                            <li class="last">
//                                <input type="reset" value="초기화">
//                                <input type="submit" value="회원가입">
//                            </li>
//                        </ul>
//                    </form>
//        `;
//
//            }
//
//            html += ``;
//
//            $('.join-form').html(html);
//        },
//        error: function () {
//            alert("memberJoin 창 불러오기 실패");
//        }
//    });
//
//
//    function sellerJoinFn() {
//
//        $.ajax({
//                type: "post",
//                url: "/member/join/seller",
//                success: function (res) {
//
//                    let html = ``;
//
//                    html += `
//                        <form th:action="@{/member/join/seller}" method="POST" th:object="${memberDto}">
//                            <h1 class="title">회원가입2</h1>
//                            <ul>
//                                <li>
//                                    <label for="email">email</label>
//                                    <input type="email" name="email" id="email" placeholder="이메일입력">
//                                    <input type="button" name="emailChkBtn" id="emailChkBtn" value="emailChkBtn">
//                                </li>
//                                <p th:if="${#fields.hasErrors('email')}" th:errors="*{email}" class="error"></p>
//
//
//                                <li>
//                                    <label for="userPw">userPw</label>
//                                    <input type="text" name="userPw" id="userPw" placeholder="비밀번호입력">
//                                </li>
//
//
//                                <p th:if="${#fields.hasErrors('userPw')}" th:errors="*{userPw}" class="error"></p>
//
//
//                                <li>
//                                    <label for="name">userName</label>
//                                    <input type="text" name="name" id="name" placeholder="이름입력">
//                                </li>
//
//
//                                <p th:if="${#fields.hasErrors('name')}" th:errors="*{name}" class="error"></p>
//
//
//                                <li>
//                                    <label for="nickName">nickName</label>
//                                    <input type="text" name="nickName" id="nickName" placeholder="닉네임입력">
//                                </li>
//
//
//                                <p th:if="${#fields.hasErrors('nickName')}" th:errors="*{nickName}" class="error"></p>
//
//
//                                <li>
//                                    <label>성별</label>
//                                    <input type="radio" id="male" name="gender" value="M" checked/>
//                                    <label for="male">Male</label>
//                                    <input type="radio" id="female" name="gender" value="F"/>
//                                    <label for="female">Female</label>
//                                </li>
//
//
//                                <p th:if="${#fields.hasErrors('gender')}" th:errors="*{gender}" class="error"></p>
//
//
//                                <li>
//                                    <label for="mainAddress">address</label>
//                                <li>
//                                    <input type="text" id="postcode" placeholder="우편번호">
//                                    <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
//                                    <input type="text" id="mainAddress" placeholder="주소"><br>
//                                    <input type="text" id="detailAddress" placeholder="상세주소">
//                                    <input type="text" id="extraAddress" placeholder="참고항목">
//                                </li>
//                                <input type="text" name="address" id="address">
//                                </li>
//
//
//                                <p th:if="${#fields.hasErrors('address')}" th:errors="*{address}" class="error"></p>
//
//
//                                <li>
//                                    <label for="note">content</label>
//                                    <textarea name="note" id="note" cols="41" rows="5"></textarea>
//                                </li>
//
//                                <!--                <li>-->
//                                <!--                    <label for="number">number</label>-->
//                                <!--                    <input type="text" name="number" id="number" placeholder="번호입력">-->
//                                <!--                </li>-->
//
//                                <!--                <li class="class2">-->
//                                <!--                    <label for="number">핸드폰번호</label>-->
//                                <!--                    <div class="right">-->
//                                <!--                        <select name="number" id="number" class="number">-->
//                                <!--                            <option value="">::선택::</option>-->
//                                <!--                            <option value="010">010</option>-->
//                                <!--                            <option value="011">011</option>-->
//                                <!--                            <option value="012">012</option>-->
//                                <!--                        </select> - -->
//                                <!--                        <input type="text" name="number" class="number" maxlength="4">-->
//                                <!--                        <input type="text" name="number" class="number" maxlength="4">-->
//                                <!--                    </div>-->
//                                <!--                </li>-->
//                                <li>
//                                    <label>전화번호</label>
//                                    <input type="text" name="number" id="number" placeholder="번호입력" maxlength="13">
//                                </li>
//
//
//                                <li class="last">
//                                    <input type="reset" value="초기화">
//                                    <input type="submit" value="회원가입">
//                                </li>
//                            </ul>
//                        </form>
//            `;
//
//                }
//
//                html += ``;
//
//                $('.join-form').html(html);
//            },
//            error: function () {
//                alert("memberJoin 창 불러오기 실패");
//            }
//        });