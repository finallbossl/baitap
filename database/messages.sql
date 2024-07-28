USE `study-with-me`;

INSERT INTO `messages` (`subject`, `email`, `createdBy`, `updatedBy`, `status`, `message`)
VALUES
('First Subject', 'first@example.com', 'user1', 'user1', 'ACTIVE', 'This is the first message.'),
('Second Subject', 'second@example.com', 'user2', 'user2', 'ACTIVE', 'This is the second message.'),
('Third Subject', 'third@example.com', 'user3', 'user3', 'IN_ACTIVE', 'This is the third message.'),
('Fourth Subject', 'fourth@example.com', 'user4', 'user4', 'ACTIVE', 'This is the fourth message.'),
('Fifth Subject', 'fifth@example.com', 'user5', 'user5', 'ACTIVE', 'This is the fifth message.');
