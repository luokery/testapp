-- Insert initial data into TBL_users

-- Insert user 1
INSERT INTO TBL_users (user_id, user_name, password, create_time, update_time)
VALUES (1001, 'user1', 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert user 2
INSERT INTO TBL_users (user_id, user_name, password, create_time, update_time)
VALUES (1002, 'testuser', 'secure', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert initial data into TBL_orders
INSERT INTO TBL_orders (order_id, user_id, total_price, order_status, create_time, update_time) VALUES
(2001, 1001, 100.00, 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2002, 1002, 250.50, 'SHIPPED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
