<<<<<<< HEAD

# ForoHub - API de Foro de Consultas Técnicas

Este proyecto es una API REST para un foro de consultas técnicas, desarrollado con **Spring Boot**, **Spring Security**, **JWT**, y documentado con **OpenAPI/Swagger**. Permite crear, listar, actualizar y eliminar tópicos, usuarios y respuestas, además de autenticarse mediante token JWT.

---

## 🔧 Tecnologías Usadas

- Java 17
- Spring Boot 3
- Spring Security
- JWT (Json Web Token)
- JPA/Hibernate
- MySQL
- OpenAPI (Springdoc)
- Lombok

---

## 📁 Estructura del Proyecto

- `Controller`: Controladores REST
- `Service`: Lógica de negocio
- `Repository`: Acceso a datos con JPA
- `Dto`: Objetos de transferencia de datos
- `Model`: Entidades del dominio
- `Security`: Seguridad y autenticación JWT

---

## 🧪 Endpoints

### 🔐 Autenticación

#### `POST /login`

Autentica un usuario y devuelve un JWT.

**Body:**
```json
{
  "email": "juan@example.com",
  "password": "123456"
}
````

**Response:**

```json
{
  "token": "Bearer eyJhbGciOiJIUzI1..."
}
```

---

### 📚 Tópicos (`/topicos`)

#### `GET /topicos`

Lista todos los tópicos.

#### `GET /topicos/{id}`

Obtiene un tópico por ID.

#### `POST /topicos`

**Body:**

```json
{
  "titulo": "Error con Hibernate",
  "mensaje": "Me da LazyInitializationException",
  "autorId": 1,
  "cursoId": 1
}
```

#### `PUT /topicos/{id}`

**Body:**

```json
{
  "titulo": "Error Lazy Init",
  "mensaje": "¿Cómo evito este error?",
  "autorId": 1,
  "cursoId": 1
}
```

#### `DELETE /topicos/{id}`

---

### 👤 Usuarios (`/usuarios`)

#### `GET /usuarios`

Lista todos los usuarios.

#### `GET /usuarios/{id}`

Detalle de un usuario.

#### `POST /usuarios`

**Body:**

```json
{
  "nombre": "María Gómez",
  "correoElectronico": "maria@example.com",
  "contrasena": "123456"
}
```

#### `PUT /usuarios/{id}`

**Body:**

```json
{
  "nombre": "María G. Rodríguez",
  "correoElectronico": "maria@example.com"
}
```

#### `DELETE /usuarios/{id}`

---

### 💬 Respuestas (`/respuestas`)

#### `GET /respuestas`

Lista todas las respuestas.

#### `POST /respuestas`

**Body:**

```json
{
  "mensaje": "Puedes usar FetchType.EAGER",
  "topicoId": 1,
  "autorId": 1
}
```

#### `PUT /respuestas/{id}`

#### `DELETE /respuestas/{id}`

---

## 🛡️ Seguridad JWT

Todas las rutas están protegidas salvo `/login` y `/swagger-ui/index.html`.

Agregar el token JWT en el header:

```
Authorization: Bearer eyJhbGciOi...
```

---

## 🧾 Documentación Swagger

Documentación interactiva disponible en:

```
http://localhost:8080/swagger-ui/index.html
```

---
---

## 🧠 Consideraciones Técnicas

* Se usa `BCryptPasswordEncoder` para encriptar contraseñas.
* Las relaciones entre entidades están gestionadas con JPA.
* Swagger configurado para soportar autenticación JWT.

---

## 👨‍💻 Autor

Desarrollado como parte del desafío final de Alura + Oracle ONE Back-End.

---
=======
# forohub-alura-challenge
>>>>>>> 906f5f9a884aac1eb4fcedca1f0ed198989c08fb
