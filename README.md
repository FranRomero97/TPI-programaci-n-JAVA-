# FoodStore - Sistema de Gestión de Pedidos

Este es un sistema de gestión para una tienda de alimentos desarrollado en **Java** 
utilizando una arquitectura en capas (UI, Service, DAO, Entities) con conexión
a una base de datos **MySQL** mediante JDBC nativo. El proyecto está configurado 
para ser compatible con el estándar **JDK 25**.

---

## 📋 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:
* **Java Development Kit (JDK):** Versión 25.
* **IDE:** NetBeans (con soporte para proyectos Maven).
* **Gestor de Base de Datos:** MySQL Server y MySQL Workbench.

---

## 🗄️ 1. Cómo crear la Base de Datos 

El sistema utiliza una base de datos local en MySQL. Sigue estos pasos para inicializar la estructura:

1. Abre **MySQL Workbench** y conéctate a tu servidor local.
2. Crea una nueva pestaña de consultas (Query Tab).
3. Pega y ejecuta el script de base de datos
4. Una vez creada la base de datos mire la tabla usuarios
ahi encontrara un usuario admin creado por defecto con el cual
usted ingresara en el progra y obtendra el acceso total al programa
5. si desea ingresar como usuario para corroborar que estos no tengo
acceso total y asi no poder modificar nunca tabla de producto o de categorias
puedo hacerlo creando un nuevo usuario el cual unicamente dejara que dicho
usuario cree pedidos
6. Dentro del IDE seleccionado para ejecutar el proyecto usted debera hacerle click derecho
a la carpeta razin del poryecto y hacer click en clean and build para que se instalen todas las depencias necesarias
7. una vez realizado el paso 6 ustedes debera buscar la carpeta "com.mycompany.foodstore" el archivo dentro
de esa usted le hara click derecho y le hara click a al opcion que dice run file

```sql
-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS foodstore;

-- Usar la base de datos
USE foodstore;

-- =========================
-- TABLA CATEGORIAS
-- =========================
CREATE TABLE IF NOT EXISTS categorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- TABLA PRODUCTOS
-- =========================
CREATE TABLE IF NOT EXISTS productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    descripcion TEXT,
    stock INT NOT NULL,
    imagen VARCHAR(255),
    disponible BOOLEAN DEFAULT TRUE,
    categoria_id BIGINT,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_productos_categorias
        FOREIGN KEY (categoria_id)
        REFERENCES categorias(id)
        ON DELETE SET NULL
);

-- =========================
-- TABLA USUARIOS
-- =========================
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    mail VARCHAR(100),
    celular VARCHAR(50),
    contraseña VARCHAR(100),
    rol VARCHAR(20),
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ADMIN POR DEFECTO
INSERT INTO usuarios
(nombre, apellido, mail, celular, contraseña, rol, eliminado, created_at)
VALUES
(
'Admin',
'Sistema',
'admin@foodstore.com',
'000',
'admin123',
'ADMIN',
false,
NOW()
);

-- =========================
-- TABLA PEDIDOS
-- =========================

CREATE TABLE IF NOT EXISTS pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    estado VARCHAR(30),
    forma_pago VARCHAR(30),
    total DOUBLE,
    usuario_id BIGINT,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_pedidos_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE CASCADE
);

-- =========================
-- VERIFICAR TABLAS
-- =========================
SELECT * FROM categorias;
SELECT * FROM usuarios;
SELECT * FROM productos;
SELECT * FROM pedidos;
