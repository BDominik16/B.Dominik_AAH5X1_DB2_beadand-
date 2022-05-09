CREATE or replace PROCEDURE SelectAUTO IS
tipus2 number(10);
begin
SELECT count(auto.típus) into tipus2 FROM auto WHERE típus LIKE 'B%';
dbms_output.put_line('B-vel kezdõdõ autók: ' ||tipus2);
end; 


begin
SelectAUTO;
end;

set SERVEROUTPUT ON