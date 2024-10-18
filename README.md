<h1>Prueba técnica para Inditex</h1>

<h2>Proyecto en construcción, pendiente dividir el código en commits más atómicos y añadir documentación</h2>

Mejoras opcionales implementadas:
- Flyway para mantener migraciones
- CRUD precios

Mejoras a implementar (WIP):
- especificación de la API mediante OpenAPI
    
Migraciones:
- Al arrancar el servicio, el plugin de Maven Flyway verifica que se hayan aplicado todas las migraciones a la base de datos del servicio. Si hubiera alguna migración pendiente de aplicar, la aplica y luego actualiza el histórico de cambios.

Aquí se puede ver en el log cómo Flyway aplica una migración al iniciar el servicio:

```
...
2024-10-15T20:24:10.982+02:00  INFO 13796 --- [           main] org.flywaydb.core.FlywayExecutor         : Database: jdbc:h2:mem:testdb (H2 2.2)
2024-10-15T20:24:11.040+02:00  INFO 13796 --- [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Schema history table "PUBLIC"."flyway_schema_history" does not exist yet
2024-10-15T20:24:11.052+02:00  INFO 13796 --- [           main] o.f.core.internal.command.DbValidate     : Successfully validated 1 migration (execution time 00:00.034s)
2024-10-15T20:24:11.068+02:00  INFO 13796 --- [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Creating Schema History table "PUBLIC"."flyway_schema_history" ...
2024-10-15T20:24:11.179+02:00  INFO 13796 --- [           main] o.f.core.internal.command.DbMigrate      : Current version of schema "PUBLIC": << Empty Schema >>
2024-10-15T20:24:11.188+02:00  INFO 13796 --- [           main] o.f.core.internal.command.DbMigrate      : Migrating schema "PUBLIC" to version "1.0.0 - create prices table"
2024-10-15T20:24:11.223+02:00  INFO 13796 --- [           main] o.f.core.internal.command.DbMigrate      : Successfully applied 1 migration to schema "PUBLIC", now at version v1.0.0 (execution time 00:00.012s)
...
```
