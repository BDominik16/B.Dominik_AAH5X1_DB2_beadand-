create or replace package AUTOPackage as 
procedure DeleteFromAUTO(evjaratBE in number);
procedure InsertAuto (szin in char,evjarat in number,tipus in char,rendszam in char, id in number); 
procedure SelectAUTO;
procedure updates (ujszin char,fajta char,preferaltmodositasok in out number);
end;