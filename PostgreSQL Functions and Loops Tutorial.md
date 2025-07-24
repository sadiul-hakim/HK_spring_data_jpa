# PostgreSQL Functions and Loops Tutorial

PostgreSQL provides powerful procedural language capabilities that allow you to create functions with complex logic,
including loops, conditionals, and variables. Here's a comprehensive guide.

## Table of Contents

1. Basic Function Structure
2. Loop Types
    - Simple LOOP
    - WHILE LOOP
    - FOR LOOP
3. Generating Series
4. Practical Example
5. Using Functions in Queries

## Basic Function Structure

PostgreSQL functions are created using the `CREATE FUNCTION` statement:

```sql
CREATE
    OR REPLACE FUNCTION function_name(parameters)
    RETURNS return_type AS
$$
DECLARE
    -- Variable declarations
BEGIN
    -- Function logic
    RETURN value;
END;
$$
    LANGUAGE plpgsql;
```

## Loop Types

### Simple LOOP

The most basic loop that runs until an EXIT condition is met:

```sql
CREATE
    OR REPLACE FUNCTION count_to_ten()
    RETURNS VOID AS
$$
DECLARE
    i INT := 1;
BEGIN
    LOOP
        RAISE NOTICE 'Count: %', i;
        i
            := i + 1;
        EXIT
            WHEN i > 10;
    END LOOP;
END;
$$
    LANGUAGE plpgsql;

select *
from count_to_ten();
```

### WHILE LOOP

Runs while a condition is true:

```sql
CREATE
    OR REPLACE FUNCTION factorial(n INT)
    RETURNS INT AS
$$
DECLARE
    result INT := 1;
    i
           INT := 1;
BEGIN
    WHILE
        i <= n
        LOOP
            result := result * i;
            i
                := i + 1;
        END LOOP;
    RETURN result;
END;
$$
    LANGUAGE plpgsql;
```

### FOR LOOP

Iterates over a range:

```sql
CREATE
    OR REPLACE FUNCTION sum_numbers()
    RETURNS INT AS
$$
DECLARE
    total INT := 0;
BEGIN
    FOR i IN 1..100
        LOOP
            total := total + i;
        END LOOP;
    RETURN total;
END;
$$
    LANGUAGE plpgsql;
```

### Examples
```sql
create or replace function count_to_ten() returns void as
$$
declare
    i int := 1;
begin
    loop
        raise notice 'count : %',i;
        i := i + 1;
        exit when i > 10;
    end loop;

end;
$$
    language plpgsql;

create or replace function factorial(n int) returns int as
$$
declare
    result int := 1;
    i      int := 1;
begin
    while i <= n
        loop
            result := result * i;
            i := i + 1;
        end loop;
    return result;
end;
$$
    language plpgsql;


-- select * from count_to_ten();
select *
from factorial(10);

create or replace function sun_till_100() returns int as
$$
declare
    result int := 0;
begin
    for i in 1..100
        loop
            result := result + i;
        end loop;
    return result;
end;
$$
    language plpgsql;

-- select * from count_to_ten();
select *
from sun_till_100();

```

## Generating Series

A series in PostgreSQL refers to a sequence of values, typically numbers or timestamps, generated in order. PostgreSQL
provides powerful functions to generate these sequences, which are extremely useful for data generation, testing, and
analytical queries.

### The generate_series() Function

PostgreSQL's built-in generate_series() function creates a set of rows containing a series of values.

### Basic Syntax

```sql
generate_series
    (start, stop, step)
```

- start: First value in the series
- stop: Last value in the series (inclusive)
- step: Increment between values (default is 1)

### Types of Series

1. Integer Series:

```sql
SELECT *
FROM generate_series(1, 5);
-- Result: 1, 2, 3, 4, 5
```

2. Series with Step:

```sql
SELECT *
FROM generate_series(1, 10, 2);
-- Result: 1, 3, 5, 7, 9
```

3. Timestamp Series:

```sql
SELECT *
FROM generate_series(
        '2023-01-01'::timestamp,
        '2023-01-05'::timestamp,
        '1 day'::interval
     );
-- Generates dates from Jan 1 to Jan 5
```

