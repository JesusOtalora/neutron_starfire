# neutron_starfire

## Instrucciones de ejecución:

1. Descargar el repositorio
2. Abrir la consola del sistema operativo y cambiar la ubicacion a la carpeta del repositorio (En windows: abrir el programa "Simbolo del sistema", luego escribir "cd C:/<<ruta_de_la_carpeta>>" )
3. Escribir el comando "mvn package" y oprimir la tecla Enter, se generará una carpeta llamada taget con un archivo .jar en su interior
4. Ejecutar el comando "cd target" y oprimir la tecla Enter, y luego se escribe el comando "java -jar <<nombre_del_archivo>>.jar"
5. Para generar las pruebas podemos usar el programa Postman, enviando las url: http://localhost:8080/topsecret para nivel 2, http://localhost:8080/topsecret_split/<<nombre_de_satelite>>/ y http://localhost:8080/topsecret_split/ para el nivel 3
