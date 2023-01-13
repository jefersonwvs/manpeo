INSERT INTO tbl_person(name, birth_date) VALUES('Machado de Assis', '1839-06-21');
INSERT INTO tbl_person(name, birth_date) VALUES('Lima Barreto', '1881-05-13');
INSERT INTO tbl_person(name, birth_date) VALUES('José de Alencar', '1829-05-01');

INSERT INTO tbl_address(street, number, zip_code, city, is_main, person_id)
    VALUES('Rua da Alfândega', '123', '20070-005', 'Rio de Janeiro', true, 1);
INSERT INTO tbl_address(street, number, zip_code, city, is_main, person_id)
    VALUES('Av. Pres. Wilson', '203', '20030-021', 'Rio de Janeiro', false, 1);

INSERT INTO tbl_address(street, number, zip_code, city, is_main, person_id)
    VALUES('Rua Visconde de Pirajá', '595', '20030-021', 'Rio de Janeiro', true, 2);
