<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<ul class="navbar-nav bg-gradient-sidebar sidebar shadow" id="accordionSidebar">

    <hr class="sidebar-divider">

    <div style="display: flex; flex-direction: column; align-items: center; height: 100%;">

        

      <div>
        <div class="sidebar-heading nav-color">
          Trabajo diario
        </div>
    
        <li class="nav-item">
            <a class="nav-link nav-colorlink" id="dailywork-link" href="/dailywork">
              <i class="fa-solid fa-code-compare"></i>
              <span>En equipo</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link nav-colorlink" id="mywork-link" href="/mywork">
            <i class="fa-solid fa-briefcase"></i>
              <span>Mi trabajo</span></a>
        </li>
    
        <hr class="sidebar-divider">
    
        <div class="sidebar-heading nav-color">
          Control general
        </div>
    
        <li class="nav-item">
          <a class="nav-link nav-colorlink" id="releases-link" href="/releases">
            <i class="fas fa-fw fa-table"></i>
              <span>Releases</span></a>
        </li>
    
        <li class="nav-item">
          <a class="nav-link nav-colorlink" id="projects-link" href="/projects">
            <i class="fa-solid fa-code"></i>
                <span>Proyectos</span></a>
        </li>
    
        <li class="nav-item">
          <a class="nav-link nav-colorlink" id="tickets-link" href="/tickets">
            <i class="fas fa-solid fa-bug"></i>
              <span>Incidencias</span></a>
        </li>
    
        <hr class="sidebar-divider">
    
    
        <div class="sidebar-heading nav-color">
          Crear
        </div>
    
        <li class="nav-item">
            <button class="nav-link nav-colorlink" data-bs-toggle="modal" data-bs-target="#createModal">
                <i class="fas fa-solid fa-pen-to-square"></i>
                <span>Incidencia</span></button>
        </li>
    
        <li class="nav-item">
          <button class="nav-link nav-colorlink" data-bs-toggle="modal" data-bs-target="#createModalRelease">
                <i class="fas fa-fw fa-table"></i>
                <span>Release</span></button>
        </li>
      </div>

        <div style="margin-bottom: 1em;;">
          <hr class="sidebar-divider">

          <!-- Sidebar Toggler (Sidebar) -->
          <div class="text-center d-none d-md-inline">
            <a class="rounded-circle border-0 nav-colorlink" id="sidebarToggle" style="font-size: 1.7rem;">
                <i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
            </a>
         </div>
        </div>
      
    </div>
    

    



  </ul>