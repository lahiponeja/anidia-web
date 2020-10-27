# Anidia

## Arranque en local

Para arrancar el Doker solo hay que levantar las imagenes mediante `docker-compose`.

```bash
anidia-web$ docker-compose up
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

## Desarrollo de fragments

Los fragments son el tipo más simple de módulos del CMS y corresponden con aquellos que son módulos de contenido puro.

El código fuente de los fragments está en  `theme/anidia-fragments` y dentro se encuentra la colección de fragmentos del proyecto en `theme/anidia-fragments/src/anidia-collection`.

Para añadir otras colecciones, fragmentos o templates de páginas se pueden seguir las instrucciones situadas en el [repositorio de Liferay](https://github.com/liferay/generator-liferay-fragments).

Además de la tarea `npm run import` hay otra manera de poder importar fragmentos al theme. Se puede ejecutar `npm run compress` y copiar el zip que se genera en `build/liferay-fragments.zip` a la carpeta `liferay/deploy/`.

### Editar un fragmento

Cada fragmento es una carpeta separada y autocontenida en la que se encuentran los ficheros:

* `index.html` se corresponde con el html del fragmento. Aunque la extension sea html no es html estático sino que es interpretado por el CMS, por lo que puede acceder a variables de configuración y más código.
* `main.js` se corresponde con el código JS necesario para inicializar el comportamiento del bloque.
* `styles.css` se corresponde con CSS propio del módulo.
* `configuration.json` se corresponde con las posibilidades de configuración dentro del CMS.
* `fragment.json` se correponde con el fichero de definición del fragmento. Normalmente no es necesario editarlo.

Más detalles sobre como desarrollar fragmentos en la documentación de Liferay:

*
* [Hacer los fragmentos configurables](https://help.liferay.com/hc/en-us/articles/360034857331-Making-a-Fragment-Configurable)

## Desarrollo de portlets

Los portlets son módulos complejos que requieren un comportamiento que se debe programar en Java.

Estos módulos se desarrollan con el Liferay Developer Studio, IDE basado en eclipse, aunque se pueden modificar sus ficheros y realizar el despliegue a docker sin necesidad de ese IDE.

Para ello, dentro de la carpeta del portlet, se ejecuta la tarea `gradle war`. Esto genera el war en la carpeta `build/libs/` dentro de la carpeta del portlet y se copia a la carpeta `liferay/deploy` como todos los war.

## Desarrollo del Asset Publisher

El Asset Publisher es un portlet estándar de Liferay que permite obtener datos de las estructuras definidas y da mucho poder al usuario.

Para desarrollar vistas nuevas en el Asset Publisher hay que tocar dos módulos de extensión de la carpeta `theme/anidia-portlets`:

* El primero está en `LRWorkspace/ext/APOVerride`. Este módulo sobreescribe la configuración del Asset Publisher y le permite incorporar nuevas opcioens de plantillas.
* El segundo es `module-jsp-override` que permite incorporar las vistas en si.

### Añadir una nueva vista al Asset Publisher

Para incorporar una nueva vista disponible en el selector del Asset Publisher lo tenemos que añadir a la interfaz de configuración (`AssetPublisherPortletInstanceConfiguration`) el nombre de la plantilla en la meta información de la propiedad `displayStyles`.

```java
	@Meta.AD(
		deflt = "table|title-list|abstracts|full-content|BasicAP|NUEVA PLANTILLA",
		description = "display-styles-key-description", name = "display-styles",
		required = false
	)
	public String[] displayStyles();
```

A continuación, en el módulo `module-jsp-override` introducimos la nueva vista.

* En `view_asset_entry_list.jsp` incorporamos código para gestionar el caso en que la plantilla sea la nueva. EL código será algo parecido a

```jsp
<c:when test='<%= Objects.equals(assetPublisherDisplayContext.getDisplayStyle(), "NUEVA PLANTILLA") %>'>
  <%
  request.setAttribute("view.jsp-assetEntryResult", assetEntryResult);
  %>
  <liferay-util:include page="/view_nueva_plantilla_template.jsp" servletContext="<%= application %>" />
