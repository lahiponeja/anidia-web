# Anidia

## Arranque en local

Para arrancar el Doker solo hay que levantar las imagenes mediante `docker-compose`.

```bash
anidia-web/$ cd docker
anidia-web/docker$ docker-compose up
```

### Primer arranque

Es bastante probable que en la primera ejecución Liferay devuelva un error al iniciarse. Esto es debido a que en la primera ejecución la base de datos tarda demasiado en iniciarse y Liferay intenta conectarse antes de que esté disponible.

Para solucionarlo pararemos el docker y lo volveremos a arrancar. En la segunda ejecución Liferay detectará el MySQL y generará todos los datos necesarios para comenzar.

Esto puede llevar un tiempo (3 minutos). Al terminar la carga deberíamos ver una línea de estilo:

```bash
portal_1  | 07-Sep-2020 11:38:13.534 INFO [main] org.apache.catalina.startup.Catalina.start Server startup in [210,989] milliseconds
```