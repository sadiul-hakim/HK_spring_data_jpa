# 🧠 SQL GROUP BY Explained Simply

`GROUP BY` is used to **group rows** that have the same value in one or more columns, often so we can **apply aggregate
functions** like:

- `COUNT()` → count how many
- `SUM()` → add values
- `AVG()` → average
- `MAX()` → biggest
- `MIN()` → smallest

---

## 🍕 Example Table: `Orders`

| order_id | customer_id | status    | total |
|----------|-------------|-----------|-------|
| 1        | 101         | Shipped   | 100   |
| 2        | 102         | Pending   | 200   |
| 3        | 101         | Shipped   | 150   |
| 4        | 103         | Cancelled | 50    |
| 5        | 101         | Shipped   | 120   |

---

## 1. Group by customer — total spending

```sql
SELECT customer_id, SUM(total) AS total_spent
FROM Orders
GROUP BY customer_id;
```

## 2. Count orders by status

```sql
SELECT status, COUNT(*) AS order_count
FROM Orders
GROUP BY status;
```

### Important Notes

- You can only SELECT columns in GROUP BY or aggregated (SUM, COUNT, etc).
- If you try to select a column that is not grouped or aggregated, you'll get an error.

### 🧪 Example with WHERE and GROUP BY

```sql
SELECT customer_id, COUNT(*) AS orders_made
FROM Orders
WHERE status = 'Shipped'
GROUP BY customer_id;

```

### 🚫 Wrong Example (will cause error)

```sql
SELECT customer_id, status, COUNT(*)
FROM Orders
GROUP BY customer_id; -- ❌ status is not grouped or aggregated
```

| Use Case       | SQL Keyword / Function |
|----------------|------------------------|
| Group rows     | GROUP BY               |
| Count items    | COUNT(*)               |
| Add up numbers | SUM(column)            |
| Average values | AVG(column)            |
| Largest value  | MAX(column)            |
| Smallest value | MIN(column)            |
