function registerUser(){
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

//    Validate Password
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

//    Input Values
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

    if (password !== confirmPassword){
        document.getElementById("confirmPasswordError").innerHTML = "Passwords do not match!";
    } else {
        postData('/register', data).then((response) => {
//            If successful, go to the success page
            if (response.code === 1012){
            //        Print the popup, redirect to login page
                window.open('/login', '_self');
            } else if (response.code === 400) {
                document.getElementById("idNumberError").innerHTML = response.description;
            } else if (response.code === 420){
//                Validation errors
                parseErrors(response.error);
            }
            console.log(response);
        });
    }
}

function validateId(){
    document.getElementById("idNumberError").innerHTML = "";
    let idNumber = document.getElementById("idNumber").value;
//    Send the value to the API, get a response
    postData('IPRSValidation', {
        idNumber: idNumber,
    })
    .then((response) => {
        if (response.code !== 200){
            document.getElementById("idNumberError").innerHTML = response.description;
        } else {
            for (const key in response.data){
                document.getElementById(key).value = response.data[key];
            }
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
//    Clear Errors
    document.getElementById("userNameError").innerHTML = "";
    document.getElementById("passwordError").innerHTML = "";

    let username = document.getElementById("userName").value;
    let password = document.getElementById("password").value;

    data = {
        userName: username,
        password: password
    }

    postData('/login', data).then((response) => {
        console.log(response);
        if (response.code === 1012){
//        Print the popup, redirect to success response
            window.open('/success', '_self');
        } else if (response.code === 400) {
            parseErrors(response.error);
        } else if (response.code === 1013) {
            document.getElementById("passwordError").innerHTML = response.description;
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
            },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify(data) // body data type must match "Content-Type" header
      });
    return response.json(); // parses JSON response into native JavaScript objects
}