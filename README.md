<div align="center">
<img src="https://raw.githubusercontent.com/JavierCarranza0101/imagenes-dresscodehost/main/img_petboutique/PetBoutiquelogo1.jpg" alt="logo petboutique" width="300" ## />
</div>

# 🐾 Pet Boutique-App Movil

Pet Boutique es una aplicación móvil desarrollada con Android Studio como parte de un proyecto académico para la Tecnicatura en Desarrollo Web y Aplicaciones Digitales del Instituto Superior Politécnico Córdoba (ISPC). Esta app permite a los usuarios explorar, comprar productos para mascotas y gestionar su perfil, ofreciendo una experiencia completa de compra desde dispositivos móviles Android.

## Tabla de Contenidos
- [Descripción](#descripción-general)
- [Funcionalidades](#funcionalidades-principales)
- [Instalación](#instalación)
- [Tecnologia](#tecnologías-utilizadas)
- [Video de Funcionamiento](#video-del-funcionamiento)
- [Enlaces Útiles](#enlaces-útiles)
- [Autores](#autores)
- [Mención a la Institución](#mención-a-la-institución)
- [Licencia](#licencia)

## 🌐 Descripción General
Esta aplicación simula una tienda online especializada en productos para mascotas, permitiendo la navegación por catálogos, historial de compras, detalles de pedidos y gestión del perfil de usuario. La app fue diseñada para proporcionar una experiencia optimizada para dispositivos móviles, complementando la versión web y fomentando el aprendizaje de desarrollo móvil con Android Java, integración con API REST (Django), Cloudinary y bases de datos relacionales.

## 🚀 Funcionalidades Principales
✅ Inicio de sesión y registro: Los usuarios pueden autenticarse en la app mediante credenciales, con validación segura.

✅ Catálogo de productos: Explora una amplia gama de productos para mascotas, organizados por categorías y con posibilidad de ver imágenes, precios y descripciones.

✅ Detalle de productos: Visualiza información detallada de cada producto seleccionado.

✅ Carrito de compras y checkout: Agrega productos al carrito y simula el proceso de compra, incluyendo opciones de pago y resumen del pedido.

✅ Pasarela de pago: Integración de un sistema de pago simulado para completar compras de manera segura y efectiva.

✅ Cupones de descuento: Gestión y aplicación de cupones válidos de compra para obtener beneficios.

✅ Historial de compras: Accede a los pedidos realizados.

✅ Visualización de productos por pedido: Consulta qué productos incluye cada compra anterior, con cantidad, nombre, precio e imagen.

✅ Dashboard del usuario: Visualización rápida del resumen de actividad del usuario (pedidos recientes, cupones vigentes y recomendaciónes).

✅ Perfil de usuario: Permite ver y editar información personal como nombre, email y foto de perfil (usando Cloudinary).

✅ Formulario de contacto: Los usuarios registrados pueden enviar sugerencias o consultas desde la app con sus datos precargados.


## 💻 Instrucciones de Instalación y Ejecución Local
**1.** Clonar el repositorio
```
git clone https://github.com/tu-usuario/pet-boutique.git
cd pet-boutique
```
**2.** Backend (Django)
```
cd backend/PetBoutique
```
**3.** Crear entorno virtual e instalar dependencias
```
python -m venv miEntorno
source miEntorno/bin/activate  # En Windows: miEntorno\Scripts\activate

# Instalar dependencias
pip install -r requirements.txt
```
**4.** Configurar base de datos
```
# Crear una base de datos MySQL llamada "petboutique"
# Importar el archivo SQL proporcionado (por ejemplo desde MySQL Workbench)

# Configurar variables en settings.py si es necesario (credenciales de BD, Cloudinary, etc.)

# Migrar base de datos y crear superusuario
python manage.py migrate
python manage.py createsuperuser
```
**5.** Ejecutar servidor backend
```
python manage.py runserver
```
**6.** Frontend (Angular)
```
cd frontend/pet-boutique
```
**7.** Instalar dependencias y ejecutar servidor
```
npm install

# Ejecutar servidor de desarrollo
ng serve
```
**8.** Abrir el proyecto con Android Studio
**9.** Sincronizar dependencias de Gradle y configurar un dispositivo (emulador o físico)
**10.** Modificar las URLs de conexión a la API
   Usar http://10.0.2.2:8000 si se utiliza un emulador de Android.

   Usar http://<tu-ip-local>:8000 si se conecta desde un dispositivo físico.

   Alternativamente, usar ngrok para exponer el servidor y obtener una URL pública.

**10.** Ejecutar la aplicación desde Android Studio

## 🛠️ Tecnologías Utilizadas
- Android Studio – IDE principal de desarrollo móvil.

- Java.

- XML.

- Django 4.2.

- MySQL Workbench.

- Cloudinary.

- Python (virtualenv).

## Video del Funcionamiento

[Video de Funcionamiento](./Frontend/VideoAppMobile.mp4)

## 🔗 Enlaces Útiles
- [Repositorio web](https://github.com/ISPC-2024-GrupoEstudio/GrupoEstudio-2024)
- [Repositorio móvil](https://github.com/ISPC-2024-GrupoEstudio/GrupoEstudio-Mobile2024)
- [Django Docs](https://www.djangoproject.com/)
- [Android Studio](https://developer.android.com/studio?hl=es-419)
- [Cloudinary](https://cloudinary.com/)
- [MySQL Workbench](https://www.mysql.com/products/workbench/)

## Autores

1. **Florencia Castelucci**  
   GitHub: [FlorCastelucci](https://github.com/FlorCastelucci) - Scrum master

2. **Florencia Noel Carrillo**  
   GitHub: [FlorenciaCarrillo](https://github.com/FlorenciaCarrillo) - Desarrollador

3. **Milena Nicole Gimenez**  
   GitHub: [MilenaGimenez](https://github.com/MilenaGimenez) - Desarrollador

4. **Valentina Angeletti**  
   GitHub: [ValeAngeletti](https://github.com/ValeAngeletti) - Desarrollador

## 🏫 Mención a la Institución
Proyecto desarrollado en el marco de la Tecnicatura en Desarrollo Web y Aplicaciones Digitales del
Instituto Superior Politécnico Córdoba (ISPC) — Año 2023-2025

## 📄 Licencia
Este proyecto fue desarrollado con fines educativos.
No está destinado a uso comercial ni producción.
Todos los derechos reservados © 2023-2025.
