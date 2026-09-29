# 🏋️‍♂️ Zona Fit - Sistema de Gestión de Gimnasio

Aplicación de consola (CLI) desarrollada en Java con **Spring Boot** y **Spring Data JPA** para la administración básica de los clientes de un gimnasio. 

## 🚀 Tecnologías Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Data JPA** (Hibernate)
- **MySQL**
- **Lombok**

## 🛠️ Configuración y Ejecución

### 1. Base de Datos
El framework está configurado para **no** crear la estructura de la base de datos automáticamente (`ddl-auto=none`). Debes ejecutar el script incluido [schema.sql](zona_fit/schema.sql) en tu gestor de bases de datos MySQL para crear la base de datos `zona_fit_db` y su tabla `cliente` antes de correr el proyecto.

### 2. Variables de Entorno (Seguridad de Credenciales) 🔐
Por buenas prácticas de seguridad, las credenciales de la base de datos no están codificadas en el archivo `application.properties`. Antes de ejecutar la aplicación, debes configurar las siguientes **variables de entorno** en tu sistema o en tu IDE (como IntelliJ IDEA):

- `DB_USER`: Tu usuario de MySQL (si no lo defines, toma `root` por defecto).
- `DB_PASSWORD`: Tu contraseña de MySQL (**Obligatorio**).

**Ejemplo para ejecutarlo desde consola (Windows PowerShell):**
```powershell
$env:DB_USER="root"
$env:DB_PASSWORD="tu_contraseña_aqui"
.\mvnw spring-boot:run
```

**Ejemplo para ejecutarlo desde consola (Linux/Mac):**
```bash
export DB_USER=root
export DB_PASSWORD=tu_contraseña_aqui
./mvnw spring-boot:run
```

*Nota: Si utilizas IntelliJ IDEA, puedes agregar estas variables en la sección "Environment Variables" de la configuración de ejecución (Run/Debug Configurations).*

---

## 💻 Funcionalidades y Flujo de Uso

Al iniciar la aplicación, la consola presentará un ciclo infinito con el menú principal que permanecerá activo hasta que decidas salir. El sistema cuenta con validación `try-catch` para evitar caídas en caso de que el usuario introduzca texto en lugar de números.

### Menú Principal
```text
--- Menú de Clientes ---
1. Listar clientes
2. Buscar cliente por ID
3. Agregar cliente
4. Actualizar cliente
5. Eliminar cliente
6. Salir
```

A continuación, una demostración de cada funcionalidad operando dentro del ciclo `while`:

### 📋 1. Listar Clientes
Muestra todos los miembros actualmente registrados en la base de datos.
```text
Ingresa opción: 1
Cliente(id=1, nombre=Carlos, apellido=Lopez, membresia=150)
Cliente(id=2, nombre=Maria, apellido=Gomez, membresia=200)
```

### 🔍 2. Buscar Cliente por ID
Permite encontrar a un cliente específico mediante su identificador.
```text
Ingresa opción: 2
Ingresa id del cliente a buscar: 1
Cliente(id=1, nombre=Carlos, apellido=Lopez, membresia=150)
```
*(Si el ID no existe, el sistema notificará: `Cliente no encontrado`)*.

### ➕ 3. Agregar Cliente
Inicia el flujo para registrar un nuevo miembro al gimnasio, pidiendo sus datos paso a paso.
```text
Ingresa opción: 3
Ingresa nombre: 
Ana
Ingresa apellido: 
Martinez
Ingresa membresia: 
350
Usuario agregado exitosamente
```

### ✏️ 4. Actualizar Cliente
Permite modificar los datos de un cliente existente. Primero valida que el ID exista, y de ser así, solicita los datos a sobreescribir.
```text
Ingresa opción: 4
Ingrese el id del cliente a actualizar: 1
Ingresa nombre: 
Carlos Roberto
Ingresa apellido: 
Lopez
Ingresa membresia: 
180
Usuario actualizado exitosamente
```

### ❌ 5. Eliminar Cliente
Elimina permanentemente el registro del sistema de la base de datos.
```text
Ingresa opción: 5
Ingrese el id del usuario a eliminar: 2
Cliente eliminado
```

### 🚪 6. Salir
Rompe el ciclo del menú, finaliza el proceso de Spring Boot y cierra la conexión a la base de datos de forma segura.
```text
Ingresa opción: 6
Saliendo del sistema
App finalizada
```
