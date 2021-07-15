# kafka-quickstart Project

## Docker build image

```shell
 # Enable storage to minikube for built images
 eval $(minikube -p minikube docker-env)
     
 mvn package 
 docker build -f src/main/docker/Dockerfile.jvm .
```

## Kubernetes Kafka setup

Simple setup https://strimzi.io/quickstarts

YAML :

- [[kafka-consumer](src/main/kubernetes/kafka-consumer.yaml)]
- [[kafka-producer](src/main/kubernetes/kafka-producer.yaml)]  

