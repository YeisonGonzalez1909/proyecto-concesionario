# Pruebas de API con Postman - Categorías

## 1. Crear una categoría
**Método:** POST  
**URL:** `http://localhost:8080/categorias`  
**Headers:** `Content-Type: application/json`  
**Body (JSON):**
```json
{
    "nombre": "Sedán"
}
```

---

## 2. Crear otra categoría
**Método:** POST  
**URL:** `http://localhost:8080/categorias`  
**Headers:** `Content-Type: application/json`  
**Body (JSON):**
```json
{
    "nombre": "SUV"
}
```

---

## 3. Crear una tercera categoría
**Método:** POST  
**URL:** `http://localhost:8080/categorias`  
**Headers:** `Content-Type: application/json`  
**Body (JSON):**
```json
{
    "nombre": "Deportivo"
}
```

---

## 4. Obtener todas las categorías
**Método:** GET  
**URL:** `http://localhost:8080/categorias`

**Respuesta esperada:**
```json
[
    {
        "id": 1,
        "nombre": "Sedán",
        "productos": []
    },
    {
        "id": 2,
        "nombre": "SUV",
        "productos": []
    },
    {
        "id": 3,
        "nombre": "Deportivo",
        "productos": []
    }
]
```

---

## Pruebas complementarias con Productos

### Crear producto con categoría
**Método:** POST  
**URL:** `http://localhost:8080/productos`  
**Headers:** `Content-Type: application/json`  
**Body (JSON):**
```json
{
    "marca": "Toyota",
    "modelo": "Corolla",
    "anio": 2022,
    "precio": 20000,
    "stock": 5,
    "categoria": {
        "id": 1
    }
}
```

### Verificar categoría con productos
**Método:** GET  
**URL:** `http://localhost:8080/categorias`

Ahora deberías ver la categoría con su lista de productos asociados.
