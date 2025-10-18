Proyecto Concesionario
======================

Descripción
-----------
Aplicación Spring Boot mínima para gestionar un concesionario con entidades principales:
- Categoria
- Producto
- Detalle (accesorios o detalles relacionados a un producto)

Estructura
----------
- `/categorias` - CRUD básico para categorías
- `/productos` - CRUD para productos (un producto puede tener una `categoria`)
- `/detalles` - CRUD para detalles (un detalle pertenece a un `producto`)

Requisitos
----------
- JDK 17+
- Maven (el proyecto incluye `mvnw` para usar el wrapper)

Cómo ejecutar
--------------
Desde la carpeta del proyecto (donde está el `mvnw`):

```powershell
# Compilar y empaquetar (sin tests)
.\mvnw.cmd -DskipTests package

# Ejecutar la aplicación
.\mvnw.cmd spring-boot:run
```

Endpoints y ejemplos de uso (Postman)
------------------------------------
Headers: `Content-Type: application/json`

1) Crear categoría
- POST /categorias
Body:
{
  "nombre": "SUV"
}

2) Crear producto (sin categoría)
- POST /productos
Body:
{
  "marca": "Toyota",
  "modelo": "RAV4",
  "anio": 2022,
  "precio": 32000.0,
  "stock": 8
}

3) Crear producto con categoría (referenciando por id)
- POST /productos
Body:
{
  "marca": "Honda",
  "modelo": "CR-V",
  "anio": 2023,
  "precio": 35000.0,
  "stock": 5,
  "categoria": { "id": 1 }
}

4) Crear detalle para producto
- POST /detalles
Body:
{
  "nombre": "Kit de audio premium",
  "precio": 499.99,
  "producto": { "id": 2 }
}

Notas
-----
- El controlador valida que las referencias (categoria/producto) existan; si no, devuelve 400 con un mensaje.
- Actualmente los endpoints exponen entidades JPA directamente. Para producción se recomienda usar DTOs y validaciones `@Valid`.

Pruebas
-------
Para ejecutar pruebas:

```powershell
.\mvnw.cmd test
```

Contacto
--------
- Proyecto generado/ajustado localmente por el desarrollador.
