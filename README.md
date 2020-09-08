# ARSWLab4

## Cinema Book System II

### Descripción 
En este ejercicio se va a construír el componente CinemaRESTAPI, el cual permita gestionar la reserva de boletos de una prestigiosa compañia de cine. La idea de este API es ofrecer un medio estandarizado e 'independiente de la plataforma' para que las herramientas que se desarrollen a futuro para la compañía puedan gestionar los boletos de forma centralizada. El siguiente, es el diagrama de componentes que corresponde a las decisiones arquitectónicas planteadas al inicio del proyecto:

![Componentes](https://github.com/mariahv9/ARSWLab4/blob/master/CINEMA%20II/img/CompDiag.png)

Donde se definió que:
* El componente CinemaRESTAPI debe resolver los servicios de su interfaz a través de un componente de servicios, el cual -a su vez- estará asociado con un componente que provea el esquema de persistencia. Es decir, se quiere un bajo acoplamiento entre el API, la implementación de los servicios, y el esquema de persistencia usado por los mismos.

Del anterior diagrama de componentes (de alto nivel), se desprendió el siguiente diseño detallado, cuando se decidió que el API estará implementado usando el esquema de inyección de dependencias de Spring (el cual requiere aplicar el principio de Inversión de Dependencias), la extensión SpringMVC para definir los servicios REST, y SpringBoot para la configurar la aplicación:

![Clases](https://github.com/mariahv9/ARSWLab4/blob/master/CINEMA%20II/img/ClassDiagram.png)

## Part I

1. Integre al proyecto base suministrado los Beans desarrollados en el Ejercicio Anterior. Sólo copie las clases, NO los archivos de configuración. Rectifique que se tenga correctamente configurado el esquema de inyección de dependencias con las anotaciones @Service y @Autowired.
2. Modifique el bean de persistecia 'InMemoryCinemaPersistence' para que por defecto se inicialice con al menos otras 2 salas de cine, y al menos 2 funciones asociadas a cada una.
3. Configure su aplicación para que ofrezca el recurso "/cinema", de manera que cuando se le haga una petición GET, retorne -en formato jSON- el conjunto de todos los cines. Para esto:
* Modifique la clase CinemaAPIController teniendo en cuenta el ejemplo de controlador REST hecho con SpringMVC/SpringBoot. (ver code 1)
* Haga que en esta misma clase se inyecte el bean de tipo CinemaServices (al cual, a su vez, se le inyectarán sus dependencias de persistencia y de filtrado de películas).
* De ser necesario modifique el método getAllCinemas(), de manera que utilice la persistencia previamente inyectada y retorne todos los cines registrados.
4. Verifique el funcionamiento de a aplicación lanzando la aplicación con maven (ver code 2). Y luego enviando una petición GET a:  [LocalHost](http://localhost:8080/cinemas). Rectifique que, como respuesta, se obtenga un objeto jSON con una lista que contenga el detalle de los cines suministados por defecto.

![cinemas](https://github.com/mariahv9/ARSWLab4/blob/master/CINEMA%20II/img/cinemas.png)

5. Modifique el controlador para que ahora, acepte peticiones GET al recurso /cinemas/{name}, el cual retorne usando una representación jSON todas las funciones del cine cuyo nombre sea {name}. Si no existe dicho cine, se debe responder con el código de error HTTP 404. Para esto, revise en la documentación de Spring, sección 22.3.2, el uso de @PathVariable. De nuevo, verifique que al hacer una petición GET -por ejemplo- a recurso [Localhost](http://localhost:8080/cinemas/cinemaY) , se obtenga en formato jSON el conjunto de funciones asociadas al cine 'cinemaY' (ajuste esto a los nombres de cine usados en el punto 2).

![cinepolis](https://github.com/mariahv9/ARSWLab4/blob/master/CINEMA%20II/img/cinePolis.png)
![cinemax](https://github.com/mariahv9/ARSWLab4/blob/master/CINEMA%20II/img/cinemaX.png)
![nocinema](https://github.com/mariahv9/ARSWLab4/blob/master/CINEMA%20II/img/notcinema.png)

6. Modifique el controlador para que ahora, acepte peticiones GET al recurso /cinemas/{name}/{date}, el cual retorne usando una representación jSON una lista de funciones asociadas al cine cuyo nombre es {name} y cuya fecha sea {date}, para mayor facilidad se seguirá manejando el formato "yyyy-MM-dd". De nuevo, si no existen dichas funciones, se debe responder con el código de error HTTP 404.

![date](https://github.com/mariahv9/ARSWLab4/blob/master/CINEMA%20II/img/date.png)
![nodate](https://github.com/mariahv9/ARSWLab4/blob/master/CINEMA%20II/img/nodate.png)

7. Modifique el controlador para que ahora, acepte peticiones GET al recurso /cinemas/{name}/{date}/{moviename}, el cual retorne usando una representación jSON sólo UNA función, en este caso es necesario detallar además de la fecha, la hora exacta de la función de la forma "yyyy-MM-dd HH:mm". Si no existe dicha función, se debe responder con el código de error HTTP 404.

![movie](https://github.com/mariahv9/ARSWLab4/blob/master/CINEMA%20II/img/movie.png)
![nomovie](https://github.com/mariahv9/ARSWLab4/blob/master/CINEMA%20II/img/nomovie.png)

## Part II

1. Agregue el manejo de peticiones POST (creación de nuevas funciones), de manera que un cliente http pueda registrar una nueva función a un determinado cine haciendo una petición POST al recurso ‘/cinemas/{name}’, y enviando como contenido de la petición todo el detalle de dicho recurso a través de un documento jSON. Para esto, tenga en cuenta el siguiente ejemplo, que considera -por consistencia con el protocolo HTTP- el manejo de códigos de estados HTTP (en caso de éxito o error): (ver code 3)
2. Para probar que el recurso ‘cinemas’ acepta e interpreta correctamente las peticiones POST, use el comando curl de Unix. Este comando tiene como parámetro el tipo de contenido manejado (en este caso jSON), y el ‘cuerpo del mensaje’ que irá con la petición, lo cual en este caso debe ser un documento jSON equivalente a la clase Cliente (donde en lugar de {ObjetoJSON}, se usará un objeto jSON correspondiente a una nueva función: (ver code 4) Con lo anterior, registre un nueva función (para 'diseñar' un objeto jSON, puede usar [esta herramienta](https://jsoneditoronline.org/)): Nota: puede basarse en el formato jSON mostrado en el navegador al consultar una función con el método GET.

![post](https://github.com/mariahv9/ARSWLab4/blob/master/CINEMA%20II/img/post.png)

3. Teniendo en cuenta el nombre del cine, la fecha y hora de la función y el nombre de la película, verifique que el mismo se pueda obtener mediante una petición GET al recurso '/cinemas/{name}/{date}/{moviename}' correspondiente.
4. Agregue soporte al verbo PUT para los recursos de la forma '/cinemas/{name}', de manera que sea posible actualizar una función determinada, el servidor se encarga de encontrar la función correspondiente y actualizarla o crearla.

```
$ curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080/cinemas -d '{cinePolis}'
```

![put](https://github.com/mariahv9/ARSWLab4/blob/master/CINEMA%20II/img/put.png)

## Construido con 

* [Java 8](https://www.java.com/es/about/whatis_java.jsp)
* [Maven](https://maven.apache.org/) - Manejador de dependencias

## Reviewed

Diego Alfonso Prieto Torres

## Authors

* **Alan Yesid Marin Mendez** - [PurpleBooth](https://github.com/Elan-MarMEn)
* **Maria Fernanda Hernandez Vargas** - [PurpleBooth](https://github.com/mariahv9)


Students of Systems Engineering of Escuela Colombiana de Ingenieria Julio Garavito 
