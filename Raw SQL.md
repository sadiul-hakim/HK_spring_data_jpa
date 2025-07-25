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

## Order By

> SELECT * FROM Products ORDER BY Price;

```sql
SELECT column1,
       column2, ...
    FROM table_name
ORDER BY column1, column2, ...ASC| DESC;
```

> SELECT * FROM Customers ORDER BY Country, CustomerName;

## And

```sql
SELECT column1,
       column2, ...
    FROM table_name
WHERE condition1 AND condition2 AND condition3...;
```

## Or

```sql
SELECT column1,
       column2, ...
    FROM table_name
WHERE condition1 OR condition2 OR condition3...;
```

## Not

```sql
SELECT column1,
       column2, ...
    FROM table_name
WHERE NOT condition;
```

> SELECT * FROM Customers WHERE CustomerName NOT LIKE '%A%' order by CustomerName asc;

## Insert Query

INSERT INTO Syntax
It is possible to write the INSERT INTO statement in two ways:

1. Specify both the column names and the values to be inserted:

```sql

INSERT INTO table_name (column1, column2, column3, ...)
VALUES (value1, value2, value3, ...);
```

2. If you are adding values for all the columns of the table, you do not need to specify the column names in the SQL
   query. However, make sure the order of the values is in the same order as the columns in the table. Here, the INSERT
   INTO syntax would be as follows:

```sql

INSERT INTO table_name
VALUES (value1, value2, value3, ...);
```

```sql
INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country)
VALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', '4006', 'Norway'),
       ('Greasy Burger', 'Per Olsen', 'Gateveien 15', 'Sandnes', '4306', 'Norway'),
       ('Tasty Tee', 'Finn Egan', 'Streetroad 19B', 'Liverpool', 'L1 0AA', 'UK');
```

Make sure you separate each set of values with a comma `,`.

## NULL

It is not possible to test for NULL values with comparison operators, such as =, <, or <>.

We will have to use the IS NULL and IS NOT NULL operators instead.

IS NULL Syntax

```sql

SELECT column_names
FROM table_name
WHERE column_name IS NULL;
```

IS NOT NULL Syntax

```sql

SELECT column_names
FROM table_name
WHERE column_name IS NOT NULL;
```

## Update

```sql
UPDATE table_name
SET column1 = value1,
    column2 = value2, ...
    WHERE condition;
```

## Delete

```sql
DELETE
FROM table_name
WHERE condition;
```

## Select Top (Does not work in mysql,postgres use `limit`)

> SQL Server / MS Access

```sql
SELECT TOP 3 *
FROM Customers;
```

> MySQL Syntax:

```sql
SELECT column_name(s)
FROM table_name
WHERE condition
LIMIT number;
```

> Oracle 12 Syntax:

```sql
SELECT column_name(s)
FROM table_name
ORDER BY column_name(s)
    FETCH FIRST number ROWS ONLY;
```

## SQL Aggregate Functions

An aggregate function is a function that performs a calculation on a set of values, and returns a single value.

Aggregate functions are often used with the GROUP BY clause of the SELECT statement. The GROUP BY clause splits the
result-set into groups of values and the aggregate function can be used to return a single value for each group.

he most commonly used SQL aggregate functions are:

- MIN() - returns the smallest value within the selected column
- MAX() - returns the largest value within the selected column
- COUNT() - returns the number of rows in a set
- SUM() - returns the total sum of a numerical column
- AVG() - returns the average value of a numerical column

Aggregate functions ignore null values (except for COUNT(*)).

> select avg(salary) as total_salary from customers;

> select max(salary) as salary from customers;
>
> select min(salary) as salary from customers;
>
> select sum(salary) as salary from customers where salary > 20000;

## Wildcards

A wildcard character is used to substitute one or more characters in a string.

Wildcard characters are used with the LIKE operator. The LIKE operator is used in a WHERE clause to search for a
specified pattern in a column.

```sql
SELECT *
FROM Customers
WHERE CustomerName LIKE 'a%';
```

| Symbol | Description                                      | SQL Example                            | Matches                            |
|--------|--------------------------------------------------|----------------------------------------|------------------------------------|
| `%`    | Zero or more characters                          | `WHERE name LIKE 'A%'`                 | `'Alice'`, `'Andrew'`, `'A'`       |
| `_`    | Exactly one character                            | `WHERE code LIKE 'H_t'`                | `'Hat'`, `'Hot'`, but not `'Heat'` |
| `[]`   | Any one character inside brackets (SQL Server)   | `WHERE word LIKE 't[ae]st'`            | `'tast'`, `'test'`                 |
| `[^]`  | Not any character inside brackets (SQL Server)   | `WHERE word LIKE 't[^ae]st'`           | `'tost'`, `'t1st'`, not `'test'`   |
| `-`    | Range of characters inside brackets (SQL Server) | `WHERE word LIKE 't[a-c]t'`            | `'tat'`, `'tbt'`, `'tct'`          |
| `{}`   | Escaped characters (SQL Server/Others)           | `WHERE note LIKE '100{%%}' ESCAPE '{'` | `'100%'`                           |

# Question and Answers

1. To get length of text use length()
2. Query the list of CITY names from STATION which have vowels (i.e., a, e, i, o, and u) as both their first and last
   characters. Your result cannot contain duplicates.

```sql
SELECT DISTINCT CITY
FROM STATION
WHERE LOWER(SUBSTRING(CITY, 1, 1)) IN ('a', 'e', 'i', 'o', 'u')
  AND LOWER(RIGHT(CITY, 1)) IN ('a', 'e', 'i', 'o', 'u');
```

3. Query the list of CITY names from STATION that do not start with vowels. Your result cannot contain duplicates.

```sql
select distinct city
from station
where lower(left(city, 1)) not in ('a', 'e', 'i', 'o', 'u');
```


