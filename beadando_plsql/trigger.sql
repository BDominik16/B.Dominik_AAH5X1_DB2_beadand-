CREATE OR REPLACE TRIGGER id
    BEFORE INSERT
    ON auto
    FOR EACH ROW
BEGIN
    IF :new.id > 3
    THEN
        :new.id := 2;
    END IF;
END;