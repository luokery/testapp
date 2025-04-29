-- Drop the existing table if it exists
DROP TABLE IF EXISTS TBL_users;

-- Recreate the table with the new structure
CREATE TABLE TBL_users (
    -- User id, for application logic usage
    user_id BIGINT PRIMARY KEY COMMENT 'User ID',
    -- User name, cannot be null, defaults to an empty string
    user_name VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'User Name',
    -- Password, cannot be null, defaults to an empty string
    password VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'Password',
    -- Creation time, cannot be null
    create_time TIMESTAMP NOT NULL COMMENT 'Creation Time',
    -- Update time
    update_time TIMESTAMP COMMENT 'Update Time'
);

-- Table comment
COMMENT ON TABLE TBL_users IS 'User Information Table';

-- Drop the existing table if it exists
DROP TABLE IF EXISTS TBL_orders;

-- Recreate the table with the new structure
CREATE TABLE TBL_orders (
    -- Order id, for application logic usage
    order_id BIGINT PRIMARY KEY COMMENT 'Order ID',
    -- User id
    user_id BIGINT COMMENT 'User ID',
    -- Order total price
    total_price DECIMAL(10, 2) COMMENT 'Total Price',
    -- Order status
    order_status VARCHAR(50) COMMENT 'Order Status',
    -- Creation time, cannot be null
    create_time TIMESTAMP NOT NULL COMMENT 'Creation Time',
    -- Update time
    update_time TIMESTAMP COMMENT 'Update Time'
);

-- Table comment
COMMENT ON TABLE TBL_orders IS 'Order Information Table';
