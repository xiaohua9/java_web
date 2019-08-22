/*表单数据验证*/
function infoCheckData() {
    var flag=true;
    if (userName.value.length<1){
        flag=false;
        userNameError.style.color="red";
        userNameError.innerText="用户名不能为空";
    }
    if (userPassword.value.length<6){
        flag=false;
        userPasswordError.style.color="red";
        userPasswordError.innerText="密码不能小于6位";
    }
    if (userGender.value!="男" && userGender.value!="女") {
        flag=false;
        userGenderError.style.color = "red";
        userGenderError.innerText = "性别必须为男或则女";
    }
    if (userAge.value<1 || userAge.value>150|| isNaN(userAge.value) ) {
        flag=false;
        userAgeError.style.color = "red";
        userAgeError.innerText = "请输入正确的年龄";
    }
    if (userAddress.value.length<1 ) {
        flag=false;
        userAddressError.style.color = "red";
        userAddressError.innerText = "地址不能为空";
    }
    var match=/^\d{4}-\d{1,2}-\d{1,2}$/;
    if (!match.test(userBirthday.value) ) {
        flag=false;
        userBirthdayError.style.color = "red";
        userBirthdayError.innerText = "格式参照：2019-1-1";
    }
    return flag;
}
//用户名的失去焦点验证
function checkUserName() {
    if (userName.value.length<1){
        userNameError.style.color="red";
        userNameError.innerText="用户名不能为空";
    } else {
        userNameError.innerText="";
    }
}
//用户密码的失去焦点验证
function checkUserPassword() {
    if (userPassword.value.length < 6) {
        userPasswordError.style.color = "red";
        userPasswordError.innerText = "密码不能小于6位";
    } else {
        userPasswordError.innerText = "";
    }
}
//用户性别的失去焦点验证
function checkUserGender() {
    if (userGender.value!="男" && userGender.value!="女") {
        userGenderError.style.color = "red";
        userGenderError.innerText = "性别必须为男或则女";
    } else {
        userGenderError.innerText = "";
    }
}
//用户年龄的失去焦点验证
function checkUserAge() {
    if (userAge.value<1 || userAge.value>150|| isNaN(userAge.value) ) {
        userAgeError.style.color = "red";
        userAgeError.innerText = "请输入正确的年龄";
    } else {
        userAgeError.innerText = "";
    }
}
//用户地址的失去焦点验证
function checkUserAddress() {
    if (userAddress.value.length<1 ) {
        userAddressError.style.color = "red";
        userAddressError.innerText = "地址不能为空";
    } else {
        userAddressError.innerText = "";
    }
}
//用户生日的失去焦点验证
function checkUserBirthday() {
    var match=/^\d{4}-\d{1,2}-\d{1,2}$/;
    if (!match.test(userBirthday.value) ) {
        userBirthdayError.style.color = "red";
        userBirthdayError.innerText = "格式参照：2019-1-1";
    } else {
        userBirthdayError.innerText = "";
    }
}