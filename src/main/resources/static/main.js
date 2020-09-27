function registerUser(){
//    Input Values
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    data = {
        userName: document.getElementById("userName").value,
        idNumber: document.getElementById("idNumber").value,
        idType: document.getElementById("idType").value,
        firstName: document.getElementById("firstName").value,
        middleName: document.getElementById("middleName").value,
        lastName: document.getElementById("lastName").value,
        country: document.getElementById("country").value,
        phoneNumber: document.getElementById("phoneNumber").value,
        dob: document.getElementById("dob").value,
        password: password,
    }

//   Clear Error Labels
    document.getElementById("userNameError").innerHTML = "";
    document.getElementById("idNumberError").innerHTML = "";
    document.getElementById("idTypeError").innerHTML = "";
    document.getElementById("firstNameError").innerHTML = "";
    document.getElementById("middleNameError").innerHTML = "";
    document.getElementById("lastNameError").innerHTML = "";
    document.getElementById("countryError").innerHTML = "";
    document.getElementById("phoneNumberError").innerHTML = "";
    document.getElementById("dobError").innerHTML = "";
    document.getElementById("passwordError").innerHTML = "";
    document.getElementById("confirmPasswordError").innerHTML = "";

    postData('/register', data).then((response) => {
//        If successful, go to the success page
        if (response.code === 1012){
        //        Print the popup, redirect to success response
            window.open('/success', '_self');
        } else if (response.code === 1101 ){
            alert(response.message + " " + response.description);
        } else if (response.code === 400) {
//            Validation errors
            parseErrors(response.error);
        }
    });
}

function parseErrors(errors){
    errors.forEach((error) => {
        let fieldError = document.getElementById(error.field + "Error");
        fieldError.innerHTML = error.defaultMessage;
    })
}

function loginUser(){
    let username = document.getElementById("userName").value;
    let password = document.getElementById("password").value;
    console.log(username);

    data = {
        userName: username,
        password: password
    }

    postData('/login', data).then((response) => {
        if (response.code === 1012){
//        Print the popup, redirect to success response
            window.open('/success', '_self');
        } else if (response.code === 400) {
            parseErrors(response.error);
        }
    });
}

async function postData(url, data = {}){
    const response = await fetch(url, {
        method: 'POST',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
              'Content-Type': 'application/json'
              // 'Content-Type': 'application/x-www-form-urlencoded',
            },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify(data) // body data type must match "Content-Type" header
      });
    return response.json(); // parses JSON response into native JavaScript objects
}