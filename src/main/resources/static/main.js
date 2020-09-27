function registerUser(){
    let username = document.getElementById("userName").value;
    let idNumber = document.getElementById("idNumber").value;
    let idType = document.getElementById("idType").value;
    let firstName = document.getElementById("firstName").value;
    let middleName = document.getElementById("middleName").value;
    let lastName = document.getElementById("lastName").value;
    let country = document.getElementById("country").value;
    let phoneNumber = document.getElementById("phoneNumber").value;
    let dob = document.getElementById("dob").value;
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    data = {
        userName: username,
        idNumber: idNumber,
        idType: idType,
        firstName: firstName,
        middleName: middleName,
        lastName: lastName,
        country: country,
        phoneNumber: phoneNumber,
        dob: dob,
        password: password,
    }

    postData('/register', data).then((response) => {
//        If successful, go to the success page
        if (response.code === 1012){
        //        Print the popup, redirect to success response
            window.open('/success', '_self');
        } else if (response.code === 1101 ){
            alert(response.message + " " + response.description);
        } else if (response.code === 400) {
//            Validation errors
        }
        console.log(response)
    });
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
        }
        console.log(response);
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