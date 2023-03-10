<%--
  Created by IntelliJ IDEA.
  User: fifak
  Date: 12/19/2022
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


    <link
            href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css"
            rel="stylesheet"
    />
    <link
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;900&display=swap"
            rel="stylesheet"
    />
    <meta
            name="viewport"
            content="width=device-width,initial-scale=1,maximum-scale=1"
    />
    <style>
        body {
            font-family: "Inter", sans-serif;
        }
    </style>
    <script
            src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.js"
            defer
    ></script>
    <script src="${pageContext.request.contextPath}/view/signUp/js/confirmEmail.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/view/jquery_libs/jquery.min.js" type="text/javascript"></script>

    <title>SignUp</title>

</head>
<body class="min-h-screen bg-gray-100 text-gray-900 flex justify-center">


<div
        class="max-w-screen-xl m-0 sm:m-20 bg-white shadow sm:rounded-lg flex justify-center flex-1"
>
    <div class="lg:w-1/2 xl:w-5/12 p-6 sm:p-12">

        <div class="mt-12 flex flex-col items-center">
            <h1 class="text-2xl xl:text-3xl font-extrabold">
                Sign up for ChatWeb
            </h1>
            <div class="w-full flex-1 mt-8">


                <div class="my-12 border-b text-center">
                    <div
                            class="leading-none px-2 inline-block text-sm text-gray-600 tracking-wide font-medium bg-white transform translate-y-1/2"
                    >
                        Confirm Email
                    </div>
                </div>
                <form action="${pageContext.request.contextPath}/confirmEmail" method="post" id="frmconfirmEmail">
                    <input
                            class="w-full px-8 py-4 rounded-lg font-medium bg-gray-100 border border-gray-200 placeholder-gray-500 text-sm focus:outline-none focus:border-gray-400 focus:bg-white mt-5"
                            type="text"
                            placeholder="Confirm Code send in your Email"
                            name="cfemailCode"
                            id="cfemailCode"
                    />
                    <input
                            name="btnConfirmCode"
                            id="btnConfirmCode"
                            onclick="validate()"
                            type="button"
                            value="Confirm Email"
                            class="mt-5 tracking-wide font-semibold bg-indigo-500 text-gray-100 w-full py-4 rounded-lg hover:bg-indigo-700 transition-all duration-300 ease-in-out flex items-center justify-center focus:shadow-outline focus:outline-none"
                    />

                    <p class="mt-6 text-xs text-gray-600 text-center">
                        I agree to abide by chatWeb's
                        <a href="#" class="border-b border-gray-500 border-dotted">
                            Terms of Service
                        </a>
                        and its
                        <a href="#" class="border-b border-gray-500 border-dotted">
                            Privacy Policy
                        </a>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <div class="flex-1 bg-indigo-100 text-center hidden lg:flex">
        <div
                class="m-12 xl:m-16 w-full bg-contain bg-center bg-no-repeat"
                style="background-image: url('https://storage.googleapis.com/devitary-image-host.appspot.com/15848031292911696601-undraw_designer_life_w96d.svg');"
        ></div>
    </div>

</div>
<script>
    function validate() {
        var cfcodebyUser = document.getElementById("cfemailCode").value;

        if (/^[0-9a-zA-Z]{6}$/.test(cfcodebyUser)) {
            $.ajax({
                url: '/Chat_Web_war_exploded/confirmEmail',
                type: 'POST',
                data: {
                    cfcodebyUser: cfcodebyUser
                },

                success: function (data) {
                    if (data.toString() === "false") {
                        alert("Wrong confirm Code!!!");
                    } else {
                        window.location = "/Chat_Web_war_exploded/" + data;
                    }
                },
            });
        } else {
            alert("Wrong Format Code!!");
        }
    }


</script>
</body>

</html>
