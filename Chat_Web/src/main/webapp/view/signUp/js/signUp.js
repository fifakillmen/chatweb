/* YOU DONT NEED THESE, these are just for the popup you see */


function validate() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var cfpassword = document.getElementById("cfpassword").value;
    let mess = "";
    if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
        mess += "Email invalid!!!\n";
    }
    if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(password)) {
        mess += "Password invalid!!!\n";
    }
    if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(cfpassword)) {
        mess += "Confirm password invalid!!!\n";
    }
    if (password !== cfpassword) {
        mess += "Confirm password must be same password\n";
    }
    if (mess === "") {
        $.ajax({
            url: '/Chat_Web_war_exploded/signUp',
            type: 'POST',
            data: {
                email: email,
                password: password,
                cfpassword: cfpassword
            },
            success: function (data) {
                if (data === "confirmEmail") {
                    window.location = "/Chat_Web_war_exploded/" + data;
                } else {
                    alert(data);
                }

            }
        });
    } else {
        alert(mess);
    }
}