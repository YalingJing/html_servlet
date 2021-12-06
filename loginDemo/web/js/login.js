var user = document.getElementById("input1");
var password = document.getElementById("psd1");
var user_info = document.getElementById("user_info");
var psd_info = document.getElementById("psd_info");
var formValid = {
    // 账号验证
    checkUserName:function (){
        var pattern = /^w{11,11}$/;
        if (user.value.length == 11){
            user_info.innerHTML = "账号已输入！";
            user_info.style.color = "green";
            return true;
        }
        if (user.value.length == 0){
            user_info.innerHTML = "账号不能为空！";
            user_info.style.color = "red";
            return false;
        }
        if (!pattern.test(user.value)){
            user_info.innerHTML = "账号需要11位手机号！";
            user_info.style.color = "red";
            return false;
        }

    },
    //密码验证
    checkPassword:function (){
        var pattern = /^w{12,18}$/;
        if (password.value.length >= 12 && password.value.length <= 18) {
            psd_info.innerHTML = "密码已输入";
            psd_info.style.color = "green";
            return true;
        }
        if (password.value.length == 0){
            psd_info.innerHTML = "密码不能为空！";
            psd_info.style.color = "red";
            return false;
        }
        if (!pattern.test(password.value)){
            psd_info.innerHTML = "密码需12-18位！";
            psd_info.style.color = "red";
            return false;
        }

    },
    //表单验证
    checkForm:function (){
        var userLogin = this.checkUserName();
        var psdLogin = this.checkPassword();
        return userLogin && psdLogin;
    }
}
