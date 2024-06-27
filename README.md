# Seek Java Challenge



---

### Configuración y Ejecución Local con Docker Compose

#### Requisitos Previos

1. **Java Development Kit (JDK)**: Asegúrate de tener instalado JDK 17.

2. **Apache Maven**: Necesitarás Apache Maven 3.x.x instalado para gestionar las dependencias y compilar el proyecto.
   
3. **Docker y Docker Compose**: Instala Docker y Docker Compose en tu máquina. Docker Compose se utilizará para levantar un contenedor MySQL para la base de datos de la aplicación.

#### Pasos para Configurar y Ejecutar Localmente

1. **Clonar el Repositorio**

   Clona el repositorio de tu aplicación desde GitHub (o desde donde tengas alojado el código) a tu máquina local:
   ```bash
   https://github.com/yvan-lopez-it/seekjavachallenge
   ```

2. **Configurar la Base de Datos MySQL con Docker Compose**

  Ejecuta el archivo Docker Compose `docker-compose.yml` para levantar el contenedor de MySQL:
   ```bash
   docker-compose up -d
   ```

   Esto iniciará un servidor MySQL en el puerto 3306 de tu máquina local.

3. **Configurar Spring Boot para la Conexión a la Base de Datos**

   Asegúrate de que las propiedades de conexión a la base de datos en `application.properties` (ubicado en `src/main/resources`) estén configuradas correctamente para conectar con el contenedor de MySQL:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/dbseekjavachallenge
   spring.datasource.username=mysql
   spring.datasource.password=mysql
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

   Estas propiedades deberían coincidir con las configuraciones del contenedor MySQL que has especificado en `docker-compose.yml`.

4. **Compilar y Ejecutar con Maven**

   Desde la raíz de tu proyecto, ejecuta los siguientes comandos Maven para compilar y ejecutar la aplicación:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   Esto compilará tu aplicación, ejecutará las pruebas (si las tienes configuradas) y luego iniciará tu aplicación Spring Boot, estableciendo automáticamente la conexión con la base de datos MySQL que está corriendo en Docker.

5. **Crear Usuario y Autenticarse para Obtener JWT**

Para interactuar con los endpoints de la API protegidos por Spring Security y JWT, sigue estos pasos:

- `/api/auth/register` Crear Usuario: Implementa un endpoint o método para crear un usuario en tu aplicación.
-  `/api/auth/login` Autenticarse y Obtener Token: Implementa la lógica para autenticar al usuario y obtener un token JWT válido. Puedes usar herramientas como Postman o curl para realizar solicitudes HTTP POST al endpoint de autenticación.

5. **Acceder a la Aplicación**

Una vez que tengas el token JWT, úsalo como encabezado Authorization (Bearer Token) en las solicitudes a los endpoints protegidos de tu API. Abre tu navegador web o usa herramientas como Postman para acceder a:
   http://localhost:8071 e interactuar con la aplicación localmente.
   
6.  **Acceder a la Aplicación en la nube**
    https://seekjavachallenge-heroku-c4106d411853.herokuapp.com para interactuar con tu aplicación localmente.
7. **Acceder a la documentación Swagger**
   - Local http://localhost:8071/swagger-ui/index.html#/
   - Cloud https://seekjavachallenge-heroku-c4106d411853.herokuapp.com/swagger-ui/index.html#/
    

### Notas Adicionales

- **Postman**: En la carpeta Info del proyecto encontrarás 2 colecciones postman: Auth y API.

- **Configuración Avanzada**: Puedes ajustar las configuraciones de Docker Compose y Spring Boot según las necesidades específicas de tu aplicación y entorno de desarrollo.


