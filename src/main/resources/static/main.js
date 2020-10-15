function setup(){
    getUsers();
}

function setPerm(perm){
    let permElement = document.getElementById("addPerm");
    permElement.innerHTML = perm.id;
}

function removePerm(perm){
    let permElement = document.getElementById("remPerm");
    permElement.innerHTML = perm.id;
}

function pushPerm(){
    let user = document.getElementById("selectedUser").innerHTML;
    let permission = document.getElementById("addPerm").innerHTML;
    postData('/admin/updatepermissions', {
            user: user,
            permissionName: permission,
        })
        .then((response) => {
            if (response.code === 200){
                console.log(response.description);
                let selectedUser = document.getElementById("selectedUser").innerHTML;
                _getPerms(selectedUser);
            }
        });
}

function pullPerm(){
    let user = document.getElementById("selectedUser").innerHTML;
    let permission = document.getElementById("remPerm").innerHTML;
    console.log(permission);
    postData('/admin/removepermissions', {
            user: user,
            permissionName: permission,
        })
        .then((response) => {
            if (response.code === 200){
                console.log(response.description);
                let selectedUser = document.getElementById("selectedUser").innerHTML;
                _getPerms(selectedUser);
            }
        });
}


function getUsers(){
// Get the user elements
    getRegisteredUsers();
    getAdmins();
}

function getAdmins(){
    let admins = document.getElementById("adminList");
// Get the user data from the server
    getData('/admin/admins').then((response) => {
        console.log(response);
        if (response.code === 200){
// If validated, add the user data to the elements
            let htmlString = "";
            response.administrators.sort().forEach((administrator) => {
                htmlString += '<div onclick="getPermissions(this)" id="' + administrator.userName + '">' + administrator.userName + '</div>';
                console.log(administrator.userName);
            })
            admins.innerHTML = htmlString;
        }
    });
}

function getRegisteredUsers(){
    let users = document.getElementById("userList");
// Get the user data from the server
    getData('/admin/users').then((response) => {
        console.log(response);
        if (response.code === 200){
// If validated, add the user data to the elements
            let htmlString = "";
            response.registeredUsers.sort().forEach((registeredUser) => {
                htmlString += '<div id="' + registeredUser.userName + '">' + registeredUser.userName + '</div>';
                console.log(registeredUser.userName);
            })
            users.innerHTML = htmlString;
        }
    });
}

function getPermissions(e){
    console.log(e.id);
    let selectedUser = document.getElementById("selectedUser");
    selectedUser.innerHTML = e.id;
    _getPerms(e.id);
}

function _getPerms(name){
    let perms = document.getElementById("permissionsList");
// Get the perm data from the server
    getData('/admin/permissions?name=' + name).then((response) => {
        console.log(response);
        if (response.code === 200){
// If validated, add the user data to the elements
            let htmlString = "";
            response.permissions.sort().forEach((permission) => {
                htmlString += '<div onclick="removePerm(this)" id="' + permission.name + '">' + permission.name + '</div>';
            })
            perms.innerHTML = htmlString;
        }
    });
}

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
                console.log(key);
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
            if (response.description === 'user'){
                window.open('/success', '_self');
            } else if (response.description === 'admin'){
                window.open('/admin', '_self');
            }
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

async function getData(url){
    const response = await fetch(url, {
        method: 'GET',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
              'Content-Type': 'application/json'
            },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
      });
    return response.json(); // parses JSON response into native JavaScript objects
}