CREATE
DATABASE itp_pos;
USE
itp_pos;
#
======================
CREATE TABLE IF NOT EXISTS customer
(
    id
    VARCHAR
(
    80
) PRIMARY KEY,
    name VARCHAR
(
    100
) NOT NULL,
    address TEXT NOT NULL,
    salary DOUBLE
    );
#
======================
CREATE TABLE IF NOT EXISTS product
(
    id
    VARCHAR
(
    80
) PRIMARY KEY,
    qty INT DEFAULT 0,
    unit_price DOUBLE DEFAULT 0.0,
    description TEXT
    );
#
======================
CREATE TABLE IF NOT EXISTS application_user
(
    email
    VARCHAR
(
    100
) PRIMARY KEY,
    full_name VARCHAR
(
    150
),
    password VARCHAR
(
    750
)
    );
#
======================
CREATE TABLE IF NOT EXISTS customer_order
(
    id
    VARCHAR
(
    80
) PRIMARY KEY,
    cost DOUBLE,
    created_date DATE
    );
#
======================
CREATE TABLE IF NOT EXISTS order_detail
(
    order_id
    VARCHAR
(
    80
),
    product_id VARCHAR
(
    80
),
    qty INT,
    unit_price DOUBLE,
    CONSTRAINT PRIMARY KEY
(
    order_id,
    product_id
),
    CONSTRAINT FOREIGN KEY
(
    order_id
) REFERENCES customer_order
(
    id
) ON DELETE CASCADE
  ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY
(
    product_id
) REFERENCES product
(
    id
)
  ON DELETE CASCADE
  ON UPDATE CASCADE
    );