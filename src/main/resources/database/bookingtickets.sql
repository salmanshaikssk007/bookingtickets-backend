-- Create Users Table
CREATE TABLE users (
    id SERIAL PRIMARY KEY, -- Auto-incrementing primary key
    username VARCHAR(50) NOT NULL UNIQUE, -- Unique username
    email VARCHAR(100) NOT NULL UNIQUE, -- Unique email
    password VARCHAR(255) NOT NULL, -- Encrypted password
    role VARCHAR(20) NOT NULL CHECK (role IN ('USER', 'ADMIN')), -- Role can be USER or ADMIN
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Creation timestamp
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Last update timestamp
);

-- Create Events Table
CREATE TABLE events (
    id SERIAL PRIMARY KEY, -- Auto-incrementing primary key
    name VARCHAR(100) NOT NULL, -- Event name
    description TEXT, -- Optional event description
    venue VARCHAR(100) NOT NULL, -- Event location
    event_date DATE NOT NULL, -- Date of the event
    total_seats INT NOT NULL CHECK (total_seats > 0), -- Total seats must be positive
    available_seats INT NOT NULL CHECK (available_seats >= 0), -- Available seats cannot be negative
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Creation timestamp
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Last update timestamp
);

-- Create Bookings Table
CREATE TABLE bookings (
    id SERIAL PRIMARY KEY, -- Auto-incrementing primary key
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE, -- FK to Users table
    event_id INT NOT NULL REFERENCES events(id) ON DELETE CASCADE, -- FK to Events table
    booked_seats INT NOT NULL CHECK (booked_seats > 0), -- Seats booked must be positive
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Booking timestamp
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Creation timestamp
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Last update timestamp
);

-- Insert Data into Users Table (test)
INSERT INTO users (username, email, password, role) 
VALUES 
('john_doe', 'john@example.com', 'securepassword', 'USER'),
('admin_user', 'admin@example.com', 'adminpassword', 'ADMIN');

-- Insert Data into Events Table
INSERT INTO events (name, description, venue, event_date, total_seats, available_seats) 
VALUES 
('Music Concert', 'An exciting music event.', 'Auditorium A', '2024-12-31', 500, 500),
('Drama Play', 'A thrilling drama performance.', 'Theatre B', '2025-01-15', 200, 200);

-- Insert Data into Bookings Table
INSERT INTO bookings (user_id, event_id, booked_seats) 
VALUES 
(1, 1, 2), -- User 1 booked 2 seats for Event 1
(2, 2, 4); -- User 2 booked 4 seats for Event 2