# Anidia

## Arranque en local

Para arrancar el Doker solo hay que levantar las imagenes mediante `docker-compose`.

```bash
anidia-web/$ cd docker
anidia-web/docker$ docker-compose up
```

Después de eso, hay que acceder a [http://localhost:8080](http://localhost:8080).

### Primer arranque

Es bastante probable que en la primera ejecución Liferay devuelva un error al iniciarse. Esto es debido a que en la primera ejecución la base de datos tarda demasiado en iniciarse y Liferay intenta conectarse antes de que esté disponible.

Para solucionarlo pararemos el docker y lo volveremos a arrancar. En la segunda ejecución Liferay detectará el MySQL y generará todos los datos necesarios para comenzar.

Esto puede llevar un tiempo (3 minutos). Al terminar la carga deberíamos ver una línea de estilo:

```bash
portal_1  | 07-Sep-2020 11:38:13.534 INFO [main] org.apache.catalina.startup.Catalina.start Server startup in [210,989] milliseconds
```

## Desarrollo del Theme

El código fuente del theme de Anidia se encuentra en `theme/anidia-theme` y sobre esa carpeta se debería trabajar.

Lo primero que hay que hacer es copiar el fichero liferay-theme.json.local

Este fichero ya incluye la configuración para hacer los despliegues contra el Liferay del docker.

```bash
anidia-web/theme/anidia-theme$ cp liferay-theme.json.local liferay-theme.json
```

Una vez hecho esto se puede comenzar a desplegar cambios en el theme.

Lo más simple es ejecutar `npx gulp deploy` lo cual compila el theme y lo lanza al docker. Pero se pueden lanzar otras órdenes:

* `npx gulp build` construye el .war per no lo despliega.
* `npx gulp overwrite` permite sobreescribir ficheros del theme básico para poder incorporar nuestros elementos.
* `npx gulp watch` se encarga de desplegar ante cualquier cambio en los ficheros del theme.

### Extension del theme

Para poder incorporar elementos al theme hay que ejecutar `npx gulp overwrite`.

Al ejecutarlo te dará la opción de navegar por el theme basico y seleccionar el fichero.