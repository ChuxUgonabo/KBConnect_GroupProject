USE kbconnect;
DROP TABLE IF EXISTS subscribedTo;
CREATE TABLE subscribedTo(
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId int,
    routeId int,
    UNIQUE (userId, routeId),
    FOREIGN KEY (routeId) REFERENCES routes(id),
    FOREIGN KEY (userId) REFERENCES users(id)
)
