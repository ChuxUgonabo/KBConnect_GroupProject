USE kbconnect;
DROP TABLE IF EXISTS alerts;
CREATE TABLE alerts (
    id INT AUTO_INCREMENT PRIMARY KEY,
	shortDescription VARCHAR(256),
	description VARCHAR(1000),
	dateCreated DATE,
    dateOfLastUpdate DATE,
    routeId int,
    FOREIGN KEY (routeId) REFERENCES routes(id)
);

