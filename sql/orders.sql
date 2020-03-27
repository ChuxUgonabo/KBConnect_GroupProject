use kbconnect;
DROP TABLE IF EXISTS orders;
CREATE TABLE  orders (
    orderId int  not NULL auto_increment primary key,
    quantity int NOT NULL DEFAULT 1,
    transactionDate DATE,
    productId int,
    placedBy int,
    approvedBy int,
    approvalStatus BOOLEAN,
    FOREIGN KEY (productId) REFERENCES products(id),
    FOREIGN KEY (placedBy) REFERENCES user(id),
    FOREIGN KEY (approvedBy) REFERENCES admin(id)
);
