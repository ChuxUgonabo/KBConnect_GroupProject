use kbconnect;
CREATE TABLE IF NOT EXISTS orders (
    orderId int auto_increment,
    quantity int NOT NULL DEFAULT 1,
    transactionDate DATE,
    productId int,
    placedBy int,
    approvedBy int,
    FOREIGN KEY productId REFERENCES products(id),
    FOREIGN KEY placedBy REFERENCES user(id),
    FOREIGN KEY approvedBy REFERENCES admin(id)
)
