# SQL Joins Explained in Simple Words

In SQL, **JOIN** is used to combine rows from two or more tables based on a related column between them.

---

## 🧩 Tables We'll Use in Examples

### `Employees` Table

| employee_id | name    | department_id |
|-------------|---------|---------------|
| 1           | Alice   | 10            |
| 2           | Bob     | 20            |
| 3           | Charlie | 10            |
| 4           | Diana   | NULL          |

### `Departments` Table

| department_id | department_name |
|---------------|-----------------|
| 10            | HR              |
| 20            | IT              |
| 30            | Marketing       |

---

## 1. INNER JOIN

**Returns only matching rows** from both tables.

```sql
SELECT e.name, d.department_name
FROM Employees e
         INNER JOIN Departments d
                    ON e.department_id = d.department_id;
```

```sql
select CONCAT(c.first_name, " ", c.last_name) as customer_name, o.total, o.order_date
from orders o
         inner join customers c on o.customer_id = c.id
order by customer_name asc;
```

## 2. LEFT JOIN (or LEFT OUTER JOIN)

Returns all rows from the left table (Employees), and the matched rows from the right table (Departments). If no match,
shows NULL.

```sql
SELECT e.name, d.department_name
FROM Employees e
         LEFT JOIN Departments d
                   ON e.department_id = d.department_id;
```

## 3. RIGHT JOIN (or RIGHT OUTER JOIN)

Returns all rows from the right table (Departments), and the matched rows from the left table (Employees). If no match,
shows NULL

```sql
SELECT e.name, d.department_name
FROM Employees e
         RIGHT JOIN Departments d
                    ON e.department_id = d.department_id;

```

## 4. FULL JOIN (or FULL OUTER JOIN) - Not available in mysql

Returns all rows from both tables. If there's no match on either side, shows NULL.

```sql
SELECT e.name, d.department_name
FROM Employees e
         FULL OUTER JOIN Departments d
                         ON e.department_id = d.department_id;

```

For mysql use `union`

```sql
select c.first_name as customer, o.total, o.order_date
from orders o
         left join customers c on o.customer_id = c.id
union
select c.first_name, o.total, o.order_date
from orders o
         right join customers c on o.customer_id = c.id;
```

## 5. CROSS JOIN

Returns all combinations of rows from both tables — also called the Cartesian product.

```sql
SELECT e.name, d.department_name
FROM Employees e
         CROSS JOIN Departments d;

```

If Employees has 4 rows and Departments has 3 rows, the result will have 4 x 3 = 12 rows.

| Join Type  | Returns Only Matching Rows | Returns All Left Table Rows | Returns All Right Table Rows | Returns All Combinations |
|------------|----------------------------|-----------------------------|------------------------------|--------------------------|
| INNER JOIN | ✅                          | ❌                           | ❌                            | ❌                        |
| LEFT JOIN  | ✅ (when matched)           | ✅                           | ❌                            | ❌                        |
| RIGHT JOIN | ✅ (when matched)           | ❌                           | ✅                            | ❌                        |
| FULL JOIN  | ✅ (when matched)           | ✅                           | ✅                            | ❌                        |
| CROSS JOIN | ❌                          | ✅                           | ✅                            | ✅                        |
