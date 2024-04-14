FROM openjdk:17
Volume /tmp
ADD /target/*.jar travel_packages_service-0.0.1-SNAPSHOT.jar
EXPOSE 9031
ENTRYPOINT ["java", "-Xmx1024m","-jar", "/travel_packages_service-0.0.1-SNAPSHOT.jar"]
