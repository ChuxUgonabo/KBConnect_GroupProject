USE kbconnect;
CREATE TABLE IF NOT EXISTS compassCards (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cardNumber VARCHAR(50) UNIQUE NOT NULL,
    cvn VARCHAR(256) NOT NULL,
    isActive BOOLEAN,
    balance DECIMAL(6,2)
)
