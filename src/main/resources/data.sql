-- Step 1: Create user accounts (patients + doctors both need a linked app_user)
INSERT INTO app_user (username, password, provider_type)
VALUES
    ('aarav.sharma@example.com', 'temp_password', 'EMAIL'),
    ('diya.patel@example.com', 'temp_password', 'EMAIL'),
    ('dishant.verma@example.com', 'temp_password', 'EMAIL'),
    ('neha.iyer@example.com', 'temp_password', 'EMAIL'),
    ('kabir.singh@example.com', 'temp_password', 'EMAIL'),
    ('rakesh.mehta@example.com', 'temp_password', 'EMAIL'),
    ('sneha.kapoor@example.com', 'temp_password', 'EMAIL'),
    ('arjun.nair@example.com', 'temp_password', 'EMAIL');

-- Step 2: Patients — user_id matches the app_user rows inserted above, in order (1-5)
INSERT INTO patient (user_id, name, gender, birth_date, email, blood_group, created_at)
VALUES
    ((SELECT id FROM app_user WHERE username = 'aarav.sharma@example.com'), 'Aarav Sharma', 'MALE', '1990-05-10', 'aarav.sharma@example.com', 'O_POSITIVE', NOW()),
    ((SELECT id FROM app_user WHERE username = 'diya.patel@example.com'), 'Diya Patel', 'FEMALE', '1995-08-20', 'diya.patel@example.com', 'A_POSITIVE', NOW()),
    ((SELECT id FROM app_user WHERE username = 'dishant.verma@example.com'), 'Dishant Verma', 'MALE', '1988-03-15', 'dishant.verma@example.com', 'A_POSITIVE', NOW()),
    ((SELECT id FROM app_user WHERE username = 'neha.iyer@example.com'), 'Neha Iyer', 'FEMALE', '1992-12-01', 'neha.iyer@example.com', 'AB_POSITIVE', NOW()),
    ((SELECT id FROM app_user WHERE username = 'kabir.singh@example.com'), 'Kabir Singh', 'MALE', '1993-07-11', 'kabir.singh@example.com', 'O_POSITIVE', NOW());

-- Step 3: Doctors — user_id matches the remaining app_user rows (6-8)
INSERT INTO doctor (user_id, name, specialization, email)
VALUES
    ((SELECT id FROM app_user WHERE username = 'rakesh.mehta@example.com'), 'Dr. Rakesh Mehta', 'Cardiology', 'rakesh.mehta@example.com'),
    ((SELECT id FROM app_user WHERE username = 'sneha.kapoor@example.com'), 'Dr. Sneha Kapoor', 'Dermatology', 'sneha.kapoor@example.com'),
    ((SELECT id FROM app_user WHERE username = 'arjun.nair@example.com'), 'Dr. Arjun Nair', 'Orthopedics', 'arjun.nair@example.com');

-- Step 4: Appointments — reference patient.id and doctor.id (their own PKs) via email lookups, not app_user ids
INSERT INTO appointment (appointment_time, reason, doctor_user_id, patient_id)
VALUES
    ('2025-07-01 10:30:00', 'General Checkup', (SELECT id FROM doctor WHERE email = 'rakesh.mehta@example.com'), (SELECT id FROM patient WHERE email = 'diya.patel@example.com')),
    ('2025-07-02 11:00:00', 'Skin Rash', (SELECT id FROM doctor WHERE email = 'sneha.kapoor@example.com'), (SELECT id FROM patient WHERE email = 'diya.patel@example.com')),
    ('2025-07-03 09:45:00', 'Knee Pain', (SELECT id FROM doctor WHERE email = 'arjun.nair@example.com'), (SELECT id FROM patient WHERE email = 'dishant.verma@example.com')),
    ('2025-07-04 14:00:00', 'Follow-up Visit', (SELECT id FROM doctor WHERE email = 'rakesh.mehta@example.com'), (SELECT id FROM patient WHERE email = 'aarav.sharma@example.com')),
    ('2025-07-05 16:15:00', 'Consultation', (SELECT id FROM doctor WHERE email = 'rakesh.mehta@example.com'), (SELECT id FROM patient WHERE email = 'neha.iyer@example.com')),
    ('2025-07-06 08:30:00', 'Allergy Treatment', (SELECT id FROM doctor WHERE email = 'sneha.kapoor@example.com'), (SELECT id FROM patient WHERE email = 'kabir.singh@example.com'));