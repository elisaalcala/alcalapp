<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Aplicación para la gestión de tareas e incidencias">
    <meta name="author" content="elisaalcala">
    <title>Alcalapp - Error</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
        body {
            background: linear-gradient(to right, #175a69, #4b8997e1);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }

        .error-container {
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: left;
            color: white;
            background-color: rgba(0, 0, 0, 0.2);
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.25);
            max-width: 925px;
            width: 100%;
        }

        .icon-container {
            font-size: 5rem;
            margin-right: 40px;
            color: white;
        }

        .text-container h1 {
            font-size: 2rem;
            font-weight: bold;
            margin-bottom: 0.5rem;
        }

        .text-container h3 {
            font-size: 1.2rem;
            margin-bottom: 1rem;
            opacity: 0.8;
        }

        .contact-support {
            font-size: 1rem;
            color: #ffffffc7;
            text-decoration: none;
            font-weight: bold;
        }

        .contact-support:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="icon-container">
            <i class="fas fa-exclamation-circle"></i>
        </div>
        <div class="text-container">
            <h1>Ups, algo ha fallado</h1>
            <h3>Se ha producido un error inesperado. Por favor, contacte con el equipo de soporte.</h3>
            <a href="mailto:soporte@alcalapp.com" class="contact-support">soporte@alcalapp.com</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
