# Backend API TDDSportsApp - [75.10] Tecnicas de diseño - Curso Montaldo 2c2023

---

<br>
<p align="center">
  <a href="#/"><img src="https://raw.githubusercontent.com/MiguelV5/MiguelV5/main/misc/logofiubatransparent_partialwhite.png" height="180"/></a>
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

## Ejecucion local (docker; Solo API y Postgres)

En el dir root del proyecto ejecutar:
```bash
docker compose -f docker-compose.yaml up -d 
```

## Ejecucion local (dev & testing)

En el dir root del proyecto ejecutar:
```bash
docker compose -f test-docker-compose.yaml up -d
```

## Implementación de API REST y Despliegue

La implementación de la API se realizó por medio del framework [Spring](https://spring.io/) para java.

Para la documentación de la misma se utiliza [springdoc-openapi](https://springdoc.org/). 
Se puede visualizar accediendo **[aquí](https://grupo-4.2023.tecnicasdedisenio.com.ar:34001/swagger-ui.html)**.

La API está desplegada por medio de [Gitlab CI/CD](https://docs.gitlab.com/ee/ci/)
en un servidor proporcionado por la catedra para ejecutar el gitlab-runner y los docker containers.

## Tests

Para correr los tests singularmente, posicionarse en el dir (...) y correr el comando:
(...)
(...)
(...)
(TODO)
