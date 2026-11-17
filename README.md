# tservice

A reactive gRPC service in Java: Spring Boot 3.2, Protocol Buffers, R2DBC and PostgreSQL, with Liquibase migrations.
It's a learning project from 2023 exploring how far a fully non-blocking stack goes, from the gRPC endpoint down to the
database driver.

## What's in it

- **`proto/`** holds the API contract: 12 `.proto` files, one per entity or join table (users, identifiers, contexts,
  domains, places, and the `tservice` / `tset` / `tspoon` records). Each service has the same shape: `create`,
  `update`, `delete`, `findById`, `findAll`, plus a few `findBy…` lookups that stream results.
- **`grpc-server/`** implements those services with
  [grpc-spring-boot-starter](https://github.com/yidongnan/grpc-spring-boot-starter):
  - `grpc/`: one service implementation per proto. Reads return `Flux`/`Mono` from the repositories and stream back
    over gRPC.
  - `mappers/`: plain static mappers between protobuf messages and entities.
  - `entities/`, `repositories/`, `services/`: Spring Data R2DBC entities and reactive repositories.
  - `exception/`: a global handler that turns exceptions into proper gRPC `Status` codes.
  - `trace/`: a server interceptor that logs call status.
  - `resources/db/`: Liquibase changelog for the schema.
- **`docker-compose.local.yml`**: PostgreSQL for local development. The full `docker-compose.yml` also sketches
  Prometheus, Grafana, Zipkin and node-exporter around the service.

## Run it

Requires Java 17+ and Maven.

```sh
docker compose -f docker-compose.local.yml up -d microservices_postgresql   # PostgreSQL on :5432
mvn -DskipTests package
java -jar grpc-server/target/grpc-server-3.2.0.jar
```

gRPC listens on `:8989`, HTTP and actuator on `:8081` and `:8082`. `grpc-server/src/test/.../Grpc.http` has sample
calls for IntelliJ's HTTP client. With [grpcurl](https://github.com/fullstorydev/grpcurl), for example:

```sh
grpcurl -plaintext -d '{"name":"demo","value":"1"}' localhost:8989 com.tservice.proto.tspoon.TspoonService/create
```

## Status

A finished learning exercise, not a maintained product.

- The code compiles and packages (`mvn -DskipTests package`).
- The tests are integration tests and need the local PostgreSQL running.
- Metrics and tracing in `docker-compose.yml` are a sketch. The service doesn't export Prometheus metrics yet.
