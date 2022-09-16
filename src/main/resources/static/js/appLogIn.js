function logIn() {
    const username = $("#username").val();
    const password = $("#psw").val();

    $.ajax({
        url: "http://localhost:8080/api/v1/login/" + username + "/" + password,
        type: "GET",
        dataType: "json",
        success: function (response) {
            if (response == true) {
                window.location = "../crud.html";
            } else {
                $("#username").val("");
                $("#psw").val("");
                alert("Credenciales incorrectas");
            }
        }
    });
}