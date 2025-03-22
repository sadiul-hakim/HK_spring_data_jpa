# JPA

> Database <- SQL <- JDBC <- JPA (Hibernate) <- Spring Data JPA

## Entity

### JPA GenerationType in Spring Data JPA

In Spring Data JPA, the `@GeneratedValue` annotation is used to specify how the primary key (ID) of an entity should be generated. It is commonly used with the `GenerationType` enum, which provides four different strategies:

#### 1. `GenerationType.AUTO`
- Default strategy.
- JPA determines the best generation strategy based on the underlying database.
- It may use `IDENTITY`, `SEQUENCE`, or `TABLE` depending on the database vendor.

##### Example:
```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
```

#### 2. `GenerationType.IDENTITY`
- Uses the database's auto-increment feature.
- The database generates the ID when inserting a new record.
- Works well with MySQL, PostgreSQL, and other databases that support auto-increment columns.
- Not suitable for batch inserts due to the immediate ID assignment.

##### Example:
```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
```

#### 3. `GenerationType.SEQUENCE`
- Uses a database sequence to generate unique identifiers.
- Requires defining a sequence explicitly or relies on the default sequence provided by the database.
- Recommended for databases like PostgreSQL, Oracle, and H2.
- Allows batch inserts since IDs are preallocated.

##### Example:
```java
@Entity
@SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;
    private String name;
}
```

#### 4. `GenerationType.TABLE`
- Uses a separate table to maintain and generate primary key values.
- More portable but less efficient compared to `IDENTITY` and `SEQUENCE`.
- Should be avoided unless necessary for compatibility reasons.

##### Example:
```java
@Entity
@TableGenerator(name = "user_table_gen", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_value", pkColumnValue = "user_id", allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_table_gen")
    private Long id;
    private String name;
}
```

#### Choosing the Right Strategy
| Strategy      | Use Case |
|--------------|---------|
| `AUTO`       | When unsure; lets JPA decide the best option. |
| `IDENTITY`   | When using auto-increment in databases like MySQL. |
| `SEQUENCE`   | When using sequence-based ID generation (recommended for PostgreSQL, Oracle). |
| `TABLE`      | When a table-based ID generation strategy is needed for compatibility. |

Each strategy has its pros and cons, so choosing the right one depends on your database and performance needs.

