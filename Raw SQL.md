# What is SQL?

- SQL stands for Structured Query Language
- SQL lets you access and manipulate databases
- SQL became a standard of the American National Standards Institute (ANSI) in 1986, and of the International
  Organization for Standardization (ISO) in 1987

# What Can SQL do?

1. SQL can execute queries against a database
2. SQL can retrieve data from a database
3. SQL can insert records in a database
4. SQL can update records in a database
5. SQL can delete records from a database
6. SQL can create new databases
7. SQL can create new tables in a database
8. SQL can create stored procedures in a database
9. SQL can create views in a database
10. SQL can set permissions on tables, procedures, and views

# RDBMS

RDBMS stands for Relational Database Management System.

RDBMS is the basis for SQL, and for all modern database systems such as MS SQL Server, IBM DB2, Oracle, MySQL, and
Microsoft Access.

The data in RDBMS is stored in database objects called tables. A table is a collection of related data entries and it
consists of columns and rows.

# Queries

## Select columns or all

> SELECT Country FROM Customers;

or

> SELECT * FROM Customers;

## Select only unique items

> SELECT DISTINCT Country FROM Customers;
>
> SELECT COUNT(DISTINCT Country) FROM Customers;

```sql
SELECT Count(*) AS DistinctCountries
FROM (SELECT DISTINCT Country FROM Customers);
```

`SELECT DISTINCT Country FROM Customers returns a list of unique countries. Then we query SELECT Count(*) AS 
DistinctCountries from the result of SELECT DISTINCT Country FROM Customers`

## where filter

> SELECT * FROM Customers WHERE Country='Mexico';
>
> SELECT * FROM Customers WHERE CustomerID=1;
>
> SELECT * FROM Customers WHERE CustomerID > 80;

### Operators

| Operator | Description                                     | Example                         |
|----------|-------------------------------------------------|---------------------------------|
| =        | Equal                                           | `WHERE age = 30`                |
| >        | Greater than                                    | `WHERE salary > 50000`          |
| <        | Less than                                       | `WHERE age < 18`                |
| >=       | Greater than or equal                           | `WHERE score >= 80`             |
| <=       | Less than or equal                              | `WHERE price <= 100`            |
| <>       | Not equal (can also be written as `!=` in some) | `WHERE name <> 'John'`          |
| BETWEEN  | Between a certain range                         | `WHERE age BETWEEN 18 AND 30`   |
| LIKE     | Search for a pattern                            | `WHERE name LIKE 'A%'`          |
| IN       | Specify multiple values for a column            | `WHERE country IN ('US', 'UK')` |
