# VinylsApp - Proyecto Kotlin para Android Studio

VinylsApp es una aplicación móvil desarrollada en Kotlin y ejecutada en Android Studio como proyecto de Maestría.

## Requisitos previos

Asegúrate de tener los siguientes requisitos instalados en tu sistema:

- [Android Studio](https://developer.android.com/studio)
- Kotlin SDK configurado en Android Studio

## Configuración e instalación

### 1. Clona el repositorio

```bash
git clone https://github.com/salchichongallo/miso-app-vinilos.git
cd miso-app-vinilos
```

### 2. Configuración de API

Puedes configurar la URL de la API modificando la constante `API_BASE_URL` ubicada en `app/src/main/java/com/example/vinylsapp/AppConstants.kt`.
Por defecto apunta a una API en Heroku, pero puedes actualizarla para con efectos de pruebas.

Asegúrate de que el de la constante finalice en `/` (slash). Ejemplo:
```
const val API_BASE_URL = "http://10.0.2.2:3000/"
```

### 3. Configura y ejecuta el proyecto en Android Studio
1. Abre Android Studio.
2. Selecciona Open y carga el directorio del proyecto clonado.
3. Asegúrate de que el dispositivo de prueba o emulador esté configurado.
4. Haz clic en Run para compilar y ejecutar la aplicación en el dispositivo.


### 4. Navega en la aplicación.
Una vez que la app esté ejecutándose, podrás navegar y verificar la conexión con la API en tiempo real.

## Ejecución de pruebas automatizadas E2E

Las pruebas automatizadas E2E (End-to-End) en este proyecto permiten verificar la funcionalidad completa de la aplicación sin necesidad de una API activa, ya que los datos utilizados están mockeados.

### Requisitos previos

Asegúrate de que tienes un emulador de Android configurado o un dispositivo físico conectado a tu máquina para ejecutar las pruebas.

### 1. Ejecuta las pruebas E2E en Android Studio

1. Abre Android Studio y carga el proyecto.
2. En la barra lateral, navega a **Project** > **app** > **src** > **androidTest** > **java** > **com** > **example**  > **vinylsapp**. Aquí encontrarás las pruebas E2E.
3. Haz clic derecho sobre el paquete o archivo de prueba que quieras ejecutar y selecciona **Run 'PruebaSeleccionada'** para correr pruebas específicas, o **Run all tests** para ejecutar todas las pruebas E2E.




