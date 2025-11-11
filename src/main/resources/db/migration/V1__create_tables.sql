CREATE TABLE compras (
   id BIGSERIAL PRIMARY KEY,
   descricao VARCHAR(255) NOT NULL,
   status VARCHAR(50) NOT NULL
);

CREATE TABLE itens (
    id BIGSERIAL PRIMARY KEY,
    compra_id BIGINT REFERENCES compras(id) ON DELETE CASCADE,
    item_pai_id BIGINT REFERENCES itens(id) ON DELETE CASCADE,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10,2) NOT NULL
);

SELECT
    c.id AS id_compra,
    c.descricao AS descricao_compra,
    SUM(i.valor) AS valor_total_compra,
    COUNT(i.id) AS quantidade_itens
FROM compras c
LEFT JOIN itens i ON i.compra_id = c.id
GROUP BY c.id, c.descricao
ORDER BY c.id;