</c:when>
```

* Un nuevo JSP `view_nueva_plantilla_template.jsp` que será el que englobará toda la colección de assets a rendear. AL final hará referencia a una plantilla para cada asset individual mediante el código `<liferay-util:include page="/view_nueva_plantilla_entry_template.jsp" servletContext="<%= application %>" />`.

* Un nuevo JSP `view_nueva_plantilla_entry_template.jsp` que será la que pinte el asset individual.

### Desplegar Asset Publisher

El despliegue tiene dos pasos:

* Primero apagamos Liferay.
* Luego, desplegamos el modulo `LRWorkspace/ext/APOVerride`. Esto lo hacemos ejecutando `gradle deploy` en su carpeta y copiando el .jar resultante (`LRWorkspace/bundles/osgi/marketplace/override/com.liferay.asset.publisher.web.jar`) a la carpeta `liferay/files/osgi/marketplace/override/`.
* Iniciamos Liferay, esto debería cargar nuestra nueva versión del módulo.
* Luego desplegamos el módulo `module-jsp-override`. Para esto ejecutamos gradle build en su carpeta y copiamos el jar resultante (`module-jsp-override/build/libs/com.liferay.blade.module.jsp.override-1.0.0.jar`) en la carpeta de deploys `liferay/deploy`.

Para producción (donde no se debe apagar Liferay) hay que:

* Entrar por la consola *Gogo Shell*.
* Buscar el paquete del *Liferay Asset Publisher Web* (`lb | grep "Asset Publisher"`).
* Hacer `stop` del paquete activo encontrado (puede ser el base o el ext si ya se ha desplegado antes).
* Hacer el deploy.
* Hacer `start` del paquete.

## Desarrollo de módulos Vue

Para desarrollar un portlet Vue se necesitan 2 partes, por un lado el portlet Vue que se arrastrará en el administrador y por otra parte las APIs que han de alimentar a Vue.

En los portlets Vue hay entonces 3 módulos a tener en cuenta:

* carpeta `vue`: Se trata de un módulo en el que va toda el código frontal.
* carpeta `api`: Define los modelos de datos e interfaces de la API.
* carpeta `impl`: Define la implementación final de la API, así como la configuración de swagger del servicio para la autogeneración de código.

### Despliegue de la API

Para el despliegue de la API se ha de hacer `build` de las carpetas `api` e `impl`. Esto genera dos ficheros .jar que se han de copiar a la carpeta deploy de Liferay de la manera acostumbrada.

Un ejemplo de las órdenes para hacer este despliegue sería:

```
carpeta-de-portlet > gradle build
carpeta-de-portlet > cp {api,impl}/build/libs/*.jar ../../../liferay/deploy/
```

Una vez desplegado, cada vez que generemos una nueva API tendremos que dar permisos para poder atacarla.

Para ello, en el Admin de Liferay, vamos a la sección Panel de Control > Service Access Policy.

Aquí generamos una policy propia para lo que le damos un nombre y título con el criterio que queramos. Una vez creada podemos dar acceso, en el campo `Allowed Service Signatures`, a cada clase y método a ejecutarse desde la API.

Por ejemplo, el campo si activamos el modo avanzado puede tener el valor:

```
com.liferay.sampleVue.internal.resource.v1_0.AddressResourceImpl#getAddressesPostalCodePage
com.liferay.sampleVue.internal.resource.v1_0.EstateResourceImpl#getEstatesAddressNamePage
com.liferay.sampleVue.internal.resource.v1_0.PostalCodeResourceImpl#getMunicipalityPostalCodePage
com.liferay.sampleVue.internal.resource.v1_0.PostalCodeResourceImpl#getPostalCodesPage
com.liferay.sampleVue.internal.resource.v1_0.PropertyResourceImpl#getPropertiesGatePage
com.liferay.sampleVue.internal.resource.v1_0.SampleResourceImpl
```

Estas cadenas se obtienen al intentar acceder a la API y recibir el error correspondiente:

```
<ForbiddenEntity>
<message>Access denied to com.liferay.sampleVue.internal.resource.v1_0.PostalCodeResourceImpl#getMunicipalityPostalCodePage</message>
</ForbiddenEntity>
```

A partir de aquí la API está disponible en la URL indicada. Por ejemplo: http://localhost:8080/o/sample-vue/v1.0/municipalities/52006.

## Despliegue con Gradle

Para encapsular los distintos procesos de despliegue usamos un build de gradle que tiene distintas opciones:

* Opción `-Penv`. Indica el entorno en que se despliega y cambia los metadatos de los fragmentos de manera acorde:
	* `-Penv=production` incorpora los metadatos de Anidia.
	* `-Penv=dev` u otro valor incorpora los metadatos de Liferay.com.
* Opción `-PbuildPublisher=true`. Indica si se debe desplegar los portlets correspondientes al `Asset Publisher`.
  * **OJO**: Aquí aplica lo mismo que lo visto en el punto de Desplegar el Asset Publisher, hay que apagar Liferay antes de hacerlo o bien apagar el módulo.

Así, las opciones que hay son:

* `gradle build`: Te despliega fragmentos y theme en local con metadatos de Liferay.com
* `gradle build -Penv=production`: Te despliega fragmentosy theme en local con metadatos de Anidia.
* `gradle build -PbuildPublisher=true`: Te despliega fragmentos, theme y Asset Publisher con metadatos de Liferay.com.

### Tareas individuales

Si se hace `gradle tasks` se verán las tareas individuales de build, de manera que se puede desplegar cada pieza por separado a base de esas tareas pequeñas. Por ejemplo, `gradle build.theme` despliega el theme.
