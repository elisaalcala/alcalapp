<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Applicacion para la gestion de tareas e incidencias">
    <meta name="author" content="elisaalcala">
    <title>Alcalapp</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
    <style>
    body {
        background: linear-gradient(to right, #175a69, #4b8997e1);
        display: flex;
        justify-content: center;
        align-items: flex-start; /* Centrado vertical un poco más arriba */
        height: 100vh; /* Hace que el body ocupe toda la pantalla */
        margin: 0;
    }
    
    .container {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-grow: 1;
        height: 100%;
    }
    
    .card {
        width: 400px; /* El tamaño del cuadrado */
        height: 400px; /* Mantiene el div cuadrado */
        padding: 0;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        box-shadow: 0px 10px 30px rgba(0, 0, 0, 0.25);
        border-radius: 8px;
        background-color: white;
    }
    
    .card-body {
        width: 100%;
    }
    
    .text-center h1 {
        margin-bottom: 20px;
    }
    
    .form-group {
        width: 90%; /* Ajustar el ancho de los inputs */
        margin-bottom: 15px;
    }
    
    .custom-control-label {
        margin-left: 5px;
    }
    
    .btn {
        width: 100%;
    }
    .bg-base-light {
        background-color: #196273;
        color: white;
    }
    .bg-base-light:hover,
    .bg-base-light:active,
    .bg-base-light:focus-visible {
        background-color: #ebd9c3 !important;
        color: #867858 !important;
        box-shadow: none !important;
        border-color: none !important;
    }
    .bg-base-light:focus {
        box-shadow: none;
    }
    </style>
</head>
<body class="bg-gradient-primary">
    <div class="container login">
        <div class="row justify-content-center">
            <div class="card shadow-lg">
                <div class="card-body">
                    <div class="col">
                        <div class="p-4 pt-3">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Bienvenido a AlcalApp!</h1>
                            </div>
                            <form class="user" id="loginForm">
                                <div class="form-group">
                                    <input class="form-control form-control-user" id="username" placeholder="Usuario">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-user" id="password" placeholder="Contraseña">
                                </div>
                                <div class="form-group">
                                    <div class="custom-control custom-checkbox small">
                                        <input type="checkbox" class="custom-control-input" id="customCheck">
                                        <label class="custom-control-label" for="customCheck">Recuerdame</label>
                                    </div>
                                </div>
                                <c:if test="${not empty param.error}">
                                    <div class="alert alert-danger" id="errorMessage">Error en el usuario o la contraseña</div>
                                </c:if>
                                <button class="btn bg-base-light btn-user btn-block" id="loginButton">
                                    Iniciar sesion
                                </button>
                            </form>
                            <hr>
                            <div class="text-center">
                                <p class="small m-0">¿Has olvidado tu contraseña?</p>
                                <p class="small ">Contacta con tu superior para recuperar la cuenta</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            document.getElementById("loginButton").addEventListener("click", function(event) {
                event.preventDefault();
                var username = document.getElementById('username').value;
                var password = document.getElementById('password').value;
                const params = new URLSearchParams();
                params.append('username', username);
                params.append('password', password);
                
                fetch("/login", {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: params.toString()
                })
                .then(response => {
                    if (!response.ok) {
                        var errorMessageElement = document.getElementById("errorMessage");
                        errorMessageElement.classList.remove("d-none");
                        throw new Error('Error al logearte');
                    }
                    window.location.href = "/dailywork";
                })
                .catch(error => {
                    console.error('Error:', error);
                    
                });
            });
        });
    </script>
</body>
</html>
