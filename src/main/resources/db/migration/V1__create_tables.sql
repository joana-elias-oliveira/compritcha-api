CREATE TABLE purchases (
                           id BIGSERIAL PRIMARY KEY,
                           description VARCHAR(255) NOT NULL,
                           status VARCHAR(50) NOT NULL,
                           total VARCHAR(255)
);

CREATE TABLE items (
                       id BIGSERIAL PRIMARY KEY,
                       description VARCHAR(255) NOT NULL,
                       value DECIMAL(10,2) NOT NULL,
                       purchase_id BIGINT REFERENCES purchases(id) ON DELETE CASCADE,
                       parent_item_id BIGINT REFERENCES items(id) ON DELETE CASCADE
);


SELECT
    p.id AS purchase_id,
    p.description AS purchase_description,
    SUM(i.value) AS total_value,
    COUNT(i.id) AS item_count
FROM purchases p
         LEFT JOIN items i ON i.purchase_id = p.id
GROUP BY p.id, p.description
ORDER BY p.id;
