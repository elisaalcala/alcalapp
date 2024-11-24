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
            align-items: flex-start;
            height: 100vh;
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
            width: 100%;
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

        /* Estilo de los campos con error */
        .input-error {
            border-color: #bd0e1f;
        }

        .error-message {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 15px;
        }

    </style>
</head>
<body class="bg-gradient-primary">
    <div style="display: flex; flex-direction: column;" class="container login">
        <div id="login_box" class="row justify-content-center">
            <div class="card shadow-lg">
                <div class="card-body">
                    <div class="col">
                        <div class="p-4 pt-3">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Bienvenido a AlcalApp!</h1>
                            </div>                            
                            <form action="/login" method="post">
                                <div class="form-group">
                                    <label for="username">Usuario:</label>
                                    <!-- Agregar clase 'input-error' si hay un error -->
                                    <input class="form-control form-control-user ${not empty error ? 'input-error' : ''}" 
                                           type="text" id="username" name="username" required>
                                </div>

                                <div class="form-group">
                                    <label for="password">Contraseña:</label>
                                    <!-- Agregar clase 'input-error' si hay un error -->
                                    <input class="form-control form-control-user ${not empty error ? 'input-error' : ''}" 
                                           type="password" id="password" name="password" required>
                                </div>

                                <c:if test="${not empty error}">
                                    <div class="error-message">
                                        <strong>Error:</strong> ${error}
                                    </div>
                                </c:if>

                                <div class="form-group">
                                    <div class="custom-control custom-checkbox small">
                                        <input type="checkbox" class="custom-control-input" id="customCheck">
                                        <label class="custom-control-label" for="customCheck">Recuerdame</label>
                                    </div>
                                </div>

                                <button class="btn bg-base-light btn-user btn-block" type="submit" id="loginButton">Iniciar Sesión</button>
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
</body>
</html>
