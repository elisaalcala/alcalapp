<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Alcalapp</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/2.0.3/css/dataTables.bootstrap5.css">
    <link href="/css/timeline.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <link rel="stylesheet" href="/css/style.css">
    
    <%@ include file="scripts.jsp" %>
    <script src="/js/ticket.js"></script>
    <script src="/js/timeline.min.js"></script>
</head>
<body>
    <div class="container">
        <!-- Topbar -->
        <%@ include file="navbar.jsp" %>

        <main class="d-flex">
            <!-- Sidebar -->
            <%@ include file="sidebar.jsp" %>

            <section class="container-fluid">
                <input type="hidden" id="ticketId" value="${ticket.idTicket}" />
                <input type="hidden" id="employeeUserHidden" value="${employee.userEmployee}" />
                <input type="hidden" id="employeeNameAssignToMe" value="${employee.employeeName}" />
                <input type="hidden" id="employeeLastNameAssignToMe" value="${employee.employeeLastName}" />
                <input type="hidden" id="initialDateHidden" value="${ticket.initialDate}" />
                <input type="hidden" id="modifyDateHidden" value="${ticket.modifyDate}" />
                <input type="hidden" id="finishDateHidden" value="${ticket.finishDate}" />

                <div class="card custom-card shadow">
                    <div class="card-header d-flex align-items-center justify-content-between bg-base">
                        <h6 class="m-0 font-weight-bold text-uppercase text-white">
                            ${ticket.nameTicket} - ${ticket.titleTicket}
                        </h6>
                        <div class="div-col">
                            <div class="ticket-button m-left">
                                <button type="button" class="btn bg-base-light" id="cloneButton" data-bs-toggle="modal" data-bs-target="#cloneModal">
                                    <i class="fa-solid fa-clone"></i> Clonar
                                </button>
                            </div>
                            <div class="ticket-button m-left">
                                <c:if test="${(ticket.teamNameAssign == employee.nameTeam) || role == 'ADMIN'}">
                                    <button type="button" class="btn bg-base-light" id="editButton" data-bs-toggle="modal" data-bs-target="#editModal">
                                        <i class="fa-solid fa-pen-to-square"></i> Editar
                                    </button>
                                </c:if>
                            </div>
                            <c:if test="${(ticket.teamNameAssign == employee.nameTeam && role == 'MANAGER') || role == 'ADMIN'}">
                                <div class="ticket-button m-left">
                                    <button type="button" class="btn btn-danger" id="deleteButton" data-bs-toggle="modal" data-bs-target="#deleteModal">
                                        <i class="fa-solid fa-trash"></i> Eliminar
                                    </button>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="card-body">
                        <div class="m-4">
                            <div class="row">
                                <div class="col">
                                    <div class="ticket-info">
                                        <div class="ticket-detail-heading color-cyan">Detalles</div>
                                        <div class="row">
                                            <dt class="col">Prioridad:</dt>
                                            <dd class="col" id="ticketPriority">${ticket.priorityTicket}</dd>
                                        </div>
                                        <div class="row">
                                            <dt class="col">Entorno prueba:</dt>
                                            <dd class="col" id="ticketEnvironment">${ticket.environmentTicket}</dd>
                                        </div>
                                        <div class="row">
                                            <dt class="col">Estado actual:</dt>
                                            <c:if test="${ticket.teamNameAssign == team.nameTeam || role == 'ADMIN'}">
                                                <div class="ticket-button dropdown m-left" style="width: 50%">
                                                    <button class="btn bg-base-light dropdown-toggle translucent_button" type="button" id="changeStatusButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        ${ticket.statusTicket}
                                                    </button>
                                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                        <h6 class="collapse-header">Cambiar a:</h6>
                                                        <c:forEach items="${allStatus}" var="status">
                                                            <a class="collapse-item ticket-item" id="${status}" href="#">${status}</a>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <c:if test="${ticket.teamNameAssign != team.nameTeam && role != 'ADMIN'}">
                                                <dd class="col" id="ticketStatus">${ticket.statusTicket}</dd>
                                            </c:if>
                                        </div>

                                    </div>
                                </div>
                                <div class="col">
                                    <div class="ticket-info">
                                        <div class="ticket-detail-heading color-cyan">Personal</div>
                                        <div class="row">
                                            <dt class="col">Empleado Asignado:</dt>
                                            <c:choose>
                                                <c:when test="${empty ticket.employeeUserAssign}">
                                                    <dd class="col" id="employeeUserAssign">
                                                        <c:if test="${(ticket.teamNameAssign == employee.nameTeam) || role == 'ADMIN'}">
                                                            <div class="ticket-button" style="width: 50%;">
                                                                <button type="button" class="btn bg-base-light translucent_button" id="assignButton" data-bs-toggle="modal" data-bs-target="#assignModal">
                                                                    Asignar
                                                                    <i style="cursor: pointer; margin-left: 4px;" class="fa-solid fa-pencil"></i>
                                                                </button>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${employee.nameTeam != ticket.teamNameAssign && role != 'ADMIN'}">
                                                            Sin asignar
                                                        </c:if>
                                                    </dd>
                                                </c:when>
                                                <c:otherwise>
                                                    <dd class="col" id="employeeUserAssign">
                                                        ${ticket.employeeUserAssign}
                                                        <c:if test="${(ticket.teamNameAssign == employee.nameTeam) || role == 'ADMIN'}">
                                                            <a><i style="cursor: pointer; margin-left: 4px;" class="fa-solid fa-pencil" data-bs-toggle="modal" data-bs-target="#assignModal"></i></a>
                                                        </c:if>
                                                        <c:if test="${ticket.employeeUserAssign == employee.userEmployee}">
                                                            <br>
                                                            <a href="#" id="unassignFromMeLink" class="color-cyan">
                                                                <i class="fa-solid fa-trash"></i>
                                                                Desasignarme
                                                            </a>
                                                        </c:if>
                                                    </dd>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="row">
                                            <dt class="col">Equipo Asignado:</dt>
                                            <dd class="col" id="teamNameAssign">
                                                <c:if test="${employee.nameTeam != ticket.teamNameAssign && role != 'ADMIN'}">
                                                    ${ticket.teamNameAssign}
                                                </c:if>
                                                <c:if test="${employee.nameTeam == ticket.teamNameAssign || role == 'ADMIN'}">
                                                    <button type="button" class="btn bg-base-light translucent_button" id="assignButton" data-bs-toggle="modal" data-bs-target="#moveModal">
                                                        ${ticket.teamNameAssign}
                                                        <a><i style="cursor: pointer; margin-left: 4px;" class="fa-solid fa-paper-plane"></i></a>
                                                    </button>
                                                </c:if>
                                            </dd>
                                        </div>

                                        <div class="row">
                                            <dt class="col">Empleado creador:</dt>
                                            <dd class="col" id="employeeUserCreation">${ticket.employeeUserCreation}</dd>
                                        </div>
                                        <div class="row">
                                            <dt class="col">Equipo del creador:</dt>
                                            <dd class="col" id="teamNameCreation">${ticket.teamNameCreation}</dd>
                                        </div>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="ticket-info">
                                        <div class="ticket-detail-heading color-cyan">Fechas</div>
                                        <div class="row">
                                            <dt class="col">Creación:</dt>
                                            <dd class="col" id="initialDate"></dd>
                                        </div>
                                        <div class="row">
                                            <dt class="col">Última modificación:</dt>
                                            <dd class="col" id="modifyDate"></dd>
                                        </div>
                                        <div class="row">
                                            <dt class="col">Finalización:</dt>
                                            <dd class="col" id="finishDate"></dd>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="d-flex">
                                <div class="width60">
                                    <div class="columnas-izquierda d-block h-100">
                                        <div class="ticket-info h-100">
                                            <div class="ticket-detail-heading color-cyan">Descripción</div>
                                            <div class="row h-100">
                                                <textarea class="form-control mb-4 mt-3" id="descriptionTicket" readonly>${ticket.descriptionTicket}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="width40">
                                    <div class="columnas-derecha flex-wrap-nowrap margin-30">
                                        <div>
                                            <div class="ticket-detail-heading color-cyan margin-30">Historial</div>
                                            <div class="timeline margin-timeline">
                                                <div class="timeline__wrap">
                                                    <div class="timeline__items">
                                                        <c:forEach items="${historial}" var="mensaje">
                                                            <div class="timeline__item">
                                                                <div class="timeline__content">
                                                                    ${mensaje.text}
                                                                    <div class="fw-bold small">${mensaje.dateRecord}</div>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Footer -->
                <%@ include file="footer.jsp" %>
            </section>

            <!-- Modals -->
            <%@ include file="modalEdit.jsp" %>
            <%@ include file="modalAssign.jsp" %>
            <%@ include file="modalCreate.jsp" %>
            <%@ include file="modalLogout.jsp" %>
            <%@ include file="modalCreateRelease.jsp" %>
            <%@ include file="modalMove.jsp" %>
            <%@ include file="modalClone.jsp" %>
            <%@ include file="modalDelete.jsp" %>
        </main>
    </div>
</body>
</html>
