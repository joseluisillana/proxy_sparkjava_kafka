# proxy_sparkjava_kafka
Proyecto Java, API que hace de proxy para alimentar un Kafka, con peticiones desde JMeter.

<snippet>
  <content><![CDATA[
# ${1:Project Name}
Proyecto Java, API que hace de proxy para alimentar un Kafka, con peticiones desde JMeter.
## Instalación
En la carpeta de recursos, se encuentran los materiales extra necesarios:

1. `\Binarios`: Los empaquetados originales de Kafka y JMeter.
2. `\JMeter`: El propio JMeter para realizar pruebas de carga.
3. `\Kafka`: El propio Kafka, en las versiones 2.10 y 2.11, a gusto del consumidor.
4. `\Proyectos_JMeter`: Proyectos y plantillas para realizar llamadas al API (pasarela a Kafka).

## Uso
El stack se compone por un lado de JMeter como fuente origen de los eventos y llamadas al API y Kafka para guardarlos en una cola. Entre éstos dos componentes se encuentra un API que simplemente hará de pasarela del mensaje, utilizando el API de Kafka para generar un productor que alimentará la cola de Kafka.
Para trabajar con el stack tenemos que hacer lo siguiente:
1. Arrancar ZooKeeper: En la carpeta de recursos, dentro del directorio de Kafka, en la versión que decidamos, ejecutar `./bin/zookeeper-server-start.sh config/zookeeper.properties`.
2. Arrancar Kafka: En la carpeta de recursos, dentro del directorio de Kafka, en la versión que decidamos, ejecutar `./bin/kafka-server-start.sh config/server.properties`.
3. Ejecutar el proyecto (Jetty Runner)
4. Realizar llamadas al API, por ejemplo: (POST)`http://{{host}}:8080/proxy_sparkjava_kafka/sendTestData/topicPruebas` con el mensaje en el body.

## Contibuye
1. Forkealo !
2. Crea tu propio branch: `git checkout -b my-new-feature`
3. Comitea tus cambios: `git commit -am 'Add some feature'`
4. Haz el puch a tu rama: `git push origin my-new-feature`
5. Lanza una pull request ¡¡¡ :D

## Historia
Me hacía falta en el curro y ya que lo hice pues... aquí está

## Enlaces
- http://kafka.apache.org/ .
- http://jmeter.apache.org/ .
- http://sparkjava.com/ .

## Licencia
Licencia Apache 2.0
]]></content>
  <tabTrigger>readme</tabTrigger>
</snippet>