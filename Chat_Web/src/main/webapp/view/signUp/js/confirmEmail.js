function validate() {
    var code = document.getElementById("cfemailCode").value;
    if (/^[0-9a-zA-Z]{6}$/.test(code)) {
        document.getElementById("frmconfirmEmail").submit();
    } else {
        alert("Wrong Format Code!!");
    }
}