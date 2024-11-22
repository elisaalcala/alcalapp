<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<nav class="navbar navbar-expand bg-gradient-navbar topbar static-top shadow ">
    
    
    <a class="navbar-brand d-flex align-items-center justify-content-center nav-link" href="/dailywork">
        <div class="sidebar-brand-icon">
          <i class="fas fa-solid fa-rocket"></i>
          <span class=" mx-2">Alcalapp</span>
        </div>
    </a>

    <div class="margin-left-15" id="breadcrumb-container"></div>

    <input type="hidden" id="currentPage" value="${page}" />

    <ul class="navbar-nav ml-auto">

        <!-- Nav Item - Questions -->
        <li class="nav-item dropdown no-arrow mx-1 p-0">
            <a class="nav-link dropdown-toggle link-text dropdown-toggle-noncontent" href="#" id="questionDropdown" role="button"
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fa fa-question"></i>
            </a>
            <!-- Dropdown - Questions -->
            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in p-0"
                aria-labelledby="questionDropdown">
                <h6 class="dropdown-header text-center  color-primary">
                    Asistencia
                </h6>
                <div class="dropdown-divider m-0"></div>
                <div class="px-3 py-2 text-center" style="font-size: 0.875rem;">
                    ¿Tienes dudas o necesitas asistencia? Consulta nuestra guía <br> en 
                    <strong class="color-primary">www.alcalapp_guide.com</strong> <br> O escríbenos a <br>
                    <strong class="color-primary">alcalapp_services@alcalapp.com</strong>
                </div>
            </div>

        </li>

      <!-- Nav Item - Calendar -->
      <li class="nav-item dropdown no-arrow mx-1 p-0">
          <a class="nav-link dropdown-toggle link-text dropdown-toggle-noncontent" href="#" id="messagesDropdown" role="button"
              data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-calendar fa-fw"></i>
          </a>
          <!-- Dropdown - Messages -->
          <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in p-0" aria-labelledby="messagesDropdown">
              <div id="calendar"></div>
          </div>
      </li>
      <sec:authorize access="hasRole('ADMIN')">
        
            <!-- Nav Item - Questions -->
            <li class="nav-item mx-1 p-0">
                <a class="nav-link link-text" href="/gestion" id="gestionAdmin" role="button">
                    <i class="fa fa-gear"></i>
                </a>

            </li>
        </sec:authorize>
      <li class="topbar-divider pl-1"></li>
      <!-- Nav Item - User Information -->
      <li class="nav-item dropdown no-arrow">
          <a class="nav-link dropdown-toggle link-text" href="#"  id="userDropdown" role="button"
              data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <span class="mr-2 d-none d-lg-inline text-gray-600 small ">${employee.userEmployee}</span>
              <i class="fas fa-circle-user "></i>
          </a>
          <!-- Dropdown - User Information -->
          <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in "
              aria-labelledby="userDropdown">
              <a class="dropdown-item nav-colorlink" href="/profile">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Mi perfil
              </a>
              <sec:authorize access="hasRole('MANAGER')">
                <a class="dropdown-item nav-colorlink" href="/teams/${team.idTeam}">
                    <i class="fas fa-gear fa-sm fa-fw mr-2 text-gray-400"></i>
                    Gestion Equipo
                    </a>
                </sec:authorize>
              <div class="dropdown-divider"></div>
              <button class="dropdown-item nav-colorlink text-danger" data-bs-toggle="modal" data-bs-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Cerrar Sesión
              </button>
          </div>
      </li>

    </ul>

  </nav>