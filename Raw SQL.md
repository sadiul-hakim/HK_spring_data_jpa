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

## In

The IN operator allows you to specify multiple values in a WHERE clause.

The IN operator is a shorthand for multiple OR conditions.

```sql
SELECT *
FROM Customers
WHERE Country IN ('Germany', 'France', 'UK');
```

```sql
SELECT *
FROM Customers
WHERE Country NOT IN ('Germany', 'France', 'UK');
```

```sql
SELECT *
FROM Customers
WHERE CustomerID IN (SELECT CustomerID FROM Orders);

SELECT *
FROM Customers
WHERE CustomerID NOT IN (SELECT CustomerID FROM Orders);
```

## The SQL BETWEEN Operator

The BETWEEN operator selects values within a given range. The values can be numbers, text, or dates.

The BETWEEN operator is inclusive: begin and end values are included.

```sql
SELECT column_name(s)
FROM table_name
WHERE column_name BETWEEN value1 AND value2;
```

## SQL Aliases

SQL aliases are used to give a table, or a column in a table, a temporary name.

Aliases are often used to make column names more readable.

An alias only exists for the duration of that query.

An alias is created with the AS keyword.

```sql
SELECT CustomerID AS ID
FROM Customers;
```

### AS is Optional

Actually, in most database languages, you can skip the AS keyword and get the same result:

```sql
SELECT CustomerID ID
FROM Customers;
```

## The SQL UNION Operator

The UNION operator is used to combine the result-set of two or more SELECT statements.

- Every SELECT statement within UNION must have the same number of columns
- The columns must also have similar data types
- The columns in every SELECT statement must also be in the same order

```sql
SELECT column_name(s)
FROM table1
UNION
SELECT column_name(s)
FROM table2;
```

### UNION ALL Syntax

The UNION operator selects only distinct/unique values by default. To allow duplicate values, use UNION ALL:

```sql
SELECT column_name(s)
FROM table1
UNION ALL
SELECT column_name(s)
FROM table2;
```

## The SQL HAVING Clause

The HAVING clause was added to SQL because the WHERE keyword cannot be used with aggregate functions. Use it with
`group by`

```sql
SELECT column_name(s)
FROM table_name
WHERE condition
GROUP BY column_name(s)
HAVING condition
ORDER BY column_name(s);
```

```sql
SELECT COUNT(CustomerID), Country
FROM Customers
GROUP BY Country
HAVING COUNT(CustomerID) > 5;
```

## The SQL EXISTS Operator

The EXISTS operator is used to test for the existence of any record in a subquery.

The EXISTS operator returns TRUE if the subquery returns one or more records.

```sql
SELECT column_name(s)
FROM table_name
WHERE EXISTS
              (SELECT column_name FROM table_name WHERE condition);
```

```sql
SELECT SupplierName
FROM Suppliers
WHERE EXISTS (SELECT ProductName FROM Products WHERE Products.SupplierID = Suppliers.supplierID AND Price < 20);
```

### If `select customer_id customer, total from orders where total >= 1500` returns any row where exists becomes true

    then select CONCAT(first_name, " ", last_name) name from customers executes

```sql
select CONCAT(first_name, " ", last_name) name
from customers
where exists (select customer_id customer, total from orders where total >= 1500);
```

## The SQL ANY and ALL Operators

The ANY and ALL operators allow you to perform a comparison between a single column value and a range of other values.

### The SQL ANY Operator

The ANY operator:

- returns a boolean value as a result
- returns TRUE if ANY of the subquery values meet the condition
  ANY means that the condition will be true if the operation is true for any of the values in the range.

```sql
SELECT column_name(s)
FROM table_name
WHERE column_name operator ANY
(SELECT column_name
 FROM table_name
 WHERE condition);
```

### The SQL ALL Operator

The ALL operator:

- returns a boolean value as a result
- returns TRUE if ALL of the subquery values meet the condition
  is used with SELECT, WHERE and HAVING statements
  ALL means that the condition will be true only if the operation is true for all values in the range.

## The SQL SELECT INTO Statement

The SELECT INTO statement copies data from one table into a new table.

### SELECT INTO Syntax (SQL Server (MSSQL) — NOT in MySQL)

Copy all columns into a new table:

```sql
SELECT *
INTO newtable [IN externaldb]
FROM oldtable
WHERE condition;
```

### MYSQL

```sql
create table order_summury select CONCAT
(
    c
    .
    first_name,
    " ",
    c
    .
    last_name
) name,o.total,o.order_date from orders o inner join customers c on o.customer_id = c.id order by name asc;
```

## The SQL INSERT INTO SELECT Statement

The INSERT INTO SELECT statement copies data from one table and inserts it into another table.

The INSERT INTO SELECT statement requires that the data types in source and target tables match.

Note: The existing records in the target table are unaffected.

INSERT INTO SELECT Syntax

```sql
INSERT INTO table2
SELECT *
FROM table1
WHERE condition;
```

