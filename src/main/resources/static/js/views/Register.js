export default function Register() {

    //language=HTML
    return `
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8"/>
            <title>Register</title>
        </head>
        <body>
        <h1>Register here!</h1>
        
        <form id="register-form">
            <label for="username">Username</label>
            <input id="username" name="username" type="text"/>
            <label for="email">Email</label>
            <input id="email" name="email" type="email">
            <label for="password">Password</label>
            <input id="password" name="password" type="password"/>
            <input id="register-btn" type="button" value="Register"/>
        </form>
        </body>
        </html>
    `;
}

//TODO #1: Begin at FEA-5-C.4 and ensure js/router.js is correct.

//TODO #2: Complete Register.js and add export function RegisterEvent() below.

//TODO #3: Complete router.js and fill fields to properly load the page.