Welcome to TriviaVet
====================

Este es un proyecto utilizado para enseñar/aprender algunas herramientas en el marco de la asignatura "Análisis y Diseño de Algoritmos" del Departamento de Computación de la UNRC.

Getting Started
---------------

1. Forkear
2. Clonar el repositorio en sus máquinas
3. Configurar las base de datos
4. Correr el servidor web

#### Forkear
La forma más fácil de forkear es hacerlo desde github, presionando en el botón Fork en el margen derecho superior de la pantalla

#### Clonar
para clonar el repositorio se puede usar el siguiente comando
```
git clone git@github.com:se-unrc/triviavet.git
```
esto creara una carpeta en sus máquinas llamada triviavet y enlazada a través de `origin` a sus repositorio en github

#### Configurar la base de datos
Vamos a utilizar mysql como el motor de base de datos, para esto configurar el archivo `{basedir}/src/resources/database.properties` con sus correspondientes valores
```
development.driver=com.mysql.jdbc.Driver
development.username=<user>
development.password=<passwd>
development.url=jdbc:mysql://localhost/trivia

development.test.driver=com.mysql.jdbc.Driver
development.test.username=<user>
development.test.password=<passwd>
development.test.url=jdbc:mysql://localhost/trivia_test
```

#### Correr el servidor web
utilizar el script `./run.sh`

#### Usar curl para crear requests al server

A continuación algunos ejemplo de como se puede usar curl para consultar al servidor

* List Users
```
curl http://localhost:4567/users
```

* List User
```
curl http://localhost:4567/users/1
```

* Create User
```
curl -X POST http://localhost:4567/users \
  -H 'content-type: application/json' \
  -d '{"firstName":"John", "lastName": "Doe", email: "john@doe.com"}'
```

* Update User
```
curl -X PUT http://localhost:4567/users/1 \
  -H 'content-type: application/json' \
  -d '{"firstName":"Michael"}'
```

# Licence

This project is licensed under the MIT License - see the LICENSE.md file for details

# How to create a PR

```sh
git checkout -b develop

git  push  origin  develop
```
 ^    ^      ^       ^
git comand remote branch_name


copy url that console gave you and paste on some browser

create your PR and check that you are creating PR to your forked repository
