Proyecto en construcción, WIP.

Pendiente dividir en commits más atómicos y encapsulados.

Mejoras opcionales:
    - flyway para mantener migraciones: mvn flyway:migrate aplica todas las migraciones
    - CRUD precios
    
Migraciones:
    - Al arrancar el servicio, el plugin de Maven Flyway verifica que se hayan aplicado todas las migraciones a la base de datos del servicio. Si hubiera alguna migración pendiente de aplicar, la aplica y luego actualiza el histórico de cambios. El snippet siguiente muestra la primera y única migración aplicada con éxito al arrancar el servicio:
    
>2024-10-15T20:24:06.838+02:00  INFO 13796 --- [           main] c.i.prices.EliasInditexApplication       : Starting EliasInditexApplication using Java 17.0.12 with PID 13796
>
> (C:\Users\User1\Documents\JavaProjects\eliV2\eli\target\classes started by User1 in C:\Users\User1\Documents\JavaProjects\eliV2\eli)
>
>2024-10-15T20:24:06.846+02:00  INFO 13796 --- [           main] c.i.prices.EliasInditexApplication       : No active profile set, falling back to 1 default profile: "default"
>
>2024-10-15T20:24:08.452+02:00  INFO 13796 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
>
>2024-10-15T20:24:08.580+02:00  INFO 13796 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 113 ms. Found 1 JPA repository interface.
>
>2024-10-15T20:24:09.862+02:00  INFO 13796 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
>
>2024-10-15T20:24:09.880+02:00  INFO 13796 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
>
>2024-10-15T20:24:09.881+02:00  INFO 13796 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.30]
>
>2024-10-15T20:24:09.970+02:00  INFO 13796 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
>
>2024-10-15T20:24:09.971+02:00  INFO 13796 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 3036 ms
>
>2024-10-15T20:24:10.202+02:00  INFO 13796 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
>
>2024-10-15T20:24:10.482+02:00  INFO 13796 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:testdb user=USER
>
>2024-10-15T20:24:10.485+02:00  INFO 13796 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
>
>2024-10-15T20:24:10.500+02:00  INFO 13796 --- [           main] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'
>
>2024-10-15T20:24:10.982+02:00  INFO 13796 --- [           main] org.flywaydb.core.FlywayExecutor         : Database: jdbc:h2:mem:testdb (H2 2.2)
>
>2024-10-15T20:24:11.040+02:00  INFO 13796 --- [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Schema history table "PUBLIC"."flyway_schema_history" does not exist yet
>
>2024-10-15T20:24:11.052+02:00  INFO 13796 --- [           main] o.f.core.internal.command.DbValidate     : Successfully validated 1 migration (execution time 00:00.034s)
>
>2024-10-15T20:24:11.068+02:00  INFO 13796 --- [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Creating Schema History table "PUBLIC"."flyway_schema_history" ...
>
>2024-10-15T20:24:11.179+02:00  INFO 13796 --- [           main] o.f.core.internal.command.DbMigrate      : Current version of schema "PUBLIC": << Empty Schema >>
>
>2024-10-15T20:24:11.188+02:00  INFO 13796 --- [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "1.0.0 - create prices table"
>
>2024-10-15T20:24:11.223+02:00  INFO 13796 --- [           main] o.f.core.internal.command.DbMigrate      : Successfully applied 1 migration to schema "PUBLIC", now at version v1.0.0 (execution time 00:00.012s)

