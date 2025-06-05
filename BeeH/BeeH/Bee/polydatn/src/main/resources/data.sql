-- Thêm tài khoản admin mặc định
INSERT INTO admins (email, password, name, role) 
VALUES ('linh@gmail.com', '123123', 'Admin', 'ADMIN')
ON CONFLICT (email) DO NOTHING; 