# VinylsApp - Proyecto Kotlin para Android Studio

VinylsApp es una aplicación móvil desarrollada en Kotlin y ejecutada en Android Studio como proyecto de Maestría. Antes de iniciar la aplicación, es necesario tener en funcionamiento un contenedor Docker que ejecute la API del backend de manera local.

## Requisitos previos

Asegúrate de tener los siguientes requisitos instalados en tu sistema:

- [Android Studio](https://developer.android.com/studio)
- [Docker](https://www.docker.com/get-started)
- Kotlin SDK configurado en Android Studio

## Configuración e instalación

### 1. Clona el repositorio

```bash
git clone https://github.com/salchichongallo/miso-app-vinilos.git
cd miso-app-vinilos
```

### 2. Levanta tu api en Docker de forma local.
Es importante tener la API ejecutándose en Docker antes de lanzar la aplicación. Sigue estos pasos para iniciarla:

1. Asegúrate de que Docker esté en ejecución.
2. Confirma que la API esté ejecutándose en http://localhost:3000/

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