4. Descending Series:

```sql
SELECT *
FROM generate_series(5, 1, -1);
-- Result: 5, 4, 3, 2, 1
```

### Practical Uses

1. Generating Test Data (as in your original example):

```sql
INSERT INTO customers (first_name, last_name)
SELECT 'FirstName_' || num,
       'LastName_' || num
FROM generate_series(1, 100) AS num;
```

2. Date Range Queries:

```sql
SELECT date, COALESCE(sales, 0) as sales
FROM generate_series(
             '2023-01-01':: date, '2023-01-31':: date, '1 day':: interval
     ) dates(date)
         LEFT JOIN daily_sales
                   ON dates.date = daily_sales.sale_date;
```

3. Number Tables for Calculations:

```sql
SELECT n, n * n as square, n * n * n as cube
FROM generate_series(1, 10) AS n;
```

### How It Differs from Loops

While generate_series() produces a set of rows, loops in PL/pgSQL (PostgreSQL's procedural language) execute procedural
code:

- generate_series():
    - Declarative approach
    - Returns a result set
    - Runs as part of a SQL query
- Loops:
    - Imperative approach
    - Used within functions/stored procedures
    - Can perform complex operations between iterations

### Performance Considerations

generate_series() is generally very efficient because:

- It's a built-in function optimized by PostgreSQL
- It generates values on demand without storing intermediate results
- It can be used in parallel query execution

However, for extremely large series (millions of values), consider:

- Using a smaller step value
- Generating in batches
- Creating a physical numbers table for frequently used ranges

The `generate_series` function is a built-in PostgreSQL function that creates a sequence of values:

```sql
-- Generate a series of numbers
SELECT *
FROM generate_series(1, 10);

-- Generate a series with step
SELECT *
FROM generate_series(1, 10, 2);

-- Generate timestamp series
SELECT *
FROM generate_series(
        '2023-01-01'::timestamp,
        '2023-01-10'::timestamp,
        '1 day'::interval
     );
```

## Practical Example

Here's a function that generates random customer data similar to your original query:

```sql
CREATE
    OR REPLACE FUNCTION generate_customers(num_rows INT)
    RETURNS VOID AS
$$
DECLARE
    i INT;
    cities
      TEXT[] := ARRAY ['Dhaka', 'Chittagong', 'Rajshahi', 'Khulna', 'Barisal', 'Sylhet', 'Rangpur', 'Mymensingh'];
BEGIN
    FOR i IN 1..num_rows
        LOOP
            INSERT INTO customers (first_name, last_name, email, phone, city)
            VALUES ('FirstName' || i,
                    'LastName' || i,
                    'user' || i || '@example.com',
                    '+8801' || LPAD((FLOOR(RANDOM() * 100000000))::TEXT, 8, '0'),
                    cities[1 + (RANDOM() * 7)::INT]);
        END LOOP;
END;
$$
    LANGUAGE plpgsql;

-- Call the function to insert 100 records
SELECT generate_customers(100);
```

## Using Functions in Queries

You can use functions directly in SQL queries:

```sql
-- Create a function that returns a table
CREATE
    OR REPLACE FUNCTION get_customers_by_city(city_name TEXT)
    RETURNS TABLE
            (
                id        INT,
                full_name TEXT,
                contact   TEXT
            )
AS
$$
BEGIN
    RETURN QUERY
        SELECT customer_id,
               first_name || ' ' || last_name,
               phone || ' / ' || email
        FROM customers
        WHERE city = city_name;
END;
$$
    LANGUAGE plpgsql;

-- Use the function
SELECT *
FROM get_customers_by_city('Dhaka');
```

## Conclusion

PostgreSQL provides robust procedural language capabilities through PL/pgSQL. While you can't use loops directly in SQL
statements, you can:

1. Create functions with complex logic using loops
2. Use built-in functions like generate_series for simple sequences
3. Combine these approaches to generate test data or implement business logic

The key is to encapsulate your looping logic within functions, which you can then call from your SQL statements.