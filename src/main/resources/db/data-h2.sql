-- Insert initial data into TBL_users

-- Insert user 1
INSERT INTO TBL_users (user_id, user_name, password, create_time, update_time, record_status)
VALUES ('USER_1001', 'user1', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE');

-- Insert user 2
INSERT INTO TBL_users (user_id, user_name, password, create_time, update_time, record_status)
VALUES ('USER_1002', 'testuser', 'secure', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE');

-- Insert initial data into TBL_orders
INSERT INTO TBL_orders (order_id, user_id, total_price, order_status, create_time, update_time, record_status) VALUES
( 'ORDER_2001', 'USER_1001', 100.00, 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE'),
( 'ORDER_2002', 'USER_1002', 250.50, 'SHIPPED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE');
