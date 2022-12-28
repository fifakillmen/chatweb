function validate() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    let mess = "";
    if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
        mess += "Email invalid!!!\n";
    }
    if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(password)) {
        mess += "Password invalid!!!\n";
    }
    if (mess === "") {
        $.ajax({
            url: '/Chat_Web_war_exploded/signIn',
            type: 'POST',
            data: {
                email: email,
                password: password
            },
            success: function (data) {
                if (data === "home") {
                    window.location = "/Chat_Web_war_exploded/" + data;
                } else if (data === "Can't find Email") {
                    alert(data);
                } else if (data === "signIn") {
                    window.location = "/Chat_Web_war_exploded/" + data;
                }

            }
        });
    } else {
        alert(mess);
    }
}