## The SQL CASE Expression

The CASE expression goes through conditions and returns a value when the first condition is met (like an if-then-else
statement). So, once a condition is true, it will stop reading and return the result. If no conditions are true, it
returns the value in the ELSE clause.

If there is no ELSE part and no conditions are true, it returns NULL.

```sql
CASE
    WHEN condition1 THEN result1
    WHEN condition2 THEN result2
    WHEN conditionN THEN resultN
    ELSE result
END;
```

EXAMPLE

```sql
SELECT CASE
           WHEN G.GRADE >= 8 THEN S.NAME
           ELSE 'NULL'
           END AS NAME,
       G.GRADE,
       S.MARKS
FROM STUDENTS S
         JOIN GRADES G ON S.MARKS BETWEEN G.MIN_MARK AND G.MAX_MARK
ORDER BY G.GRADE DESC,
         CASE
             WHEN G.GRADE >= 8 THEN S.NAME
             ELSE NULL
             END ASC,
         CASE
             WHEN G.GRADE < 8 THEN S.MARKS
             ELSE NULL
             END ASC;
```

## NULL functions - MySQl

Suppose that a column is optional, and may contain NULL values.

Look at the following SELECT statement:

```sql
SELECT ProductName, UnitPrice * (UnitsInStock + UnitsOnOrder)
FROM Products;
```

In the example above, if any of the "UnitsOnOrder" values are NULL, the result will be NULL.

### Solutions - MYSQL

`The MySQL IFNULL() function lets you return an alternative value if an expression is NULL:`

```sql
SELECT ProductName, UnitPrice * (UnitsInStock + IFNULL(UnitsOnOrder, 0))
FROM Products;
```

`or we can use the COALESCE() function, like this:`

```sql
SELECT ProductName, UnitPrice * (UnitsInStock + COALESCE(UnitsOnOrder, 0))
FROM Products;
```

## What is a Stored Procedure?

A stored procedure is a prepared SQL code that you can save, so the code can be reused over and over again.

So if you have an SQL query that you write over and over again, save it as a stored procedure, and then just call it to
execute it.

You can also pass parameters to a stored procedure, so that the stored procedure can act based on the parameter value(s)
that is passed.

### Stored Procedure Syntax MYSQL

```sql
DELIMITER //

CREATE PROCEDURE get_customer_orders()
BEGIN
    SELECT c.name  AS customer_name,
           o.total AS order_total,
           o.order_date,
           p.product_name
    FROM orders o
             JOIN customers c ON o.customer_id = c.id
             JOIN products p ON o.product_id = p.id;
END //

DELIMITER;

```

### Execute a Stored Procedure

```sql
call procedure_name;
```

### Example

```sql
DELIMITER //

CREATE PROCEDURE orders_report()
BEGIN
    SELECT CONCAT(C.FIRST_NAME," ",C.LAST_NAME) CUSTOMER,P.NAME, O.TOTAL,O.CURRENCY,O.ORDER_DATE FROM orders O JOIN customers C ON O.CUSTOMER_ID = C.ID JOIN products P ON P.ID = O.PRODUCT_ID;
END //

DELIMITER ;
```

Execute the stored procedure above as follows:
> CALL orders_report();

### Stored Procedure With One Parameter mysql syntax
```sql
DELIMITER //

CREATE PROCEDURE get_orders_by_customer(IN cust_id INT)
BEGIN
    SELECT c.name  AS customer_name,
           o.total AS order_total,
           o.order_date,
           p.product_name
    FROM orders o
             JOIN customers c ON o.customer_id = c.id
             JOIN products p ON o.product_id = p.id
    WHERE o.customer_id = cust_id;
END //

DELIMITER;

```

```sql
CALL get_orders_by_customer(3);
```

### Stored Procedure With Multiple Parameters

```sql
DELIMITER //

CREATE PROCEDURE get_customer_orders_in_range(
    IN cust_id INT,
    IN start_date DATE,
    IN end_date DATE
)
BEGIN
    SELECT c.name  AS customer_name,
           o.total AS order_total,
           o.order_date,
           p.product_name
    FROM orders o
             JOIN customers c ON o.customer_id = c.id
             JOIN products p ON o.product_id = p.id
    WHERE o.customer_id = cust_id
      AND o.order_date BETWEEN start_date AND end_date;
END //

DELIMITER;

```

```sql
CALL get_customer_orders_in_range(3, '2024-01-01', '2024-12-31');
```

## Comment
### Single Line Comments
Single line comments start with --.

Any text between -- and the end of the line will be ignored (will not be executed).

The following example uses a single-line comment as an explanation:

```sql
-- Select all:
SELECT * FROM Customers;
```

### Multi-line Comments
Multi-line comments start with /* and end with */.

Any text between /* and */ will be ignored.

The following example uses a multi-line comment as an explanation:

```sql
/*Select all the columns
of all the records
in the Customers table:*/
SELECT *
FROM Customers;
```