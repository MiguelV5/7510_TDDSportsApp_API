# Backend API TDDSportsApp - [75.10] Tecnicas de diseño - Curso Montaldo 2c2023

---

<br>
<p align="center">
  <a href="#/"><img src="https://raw.githubusercontent.com/MiguelV5/MiguelV5/main/misc/logofiubatransparent_partialwhite.png" width="50%"/></a>
</p>
<br>

---

<br>
<p align="center">
<font size="+3">
GRUPO 4
</font>
<br>
<font size="+2">
INTEGRANTES
</font>
<br>
<font size="+1">
<a href="https://github.com/MartinUgarte">Martin Ugarte</a>, 
<a href="https://github.com/bonshot">Facundo Argerich</a>, 
<a href="https://github.com/rplatini">Rocio Platini</a>, 
<a href="https://github.com/DiegoCivi">Diego Civini</a>, 
<a href="https://github.com/MiguelV5">Miguel Vasquez</a>
</font>
</p>
<br>


---

## Ejecucion local (docker; API y Postgres)

En el dir root del proyecto ejecutar:
```bash
docker compose up -d 
```

## Implementación de API REST y Despliegue

La implementación de la API se realizó por medio del framework [Spring](https://spring.io/) para java.

Para la documentación de la misma se utiliza [springdoc-openapi](https://springdoc.org/). 

Tambien se utilizó el framework [Spring Security](https://docs.spring.io/spring-security/reference/index.html) para creación de filtro JWT para autenticación y autorización de requests, y [JPA + Hibernate](https://docs.spring.io/spring-framework/reference/data-access/orm/jpa.html) para acceso a la base de datos y ORM.

La API fue desplegada por medio de [Gitlab CI/CD](https://docs.gitlab.com/ee/ci/)
en un servidor proporcionado por la catedra para ejecutar el gitlab-runner y los docker containers.
