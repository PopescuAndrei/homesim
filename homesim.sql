-- Trebuie verificat
CREATE TABLE IF NOT EXISTS APPLIANCES (
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  POSES varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  NODE_ID bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY `FK_NODE_ID` (`NODE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;


INSERT INTO APPLIANCES (ID, POSES, `TYPE`, NODE_ID) VALUES
(1, 'ArmsHigh +HandlingDoor', 'Cabinet', 79),
(2, '+Leaning +HandlingDrawer ArmsLow', 'Drawer', 79),
(4, 'ArmsNinety', 'Oven', 38),
(5, '+HandlingDoor ArmsOut', 'Refridgerator', 39),
(6, 'Sitting', 'Chair', 42),
(7, 'Sitting', 'Chair', 43),
(10, 'Sitting', 'Sofa', 47),
(11, 'Sitting', 'Sofa', 50),
(12, 'Sitting', 'PC', 46),
(13, 'Liedown', 'Bed', 31),
(15, 'ArmsOut +HandlingDoor', 'Closet', 34),
(16, 'Sitting', 'Toilet', 26),
(17, 'ArmsOut Kneeling', 'WashingMachine', 26),
(18, 'ArmsOut', 'Tumbler', 26),
(19, NULL, 'Shower', 19),
(20, 'Sitting', 'Toilet', 18),
(21, NULL, 'Outside', 2),
(22, 'Sitting', 'Sofa', 48),
(23, 'Sitting', 'Sofa', 49),
(24, '+Handlingdoor Armsreaching', 'Bookshelf', 86),
(25, NULL, 'Shoerack', 5),
(26, NULL, 'ClothesBin', 20),
(27, 'ArmsOut +HandlingDoor', 'Jacketrack', 6),
(28, 'ArmsOut', 'BathSink', 23),
(29, 'ArmsOut', 'BathSink', 25),
(30, 'Bending ArmsLow +HandlingDoor', 'Dishwasher', 80),
(31, 'ArmsNinety', 'Bench', 80),
(32, 'Liedown', 'Sofa2', 82),
(33, 'Liedown', 'Sofa2', 83),
(34, 'Sitting', 'ReadingChair', 54),
(35, 'Sitting', 'ReadingChair', 55);

CREATE TABLE IF NOT EXISTS EDGES (
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  A_ID bigint(20) DEFAULT NULL,
  B_ID bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY `FK_A_ID` (A_ID),
  KEY `FK_B_ID` (B_ID)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=149 ;

INSERT INTO EDGES (ID, A_ID, B_ID) VALUES
(3, 4, 3),
(4, 3, 1),
(5, 2, 1),
(6, 5, 3),
(7, 1, 5),
(8, 4, 5),
(9, 5, 6),
(10, 4, 6),
(11, 7, 6),
(12, 7, 4),
(13, 35, 29),
(14, 65, 29),
(16, 28, 29),
(17, 27, 29),
(21, 74, 37),
(22, 43, 37),
(23, 75, 37),
(24, 41, 37),
(25, 38, 37),
(26, 38, 41),
(28, 39, 41),
(29, 75, 41),
(30, 43, 75),
(31, 42, 75),
(32, 51, 74),
(33, 48, 51),
(34, 47, 51),
(35, 77, 51),
(36, 76, 51),
(37, 49, 76),
(38, 52, 76),
(39, 52, 77),
(40, 62, 52),
(41, 66, 52),
(42, 44, 66),
(43, 65, 66),
(44, 35, 66),
(45, 46, 44),
(47, 65, 44),
(49, 12, 27),
(50, 28, 27),
(51, 11, 27),
(52, 26, 12),
(53, 11, 12),
(54, 13, 11),
(55, 10, 11),
(56, 14, 13),
(57, 9, 13),
(58, 9, 10),
(59, 67, 9),
(60, 8, 9),
(61, 7, 8),
(62, 68, 67),
(63, 69, 68),
(64, 78, 69),
(65, 71, 72),
(66, 73, 72),
(67, 68, 73),
(68, 30, 28),
(69, 32, 30),
(70, 34, 30),
(71, 33, 34),
(72, 33, 32),
(73, 31, 33),
(74, 15, 14),
(75, 17, 15),
(76, 25, 15),
(77, 24, 25),
(78, 18, 17),
(79, 21, 17),
(80, 22, 17),
(81, 23, 22),
(82, 24, 22),
(83, 24, 23),
(84, 21, 22),
(85, 20, 21),
(86, 19, 20),
(87, 74, 64),
(88, 64, 51),
(89, 61, 64),
(90, 63, 64),
(91, 62, 64),
(92, 63, 62),
(93, 59, 63),
(94, 56, 63),
(95, 56, 61),
(96, 63, 61),
(97, 53, 56),
(98, 55, 56),
(99, 58, 59),
(100, 57, 58),
(101, 55, 58),
(102, 54, 57),
(103, 60, 57),
(104, 60, 56),
(105, 60, 53),
(106, 54, 60),
(107, 76, 48),
(108, 52, 49),
(109, 50, 52),
(110, 50, 77),
(111, 47, 77),
(112, 80, 41),
(113, 79, 41),
(114, 80, 79),
(115, 38, 80),
(116, 39, 79),
(117, 39, 75),
(118, 44, 81),
(119, 46, 81),
(120, 82, 81),
(121, 83, 81),
(122, 8, 67),
(123, 10, 67),
(124, 8, 14),
(125, 9, 14),
(126, 21, 18),
(127, 80, 39),
(128, 38, 39),
(129, 84, 65),
(130, 74, 84),
(131, 51, 84),
(132, 29, 84),
(133, 85, 84),
(134, 85, 65),
(135, 29, 85),
(136, 85, 37),
(137, 42, 39),
(138, 65, 35),
(139, 25, 17),
(140, 22, 18),
(141, 24, 17),
(142, 13, 10),
(143, 35, 86),
(144, 65, 86),
(145, 66, 86),
(146, 44, 86),
(147, 74, 87),
(148, 42, 87);

CREATE TABLE IF NOT EXISTS NODES (
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  POSX int(11) NOT NULL,
  POSY int(11) NOT NULL,
  ROOM_ID bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY `FK_ROOM_ID` (ROOM_ID)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=88 ;

INSERT INTO NODES (ID, POSX, POSY, ROOM_ID) VALUES
(1, 107, 672, 1),
(2, 78, 672, 1),
(3, 141, 672, 1),
(4, 182, 664, 1),
(5, 142, 694, 1),
(6, 199, 690, 1),
(7, 208, 656, 1),
(8, 224, 634, 1),
(9, 239, 603, 1),
(10, 246, 569, 1),
(11, 246, 544, 1),
(12, 224, 507, 1),
(13, 219, 566, 1),
(14, 202, 577, 1),
(15, 167, 574, 1),
(17, 133, 558, 1),
(18, 138, 526, 1),
(19, 66, 472, 1),
(20, 74, 502, 1),
(21, 96, 522, 1),
(22, 86, 555, 1),
(23, 70, 587, 1),
(24, 103, 589, 1),
(25, 139, 589, 1),
(26, 208, 505, 1),
(27, 251, 504, 1),
(28, 278, 473, 1),
(29, 251, 436, 1),
(30, 305, 474, 1),
(31, 407, 505, 1),
(32, 344, 476, 1),
(33, 382, 478, 1),
(34, 340, 498, 1),
(35, 297, 393, 1),
(37, 166, 333, 1),
(38, 148, 397, 1),
(39, 84, 353, 1),
(41, 123, 365, 1),
(42, 93, 296, 1),
(43, 149, 296, 1),
(44, 378, 337, 1),
(46, 440, 329, 1),
(47, 297, 285, 1),
(48, 289, 218, 1),
(49, 337, 216, 1),
(50, 333, 285, 1),
(51, 267, 252, 1),
(52, 358, 255, 1),
(53, 256, 109, 1),
(54, 251, 60, 1),
(55, 317, 113, 1),
(56, 286, 111, 1),
(57, 291, 51, 1),
(58, 316, 74, 1),
(59, 358, 112, 1),
(60, 264, 82, 1),
(61, 283, 150, 1),
(62, 367, 188, 1),
(63, 328, 151, 1),
(64, 264, 186, 1),
(65, 301, 339, 1),
(66, 361, 315, 1),
(67, 279, 610, 1),
(68, 321, 616, 1),
(69, 328, 671, 1),
(71, 415, 680, 1),
(72, 445, 673, 1),
(73, 441, 606, 1),
(74, 182, 181, 1),
(75, 119, 328, 1),
(76, 313, 233, 1),
(77, 312, 269, 1),
(78, 362, 679, 1),
(79, 91, 395, 1),
(80, 119, 397, 1),
(81, 422, 366, 1),
(82, 407, 404, 1),
(83, 440, 407, 1),
(84, 251, 308, 1),
(85, 215, 339, 1),
(86, 338, 393, 1),
(87, 85, 178, 1);

CREATE TABLE IF NOT EXISTS NODES_NODES (
  NODES_ID bigint(20) NOT NULL,
  NEIGHBORS_ID bigint(20) NOT NULL,
  UNIQUE KEY `UK_NEIGHBORS_ID` (NEIGHBORS_ID),
  KEY `FK_NODES_ID` (NODES_ID),
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS NODE_OBJECTS (
  ID bigint(20) NOT NULL,
  POSES varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  NODE_ID bigint(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  KEY `FK_NODE_IDE` (NODE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS ROOMS (
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  NAME varchar(255) DEFAULT NULL,
  OWNER int(11) NOT NULL,
  PRIV varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

INSERT INTO ROOMS (ID, NAME, OWNER, PRIV) VALUES
(1, 'Living room', -1, '0'),
(2, 'Bathroom', -1, '1'),
(3, 'Bedroom 1', 0, '0'),
(4, 'Bedroom 2', 1, '0');

ALTER TABLE APPLIANCES
  ADD CONSTRAINT FK_APP_NODE FOREIGN KEY (NODE_ID) REFERENCES NODES (ID);

ALTER TABLE EDGES
  ADD CONSTRAINT FK_EDGE_A_NODE FOREIGN KEY (A_ID) REFERENCES NODES (ID),
  ADD CONSTRAINT FK_EDGE_B_NODE FOREIGN KEY (B_ID) REFERENCES NODES (ID);

ALTER TABLE NODES
  ADD CONSTRAINT FK_ROOM_NODE FOREIGN KEY (ROOM_ID) REFERENCES ROOMS (ID);

ALTER TABLE NODES_NODES
  ADD CONSTRAINT FK_NEIGHBOOR_NODE FOREIGN KEY (NEIGHBORS_ID) REFERENCES NODES (ID),
  ADD CONSTRAINT FK_NODES_NODE FOREIGN KEY (NODES_ID) REFERENCES NODES (ID);

ALTER TABLE NODE_OBJECTS
  ADD CONSTRAINT FK_NODE_OBJECT FOREIGN KEY (NODE_ID) REFERENCES NODES (ID);

  
CREATE TABLE IF NOT EXISTS NEEDS (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	NEED_NAME VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS AGENTS (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	AGENT_NAME VARCHAR(50) NOT NULL,
	AVATAR_IMG VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS AGENT_NEEDS (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	AGENT_ID INT NOT NULL,
	NEED_ID INT NOT NULL,
	FOREIGN KEY (AGENT_ID) REFERENCES AGENTS(ID),
	FOREIGN KEY (NEED_ID) REFERENCES NEEDS(ID),
	DECAY_RATE DOUBLE NOT NULL
);

INSERT INTO NEEDS(NEED_NAME) VALUES('Energy');
INSERT INTO NEEDS(NEED_NAME) VALUES('Hunger');
INSERT INTO NEEDS(NEED_NAME) VALUES('Bladder');
INSERT INTO NEEDS(NEED_NAME) VALUES('Hygiene');
INSERT INTO NEEDS(NEED_NAME) VALUES('Fun');

CREATE TABLE IF NOT EXISTS SCENARIOS (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20) NOT NULL,
	ACTIVITIES_FILE VARCHAR(255) NOT NULL,
	HOUSE_FILE VARCHAR(255) NOT NULL,
	SENSOR_FILE VARCHAR(255) NOT NULL,
	STARTING_POINT INT NOT NULL,
	DAYS INT NOT NULL,
	WALKING_SPEED INT NOT NULL
);

CREATE TABLE IF NOT EXISTS SCENARIOS_AGENTS (
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	AGENT_ID INT NOT NULL,
	SCENARIO_ID INT NOT NULL,
	FOREIGN KEY (SCENARIO_ID) REFERENCES SCENARIOS(ID),
	FOREIGN KEY (AGENT_ID) REFERENCES AGENTS(ID)
);

ALTER TABLE AGENTS
ADD COLUMN LOC_X INT;

ALTER TABLE AGENTS
ADD COLUMN LOC_Y INT;