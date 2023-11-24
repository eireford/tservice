INSERT INTO users (user_id, username, email, first_name, last_name)
SELECT '12345678-1234-1234-1234-123456789012'::uuid, 'test', 'test@test.com', 'test', 'test'
WHERE NOT EXISTS (
    SELECT 1
    FROM users
    WHERE user_id = '12345678-1234-1234-1234-123456789012'::uuid
);
