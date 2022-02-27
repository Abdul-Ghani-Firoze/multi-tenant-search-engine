CREATE DATABASE tenant_db_2;

CREATE TABLE `tbl_documents` (
  `doc_id` int(11) NOT NULL,
  `doc_name` varchar(100) NOT NULL,
  `doc_description` varchar(200) NOT NULL
);

INSERT INTO `tbl_documents` (`doc_id`, `doc_name`, `doc_description`) VALUES
(1, 'ELK Tutorial.pdf', 'This file contains a tutorial on ELK Stack'),
(2, 'Accounts & Expenses.xlsx', 'This file contains accounts and expenses');

CREATE DATABASE tenant_db_2;

CREATE TABLE `tbl_documents` (
  `doc_id` int(11) NOT NULL,
  `doc_name` varchar(100) NOT NULL,
  `doc_description` varchar(200) NOT NULL
);

INSERT INTO `tbl_documents` (`doc_id`, `doc_name`, `doc_description`) VALUES
(1, 'ELK Tutorial.pdf', 'This file contains a tutorial on ELK Stack'),
(2, 'Accounts & Expenses.xlsx', 'This file contains accounts and expenses');