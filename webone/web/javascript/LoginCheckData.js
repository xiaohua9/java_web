/*表单数据验证*/
function loginCheckData() {
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