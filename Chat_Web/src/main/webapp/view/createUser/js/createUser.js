function validateUser() {
    var fullname = document.getElementById("fullname").value;
    var displayname = document.getElementById("displayname").value;
    var dob = document.getElementById("dob").value;
    var address = document.getElementById("address").value;
    var phone = document.getElementById("phone").value;
    var select = document.getElementById("gender");
    var gender = select.options[select.selectedIndex].text;
    var mess = "";
    if (/^[a-zA-ZÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ].*[\s\.]*$/.test(fullname) === false) {
        mess += "Invalid full name\n";
    }
    if (displayname === null) {
        displayname = fullname;
    } else if (/^[a-zA-ZÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ].*[\s\.]*$/.test(displayname) === true) {

    } else {
        mess += "Invalid display name\n";
    }
    var dob_date = Date.parse(dob);
    var today = new Date();
    if (/^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$/.test(dob)) {
        if (today < dob_date) {
            mess += "Your birthday must be in the pass\n";
        }
    } else {
        mess += "Invalid birthday\n";
    }
    if (!/^[#.0-9a-zA-ZÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s,-]+$/.test(address)) {
        mess += "Invalid address\n";
    }
    if (!/^(\+\d{1,3}[- ]?)?\d{10}$/.test(phone)) {
        mess += "Invalid phone\n"
    }
    if (!/^Male$|^Female$/.test(gender)) {
        mess += "Invalid gender\n";
    }
    if (mess === "") {
        $.ajax({
            url: '/Chat_Web_war_exploded/createUser',
            type: 'POST',
            data: {
                fullname: fullname,
                displayname: displayname,
                dob: dob,
                address: address,
                phone: phone,
                gender: gender
            },
            success: function (data) {
                if (data === "User is exist") {
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