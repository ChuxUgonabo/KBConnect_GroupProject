USE kbconnect;
DROP TABLE IF EXISTS routes;

CREATE TABLE routes (
    id int AUTO_INCREMENT PRIMARY KEY,
    routeNo VARCHAR(50),
    startingStop VARCHAR(50),
    terminationStop VARCHAR(50),
    fromCity VARCHAR(50),
    toCity VARCHAR(50)
)
