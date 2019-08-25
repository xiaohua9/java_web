


/*各个分项的表单验证*/
function checkStudentName() {
    if (studentName.value.length < 1) {
        studentNameError.style.color="red";
        studentNameError.innerText="姓名不能为空";
    }else {
        studentNameError.style.color="green";
        studentNameError.innerText="验证通过";
    }
}
function checkAge() {
    if (age.value>150||age.value<1||isNaN(age.value)){
        ageError.style.color="red";
        ageError.innerText="请输入正确的年龄";
    }else {
        ageError.style.color="green";
        ageError.innerText="验证通过";
    }
}
function checkBoreDate() {
    var partern=/^\d{4}-\d{1,2}-\d{1,2}$/;
    if (partern.test(boreDate.value)){
        boreDateError.style.color="green";
        boreDateError.innerText="验证通过";
    } else {
        boreDateError.style.color="red";
        boreDateError.innerText="参照格式：2019-1-1";
    }
}

/*整个表单的提交验证，如果不满足条件就不让提交，前面的各个项验证只是给出提示，并不能阻止表单的提交，故在此完成此功能*/
function checkData() {
    var flag=true;
    if (studentName.value.length < 1) {
        flag=false;
        studentNameError.style.color="red";
        studentNameError.innerText="姓名不能为空";
    }
    if (age.value < 1 || age.value > 150 || isNaN(age.value)) {
        flag=false;
        ageError.style.color="red";
        ageError.innerText="请输入正确的年龄";
    }
    var partern=/^\d{4}-\d{1,2}-\d{1,2}$/;
    if (!partern.test(boreDate.value)){
        flag=false;
        boreDateError.style.color="red";
        boreDateError.innerText="参照格式：2019-1-1";
    }
    return flag;
}